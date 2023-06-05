package Compilador;

public class Token 
{
    final Tokentype type;
    final String lexeme;
    final int posicion;

    public Token(Tokentype type, String lexeme, int posicion)
    {
        this.type = type;
        this.lexeme = lexeme;
        this.posicion = posicion;
    }

    public Token(Tokentype type, String lexeme)
    {
        this.type = type;
        this.lexeme = lexeme;
        this.posicion = 0;
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
        return type + " :" + lexeme + "" + posicion + " ";
    }
}
