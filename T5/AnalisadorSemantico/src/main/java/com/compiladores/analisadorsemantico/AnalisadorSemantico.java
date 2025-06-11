package com.compiladores.analisadorsemantico;

import com.compiladores.analisadorsemantico.T5GrammarParser.ProgramaContext;
import java.io.FileWriter;
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
 * Analisador semântico para a Linguagem Algoritmica desenvolvida pelo professor Jander do DC-UFSCAR
 */
public class AnalisadorSemantico {

    public static void main(String[] args) throws IOException {
         // Inicializa o PrintWriter para escrita no arquivo de saída
        try(PrintWriter pw = new PrintWriter(new FileWriter(args[1]))) { 
            CharStream cs = null;
            
            // Tenta carregar o conteúdo do arquivo de entrada como um fluxo de caracteres
            try {
                cs = CharStreams.fromFileName(args[0]);
            } catch (IOException ex) {
                Logger.getLogger(AnalisadorSemantico.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Analisador léxico - Gera os tokens léxicos a partir do código-fonte
            T5GrammarLexer lexer = new T5GrammarLexer(cs);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            
            // Analisador sintático - Cria o parser a partir dos tokens gerados
            T5GrammarParser parser = new T5GrammarParser(tokens);
            Visitor visitor = new Visitor();
            ProgramaContext arvore = parser.programa();
            
            // Chama o visitor
            visitor.visitPrograma(arvore);
             
            // Tratamento de erros customizados
            // Remove os listeners de erro padrão e adiciona o listener personalizado
            // Imprime erros semânticos, se houver
                       // Escreve erros semânticos, se houver
            if (!AnalisadorSemanticoUtils.errosSemanticos.isEmpty()) {
                AnalisadorSemanticoUtils.errosSemanticos.forEach(pw::println);
                
                // Finaliza a compilação
                pw.println("Fim da compilacao");
            } else {
                // Gera e escreve código C se não houver erros
                GeradorC agc = new GeradorC();
                try {
                    agc.visitPrograma(arvore);
                    pw.print(agc.saida.toString());
                } catch (Exception ex) {
                    String msg = "Erro ao gerar código C: " + ex.getMessage();
                    pw.println(msg);
                }
            }

            pw.flush(); // Garante que todo o conteúdo seja escrito no arquivo 
        } catch(RuntimeException e){
            // Captura exceção gerada pelo ErrorHandler para evitar mensagens duplicadas
        } 
    }
}
