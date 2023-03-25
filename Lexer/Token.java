package Lexer;

public class Token 
{
    private Tokentype type;
    private String lexeme;
    private int line;

    public Token(Tokentype type, String lexeme, int line)
    {
        this.type = type;
        this.lexeme = lexeme;
        this.line = line;
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
