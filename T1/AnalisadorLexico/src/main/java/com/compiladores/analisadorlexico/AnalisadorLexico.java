/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.compiladores.analisadorlexico;

import java.io.IOException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author Lucas Sciarra Gonçalves, Enzo Murayama, Gabriel Zago
 * 
 * Analisador léxico para a Linguagem Algoritmica desenvolvida pelo professor Jander do DC-UFSCAR
 */
public class AnalisadorLexico {
    
    //Clase principal do programa
    public static void main(String[] args) throws IOException {
        CharStream cs = CharStreams.fromFileName(args[0]);
          
        AnalisadorLexicoLA la = new AnalisadorLexicoLA(cs);
        
        while(la.nextToken().getType() != Token.EOF) {
            System.out.print("");
        }
    }
}
