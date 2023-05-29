package Parser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Analizer 
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
    
    private static void ejecutarArchivo(String path)
    {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            ejecutar(new String(bytes, Charset.defaultCharset()));
        } catch (IOException e) {
            System.err.println("Error: no se pudo leer el archivo " + path);
        }
    }
    

    private static void ejecutarPrompt() 
    {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
    
        for (;;) {
            try {
                System.out.println(">>>");
                String linea = reader.readLine();
                if (linea == null) break;
                ejecutar(linea);
            } catch (IOException e) {
                System.err.println("Error: no se pudo leer la entrada del usuario");
            }
        }
    }
    

    private static void ejecutar(String source)
    {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanList();

        for(Token token : tokens)
        {
            System.out.println(token);
        }
    }
}