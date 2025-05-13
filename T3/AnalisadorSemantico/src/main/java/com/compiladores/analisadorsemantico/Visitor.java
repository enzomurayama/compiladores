/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.compiladores.analisadorsemantico;

/**
 *
 * @author lucas
 */
public class Visitor extends T3ParserBaseVisitor<void> {
    // Gerenciador de escopos aninhados
    private Escopos escopos;
    
    // Método auxiliar para obter o escopo atual
    private TabelaDeSimbolos getEscopoAtual() {
        return escopos.obterEscopoAtual();
    }

    // Método auxiliar para mapear string de tipo para TipoT3
    private TipoT3 mapearTipo(String tipoStr, Token tipoToken) {
        switch (tipoStr) {
            case "literal":
                return TipoT3.LITERAL;
            case "inteiro":
                return TipoT3.INTEIRO;
            case "real":
                return TipoT3.REAL;
            case "logico":
                return TipoT3.LOGICO;
            default:
                adicionaErroSemantico(tipoToken, "tipo " + tipoStr + " não declarado");
                return TipoT3.INVALIDO;
        }
    }

    // Método auxiliar para declarar uma variável na tabela de símbolos
    private void declararVariavel(String nome, String tipoStr, Token nomeToken, Token tipoToken) {
        TabelaDeSimbolos escopoAtual = getEscopoAtual();
        TipoT3 tipo = mapearTipo(tipoStr, tipoToken);

        if (tipo != TipoT3.INVALIDO) {
            if (escopoAtual.existe(nome)) {
                adicionaErroSemantico(nomeToken, "identificador " + nome + " já declarado anteriormente");
            } else {
                escopoAtual.adicionar(nome, tipo);
            }
        }
    }

    // Método auxiliar para verificar se uma variável foi declarada
    private void verificarDeclaracao(String nome, Token token) {
        if (!getEscopoAtual().existe(nome)) {
            adicionaErroSemantico(token, "identificador " + nome + " não declarado");
        }
    }

    @Override
    public Void visitPrograma(Analisador SintaticoLADistinctParser.ProgramaContext ctx) {
        // Inicializa os escopos
        escopos = new Escopos();
        escopos.criarNovoEscopo();
        return super.visitPrograma(ctx);
    }

    @Override
    public Void visitDecl_bloco(AnalisadorSintaticoLADistinctParser.Decl_blocoContext ctx) {
        // Processa todas as declarações no bloco
        for (AnalisadorSintaticoLADistinctParser.Decl_itemContext decl : ctx.decl_item()) {
            visitDecl_item(decl);
        }
        return null;
    }

    @Override
    public Void visitDecl_item(AnalisadorSintaticoLADistinctParser.Decl_itemContext ctx) {
        // Redireciona para a declaração específica
        if (ctx.decl_variavel() != null) {
            visitDecl_variavel(ctx.decl_variavel());
        } else if (ctx.decl_constante() != null) {
            visitDecl_constante(ctx.decl_constante());
        } else if (ctx.decl_tipo() != null) {
            visitDecl_tipo(ctx.decl_tipo());
        } else if (ctx.decl_global() != null) {
            visitDecl_global(ctx.decl_global());
        }
        return null;
    }

    @Override
    public Void visitDecl_variavel(AnalisadorSintaticoLADistinctParser.Decl_variavelContext ctx) {
        // Processa cada grupo de variáveis declarado
        for (AnalisadorSintaticoLADistinctParser.Lista_varsContext lista : ctx.lista_vars()) {
            String tipoStr = lista.tipo_var().getText();
            Token tipoToken = lista.tipo_var().getStart();

            for (AnalisadorSintaticoLADistinctParser.Id_listaContext id : lista.id_lista()) {
                String nome = id.getText();
                declararVariavel(nome, tipoStr, id.getStart(), tipoToken);
            }
        }
        return null;
    }

    @Override
    public Void visitDecl_constante(AnalisadorSintaticoLADistinctParser.Decl_constanteContext ctx) {
        String nome = ctx.IDENT().getText();
        String tipoStr = ctx.tipo_basico().getText();
        Token nomeToken = ctx.IDENT().getSymbol();
        Token tipoToken = ctx.tipo_basico().getStart();

        declararVariavel(nome, tipoStr, nomeToken, tipoToken);
        return null;
    }

    @Override
    public Void visitCmd_leia(AnalisadorSintaticoLADistinctParser.Cmd_leiaContext ctx) {
        // Verifica se todas as variáveis lidas foram declaradas
        for (AnalisadorSintaticoLADistinctParser.Id_listaContext id : ctx.id_lista()) {
            verificarDeclaracao(id.getText(), id.getStart());
        }
        return null;
    }

    @Override
    public Void visitCmd_escreva(AnalisadorSintaticoLADistinctParser.Cmd_escrevaContext ctx) {
        // Verifica o tipo de cada expressão escrita
        for (AnalisadorSintaticoLADistinctParser.ExpressaoContext expr : ctx.expressao()) {
            verificarTipo(getEscopoAtual(), expr);
        }
        return null;
    }

    @Override
    public Void visitCmd_enquanto(AnalisadorSintaticoLADistinctParser.Cmd_enquantoContext ctx) {
        // Verifica o tipo da expressão condicional
        verificarTipo(getEscopoAtual(), ctx.expressao());
        return super.visitCmd_enquanto(ctx);
    }

    @Override
    public Void visitCmd_atribuicao(AnalisadorSintaticoLADistinctParser.Cmd_atribuicaoContext ctx) {
        TabelaDeSimbolos escopoAtual = getEscopoAtual();
        String nomeVar = ctx.id_lista().getText();
        Token varToken = ctx.id_lista().getStart();
        TipoT3 tipoExpr = verificarTipo(escopoAtual, ctx.expressao());

        if (tipoExpr != TipoT3.INVALIDO) {
            // Verifica se a variável foi declarada
            if (!escopoAtual.existe(nomeVar)) {
                adicionaErroSemantico(varToken, "identificador " + nomeVar + " não declarado");
            } else {
                TipoT3 tipoVar = verificarTipo(escopoAtual, nomeVar);

                // Verifica compatibilidade para tipos numéricos
                if (tipoVar == TipoT3.INTEIRO || tipoVar == TipoT3.REAL) {
                    if (!verificaCompatibilidade(tipoVar, tipoExpr)) {
                        if (tipoExpr != TipoT3.INTEIRO) {
                            adicionaErroSemantico(varToken, "atribuição não compatível para " + nomeVar);
                        }
                    }
                } else if (tipoVar != tipoExpr) {
                    // Verifica compatibilidade para outros tipos
                    adicionaErroSemantico(varToken, "atribuição não compatível para " + nomeVar);
                }
            }
        }
        return null;
    }
}
