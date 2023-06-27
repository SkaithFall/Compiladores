package Compilador;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostFija 
{
    private final List<Token> infija;
    private final Stack<Token> pila;
    private final List<Token> postfija;
    
    public PostFija(List<Token> infija)
    {
        this.infija = infija;
        this.pila =  new Stack<>();
        this.postfija = new ArrayList<>();     
    }

    public List<Token> convert()
    {
        boolean controlstructed = false;
        Stack<Token> controlstructedpila = new Stack<>();
        for(int i = 0; i < infija.size(); i++)
        {
            Token t = infija.get(i);

            if(t.type == Tokentype.END)
            {
                break;
            }

            if(t.isKeyword())
            {
                postfija.add(t);
                if(t.isControlStructure())
                {
                    controlstructed = true;
                    controlstructedpila.push(t);
                }
            }
            if(t.isOperando())
            {
                postfija.add(t);
            }
            else if(t.type == Tokentype.PAR_ABRE)
            {
                pila.push(t);
            }
            else if(t.type == Tokentype.PAR_CIERRE)
            {
                while(!pila.isEmpty() && pila.peek().getType() != Tokentype.PAR_ABRE)
                {
                    Token temp = pila.pop();
                    postfija.add(temp);
                }
                if(pila.peek().type == Tokentype.PAR_ABRE)
                {
                    pila.pop();
                }
                if(controlstructed && infija.get(i + 1).type == Tokentype.PAR_ABRE)
                {
                    postfija.add(new Token(Tokentype.PUNTO_COMA, ";", null));
                }
            }
            else if(t.isOperador())
            {
                while(!pila.isEmpty() && pila.peek().precedenciaMayorIgual(t))
                {
                    Token temp = pila.pop();
                    postfija.add(temp);
                }
                    pila.push(t);
            }
            else if(t.type == Tokentype.PUNTO_COMA)
            {
                while(!pila.isEmpty() && pila.peek().type != Tokentype.LLAV_ABRE)
                {
                    Token temp = pila.pop();
                    postfija.add(temp);
                }
                postfija.add(t);
            }
            else if(t.type == Tokentype.LLAV_ABRE)
            {
                pila.push(t);
            }
            else if(t.type == Tokentype.LLAV_CIERRE && controlstructed)
            {
                if(infija.get(i + 1).type == Tokentype.ELSE)
                {
                    pila.pop();
                }
                else 
                {
                    pila.pop();
                    postfija.add(new Token(Tokentype.PUNTO_COMA, ";", null));
                    Token aux = controlstructedpila.pop();
                    if(aux.type == Tokentype.ELSE)
                    {
                        controlstructedpila.pop();
                        postfija.add(new Token(Tokentype.PUNTO_COMA, ";", null));
                    }
                    if(controlstructedpila.isEmpty())
                    {
                        controlstructed = false;
                    }
                } 
            }
        }
        while(!pila.isEmpty())
        {
            Token temp = pila.pop();
            postfija.add(temp);
        }
        while(!controlstructedpila.isEmpty())
        {
            controlstructedpila.pop();
            postfija.add(new Token(Tokentype.PUNTO_COMA, ";", null));
        }
        return postfija;
    }
}

    