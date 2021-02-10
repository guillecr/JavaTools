package com.guillermo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase generadora de conexiones
 * 
 * @version 0.7.0
 * @author Guillermo Casas Reche
 * @author g.casas.r94@gmail.com
 */
public class Conector {
    
    public static final String DRIVER_MYSQL = "jdbc:mysql://";
    public static final String DRIVER_ORACLE = "jdbc:oracle:thin:@";
    public static final String DRIVER_POSTGRESQL = "jdbc:postgresql://";
    public static final String DRIVER_SQLITE = "jdbc:sqlite:";
    
    public static final String PORT_MYSQL = "3306";
    public static final String PORT_MYSQL_DOCKER = "33060";
    public static final String PORT_ORACLE = "1521:xe";
    public static final String PORT_POSTGRESQL = "5432";
    
    
    private static final String URL_DEFAULT = "localhost";
    private static final String DB_DEFAULT = "test";
    private static final String USER_DEFAULT = "root";
    private static final String PW_DEFAULT = "";
    
    /*
    ================================================
    ============= MÉTODOS Privados =================
    ================================================
    */
    
    private static Connection getConn(String driver,String port ,String URLconection, String user, String pw){
        
        String driverUrl = generateUrl(driver,port,URLconection);
        System.out.println("Ruta: " + driverUrl);
        Connection connect = null;
        
        try {
            connect = DriverManager.getConnection(driverUrl,user,pw);
        } catch (SQLException ex) {
            System.err.println("[ERROR-CONECTION] " + ex);
        }
        
        return connect;
    }
    
    private static String generateUrl(String driver, String port, String url){
        port = (port == null) ? "" : ":"+port;
        return driver + url + port;
    }
    
    private static void setDataBase(Connection conn, String db){
        try {
            if( conn != null){
                PreparedStatement ps;
                ps = conn.prepareStatement("CREATE DATABASE IF NOT EXISTS " + db);
                ps.execute();
                ps = conn.prepareStatement("USE "+db);
                ps.execute();            
            }
        } catch (SQLException ex) {
            System.err.println("[WARNING-DATABASE] " + ex);
        }
    }
    
    /*
    ================================================
    ============== MÉTODOS Públicos ================
    ================================================
    */
    
    /**
     * Método principal de <code>Conector</code> para obtener la conexion con una base de 
     * datos. Hay que indicar todos los parámetros de conexión. Devolverá 
     * <code>null</code> si hay un problema en la conexión.
     * 
     * Para establecer conexión con bases MySQL u Oracle se recomienda usar 
     * sus metodos especificos. Alternativamente se puede usar los drivers y
     * puertos de esta clase (<code>DRIVER_MYSQL</code> por ejemplo)
     * 
     * @param driver Driver empleado para la conexión a la base de datos
     * @param port Puerto de conexión que emplea la base de datos
     * @param url Direccion del servidor
     * @param user Usuario
     * @param pw Contraseña
     * @return Conexión 
     */
    public static Connection getConexion(String driver, String port, String url, String user,String pw){
        return Conector.getConn(driver, port, url, user, pw);
    }
    
    /**
     * Método principal para conexiones a bases de datos MySQL. Hay que
     * indicar el puerto, la dirección del servidor, el nombre de la base de 
     * datos y las credenciales.
     * Se creará la base de datos indicada si existe y la usará
     * 
     * Está indicado para conexiones a MySQL que no usan su puerto por defecto.
     * Las bases de datos MySQL montadas en Docker suele emplear el puerto 
     * <code>PORT_MYSQL_DOCKER</code>.
     * Si no se puede establecer conexión, devolverá <code>null</code>
     * 
     * @param port Puerto de conexión
     * @param url Direccion del servidor
     * @param DBname Nombre de la base de datos
     * @param user Nombre de usuario
     * @param pw Contraseña
     * @return Conexión
     */
    public static Connection getConexionMySQL(String port, String url,String DBname, String user,String pw){
        Connection conn = Conector.getConn(DRIVER_MYSQL, port, url, user, pw);
        Conector.setDataBase(conn, DBname);
        return conn;
    }
    
    /**
     * Método principal para conexiones a bases de datos Oracle. Hay que
     * indicar el puerto, la dirección del servidor y las credenciales.
     * 
     * Está indicado para conexiones a Oracle que no usan su puerto por defecto.
     * Si no se puede establecer conexión, devolverá <code>null</code>
     * 
     * @param port Puerto de conexión
     * @param url Direccion del servidor
     * @param user Nombre de usuario
     * @param pw Contraseña
     * @return Conexión
     */
    public static Connection getConexionOracle(String port, String url, String user, String pw){
        return Conector.getConexion(DRIVER_ORACLE, port, url, user, pw);
    }

    /**
     * Método principal de conexiones a bases de datos Postgres. Hay que
     * indicar el puerto, la dirección del servidor y las credenciales.
     *
     * Si no se puede establecer conexión, devolverá <code>null</code>
     *
     * @param port Puerto de conexión
     * @param url Direccion del servidor
     * @param user Nombre de usuario
     * @param pw Contraseña
     * @return Conexión
     */
    public static Connection getConexionPostgres(String url, String user, String pw){
        return Conector.getConexion(DRIVER_POSTGRESQL,PORT_POSTGRESQL, url, user, pw);
    }
    
    /**
     * Método principal para conexiones a bases de datos SQLite. 
     * Bastará solo con indicar la path de la base de datos en la que se encuentra.
     * 
     * Si no se puede establecer conexión, devolverá <code>null</code>
     * 
     * @param url Path de la BD SQLite
     * @return Conexión a la base de datos
     */
    public static Connection getConexionSQLite(String url){
        return Conector.getConexion(DRIVER_SQLITE, null, url, "", "");
    }

    // ====================================================
    // DEFAULTS DE LOS METODOS PRINCIPALES DE CONEXIÓN
    // ====================================================

    /**
     * Devolverá una conexión a MySQL.
     * Usará por defecto la base de datos indicada. Si no existe, la creará primero.
     * 
     * Si no se puede establecer conexión, devolverá <code>null</code>
     *
     * @param url Direccion del servidor
     * @param DBname Nombre de la base de datos
     * @param user Usuario
     * @param pw Contraseña
     * @return Conexión 
     */
    public static Connection getConexionMySQL(String url,String DBname, String user,String pw){
        return Conector.getConexionMySQL(PORT_MYSQL, url, DBname, user, pw);
    }
    
    /**
     * Devolverá una conexión a Oracle.
     * Usará el puerto estandar de Oracle.
     * 
     * Si no se puede establecer conexión, devolverá <code>null</code>
     *
     * @param url Direccion del servidor
     * @param user Usuario
     * @param pw Contraseña
     * @return Conexión 
     */
    public static Connection getConexionOracle(String url, String user, String pw){
        return Conector.getConexionOracle(PORT_ORACLE, url, user, pw);
    }
    
    /**
     * Devolverá una conexión a MySQL en <code>localhost</code>.
     * Usará por defecto la base de datos indicada. Si no existe, la creará primero.
     * 
     * Si no se puede establecer conexión, devolverá <code>null</code>
     *
     * @param DBname Nombre de la base de datos
     * @param user Usuario
     * @param pw Contraseña
     * @return Conexión 
     */
    public static Connection getConexionMySQL(String DBname,String user,String pw){
        return Conector.getConexionMySQL(URL_DEFAULT, DBname, user, pw);
    }
    
    /**
     * Devolverá una conexión a Oracle en <code>localhost</code>.
     * Usará el puerto estandar de Oracle.
     * 
     * Si no se puede establecer conexión, devolverá <code>null</code>
     *
     * @param user Usuario
     * @param pw Contraseña
     * @return Conexión 
     */
    public static Connection getConexionOracle(String user, String pw){
        return Conector.getConexionOracle(URL_DEFAULT, user,pw);
    }
    
    /**
     * Devolverá una conexión a MySQL en <code>localhost</code>.
     * Se conectará a la base de datos <code>test</code> por defecto.
     * 
     * Si no se puede establecer conexión, devolverá <code>null</code>
     *
     * @param user Usuario
     * @param pw Contraseña
     * @return Conexión 
     */
    
    public static Connection getConexionMySQL(String user, String pw){
        return Conector.getConexionMySQL(DB_DEFAULT, user, pw);
    }
    
    /**
     * Devolverá una conexión a MySQL en su forma mas basica. Empleará como parametros:
     * <p> - Puerto: 3306</p>
     * <p> - URL: <code>localhost</code></p>
     * <p> - DBname: <code>test</code><p>
     * <p> - Usuario: 'root'</p>
     * <p> - Password: ''</p>
     * 
     * Si no se puede establecer conexión, devolverá <code>null</code>
     *
     * @return Conexión 
     */
    public static Connection getConexionMySQL(){
        return Conector.getConexionMySQL(URL_DEFAULT, DB_DEFAULT, USER_DEFAULT, PW_DEFAULT);
    }
}
