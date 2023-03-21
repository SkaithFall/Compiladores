package Lexer;

public enum Token
{
    ID,
    //ESTRUCTURAS DE CONTROL
    IF,
    THEN,
    ELSE,
    FOR,
    TO,
    CASE,
    OF,
    WHILE,
    DO,
    REPEAT,
    UNTIL,
    BEGIN,
    END,
    //OPERADORES
    SUMA,
    RESTA,
    MULTIPLICACION,
    DIVISION,
    //RELACIONALES
    MAYOR,
    MENOR,
    MAYORIGUAL,
    MENORIGUAL,
    IGUAL,
    DIFERENTE,
    //TIPOS DE DATOS
    INT,
    FLOAT,
    STRING,
    //METODOS
    RETURN,
    SCAN,
    PRINT,
    //SIMBOLOS
    ASIGNACION,
    PUNTOCOMA,
    PUNTO,
    PAR_ABRE,
    PAR_CIERRA,
    LLAV_ABRE,
    LLAV_CIERRA,
    COMILLADOBLE,
    COMILLASIMPLE,
    DIAGONAL,
    DIAGONALDOBLE,


}