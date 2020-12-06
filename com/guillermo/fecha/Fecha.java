package librerias;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase abstracta para manejar fechas.
 * Muy orientado en datos procedentes de 
 * BBDD
 *
 * @version 0.2 
 * @author Guillermo Casas Reche
 * @author g.casas.r94@gmail.com
 */
public abstract class Fecha {
    
    private static final String FORMAT_SQL = "yyyy-mm-dd";
    private static final String FORMAT_JAVA = "dd/mm/yyyy";

    public static String getFORMAT_SQL() {
        return FORMAT_SQL;
    }
    public static String getFORMAT_JAVA() {
        return FORMAT_JAVA;
    }
    
    public static String formatSQL(String format,String date){
        return parseador(FORMAT_SQL,format,date);
    }
    public static String formatJAVA(String format,String date){
        return parseador(FORMAT_JAVA,format,date);
    }
    
    private static String parseador(String formatSalida,String formatEntrada,String date){
        String salida="";

        try {
            SimpleDateFormat sdm = new SimpleDateFormat(formatEntrada);
            Date fecha;
            fecha = sdm.parse(date);
            sdm.applyPattern(formatSalida);
            salida = sdm.format(fecha);
        } catch (ParseException ex) {
            System.err.println("[ERROR-DATE] " + ex);
        }
        return salida;
    }
}
