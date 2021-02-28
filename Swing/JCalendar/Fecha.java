package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fecha {
    
    private static final String FORMAT_SQL = "yyyy-mm-dd";
    private static final String FORMAT_JAVA = "dd/mm/yyyy";
    private static final String FORMAT_JDATE = "dd/MM/yyyy";

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
    public static String formatJAVA(String format,Date date){
        return parseadorDate(FORMAT_JAVA,format,date);
    }
    
    public static String formatJData(String format,Date date){
        return parseadorDate(FORMAT_JDATE,format,date);
    }
    
    private static String parseadorDate(String formatSalida,String formatEntrada,Date date){
        SimpleDateFormat sdm = new SimpleDateFormat(formatEntrada); // Indicamos el formato que recibe
        sdm.applyPattern(formatSalida); // Indicamos el formato de salida
        String salida = sdm.format(date);
        return salida;
    }
    
    private static String parseador(String formatSalida,String formatEntrada,String date){
        String salida="";

        try {
            SimpleDateFormat sdm = new SimpleDateFormat(formatEntrada); // Indicamos el formato que recibe
            Date fecha;
            fecha = sdm.parse(date);
            sdm.applyPattern(formatSalida); // Indicamos el formato de salida
            salida = sdm.format(fecha);
        } catch (ParseException ex) {
            System.err.println("[ERROR-DATE] " + ex);
        }
        return salida;
    }
}
