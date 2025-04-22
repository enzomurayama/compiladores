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
 * @author Lucas Sciarra Gonçalves, Enzo Youji Murayama, Gabriel Henrique Alves Zago
 * 
 * Analisador léxico para a Linguagem Algoritmica desenvolvida pelo professor Jander do DC-UFSCAR
 */

public class AnalisadorLexico {
    
    public static void main(String[] args){
        
        // Abertura de arquivo para escrita
        try (PrintWriter pw = new PrintWriter(new FileWriter(args[1]))) {
            CharStream cs = null;
            
            // Tenta carregar o conteúdo do arquivo de entrada como um fluxo de caracteres
            try {
                cs = CharStreams.fromFileName(args[0]);
            } catch (IOException ex) {
                Logger.getLogger(AnalisadorLexico.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Inicializa o analisador léxico gerado pelo ANTLR
            AnalisadorLexicoLA la = new AnalisadorLexicoLA(cs);
            Token token;
               
            // Loop para leitura e análise dos tokens até encontrar EOF (fim do arquivo)
            while((token = la.nextToken()).getType() != Token.EOF) {
                int tipoToken = token.getType();
                
                // Tratamento do token do tipo erro
                // Mostra a linha em que ocorreu erro, o caracter inválido e finaliza a execução
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
                   
                // Impressão dos tokens válidos
                switch (tipoToken) {       
                    // número inteiro (5), número real (6), identificador (7), cadeia de caracteres (8)
                    // Imprime o texto do token e seu nome de exibição correspondente do vocabulário. 
                    // Impressão do token no formato: <'valor','tipo'>
                    case 5, 6, 7, 8 -> pw.println("<'" + token.getText() + "'," + AnalisadorLexicoLA.VOCABULARY.getDisplayName(token.getType()) + ">");

                    // Caso padrão: imprime tanto o valor quanto o tipo como sendo o texto do token
                    default -> pw.println("<'" + token.getText() + "','" + token.getText() + "'>");
                }
            }
        // Captura erro ao tentar abrir ou escrever no arquivo de saída
        } catch (IOException ex) {
            Logger.getLogger(AnalisadorLexico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
