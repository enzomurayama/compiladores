/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.compiladores.analisadorlexico;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public static void main(String[] args){
        
        try ( PrintWriter pw = new PrintWriter(new FileWriter(args[1]))) {
            CharStream cs = null;
            try {
                cs = CharStreams.fromFileName(args[0]);
            } catch (IOException ex) {
                Logger.getLogger(AnalisadorLexico.class.getName()).log(Level.SEVERE, null, ex);
            }

            AnalisadorLexicoLA la = new AnalisadorLexicoLA(cs);

            Token token = null;
            
            while((token=la.nextToken()).getType() != Token.EOF) {

                int tipoToken = token.getType();
                
               //Tratamento do token do tipo erro: mostra a linha do erro, o caracter invalido e para a aplicao
                if(tipoToken == 20 || tipoToken == 23)
                {
                    pw.println("Linha " + token.getLine() + ": " + token.getText() + " - simbolo nao identificado" ); 
                    break;
                } else if(tipoToken == 21)
                {
                    pw.println("Linha " + token.getLine() + ": comentario nao fechado" ); 
                    break;
                } else if(tipoToken == 22)
                {
                    pw.println("Linha " + token.getLine() + ": cadeia literal nao fechada" ); 
                    break;
                }

                switch (tipoToken) {                    
                    // Casos para os tipos de tokens 5, 6, 7, 8 (numero inteiro, numero real, identificador e cadeia)
                    // Imprime o texto do token em ambos os campos da tupla, envolto por aspas simples 
                    case 5, 6, 7, 8 -> pw.println("<'" + token.getText() + "'," + AnalisadorLexicoLA.VOCABULARY.getDisplayName(token.getType()) + ">");

                    // Caso padrão para todos os outros tipos de tokens
                    // Imprime o texto do token e seu nome de exibição correspondente do vocabulário
                    default -> pw.println("<'" + token.getText() + "','" + token.getText() + "'>");
                }
            }
 
        } catch (IOException ex) {
            Logger.getLogger(AnalisadorLexico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
