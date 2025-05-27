/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.compiladores.analisadorsemantico;

import java.util.HashMap;
import java.util.Map;


public class TabelaDeSimbolos {
    // Mapa que associa nomes de identificadores (variáveis, funções, etc.) às suas entradas na tabela de símbolos
    private final Map<String, EntradaTabelaDeSimbolos> tabela;
    
    // Inicializa a tabela como um HashMap vazio
    public TabelaDeSimbolos() {
        this.tabela = new HashMap<>();
    }
       
    // Retorna o tipo associado a um identificador, assumindo que ele já existe
    public Tipos verificar(String nome) {
        return tabela.get(nome).tipo;
    }
    
    // Adiciona uma nova entrada à tabela com o nome e tipo fornecidos
    public void adicionar(String nome, Tipos tipo) {
        tabela.put(nome, new EntradaTabelaDeSimbolos(nome, tipo));
    }
    
    // Verifica se o identificador já foi declarado neste escopo
    public boolean existe(String nome) {
        return tabela.containsKey(nome);
    }
}
