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
        
         // Inicializa o PrintWriter para escrita no arquivo de saída
        try(PrintWriter pw = new PrintWriter(new File(args[1]))) { 
            CharStream cs = null;
            
            // Tenta carregar o conteúdo do arquivo de entrada como um fluxo de caracteres
            try {
                cs = CharStreams.fromFileName(args[0]);
            } catch (IOException ex) {
                Logger.getLogger(AnalisadorSintatico.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Gera os tokens léxicos a partir do código-fonte
            AnalisadorSintaticoLALexer lexer = new AnalisadorSintaticoLALexer(cs);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            
            // Cria o parser a partir dos tokens gerados
            AnalisadorSintaticoLAParser parser = new AnalisadorSintaticoLAParser(tokens);
            
            // Tratamento de erros customizados
            // Remove os listeners de erro padrão e adiciona o listener personalizado
            ErrorHandler mcel = new ErrorHandler(pw);
            parser.removeErrorListeners(); 
            parser.addErrorListener(mcel);

            // Inicia a análise sintática a partir da regra 'programa'
            parser.programa();
         
        } catch(RuntimeException e){
            // Captura exceção gerada pelo ErrorHandler para evitar mensagens duplicadas
        } 
    }
}
