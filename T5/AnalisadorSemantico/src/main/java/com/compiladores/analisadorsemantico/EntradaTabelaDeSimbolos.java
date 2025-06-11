package com.compiladores.analisadorsemantico;

/**
 * Classe que representa uma entrada na tabela de símbolos
 * Cada entrada tem um nome, um tipo e um tipo de entrada
 */
class EntradaTabelaDeSimbolos {
    String nome;
    Tipos tipo;
    TiposEntrada tipoEntrada;
    
    // Construtor da classe
    public EntradaTabelaDeSimbolos(String nome, Tipos tipo, TiposEntrada tipoEntrada) {
        this.nome = nome;
        this.tipo = tipo;
        this.tipoEntrada = tipoEntrada;
    }
}
