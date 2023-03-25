package Lexer;

public class Tokentype 
{
    public enum Type
    {
        //ESENCIALES
        IDE,
        STR,
        //TIPOS DE DATOS
        INT,
        FLO,
        CHA,
        //PALABRAS RESERVADAS
        IF,
        ELSE,
        THEN,
        WHILE,
        DO,
        FOR,
        SWITCH,
        CASE,
        //OPERADORES
        SUM,
        RES,
        MUL,
        DIV,
        IGUAL,
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
        PUNRO,
        PUNTO_COMA,
        //FIN DE CADENA
        ENDS
    }
}
