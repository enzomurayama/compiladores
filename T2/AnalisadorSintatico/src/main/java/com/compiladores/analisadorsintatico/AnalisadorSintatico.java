/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.compiladores.analisadorsintatico;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 *
 * @author Lucas Sciarra Gonçalves, Enzo Youji Murayama, Gabriel Henrique Alves Zago
 * 
 * Analisador sintático para a Linguagem Algoritmica desenvolvida pelo professor Jander do DC-UFSCAR
 */

public class AnalisadorSintatico {

    public static void main(String[] args) throws IOException{
        
        // Abertura de arquivo para escrita
        try(PrintWriter pw = new PrintWriter(new File(args[1]))) { 
            CharStream cs = null;
            
            // Tenta carregar o conteúdo do arquivo de entrada como um fluxo de caracteres
            try {
                cs = CharStreams.fromFileName(args[0]);
            } catch (IOException ex) {
                Logger.getLogger(AnalisadorSintatico.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Geração dos tokens que serão utilizados para as verificações.
            AnalisadorSintaticoLALexer lexer = new AnalisadorSintaticoLALexer(cs);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            
            // Início do processo de análise.
            AnalisadorSintaticoLAParser parser = new AnalisadorSintaticoLAParser(tokens);

            // Analisa o programa de entrada.
            parser.programa();
         
        } catch(RuntimeException e){} // Exceção criada para evitar mensagens duplicadas.
    }
}
