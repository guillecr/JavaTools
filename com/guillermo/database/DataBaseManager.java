package libreria;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import modelo.Alumno;


/**
 * Gestor de base de datos.
 * Clase sin terminar de implementar de metodos generalizados
 * a cualquier tipo de tablas.
 * 
 * @version 0.3.0
 * @author Guillermo Casas Reche
 * @author g.casas.r94@gmail.com
 */
public class DataBaseManager implements Operaciones{
    
    // ATRIBUTOS GLOBALES
    private final Connection conn;
    private String tableSelect = "test ";
    private final String[] keys = {"id","nombre","apellidos","grupo","dob"};
    private static final String QUERY_BASE = "SELECT * FROM ";
    private static final String QUERY_ADD = "INSERT INTO ";
    private static final String QUERY_UPDATE = "UPDATE ";
    private static final String QUERY_DELETE = "DELETE FROM ";
    private static boolean error = false;

    
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
    
    /**
     * Método que transforma la salida de la consulta a la base de datos SQL
     * a una lista de Alumno
     * @param rs Resultado de la consulta a la DDBB
     * @return Lista de todos los Doctores obtenidos en la consulta
     */
    private List<Alumno> mapingAlumnos(ResultSet rs){
        List<Alumno> salida = new ArrayList<>();
        if(rs!=null){
            try {
                while(rs.next()){
                    salida.add(new Alumno(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5)));
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
    
    /**
     * Devuelve una clausula <code>WHERE</code>
     * @param column Nombre de la columna al aplicar la restricción
     * @param filter Valor de la condición
     * @return 
     */
    private String getWhere(String column, String filter){
        return "WHERE " + column +"='"+filter+"' ";
    }
    
    /**
     * Obtiene la consulta basica para consultar los registro de la tabla 
     * <code>tableSelect</code>. Solo para obtener los registros, no para 
     * modificar valores (solo <code>SELECT</code>)
     * @return Query de consulta
     */
    private String getSelectQuery(){
        return getSelectQuery(tableSelect);
    }
    
    /**
     * Obtiene la consulta basica para consultar los registro de la tabla
     * pasada como argumento. Solo para obtener los registros, no para 
     * modificar valores (solo <code>SELECT</code>)
     * @param table Nombre de la tabla
     * @return Query de consulta
     */
    private String getSelectQuery(String table){
        return QUERY_BASE+table;
    }
    /**
     * Obtiene la consulta de consulta con filtros para la tabla <code>tableSelect</code>. Solo para obtener 
     * datos de los registros de la base de datos, no para modificar valores
     * (solo <code>SELECT</code>)
     * @param column Columna a la que aplicar algun filtro
     * @param filter Condición por la que buscar
     * @return Query de consulta con filtro
     */
    private String getSelectQuery(String column, String filter){
        return getSelectQuery(tableSelect)+getWhere(column, filter);
    }
    
    /**
     * Devuelve una consulta <code>INSERT</code> para el alumno entregado
     * @param a Alumno con el que contruir la consulta
     * @return Query
     */
    private String getInsertQuery(Alumno a){
        String query = QUERY_ADD + tableSelect + "VALUES (";
        query+= a.getId()+",";
        query+= "'" + a.getNombre()+"',";
        query+= "'" + a.getApellidos()+"',";
        query+= "'" + a.getGrupo()+"',";
        query+= "'" + a.getFecha_nacimiento()+"')";
        return query;
    }
    
    /**
     * Devuelve una consulta <code>UPDATE</code>. Sustituirá todos los parametros del
     * registro con la ID indicada por el objeto Alumno entregado.
     * Devuelve el numero de filas afectadas, si no es afectada ninguna, devolverá un 0.
     * @param id Key del registro a modificar
     * @param a Nuevo Alumno a escribir
     * @return Nº de filas afectadas
     */
    private String getUpdateQuery(int id, Alumno a){
        String query = QUERY_UPDATE + tableSelect + "SET ";
        query+= keys[1] + "='" + a.getNombre() + "',";
        query+= keys[2] + "='" + a.getApellidos()+ "',";
        query+= keys[3] + "='" + a.getGrupo()+ "',";
        query+= keys[4] + "='" + a.getFecha_nacimiento()+ "' ";
        query+= getWhere(keys[0], String.valueOf(id));
        return query;
    }
    
    /**
     * Devuelve una consulta <code>DELETE</code> para la key indicada.
     * Devuelve el numero de filas afectadas, si no es afectada ninguna, devolverá un 0.
     * @param id Key del registro a eliminar
     * @return Nº de filas afectadas
     */
    private String getDeleteQuery(int id){
        return QUERY_DELETE+tableSelect+getWhere(keys[0], String.valueOf(id));
    }
    
    /**
     * Método que ejecuta una Query de selección a la base de datos conectada.
     * @param query Consulta 
     * @return Respuesta de la base de datos. Si se produce algun error devolverá
     * un null
     */
    private ResultSet executeQStr(String query){
        if(isConection()){
            ResultSet salida = null;
            error=false;
            try {
                PreparedStatement ps;
                ps = conn.prepareStatement(query);
                salida = ps.executeQuery();
                
            } catch (SQLException ex) {
                System.err.println("[ERROR-EXECUTE] " + ex);
                error=true;
            }
            return salida;
        }else return null;
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
        if(isConection()){
            int updRow = 0;
            error=false;
            try{
                PreparedStatement ps;
                ps = conn.prepareStatement(query);
                updRow = ps.executeUpdate();
            
            } catch (SQLIntegrityConstraintViolationException ex){
                System.err.println("La ID del registro ya existe");    
            } catch (SQLSyntaxErrorException ex){
                System.out.println(ex.getMessage());
            } catch (SQLException ex) {
                System.err.println("[ERROR-EXECUTE] " + ex);
                error=true;
            }
            return updRow;
        }else return 0;
    }
    
    /**
     * Métodos de comodidad
     * Ejecutará <code>maping()</code> en la respuesta obtenida en el <code>execute()</code> con la consulta obtenida con <code>getQuery()</code>.
     * @return Lista de doctores
     */
    private List<Alumno> ejecutarSelect(){
        if(isConection()) return mapingAlumnos(executeQStr(getSelectQuery()));
        return null;
    }
    
    /**
     * Métodos de comodidad
     * Ejecutará maping() en la respuesta obtenida en el execute() con la consulta obtenida con getQuery()
     * @param column Nombre de la columna a la que aplicar el filtro
     * @param filter Filtro a aplicar a la columna elegida
     * @return Lista de doctores
     */
    private List<Alumno> ejecutarSelect(String column,String filter){
        if(isConection()) return mapingAlumnos(executeQStr(getSelectQuery(column, filter)));
        return null;
    }
    
    /*
    =====================================================================
    ========================= MÉTODOS Públicos ===================================
    =====================================================================
    */
    
    /**
     * Indica si la clase se encuentra conectada a una base de datos
     * @return Estado de la conexión
     */
    public boolean isConection(){
        return (this.conn != null);
    }
    
    @Override
    public List<Alumno> obtenerTodosLosAlumnos() {
        return this.ejecutarSelect();
    }

    @Override
    public Alumno buscarAlumnoPorId(int id) {
        List<Alumno> idA = this.ejecutarSelect(keys[0], String.valueOf(id));
        if(idA==null) return null;
        Alumno a = null;
        if(idA.size()>0)a=idA.get(0);
        return a;
    }

    @Override
    public List<Alumno> buscarAlumnoPorApellido(String apellido) {
        return this.ejecutarSelect(keys[2], apellido);
    }

    @Override
    public Alumno obtenerUltimoAlumno() {
        List<Alumno> aux = this.obtenerTodosLosAlumnos();
        if(aux!=null && aux.size()>0) return aux.get(aux.size()-1);
        return null;
    }

    @Override
    public int crearAlumno(Alumno alumno) {
        return this.executeUStr(this.getInsertQuery(alumno));
    }

    @Override
    public int actualizarAlumno(int id, Alumno alumno) {
        return this.executeUStr(this.getUpdateQuery(id, alumno));
    }

    @Override
    public int borrarAlumno(int id) {
        return this.executeUStr(this.getDeleteQuery(id));
    }

    @Override
    public boolean existeTablaAlumno() {
        return existeTablaDada(tableSelect.trim());
    }

    @Override
    public boolean existeTablaDada(String table) {
        return obtenerTodasTablasBaseDatos().contains(table);
    }

    @Override
    public boolean borrarTablaAlumno() {
        executeUStr("DROP TABLE "+tableSelect);
        return !isError();
    }

    @Override
    public List<String> obtenerTodasTablasBaseDatos() {
        return mapingDataOne(executeQStr("SHOW TABLES"));
    }

    @Override
    public List<String> obtenerTodasBaseDatos() {
        return mapingDataOne(executeQStr("SHOW DATABASES"));
    }

    @Override
    public List<String> obtenerTodasColumnasTablaAlumno() {
        return obtenerTodasColumnasDadoTabla(tableSelect);
    }

    @Override
    public List<String> obtenerTodasTablasBaseDatosDada(String basedatos) {
        return mapingDataOne(executeQStr("SHOW TABLES IN " + basedatos));
    }

    @Override
    public List<String> obtenerTodasColumnasDadoTabla(String tabla) {
        return mapingDataOne(executeQStr("SHOW COLUMNS IN " + tabla));
    }

    @Override
    public String convertirTablaSqlToJson(String table) {
        return Archivo.StringToJson(mapingDataAll(executeQStr(getSelectQuery(table))));
    }

    @Override
    public Date convertirFechaStringToDateMYSQL(String fechastring) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return new Date (sdf.parse(fechastring).getTime());
        } catch (ParseException ex) {
            return null;
        }
    }
    
    public boolean nuevaTablaAlumnos(){
        String sql = 
                "CREATE TABLE "+tableSelect+" (\n" +
                "  `id` int(11) NOT NULL PRIMARY KEY,\n" +
                "  `nombre` varchar(50) NOT NULL,\n" +
                "  `apellidos` varchar(50) NOT NULL,\n" +
                "  `grupo` varchar(50) NOT NULL,\n" +
                "  `dob` date NOT NULL)";
        executeUStr(sql);
        return !isError();
    }
}
