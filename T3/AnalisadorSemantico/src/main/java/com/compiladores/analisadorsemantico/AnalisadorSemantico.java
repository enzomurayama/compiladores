/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.compiladores.analisadorsemantico;

import com.compiladores.analisadorsemantico.T3ParserParser.ProgramaContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 *
 * @author lucas
 */
public class AnalisadorSemantico {

    public static void main(String[] args) throws IOException {
         // Inicializa o PrintWriter para escrita no arquivo de saída
        try(PrintWriter pw = new PrintWriter(new File(args[1]))) { 
            CharStream cs = null;
            
            // Tenta carregar o conteúdo do arquivo de entrada como um fluxo de caracteres
            try {
                cs = CharStreams.fromFileName(args[0]);
            } catch (IOException ex) {
                Logger.getLogger(AnalisadorSemantico.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Gera os tokens léxicos a partir do código-fonte
            T3ParserLexer lexer = new T3ParserLexer(cs);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            
            // Cria o parser a partir dos tokens gerados
            T3ParserParser parser = new T3ParserParser(tokens);
            Visitor visitor = new Visitor();
            ProgramaContext arvore = parser.programa();
            
            //Chama o visitor
            visitor.visitPrograma(arvore);
             
            // Tratamento de erros customizados
            // Remove os listeners de erro padrão e adiciona o listener personalizado
           Visitor.errosSemanticos.forEach((erro) -> pw.println(erro));
           pw.println("Fim da compilacao");
           pw.close();
         
        } catch(RuntimeException e){
            // Captura exceção gerada pelo ErrorHandler para evitar mensagens duplicadas
        } 
    }
}
