package Model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

public class ChambreOper {


    /* inserer une chambre */
    public static void insertChambre(int numChambre, String type, int prix) {
        MongoCollection collection =MongoFunctions.mongconnect("TP_Hotel","Chambres");

        Document document = new Document("NumChambre", numChambre)
                .append("Type", type)
                .append("libre", true)
                .append("prix", prix);

        collection.insertOne(document);
        System.out.println("Chambre crée avec succés");
    }

    /* supprimer une chambre */
    public static void suppChambre(Object value) {
        MongoCollection collection = MongoFunctions.mongconnect("TP_Hotel","Chambres");
        Document search = new Document();
        search.put("NumChambre", value);

        DeleteResult deleteResult = collection.deleteMany(search);
        long deletedCmp = deleteResult.getDeletedCount();

        if (deletedCmp > 0) {
            if (deletedCmp == 1) {
                System.out.println(deletedCmp + " Chambre supprimée avec succès ");
            } else {
                System.out.println(deletedCmp + " Chambres supprimées avec succès ");
            }
        } else {
            System.out.println("Aucune chambre trouvée.");
        }
    }

}
