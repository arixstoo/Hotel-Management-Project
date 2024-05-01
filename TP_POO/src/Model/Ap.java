package Mongo;

import Code.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.*;

public class MongoFunctions {
    public static MongoCollection<Document> mongconnect(String dbName, String collName) {
        String uri = "mongodb+srv://nassimhessas60:nassimhessas60@cluster0.3ye673x.mongodb.net/";
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(dbName);
        System.out.println("DataBase connected");
        MongoCollection collection = mongoDatabase.getCollection(collName);
        return collection;
    }

    public static void insertDoc(MongoCollection collection, Document document) {
        collection.insertOne(document);
        System.out.println("Document inseré avec succés!");
    }
    public static void ListDocs(MongoCollection collection) {
        long compteur = collection.countDocuments();
        if (compteur != 0) {
            // Lister les documents
            System.out.println(collection +":");
            for (Object document : collection.find()) {
                System.out.println(document.toString());
            }
            System.out.println("\nNombre de documents dans la collection: " + collection.getNamespace() + " : " + compteur);
        } else {
            System.out.println("\nLa collection est vide");
        }
    }
}