package Lexer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Scanner 
{
    private String input;
    private int position;
    private Map<String, Tokentype> keywords;
    private Map<Character, Tokentype> operators;
    
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
        //OPERADORES
        operators = new HashMap<>();
        operators.put('=',Tokentype.IGU);
        operators.put('+', Tokentype.SUM);
    }

    public List<Token> scan() {
        List<Token> tokens = new LinkedList<>();
        
        while (position < input.length()) {
            char currentChar = input.charAt(position);
            
            if (Character.isDigit(currentChar)) {
                // Token de número
                int value = 0;
                while (position < input.length() && Character.isDigit(input.charAt(position))) {
                    value = value * 10 + Character.getNumericValue(input.charAt(position));
                    position++;
                }
                tokens.add(new Token(Tokentype.NUMBER, Integer.toString(value)));
            } else if (Character.isLetter(currentChar)) {
                // Token de palabra clave o identificador
                StringBuilder sb = new StringBuilder();
                while (position < input.length() && (Character.isLetterOrDigit(input.charAt(position)) || input.charAt(position) == '_')) {
                    sb.append(input.charAt(position));
                    position++;
                }
                String word = sb.toString();
                Tokentype type = keywords.getOrDefault(word, Tokentype.ID);
                tokens.add(new Token(type, word));
            } else if (operators.containsKey(currentChar)) {
                // Token de operador
                Tokentype type = operators.get(currentChar);
                tokens.add(new Token(type, Character.toString(currentChar)));
                position++;
            } else if (Character.isWhitespace(currentChar)) {
                // Ignorar espacios en blanco
                position++;
            }
        }
        
        // Agregar token de fin de archivo
        tokens.add(new Token(Tokentype.END, ""));
        
        return tokens;
    }
}
