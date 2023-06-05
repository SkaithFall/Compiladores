package Compilador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Scanner {
    private final String source;

    private final List<Token> tokens = new ArrayList<>();

    private static final Map<String, Tokentype> keywords;
    static {
        keywords = new HashMap<>();
        keywords.put("super", Tokentype.SUPER);
        keywords.put("true", Tokentype.TRUE);
        keywords.put("false", Tokentype.FALSE);
        keywords.put("null", Tokentype.NULL);
        keywords.put("this", Tokentype.THIS);
        keywords.put("and", Tokentype.AND);
        keywords.put("or", Tokentype.OR);
        keywords.put("while", Tokentype.WHILE);
        keywords.put("if", Tokentype.IF);
        keywords.put("else", Tokentype.ELSE);
        keywords.put("return", Tokentype.RETURN);
        keywords.put("print", Tokentype.PRINT);
        keywords.put("for", Tokentype.FOR);
        keywords.put("fun", Tokentype.FUN);
        keywords.put("var", Tokentype.VAR);
        keywords.put("class", Tokentype.CLASS);
    }

    Scanner(String source){
        this.source = source + " ";
    }

    List<Token> scanTokens(){
        int estado = 0;
        char caracter = 0;
        String lexema = "";
        int inicioLexema = 0;

        for(int i=0; i<source.length(); i++){
            caracter = source.charAt(i);
            char nxtcaracter = (i + 1 < source.length()) ? source.charAt(i + 1) : '\0';
            
            switch (estado){
                case 0:
                    if(caracter == '+'){
                        tokens.add(new Token(Tokentype.SUMA, "+", i + 1));
                    }
                    else if(caracter == '-')
                    {
                        tokens.add(new Token(Tokentype.RESTA, "-", i + 1));
                    }
                    else if(caracter == '*')
                    {
                    tokens.add(new Token(Tokentype.MULTI, "*", i + 1));
                    }
                    else if(caracter == '/')
                    {
                    tokens.add(new Token(Tokentype.DIV, "/",i + 1));
                    }
                    else if(caracter == '=')
                    {
                        if(nxtcaracter == '=')
                        {
                            tokens.add(new Token(Tokentype.IGUAL, "==", i + 1));
                            i++;
                        }
                        else
                        {
                            tokens.add(new Token(Tokentype.ASIG, "=", i + 1));
                        }
                    }
                    else if(caracter == '(')
                    {
                        tokens.add(new Token(Tokentype.PAR_ABRE, "(", i + 1));
                    }
                    else if(caracter == ')')
                    {
                        tokens.add(new Token(Tokentype.PAR_CIERRE, ")", i + 1));
                    }
                    else if(caracter == '{')
                    {
                        tokens.add(new Token(Tokentype.LLAV_ABRE, "{", i + 1));
                    }
                    else if(caracter == '}')
                    {
                        tokens.add(new Token(Tokentype.LLAV_CIERRE, "}", i + 1));
                    } 
                    else if(caracter == '<')
                    {
                        if(nxtcaracter == '=')
                        {
                            tokens.add(new Token(Tokentype.MENOR_IGUAL, "<=", i + 1));
                            i++;
                        }
                        else
                        {
                            tokens.add(new Token(Tokentype.MENOR, "<", i + 1));
                        }
                    }
                    else if(caracter == '>')
                    {
                        if(nxtcaracter == '=')
                        {
                            tokens.add(new Token(Tokentype.MENOR_IGUAL, "<=", i + 1));
                            i++;
                        }
                        else
                        {
                            tokens.add(new Token(Tokentype.MAYOR, ">", i + 1));
                        }
                    }
                    else if(caracter == '!')
                    {
                        if(nxtcaracter == '=')
                        {
                            tokens.add(new Token(Tokentype.DIFERENTE, "!=", i + 1));
                            i++;
                        }
                        else
                        {
                            tokens.add(new Token(Tokentype.NOT, "!", i + 1));
                        }
                    }
                    
                    else if(caracter == ','){
                        tokens.add(new Token(Tokentype.COMA, ",", i + 1));
                    }
                    else if(caracter == '.'){
                        tokens.add(new Token(Tokentype.PUNTO, ".", i + 1));
                    }
                    else if(caracter == ';')
                    {
                        tokens.add(new Token(Tokentype.PUNTO_COMA, ";", i + 1));
                    }
                    else if(Character.isAlphabetic(caracter)){
                        estado = 1;
                        lexema = lexema + caracter;
                        inicioLexema = i;
                    }
                    else if(Character.isDigit(caracter))
                    {
                        estado = 2;
                        lexema = lexema + caracter;
                        inicioLexema = i;
                    }
                    else if(caracter == '"')
                    {
                        estado = 3;
                        lexema = lexema + caracter;
                        inicioLexema = i;
                    }
                    break;

                case 1:
                    if(Character.isAlphabetic(caracter) || Character.isDigit(caracter) ){
                        lexema = lexema + caracter;
                    }
                    else
                    {
                        Tokentype tt = keywords.get(lexema);
                        if(tt == null)
                        {
                            tokens.add(new Token(Tokentype.ID, lexema, inicioLexema + 1));
                        }
                        else
                        {
                            tokens.add(new Token(tt, lexema, inicioLexema + 1));
                        }
                        estado = 0;
                        i--;
                        lexema = "";
                        inicioLexema = 0;
                    }
                    break;
                case 2:
                    if(Character.isDigit(caracter) || caracter == '.' )
                    {
                        lexema = lexema + caracter;
                    }
                    else
                    {
                        if (lexema.contains("."))
                        {
                           try
                           {
                            tokens.add(new Token(Tokentype.NUMBER, lexema, inicioLexema + 1));
                           } 
                           catch (NumberFormatException e)
                           {
                            tokens.add(new Token(Tokentype.ERROR, lexema, inicioLexema + 1));
                           } 
                        }
                        else 
                        {
                            try
                            {
                                tokens.add(new Token(Tokentype.NUMBER, lexema, inicioLexema + 1));
                            } 
                            catch (NumberFormatException e)
                            {
                                tokens.add(new Token(Tokentype.ERROR, lexema, inicioLexema + 1));
                            }
                        }
                        estado = 0;
                        i--;
                        lexema = "";
                        inicioLexema = 0;
                    }
                    break;
                case 3:
                    lexema = lexema + caracter;
                    if (caracter == '"') 
                    {
                        if (lexema.length() > 1 && lexema.charAt(lexema.length() - 2) != '\\')
                        {
                            tokens.add(new Token(Tokentype.STRING, lexema, inicioLexema + 1));
                            estado = 0;
                            lexema = "";
                            inicioLexema = 0;
                        }
                    }
                    break;
            }
        }

        if (estado != 0) {
            throw new Error("Error: No se encontró el cierre de las comillas para el lexema de tipo string.");
        }
        tokens.add(new Token(Tokentype.END, "", source.length()));

        return tokens;
    }

}
