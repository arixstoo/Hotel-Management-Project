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

public class ChambreOper {


    /* inserer une chambre */ public static void insertChambre(int numChambre, String type, int prix) {
        MongoCollection collection =MongoFunctions.mongconnect("TP_Hotel","Chambres");

        Document document = new Document("NumChambre", numChambre)
                .append("Type", type)
                .append("libre", true)
                .append("prix", prix);

        collection.insertOne(document);
        System.out.println("Chambre crée avec succés");
    }
    /* supprimer une chambre */ public static void suppChambre(Object value) {
        MongoCollection collection = MongoFunctions.mongconnect("TP_Hotel","Chamres");
        Document search = new Document();
        search.put("NumChambre", value);

        DeleteResult deleteResult = collection.deleteMany(search);
        long deletedCmp = deleteResult.getDeletedCount();

        if (deletedCmp > 0) {
            if (deletedCount == 1) {
                System.out.println(deletedCmp + " Chambre supprimée avec succès ");
            } else {
                System.out.println(deletedCmp + " Chambres supprimées avec succès ");
            }
        } else {
            System.out.println("Aucune chambre trouvée.");
        }
    }
}