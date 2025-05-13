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
    // Tabela utilizada para armazenar os escopos gerados ao longo da análise.
    TabelaDeSimbolos tabela;

    // Geração de um conjunto de escopos que serão analisados de forma auxiliar
    // no decorrer da análise.
    static Escopos escoposAninhados = new Escopos();
    
    // Criação da lista que armazenará os erros identificados pelo analisador.
    static List<String> errosSemanticos = new ArrayList<>();
    
    TabelaDeSimbolos tabelaEscopo;

    // Método que adiciona a variável que está sendo analisado à tabela.
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
        // Inicialização do programa.
        tabela = new TabelaDeSimbolos();
        return super.visitPrograma(ctx);
    }

    @Override
    public Void visitDeclaracoes(T3ParserParser.DeclaracoesContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();
        
        // Verifica a declaração atual.
        for (T3ParserParser.Decl_local_globalContext declaracao : ctx.decl_local_global())
            visitDecl_local_global(declaracao);
        
        return super.visitDeclaracoes(ctx);
    }

    @Override
    public Void visitDecl_local_global(T3ParserParser.Decl_local_globalContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();
        
        // Identifica se é uma declaração local ou global.
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

        // Tenta identificar uma declaração
        if (ctx.getText().contains("declare")) {
            tipoVariavel = ctx.variavel().tipo().getText();

            // Adiciona a variável atual na tabela (a verificação de variável repetida ocorre
            // no método adicionaVariavelTabela.
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
            // Verifica se a variável já foi declarada.
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
            // Caso a variável não tenha sido declarada, informa o erro.
            if (!tabela.existe(varNome)) {
                adicionaErroSemantico(ctx.identificador().getStart(), "identificador " + ctx.identificador().getText() + " nao declarado");
            } else {
                // Caso contrário, identifica o tipo da variável para as condições posteriores.
                Tipos varTipo = verificarTipo(tabela, varNome);
                
                // Caso o tipo seja inteiro ou real, é utilizada a função verificaCompatibilidade para verificar
                // se o valor a ser trabalhado é real ou não (mais informações sobre a função podem ser encontradas
                // no arquivo VisitorUtils.java.
                if (varTipo == Tipos.INTEIRO || varTipo == Tipos.REAL) {
                    if (!verificaCompatibilidade(varTipo, tipoExpressao)) {
                        // Caso o tipo da expressão (restante da parcela sendo analisada) seja diferente de inteiro,
                        // não é possível tratar o valor como um número real, logo, os tipos são incompatíveis, pois
                        // seria a situação de estar comparando um número com um literal, por exemplo.
                        if (tipoExpressao != Tipos.INTEIRO) {
                            adicionaErroSemantico(ctx.identificador().getStart(), "atribuicao nao compativel para " + ctx.identificador().getText());
                        }
                    }
                // Caso a expressão analisada não tenha números que precisem ser tratados de maneira especial,
                // apenas verifica se os tipos são diferentes.
                } else if (varTipo != tipoExpressao)
                    adicionaErroSemantico(ctx.identificador().getStart(), "atribuicao nao compativel para " + ctx.identificador().getText());
            }
        }
        
        return super.visitCmdAtribuicao(ctx);
    }
    
    
    

    // Método auxiliar utilizado para adicionar um novo erro identificado na lista.
    public static void adicionaErroSemantico(Token tok, String mensagem) {
        int linha = tok.getLine();
        
        // Verifica se o erro já foi identificado para poder adicioná-lo à lista.
        if (!errosSemanticos.contains("Linha " + linha + ": " + mensagem)) 
            errosSemanticos.add(String.format("Linha %d: %s", linha, mensagem));
    }
    
    // Método auxiliar que verifica a compatibilidade entre operadores aritméticos.
    // Caso a operação envolva pelo menos um valor real, a operação deve ser tratada
    // como uma operação entre números reais, mesmo que um deles seja um inteiro.
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
    
    // Método auxiliar que verifica a compatibilidade entre operadores para tratá-los
    // como uma operação lógica.
    public static boolean verificaCompatibilidadeLogica(Tipos T1, Tipos T2) {
        boolean flag = false;
        
        if (T1 == Tipos.INTEIRO && T2 == Tipos.REAL)
            flag = true;
        else if (T1 == Tipos.REAL && T2 == Tipos.INTEIRO)
            flag = true;

        return flag;
    }
                    
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.Exp_aritmeticaContext ctx) {
        // A variável que será retornada ao fim da execução é inicializada com o tipo
        // do primeiro elemento que será verificado, para fins de comparação.
        Tipos tipoRetorno = verificarTipo(tabela, ctx.termo().get(0));
                
        for (var termoArit : ctx.termo()) {
            // Esta outra variável recebe os tipos dos outros termos da expressão.
            Tipos tipoAtual = verificarTipo(tabela, termoArit);
            
            // Com o auxílio do método declarado anteriormente, o programa verifica se deve tratar a
            // verificação atual como uma operação entre números reais.
            if ((verificaCompatibilidade(tipoAtual, tipoRetorno)) && (tipoAtual != Tipos.INVALIDO))
                tipoRetorno = Tipos.REAL;
            else
                tipoRetorno = tipoAtual;
        }

        return tipoRetorno;
    }

    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.TermoContext ctx) {
        // A variável que será retornada ao fim da execução é inicializada com o tipo
        // do primeiro elemento que será verificado, para fins de comparação.
        Tipos tipoRetorno = verificarTipo(tabela, ctx.fator().get(0));
                
        for (T3ParserParser.FatorContext fatorArit : ctx.fator()) {
            // Esta outra variável recebe os tipos dos outros termos da expressão.
            Tipos tipoAtual = verificarTipo(tabela, fatorArit);
            
            // Com o auxílio do método declarado anteriormente, o programa verifica se deve tratar a
            // verificação atual como uma operação entre números reais.
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
        // Identifica se é uma parcela unária ou não unária.
        if (ctx.parcela_unario() != null)
            return verificarTipo(tabela, ctx.parcela_unario());
        else
            return verificarTipo(tabela, ctx.parcela_nao_unario());
    }

    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.Parcela_unarioContext ctx) {
        Tipos tipoRetorno;
        String nome;
        
        if (ctx.identificador() != null) {
            // Obtém o nome da variável atual.
            nome = ctx.identificador().getText();
            
            // Caso a variável já tenha sido declarada, apenas retorna o tipo associado a ela.
            if (tabela.existe(nome))
                tipoRetorno = tabela.verificar(nome);
            // Caso contrário, utiliza uma tabela auxiliar para prosseguir com a verificação. Se a variável não
            // tiver sido declarada, utiliza o método adicionaErroSemantico para verificar se o erro já foi
            // exibido e, caso ainda não tenha sido, o adiciona à lista.
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

        // Utiliza uma lógica semelhante à verificação de tipo anterior, verificando a existência da variável
        // e tentando adicioná-la à lista de erros.
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

        // Para expressões lógicas, a ideia resume-se apenas em verificar se os tipos analisados
        // são diferentes.
        for (T3ParserParser.Termo_logicoContext termoLogico : ctx.termo_logico()) {
            Tipos tipoAtual = verificarTipo(tabela, termoLogico);
            if (tipoRetorno != tipoAtual && tipoAtual != Tipos.INVALIDO)
                tipoRetorno = Tipos.INVALIDO;
        }

        return tipoRetorno;
    }

    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T3ParserParser.Termo_logicoContext ctx) {
        Tipos tipoRetorno = verificarTipo(tabela, ctx.fator_logico(0));

        // Para expressões lógicas, a ideia resume-se apenas em verificar se os tipos analisados
        // são diferentes.
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

            // Semelhante ao que foi feito com as expressões aritméticas, ocorre uma verificação
            // para saber se a expressão atual pode ser tratada como uma operação lógica.
            if (tipoRetorno == tipoAtual || verificaCompatibilidadeLogica(tipoRetorno, tipoAtual))
                tipoRetorno = Tipos.LOGICO;
            else
                tipoRetorno = Tipos.INVALIDO;
        }

        return tipoRetorno;

    }

    // Verificação padrão de tipos de variáveis a partir da tabela.
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, String nomeVar) {
        return tabela.verificar(nomeVar);
    }
}
