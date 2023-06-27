package Compilador;

public class Token 
{
    final Tokentype type;
    final String lexeme;
    final Object literal;

    public Token(Tokentype type, String lexeme)
    {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = null;
    }

    public Token(Tokentype type, String lexeme, Object literal)
    {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
    }

    public Tokentype getType()
    {
        return type;
    }

    public String getLexeme()
    {
        return lexeme;
    }
 

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Token)) {
            return false;
        }

        if(this.type == ((Token)o).type){
            return true;
        }

        return false;
    }

    
    public String toString() 
    {
        return type + ": " + lexeme + " | " + (literal == null ? " " : literal.toString());
    }


    // Metodos Auxiliares

    public boolean isOperando()
    {
        switch(this.type)
        {
            case ID:
            case NUMBER:
                return true;
            default:
                return false;
        }
    }

    public boolean isOperador()
    {
        switch(this.type)
        {
            case SUMA:
            case RESTA:
            case MULTI:
            case DIV:
            case ASIG:
            case IGUAL:
            case MAYOR:
            case MENOR:
            case MAYOR_IGUAL:
            case MENOR_IGUAL:
                return true;
            default:
                return false;
        }
    }

    public boolean isKeyword()
    {
        switch(this.type)
        {
            case SUPER:
            case TRUE:
            case FALSE:
            case NULL:
            case THIS:
            case AND:
            case OR:
            case WHILE:
            case IF:
            case ELSE:
            case FOR:
            case RETURN:
            case PRINT:
            case FUN:
            case CLASS:
            case VAR:
                return true;
            default:
                return false;
        }
    }

    public boolean isControlStructure()
    {
         switch(this.type)
        {
            case IF:
            case ELSE:
            case WHILE:
            case FOR:
                return true;
            default:
                return false;
        }
    }

    public boolean precedenciaMayorIgual(Token t)
    {
        return this.GetPrece() >= t.GetPrece();
    }

    public int GetPrece()
    {
        switch(this.type)
        {
            case MULTI:
            case DIV:
                return 3;
            case SUMA:
            case RESTA:
                return 2;
            case IGUAL:
            case ASIG:
            case MAYOR:
            case MENOR:
            case MAYOR_IGUAL:
            case MENOR_IGUAL:
                return 1;
            default:
                break;
        }
        return 0;
    }

    public int aridad()
    {
        switch (this.type)
        {
            case MULTI:
            case DIV:
            case SUMA:
            case RESTA:
            case IGUAL:
            case MAYOR:
            case MENOR:
            case MAYOR_IGUAL:
            case MENOR_IGUAL:
                return 2;
            default:
                break;
        }
        return 0;
    }


}
