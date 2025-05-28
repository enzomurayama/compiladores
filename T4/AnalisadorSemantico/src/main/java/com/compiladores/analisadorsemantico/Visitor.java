package com.compiladores.analisadorsemantico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.antlr.v4.runtime.Token;

import static com.compiladores.analisadorsemantico.AnalisadorSemanticoUtils.verificarTipo;
import static com.compiladores.analisadorsemantico.AnalisadorSemanticoUtils.verificaCompatibilidade;
import static com.compiladores.analisadorsemantico.AnalisadorSemanticoUtils.adicionaErroSemantico;
import static com.compiladores.analisadorsemantico.AnalisadorSemanticoUtils.confereTipo;

public class Visitor extends T4GrammarBaseVisitor<Void> {

    // Tabela de símbolos utilizada para armazenar os identificadores do escopo atual.
    TabelaDeSimbolos tabela;

    // Estrutura que gerencia os escopos aninhados (escopo global, funções, procedimentos, etc.).
    static Escopos escoposAninhados = new Escopos();
    
    // Tabela auxiliar que armazena os nomes e tipos dos parâmetros de funções e procedimentos.
    static HashMap<String, ArrayList<Tipos>> dadosFuncaoProcedimento = new HashMap<>();

    // Tabela que armazena os campos de registros declarados, associando seus nomes e tipos.
    HashMap<String, ArrayList<String>> tabelaRegistro = new HashMap<>();

    /**
     * Método que adiciona um símbolo à tabela de símbolos atual, realizando validações semânticas.
     */
    public void adicionaSimboloTabela(String nome, String tipo, Token nomeT, Token tipoT, TiposEntrada tipoE) {
        TabelaDeSimbolos tabelaLocal = escoposAninhados.obterEscopoAtual();
        Tipos tipoItem;

        // Remove o ponteiro (^) do tipo, se presente.
        if (tipo.charAt(0) == '^')
            tipo = tipo.substring(1);

        // Converte o tipo textual para o enumerador Tipos.
        switch (tipo) {
            case "literal": tipoItem = Tipos.LITERAL; break;
            case "inteiro": tipoItem = Tipos.INTEIRO; break;
            case "real": tipoItem = Tipos.REAL; break;
            case "logico": tipoItem = Tipos.LOGICO; break;
            case "void": tipoItem = Tipos.VOID; break;
            case "registro": tipoItem = Tipos.REGISTRO; break;
            default: tipoItem = Tipos.INVALIDO; break;
        }

        // Emite erro se o tipo não for reconhecido.
        if (tipoItem == Tipos.INVALIDO)
            adicionaErroSemantico(tipoT, "tipo " + tipo + " nao declarado");

        // Adiciona símbolo à tabela se ainda não declarado; caso contrário, emite erro.
        if (!tabelaLocal.existe(nome))
            tabelaLocal.adicionar(nome, tipoItem, tipoE);
        else
            adicionaErroSemantico(nomeT, "identificador " + nome + " ja declarado anteriormente");
    }

    @Override
    public Void visitPrograma(T4GrammarParser.ProgramaContext ctx) {
        // Valida se há comandos 'retorne' no escopo principal, o que não é permitido.
        for (T4GrammarParser.CmdContext c : ctx.corpo().cmd())
            if (c.cmdRetorne() != null)
                adicionaErroSemantico(c.getStart(), "comando retorne nao permitido nesse escopo");

        return super.visitPrograma(ctx);
    }

    @Override
    public Void visitDeclaracao_local(T4GrammarParser.Declaracao_localContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();

        String tipoVariavel;
        String nomeVariavel;

        // Trata declarações de variáveis.
        if (ctx.getText().contains("declare")) {
            // Declaração de variável do tipo registro inline.
            if (ctx.variavel().tipo().registro() != null) {
                for (T4GrammarParser.IdentificadorContext ic : ctx.variavel().identificador()) {
                    // Adiciona o nome do registro como variável do tipo 'registro'.
                    adicionaSimboloTabela(ic.getText(), "registro", ic.getStart(), null, TiposEntrada.VARIAVEL);

                    // Adiciona os campos internos do registro como variáveis qualificadas (ex: reg.campo).
                    for (T4GrammarParser.VariavelContext vc : ctx.variavel().tipo().registro().variavel()) {
                        tipoVariavel = vc.tipo().getText();
                        for (T4GrammarParser.IdentificadorContext icr : vc.identificador()) {
                            adicionaSimboloTabela(ic.getText() + "." + icr.getText(), tipoVariavel, icr.getStart(), vc.tipo().getStart(), TiposEntrada.VARIAVEL);
                        }
                    }
                }
            } else {
                // Declaração de variável com tipo existente (básico ou registro).
                tipoVariavel = ctx.variavel().tipo().getText();

                // Se for um tipo registro já definido.
                if (tabelaRegistro.containsKey(tipoVariavel)) {
                    ArrayList<String> variaveisRegistro = tabelaRegistro.get(tipoVariavel);

                    for (T4GrammarParser.IdentificadorContext ic : ctx.variavel().identificador()) {
                        nomeVariavel = ic.IDENT().get(0).getText();

                        // Verifica se a variável já existe na tabela de símbolos ou se colide com o nome do tipo.
                        if (tabela.existe(nomeVariavel) || tabelaRegistro.containsKey(nomeVariavel)) {
                            adicionaErroSemantico(ic.getStart(), "identificador " + nomeVariavel + " ja declarado anteriormente");
                        } else {
                            // Adiciona a variável do tipo registro.
                            adicionaSimboloTabela(nomeVariavel, "registro", ic.getStart(), ctx.variavel().tipo().getStart(), TiposEntrada.VARIAVEL);

                            // Adiciona os campos do registro com nomes qualificados (ex: var.campo).
                            for (int i = 0; i < variaveisRegistro.size(); i += 2) {
                                adicionaSimboloTabela(nomeVariavel + "." + variaveisRegistro.get(i), variaveisRegistro.get(i+1), ic.getStart(), ctx.variavel().tipo().getStart(), TiposEntrada.VARIAVEL);
                            }
                        }
                    }
                } else {
                    // Declaração de variável com tipo básico.
                    for (T4GrammarParser.IdentificadorContext ident : ctx.variavel().identificador()) {
                        nomeVariavel = ident.getText();

                        // Verifica se o identificador colide com uma função ou procedimento.
                        if (dadosFuncaoProcedimento.containsKey(nomeVariavel))
                            adicionaErroSemantico(ident.getStart(), "identificador " + nomeVariavel + " ja declarado anteriormente");
                        else
                            adicionaSimboloTabela(nomeVariavel, tipoVariavel, ident.getStart(), ctx.variavel().tipo().getStart(), TiposEntrada.VARIAVEL); 
                    }
                }
            }
        } else if (ctx.getText().contains("tipo")) {
            // Declaração de um novo tipo (registro).
            if (ctx.tipo().registro() != null) {
                ArrayList<String> variaveisRegistro = new ArrayList<>();

                // Coleta os campos e tipos do novo registro.
                for (T4GrammarParser.VariavelContext vc : ctx.tipo().registro().variavel()) {
                    tipoVariavel = vc.tipo().getText();

                    for (T4GrammarParser.IdentificadorContext ic : vc.identificador()) {
                        variaveisRegistro.add(ic.getText());
                        variaveisRegistro.add(tipoVariavel);
                    }
                }
                // Armazena o novo tipo de registro na tabela global de registros.
                tabelaRegistro.put(ctx.IDENT().getText(), variaveisRegistro);
            }
        } else if (ctx.getText().contains("constante")) {
            // Declaração de constante.
            adicionaSimboloTabela(ctx.IDENT().getText(), ctx.tipo_basico().getText(), ctx.IDENT().getSymbol(), ctx.IDENT().getSymbol(), TiposEntrada.VARIAVEL);
        }

        return super.visitDeclaracao_local(ctx);
    }


    @Override
    public Void visitDeclaracao_global(T4GrammarParser.Declaracao_globalContext ctx) {
        // Cria novo escopo para o corpo da função ou procedimento.
        escoposAninhados.criarNovoEscopo();
        tabela = escoposAninhados.obterEscopoAtual();

        ArrayList<Tipos> tiposVariaveis = new ArrayList<>();
        ArrayList<String> variaveisRegistro;
        String tipoVariavel;
        Tipos tipoAux;

        if (ctx.getText().contains("procedimento")) {
            // Processa os parâmetros do procedimento.
            for (T4GrammarParser.ParametroContext parametro : ctx.parametros().parametro()) {
                // Parâmetro de tipo básico.
                if (parametro.tipo_estendido().tipo_basico_ident().tipo_basico() != null) {
                    adicionaSimboloTabela(parametro.identificador().get(0).getText(), parametro.tipo_estendido().tipo_basico_ident().tipo_basico().getText(), parametro.getStart(), parametro.getStart(), TiposEntrada.VARIAVEL);
                    tipoAux = confereTipo(tabelaRegistro, parametro.tipo_estendido().getText());
                    tiposVariaveis.add(tipoAux);
                } 
                // Parâmetro de tipo registro definido anteriormente.
                else if (tabelaRegistro.containsKey(parametro.tipo_estendido().tipo_basico_ident().IDENT().getText())) {
                    variaveisRegistro = tabelaRegistro.get(parametro.tipo_estendido().tipo_basico_ident().IDENT().getText());
                    tipoAux = confereTipo(tabelaRegistro, parametro.tipo_estendido().getText());
                    tiposVariaveis.add(tipoAux);

                    // Adiciona os campos internos do registro como variáveis locais.
                    for (T4GrammarParser.IdentificadorContext ic : parametro.identificador())
                        for (int i = 0; i < variaveisRegistro.size(); i += 2)
                            adicionaSimboloTabela(ic.getText() + "." + variaveisRegistro.get(i), variaveisRegistro.get(i + 1), ic.getStart(), ic.getStart(), TiposEntrada.VARIAVEL);                       
                } else {
                    // Tipo do parâmetro não foi declarado.
                    adicionaErroSemantico(parametro.getStart(), "tipo nao declarado");                       
                }
            }

            // Verifica presença indevida de 'retorne' em procedimentos.
            for (T4GrammarParser.CmdContext c : ctx.cmd())    
                if (c.cmdRetorne() != null)  
                    adicionaErroSemantico(c.getStart(), "comando retorne nao permitido nesse escopo");

            dadosFuncaoProcedimento.put(ctx.IDENT().getText(), tiposVariaveis);

        } else if (ctx.getText().contains("funcao")) {
            // Processamento semelhante ao de procedimento, mas funções permitem retorno.
            for (T4GrammarParser.ParametroContext parametro : ctx.parametros().parametro()) {
                if (parametro.tipo_estendido().tipo_basico_ident().tipo_basico() != null) {
                    adicionaSimboloTabela(parametro.identificador().get(0).getText(), parametro.tipo_estendido().tipo_basico_ident().tipo_basico().getText(), parametro.getStart(), parametro.getStart(), TiposEntrada.VARIAVEL);
                    tipoAux = confereTipo(tabelaRegistro, parametro.tipo_estendido().getText());
                    tiposVariaveis.add(tipoAux);
                } else if (tabelaRegistro.containsKey(parametro.tipo_estendido().tipo_basico_ident().IDENT().getText())) {
                    variaveisRegistro = tabelaRegistro.get(parametro.tipo_estendido().tipo_basico_ident().IDENT().getText());
                    tipoAux = confereTipo(tabelaRegistro, parametro.tipo_estendido().getText());
                    tiposVariaveis.add(tipoAux);

                    for (T4GrammarParser.IdentificadorContext ic : parametro.identificador())
                        for (int i = 0; i < variaveisRegistro.size(); i += 2)
                            adicionaSimboloTabela(ic.getText() + "." + variaveisRegistro.get(i), variaveisRegistro.get(i + 1), ic.getStart(), ic.getStart(), TiposEntrada.VARIAVEL);
                } else {
                    adicionaErroSemantico(parametro.getStart(), "tipo nao declarado");
                }
            }

            dadosFuncaoProcedimento.put(ctx.IDENT().getText(), tiposVariaveis);
        }

        // Processa o corpo do procedimento ou função.
        super.visitDeclaracao_global(ctx);
        escoposAninhados.abandonarEscopo();

        // Após processar o corpo, adiciona o identificador global da função/procedimento.
        if (ctx.getText().contains("procedimento"))      
            adicionaSimboloTabela(ctx.IDENT().getText(), "void", ctx.getStart(), ctx.getStart(), TiposEntrada.PROCEDIMENTO);
        else if (ctx.getText().contains("funcao"))
            adicionaSimboloTabela(ctx.IDENT().getText(), ctx.tipo_estendido().tipo_basico_ident().tipo_basico().getText(), ctx.getStart(), ctx.getStart(), TiposEntrada.FUNCAO);

        return null;
    }


    @Override
    public Void visitCmdLeia(T4GrammarParser.CmdLeiaContext ctx) {
        // Verifica se os identificadores usados no comando 'leia' foram declarados.
        tabela = escoposAninhados.obterEscopoAtual();
        for (T4GrammarParser.IdentificadorContext id : ctx.identificador()) 
            if (!tabela.existe(id.getText()))
                adicionaErroSemantico(id.getStart(), "identificador " + id.getText() + " nao declarado");
        return super.visitCmdLeia(ctx);
    }

    @Override
    public Void visitCmdEscreva(T4GrammarParser.CmdEscrevaContext ctx) {
        // Verifica os tipos das expressões usadas no comando 'escreva'.
        tabela = escoposAninhados.obterEscopoAtual();
        for (T4GrammarParser.ExpressaoContext expressao : ctx.expressao())
            verificarTipo(tabela, expressao);
        return super.visitCmdEscreva(ctx);
    }

    @Override
    public Void visitCmdEnquanto(T4GrammarParser.CmdEnquantoContext ctx) {
        // Verifica o tipo da expressão condicional no comando 'enquanto'.
        tabela = escoposAninhados.obterEscopoAtual();
        verificarTipo(tabela, ctx.expressao());
        return super.visitCmdEnquanto(ctx);
    }

    @Override
    public Void visitCmdSe(T4GrammarParser.CmdSeContext ctx) {
        // Verifica o tipo da expressão condicional no comando 'se'.
        tabela = escoposAninhados.obterEscopoAtual();
        verificarTipo(tabela, ctx.expressao());
        return super.visitCmdSe(ctx);
    }

    @Override
    public Void visitCmdAtribuicao(T4GrammarParser.CmdAtribuicaoContext ctx) {
        // Verifica compatibilidade de tipos na atribuição e se o identificador está declarado.
        tabela = escoposAninhados.obterEscopoAtual();
        Tipos tipoExpressao = verificarTipo(tabela, ctx.expressao());
        String varNome = ctx.identificador().getText();

        if (tipoExpressao != Tipos.INVALIDO) {
            if (!tabela.existe(varNome)) {
                adicionaErroSemantico(ctx.identificador().getStart(), "identificador " + varNome + " nao declarado");
            } else {
                Tipos varTipo = verificarTipo(tabela, varNome);

                if (varTipo == Tipos.INTEIRO || varTipo == Tipos.REAL) {
                    if (ctx.getText().contains("ponteiro")) {
                        if (!verificaCompatibilidade(varTipo, tipoExpressao) && tipoExpressao != Tipos.INTEIRO)
                            adicionaErroSemantico(ctx.identificador().getStart(), "atribuicao nao compativel para ^" + varNome);
                    } else if (!verificaCompatibilidade(varTipo, tipoExpressao) && tipoExpressao != Tipos.INTEIRO) {
                        adicionaErroSemantico(ctx.identificador().getStart(), "atribuicao nao compativel para " + varNome);
                    }
                } else if (varTipo != tipoExpressao) {
                    adicionaErroSemantico(ctx.identificador().getStart(), "atribuicao nao compativel para " + varNome);
                }
            }
        }
        return super.visitCmdAtribuicao(ctx);
    }
}
