/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.compiladores.analisadorsemantico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.antlr.v4.runtime.Token;

public class Visitor extends T4GrammarBaseVisitor<Void> {
    TabelaDeSimbolos tabela;

    static Escopos escoposAninhados = new Escopos();
    
    static List<String> errosSemanticos = new ArrayList<>();
    
    static HashMap<String, ArrayList<Tipos>> dadosFuncaoProcedimento = new HashMap<>();

    // Criação de uma tabela que armazenará as variaveis pertencentes a um registro
    HashMap<String, ArrayList<String>> tabelaRegistro = new HashMap<>();
    
    TabelaDeSimbolos tabelaEscopo;

    // Adiciona uma variável à tabela de símbolos, verificando a validade do tipo e declarações duplicadas
    public void adicionaVariavelTabela(String nome, String tipo, Token nomeT, Token tipoT, TiposEntrada tipoEntrada) {
        tabelaEscopo = escoposAninhados.obterEscopoAtual();

        Tipos tipoItem;

        // Mapeia o tipo em string para o tipo enum para análise semântica
        tipoItem = switch (tipo) {
            case "literal" -> Tipos.LITERAL;
            case "inteiro" -> Tipos.INTEIRO;
            case "real" -> Tipos.REAL;
            case "logico" -> Tipos.LOGICO;
            case "void" -> Tipos.VOID;
            case "registro" -> Tipos.REGISTRO;
            default -> Tipos.INVALIDO;
        };

        // Reporta um erro se o tipo for inválido
        if (tipoItem == Tipos.INVALIDO)
            adicionaErroSemantico(tipoT, "tipo " + tipo + " nao declarado");

        // Verifica se há declarações duplicadas de variáveis no escopo atual
        if (!tabelaEscopo.existe(nome))
            tabelaEscopo.adicionar(nome, tipoItem, tipoEntrada);
        else
            adicionaErroSemantico(nomeT, "identificador " + nome + " ja declarado anteriormente");
    }

    // Inicializa a tabela de símbolos para o programa
    @Override
    public Void visitPrograma(T4GrammarParser.ProgramaContext ctx) {
        // Verifica utilização do comando retorne no escopo do programa principal (erro)
        for (T4GrammarParser.CmdContext c : ctx.corpo().cmd())
            if (c.cmdRetorne() != null)
                adicionaErroSemantico(c.getStart(), "comando retorne nao permitido nesse escopo");
        
        return super.visitPrograma(ctx);
    }

    // Processa as declarações locais e globais do programa
    @Override
    public Void visitDeclaracoes(T4GrammarParser.DeclaracoesContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();
        
        // Visita todas as declarações locais ou globais no contexto
        for (T4GrammarParser.Decl_local_globalContext declaracao : ctx.decl_local_global())
            visitDecl_local_global(declaracao);
        
        return super.visitDeclaracoes(ctx);
    }

    // Processa declarações locais, como variáveis, e as adiciona à tabela de símbolos
    @Override
    public Void visitDeclaracao_local(T4GrammarParser.Declaracao_localContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();

        String tipoVariavel;
        String nomeVariavel;

        // Tenta identificar uma declaração local.
        if (ctx.getText().contains("declare")) {
            // Verifica se é a declaração de algum tipo de registro.
            if (ctx.variavel().tipo().registro() != null) {

                for (T4GrammarParser.IdentificadorContext ic : ctx.variavel().identificador()) {
                    adicionaVariavelTabela(ic.getText(), "registro", ic.getStart(), null, TiposEntrada.VARIAVEL);

                    for (T4GrammarParser.VariavelContext vc : ctx.variavel().tipo().registro().variavel()) {
                        tipoVariavel = vc.tipo().getText();
                        
                        for (T4GrammarParser.IdentificadorContext icr : vc.identificador())
                            adicionaVariavelTabela(ic.getText() + "." + icr.getText(), tipoVariavel, icr.getStart(), vc.tipo().getStart(), TiposEntrada.VARIAVEL);
                    }
                }
            // Verifica se é um tipo já declarado.
            } else {
                tipoVariavel = ctx.variavel().tipo().getText(); 
                // Verifica se o tipo é um registro já declarado anteriormente.
                if (tabelaRegistro.containsKey(tipoVariavel)) {
                    ArrayList<String> variaveisRegistro = tabelaRegistro.get(tipoVariavel);
                    
                    for (T4GrammarParser.IdentificadorContext ic : ctx.variavel().identificador()) {
                        nomeVariavel = ic.IDENT().get(0).getText();
                        
                        if (tabela.existe(nomeVariavel) || tabelaRegistro.containsKey(nomeVariavel)) {
                            adicionaErroSemantico(ic.getStart(), "identificador " + nomeVariavel + " ja declarado anteriormente");
                        } else {  
                            adicionaVariavelTabela(nomeVariavel, "registro", ic.getStart(), ctx.variavel().tipo().getStart(), TiposEntrada.VARIAVEL);                            

                            for (int i = 0; i < variaveisRegistro.size(); i = i + 2) {
                                adicionaVariavelTabela(nomeVariavel + "." + variaveisRegistro.get(i), variaveisRegistro.get(i+1), ic.getStart(), ctx.variavel().tipo().getStart(), TiposEntrada.VARIAVEL);
                            }
                        }
                    }
                // Caso em que apenas é uma variável de um dos tipos básicos.
                } else {
                    for (T4GrammarParser.IdentificadorContext ident : ctx.variavel().identificador()) {
                        nomeVariavel = ident.getText();
                        
                        // Verifica se a declaração atual é o nome de uma função ou procedimento.
                        if (dadosFuncaoProcedimento.containsKey(nomeVariavel))
                            adicionaErroSemantico(ident.getStart(), "identificador " + nomeVariavel + " ja declarado anteriormente");
                        else
                            adicionaVariavelTabela(nomeVariavel, tipoVariavel, ident.getStart(), ctx.variavel().tipo().getStart(), TiposEntrada.VARIAVEL); 
                    }
                }
            }
        // Verifica se é a declaração de um novo tipo (registro).
        } else if (ctx.getText().contains("tipo")) {
            
            if (ctx.tipo().registro() != null) {
                ArrayList<String> variaveisRegistro = new ArrayList<>();
                
                for (T4GrammarParser.VariavelContext vc : ctx.tipo().registro().variavel()) {
                    tipoVariavel = vc.tipo().getText();
                    
                    for (T4GrammarParser.IdentificadorContext ic : vc.identificador()) {
                        variaveisRegistro.add(ic.getText());
                        variaveisRegistro.add(tipoVariavel);
                    }
                }
                tabelaRegistro.put(ctx.IDENT().getText(), variaveisRegistro);
            }
        // Verifica se é a declaração de uma constante.
        } else if (ctx.getText().contains("constante"))
            adicionaVariavelTabela(ctx.IDENT().getText(), ctx.tipo_basico().getText(), ctx.IDENT().getSymbol(), ctx.IDENT().getSymbol(), TiposEntrada.VARIAVEL);
        
        return super.visitDeclaracao_local(ctx);
    }
    
    @Override
    public Void visitDeclaracao_global(T4GrammarParser.Declaracao_globalContext ctx) {
        // Cria um novo escopo para cada novo procedimento ou função.
        escoposAninhados.criarNovoEscopo();
        
        tabela = escoposAninhados.obterEscopoAtual();

        // Lista auxiliar que armazenará os tipos das variáveis.
        ArrayList<Tipos> tiposVariaveis = new ArrayList<>();
        ArrayList<String> variaveisRegistro;
                
        String tipoVariavel;
        Tipos tipoAux;
        
        // Verifica se o escopo atual pertence a um procedimento.
        if (ctx.getText().contains("procedimento")) {
            
            for (T4GrammarParser.ParametroContext parametro : ctx.parametros().parametro()) {
                // Verifica se é um tipo básico válido.
                if (parametro.tipo_estendido().tipo_basico_ident().tipo_basico() != null) {
                    // Adiciona o parâmetro na tabela.
                    adicionaVariavelTabela(parametro.identificador().get(0).getText(), parametro.tipo_estendido().tipo_basico_ident().tipo_basico().getText(), parametro.getStart(), parametro.getStart(), TiposEntrada.VARIAVEL);
                    
                    // Obtém os tipos dos parâmetros para adicioná-las na tabela de dados de funções e procedimentos.
                    tipoVariavel = parametro.tipo_estendido().getText();
                    tipoAux = confereTipo(tabelaRegistro, tipoVariavel);
                    tiposVariaveis.add(tipoAux);
                // Verifica se é um registro
                } else if (tabelaRegistro.containsKey(parametro.tipo_estendido().tipo_basico_ident().IDENT().getText())) {
                    // Recupera os elementos do registro.
                    variaveisRegistro = tabelaRegistro.get(parametro.tipo_estendido().tipo_basico_ident().IDENT().getText());

                    // Obtém os tipos dos parâmetros para adicioná-las na tabela de dados de funções e procedimentos.
                    tipoVariavel = parametro.tipo_estendido().getText();
                    tipoAux = confereTipo(tabelaRegistro, tipoVariavel);
                    tiposVariaveis.add(tipoAux);

                    for (T4GrammarParser.IdentificadorContext ic : parametro.identificador())
                        // Adiciona os elementos do registro na tabela no formato adequado com seus
                        // respectivos tipos individuais.
                        for (int i = 0; i < variaveisRegistro.size(); i = i + 2)
                            adicionaVariavelTabela(ic.getText() + "." + variaveisRegistro.get(i), variaveisRegistro.get(i + 1), ic.getStart(), ic.getStart(), TiposEntrada.VARIAVEL);                       
                } else
                    adicionaErroSemantico(parametro.getStart(), "tipo nao declarado");                       
            }
            // Verifica se há algum comando "retorne" dentro de um procedimento e indica o erro.
            for (T4GrammarParser.CmdContext c : ctx.cmd())    
                if (c.cmdRetorne() != null)  
                    adicionaErroSemantico(c.getStart(), "comando retorne nao permitido nesse escopo");    
            
            // Adiciona o nome do procedimento e os tipos dos parâmetros na tabela de dados.
            dadosFuncaoProcedimento.put(ctx.IDENT().getText(), tiposVariaveis);
            
        // Verifica se o escopo atual pertence a uma função.
        } else if (ctx.getText().contains("funcao")) {
            // A partir daqui, utiliza uma lógica semelhante às verificações para o procedimento.
            for (T4GrammarParser.ParametroContext parametro : ctx.parametros().parametro()) {
                
                if (parametro.tipo_estendido().tipo_basico_ident().tipo_basico() != null) {
                
                    adicionaVariavelTabela(parametro.identificador().get(0).getText(), parametro.tipo_estendido().tipo_basico_ident().tipo_basico().getText(), parametro.getStart(), parametro.getStart(), TiposEntrada.VARIAVEL);

                    tipoVariavel = parametro.tipo_estendido().getText();
                    tipoAux = confereTipo(tabelaRegistro, tipoVariavel);
                    tiposVariaveis.add(tipoAux);
                } else if (tabelaRegistro.containsKey(parametro.tipo_estendido().tipo_basico_ident().IDENT().getText())) {

                    variaveisRegistro = tabelaRegistro.get(parametro.tipo_estendido().tipo_basico_ident().IDENT().getText());

                    tipoVariavel = parametro.tipo_estendido().tipo_basico_ident().IDENT().getText();
                    tipoAux = confereTipo(tabelaRegistro, tipoVariavel);
                    tiposVariaveis.add(tipoAux);
                    
                    for (T4GrammarParser.IdentificadorContext ic : parametro.identificador())
                        for (int i = 0; i < variaveisRegistro.size(); i = i + 2)
                                adicionaVariavelTabela(ic.getText() + "." + variaveisRegistro.get(i), variaveisRegistro.get(i + 1), ic.getStart(), ic.getStart(), TiposEntrada.VARIAVEL);
                } else
                    adicionaErroSemantico(parametro.getStart(), "tipo nao declarado");
            }
            
            // Adiciona o nome da função e os tipos dos parâmetros na tabela de dados.
            dadosFuncaoProcedimento.put(ctx.IDENT().getText(), tiposVariaveis);
        }
        
        super.visitDeclaracao_global(ctx);
        
        // Desempilha o escopo atual
        escoposAninhados.abandonarEscopo();
        
        // Adiciona o nome do procedimento/função na tabela
        if (ctx.getText().contains("procedimento"))      
            adicionaVariavelTabela(ctx.IDENT().getText(), "void", ctx.getStart(), ctx.getStart(), TiposEntrada.PROCEDIMENTO);
        else if (ctx.getText().contains("funcao"))
            adicionaVariavelTabela(ctx.IDENT().getText(), ctx.tipo_estendido().tipo_basico_ident().tipo_basico().getText(), ctx.getStart(), ctx.getStart(), TiposEntrada.FUNCAO);

        return null;
    }

    // Verifica se os identificadores no comando de leitura estão declarados
    @Override
    public Void visitCmdLeia(T4GrammarParser.CmdLeiaContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();

        // Reporta erro se algum identificador não estiver na tabela de símbolos
        for (T4GrammarParser.IdentificadorContext id : ctx.identificador())
            if (!tabela.existe(id.getText()))
                adicionaErroSemantico(id.getStart(), "identificador " + id.getText() + " nao declarado");

        return super.visitCmdLeia(ctx);
    }

    // Verifica o tipo das expressões no comando de escrita
    @Override
    public Void visitCmdEscreva(T4GrammarParser.CmdEscrevaContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();
        
        // Analisa o tipo de cada expressão no comando
        for (T4GrammarParser.ExpressaoContext expressao : ctx.expressao())
            verificarTipo(tabela, expressao);

        return super.visitCmdEscreva(ctx);
    }

    // Verifica o tipo da expressão no comando enquanto (loop)
    @Override
    public Void visitCmdEnquanto(T4GrammarParser.CmdEnquantoContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();
        
        // Verifica se a expressão do loop é válida
        Tipos tipo = verificarTipo(tabela, ctx.expressao());
        
        return super.visitCmdEnquanto(ctx);
    }
    
    @Override
    public Void visitCmdSe(T4GrammarParser.CmdSeContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();
        
        Tipos tipo = verificarTipo(tabela, ctx.expressao());
        
        return super.visitCmdSe(ctx);
    }

    // Verifica a compatibilidade de tipos em uma atribuição
     @Override
    public Void visitCmdAtribuicao(T4GrammarParser.CmdAtribuicaoContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();
        
        Tipos tipoExpressao = verificarTipo(tabela, ctx.expressao());
        
        String varNome = ctx.identificador().getText();
        
        if (tipoExpressao != Tipos.INVALIDO) {
            // Caso a variável não tenha sido declarada, informa o erro.
            if (!tabela.existe(varNome))
                adicionaErroSemantico(ctx.identificador().getStart(), "identificador " + ctx.identificador().getText() + " nao declarado");
            else {
                Tipos varTipo = verificarTipo(tabela, varNome);
                
                // Caso o tipo seja inteiro ou real, é utilizada a função verificaCompatibilidade para verificar
                // se o valor a ser trabalhado é real ou não (mais informações sobre a função podem ser encontradas
                // no arquivo T4SemanticoUtils.java.
                if (varTipo == Tipos.INTEIRO || varTipo == Tipos.REAL) {
                    // Verifica se a variável atual é um ponteiro para apresentar uma mensagem personalizada
                    // para este caso.
                    if (ctx.getText().contains("ponteiro")) {
                        if (!verificaCompatibilidade(varTipo, tipoExpressao))
                            // Caso o tipo da expressão (restante da parcela sendo analisada) seja diferente de inteiro,
                            // não é possível tratar o valor como um número real, logo, os tipos são incompatíveis, pois
                            // seria a situação de estar comparando um número com um literal, por exemplo.
                            if (tipoExpressao != Tipos.INTEIRO)
                                adicionaErroSemantico(ctx.identificador().getStart(), "atribuicao nao compativel para ^" + ctx.identificador().getText());
                    } else if (!verificaCompatibilidade(varTipo, tipoExpressao))
                        if (tipoExpressao != Tipos.INTEIRO)
                            adicionaErroSemantico(ctx.identificador().getStart(), "atribuicao nao compativel para " + ctx.identificador().getText());               
                // Caso a expressão analisada não tenha números que precisem ser tratados de maneira especial,
                // apenas verifica se os tipos são diferentes.
                } else if (varTipo != tipoExpressao)
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
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T4GrammarParser.Exp_aritmeticaContext ctx) {
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
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T4GrammarParser.TermoContext ctx) {
        // Começa verificando o tipo do primeiro fator
        Tipos tipoRetorno = verificarTipo(tabela, ctx.fator().get(0));
                
        // Analisa cada fator para determinar o tipo resultante
        for (T4GrammarParser.FatorContext fatorArit : ctx.fator()) {
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
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T4GrammarParser.FatorContext ctx) {
        Tipos tipoRetorno = null;
        
        // Propaga o tipo da parcela
        for (T4GrammarParser.ParcelaContext parcela : ctx.parcela())
            tipoRetorno = verificarTipo(tabela, parcela);

        return tipoRetorno;
    }

    // Determina o tipo de uma parcela, que pode ser unária ou não unária
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T4GrammarParser.ParcelaContext ctx) {
        if (ctx.parcela_unario() != null)
            return verificarTipo(tabela, ctx.parcela_unario());
        else
            return verificarTipo(tabela, ctx.parcela_nao_unario());
    }

    // Determina o tipo de uma parcela unária (identificador, número ou expressão)
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T4GrammarParser.Parcela_unarioContext ctx) {
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
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T4GrammarParser.Parcela_nao_unarioContext ctx) {
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
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T4GrammarParser.ExpressaoContext ctx) {
        // Começa verificando o tipo do primeiro termo lógico
        Tipos tipoRetorno = verificarTipo(tabela, ctx.termo_logico(0));

        // Verifica consistência entre os termos lógicos
        for (T4GrammarParser.Termo_logicoContext termoLogico : ctx.termo_logico()) {
            Tipos tipoAtual = verificarTipo(tabela, termoLogico);
            if (tipoRetorno != tipoAtual && tipoAtual != Tipos.INVALIDO)
                tipoRetorno = Tipos.INVALIDO;
        }

        return tipoRetorno;
    }

    // Determina o tipo de um termo lógico
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T4GrammarParser.Termo_logicoContext ctx) {
        // Começa verificando o tipo do primeiro fator lógico
        Tipos tipoRetorno = verificarTipo(tabela, ctx.fator_logico(0));

        // Verifica consistência entre os fatores lógicos
        for (T4GrammarParser.Fator_logicoContext fatorLogico : ctx.fator_logico()) {
            Tipos tipoAtual = verificarTipo(tabela, fatorLogico);
            if (tipoRetorno != tipoAtual && tipoAtual != Tipos.INVALIDO)
                tipoRetorno = Tipos.INVALIDO;
        }
        return tipoRetorno;
    }

    // Determina o tipo de um fator lógico
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T4GrammarParser.Fator_logicoContext ctx) {
        // Propaga o tipo da parcela lógica
        Tipos tipoRetorno = verificarTipo(tabela, ctx.parcela_logica());
        return tipoRetorno;
    }

    // Determina o tipo de uma parcela lógica
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T4GrammarParser.Parcela_logicaContext ctx) {
        Tipos tipoRetorno;

        if (ctx.exp_relacional() != null)
            tipoRetorno = verificarTipo(tabela, ctx.exp_relacional());
        else
            tipoRetorno = Tipos.LOGICO;

        return tipoRetorno;
    }

    // Determina o tipo de uma expressão relacional
    public static Tipos verificarTipo(TabelaDeSimbolos tabela, T4GrammarParser.Exp_relacionalContext ctx) {
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
    
    // Método auxiliar que reduz o nome de um identificador de vetor ou procedimento/função.
    // O parâmetro símbolo refere-se ao "[" no caso de vetor, ou "(" no caso de procedimento/função.
    // Exemplo: vetor[i] vira apenas vetor.
    public static String reduzNome(String nome, String simbolo) {
        
        if (nome.contains(simbolo)) {

            boolean continua = true;
            int cont = 0;
            String nomeAux;

            while (continua) {
                nomeAux = nome.substring(cont);

                if (nomeAux.startsWith(simbolo))
                    continua = false;
                else
                    cont++;
            }

            nome = nome.substring(0, cont); 
        }
        
        return nome;
    }   
    
    // Método auxiliar utilizado para retornar o TipoT4 referente ao literal que está sendo analisado.
    public static Tipos confereTipo (HashMap<String, ArrayList<String>> tabela, String tipoRetorno) {
        Tipos tipoAux;
        
        // Remoção do ponteiro.
        if (tipoRetorno.charAt(0) == '^') {
            tipoRetorno = tipoRetorno.substring(1);
        }
        
        if (tabela.containsKey(tipoRetorno))
            tipoAux = Tipos.REGISTRO;
        else if (tipoRetorno.equals("literal"))
            tipoAux = Tipos.LITERAL;
        else if (tipoRetorno.equals("inteiro"))
            tipoAux = Tipos.INTEIRO;
        else if (tipoRetorno.equals("real"))
            tipoAux = Tipos.REAL;
        else if (tipoRetorno.equals("logico"))
            tipoAux = Tipos.LOGICO;
        else
            tipoAux = Tipos.INVALIDO;
        
        return tipoAux;
    }
}