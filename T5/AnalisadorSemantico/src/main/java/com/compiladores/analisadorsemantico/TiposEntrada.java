package com.compiladores.analisadorsemantico;

/**
 * Enumeração que representa os diferentes tipos de entradas na tabela de símbolos.
 * 
 * - VARIAVEL: Inclui variáveis comuns, constantes, parâmetros de funções/procedimentos e campos de registros.
 * - PROCEDIMENTO: Representa um procedimento declarado no programa.
 * - FUNCAO: Representa uma função declarada no programa.
 */
public enum TiposEntrada {
    VARIAVEL,
    PROCEDIMENTO,
    FUNCAO
}
