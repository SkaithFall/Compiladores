package Compilador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    static boolean existenErrores = false;

    public static void main(String[] args) throws IOException {
        ejecutarPrompt();
    }
    

    private static void ejecutarPrompt() throws IOException
    {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        StringBuilder entrada = new StringBuilder();
        boolean salir = false;
        
        while (!salir) 
        {
            System.out.print(">>> ");
            String linea = reader.readLine();

            if (linea == null) 
            {
                if (entrada.length() > 0) 
                {
                    ejecutar(entrada.toString()); // Ejecutar la entrada concatenada
                    entrada.setLength(0); // Reiniciar la entrada para la siguiente iteración
                }
                break;
            }
            
            if (linea.equalsIgnoreCase("archivo")) 
            {
                System.out.print("Ingrese el nombre del archivo: ");
                String nombreArchivo = reader.readLine();
                ejecutarArchivo(nombreArchivo);
            } else 
            {
                entrada.append(linea).append(" "); // Agregar la línea actual a la entrada con un espacio
            }
            existenErrores = false;
        }
    }
    

    private static void ejecutarArchivo(String nombreArchivo) throws IOException {
        FileReader archivo = new FileReader(nombreArchivo);
        BufferedReader bufferedReader = new BufferedReader(archivo);

        String linea;
        while ((linea = bufferedReader.readLine()) != null) {
            ejecutar(linea);
        }

        archivo.close();
    }

    private static void ejecutar(String source)
    {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        /*for(Token token : tokens){
            System.out.println(token);
        }*/

        Parser parser = new Parser(tokens);
        parser.parse();
    }
    static void error(int linea, String mensaje)
    {
        reportar(linea, "", mensaje);
    }

    private static void reportar(int linea, String donde, String mensaje)
    {
        System.err.println(
                "[linea " + linea + "] Error " + donde + ": " + mensaje
        );
        existenErrores = true;
    }

}