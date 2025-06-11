package com.compiladores.analisadorsemantico;

import static com.compiladores.analisadorsemantico.AnalisadorSemanticoUtils.verificarTipo;

/**
 * Classe que implementa o gerador de código C.
 */
public class GeradorC extends T5GrammarBaseVisitor<Void> {
    // Armazena o código C gerado. 
    private StringBuilder saida = new StringBuilder();
    
    // Tabela de símbolos para o escopo global. 
    private TabelaDeSimbolos tabela = new TabelaDeSimbolos();
    
    // Gerencia escopos aninhados para declarações e blocos. 
    private Escopos escopos = new Escopos();
    
    // Gerencia escopos aninhados para análise semântica. 
    private static Escopos escoposAninhados = new Escopos();
    
    /**
     * Converte um tipo da linguagem T5 para o tipo correspondente em C.
     * @param tipoAuxT5 Tipo da linguagem T5 (ex.: INTEIRO, LITERAL).
     * @return Tipo em C (ex.: "int", "char") ou null se inválido.
     */
    public String converteTipo(Tipos tipoAuxT5) {
        if (tipoAuxT5 == null) return null;
        return switch (tipoAuxT5) {
            case INTEIRO -> "int";
            case LITERAL -> "char";
            case REAL -> "float";
            default -> null;
        };
    }

    /**
     * Converte um tipo da linguagem T5 (em string) para o enum Tipos.
     * @param tipo Tipo da linguagem T5 (ex.: "literal", "inteiro").
     * @return Enum Tipos correspondente ou INVALIDO se não reconhecido.
     */
    public Tipos converteTipos(String tipo) {
        return switch (tipo) {
            case "literal" -> Tipos.LITERAL;
            case "inteiro" -> Tipos.INTEIRO;
            case "real" -> Tipos.REAL;
            case "logico" -> Tipos.LOGICO;
            default -> Tipos.INVALIDO;
        };
    }

    /**
     * Converte um tipo da linguagem T5 para o tipo C correspondente.
     * @param tipo Tipo da linguagem T5 (ex.: "inteiro", "literal").
     * @return Tipo em C (ex.: "int", "char") ou null se inválido.
     */
    public String verificaTipoC(String tipo) {
        return switch (tipo) {
            case "inteiro" -> "int";
            case "literal" -> "char";
            case "real" -> "float";
            default -> null;
        };
    }

    /**
     * Converte um tipo C para o formato de especificador usado em scanf/printf.
     * @param tipo Tipo em C (ex.: "int", "char").
     * @return Especificador de formato (ex.: "d", "s") ou null se inválido.
     */
    public String verificaParamTipo(String tipo) {
        return switch (tipo) {
            case "int" -> "d";
            case "float" -> "f";
            case "char" -> "s";
            default -> null;
        };
    }

    /**
     * Converte um tipo da linguagem T5 para o especificador de formato em C.
     * @param tipoAuxT5 Tipo da linguagem T5 (ex.: INTEIRO, LITERAL).
     * @return Especificador de formato (ex.: "d", "s") ou null se inválido.
     */
    public String verificaParamTipos(Tipos tipoAuxT5) {
        if (tipoAuxT5 == null) return null;
        return switch (tipoAuxT5) {
            case INTEIRO -> "d";
            case REAL -> "f";
            case LITERAL -> "s";
            default -> null;
        };
    }

    /**
     * Verifica se um tipo existe na tabela de símbolos.
     * @param tabela Tabela de símbolos do escopo atual.
     * @param tipo Nome do tipo a verificar.
     * @return true se o tipo existe, false caso contrário.
     */
    public boolean verificaTipoTabela(TabelaDeSimbolos tabela, String tipo) {
        return tabela.existe(tipo);
    }
    
    /**
     * Extrai o limite esquerdo ou direito de um intervalo em uma string.
     * @param str String contendo o intervalo (ex.: "1..5").
     * @param ehEsquerda true para retornar o limite esquerdo, false para o direito.
     * @return Limite extraído como string.
     */
    public String getLimitesCaso(String str, boolean ehEsquerda) {
        String strAux;
        
        if (str.contains(".")) {
            int cont = 0;
            boolean continua = true;
            
            while (continua) {
                strAux = str.substring(cont);
                if (strAux.startsWith(".")) {
                    continua = false;
                } else {
                    cont++;
                }
            }

            strAux = ehEsquerda ? str.substring(0, cont) : str.substring(cont + 2);
        } else {
            strAux = str;
        }

        return strAux;
    }
    
    /**
     * Separa argumentos de uma expressão relacional (ex.: "a=b" em "a" e "b").
     * @param total Expressão completa.
     * @param valor 0 para o primeiro argumento, 1 para o segundo.
     * @return Argumento extraído como string.
     */
    public String separaArg(String total, int valor) {
        String argAux;
        boolean continua = true;
        int cont = 0;

        total = total.substring(1);
        
        while (continua) {
            argAux = total.substring(cont);
            if (argAux.startsWith("=") || argAux.startsWith("<>")) {
                continua = false;
            } else {
                cont++;
            }
        }

        if (valor == 0) {
            argAux = total.substring(0, cont);
        } else {
            total = total.substring(cont + 1);
            cont = 0;
            continua = true;
            while (continua) {
                argAux = total.substring(cont);
                if (argAux.startsWith(")")) {
                    continua = false;
                } else {
                    cont++;
                }
            }
            argAux = total.substring(0, cont);
        }
        
        return argAux;
    }
    
    /**
     * Separa operandos de uma expressão aritmética (ex.: "a+b" em "a" e "b").
     * @param total Expressão completa.
     * @param valor 0 para o primeiro operando, 1 para o segundo.
     * @return Operando extraído como string.
     */
    public String separaExp(String total, int valor) {
        String argAux;
        boolean continua = true;
        int cont = 0;
        
        while (continua) {
            argAux = total.substring(cont);
            if (argAux.startsWith("+") || argAux.startsWith("-") || 
                argAux.startsWith("*") || argAux.startsWith("/")) {
                continua = false;
            } else {
                cont++;
            }
        }

        return valor == 0 ? total.substring(0, cont) : total.substring(cont + 1);
    }
    
    /**
     * Identifica o operador aritmético em uma expressão.
     * @param total Expressão contendo o operador.
     * @return Operador encontrado (+, -, *, /) ou null se não houver.
     */
    public String verificaOp(String total) {
        if (total.contains("+")) return "+";
        if (total.contains("-")) return "-";
        if (total.contains("*")) return "*";
        if (total.contains("/")) return "/";
        return null;
    }
    
    /**
     * Gera o esqueleto do programa C, incluindo headers, declarações globais,
     * função main e corpo do programa.
     * @param ctx Contexto do programa na gramática T5.
     */
    @Override
    public Void visitPrograma(T5GrammarParser.ProgramaContext ctx) {
        saida.append("#include <stdio.h>\n");
        saida.append("#include <stdlib.h>\n\n");
         
        visitDeclaracoes(ctx.declaracoes());
        saida.append("\nint main() {\n");
        visitCorpo(ctx.corpo());
        saida.append("\nreturn 0;\n}\n");

        return null;
    }
    
    /**
     * Gera código C para uma declaração local (variável, constante ou tipo).
     * @param ctx Contexto da declaração local na gramática T5.
     */
    @Override
    public Void visitDeclaracao_local(T5GrammarParser.Declaracao_localContext ctx) {
        if (ctx.valor_constante() != null) {
            // Define constante usando #define
            String str = "#define " + ctx.IDENT().getText() + " " + ctx.valor_constante().getText() + "\n";
            saida.append(str);
        } else if (ctx.tipo() != null) {
            // Declara tipo personalizado (struct) e adiciona à tabela de símbolos
            TabelaDeSimbolos escopoAtual = escopos.obterEscopoAtual();
            escopos.criarNovoEscopo();
            saida.append("typedef struct {\n");
            super.visitRegistro(ctx.tipo().registro());
            escopos.abandonarEscopo();
            escopoAtual.adicionar(ctx.IDENT().getText(), Tipos.REGISTRO, TiposEntrada.VARIAVEL);
            String str = "} " + ctx.IDENT().getText() + ";\n";
            saida.append(str);
        } else if (ctx.variavel() != null) {
            visitVariavel(ctx.variavel());
        }
        
        return null;
    }
    
    /**
     * Gera código C para declaração de variáveis, incluindo tipos básicos, ponteiros e registros.
     * @param ctx Contexto da variável na gramática T5.
     */
    @Override
    public Void visitVariavel(T5GrammarParser.VariavelContext ctx) {
        TabelaDeSimbolos escopoAtual = escopos.obterEscopoAtual();
        boolean tipoEstendido = false;
        String str;

        if (ctx.tipo().tipo_estendido() != null) {
            String nomeVar;
            String tipoVariavel = ctx.tipo().getText();
            Tipos tipoAuxT5;
            boolean ehPonteiro = false;

            if (tipoVariavel.contains("^")) {
                ehPonteiro = true;
                tipoVariavel = tipoVariavel.substring(1);
            }

            if (verificaTipoTabela(escopoAtual, tipoVariavel)) {
                tipoEstendido = true;
                tipoAuxT5 = Tipos.TIPOESTENDIDO;
            } else {
                tipoAuxT5 = converteTipos(tipoVariavel);
                tipoVariavel = converteTipo(tipoAuxT5);
            }

            if (ehPonteiro) {
                tipoVariavel += "*";
            }

            for (T5GrammarParser.IdentificadorContext ictx : ctx.identificador()) {
                nomeVar = ictx.getText();
                if (tipoEstendido) {
                    escopoAtual.adicionar(nomeVar, Tipos.REGISTRO, TiposEntrada.VARIAVEL);
                } else {
                    escopoAtual.adicionar(nomeVar, tipoAuxT5, TiposEntrada.VARIAVEL);
                }

                if (tipoAuxT5 == Tipos.LITERAL) {
                    str = tipoVariavel + " " + nomeVar + "[80];\n";
                    saida.append(str);
                } else {
                    str = tipoVariavel + " " + nomeVar + ";\n";
                    saida.append(str);
                }
            }
        } else {
            escopos.criarNovoEscopo();
            saida.append("struct {\n");
            for (T5GrammarParser.VariavelContext vctx : ctx.tipo().registro().variavel()) {
                visitVariavel(vctx);
            }
            str = "} " + ctx.identificador(0).getText() + ";\n";
            saida.append(str);
            escopos.abandonarEscopo();
            escopoAtual.adicionar(ctx.identificador(0).getText(), Tipos.REGISTRO, TiposEntrada.VARIAVEL);
        }

        return null;
    }
    
    /**
     * Gera código C para declarações globais (procedimentos ou funções).
     * @param ctx Contexto da declaração global na gramática T5.
     */
    @Override
    public Void visitDeclaracao_global(T5GrammarParser.Declaracao_globalContext ctx) {
        TabelaDeSimbolos escopoAtual = escopos.obterEscopoAtual();
        escopos.criarNovoEscopo();
        TabelaDeSimbolos escopoParametros = escopos.obterEscopoAtual();
        String tipo, nomeVariaveis;

        if (ctx.tipo_estendido() != null) {
            saida.append(verificaTipoC(ctx.tipo_estendido().getText()));
        } else {
            saida.append("void");
        }

        String str = " " + ctx.IDENT().getText() + "(";
        saida.append(str);

        if (ctx.parametros() != null) {
            for (T5GrammarParser.ParametroContext pctx : ctx.parametros().parametro()) {
                tipo = verificaTipoC(pctx.tipo_estendido().getText());
                nomeVariaveis = "";

                for (T5GrammarParser.IdentificadorContext ictx : pctx.identificador()) {
                    nomeVariaveis += ictx.getText();
                    escopoParametros.adicionar(ictx.getText(), converteTipos(pctx.tipo_estendido().getText()), TiposEntrada.VARIAVEL);
                }

                if (tipo.equals("char")) {
                    tipo = "char*";
                }
                
                str = tipo + " " + nomeVariaveis;
                saida.append(str);
            }
        }

        saida.append(") {\n");

        if (ctx.tipo_estendido() != null) {
            escopoAtual.adicionar(ctx.IDENT().getText(), converteTipos(ctx.tipo_estendido().getText()), TiposEntrada.FUNCAO);
        } else {
            escopoAtual.adicionar(ctx.IDENT().getText(), Tipos.VOID, TiposEntrada.PROCEDIMENTO);
        }

        for (T5GrammarParser.CmdContext cctx : ctx.cmd()) {
            visitCmd(cctx);
        }

        saida.append("}\n");
        escopos.abandonarEscopo();

        return null;
    }
    
    /**
     * Gera código C para uma parcela não unária (ex.: endereço de variável ou literal).
     * @param ctx Contexto da parcela não unária.
     */
    @Override
    public Void visitParcela_nao_unario(T5GrammarParser.Parcela_nao_unarioContext ctx) {
        if (ctx.identificador() != null) {
            saida.append(ctx.identificador().getText());
        }
        super.visitParcela_nao_unario(ctx);
        return null;
    }
    
    /**
     * Gera código C para uma parcela unária, tratando parênteses adequadamente.
     * @param ctx Contexto da parcela unária.
     */
    @Override
    public Void visitParcela_unario(T5GrammarParser.Parcela_unarioContext ctx) {
        if (!ctx.expressao().isEmpty() && ctx.expressao().get(0).getText().contains("(")) {
            saida.append("(");
            super.visitParcela_unario(ctx);
            saida.append(")");
        } else {
            saida.append(ctx.getText());
        }
        return null;
    }
    
    /**
     * Gera código C para operadores relacionais, convertendo '=' para '==' quando necessário.
     * @param ctx Contexto do operador relacional.
     */
    @Override
    public Void visitOp_relacional(T5GrammarParser.Op_relacionalContext ctx) {
        String strRetorno = ctx.getText().equals("=") ? "==" : ctx.getText();
        saida.append(strRetorno);
        super.visitOp_relacional(ctx);
        return null;
    }
    
    /**
     * Gera código C para o comando de retorno (return).
     * @param ctx Contexto do comando retorne.
     */
    @Override
    public Void visitCmdRetorne(T5GrammarParser.CmdRetorneContext ctx) {
        saida.append("return ");
        super.visitExpressao(ctx.expressao());
        saida.append(";\n");
        return null;
    }
    
    /**
     * Gera código C para uma atribuição, tratando ponteiros, registros e variáveis simples.
     * @param ctx Contexto da atribuição.
     */
    @Override
    public Void visitCmdAtribuicao(T5GrammarParser.CmdAtribuicaoContext ctx) {
        String str;
        tabela = escoposAninhados.obterEscopoAtual();

        if (ctx.getText().contains("^")) {
            // Gera atribuição para ponteiro com desreferência
            str = "*" + ctx.identificador().getText() + " = " + ctx.expressao().getText() + ";\n";
        } else if (ctx.identificador().getText().contains(".") && ctx.getText().contains("\"")) {
            // Usa strcpy para atribuir cadeias em campos de registros
            str = "strcpy(" + ctx.identificador().getText() + "," + ctx.expressao().getText() + ");\n";
        } else {
            // Atribuição direta para variáveis de tipos básicos
            str = ctx.identificador().getText() + " = " + ctx.expressao().getText() + ";\n";
        }
        saida.append(str);
        return null;
    }
    
    /**
     * Gera código C para uma expressão lógica, tratando operadores 'ou'.
     * @param ctx Contexto da expressão lógica.
     */
    @Override
    public Void visitExpressao(T5GrammarParser.ExpressaoContext ctx) {
        if (ctx.termo_logico().size() > 1) {
            for (T5GrammarParser.Termo_logicoContext termoLogico : ctx.termo_logico()) {
                saida.append(" || ");
                visitTermo_logico(termoLogico);
            }
        } else {
            visitTermo_logico(ctx.termo_logico(0));
        }
        return null;
    }
    
    /**
     * Gera código C para um termo lógico, tratando operadores 'e'.
     * @param ctx Contexto do termo lógico.
     */
    @Override 
    public Void visitTermo_logico(T5GrammarParser.Termo_logicoContext ctx) {
        if (ctx.fator_logico().size() > 1) {
            for (T5GrammarParser.Fator_logicoContext fatorLogico : ctx.fator_logico()) {
                saida.append(" && ");
                visitFator_logico(fatorLogico);
            }
        } else {
            visitFator_logico(ctx.fator_logico(0));
        }
        return null;
    }
    
    /**
     * Gera código C para um fator lógico, tratando o operador de negação 'nao'.
     * @param ctx Contexto do fator lógico.
     */
    @Override
    public Void visitFator_logico(T5GrammarParser.Fator_logicoContext ctx) {
        if (ctx.getText().contains("nao")) {
            saida.append("!");
        }
        visitParcela_logica(ctx.parcela_logica());
        return null;
    }
    
    /**
     * Gera código C para operadores aritméticos (*, /).
     * @param ctx Contexto do operador aritmético.
     */
    @Override
    public Void visitOp2(T5GrammarParser.Op2Context ctx) {
        saida.append(ctx.getText());
        super.visitOp2(ctx);
        return null;
    }
    
    /**
     * Gera código C para uma parcela lógica, traduzindo 'verdadeiro'/'falso' para 'true'/'false'.
     * @param ctx Contexto da parcela lógica.
     */
    @Override
    public Void visitParcela_logica(T5GrammarParser.Parcela_logicaContext ctx) {
        if (ctx.getText().contains("falso")) {
            saida.append("false");
        } else if (ctx.getText().contains("verdadeiro")) {
            saida.append("true");
        } else {
            visitExp_relacional(ctx.exp_relacional());
        }
        return null;
    }
    
    /**
     * Gera código C para uma expressão relacional, tratando operadores como '==' e '!='.
     * @param ctx Contexto da expressão relacional.
     */
    @Override
    public Void visitExp_relacional(T5GrammarParser.Exp_relacionalContext ctx) {
        if (ctx.op_relacional() != null) {
            visitExp_aritmetica(ctx.exp_aritmetica(0));
            String op = ctx.op_relacional().getText().equals("=") ? "==" : ctx.op_relacional().getText();
            saida.append(op);
            visitExp_aritmetica(ctx.exp_aritmetica(1));
        } else {
            String expAtual = ctx.exp_aritmetica(0).getText();
            if (expAtual.contains("=") && !expAtual.contains("<=") && !expAtual.contains(">=")) {
                String arg1 = separaArg(expAtual, 0);
                String arg2 = separaArg(expAtual, 1);
                saida.append("(" + arg1 + "==" + arg2 + ")");
            } else if (expAtual.contains("<>")) {
                saida.append("!=");
            } else {
                String arg1 = separaExp(expAtual, 0);
                String arg2 = separaExp(expAtual, 1);
                saida.append(arg1);
                saida.append(verificaOp(expAtual));
                saida.append(arg2);
            }
        }
        return null;
    }
    
    /**
     * Gera código C para um comando condicional (if-else).
     * @param ctx Contexto do comando 'se' da gramática T5.
     */
    @Override
    public Void visitCmdSe(T5GrammarParser.CmdSeContext ctx) {
        // Normaliza operadores lógicos para sintaxe C
        String textoExpressao = ctx.expressao().getText()
            .replace("e", "&&")
            .replace("=", "==");
        saida.append("if (" + textoExpressao + ") {\n");
        
        // Gera comandos do bloco 'entao'
        for (T5GrammarParser.CmdContext cctx : ctx.cmdEntao) {
            visitCmd(cctx);
        }
        saida.append("}\n");
        
        // Gera bloco 'senao', se presente
        if (!ctx.cmdSenao.isEmpty()) {
            saida.append("else {\n");
            for (T5GrammarParser.CmdContext cctx : ctx.cmdSenao) {
                visitCmd(cctx);
            }
            saida.append("}\n");
        }
        return null;
    }
    
    /**
     * Gera código C para o comando de leitura (leia).
     * @param ctx Contexto do comando 'leia'.
     */
    @Override
    public Void visitCmdLeia(T5GrammarParser.CmdLeiaContext ctx) {
        TabelaDeSimbolos escopoAtual = escopos.obterEscopoAtual();
        for (T5GrammarParser.IdentificadorContext ictx : ctx.identificador()) {
            String nomeVar = ictx.getText();
            Tipos tipoAuxT5 = escopoAtual.verificar(nomeVar);
            String codigoTipo = verificaParamTipos(tipoAuxT5);
            String str;
            if (tipoAuxT5 == Tipos.LITERAL) {
                str = "gets(" + nomeVar + ");\n";
            } else {
                str = "scanf(\"%" + codigoTipo + "\",&" + nomeVar + ");\n";
            }
            saida.append(str);
        }
        return null;
    }

    /**
     * Gera código C para o comando de repetição 'enquanto'.
     * @param ctx Contexto do comando 'enquanto'.
     */
    @Override
    public Void visitCmdEnquanto(T5GrammarParser.CmdEnquantoContext ctx) {
        saida.append("while(");
        super.visitExpressao(ctx.expressao());
        saida.append(") {\n");
        
        for (T5GrammarParser.CmdContext cctx : ctx.cmd()) {
            super.visitCmd(cctx);
        }
        saida.append("}\n");
        return null;
    }
    
    /**
     * Gera código C para o comando de repetição 'para'.
     * @param ctx Contexto do comando 'para'.
     */
    @Override
    public Void visitCmdPara(T5GrammarParser.CmdParaContext ctx) {
        String nomeVariavel = ctx.IDENT().getText();
        String limiteEsq = ctx.exp_aritmetica(0).getText();
        String limiteDir = ctx.exp_aritmetica(1).getText();

        String str = "for(" + nomeVariavel + " = " + limiteEsq + "; " + 
                     nomeVariavel + " <= " + limiteDir + "; " + nomeVariavel + "++) {\n";
        saida.append(str);

        for (T5GrammarParser.CmdContext cctx : ctx.cmd()) {
            visitCmd(cctx);
        }
        saida.append("}\n");
        return null;
    }
    
    /**
     * Gera código C para o comando de repetição 'faca...ate'.
     * @param ctx Contexto do comando 'faca'.
     */
    @Override
    public Void visitCmdFaca(T5GrammarParser.CmdFacaContext ctx) {
        saida.append("do {\n");
        for (T5GrammarParser.CmdContext cctx : ctx.cmd()) {
            super.visitCmd(cctx);
        }
        saida.append("} while(");
        super.visitExpressao(ctx.expressao());
        saida.append(");\n");
        return null;
    } 
    
    /**
     * Gera código C para o comando de escrita (escreva).
     * @param ctx Contexto do comando 'escreva'.
     */
    @Override
    public Void visitCmdEscreva(T5GrammarParser.CmdEscrevaContext ctx) {
        TabelaDeSimbolos escopoAtual = escopos.obterEscopoAtual();
        
        for (T5GrammarParser.ExpressaoContext ectx : ctx.expressao()) {
            String str;
            if (ectx.getText().contains("\"")) {
                // Imprime cadeia literal sem aspas
                str = "printf(\"" + ectx.getText().replace("\"", "") + "\");\n";
                saida.append(str);
            } else {
                Tipos tipoAuxT5Exp = verificarTipo(escopoAtual, ectx);
                String codTipoExp = verificaParamTipos(tipoAuxT5Exp);
                str = "printf(\"%" + codTipoExp + "\", " + ectx.getText() + ");\n";
                saida.append(str);
            }
        }
        return null;
    }
    
    /**
     * Gera código C para o comando de seleção (caso).
     * @param ctx Contexto do comando 'caso'.
     */
    @Override
    public Void visitCmdCaso(T5GrammarParser.CmdCasoContext ctx) {
        String str = "switch (" + ctx.exp_aritmetica().getText() + ") {\n";
        saida.append(str);

        for (T5GrammarParser.Item_selecaoContext sctx : ctx.selecao().item_selecao()) {
            String strOriginal = sctx.constantes().numero_intervalo(0).getText();
            String limiteEsq, limiteDir;

            if (strOriginal.contains(".")) {
                limiteEsq = getLimitesCaso(strOriginal, true);
                limiteDir = getLimitesCaso(strOriginal, false);
            } else {
                limiteEsq = limiteDir = getLimitesCaso(strOriginal, true);
            }

            if (!sctx.constantes().isEmpty()) {
                for (int i = Integer.parseInt(limiteEsq); i <= Integer.parseInt(limiteDir); i++) {
                    str = "case " + i + ":\n";
                    saida.append(str);
                }
            } else {
                str = "case " + limiteEsq + ":\n";
                saida.append(str);
            }
            
            for (T5GrammarParser.CmdContext cctx : sctx.cmd()) {
                visitCmd(cctx);
            }
            saida.append("break;\n");
        }

        saida.append("default:\n");
        for (T5GrammarParser.CmdContext cctx : ctx.cmd()) {
            visitCmd(cctx);
        }
        saida.append("}\n");
        return null;
    }
    
    /**
     * Gera código C para uma chamada de função ou procedimento.
     * @param ctx Contexto do comando 'chamada'.
     */
    @Override
    public Void visitCmdChamada(T5GrammarParser.CmdChamadaContext ctx) {
        String str = ctx.IDENT().getText() + "(";
        saida.append(str);

        int cont = 0;
        for (T5GrammarParser.ExpressaoContext ectx : ctx.expressao()) {
            if (cont >= 1) {
                saida.append(", ");
            }
            saida.append(ectx.getText());
            cont++;
        }
        saida.append(");\n");
        return null;
    }
}