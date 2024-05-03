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

public class ClientOper {

    /* inserer un client */ public static void insertClient(String nom_client, String prenom_client, String e_mail, String telephone, String userName, String motDePasse) {

        MongoCollection collection = MongoFunctions.mongconnect("TP_Hotel","Clients");

        Document document = new Document("Nom", nom_client)
                .append("Prenom", prenom_client)
                .append("email", e_mail)
                .append("Telephone", telephone)
                .append("UserName", userName)
                .append("MotDePasse", motDePasse)
                .append("réservation_rejetée", null);

        collection.insertOne(document);
        System.out.println("Client ajouté avec succés");
    }

    /* supprimer un client */ public static void suppClient(Object value) {

        MongoCollection collection = MongoFunctions.mongconnect("TP_Hotel","Clients");
        Document rec = new Document();
        rec.put("UserName", value);

        DeleteResult supp = collection.deleteMany(rec);
        long deletedCount = supp.getDeletedCount();

        if (deletedCount > 0) {
            if (deletedCount == 1) {
                System.out.println(deletedCount + " Client supprimé avec succès ");
            } else {
                System.out.println(deletedCount + " Clients supprimés avec succès ");
            }
        } else {
            System.out.println("Aucun client trouvé.");
        }
    }
