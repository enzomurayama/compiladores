lexer grammar AnalisadorLexicoLA;

PALAVRA_CHAVE: 'algoritmo' | 'fim_algoritmo' | 'declare' | 'literal' | 'inteiro' |
        'leia' | 'escreva' | 'real' | 'logico' | 'enquanto' | 'fim_enquanto' |
        'se' | 'entao' | 'senao' | 'fim_se' | 'caso' |
        'seja' | 'fim_caso' | 'para' | 'ate' | 'faca' | 'fim_para' |
        'registro' | 'fim_registro' | 'tipo' | 'procedimento' | 'var' | 'fim_procedimento' |
        'funcao' | 'retorne' | 'fim_funcao' | 'constante' | 'falso' | 'verdadeiro';

COMMENT: '{' ~('}'|'{'|'\r'|'\n')* '}' -> skip;
WHITE_SPACE: ([ \t\r\n]) -> skip;
