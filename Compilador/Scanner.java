package Compilador;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner 
{
    private final String source;

    private final List<Token> tokens = new ArrayList<>();

    private static final Map<String, Tokentype> keyword;
    static
    {
        keyword = new HashMap<>();
        keyword.put("super", Tokentype.SUPER);
        keyword.put("true", Tokentype.TRUE);
    }

    Scanner(String source)
    {
        this.source = source + "";
    }

    List<Token> scanList()
    {
        int estado = 0;
        char caracter = 0;
        String lexema = "";
        int inicioLexema = 0;

        for( int i=0; i<source.length(); i++)
        {
            caracter = source.charAt(i);
            char nxtcaracter = (i + 1 < source.length()) ? source.charAt(i + 1) : '\0';

            switch (estado)
            {
                case 0:
                if(caracter == '+')
                {
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
                    }
                    else
                    {
                        tokens.add(new Token(Tokentype.MAYOR, ">", i + 1));
                    }
                }
                else if(caracter == '!' && nxtcaracter == '=')
                {
                    tokens.add(new Token(Tokentype.DIFERENTE, "!=", i + 1));
                }
                break;
                case 1:
                if(Character.isAlphabetic(caracter) || Character.isDigit(caracter))
                {
                    lexema = lexema + caracter;
                }
                else
                {
                    Tokentype tt = keyword.get(lexema);
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
            }
        }
        tokens.add(new Token(Tokentype.END, "", source.length()));

        return tokens;
    }
    
}
