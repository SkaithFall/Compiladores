package Compilador;

public class Token 
{
    final Tokentype type;
    final String lexeme;
    final int line;

    public Token(Tokentype type, String lexeme, int line)
    {
        this.type = type;
        this.lexeme = lexeme;
        this.line = line;
    }

    public Token(Tokentype type, String lexeme)
    {
        this.type = type;
        this.lexeme = lexeme;
        this.line = 0;
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
        return type + " :" + lexeme + " at line " + line;
    }
}
