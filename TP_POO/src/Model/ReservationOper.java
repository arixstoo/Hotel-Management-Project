package Model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import java.util.List;

public class ReservationOper {

    public static void reserverChambres(String username, List<Integer> reservedRooms, String startDate, String endDate , double prix) {

        // Connect to MongoDB database
        MongoCollection collection = MongoFunctions.mongconnect("TP_Hotel","Reservations");

        // creer un document pour inserer les donnees de la reservation
        Document reservationDocument = new Document();

        //inserer username du client
        reservationDocument.put("username", username);

        //inserer list des chambres reservées
        reservationDocument.put("reservedRooms", reservedRooms);

        //inserer dates debut et fin
        reservationDocument.put("startDate", startDate);
        reservationDocument.put("endDate", endDate);

        //inserer le prix
        reservationDocument.put("totalPrice", prix);

        //inserer l'etat de reservation
        reservationDocument.put("réservation_confirmée",false);

        // Inserer le document de la reservation dans la collection "Reservations"
        MongoFunctions.insertDoc(collection,reservationDocument);

    }

    /* supprimer une reservation */
    public static void suppReservation(String value) {
        MongoCollection collection = MongoFunctions.mongconnect("TP_Hotel","Reservations");
        Document search = new Document();
        search.put("username", value);

        DeleteResult deleteResult = collection.deleteMany(search);
        long deletedCount = deleteResult.getDeletedCount();

        if (deletedCount > 0) {
            if (deletedCount == 1) {
                System.out.println(deletedCount + " Reservation supprimée avec succès ");
            } else {
                System.out.println(deletedCount + " Reservations supprimées avec succès ");
            }
        } else {
            System.out.println("Aucune reservation trouvée.");
        }
    }

}
