package com.compiladores.analisadorsemantico;

import java.util.HashMap;
import java.util.Map;
import static com.compiladores.analisadorsemantico.Visitor.reduzNome;

public class TabelaDeSimbolos {
    // Mapa que associa nomes de identificadores (variáveis, funções, etc.) às suas entradas na tabela de símbolos
    private final Map<String, EntradaTabelaDeSimbolos> tabela;
    
    // Inicializa a tabela como um HashMap vazio
    public TabelaDeSimbolos() {
        this.tabela = new HashMap<>();
    }
       
    // Retorna o tipo associado a um identificador, assumindo que ele já existe
    public Tipos verificar(String nome) {
        // Remoção do índice de um vetor.
        nome = reduzNome(nome, "[");
        return tabela.get(nome).tipo;
    }
    
    // Adiciona uma nova entrada à tabela com o nome e tipo fornecidos
    public void adicionar(String nome, Tipos tipo, TiposEntrada tipoEntrada) {
        // Remoção do índice de um vetor.
        nome = reduzNome(nome, "[");
        tabela.put(nome, new EntradaTabelaDeSimbolos(nome, tipo, tipoEntrada));
    }
    
    // Verifica se o identificador já foi declarado neste escopo
    public boolean existe(String nome) {
        // Remoção do índice de um vetor.
        nome = reduzNome(nome, "[");
        return tabela.containsKey(nome);
    }
}
