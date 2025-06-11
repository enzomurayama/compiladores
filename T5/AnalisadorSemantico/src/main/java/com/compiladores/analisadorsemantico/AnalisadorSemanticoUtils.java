package com.compiladores.analisadorsemantico;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import org.antlr.v4.runtime.Token;

import static com.compiladores.analisadorsemantico.Visitor.dadosFuncaoProcedimento;
import static com.compiladores.analisadorsemantico.Visitor.escoposAninhados;

public class AnalisadorSemanticoUtils {

    // Lista global utilizada para armazenar os erros semânticos encontrados.
    public static List<String> errosSemanticos = new ArrayList<>();

    /**
     * Adiciona um erro semântico à lista, garantindo que não haja duplicidade.
     *
     * @param tok      Token associado ao erro
     * @param mensagem Descrição do erro semântico
     */
    public static void adicionaErroSemantico(Token tok, String mensagem) {
        int linha = tok.getLine();
        String erro = String.format("Linha %d: %s", linha, mensagem);

        if (!errosSemanticos.contains(erro)) {
            errosSemanticos.add(erro);
        }
    }

    /**
     * Verifica a compatibilidade entre dois tipos em operações aritméticas.
     * Se um dos tipos for real, a operação é considerada de ponto flutuante.
     */
    public static boolean verificaCompatibilidade(Tipos T1, Tipos T2) {
        return (T1 == Tipos.INTEIRO && T2 == Tipos.REAL)
            || (T1 == Tipos.REAL && T2 == Tipos.INTEIRO)
            || (T1 == Tipos.REAL && T2 == Tipos.REAL);
    }

    /**
     * Verifica a compatibilidade de tipos em expressões relacionais.
     * Considera compatíveis inteiros e reais.
     */
    public static boolean verificaCompatibilidadeLogica(Tipos T1, Tipos T2) {
        return (T1 == Tipos.INTEIRO && T2 == Tipos.REAL)
            || (T1 == Tipos.REAL && T2 == Tipos.INTEIRO);
    }

    /**
     * Reduz o nome de um identificador (vetor ou função), removendo a parte após '[' ou '('.
     *
     * @param nome    Nome completo do identificador
     * @param simbolo Símbolo que indica a separação (ex: "[" ou "(")
     * @return Nome reduzido
     */
    public static String reduzNome(String nome, String simbolo) {
        if (nome.contains(simbolo)) {
            int cont = 0;
            while (!nome.substring(cont).startsWith(simbolo)) {
                cont++;
            }
            nome = nome.substring(0, cont);
        }
        return nome;
    }

    /**
     * Retorna o tipo associado a um literal, baseado na tabela de tipos ou no valor literal.
     *
     * @param tabela       Tabela de símbolos com tipos definidos
     * @param tipoRetorno  Nome do tipo como string
     * @return Tipo correspondente
     */
    public static Tipos confereTipo(HashMap<String, ArrayList<String>> tabela, String tipoRetorno) {
        if (tipoRetorno.startsWith("^")) {
            tipoRetorno = tipoRetorno.substring(1);
        }

        if (tabela.containsKey(tipoRetorno)) return Tipos.REGISTRO;
        return switch (tipoRetorno) {
            case "literal" -> Tipos.LITERAL;
            case "inteiro" -> Tipos.INTEIRO;
            case "real" -> Tipos.REAL;
            case "logico" -> Tipos.LOGICO;
            default -> Tipos.INVALIDO;
        };
    }

    // Abaixo seguem métodos recursivos para verificação de tipos em estruturas da linguagem

    // Verifica o tipo de uma expressão aritmética
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T5GrammarParser.Exp_aritmeticaContext ctx) {
        Tipos tipoRetorno = verificarTipo(tabela, ctx.termo().get(0));
        for (var termo : ctx.termo()) {
            Tipos tipoAtual = verificarTipo(tabela, termo);
            // Se os tipos são compatíveis, o resultado é REAL; senão, assume o tipo atual
            if (verificaCompatibilidade(tipoAtual, tipoRetorno) && tipoAtual != Tipos.INVALIDO)
                tipoRetorno = Tipos.REAL;
            else
                tipoRetorno = tipoAtual;
        }
        return tipoRetorno;
    }

    // Verifica o tipo de um termo, que é composto por fatores
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T5GrammarParser.TermoContext ctx) {
        Tipos tipoRetorno = verificarTipo(tabela, ctx.fator().get(0));
        for (var fator : ctx.fator()) {
            Tipos tipoAtual = verificarTipo(tabela, fator);
            if (verificaCompatibilidade(tipoAtual, tipoRetorno) && tipoAtual != Tipos.INVALIDO)
                tipoRetorno = Tipos.REAL;
            else
                tipoRetorno = tipoAtual;
        }
        return tipoRetorno;
    }

    // Verifica o tipo de um fator, que é composto por parcelas
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T5GrammarParser.FatorContext ctx) {
        Tipos tipoRetorno = null;
        for (var parcela : ctx.parcela()) {
            tipoRetorno = verificarTipo(tabela, parcela);
            // Se for registro, tenta resolver o nome e verificar tipo diretamente
            if (tipoRetorno == Tipos.REGISTRO) {
                String nome = reduzNome(parcela.getText(), "(");
                tipoRetorno = verificarTipo(tabela, nome);
            }
        }
        return tipoRetorno;
    }

    // Verifica o tipo de uma parcela (unária ou não unária)
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T5GrammarParser.ParcelaContext ctx) {
        if (ctx.parcela_unario() != null)
            return verificarTipo(tabela, ctx.parcela_unario());
        return verificarTipo(tabela, ctx.parcela_nao_unario());
    }

    // Verifica o tipo de uma parcela unária
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T5GrammarParser.Parcela_unarioContext ctx) {
        Tipos tipoRetorno = null;
        String nome;

        if (ctx.identificador() != null) {
            nome = ctx.identificador().getText();
            // Verifica se há uso de vetor (dimensão)
            if (ctx.identificador().dimensao() != null &&
                !ctx.identificador().dimensao().exp_aritmetica().isEmpty()) {
                nome = ctx.identificador().IDENT().get(0).getText();
            }

            // Verifica se o identificador existe na tabela de símbolos
            if (tabela.existe(nome)) {
                tipoRetorno = tabela.verificar(nome);
            } else {
                TabelaDeSimbolos escopoAtual = escoposAninhados.obterEscopoAtual();
                if (!escopoAtual.existe(nome)) {
                    adicionaErroSemantico(ctx.identificador().getStart(), "identificador " + nome + " nao declarado");
                    tipoRetorno = Tipos.INVALIDO;
                } else {
                    tipoRetorno = escopoAtual.verificar(nome);
                }
            }
        } else if (ctx.IDENT() != null) { // chamada de função
            String func = ctx.IDENT().getText();
            if (dadosFuncaoProcedimento.containsKey(func)) {
                List<Tipos> parametros = dadosFuncaoProcedimento.get(func);
                // Verifica compatibilidade entre argumentos e parâmetros
                if (parametros.size() == ctx.expressao().size()) {
                    for (int i = 0; i < ctx.expressao().size(); i++) {
                        if (parametros.get(i) != verificarTipo(tabela, ctx.expressao().get(i)))
                            adicionaErroSemantico(ctx.expressao().get(i).getStart(), "incompatibilidade de parametros na chamada de " + func);
                    }
                    tipoRetorno = parametros.get(parametros.size() - 1); // tipo de retorno
                } else {
                    adicionaErroSemantico(ctx.IDENT().getSymbol(), "incompatibilidade de parametros na chamada de " + func);
                    tipoRetorno = Tipos.INVALIDO;
                }
            } else {
                tipoRetorno = Tipos.INVALIDO;
            }
        } else if (ctx.NUM_INT() != null)
            tipoRetorno = Tipos.INTEIRO;
        else if (ctx.NUM_REAL() != null)
            tipoRetorno = Tipos.REAL;
        else
            tipoRetorno = verificarTipo(tabela, ctx.expressao().get(0)); // expressão entre parênteses

        return tipoRetorno;
    }

    // Verifica o tipo de uma parcela não unária
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T5GrammarParser.Parcela_nao_unarioContext ctx) {
        if (ctx.identificador() != null) {
            String nome = ctx.identificador().getText();
            if (!tabela.existe(nome)) {
                adicionaErroSemantico(ctx.identificador().getStart(), "identificador " + nome + " nao declarado");
                return Tipos.INVALIDO;
            }
            return tabela.verificar(nome);
        }
        return Tipos.LITERAL; // strings e outros literais
    }

    // Verifica o tipo de uma expressão lógica
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T5GrammarParser.ExpressaoContext ctx) {
        Tipos tipoRetorno = verificarTipo(tabela, ctx.termo_logico(0));
        for (var termo : ctx.termo_logico()) {
            Tipos tipoAtual = verificarTipo(tabela, termo);
            if (tipoRetorno != tipoAtual && tipoAtual != Tipos.INVALIDO)
                tipoRetorno = Tipos.INVALIDO;
        }
        return tipoRetorno;
    }

    // Verifica o tipo de um termo lógico
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T5GrammarParser.Termo_logicoContext ctx) {
        Tipos tipoRetorno = verificarTipo(tabela, ctx.fator_logico(0));
        for (var fator : ctx.fator_logico()) {
            Tipos tipoAtual = verificarTipo(tabela, fator);
            if (tipoRetorno != tipoAtual && tipoAtual != Tipos.INVALIDO)
                tipoRetorno = Tipos.INVALIDO;
        }
        return tipoRetorno;
    }

    // Verifica o tipo de um fator lógico
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T5GrammarParser.Fator_logicoContext ctx) {
        return verificarTipo(tabela, ctx.parcela_logica());
    }

    // Verifica o tipo de uma parcela lógica
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T5GrammarParser.Parcela_logicaContext ctx) {
        if (ctx.exp_relacional() != null)
            return verificarTipo(tabela, ctx.exp_relacional());
        return Tipos.LOGICO;
    }

    // Verifica o tipo de uma expressão relacional
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T5GrammarParser.Exp_relacionalContext ctx) {
        Tipos tipo1 = verificarTipo(tabela, ctx.exp_aritmetica().get(0));
        if (ctx.exp_aritmetica().size() > 1) {
            Tipos tipo2 = verificarTipo(tabela, ctx.exp_aritmetica().get(1));
            if (tipo1 == tipo2 || verificaCompatibilidadeLogica(tipo1, tipo2))
                return Tipos.LOGICO;
            else
                return Tipos.INVALIDO;
        }
        return tipo1;
    }

    // Verifica o tipo de um identificador simples
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T5GrammarParser.IdentificadorContext ctx) {
        return tabela.verificar(ctx.IDENT().get(0).getText());
    }

    // Verifica tipo dado apenas o nome da variável
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, String nomeVar) {
        return tabela.verificar(nomeVar);
    }

}
