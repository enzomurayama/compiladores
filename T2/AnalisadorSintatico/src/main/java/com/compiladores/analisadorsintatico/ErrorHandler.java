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

public class ErrorHandler implements ANTLRErrorListener{
    
    PrintWriter pw;
    public ErrorHandler(PrintWriter pw) {
        this.pw = pw;    
    }

    @Override
    public void syntaxError(Recognizer<?, ?> rcgnzr, Object o, int line, int charPositionInLine, String msg, RecognitionException re) {
        // Token que será analisado.
        Token t = (Token) o;
        
        // Dentre as opções disponíveis, o programa identifica a que está
        // de acordo com a saída desejada.
        switch (t.getType()) { // OBS: os valores de t.getType() podem ser conferidos no arquivo t2SintLexer.tokens.
            case -1 -> // -1 refere-se ao token EOF, que indica o fim do programa analisado.
                pw.println("Linha " + line + ": erro sintatico proximo a EOF");
            case 66 -> // 66 refere-se ao erro em uma cadeia literal.
                pw.println("Linha " + line + ": cadeia literal nao fechada");
            case 67 -> // 67 refere-se ao erro em um comentário.
                pw.println("Linha " + line + ": comentario nao fechado");
            case 68 -> // 68 refere-se a um erro na identificação de símbolos.
                pw.println("Linha " + line + ": " + t.getText() + " - simbolo nao identificado");
            default -> // Por padrão, o programa exibe a mensagem de erro sintático.
                pw.println("Linha " + line + ": erro sintatico proximo a " + t.getText());
        }
        // OBS: os valores de t.getType() podem ser conferidos no arquivo t2SintLexer.tokens.
        
        // Encerramente da compilação.
        pw.println("Fim da compilacao"); 
        throw new RuntimeException();
    }

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
