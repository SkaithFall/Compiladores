package Compilador;
import java.util.List;
import java.util.function.Function;

public class Parser 
{
    private final List<Token> tokens;
    
    private final Token IDENTIFICADOR = new Token(Tokentype.ID, "");
    private final Token SUPER = new Token(Tokentype.SUPER, "super");
    private final Token FALSE = new Token(Tokentype.FALSE, "false");
    private final Token CLASS = new Token(Tokentype.CLASS, "class");
    private final Token TRUE = new Token(Tokentype.TRUE, "true");
    private final Token NULL = new Token(Tokentype.NULL, "null");
    private final Token THIS = new Token(Tokentype.THIS, "this");
    private final Token NUMERO = new Token(Tokentype.NUMBER, "");
    private final Token STRING = new Token(Tokentype.STRING, "string");
    private final Token AND = new Token(Tokentype.AND, "and");
    private final Token OR = new Token(Tokentype.OR, "or");
    private final Token WHILE = new Token(Tokentype.WHILE, "while");
    private final Token ELSE = new Token(Tokentype.ELSE, "else");
    private final Token RETURN = new Token(Tokentype.RETURN, "return");
    private final Token PRINT = new Token(Tokentype.PRINT, "print");
    private final Token FOR = new Token(Tokentype.FOR, "for");
    private final Token FUN = new Token(Tokentype.FUN, "fun");
    private final Token END = new Token(Tokentype.END, "");
    private final Token LLAV_ABRE = new Token(Tokentype.LLAV_ABRE, "{");
    private final Token LLAV_CIERRE = new Token(Tokentype.LLAV_CIERRE, "}");
    private final Token PAR_ABRE = new Token(Tokentype.PAR_ABRE, "(");
    private final Token PAR_CIERRE = new Token(Tokentype.PAR_CIERRE, ")");
    private final Token COMA = new Token(Tokentype.COMA, ",");
    private final Token PUNTO = new Token(Tokentype.PUNTO, ".");
    private final Token SUMA = new Token(Tokentype.SUMA, "+");
    private final Token RESTA = new Token(Tokentype.RESTA, "-");
    private final Token MULTI = new Token(Tokentype.MULTI, "*");
    private final Token DIV = new Token(Tokentype.DIV, "/");
    private final Token MAYOR = new Token(Tokentype.MAYOR, ">");
    private final Token MENOR = new Token(Tokentype.MENOR, "<");
    private final Token IGUAL = new Token(Tokentype.IGUAL, "==");
    private final Token ASIG = new Token(Tokentype.ASIG, "=");
    private final Token MAYOR_IGUAL = new Token(Tokentype.MAYOR_IGUAL, ">=");
    private final Token MENOR_IGUAL = new Token(Tokentype.MENOR_IGUAL, "<=");
    private final Token DIFERENTE = new Token(Tokentype.DIFERENTE, "!=");
    private final Token PUNTO_COMA = new Token(Tokentype.PUNTO_COMA,";");

    private int i = 0;
    private boolean hayErrores = false;

    private Token preanalisis;

    public Parser(List<Token> tokens)
    {
        this.tokens = tokens;
    }

    public void parse()
    {
        i = 0;
        preanalisis = tokens.get(i);
        PROGRAM();
    
        if(!hayErrores && !preanalisis.equals(fincadena))
        {
        System.out.println("Error en la posición " + preanalisis.posicion + ". No se esperaba el token " + preanalisis.type);
        }
        else if(!hayErrores && preanalisis.equals(fincadena))
        {
        System.out.println("Consulta válida");
        }
    }

    void PROGRAM()
    {
        if(preanalisis.equals(DECLARATION))
        {
            coincidir(DECLARATION);
        }
        else
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion);
        }
    }

    void DECLARATION()
    {
        if(hayErrores) return;

        if(preanalisis.equals(CLASS_DECL))
        {
            coincidir(CLASS_DECL);
            DECLARATION();
        }
        else if(preanalisis.equals(FUN_DECL))
        {
            coincidir(FUN_DECL);
            DECLARATION();
        }
        else if(preanalisis.equals(VAR_DECL))
        {
            coincidir(VAR_DECL);
            DECLARATION();
        }
        else if(preanalisis.equals(STATEMENT))
        {
            coincidir(STATEMENT);
            DECLARATION();
        }
    }

    void CLASS_DECL()
    {
        if(hayErrores) return;

        if(preanalisis.equals(CLASS))
        {
            coincidir(CLASS);
            coincidir(IDENTIFICADOR);
            CLASS_INHER();
            coincidir(LLAV_ABRE);
            FUNCTIONS();
            coincidir(LLAV_CIERRE);
        }
        else
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  " + CLASS.type);
        }
    }

    void CLASS_INHER()
    {
        if(hayErrores) return;

        if(preanalisis.equals(MENOR))
        {
            coincidir(MENOR);
            coincidir(IDENTIFICADOR);
        }
    }

    void FUN_DECL()
    {
        if(hayErrores) return;

        if(preanalisis.equals(FUN))
        {
            coincidir(FUN);
            FUNCTION();
        }
        else
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  " + FUN.type);
        }
    }

    void VAR_DECL()
    {
        if(hayErrores) return;

        if(preanalisis.equals(IDENTIFICADOR))
        {
            coincidir(IDENTIFICADOR);
            VAR_INIT();
            coincidir(PUNTO_COMA);
        }
        else
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  " + IDENTIFICADOR.type);
        }
    }

    void VAR_INIT()
    {
        if(hayErrores) return;

        if(preanalisis.equals(ASIG))
        {
            coincidir(ASIG);
            EXPRESSION();
        }
    }

    private void coincidir(Token t) 
    {
        if(hayErrores) return;

        if(preanalisis.type == t.type){
            i++;
            preanalisis = tokens.get(i);
        }
        else{
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  " + t.type);

        }
    }

}
