/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.compiladores.analisadorsemantico;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lucas
 */
public class TabelaDeSimbolos {
    private final Map<String, EntradaTabelaDeSimbolos> tabela;
    
    public TabelaDeSimbolos() {
        this.tabela = new HashMap<>();
    }
    
    public Tipos verificar(String nome) {
        return tabela.get(nome).tipo;
    }

    public void adicionar(String nome, Tipos tipo) {
        tabela.put(nome, new EntradaTabelaDeSimbolos(nome, tipo));
    }

    public boolean existe(String nome) {
        return tabela.containsKey(nome);
    }
}
