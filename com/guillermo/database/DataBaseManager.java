package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import model.Registro;


/**
 * Gestor de base de datos.
 * Clase sin terminar de implementar de metodos generalizados
 * a cualquier tipo de tablas.
 * 
 * CLASE: Registro
 * 
 * @version 0.4.0
 * @author Guillermo Casas Reche
 * @author g.casas.r94@gmail.com
 */
public class DataBaseManager{
    
    // VARIABLES ESPECIFICAS AL EJERCICIO
    private String tableSelect = "consulta ";
    private final String[] keys = {"numeroConsulta","procedencia","nombreMedico","deinpr","fecha"};

    // ATRIBUTOS GLOBALES
    private final Connection conn;
    private static final String QUERY_SELECT = "SELECT %s FROM ";
    private static final String QUERY_ADD = "INSERT INTO ";
    private static final String QUERY_UPDATE = "UPDATE ";
    private static final String QUERY_DELETE = "DELETE FROM ";
    private static final String FORMATO_DATE = "yyyy-MM-dd";
    private static boolean error = false;
    private boolean verbose = false;

    
    // GETTER AND SETTERS
    public String getTableSelect() {
        return tableSelect;
    }

    public void setTableSelect(String tableSelect) {
        this.tableSelect = tableSelect;
    }

    public static boolean isError() {
        return error;
    }
    
    public void setVerbose(boolean tipo){
        this.verbose = tipo;
    }
    
    // CONSTRUCTORES
    public DataBaseManager(Connection conn) {
        super();
        this.conn = conn;
    }
    
    public DataBaseManager(Connection conn, String tableSelect){
        this.conn = conn;
        this.tableSelect = tableSelect + " ";
    }
    
    /*
    =====================================================================
    ====================== MÉTODOS Privados =============================
    =====================================================================
    */
    
    /*
    =================< MÉTODOS DE LA FAMILIA GET-INSERT >=======================
    */
    /**
     * Método principal para la obtencion de una consulta tipo SELECT. 
     * Se debe indicar las columnas a obtener, la tabla a la que atacar y los 
     * filtros que realizar.
     * 
     * @param consulta Indica el conenido entre el SELECT y el FROM.
     * @param tabla Tabla a la que atacar
     * @param filtro Filtro que aplicar.
     * @return Consulta ya formada
     */
    private String getSelectQueryPRINCIPAL(String consulta, String tabla, String filtro){
        return String.format(QUERY_SELECT, consulta) + tabla + " " + filtro;
    }
    
    /**
     * Obtiene la consulta de consulta con filtros para la tabla 
     * <code>tableSelect</code>. Solo para obtener datos de los registros de 
     * la base de datos, no para modificar valores (solo <code>SELECT</code>).
     * 
     * @param column Columna a la que aplicar algun filtro
     * @param filter Condición por la que buscar
     * @return Query de consulta con filtro
     */
    private String getSelectQuery(int column, String filter){
        return getSelectQueryPRINCIPAL(String.join(",", keys),tableSelect,getWhere(keys[column], filter));
    }
    
    /**
     * Obtiene la consulta basica para consultar los registro de la tabla 
     * <code>tableSelect</code>. Solo para obtener los registros, no para 
     * modificar valores (solo <code>SELECT</code>).
     * 
     * @param table Nombre de la tabla
     * @return Query de consulta
     */
    private String getSelectQuery(){
        return getSelectQueryPRINCIPAL(String.join(",", keys), tableSelect, "");
    }
    
    /**
     * Obtiene la consulta de selección de una columna al que se le aplica un 
     * atributo. Este metodo es idoneo para obtener consultas como obtener los
     * nombres diferentes.
     * 
     * @param columna Columna que obtener
     * @param atribute Atributo que añadir
     * @return Query de la consulta
     */
    private String getSelectQueryAtribute(int columna, String atribute){
        return getSelectQueryPRINCIPAL(atribute + " " + keys[columna], tableSelect, "");
    }
    
    /**
     * Obtiene la consulta basica para la columna indicada. 
     * El parametros a pasar corresponde al numero de indice de la lista 
     * <code>keys[]</code>
     * 
     * @param columna Indice de la lista <code>keys[]</code>
     * @return 
     */
    private String getSelectQuery(int columna){
        return getSelectQueryPRINCIPAL(keys[columna], tableSelect, "");
    }
    
    
    /*
    ====================< MÉTODO GET-INSERT >===================================
    */
    /**
     * Devuelve una consulta <code>INSERT</code> para el alumno entregado.
     * 
     * @param a Registro con el que contruir la consulta
     * @return Query
     */
    private String getInsertQuery(Registro a){
        String query = QUERY_ADD + tableSelect + " (" + String.join(",", keys) + ") VALUES (";
        query+= "'" + a.getNumeroConsulta() + "',";
        query+= "'" + a.getProcedencia()    + "',";
        query+= "'" + a.getNombreMedico()   + "',";
        query+= "'" + a.getDeinpr()         + "',";
        query+= "'" + getFechaString(a.getFecha()) + "')";
        return query;
    }
    
    /*
    ====================< MÉTODO GET-UPDATE >===================================
    */
    /**
     * Devuelve una consulta <code>UPDATE</code>. Sustituirá todos los 
     * parametros del registro con la ID indicada por el objeto Registro 
     * entregado.
     * Devuelve el numero de filas afectadas, si no es afectada ninguna, 
     * devolverá un 0.
     * 
     * @param id Key del registro a modificar
     * @param a Nuevo Registro a escribir
     * @return Nº de filas afectadas
     */
    private String getUpdateQuery(int id, Registro a){
        String query = QUERY_UPDATE + tableSelect + " SET ";
        query+= keys[1] + "='" + a.getProcedencia()     + "',";
        query+= keys[2] + "='" + a.getNombreMedico()    + "',";
        query+= keys[3] + "='" + a.getDeinpr()          + "',";
        query+= keys[4] + "='" + getFechaString(a.getFecha()) + "' ";
        query+= getWhere(keys[0], String.valueOf(id));
        return query;
    }
    
    
    /*
    ====================< MÉTODO GET-DELETE >===================================
    */
    /**
     * Devuelve una consulta <code>DELETE</code> para la key indicada.
     * Devuelve el numero de filas afectadas, si no es afectada ninguna, 
     * devolverá un 0.
     * 
     * @param id Key del registro a eliminar
     * @return Nº de filas afectadas
     */
    private String getDeleteQuery(int id){
        return QUERY_DELETE + tableSelect + " " + getWhere(keys[0], String.valueOf(id));
    }
    
    
    /*
    =====================< MÉTODO GET-WHERE >===================================
    */
    /**
     * Devuelve una clausula <code>WHERE</code>
     * @param column Nombre de la columna al aplicar la restricción
     * @param filter Valor de la condición
     * @return 
     */
    private String getWhere(String column, String filter){
        return "WHERE " + column +"='"+filter+"' ";
    }
    
    
    /*
    ====================< MÉTODOS MAPING >===================================
    */
    /**
     * Método que transforma la salida de la consulta a la base de datos SQL
     * a una lista de Registro
     * @param rs Resultado de la consulta a la DDBB
     * @return Lista de todos los Doctores obtenidos en la consulta
     */
    private List<Registro> mapingRegistros(ResultSet rs){
        List<Registro> salida = new ArrayList<>();
        if(rs!=null){
            try {
                while(rs.next()){
                    salida.add(new Registro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), getFechaDate(rs.getString(5))));
                }
            } catch (SQLException ex ) {
                System.err.println("[ERROR-MAPING] " + ex);
            }
        }else salida = null;
        return salida;
    }
    
    /**
     * Devuelve una lista de solo el String de la primera columna de la fila.
     * Este metodo esta pensado para las consultas en las que se recive una
     * lista nada mas, como las tablas de una base de datos o las bases de 
     * datos que tiene nuestro servidor.
     * @param rs Respuesta de una consulta
     * @return Lista de delementos <code>String</code>
     */
    private List<String> mapingDataOne(ResultSet rs){
        List<String> salida = new ArrayList<>();
        if(rs != null){
            try {
                while(rs.next()){

                    salida.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                System.err.println("[ERROR-MAPING] " + ex);
            }
        }else salida = null;
        return salida;
    }
    
    /**
     * Devuelve una lista de properties. Este método es idoneo para 
     * la trasformación de cualquier resultado de una consulta a un formato
     * JSON, ya que todos los datos de una fila las almacena indicando el
     * nombre de la columna a la que pertenece.
     * Cada elemento <code>Properties</code> contiene una tupla o fila.
     * El metodo es general a cualquier tabla.
     * 
     * Metodo desarrollado para cuando la clase sea generica
     * 
     * @param rs Resultado de una consulta
     * @return Una lista de <code>Properties</code>
     */
    private List<Properties> mapingDataAll(ResultSet rs){
        List<Properties> datos = new ArrayList<>();
        if(rs != null){
            try {
                ResultSetMetaData md = rs.getMetaData();
                int numero = md.getColumnCount();
                while(rs.next()){
                    Properties p = new Properties();
                    for(int i=1;i<=numero;i++){
                        p.setProperty(md.getColumnLabel(i), rs.getString(i));
                    }
                    datos.add(p);
                }
            } catch (SQLException ex) {
                System.err.println("[ERROR-MAPING] " + ex);
            }
        }else datos = null;
        return datos;
    }    
    
    
    /*
    ====================< MÉTODO DE EJECUCIÓN >===================================
    */
    /**
     * Método que ejecuta una Query de selección a la base de datos conectada.
     * @param query Consulta 
     * @return Respuesta de la base de datos. Si se produce algun error 
     * devolverá un null
     */
    private ResultSet executeQStr(String query){
        if(verbose) System.out.println("Consulta S: " + query);
        ResultSet salida = null;
        
        if(isConection()){
            error=false;
            try {
                PreparedStatement ps;
                ps = conn.prepareStatement(query);
                salida = ps.executeQuery();
                
            } catch (SQLException ex) {
                System.err.println("[ERROR-EXECUTE] " + ex);
                error=true;
            }
        }
        return salida;
    }
    
    /**
     * Método que ejecuta una Query para realizar modificaciones de los 
     * registros de la base de datos conectada.
     * Devolverá el numero de filas afectadas.
     * 
     * @param query
     * @return Nº de filas afectadas
     */
    private int executeUStr(String query){
        if(verbose) System.out.println("Consulta U: " + query);
        int updRow = 0;
        
        if(isConection()){
            error=true;
            try{
                PreparedStatement ps;
                ps = conn.prepareStatement(query);
                updRow = ps.executeUpdate();
                //conn.prepareStatement("commit").execute();
                error=false;
                
            } catch (SQLIntegrityConstraintViolationException ex){
                System.err.println("La ID del registro ya existe");    
            } catch (SQLSyntaxErrorException ex){
                System.err.println("Consulta erronea: " + ex.getMessage());
            } catch (SQLException ex) {
                System.err.println("[ERROR-UPDATE] " + ex);
            }
        }
        return updRow;
    }
    
    
    /*
    ==============================< METODO DE FECHA >===========================
    */
    public String getFechaString(Date d){
        return new SimpleDateFormat(FORMATO_DATE).format(d);
    }
    public Date getFechaDate(String fecha){
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        } catch (ParseException ex) {
            System.out.println("Error de formato de fecha");
            return null;
        }
    }
    
    /*
    =========================< METODOS DE COMODIDAD >===========================
    */
    /**
     * Métodos de comodidad
     * Ejecutará <code>maping()</code> en la respuesta obtenida en el 
     * <code>execute()</code> con la consulta obtenida con 
     * <code>getQuery()</code>.
     * 
     * @return Lista de doctores
     */
    private List<Registro> ejecutarSelect(){
        if(isConection()) return mapingRegistros(executeQStr(getSelectQuery()));
        return null;
    }
    
    /**
     * Métodos de comodidad
     * Ejecutará maping() en la respuesta obtenida en el execute() con la 
     * consulta obtenida con getQuery().
     * 
     * @param column Nombre de la columna a la que aplicar el filtro
     * @param filter Filtro a aplicar a la columna elegida
     * @return Lista de doctores
     */
    private List<Registro> ejecutarSelect(int column,String filter){
        if(isConection()) return mapingRegistros(executeQStr(getSelectQuery(column, filter)));
        return null;
    }
    
    /*
    =====================================================================
    ========================= MÉTODOS Públicos ==========================
    =====================================================================
    */
    
    /**
     * Indica si la clase se encuentra conectada a una base de datos
     * @return Estado de la conexión
     */
    public boolean isConection(){
        return (this.conn != null);
    }
    
    
    /*
    ============================< MÉTODOS CRUD >================================
    */
    public List<Registro> obtenerTodosLosRegistros() {
        return this.ejecutarSelect();
    }

    public Registro obtenerRegistroPorId(int id) {
        List<Registro> idA = this.ejecutarSelect(0, String.valueOf(id));
        
        Registro a = (idA != null && idA.size()>0) ? idA.get(0) : null;
        return a;
    }

    public List<Registro> obtenerRegistroPorNombre(String nombre) {
        return this.ejecutarSelect(2, nombre);
    }

    public Registro obtenerUltimoRegistro() {
        List<Registro> aux = this.obtenerTodosLosRegistros();
        if(aux!=null && aux.size()>0) return aux.get(aux.size()-1);
        return null;
    }
    
    public List<String> obtenerValoresDistintos(int columna){
        return mapingDataOne(executeQStr(getSelectQueryAtribute(columna,"DISTINCT")));
    }

    public int crearRegistro(Registro registro) {
        return this.executeUStr(this.getInsertQuery(registro));
    }

    public int actualizarRegistro(int id, Registro registro) {
        return this.executeUStr(this.getUpdateQuery(id, registro));
    }

    public int borrarRegistro(int id) {
        return this.executeUStr(this.getDeleteQuery(id));
    }

    
    /*
    ===============< MÉTODOS DE GESTIÓN DE LA BASE DE DATOS >===================
    */
    public boolean existeTablaRegistro() {
        return existeTablaDada(tableSelect.trim());
    }

    public boolean existeTablaDada(String table) {
        return obtenerTodasTablasBaseDatos().contains(table);
    }

    public int borrarTablaRegistro() {
        return executeUStr("DROP TABLE "+tableSelect);
        
    }

    public List<String> obtenerTodasTablasBaseDatos() {
        return mapingDataOne(executeQStr("SHOW TABLES"));
    }

    public List<String> obtenerTodasBaseDatos() {
        return mapingDataOne(executeQStr("SHOW DATABASES"));
    }

    public List<String> obtenerTodasColumnas() {
        return obtenerTodasColumnas(tableSelect);
    }
    
    public List<String> obtenerTodasColumnas(String tabla) {
        return mapingDataOne(executeQStr("SHOW COLUMNS IN " + tabla));
    }

    public List<String> obtenerTodasTablasBaseDatosDada(String basedatos) {
        return mapingDataOne(executeQStr("SHOW TABLES IN " + basedatos));
    }
    public void closeConn(){
        try {
            this.conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR al cerrar la conexión: " + ex);
        }
    }
}
