package Lexer;

public enum Tokentype
    {
        //PALABRAS RESERVADAS
        IF,
        ELSE,
        THEN,
        WHILE,
        DO,
        FOR,
        SWITCH,
        CASE,
        RETURN,
        //OPERADORES
        OPERATOR,
        SUM,
        RES,
        MUL,
        DIV,
        IGU,
        ASI,
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
        IDE,
        NUMBER,
        END
    }
