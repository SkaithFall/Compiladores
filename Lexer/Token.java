package Lexer;

public class Token 
{
    private Tokentype type;
    private String lexeme;
    private int line;

    public Token(Tokentype type, String lexeme)
    {
        this.type = type;
        this.lexeme = lexeme;
    }

    public Tokentype getType() 
    {
        return type;
    }

    public String getLexeme() 
    {
        return lexeme;
    }

    public int getLine() 
    {
        return line;
    }

    @Override
    public String toString() 
    {
        return type + " " + lexeme + " at line " + line;
    }
}
