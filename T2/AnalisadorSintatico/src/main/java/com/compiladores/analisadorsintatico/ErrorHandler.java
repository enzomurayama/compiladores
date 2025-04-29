/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.compiladores.analisadorsintatico;

// Importações necessárias para seu funcionamento.
import java.io.PrintWriter;
import java.util.BitSet;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

/**
 * Implementação personalizada de um listener de erros do ANTLR.
 * Redireciona as mensagens de erro sintático para um PrintWriter fornecido
 */
public class ErrorHandler implements ANTLRErrorListener{
    
    // Objeto para escrever a saída dos erros
    PrintWriter pw;
    public ErrorHandler(PrintWriter pw) {
        this.pw = pw;    
    }
       
    /**
     * Método invocado quando ocorre um erro sintático durante a análise
     *
     * @param rcgnzr                Reconhecedor que detectou o erro
     * @param o                     Objeto associado ao erro, geralmente um Token
     * @param line                  Linha onde o erro ocorreu
     * @param charPositionInLine    Posição do caractere na linha
     * @param msg                   Mensagem de erro padrão do ANTLR
     * @param re                    Exceção de reconhecimento (pode ser nula)
     */
    @Override
    public void syntaxError(Recognizer<?, ?> rcgnzr, Object o, int line, int charPositionInLine, String msg, RecognitionException re) {
        // Token relacionado ao erro
        Token t = (Token) o;
        
        // Identifica o tipo de erro com base no tipo do token.
        switch (t.getType()) { 
            case -1 -> // -1 refere-se ao token EOF, que indica o fim do programa analisado
                pw.println("Linha " + line + ": erro sintatico proximo a EOF");
            case 66 -> // Refere-se ao erro em uma cadeia literal
                pw.println("Linha " + line + ": cadeia literal nao fechada");
            case 67 -> // Refere-se ao erro em um comentário
                pw.println("Linha " + line + ": comentario nao fechado");
            case 68 -> // Refere-se a um erro na identificação de símbolos
                pw.println("Linha " + line + ": " + t.getText() + " - simbolo nao identificado");
            default -> // Por padrão, o programa exibe a mensagem de erro sintático.
                pw.println("Linha " + line + ": erro sintatico proximo a " + t.getText());
        }
        
        // Finaliza a análise após o primeiro erro encontrado.
        pw.println("Fim da compilacao"); 
        throw new RuntimeException();
    }
    
    // Os métodos abaixo são exigidos pela interface, mas não estão implementados,
    // pois a análise não trata ambiguidade ou contextos completos.
    
    @Override
    public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean bln, BitSet bitset, ATNConfigSet atncs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitset, ATNConfigSet atncs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atncs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
