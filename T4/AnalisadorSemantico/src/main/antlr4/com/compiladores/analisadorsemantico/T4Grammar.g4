grammar T4Grammar;

// -------------------
// Tokens Léxicos
// -------------------

// Números inteiros e reais
NUM_INT     : ('0'..'9')+;
NUM_REAL    : ('0'..'9')+ ('.' ('0'..'9')+)?;

// Identificadores
IDENT       : ('a'..'z' | 'A'..'Z') ('_' | 'a'..'z' | 'A'..'Z' | '0'..'9')*;

// Cadeias de caracteres
CADEIA      : '"' ( ESC_SEQ | ~('"' | '\\' | '\n') )* '"';

// Sequência de escape para aspas
fragment ESC_SEQ : '\\"';

// Comentários ignorados
COMENTARIO : '{' ~('}')* '}' -> skip;

// Espaços em branco
WS : ( ' ' | '\t' | '\r' | '\n' ) {skip();};

// Tratamento de erros léxicos
CADEIA_ERRADA     : '"' ( ESC_SEQ | ~('"' | '\\' | '\n'))* '\n';
COMENTARIO_ERRADO : '{' ~('\n' | '}')* '\n';
ERRO              : .;

// -------------------
// Regras Sintáticas
// -------------------

// Estrutura principal do programa
programa : declaracoes 'algoritmo' corpo 'fim_algoritmo' EOF;

// Declarações (locais ou globais)
declaracoes : (decl_local_global)*;

decl_local_global 
    : declaracao_local 
    | declaracao_global;

// Declarações locais
declaracao_local 
    : 'declare' variavel
    | 'constante' IDENT ':' tipo_basico '=' valor_constante
    | 'tipo' IDENT ':' tipo;

// Declaração de variáveis
variavel : identificador (',' identificador)* ':' tipo;

// Identificador com possível dimensão
identificador : IDENT ('.' IDENT)* dimensao;

// Dimensão de vetor/matriz
dimensao : ('[' exp_aritmetica ']')*;

// Tipos
tipo : registro | tipo_estendido;

tipo_basico : 'literal' | 'inteiro' | 'real' | 'logico';
tipo_basico_ident : tipo_basico | IDENT;
tipo_estendido : ('^')? tipo_basico_ident;

// Constantes
valor_constante : CADEIA | NUM_INT | NUM_REAL | 'verdadeiro' | 'falso';

// Registro de dados
registro : 'registro' (variavel)* 'fim_registro';

// Declarações globais (procedimentos e funções)
declaracao_global 
    : 'procedimento' IDENT '(' (parametros)? ')' (declaracao_local)* (cmd)* 'fim_procedimento'
    | 'funcao' IDENT '(' (parametros)? ')' ':' tipo_estendido (declaracao_local)* (cmd)* 'fim_funcao';

// Parâmetros
parametro : 'var'? identificador (',' identificador)* ':' tipo_estendido;
parametros : parametro (',' parametro)*;

// Corpo do algoritmo
corpo : (declaracao_local)* (cmd)*;

// -------------------
// Comandos
// -------------------
cmd 
    : cmdLeia 
    | cmdEscreva 
    | cmdSe 
    | cmdCaso 
    | cmdPara 
    | cmdEnquanto
    | cmdFaca 
    | cmdAtribuicao 
    | cmdChamada 
    | cmdRetorne;

// Comando de leitura
cmdLeia : 'leia' '(' ('^')? identificador (',' ('^')? identificador)* ')';

// Comando de escrita
cmdEscreva : 'escreva' '(' expressao (',' expressao)* ')';

// Comando condicional (if-else)
cmdSe : 'se' expressao 'entao' (cmd)* ('senao' (cmd)*)? 'fim_se';

// Comando de escolha (switch-case)
cmdCaso : 'caso' exp_aritmetica 'seja' selecao ('senao' (cmd)*)? 'fim_caso';

// Comando de repetição "para"
cmdPara : 'para' IDENT '<-' exp_aritmetica 'ate' exp_aritmetica 'faca' (cmd)* 'fim_para';

// Comando de repetição "enquanto"
cmdEnquanto : 'enquanto' expressao 'faca' cmd* 'fim_enquanto';

// Comando "faça...até"
cmdFaca : 'faca' (cmd)* 'ate' expressao;

// Comando de atribuição
cmdAtribuicao : ('^')? identificador '<-' expressao;

// Chamada de função ou procedimento
cmdChamada : IDENT '(' expressao (',' expressao)* ')';

// Comando de retorno de função
cmdRetorne : 'retorne' expressao;

// -------------------
// Comando "caso"
// -------------------
selecao : (item_selecao)*;

item_selecao : constantes ':' (cmd)*;

constantes : numero_intervalo (',' numero_intervalo)*;

numero_intervalo : (op_unario)? NUM_INT ('..' (op_unario)? NUM_INT)?;

op_unario : '-';

// -------------------
// Expressões Aritméticas
// -------------------
exp_aritmetica : termo (op1 termo)*;
termo          : fator (op2 fator)*;
fator          : parcela (op3 parcela)*;

op1 : '+' | '-';
op2 : '*' | '/';
op3 : '%';

// Parcelas (elementos atômicos das expressões)
parcela : (op_unario)? parcela_unario | parcela_nao_unario;

parcela_unario 
    : ('^')? identificador
    | IDENT '(' expressao (',' expressao)* ')'
    | NUM_INT
    | NUM_REAL
    | '(' expressao ')';

parcela_nao_unario 
    : '&' identificador
    | CADEIA;

// -------------------
// Expressões Relacionais e Lógicas
// -------------------
exp_relacional : exp_aritmetica (op_relacional exp_aritmetica)?;

op_relacional : '=' | '<>' | '>=' | '<=' | '>' | '<';

expressao : termo_logico (op_logico_1 termo_logico)*;

termo_logico : fator_logico (op_logico_2 fator_logico)*;

fator_logico : ('nao')? parcela_logica;

parcela_logica : ('verdadeiro' | 'falso') | exp_relacional;

op_logico_1 : 'ou';
op_logico_2 : 'e';