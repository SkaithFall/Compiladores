package Lexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner 
{
    private String input;
    private int position;
    private Map<String, Tokentype> keywords;
    private List<Token> tokens = new ArrayList<>();
    
    public Scanner(String input)
    {
        this.input = input;
        this.position = 0;

        //PALABRAS CLAVE
        keywords = new HashMap<>();
        keywords.put("if", Tokentype.IF);
        keywords.put("else", Tokentype.ELSE);
        keywords.put("then", Tokentype.THEN);
        keywords.put("while",Tokentype.WHILE);
        keywords.put("do",Tokentype.DO);
        keywords.put("switch", Tokentype.SWITCH);
        keywords.put("case", Tokentype.CASE);
        keywords.put("return", Tokentype.RETURN);

    }

    public List<Token> scanList()
    {
        while(position < input.length())
        {

            char entrada = input.charAt(position);

            if(Character.isWhitespace(entrada))
            {
                //IGNORAR ESPACIOS VACIOS
                position++;
            } else if (Character.isLetter(entrada))
            {
                //PARA IDENTIFICADOR
                String identifier = "";

                while(position < input.length() && Character.isLetterOrDigit(input.charAt(position)))
                {
                    identifier += input.charAt(position);
                    position++;
                }

                Tokentype tokentype;
                
                if(keywords.containsKey(identifier))
                {
                    tokentype = keywords.get(identifier);
                } else 
                {
                    tokentype = Tokentype.ID;
                }
                tokens.add(new Token(tokentype, identifier));
            } else if (Character.isDigit(entrada))
            {
                //PARA NUMERO
                String number = "";

                while( position < input.length() && Character.isDigit(input.charAt(position)))
                {
                    number += input.charAt(position);
                    position++;
                }
                tokens.add(new Token(Tokentype.NUMBER, number));
            } else if (entrada == '"')
            {
                //PARA CADENAS
                String string = "";
                
                position++;
                while(position < input.length() && input.charAt(position) != '"')
                {
                    string += input.charAt(position);
                    position++;
                }
                position++;
                tokens.add(new Token(Tokentype.STRING, string));
            } else 
            {
                //PARA SIMBOLOS Y OPERADORES
                switch(entrada)
                {
                    case '+':
                    tokens.add(new Token(Tokentype.SUMA, "+"));
                    position++;
                    break;
                    case '-':
                    tokens.add(new Token(Tokentype.RESTA, "-"));
                    position++;
                    break;
                    case '*':
                    tokens.add(new Token(Tokentype.MULTI, "*"));
                    case '/':
                    tokens.add(new Token(Tokentype.DIV, "/"));
                    case '=':
                    if (position + 1 < input.length() && input.charAt(position + 1) == '=')
                    {
                        tokens.add(new Token(Tokentype.IGUAL, "=="));
                        position += 2;
                    } else 
                    {
                        tokens.add(new Token(Tokentype.ASIGNACION, "="));
                        position++;
                    }
                    break;
                    case '<':
                    if (position +1 < input.length() && input.charAt(position + 1) == '=')
                    {
                        tokens.add(new Token(Tokentype.MENOR_IGUAL, "<="));
                        position += 2;
                    } else 
                    {
                        tokens.add(new Token(Tokentype.MENOR, "<"));
                        position++;
                    }
                    break;
                    case '>':
                    if (position +1 < input.length() && input.charAt(position + 1) == '=')
                    {
                        tokens.add(new Token(Tokentype.MAYOR_IGUAL, ">="));
                        position += 2;
                    } else 
                    {
                        tokens.add(new Token(Tokentype.MAYOR, ">"));
                        position++;
                    }
                    break;
                    case '(':
                    tokens.add(new Token(Tokentype.PAR_ABRE, "("));
                    position++;
                    break;
                    case ')':
                    tokens.add(new Token(Tokentype.PAR_CIERRE, ")"));
                    position++;
                    break;
                    case '{':
                    tokens.add(new Token(Tokentype.LLAV_ABRE, "{"));
                    position++;
                    break;
                    case '}':
                    tokens.add(new Token(Tokentype.LLAV_CIERRE, "}"));
                    position++;
                    break;
                    case '.':
                    tokens.add(new Token(Tokentype.PUNTO, "."));
                    position++;
                    break;
                    case ',':
                    tokens.add(new Token(Tokentype.COMA, ","));
                    position++;
                    break;
                    case ';':
                    tokens.add(new Token(Tokentype.PUNTO_COMA, ";"));
                    position++;
                    break;
                    default:
                    throw new IllegalArgumentException("Caracter inesperado:"+entrada);

                }
            }
        }
        tokens.add(new Token(Tokentype.END, "$"));
        return tokens;
    }

    
}
