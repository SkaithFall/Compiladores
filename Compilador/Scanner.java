package Compilador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {
    private final String source;

    private final List<Token> tokens = new ArrayList<>();

    private static final Map<String, Tokentype> palabrasReservadas;
    static {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("super", Tokentype.SUPER);
        palabrasReservadas.put("true", Tokentype.TRUE);
        palabrasReservadas.put("false", Tokentype.FALSE);
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

            switch (estado){
                case 0:
                    if(caracter == '*'){
                        tokens.add(new Token(Tokentype.MULTI, "*", i + 1));
                    }
                    else if(caracter == ','){
                        tokens.add(new Token(Tokentype.COMA, ",", i + 1));
                    }
                    else if(caracter == '.'){
                        tokens.add(new Token(Tokentype.PUNTO, ".", i + 1));
                    }
                    else if(Character.isAlphabetic(caracter)){
                        estado = 1;
                        lexema = lexema + caracter;
                        inicioLexema = i;
                    }
                    break;

                case 1:
                    if(Character.isAlphabetic(caracter) || Character.isDigit(caracter) ){
                        lexema = lexema + caracter;
                    }
                    else{
                        Tokentype tt = palabrasReservadas.get(lexema);
                        if(tt == null){
                            tokens.add(new Token(Tokentype.ID, lexema, inicioLexema + 1));
                        }
                        else{
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
