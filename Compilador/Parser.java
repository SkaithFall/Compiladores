package Compilador;
import java.util.List;


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
    private final Token IF = new Token(Tokentype.IF, "if");
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
    private final Token NOT = new Token(Tokentype.NOT, "!=");
    private final Token VAR = new Token(Tokentype.VAR, "var");

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
    
        if(!hayErrores && !preanalisis.equals(END))
        {
        System.out.println("Error en la posición " + preanalisis.posicion + ". No se esperaba el token " + preanalisis.type);
        }
        else if(!hayErrores && preanalisis.equals(END))
        {
        System.out.println("Consulta válida");
        }
    }

    void PROGRAM()
    {
        if(preanalisis.equals(CLASS) || preanalisis.equals(FUN) || preanalisis.equals(VAR) || preanalisis.equals(NOT) || 
           preanalisis.equals(RESTA) || preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || preanalisis.equals(NULL) || 
           preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || preanalisis.equals(IDENTIFICADOR) || 
           preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER) || preanalisis.equals(FOR) || preanalisis.equals(IF) || 
           preanalisis.equals(PRINT) || preanalisis.equals(RETURN) || preanalisis.equals(WHILE) || preanalisis.equals(LLAV_ABRE))
        {
            DECLARATION();
        }
        else
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". No se esperaba el token " + preanalisis.type);
        }           
    }

    //DECLARACIONES

    void DECLARATION()
    {
        if(hayErrores) return;

        if(preanalisis.equals(CLASS))
        {
            CLASS_DECL();
            DECLARATION();
        }
        else if(preanalisis.equals(FUN))
        {
            FUN_DECL();
            DECLARATION();
        }
        else if(preanalisis.equals(VAR)) 
        {
            VAR_DECL();
            DECLARATION();
        }
        else if(preanalisis.equals(NOT) || preanalisis.equals(RESTA) || preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || 
                preanalisis.equals(NULL) || preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || 
                preanalisis.equals(IDENTIFICADOR) || preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER) || preanalisis.equals(FOR) || 
                preanalisis.equals(IF) || preanalisis.equals(PRINT) || preanalisis.equals(RETURN) || preanalisis.equals(WHILE) || 
                preanalisis.equals(LLAV_ABRE))
        {
            STATEMENT();
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

        if(preanalisis.equals(VAR))
        {
            coincidir(VAR);
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

    //SENTENCIAS

    void STATEMENT()
    {
        if(hayErrores) return;

        if(preanalisis.equals(NOT) || preanalisis.equals(RESTA) || preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || 
        preanalisis.equals(NULL) || preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || 
        preanalisis.equals(IDENTIFICADOR) || preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(FOR))
        {
            FOR_STMT();
        } 
        else if(preanalisis.equals(IF))
        {
            IF_STMT();
        }
        else if(preanalisis.equals(PRINT))
        {
            PRINT_STMT();
        }
        else if(preanalisis.equals(RETURN))
        {
            RETURN_STMT();
        }
        else if(preanalisis.equals(WHILE))
        {
            WHILE_STMT();
        }
        else if(preanalisis.equals(LLAV_ABRE))
        {
            BLOCK();
        }
        else
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  Token tipo EXPRESSION");
        }
    }

    void EXPR_STMT()
    {
        if(hayErrores) return;

        if(preanalisis.equals(NOT) || preanalisis.equals(RESTA) || preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || 
           preanalisis.equals(NULL) || preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || 
           preanalisis.equals(IDENTIFICADOR) || preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un Token tipo EXPRESSION ");
        }
    }

    void FOR_STMT()
    {
        if(hayErrores) return;

        if(preanalisis.equals(FOR))
        {
            coincidir(FOR);
            coincidir(PAR_ABRE);
            FOR_STMT_1();
            FOR_STMT_2();
            FOR_STMT_3();
            coincidir(PAR_CIERRE);
            STATEMENT();
        }
        else
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  " + FOR.type);
        }
    }

    void FOR_STMT_1()
        {
        if(hayErrores) return;

        if(preanalisis.equals(VAR))
        {
            VAR_DECL();
        }
        else if(preanalisis.equals(NOT) || preanalisis.equals(RESTA) || preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || 
           preanalisis.equals(NULL) || preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || 
           preanalisis.equals(IDENTIFICADOR) || preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(PUNTO_COMA))
        {
            coincidir(PUNTO_COMA);
        }
        else
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  " + PAR_ABRE.type);
        }           
    }
    
    void FOR_STMT_2()
    {
        if(hayErrores) return;

        if(preanalisis.equals(NOT) || preanalisis.equals(RESTA) || preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || 
           preanalisis.equals(NULL) || preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || 
           preanalisis.equals(IDENTIFICADOR) || preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(PUNTO_COMA))
        {
            coincidir(PUNTO_COMA);
        }     
        else
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  " + NOT.type);
        }   
    }

    void FOR_STMT_3()
    {
        if(hayErrores) return;

        if(preanalisis.equals(NOT) || preanalisis.equals(RESTA) || preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || 
           preanalisis.equals(NULL) || preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || 
           preanalisis.equals(IDENTIFICADOR) || preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER))
        {
            EXPRESSION();
        }
    }
    
    void IF_STMT()
    {
        if(hayErrores) return;

        if(preanalisis.equals(IF))
        {
            coincidir(IF);
            coincidir(PAR_ABRE);
            EXPRESSION();
            coincidir(PAR_CIERRE);
            STATEMENT();
            ELSE_STATEMENT();
        }
        else 
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  " + IF.type);
        }
    }

    void ELSE_STATEMENT()
    {
        if(hayErrores) return;

        if(preanalisis.equals(ELSE))
        {
            coincidir(ELSE);
            STATEMENT();
        }
    }

    void PRINT_STMT()
    {
        if(hayErrores) return;

        if(preanalisis.equals(PRINT))
        {
            coincidir(PRINT);
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else 
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  " + PRINT.type);
        }
    }

    void RETURN_STMT()
    {
        if(hayErrores) return;

        if(preanalisis.equals(RETURN))
        {
            coincidir(RETURN);
            RETURN_EXP_OPC();
            coincidir(PUNTO_COMA);
        }
        else 
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  " + RETURN.type);
        }
    }

    void RETURN_EXP_OPC()
    {
        if(hayErrores) return;

        if(preanalisis.equals(NOT) || preanalisis.equals(RESTA) || preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || 
           preanalisis.equals(NULL) || preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || 
           preanalisis.equals(IDENTIFICADOR) || preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER))
        {
            EXPRESSION();
        }
    }

    void WHILE_STMT()
    {
        if(hayErrores) return;

        if(preanalisis.equals(WHILE))
        {
            coincidir(WHILE);
            coincidir(PAR_ABRE);
            EXPRESSION();
            coincidir(PAR_CIERRE);
            STATEMENT();
        }
        else 
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  " + WHILE.type);
        }
    }

    void BLOCK()
    {
        if(hayErrores) return;

        if(preanalisis.equals(LLAV_ABRE))
        {
            coincidir(LLAV_ABRE);
            BLOCK_DECL();
            coincidir(LLAV_CIERRE);
        }
        else 
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  " + LLAV_ABRE.type);
        }
    }

    void BLOCK_DECL()
    {
        if(hayErrores) return;

        if(preanalisis.equals(CLASS) || preanalisis.equals(FUN) || preanalisis.equals(VAR) || preanalisis.equals(NOT) || 
           preanalisis.equals(RESTA) || preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || preanalisis.equals(NULL) || 
           preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || preanalisis.equals(IDENTIFICADOR) || 
           preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER) || preanalisis.equals(FOR) || preanalisis.equals(IF) || 
           preanalisis.equals(PRINT) || preanalisis.equals(RETURN) || preanalisis.equals(WHILE) || preanalisis.equals(LLAV_ABRE))
        {
            DECLARATION();
            BLOCK_DECL();
        }
    }

    //EXPRESIONES

    void EXPRESSION()
    {
        if(hayErrores) return;

        if(preanalisis.equals(NOT) || preanalisis.equals(RESTA) || preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || 
           preanalisis.equals(NULL) || preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || 
           preanalisis.equals(IDENTIFICADOR) || preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER))
        {
            ASSIGNMENT();
        }
        else 
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  ");
        }
    }

    void ASSIGNMENT()
    {
        if(hayErrores) return;

        if(preanalisis.equals(NOT) || preanalisis.equals(RESTA) || preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || 
           preanalisis.equals(NULL) || preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || 
           preanalisis.equals(IDENTIFICADOR) || preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER))
        {
            LOGIC_OR();
            ASSIGNMENT_OPC();
        }
        else 
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  ");
        }
    }

    void ASSIGNMENT_OPC()
    {
        if(hayErrores) return;

        if(preanalisis.equals(ASIG))
        {
            coincidir(ASIG);
            EXPRESSION();
        }
    }

    void LOGIC_OR()
    {
        if(hayErrores) return;

        if(preanalisis.equals(NOT) || preanalisis.equals(RESTA) || preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || 
           preanalisis.equals(NULL) || preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || 
           preanalisis.equals(IDENTIFICADOR) || preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER))
        {
            LOGIC_AND();
            ASSIGNMENT_OPC();
        }
        else 
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  ");
        }
    }

    void LOGIC_OR_2()
    {
        if(hayErrores) return;

        if(preanalisis.equals(OR))
        {
            coincidir(OR);
            LOGIC_AND();
            LOGIC_OR_2();
        }
    }

    void LOGIC_AND()
    {
        if(hayErrores) return;

        if(preanalisis.equals(NOT) || preanalisis.equals(RESTA) || preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || 
           preanalisis.equals(NULL) || preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || 
           preanalisis.equals(IDENTIFICADOR) || preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER))
        {
            EQUALITY();
            LOGIC_AND_2();
        }
        else
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  ");
        }     
    }

    void LOGIC_AND_2()
    {
        if(hayErrores) return;
        
        if(preanalisis.equals(AND))
        {
            coincidir(AND);
            LOGIC_AND_2();
        }
    }

    void EQUALITY()
    {
        if(hayErrores) return;

        if(preanalisis.equals(NOT) || preanalisis.equals(RESTA) || preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || 
           preanalisis.equals(NULL) || preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || 
           preanalisis.equals(IDENTIFICADOR) || preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER))
           {
            COMPARISON();
            EQUALITY_2();
           }
    }

    void EQUALITY_2()
    {
        if(hayErrores) return;

        if(preanalisis.equals(DIFERENTE))
        {
            coincidir(DIFERENTE);
            COMPARISON();
            EQUALITY_2();
        }
        else if(preanalisis.equals(IGUAL))
        {
            coincidir(IGUAL);
            COMPARISON();
            EQUALITY_2();
        }
    }

    void COMPARISON()
    {
        if(hayErrores) return;

        if(preanalisis.equals(NOT) || preanalisis.equals(RESTA) || preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || 
           preanalisis.equals(NULL) || preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || 
           preanalisis.equals(IDENTIFICADOR) || preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER))
        {
            TERM();
            COMPARISON_2();
        }
        else 
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  ");
        }    
    }

    void COMPARISON_2()
    {
        if(hayErrores) return;

        if(preanalisis.equals(MAYOR))
        {
            coincidir(MAYOR);
            TERM();
            COMPARISON_2();
        }
        else if(preanalisis.equals(MAYOR_IGUAL))
        {
            coincidir(MAYOR_IGUAL);
            TERM();
            COMPARISON_2();
        }
        else if(preanalisis.equals(MENOR))
        {
            coincidir(MENOR);
            TERM();
            COMPARISON_2();
        }
        else if(preanalisis.equals(MENOR_IGUAL))
        {
            coincidir(MENOR_IGUAL);
            TERM();
            COMPARISON_2();
        }
    }

    void TERM()
    {
        if(hayErrores) return;

        if(preanalisis.equals(NOT) || preanalisis.equals(RESTA) || preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || 
           preanalisis.equals(NULL) || preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || 
           preanalisis.equals(IDENTIFICADOR) || preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER))
        {
            FACTOR();
            TERM_2();
        }
        else 
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  ");
        }
    }

    void TERM_2()
    {
        if(hayErrores) return;

        if(preanalisis.equals(RESTA))
        {
            coincidir(RESTA);
            FACTOR();
            TERM_2();
        }
        else if(preanalisis.equals(SUMA))
        {
            coincidir(SUMA);
            FACTOR();
            TERM_2();
        }
    }

    void FACTOR()
    {
        if(hayErrores) return;

        if(preanalisis.equals(NOT) || preanalisis.equals(RESTA) || preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || 
           preanalisis.equals(NULL) || preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || 
           preanalisis.equals(IDENTIFICADOR) || preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER))
        {
            UNARY();
            FACTOR_2();
        }
        else 
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  ");
        }
    }

    void FACTOR_2()
    {
        if(hayErrores) return;

        if(preanalisis.equals(DIV))
        {
            coincidir(DIV);
            UNARY();
            FACTOR_2();
        }
        else if(preanalisis.equals(MULTI))
        {
            coincidir(MULTI);
            UNARY();
            FACTOR_2();
        }
    }

    void UNARY()
    {
        if(hayErrores) return;

        if(preanalisis.equals(NOT))
        {
            coincidir(NOT);
            UNARY();
        }
        else if(preanalisis.equals(RESTA))
        {
            coincidir(RESTA);
            UNARY();
        }
        else if(preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || 
                preanalisis.equals(NULL) || preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || 
                preanalisis.equals(IDENTIFICADOR) || preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER))
        {
            CALL();
        }
        else 
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  ");
        }
    }

    void CALL()
    {
        if(hayErrores) return;


        if(preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || 
           preanalisis.equals(NULL) || preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || 
           preanalisis.equals(IDENTIFICADOR) || preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER))
        {
            PRIMARY();
            CALL_2();
        }
        else 
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  ");
        }
    }

    void CALL_2()
    {
        if(hayErrores) return;

        if(preanalisis.equals(PAR_ABRE))
        {
            coincidir(PAR_ABRE);
            ARGUMENT_OPC();
            coincidir(PAR_CIERRE);
            CALL_2();
        }
        else if(preanalisis.equals(PUNTO))
        {
            coincidir(PUNTO);
            coincidir(IDENTIFICADOR);
            CALL_2();
        }
    }

    void CALL_OPC()
    {
        if(preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || 
           preanalisis.equals(NULL) || preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || 
           preanalisis.equals(IDENTIFICADOR) || preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER))
        {
            CALL();
            coincidir(PUNTO);
        }
    }

    void PRIMARY()
    {
        if(preanalisis.equals(TRUE))
        {
            coincidir(TRUE);
        }
        else if(preanalisis.equals(FALSE))
        {
            coincidir(FALSE);
        }
        else if(preanalisis.equals(NULL))
        {
            coincidir(NULL);
        }
        else if(preanalisis.equals(THIS))
        {
            coincidir(THIS);
        }
        else if(preanalisis.equals(NUMERO))
        {
            coincidir(NUMERO);
        }
        else if(preanalisis.equals(STRING))
        {
            coincidir(STRING);
        }
        else if(preanalisis.equals(IDENTIFICADOR))
        {
            coincidir(IDENTIFICADOR);
        }
        else if(preanalisis.equals(PAR_ABRE))
        {
            coincidir(PAR_ABRE);
            EXPRESSION();
            coincidir(PAR_CIERRE);
        }
        else if(preanalisis.equals(SUPER))
        {
            coincidir(SUPER);
            coincidir(PUNTO);
            coincidir(IDENTIFICADOR);
        }
        else 
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  ");
        }
    }

    void FUNCTION()
    {
        if(hayErrores) return;

        if(preanalisis.equals(IDENTIFICADOR))
        {
            coincidir(IDENTIFICADOR);
            coincidir(PAR_ABRE);
            PARAMETERS_OPC();
            coincidir(PAR_CIERRE);
            BLOCK();
        }
        else 
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  ");
        }
    }

    void FUNCTIONS()
    {
        if(hayErrores) return;

        if(preanalisis.equals(IDENTIFICADOR))
        {
            FUNCTION();
            FUNCTIONS();
        }
    }

    void PARAMETERS_OPC()
    {
        if(hayErrores) return;

        if(preanalisis.equals(IDENTIFICADOR))
        {
            PARAMETERS();
        }
    }

    void PARAMETERS()
    {
        if(hayErrores) return;

        if(preanalisis.equals(IDENTIFICADOR))
        {
            coincidir(IDENTIFICADOR);
            PARAMETERS_2();
        }
        else
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  ");
        }
    }

    void PARAMETERS_2()
    {
        if(hayErrores) return;

        if(preanalisis.equals(COMA))
        {
            coincidir(COMA);
            coincidir(IDENTIFICADOR);
            PARAMETERS_2();
        }
    }

    void ARGUMENT_OPC()
    {
        if(hayErrores) return;

        if(preanalisis.equals(NOT) || preanalisis.equals(RESTA) || preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || 
           preanalisis.equals(NULL) || preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || 
           preanalisis.equals(IDENTIFICADOR) || preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER))
        {
            ARGUMENTS();
        }
    }

    void ARGUMENTS()
    {
        if(hayErrores) return;

        if(preanalisis.equals(NOT) || preanalisis.equals(RESTA) || preanalisis.equals(TRUE) || preanalisis.equals(FALSE) || 
           preanalisis.equals(NULL) || preanalisis.equals(THIS) || preanalisis.equals(NUMERO) || preanalisis.equals(STRING) || 
           preanalisis.equals(IDENTIFICADOR) || preanalisis.equals(PAR_ABRE) || preanalisis.equals(SUPER))
        {
            EXPRESSION();
            ARGUMENTS_2();
        }
        else 
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  ");
        }
    }

    void ARGUMENTS_2()
    {
        if(hayErrores) return;

        if(preanalisis.equals(COMA))
        {
            coincidir(COMA);
            EXPRESSION();
            ARGUMENTS_2();
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

