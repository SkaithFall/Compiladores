package Compilador;

public enum Tokentype
{
        //PALABRAS RESERVADAS
        SUPER,
        TRUE,
        FALSE,
        NULL,
        THIS,
        NUMBER,
        STRING,
        AND,
        OR,
        WHILE,
        ELSE,
        FOR,
        RETURN,
        PRINT,
        FUN,
        CLASS,
        //OPERADORES
        SUMA,
        RESTA,
        MULTI,
        DIV,
        IGUAL,
        ASIG,
        DIFERENTE,
        MAYOR,
        MENOR,
        MAYOR_IGUAL,
        MENOR_IGUAL,
        //SIMBOLOS
        PAR_ABRE,
        PAR_CIERRE,
        LLAV_ABRE,
        LLAV_CIERRE,
        COMA,
        PUNTO,
        PUNTO_COMA,
        //OTROS
        ID,
        END
}
