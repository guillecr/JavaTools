package libreria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase para lectura por teclado en consola
 * 
 * @author Guillermo Casas
 * @author g.casas.r94@gmail.com
 * @version 2.241120
 */
public abstract class Entrada {
    
    // Objeto de lectura
    protected static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    
    // =================== METODOS =====================================
    
    /**
     * Método para lectura de una cadena de caracteres.
     * @return Devuelve el String introducido por teclado
     */
    public static String lecturaString(){
        try {
            String lec = br.readLine();
            
            if(lec.length()<1){
                //System.out.print("[ERROR]: Dato no valido, introduce dato valido (String): ");
                //lec = lecturaString();
            }
            
            return lec;
            
        } catch (IOException ex) {
            //System.err.println("[ERROR] Dato no valido, introduzca de nuevo");
            //return lecturaString();
            return "";
        }
    }
    
    /**
     * Método para lectura de un numero entero introducido por teclado
     * Si se introduce un valor no válido, devolverá un error y pedirá de nuevo
     * que se introduzca un valor válido
     * @return Valor entero introducido por teclado
     */
    public static int lecturaInt(){
        try{
            return (Integer.parseInt(br.readLine()));
        }
        catch(IOException | NumberFormatException e){
            //System.out.print("[ERROR] Dato no valido, introduce dato valido (Entero): ");
            //return lecturaInt();
            return 0;
        }
    }
    
    /**
     * Método para lectura de un número real (float) introducido por teclado
     * Si se introduce un valor no válido, devolverá un error y pedirá de nuevo
     * que se introduzca un valor válido
     * @return Valor float (numero real) introducido por teclado
     */
    public static float lecturaFloat(){
        try{
            return Float.parseFloat(br.readLine());
        }
        catch(IOException | NumberFormatException e){
            //System.out.print("[ERROR] Dato no valido, introduce dato valido (Numero real): ");
            //return lecturaFloat();
            return 0;
        }
    }
}
