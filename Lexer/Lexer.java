package Lexer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lexer 
{
    public static void main(String[] args) throws IOException 
    {
        if (args.length == 1)
        {
            ejecutarArchivo(args[0]);
        } else 
        {
            ejecutarPrompt();
        }
    }
    
    private static void ejecutarArchivo(String path) throws IOException
    {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        ejecutar(new String(bytes, Charset.defaultCharset()));
    }

    private static void ejecutarPrompt() throws IOException
    {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for(;;)
        {
            System.out.println(">>>");
            String linea = reader.readLine();
            if (linea == null) break;
            ejecutar(linea);
        }
    }

    private static void ejecutar(String source)
    {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scan();

        for(Token token : tokens)
        {
            System.out.println(token);
        }
    }
}