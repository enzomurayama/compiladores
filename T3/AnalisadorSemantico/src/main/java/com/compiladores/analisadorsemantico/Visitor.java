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

    // Adiciona uma variável à tabela de símbolos, verificando a validade do tipo e declarações duplicadas
    public void adicionaVariavelTabela(String nome, String tipo, Token nomeT, Token tipoT) {
        tabelaEscopo = escoposAninhados.obterEscopoAtual();

        Tipos tipoItem;

        // Mapeia o tipo em string para o tipo enum para análise semântica
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

        // Reporta um erro se o tipo for inválido
        if (tipoItem == Tipos.INVALIDO)
            adicionaErroSemantico(tipoT, "tipo " + tipo + " nao declarado");

        // Verifica se há declarações duplicadas de variáveis no escopo atual
        if (!tabelaEscopo.existe(nome))
            tabelaEscopo.adicionar(nome, tipoItem);
        else
            adicionaErroSemantico(nomeT, "identificador " + nome + " ja declarado anteriormente");
    }

    // Inicializa a tabela de símbolos para o programa
    @Override
    public Void visitPrograma(T3ParserParser.ProgramaContext ctx) {
        tabela = new TabelaDeSimbolos();
        return super.visitPrograma(ctx);
    }

    // Processa as declarações locais e globais do programa
    @Override
    public Void visitDeclaracoes(T3ParserParser.DeclaracoesContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();
        
        // Visita todas as declarações locais ou globais no contexto
        for (T3ParserParser.Decl_local_globalContext declaracao : ctx.decl_local_global())
            visitDecl_local_global(declaracao);
        
        return super.visitDeclaracoes(ctx);
    }

    // Decide se a declaração é local ou global e a processa adequadamente
    @Override
    public Void visitDecl_local_global(T3ParserParser.Decl_local_globalContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();
        
        if (ctx.declaracao_local() != null)
            visitDeclaracao_local(ctx.declaracao_local());
        else if (ctx.declaracao_global() != null)
            visitDeclaracao_global(ctx.declaracao_global());

        return super.visitDecl_local_global(ctx);
    }

    // Processa declarações locais, como variáveis, e as adiciona à tabela de símbolos
    @Override
    public Void visitDeclaracao_local(T3ParserParser.Declaracao_localContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();

        String tipoVariavel;
        String nomeVariavel;

        // Verifica se a declaração contém a palavra-chave "declare"
        if (ctx.getText().contains("declare")) {
            tipoVariavel = ctx.variavel().tipo().getText();

            // Adiciona cada identificador à tabela de símbolos
            for (T3ParserParser.IdentificadorContext ident : ctx.variavel().identificador()) {
                nomeVariavel = ident.getText();
                adicionaVariavelTabela(nomeVariavel, tipoVariavel, ident.getStart(), ctx.variavel().tipo().getStart());
            }
        }

        return super.visitDeclaracao_local(ctx);
    }

    // Verifica se os identificadores no comando de leitura estão declarados
    @Override
    public Void visitCmdLeia(T3ParserParser.CmdLeiaContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();

        // Reporta erro se algum identificador não estiver na tabela de símbolos
        for (T3ParserParser.IdentificadorContext id : ctx.identificador())
            if (!tabela.existe(id.getText()))
                adicionaErroSemantico(id.getStart(), "identificador " + id.getText() + " nao declarado");

        return super.visitCmdLeia(ctx);
    }

    // Verifica o tipo das expressões no comando de escrita
    @Override
    public Void visitCmdEscreva(T3ParserParser.CmdEscrevaContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();
        
        // Analisa o tipo de cada expressão no comando
        for (T3ParserParser.ExpressaoContext expressao : ctx.expressao())
            verificarTipo(tabela, expressao);

        return super.visitCmdEscreva(ctx);
    }

    // Verifica o tipo da expressão no comando enquanto (loop)
    @Override
    public Void visitCmdEnquanto(T3ParserParser.CmdEnquantoContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();
        
        // Verifica se a expressão do loop é válida
        Tipos tipo = verificarTipo(tabela, ctx.expressao());
        
        return super.visitCmdEnquanto(ctx);
    }

    // Verifica a compatibilidade de tipos em uma atribuição
    @Override
    public Void visitCmdAtribuicao(T3ParserParser.CmdAtribuicaoContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();
        
        // Verifica o tipo da expressão à direita da atribuição
        Tipos tipoExpressao = verificarTipo(tabela, ctx.expressao());
        
        String varNome = ctx.identificador().getText();
        
        if (tipoExpressao != Tipos.INVALIDO) {
            // Reporta erro se a variável não estiver declarada
            if (!tabela.existe(varNome)) {
                adicionaErroSemantico(ctx.identificador().getStart(), "identificador " + ctx.identificador().getText() + " nao declarado");
            } else {
                Tipos varTipo = verificarTipo(tabela, varNome);
                
                // Verifica compatibilidade de tipos para números
                if (varTipo == Tipos.INTEIRO || varTipo == Tipos.REAL) {
                    if (!verificaCompatibilidade(varTipo, tipoExpressao)) {
                        if (tipoExpressao != Tipos.INTEIRO) {
                            adicionaErroSemantico(ctx.identificador().getStart(), "atribuicao nao compativel para " + ctx.identificador().getText());
                        }
                    }
                } else if (varTipo != tipoExpressao)
                    // Reporta erro se os tipos não forem iguais para outros casos
                    adicionaErroSemantico(ctx.identificador().getStart(), "atribuicao nao compativel para " + ctx.identificador().getText());
            }
        }
        
        return super.visitCmdAtribuicao(ctx);
    }
    
    // Adiciona um erro semântico à lista, incluindo a linha do token
    public static void adicionaErroSemantico(Token tok, String mensagem) {
        int linha = tok.getLine();
        
        // Evita duplicação de erros na mesma linha
        if (!errosSemanticos.contains("Linha " + linha + ": " + mensagem)) 
            errosSemanticos.add(String.format("Linha %d: %s", linha, mensagem));
    }

    // Verifica compatibilidade entre tipos numéricos (inteiro e real)
    public static boolean verificaCompatibilidade(Tipos T1, Tipos T2) {
        boolean flag = false;
        
        // Define regras de compatibilidade entre inteiro e real
        if (T1 == Tipos.INTEIRO && T2 == Tipos.REAL)
            flag = true;
        else if (T1 == Tipos.REAL && T2 == Tipos.INTEIRO)
            flag = true;
        else if (T1 == Tipos.REAL && T2 == Tipos.REAL)
            flag = true;
        
        return flag;
    }
    
    // Verifica compatibilidade para operações lógicas entre tipos numéricos
    public static boolean verificaCompatibilidadeLogica(Tipos T1, Tipos T2) {
        boolean flag = false;
        
        // Define compatibilidade para operações lógicas entre inteiro e real
        if (T1 == Tipos.INTEIRO && T2 == Tipos.REAL)
            flag = true;
        else if (T1 == Tipos.REAL && T2 == Tipos.INTEIRO)
            flag = true;

        return flag;
    }
                    
    // Determina o tipo de uma expressão aritmética
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.Exp_aritmeticaContext ctx) {
        // Começa verificando o tipo do primeiro termo
        Tipos tipoRetorno = verificarTipo(tabela, ctx.termo().get(0));
                
        // Analisa cada termo para determinar o tipo resultante
        for (var termoArit : ctx.termo()) {
            Tipos tipoAtual = verificarTipo(tabela, termoArit);
            
            // Promove para real se os tipos forem compatíveis
            if ((verificaCompatibilidade(tipoAtual, tipoRetorno)) && (tipoAtual != Tipos.INVALIDO))
                tipoRetorno = Tipos.REAL;
            else
                tipoRetorno = tipoAtual;
        }

        return tipoRetorno;
    }

    // Determina o tipo de um termo em uma expressão aritmética
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.TermoContext ctx) {
        // Começa verificando o tipo do primeiro fator
        Tipos tipoRetorno = verificarTipo(tabela, ctx.fator().get(0));
                
        // Analisa cada fator para determinar o tipo resultante
        for (T3ParserParser.FatorContext fatorArit : ctx.fator()) {
            Tipos tipoAtual = verificarTipo(tabela, fatorArit);
            
            // Promove para real se os tipos forem compatíveis
            if ((verificaCompatibilidade(tipoAtual, tipoRetorno)) && (tipoAtual != Tipos.INVALIDO))
                tipoRetorno = Tipos.REAL;
            else
                tipoRetorno = tipoAtual;
        }
        
        return tipoRetorno;
    }

    // Determina o tipo de um fator em uma expressão aritmética
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.FatorContext ctx) {
        Tipos tipoRetorno = null;
        
        // Propaga o tipo da parcela
        for (T3ParserParser.ParcelaContext parcela : ctx.parcela())
            tipoRetorno = verificarTipo(tabela, parcela);

        return tipoRetorno;
    }

    // Determina o tipo de uma parcela, que pode ser unária ou não unária
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.ParcelaContext ctx) {
        if (ctx.parcela_unario() != null)
            return verificarTipo(tabela, ctx.parcela_unario());
        else
            return verificarTipo(tabela, ctx.parcela_nao_unario());
    }

    // Determina o tipo de uma parcela unária (identificador, número ou expressão)
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.Parcela_unarioContext ctx) {
        Tipos tipoRetorno;
        String nome;
        
        if (ctx.identificador() != null) {
            nome = ctx.identificador().getText();
            
            // Verifica se o identificador existe no escopo atual
            if (tabela.existe(nome))
                tipoRetorno = tabela.verificar(nome);
            else {
                // Busca em escopos aninhados se necessário
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

    // Determina o tipo de uma parcela não unária (identificador ou literal)
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.Parcela_nao_unarioContext ctx) {
        Tipos tipoRetorno;
        String nome;

        if (ctx.identificador() != null) {
            nome = ctx.identificador().getText();
        
            // Verifica se o identificador existe no escopo atual
            if (!tabela.existe(nome)) {
                adicionaErroSemantico(ctx.identificador().getStart(), "identificador " + ctx.identificador().getText() + " nao declarado");
                tipoRetorno = Tipos.INVALIDO;
            } else 
                tipoRetorno = tabela.verificar(ctx.identificador().getText());
        } else
            tipoRetorno = Tipos.LITERAL;

        return tipoRetorno;
    }

    // Determina o tipo de uma expressão lógica
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.ExpressaoContext ctx) {
        // Começa verificando o tipo do primeiro termo lógico
        Tipos tipoRetorno = verificarTipo(tabela, ctx.termo_logico(0));

        // Verifica consistência entre os termos lógicos
        for (T3ParserParser.Termo_logicoContext termoLogico : ctx.termo_logico()) {
            Tipos tipoAtual = verificarTipo(tabela, termoLogico);
            if (tipoRetorno != tipoAtual && tipoAtual != Tipos.INVALIDO)
                tipoRetorno = Tipos.INVALIDO;
        }

        return tipoRetorno;
    }

    // Determina o tipo de um termo lógico
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.Termo_logicoContext ctx) {
        // Começa verificando o tipo do primeiro fator lógico
        Tipos tipoRetorno = verificarTipo(tabela, ctx.fator_logico(0));

        // Verifica consistência entre os fatores lógicos
        for (T3ParserParser.Fator_logicoContext fatorLogico : ctx.fator_logico()) {
            Tipos tipoAtual = verificarTipo(tabela, fatorLogico);
            if (tipoRetorno != tipoAtual && tipoAtual != Tipos.INVALIDO)
                tipoRetorno = Tipos.INVALIDO;
        }
        return tipoRetorno;
    }

    // Determina o tipo de um fator lógico
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.Fator_logicoContext ctx) {
        // Propaga o tipo da parcela lógica
        Tipos tipoRetorno = verificarTipo(tabela, ctx.parcela_logica());
        return tipoRetorno;
    }

    // Determina o tipo de uma parcela lógica
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.Parcela_logicaContext ctx) {
        Tipos tipoRetorno;

        if (ctx.exp_relacional() != null)
            tipoRetorno = verificarTipo(tabela, ctx.exp_relacional());
        else
            tipoRetorno = Tipos.LOGICO;

        return tipoRetorno;
    }

    // Determina o tipo de uma expressão relacional
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.Exp_relacionalContext ctx) {
        // Começa verificando o tipo da primeira expressão aritmética
        Tipos tipoRetorno = verificarTipo(tabela, ctx.exp_aritmetica().get(0));

        // Verifica compatibilidade entre as expressões relacionais
        if (ctx.exp_aritmetica().size() > 1) {
            Tipos tipoAtual = verificarTipo(tabela, ctx.exp_aritmetica().get(1));

            if (tipoRetorno == tipoAtual || verificaCompatibilidadeLogica(tipoRetorno, tipoAtual))
                tipoRetorno = Tipos.LOGICO;
            else
                tipoRetorno = Tipos.INVALIDO;
        }

        return tipoRetorno;
    }

    // Obtém o tipo de uma variável da tabela de símbolos
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, String nomeVar) {
        return tabela.verificar(nomeVar);
    }
}