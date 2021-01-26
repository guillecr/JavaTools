package com.guillermo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase generadora de conexiones
 * 
 * @version 0.5.0
 * @author Guillermo Casas Reche
 * @author g.casas.r94@gmail.com
 */
public class Conector {
    
    public static final String DRIVER_MYSQL = "jdbc:mysql://";
    public static final String DRIVER_ORACLE = "jdbc:oracle:thin:@";
    
    public static final String PORT_MYSQL = "3306";
    public static final String PORT_MYSQL_DOCKER = "33060";
    public static final String PORT_ORACLE = "1521:xe";
    
    
    private static final String URL_DEFAULT = "localhost";
    private static final String DB_DEFAULT = "test";
    private static final String USER_DEFAULT = "root";
    private static final String PW_DEFAULT = "";
    
    /*
    ================================================
    ============= MÉTODOS Privados =================
    ================================================
    */
    
    private static Connection getConection(String driver,String port ,String URLconection, String user, String pw){
        
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
        return driver + url + ":" + port;
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
     * Metodo principal de la clase para obtener la conexion con una base de 
     * datos. Hay que indicar todos los parametros de conexión. Devolverá 
     * <code>null</code> si hay un problema de conexión.
     * Se creará la base de datos indicada si existe y la usará
     * 
     * Para establecer conexión con bases MySQL u Oracle se recomienda usar 
     * sus metodos especificos. Alternativamente se puede usar los drivers y
     * puertos de esta clase (<code>DRIVER_MYSQL</code> por ejemplo)
     * 
     * @param driver Driver empleado para la conexión a la base de datos
     * @param port Puerto de conexión que emplea la base de datos
     * @param url Direccion del servidor
     * @param DBname Nombre de la base de datos
     * @param user Usuario
     * @param pw Contraseña
     * @return Conexión 
     */
    public static Connection getConexion(String driver, String port, String url,String DBname, String user,String pw){
        
        Connection conn = Conector.getConection(driver, port, url, user, pw);
        Conector.setDataBase(conn, DBname);
        
        return conn;
    }
    
    /**
     * Método principal de conexiones a bases de datos MySQL. Hay que
     * indicar el puerto, la dirección del servidor, el nombre de la base de 
     * datos y las credenciales.
     * Se creará la base de datos indicada si existe y la usará
     * 
     * Está indicado para conexiones a MySQL que no usan su puerto por defecto.
     * Para establecer conexión con docker, suele emplear el puerto 
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
        return Conector.getConexion(DRIVER_MYSQL, port, url, DBname, user, pw);
    }
    
    /**
     * Método principal de conexiones a bases de datos Oracle. Hay que
     * indicar el puerto, la dirección del servidor, el nombre de la base de 
     * datos y las credenciales.
     * Se creará la base de datos indicada si existe y la usará
     * 
     * Está indicado para conexiones a Oracle que no usan su puerto por defecto.
     * Si no se puede establecer conexión, devolverá <code>null</code>
     * 
     * @param port Puerto de conexión
     * @param url Direccion del servidor
     * @param DBname Nombre de la base de datos
     * @param user Nombre de usuario
     * @param pw Contraseña
     * @return Conexión
     */
    public static Connection getConexionOracle(String port, String url, String DBname, String user, String pw){
        return Conector.getConexion(DRIVER_ORACLE, port, DBname, url, user, pw);
    }
    
    /**
     * Devolverá una conexión a MySQL.
     * Se creará la base de datos indicada si existe y la usará
     * Si no se puede establecer conexión, devolverá <code>null</code>
     * 
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
     * Se creará la base de datos indicada si existe y la usará
     * Si no se puede establecer conexión, devolverá <code>null</code>
     * 
     *
     * @param url Direccion del servidor
     * @param DBname Nombre de la base de datos
     * @param user Usuario
     * @param pw Contraseña
     * @return Conexión 
     */
    public static Connection getConexionOracle(String url, String DBname, String user, String pw){
        return Conector.getConexionOracle(PORT_ORACLE, url, DBname, user, pw);
    }
    
    /**
     * Devolverá una conexión a MySQL en <code>localhost</code>.
     * Se creará la base de datos indicada si existe y la usará
     * Si no se puede establecer conexión, devolverá <code>null</code>
     * 
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
     * Se creará la base de datos indicada si existe y la usará
     * Si no se puede establecer conexión, devolverá <code>null</code>
     * 
     *
     * @param DBname Nombre de la base de datos
     * @param user Usuario
     * @param pw Contraseña
     * @return Conexión 
     */
    public static Connection getConexionOracle(String DBname, String user, String pw){
        return Conector.getConexionOracle(URL_DEFAULT,DBname, user,pw);
    }
    
    /**
     * Devolverá una conexión a MySQL en <code>localhost</code>.
     * Se conectará a la base de datos <code>test</code> por defecto.
     * Si no se puede establecer conexión, devolverá <code>null</code>
     * 
     *
     * @param user Usuario
     * @param pw Contraseña
     * @return Conexión 
     */
    public static Connection getConexionMySQL(String user, String pw){
        return Conector.getConexionMySQL(URL_DEFAULT, DB_DEFAULT, user, pw);
    }
    
    /**
     * Devolverá una conexión a Oracle en <code>localhost</code>.
     * Se conectará a la base de datos <code>test</code> por defecto.
     * Si no se puede establecer conexión, devolverá <code>null</code>
     * 
     *
     * @param user Usuario
     * @param pw Contraseña
     * @return Conexión 
     */
    public static Connection getConexionOracle(String user, String pw){
        return Conector.getConexionOracle(URL_DEFAULT,DB_DEFAULT, user,pw);
    }
    
    /**
     * Devolverá una conexión a MySQL en <code>localhost</code>.
     * Se conectará a la base de datos <code>test</code> por defecto.
     * Las credenciales serán:
     * - Usuario: 'root'
     * - Password: ''
     * 
     * Si no se puede establecer conexión, devolverá <code>null</code>
     *
     * @return Conexión 
     */
    public static Connection getConexionMySQL(){
        return Conector.getConexionMySQL(URL_DEFAULT, DB_DEFAULT, USER_DEFAULT, PW_DEFAULT);
    }
}
