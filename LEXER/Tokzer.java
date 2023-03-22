package LEXER;

import java.security.PublicKey;

public class Tokzer 
{
    private String Value;
    private Tokens Token;

    public Tokzer (Tokens Token, String Value)
    {
        this.Token = Token;
        this.Value = Value;
    }

    public Tokens getTokens()
    {
        return Token;
    }

    public String getValue()
    {
        return Value;
    }

    public String toString()
    {
        return "Tokzer( value = "+Value+", token = "+Token+")";
    }
}
