package conector;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 * Primer esquema de un repositorio MongoDB con gestor de conexión
 * Desarrollada para la version 3.17 del driver Mongo
 * 
 * @author Guillermo Casas Reche
 * @version 0.1
 */
public class MongoCRUD {
    
    private MongoCollection<Document> collecction;
    
    private void setConection(String host, int port, String database, String coleccion){
        MongoClient cliente = new MongoClient(host,port);
        MongoDatabase conexion = cliente.getDatabase(database);
        collecction = conexion.getCollection(coleccion);
    }

    public MongoCRUD(String host, int port, String database, String coleccion) {
        setConection(host, port, database, coleccion);
    }
    
    public void insertarDocument(Document d){
        this.collecction.insertOne(d);
    }
    
    public Document getPrimero(){
        return this.collecction.find().first();
    }
    
    public List<Document> getTodos(){
        MongoCursor<Document> datos = this.collecction.find().iterator();
        List<Document> salida = new ArrayList<>();
        while(datos.hasNext()){
            salida.add(datos.next());
        }
        return salida;
    }
}
