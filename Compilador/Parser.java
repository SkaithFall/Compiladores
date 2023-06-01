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
        if(preanalisis.equals(CLASS))
        {
            DECLARATION();
        }
        else if(preanalisis.equals(FUN))
        {
            DECLARATION();
        }
        else if(preanalisis.equals(IDENTIFICADOR)) 
        {
            DECLARATION();
        }
        if(preanalisis.equals(NOT))
        {
            DECLARATION();
        }
        else if(preanalisis.equals(RESTA))
        {
            DECLARATION();
        }
        else if(preanalisis.equals(TRUE))
        {
            DECLARATION();
        }
        else if(preanalisis.equals(FALSE))
        {
            DECLARATION();
        }
        else if(preanalisis.equals(NULL))
        {
            DECLARATION();
        }
        else if(preanalisis.equals(THIS))
        {
            DECLARATION();
        }
        else if(preanalisis.equals(NUMERO))
        {
            DECLARATION();
        }
        else if(preanalisis.equals(STRING))
        {
            DECLARATION();
        }
        else if(preanalisis.equals(IDENTIFICADOR))
        {
            DECLARATION();
        }
        else if(preanalisis.equals(PAR_ABRE))
        {
            DECLARATION();
        }
        else if(preanalisis.equals(SUPER))
        {
            DECLARATION();
        }
        else if(preanalisis.equals(FOR))
        {
            DECLARATION();
        } 
        else if(preanalisis.equals(IF))
        {
            DECLARATION();
        }
        else if(preanalisis.equals(PRINT))
        {
            DECLARATION();
        }
        else if(preanalisis.equals(RETURN))
        {
            DECLARATION();
        }
        else if(preanalisis.equals(WHILE))
        {
            DECLARATION();
        }
        else if(preanalisis.equals(LLAV_ABRE))
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
        else if(preanalisis.equals(IDENTIFICADOR)) 
        {
            VAR_DECL();
            DECLARATION();
        }
        if(preanalisis.equals(NOT))
        {
            CLASS_DECL();
            DECLARATION();
        }
        else if(preanalisis.equals(RESTA))
        {
            CLASS_DECL();
            DECLARATION();
        }
        else if(preanalisis.equals(TRUE))
        {
            CLASS_DECL();
            DECLARATION();
        }
        else if(preanalisis.equals(FALSE))
        {
            CLASS_DECL();
            DECLARATION();
        }
        else if(preanalisis.equals(NULL))
        {
            CLASS_DECL();
            DECLARATION();
        }
        else if(preanalisis.equals(THIS))
        {
            CLASS_DECL();
            DECLARATION();
        }
        else if(preanalisis.equals(NUMERO))
        {
            CLASS_DECL();
            DECLARATION();
        }
        else if(preanalisis.equals(STRING))
        {
            CLASS_DECL();
            DECLARATION();
        }
        else if(preanalisis.equals(IDENTIFICADOR))
        {
            CLASS_DECL();
            DECLARATION();
        }
        else if(preanalisis.equals(PAR_ABRE))
        {
            CLASS_DECL();
            DECLARATION();
        }
        else if(preanalisis.equals(SUPER))
        {
            CLASS_DECL();
            DECLARATION();
        }
        else if(preanalisis.equals(FOR))
        {
            STATEMENT();
            DECLARATION();
        } 
        else if(preanalisis.equals(IF))
        {
            STATEMENT();
            DECLARATION();
        }
        else if(preanalisis.equals(PRINT))
        {
            STATEMENT();
            DECLARATION();
        }
        else if(preanalisis.equals(RETURN))
        {
            STATEMENT();
            DECLARATION();
        }
        else if(preanalisis.equals(WHILE))
        {
            STATEMENT();
            DECLARATION();
        }
        else if(preanalisis.equals(LLAV_ABRE))
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

    //SENTENCIAS

    void STATEMENT()
    {
        if(hayErrores) return;

        if(preanalisis.equals(NOT))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(RESTA))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(TRUE))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(FALSE))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(NULL))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(THIS))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(NUMERO))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(STRING))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(IDENTIFICADOR))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(PAR_ABRE))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(SUPER))
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
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  " + NOT.type);
        }
    }

    void EXPR_STMT()
    {
        if(hayErrores) return;

        if(preanalisis.equals(NOT))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(RESTA))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(TRUE))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(FALSE))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(NULL))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(THIS))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(NUMERO))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(STRING))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(IDENTIFICADOR))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(PAR_ABRE))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(SUPER))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  " + EXPRESSION.type);
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

        if(preanalisis.equals(IDENTIFICADOR))
        {
            VAR_DECL();
        }
        if(preanalisis.equals(NOT))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(RESTA))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(TRUE))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(FALSE))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(NULL))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(THIS))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(NUMERO))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(STRING))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(IDENTIFICADOR))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(PAR_ABRE))
        {
            EXPR_STMT();
        }
        else if(preanalisis.equals(SUPER))
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

        if(preanalisis.equals(NOT))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(RESTA))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(TRUE))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(FALSE))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(NULL))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(THIS))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(NUMERO))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(STRING))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(IDENTIFICADOR))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(PAR_ABRE))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(SUPER))
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

        if(preanalisis.equals(NOT))
        {
            EXPRESSION();
        }
        else if(preanalisis.equals(RESTA))
        {
            EXPRESSION();
        }
        else if(preanalisis.equals(TRUE))
        {
            EXPRESSION();
        }
        else if(preanalisis.equals(FALSE))
        {
            EXPRESSION();
        }
        else if(preanalisis.equals(NULL))
        {
            EXPRESSION();
        }
        else if(preanalisis.equals(THIS))
        {
            EXPRESSION();
        }
        else if(preanalisis.equals(NUMERO))
        {
            EXPRESSION();
        }
        else if(preanalisis.equals(STRING))
        {
            EXPRESSION();
        }
        else if(preanalisis.equals(IDENTIFICADOR))
        {
            EXPRESSION();
        }
        else if(preanalisis.equals(PAR_ABRE))
        {
            EXPRESSION();
        }
        else if(preanalisis.equals(SUPER))
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

        if(preanalisis.equals(NOT))
        {
            EXPRESSION();
        }
        else if(preanalisis.equals(RESTA))
        {
            EXPRESSION();
        }
        else if(preanalisis.equals(TRUE))
        {
            EXPRESSION();
        }
        else if(preanalisis.equals(FALSE))
        {
            EXPRESSION();
        }
        else if(preanalisis.equals(NULL))
        {
            EXPRESSION();
        }
        else if(preanalisis.equals(THIS))
        {
            EXPRESSION();
        }
        else if(preanalisis.equals(NUMERO))
        {
            EXPRESSION();
        }
        else if(preanalisis.equals(STRING))
        {
            EXPRESSION();
        }
        else if(preanalisis.equals(IDENTIFICADOR))
        {
            EXPRESSION();
        }
        else if(preanalisis.equals(PAR_ABRE))
        {
            EXPRESSION();
        }
        else if(preanalisis.equals(SUPER))
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
            coincidir(PAR_CIERRE);
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

        if(preanalisis.equals(CLASS))
        {
            DECLARATION();
            BLOCK_DECL();
        }
        else if(preanalisis.equals(FUN))
        {
            DECLARATION();
            BLOCK_DECL();
        }
        else if(preanalisis.equals(IDENTIFICADOR)) 
        {
            DECLARATION();
            BLOCK_DECL();
        }
        if(preanalisis.equals(NOT))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(RESTA))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(TRUE))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(FALSE))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(NULL))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(THIS))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(NUMERO))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(STRING))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(IDENTIFICADOR))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(PAR_ABRE))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(SUPER))
        {
            EXPRESSION();
            coincidir(PUNTO_COMA);
        }
        else if(preanalisis.equals(FOR))
        {
            DECLARATION();
            BLOCK_DECL();
        } 
        else if(preanalisis.equals(IF))
        {
            DECLARATION();
            BLOCK_DECL();
        }
        else if(preanalisis.equals(PRINT))
        {
            DECLARATION();
            BLOCK_DECL();
        }
        else if(preanalisis.equals(RETURN))
        {
            DECLARATION();
            BLOCK_DECL();
        }
        else if(preanalisis.equals(WHILE))
        {
            DECLARATION();
            BLOCK_DECL();
        }
        else if(preanalisis.equals(LLAV_ABRE))
        {
            DECLARATION();
            BLOCK_DECL();
        }
    }

    //EXPRESIONES

    void EXPRESSION()
    {
        if(hayErrores) return;

        if(preanalisis.equals(NOT))
        {
            ASSIGNMENT();
        }
        else if(preanalisis.equals(RESTA))
        {
            ASSIGNMENT();
        }
        else if(preanalisis.equals(TRUE))
        {
            ASSIGNMENT();
        }
        else if(preanalisis.equals(FALSE))
        {
            ASSIGNMENT();
        }
        else if(preanalisis.equals(NULL))
        {
            ASSIGNMENT();
        }
        else if(preanalisis.equals(THIS))
        {
            ASSIGNMENT();
        }
        else if(preanalisis.equals(NUMERO))
        {
            ASSIGNMENT();
        }
        else if(preanalisis.equals(STRING))
        {
            ASSIGNMENT();
        }
        else if(preanalisis.equals(IDENTIFICADOR))
        {
            ASSIGNMENT();
        }
        else if(preanalisis.equals(PAR_ABRE))
        {
            ASSIGNMENT();
        }
        else if(preanalisis.equals(SUPER))
        {
            ASSIGNMENT();
        }
        else 
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  " + EXPRESSION.type);
        }
    }

    void ASSIGNMENT()
    {
        if(hayErrores) return;

        if(preanalisis.equals(NOT))
        {
            LOGIC_OR();
            ASSIGNMENT_OPC();
        }
        else if(preanalisis.equals(RESTA))
        {
            LOGIC_OR();
            ASSIGNMENT_OPC();
        }
        else if(preanalisis.equals(TRUE))
        {
            LOGIC_OR();
            ASSIGNMENT_OPC();
        }
        else if(preanalisis.equals(FALSE))
        {
            LOGIC_OR();
            ASSIGNMENT_OPC();
        }
        else if(preanalisis.equals(NULL))
        {
            LOGIC_OR();
            ASSIGNMENT_OPC();
        }
        else if(preanalisis.equals(THIS))
        {
            LOGIC_OR();
            ASSIGNMENT_OPC();
        }
        else if(preanalisis.equals(NUMERO))
        {
            LOGIC_OR();
            ASSIGNMENT_OPC();
        }
        else if(preanalisis.equals(STRING))
        {
            LOGIC_OR();
            ASSIGNMENT_OPC();
        }
        else if(preanalisis.equals(IDENTIFICADOR))
        {
            LOGIC_OR();
            ASSIGNMENT_OPC();
        }
        else if(preanalisis.equals(PAR_ABRE))
        {
            LOGIC_OR();
            ASSIGNMENT_OPC();
        }
        else if(preanalisis.equals(SUPER))
        {
            LOGIC_OR();
            ASSIGNMENT_OPC();
        }
        else 
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  " + EXPRESSION.type);
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

        if(preanalisis.equals(NOT))
        {
            LOGIC_AND();
            ASSIGNMENT_OPC();
        }
        else if(preanalisis.equals(RESTA))
        {
            LOGIC_AND();
            ASSIGNMENT_OPC();
        }
        else if(preanalisis.equals(TRUE))
        {
            LOGIC_AND();
            ASSIGNMENT_OPC();
        }
        else if(preanalisis.equals(FALSE))
        {
            LOGIC_AND();
            ASSIGNMENT_OPC();
        }
        else if(preanalisis.equals(NULL))
        {
            LOGIC_AND();
            ASSIGNMENT_OPC();
        }
        else if(preanalisis.equals(THIS))
        {
            LOGIC_AND();
            ASSIGNMENT_OPC();
        }
        else if(preanalisis.equals(NUMERO))
        {
            LOGIC_AND();
            ASSIGNMENT_OPC();
        }
        else if(preanalisis.equals(STRING))
        {
            LOGIC_AND();
            ASSIGNMENT_OPC();
        }
        else if(preanalisis.equals(IDENTIFICADOR))
        {
            LOGIC_AND();
            ASSIGNMENT_OPC();
        }
        else if(preanalisis.equals(PAR_ABRE))
        {
            LOGIC_AND();
            ASSIGNMENT_OPC();
        }
        else if(preanalisis.equals(SUPER))
        {
            LOGIC_AND();
            ASSIGNMENT_OPC();
        }
        else 
        {
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  " + EXPRESSION.type);
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
