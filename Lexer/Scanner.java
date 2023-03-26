package Lexer;

import java.util.HashMap;
import java.util.Map;

public class Scanner 
{
    private String input;
    private int position;
    private Map<String, Tokentype> keywords;
    
    public Scanner(String input)
    {
        this.input = input;
        this.position = 0;

        this.keywords = new HashMap<String, Tokentype>();
        this.keywords.put("if", Tokentype.IF);
        this.keywords.put("else", Tokentype.ELSE);
        this.keywords.put("while",Tokentype.WHILE);
        this.keywords.put("do",Tokentype.DO);
        this.keywords.put("switch", Tokentype.SWITCH);
        this.keywords.put("case", Tokentype.CASE);
        this.keywords.put("return", Tokentype.RETURN);
    }

    public Token getNexToken()
    {
        while(position < input.length())
        {
            char entrada = input.charAt(position);

            if(Character.isDigit(entrada))
            {
                return getNum();
            } else if (Character.isLetter(entrada))
            {
                return getIDEorKYW();
            } else if (entrada == ')')
            {
                position++;
                return new Token(Tokentype.PAR_CIERRE, ")");
            } else if (entrada == '{')
            {
                position++;
                return new Token(Tokentype.LLAV_ABRE, "{");
            } else if (entrada == '}')
            {
                position++;
                return new Token(Tokentype.PAR_ABRE, "}");
            } else if (entrada == ',')
            {
                position++;
                return new Token(Tokentype.COMA, ",");
            } else if (entrada == '.')
            {
                position++;
                return new Token(Tokentype.PUNTO, ".");
            } else if (entrada == ';')
            {
                position++;
                return new Token(Tokentype.PUNTO_COMA, ";");
            } else if (entrada == '+')
            {
                position++;
                return new Token(Tokentype.SUM, "+");
            } else if (entrada == '-')
            {
                position++;
                return new Token(Tokentype.RES, "-");
            } else if (entrada == '*')
            {
                position++;
                return new Token(Tokentype.MUL, "*");
            } else if (entrada == '/')
            {
                position++;
                return new Token(Tokentype.DIV, "/");
            } else if (entrada == '>')
            {
                position++;
                if (position < input.length() && input.charAt(position) == '=')
                {
                    position++;
                    return new Token(Tokentype.MAYOR_IGUAL, ">=");
                }
                return new Token(Tokentype.MAYOR, ">");
            } else if (entrada == '<')
            {
                position++;
                if (position < input.length() && input.charAt(position) == '=')
                {
                    position++;
                    return new Token(Tokentype.MENOR_IGUAL, "<=");
                }
                return new Token(Tokentype.MENOR, "<");
            } else if (entrada == '=')
            {
                position++;
                if (position < input.length() && input.charAt(position) == '=')
                {
                    position++;
                    return new Token(Tokentype.ASI, "==");
                }
                return new Token(Tokentype.IGU, "=");
            } else if (entrada == '!')
            {
                position++;
                if (position < input.length() && input.charAt(position) == '=')
                {
                    position++;
                    return new Token(Tokentype.DIFERENTE, "!=");
                }
                throw new RuntimeException("Operador invalido: !");
            }
            {
                throw new IllegalArgumentException("Caracter Invalido:"+entrada);
            }
        } 
        
        return new Token(Tokentype.END,"END");
    }

    private Token getNum() 
    {
        StringBuilder sb = new StringBuilder();
        char entrada = input.charAt(position);

        while(position < input.length() && Character.isDigit(entrada))
        {
            sb.append(entrada);
            position++;
            if(position < input.length())
            {
                entrada = input.charAt(position);
            }
        }

        return new Token(Tokentype.NUMBER, sb.toString());
    }

    private Token getIDEorKYW() 
    {
        StringBuilder sb = new StringBuilder();
        char entrada = input.charAt(position);

        while (position < input.length() && (Character.isLetterOrDigit(entrada) || entrada == '_'))
        {
            sb.append(entrada);
            position++;
            if(position < input.length())
            {
                entrada = input.charAt(position);
            } 
        }

        String ide = sb.toString();

        Tokentype type = Tokentype.IDE;
        if(keywords.containsKey(ide))
        {
            type = keywords.get(ide);
        }

        return new Token(type, ide);
    }
}
