package Parser;
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
        keywords.put("if", Tokentype.SUPER);
        keywords.put("else", Tokentype.ELSE);
        keywords.put("while",Tokentype.WHILE);
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
                tokens.add(new Token(tokentype, identifier,position));
            } else if (Character.isDigit(entrada))
            {
                //PARA NUMERO
                String number = "";

                while( position < input.length() && Character.isDigit(input.charAt(position)))
                {
                    number += input.charAt(position);
                    position++;
                }
                tokens.add(new Token(Tokentype.NUMBER, number, position));
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
                tokens.add(new Token(Tokentype.STRING, string, position));
            } else 
            {
                //PARA SIMBOLOS Y OPERADORES
                switch(entrada)
                {
                    case '+':
                    tokens.add(new Token(Tokentype.SUMA, "+", position));
                    position++;
                    break;
                    case '-':
                    tokens.add(new Token(Tokentype.RESTA, "-", position));
                    position++;
                    break;
                    case '*':
                    tokens.add(new Token(Tokentype.MULTI, "*", position));
                    case '/':
                    tokens.add(new Token(Tokentype.DIV, "/", position));
                    case '=':
                    if (position + 1 < input.length() && input.charAt(position + 1) == '=')
                    {
                        tokens.add(new Token(Tokentype.IGUAL, "==", position));
                        position += 2;
                    } else 
                    {
                        tokens.add(new Token(Tokentype.ASIGNACION, "=", position));
                        position++;
                    }
                    break;
                    case '<':
                    if (position +1 < input.length() && input.charAt(position + 1) == '=')
                    {
                        tokens.add(new Token(Tokentype.MENOR_IGUAL, "<=", position));
                        position += 2;
                    } else 
                    {
                        tokens.add(new Token(Tokentype.MENOR, "<", position));
                        position++;
                    }
                    break;
                    case '>':
                    if (position +1 < input.length() && input.charAt(position + 1) == '=')
                    {
                        tokens.add(new Token(Tokentype.MAYOR_IGUAL, ">=", position));
                        position += 2;
                    } else 
                    {
                        tokens.add(new Token(Tokentype.MAYOR, ">", position));
                        position++;
                    }
                    break;
                    case '(':
                    tokens.add(new Token(Tokentype.PAR_ABRE, "(", position));
                    position++;
                    break;
                    case ')':
                    tokens.add(new Token(Tokentype.PAR_CIERRE, ")", position));
                    position++;
                    break;
                    case '{':
                    tokens.add(new Token(Tokentype.LLAV_ABRE, "{", position));
                    position++;
                    break;
                    case '}':
                    tokens.add(new Token(Tokentype.LLAV_CIERRE, "}", position));
                    position++;
                    break;
                    case '.':
                    tokens.add(new Token(Tokentype.PUNTO, ".", position));
                    position++;
                    break;
                    case ',':
                    tokens.add(new Token(Tokentype.COMA, ",", position));
                    position++;
                    break;
                    case ';':
                    tokens.add(new Token(Tokentype.PUNTO_COMA, ";", position));
                    position++;
                    break;
                    default:
                    throw new IllegalArgumentException("Caracter inesperado:"+entrada);

                }
            }
        }
        tokens.add(new Token(Tokentype.END, "$", position));
        return tokens;
    }

    
}
