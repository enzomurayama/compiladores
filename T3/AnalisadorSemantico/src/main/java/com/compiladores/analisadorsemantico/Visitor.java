/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.compiladores.analisadorsemantico;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author lucas
 */
public class Visitor extends T3ParserBaseVisitor<Void> {
    TabelaDeSimbolos tabela;


    static Escopos escoposAninhados = new Escopos();
    
    static List<String> errosSemanticos = new ArrayList<>();
    
    TabelaDeSimbolos tabelaEscopo;

    public void adicionaVariavelTabela(String nome, String tipo, Token nomeT, Token tipoT) {
        tabelaEscopo = escoposAninhados.obterEscopoAtual();

        Tipos tipoItem;

        switch (tipo) {
            case "literal":
                tipoItem = Tipos.LITERAL;
                break;
            case "inteiro":
                tipoItem = Tipos.INTEIRO;
                break;
            case "real":
                tipoItem = Tipos.REAL;
                break;
            case "logico":
                tipoItem = Tipos.LOGICO;
                break;
            default:
                tipoItem = Tipos.INVALIDO;
                break;
        }

        // Caso o tipo seja inválido, exibe a mensagem de que o tipo não foi declarado.
        if (tipoItem == Tipos.INVALIDO)
            adicionaErroSemantico(tipoT, "tipo " + tipo + " nao declarado");

        // Verifica se a variável já foi declarada, ou seja, já foi adicionada na tabela.
        if (!tabelaEscopo.existe(nome))
            tabelaEscopo.adicionar(nome, tipoItem);
        else
            adicionaErroSemantico(nomeT, "identificador " + nome + " ja declarado anteriormente");
    }

    @Override
    public Void visitPrograma(T3ParserParser.ProgramaContext ctx) {
        tabela = new TabelaDeSimbolos();
        return super.visitPrograma(ctx);
    }

    @Override
    public Void visitDeclaracoes(T3ParserParser.DeclaracoesContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();
        
        for (T3ParserParser.Decl_local_globalContext declaracao : ctx.decl_local_global())
            visitDecl_local_global(declaracao);
        
        return super.visitDeclaracoes(ctx);
    }

    @Override
    public Void visitDecl_local_global(T3ParserParser.Decl_local_globalContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();
        
        if (ctx.declaracao_local() != null)
            visitDeclaracao_local(ctx.declaracao_local());
        else if (ctx.declaracao_global() != null)
            visitDeclaracao_global(ctx.declaracao_global());

        return super.visitDecl_local_global(ctx);
    }

    @Override
    public Void visitDeclaracao_local(T3ParserParser.Declaracao_localContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();

        String tipoVariavel;
        String nomeVariavel;

        if (ctx.getText().contains("declare")) {
            tipoVariavel = ctx.variavel().tipo().getText();


            for (T3ParserParser.IdentificadorContext ident : ctx.variavel().identificador()) {
                nomeVariavel = ident.getText();
                adicionaVariavelTabela(nomeVariavel, tipoVariavel, ident.getStart(), ctx.variavel().tipo().getStart());
            }
        }

        return super.visitDeclaracao_local(ctx);
    }

    @Override
    public Void visitCmdLeia(T3ParserParser.CmdLeiaContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();

        for (T3ParserParser.IdentificadorContext id : ctx.identificador())
            if (!tabela.existe(id.getText()))
                adicionaErroSemantico(id.getStart(), "identificador " + id.getText() + " nao declarado");

        return super.visitCmdLeia(ctx);
    }

    @Override
    public Void visitCmdEscreva(T3ParserParser.CmdEscrevaContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();
        
        Tipos tipo;

        for (T3ParserParser.ExpressaoContext expressao : ctx.expressao())
            tipo = verificarTipo(tabela, expressao);

        return super.visitCmdEscreva(ctx);
    }

    @Override
    public Void visitCmdEnquanto(T3ParserParser.CmdEnquantoContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();
        
        Tipos tipo = verificarTipo(tabela, ctx.expressao());
        
        return super.visitCmdEnquanto(ctx);
    }

    @Override
    public Void visitCmdAtribuicao(T3ParserParser.CmdAtribuicaoContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();
        
        Tipos tipoExpressao = verificarTipo(tabela, ctx.expressao());
        
        String varNome = ctx.identificador().getText();
        
        if (tipoExpressao != Tipos.INVALIDO) {
            if (!tabela.existe(varNome)) {
                adicionaErroSemantico(ctx.identificador().getStart(), "identificador " + ctx.identificador().getText() + " nao declarado");
            } else {
                Tipos varTipo = verificarTipo(tabela, varNome);
                
                
                if (varTipo == Tipos.INTEIRO || varTipo == Tipos.REAL) {
                    if (!verificaCompatibilidade(varTipo, tipoExpressao)) {
                        
                        if (tipoExpressao != Tipos.INTEIRO) {
                            adicionaErroSemantico(ctx.identificador().getStart(), "atribuicao nao compativel para " + ctx.identificador().getText());
                        }
                    }
   
                } else if (varTipo != tipoExpressao)
                    adicionaErroSemantico(ctx.identificador().getStart(), "atribuicao nao compativel para " + ctx.identificador().getText());
            }
        }
        
        return super.visitCmdAtribuicao(ctx);
    }
    
 
    public static void adicionaErroSemantico(Token tok, String mensagem) {
        int linha = tok.getLine();
        
        if (!errosSemanticos.contains("Linha " + linha + ": " + mensagem)) 
            errosSemanticos.add(String.format("Linha %d: %s", linha, mensagem));
    }

    public static boolean verificaCompatibilidade(Tipos T1, Tipos T2) {
        boolean flag = false;
        
        if (T1 == Tipos.INTEIRO && T2 == Tipos.REAL)
            flag = true;
        else if (T1 == Tipos.REAL && T2 == Tipos.INTEIRO)
            flag = true;
        else if (T1 == Tipos.REAL && T2 == Tipos.REAL)
            flag = true;
        
        return flag;
    }
    
    public static boolean verificaCompatibilidadeLogica(Tipos T1, Tipos T2) {
        boolean flag = false;
        
        if (T1 == Tipos.INTEIRO && T2 == Tipos.REAL)
            flag = true;
        else if (T1 == Tipos.REAL && T2 == Tipos.INTEIRO)
            flag = true;

        return flag;
    }
                    
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.Exp_aritmeticaContext ctx) {

        Tipos tipoRetorno = verificarTipo(tabela, ctx.termo().get(0));
                
        for (var termoArit : ctx.termo()) {
            Tipos tipoAtual = verificarTipo(tabela, termoArit);
            
            if ((verificaCompatibilidade(tipoAtual, tipoRetorno)) && (tipoAtual != Tipos.INVALIDO))
                tipoRetorno = Tipos.REAL;
            else
                tipoRetorno = tipoAtual;
        }

        return tipoRetorno;
    }

    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.TermoContext ctx) {

        Tipos tipoRetorno = verificarTipo(tabela, ctx.fator().get(0));
                
        for (T3ParserParser.FatorContext fatorArit : ctx.fator()) {
            Tipos tipoAtual = verificarTipo(tabela, fatorArit);
            
            if ((verificaCompatibilidade(tipoAtual, tipoRetorno)) && (tipoAtual != Tipos.INVALIDO))
                tipoRetorno = Tipos.REAL;
            else
                tipoRetorno = tipoAtual;
        }
        
        return tipoRetorno;
    }

    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.FatorContext ctx) {
        Tipos tipoRetorno = null;
        
        for (T3ParserParser.ParcelaContext parcela : ctx.parcela())
            tipoRetorno = verificarTipo(tabela, parcela);

        return tipoRetorno;
    }

    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.ParcelaContext ctx) {
        if (ctx.parcela_unario() != null)
            return verificarTipo(tabela, ctx.parcela_unario());
        else
            return verificarTipo(tabela, ctx.parcela_nao_unario());
    }

    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.Parcela_unarioContext ctx) {
        Tipos tipoRetorno;
        String nome;
        
        if (ctx.identificador() != null) {
            nome = ctx.identificador().getText();
            
            if (tabela.existe(nome))
                tipoRetorno = tabela.verificar(nome);

            else {
                TabelaDeSimbolos tabelaAux = Visitor.escoposAninhados.percorrerEscoposAninhados().get(Visitor.escoposAninhados.percorrerEscoposAninhados().size() - 1);
                if (!tabelaAux.existe(nome)) {
                    adicionaErroSemantico(ctx.identificador().getStart(), "identificador " + ctx.identificador().getText() + " nao declarado");
                    tipoRetorno = Tipos.INVALIDO;
                } else 
                    tipoRetorno = tabelaAux.verificar(nome);
            }
        } else if (ctx.NUM_INT() != null)
            tipoRetorno = Tipos.INTEIRO;
        else if (ctx.NUM_REAL() != null)
            tipoRetorno = Tipos.REAL;
        else
            tipoRetorno = verificarTipo(tabela, ctx.expressao().get(0));

        return tipoRetorno;
    }

    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.Parcela_nao_unarioContext ctx) {
        Tipos tipoRetorno;
        String nome;

        if (ctx.identificador() != null) {
            nome = ctx.identificador().getText();
        
            if (!tabela.existe(nome)) {
                adicionaErroSemantico(ctx.identificador().getStart(), "identificador " + ctx.identificador().getText() + " nao declarado");
                tipoRetorno = Tipos.INVALIDO;
            } else 
                tipoRetorno = tabela.verificar(ctx.identificador().getText());
        } else
            tipoRetorno = Tipos.LITERAL;

        return tipoRetorno;
    }

    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.ExpressaoContext ctx) {
        Tipos tipoRetorno = verificarTipo(tabela, ctx.termo_logico(0));


        for (T3ParserParser.Termo_logicoContext termoLogico : ctx.termo_logico()) {
            Tipos tipoAtual = verificarTipo(tabela, termoLogico);
            if (tipoRetorno != tipoAtual && tipoAtual != Tipos.INVALIDO)
                tipoRetorno = Tipos.INVALIDO;
        }

        return tipoRetorno;
    }

    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.Termo_logicoContext ctx) {
        Tipos tipoRetorno = verificarTipo(tabela, ctx.fator_logico(0));


        for (T3ParserParser.Fator_logicoContext fatorLogico : ctx.fator_logico()) {
            Tipos tipoAtual = verificarTipo(tabela, fatorLogico);
            if (tipoRetorno != tipoAtual && tipoAtual != Tipos.INVALIDO)
                tipoRetorno = Tipos.INVALIDO;
        }
        return tipoRetorno;
    }

    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.Fator_logicoContext ctx) {
        Tipos tipoRetorno = verificarTipo(tabela, ctx.parcela_logica());
        return tipoRetorno;

    }

    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.Parcela_logicaContext ctx) {
        Tipos tipoRetorno;

        if (ctx.exp_relacional() != null)
            tipoRetorno = verificarTipo(tabela, ctx.exp_relacional());
         else
            tipoRetorno = Tipos.LOGICO;

        return tipoRetorno;

    }

    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.Exp_relacionalContext ctx) {
        Tipos tipoRetorno = verificarTipo(tabela, ctx.exp_aritmetica().get(0));

        if (ctx.exp_aritmetica().size() > 1) {
            Tipos tipoAtual = verificarTipo(tabela, ctx.exp_aritmetica().get(1));

            if (tipoRetorno == tipoAtual || verificaCompatibilidadeLogica(tipoRetorno, tipoAtual))
                tipoRetorno = Tipos.LOGICO;
            else
                tipoRetorno = Tipos.INVALIDO;
        }

        return tipoRetorno;

    }

    public static Tipos verificarTipo(TabelaDeSimbolos tabela, String nomeVar) {
        return tabela.verificar(nomeVar);
    }
}
