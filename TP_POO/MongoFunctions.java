package Model;

import Controller.*;
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
    /* connexion a la base de donnees */ public static MongoCollection<Document> mongconnect(String dbName, String collName) {
        String uri = "mongodb+srv://nassimhessas60:nassimhessas60@cluster0.3ye673x.mongodb.net/";
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(dbName);
        System.out.println("DataBase connected");
        MongoCollection collection = mongoDatabase.getCollection(collName);
        return collection;
    }
    /*inserer un document */ public static void insertDoc(MongoCollection collection, Document document) {
        collection.insertOne(document);
        System.out.println("Document inseré avec succés!");
    }
    /*lister les documents d'une collection */ public static void ListDocs(MongoCollection collection) {
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
    /*supprimer un document */ public static void suppDoc(MongoCollection collection, String code, Object value) {
        Document search = new Document();
        search.put(code, value);

        // Supprimer un ou plusieurs documents
        DeleteResult deleteResult = collection.deleteMany(search);
        long deletedCount = deleteResult.getDeletedCount();

        if (deletedCount > 0) {
            if (deletedCount == 1) {
                System.out.println(deletedCount + " Document supprimé avec succès ");
            } else {
                System.out.println(deletedCount + " Documents supprimés avec succès ");
            }
        } else {
            System.out.println("Aucun document trouvé correspondant à la condition.");
        }
    }
    /*modifier un document */ public static void updateDoc(MongoCollection collectionName, String field_recherche, Object valeur_or, String field_modif, Object valeur_mod) {
        collectionName.updateOne(Filters.eq(field_recherche, valeur_or), Updates.set(field_modif, valeur_mod));
        System.out.println("document modifié avec succés");
    }
}