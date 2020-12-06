package libreria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase generadora de conexiones
 * 
 * @version 0.4.0
 * @author Guillermo Casas Reche
 * @author g.casas.r94@gmail.com
 */
public class Conector {
    
    private static Connection conn = null;
    private static final String[] DRIVERS = {"jdbc:mysql:"};
    private static final String[] PORTS = {"3306"};
    private static final String URL_DEFAULT = "localhost";
    private static final String DB_DEFAULT = "test";
    private static final String USER_DEFAULT = "root";
    private static final String PW_DEFAULT = "";

    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        Conector.conn = conn;
    }

    public static String[] getDRIVERS() {
        return DRIVERS;
    }

    public static String[] getPORTS() {
        return PORTS;
    }

    public static String getURL_DEFAULT() {
        return URL_DEFAULT;
    }

    public static String getDB_DEFAULT() {
        return DB_DEFAULT;
    }

    public static String getUSER_DEFAULT() {
        return USER_DEFAULT;
    }

    public static String getPW_DEFAULT() {
        return PW_DEFAULT;
    }
    
    /*
    ================================================
    ============= MÉTODOS Privados =================
    ================================================
    */
    
    private static Connection getConection(int selc, String URLconection, String user, String pw){
        
        String driverUrl = generateUrl(selc, URLconection);
        Connection connect = null;
        
        try {
            connect = DriverManager.getConnection(driverUrl,user,pw);
        } catch (SQLException ex) {
            System.err.println("[ERROR-CONECTION] " + ex);
        }
        
        return connect;
    }
    
    
    
    
    private static String generateUrl(int selc, String url){
        return Conector.getDRIVERS()[selc] + "//" + url + ":" + Conector.getPORTS()[selc];
    }
    
    private static void setDataBase(String db){
        try {
            if( Conector.getConn() != null){
                PreparedStatement ps;
                ps = Conector.getConn().prepareStatement("CREATE DATABASE IF NOT EXISTS " + db);
                ps.execute();
                ps = Conector.getConn().prepareStatement("USE "+db);
                ps.execute();            
            }
        } catch (SQLException ex) {
            System.err.println("[WARNING-DATABASE] " + ex);
        }
    }
    
    /*
    ================================================
    ============== MÉTODOS Publicos ================
    ================================================
    */
    
    /**
     * Devolverá una conexión utilizando el driver MySQl (jdbc:mysql:).
     * Esta se quedará almacenada en la clase, para poder cerrarla con el comando 
     * Conector.close().
     * Se creará la base de datos indicada si existe y la usará
     * Si no se puede establecer conexión, devolverá null
     * 
     * @param url Direccion del servidor
     * @param DBname Nombre de la base de datos (Colección)
     * @param user Usuario
     * @param pw Contraseña
     * @return Conexión 
     */
    public static Connection getConexionMySQL(String url,String DBname, String user,String pw){
        
        Conector.setConn(Conector.getConection(0, url, user, pw));
        Conector.setDataBase(DBname);
        
        return conn;
    }
    
    /**
     * Devolverá una conexión utilizando el driver MySQl (jdbc:mysql:).
     * Esta se quedará almacenada en la clase, para poder cerrarla con el comando 
     * Conector.close().
     * Usaral una base de datos por defecto (test)
     * Si no se puede establecer conexión, devolverá null
     * 
     * @param url Direccion del servidor
     * @param user Usuario
     * @param pw Contraseña
     * @return Conexión 
     */
    public static Connection getConexionMySQL(String url,String user,String pw){
        return Conector.getConexionMySQL(url, Conector.getDB_DEFAULT(), user, pw);
    }
    
    /**
     * Devolverá una conexión utilizando el driver MySQl (jdbc:mysql:).
     * Esta se quedará almacenada en la clase, para poder cerrarla con el comando 
     * Conector.close().
     * La dirección de servidor usada será la `localhost`
     * Usaral una base de datos por defecto (test)
     * Si no se puede establecer conexión, devolverá null
     * 
     * @param user Usuario
     * @param pw Contraseña
     * @return Conexión 
     */
    public static Connection getConexionMySQL(String user, String pw){
        return Conector.getConexionMySQL(Conector.getURL_DEFAULT(), Conector.getDB_DEFAULT(), user, pw);
    }    
    
    /**
      * Devolverá una conexión utilizando el driver MySQl (jdbc:mysql:).
     * Esta se quedará almacenada en la clase, para poder cerrarla con el comando 
     * Conector.close().
     * La dirección de servidor usada será la `localhost`
     * Usaral una base de datos por defecto (test).
     * Las credenciales usadas seran => usuario: root, pw: empty
     * Si no se puede establecer conexión, devolverá null
     * 
     * @return Conexión 
     */
    public static Connection getConexionMySQL(){
        return Conector.getConexionMySQL(Conector.getURL_DEFAULT(), Conector.getDB_DEFAULT(), Conector.getUSER_DEFAULT(), Conector.getPW_DEFAULT());
    }
    
    public static void closeConn(){
        try {
            Conector.conn.close();
        } catch (SQLException ex) {
            System.err.println("[ERROR - CLOSE CONECTION] " + ex);
        }
    }
}
