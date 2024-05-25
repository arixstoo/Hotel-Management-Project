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

import java.text.ParseException;
import java.util.*;

public class MongoFunctions {
    //done
    public static MongoCollection<Document> mongconnect(String dbName, String collName) {
        String uri = "mongodb+srv://nassimhessas60:nassimhessas60@cluster0.3ye673x.mongodb.net/";
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(dbName);
        System.out.println("DataBase connected");
        MongoCollection collection = mongoDatabase.getCollection(collName);
        return collection;
    }
    //done
    public static void insertDoc(MongoCollection collection, Document document) {
        collection.insertOne(document);
        System.out.println("Document inserted successfully!");
    }

    public static void suppDoc(MongoCollection collection, String code, Object value) {
        Document search = new Document();
        search.put(code, value);

        // Supprimer un ou plusieurs documents
        DeleteResult supp = collection.deleteMany(search);
        long deletedCount = supp.getDeletedCount();

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
    //done
    public static void updateDoc(MongoCollection collectionName, String field_recherche, Object valeur_or, String field_modif, Object valeur_mod) {
        collectionName.updateOne(Filters.eq(field_recherche, valeur_or), Updates.set(field_modif, valeur_mod));
        System.out.println("document modifié successfully");
    }
    //done
    public static void ListDocs(MongoCollection collection) {
        long compteur = collection.countDocuments();
        if (compteur != 0) {
            // Retrieve and print all documents
            System.out.println("\nRetrieving all documents:");
            for (Object document : collection.find()) {
                System.out.println(document.toString());
            }
            System.out.println("\nNombre de documents dans la collection: " + collection.getNamespace() + " : " + compteur);
        } else {
            System.out.println("\nLa collection est vide");
        }
    }
    //done
    public static void updateClient(Object valeur_rech, String field_modif, Object valeur_mod) {
        MongoCollection collection = MongoFunctions.mongconnect("TP_Hotel", "Clients");

        // Recherche du document correspondant
        Document recherche = new Document("UserName", valeur_rech);
        long cmp = collection.countDocuments(recherche);

        if (cmp > 0) {
            // Mise à jour du document trouvé
            collection.updateOne(recherche, Updates.set(field_modif, valeur_mod));
            System.out.println("Client modifié avec succès");
        } else {
            // Aucun document trouvé correspondant à la valeur de recherche
            System.out.println("Aucun client trouvé avec le nom d'utilisateur  : " + valeur_rech);
        }
    }

}

// charger les collections dans les maps
// insert document -> insert clients reservations chambres
// modifier reservation chambre
