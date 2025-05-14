/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.compiladores.analisadorsemantico;


/**
 * Classe que representa uma entrada na tabela de símbolos
 * Cada entrada tem um nome e um tipo
 */
class EntradaTabelaDeSimbolos {
    String nome;
    Tipos tipo;
    
    // Construtor da classe
    public EntradaTabelaDeSimbolos(String nome, Tipos tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }
}
