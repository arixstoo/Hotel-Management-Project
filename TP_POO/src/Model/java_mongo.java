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

public class java_mongo {
     public static void Chambretojava(Document document, Map<Integer, Chambre> chambresMap) {
        MongoCollection collection = MongoFunctions.mongconnect("TP_Hotel","Chambres");
        if (document != null) {
            int numChambre = document.getInteger("NumChambre");
            String type = document.getString("Type");
            boolean libre = document.getBoolean("libre");

            TypeChambre typee = TypeChambre.valueOf(type);
            if (typee == TypeChambre.Individuel) {
                Chambre chambre = new Chambre(numChambre, TypeChambre.Individuel, libre);
                chambresMap.put(numChambre, chambre);
            }
            if (typee == TypeChambre.Double) {
                Chambre chambre = new Chambre(numChambre, TypeChambre.Double, libre);
                chambresMap.put(numChambre, chambre);
            }
            if (typee == TypeChambre.Suite) {
                Chambre chambre = new Chambre(numChambre, TypeChambre.Suite, libre);
                chambresMap.put(numChambre, chambre);
            }

            System.out.println("Chambre ajoutée à la Map: " + numChambre);
        } else {
            System.out.println("Document null");
        }
    }
     public static void clienttojava (Document document, Map<String, Client> clientsMap) {
        if (document != null) {
            String nom = document.getString("Nom");
            String prenom = document.getString("Prenom");
            String email = document.getString("email");
            String telephone = document.getString("Telephone");
            String username = document.getString("UserName");
            String mdp = document.getString("MotDePasse");

            Client client = new Client(nom,prenom,email,telephone,username,mdp);
            clientsMap.put(username, client);

            System.out.println("Client ajoutée à la Map: " + username);
        } else {
            System.out.println("Document null");
        }
    }
     public static void adminttojava (Document document, Map<String, Admin> adminsMap) {
        if (document != null) {
            String nom = document.getString("Nom");
            String prenom = document.getString("Prenom");
            String email = document.getString("email");
            String telephone = document.getString("Telephone");
            String username = document.getString("UserName");
            String mdp = document.getString("MotDePasse");

            Admin admin = new Admin(nom , prenom , email , telephone , username , mdp);
            adminsMap.put(username, admin);

            System.out.println("Admin ajoutée à la Map: " + username);
        } else {
            System.out.println("Document null");
        }
    }
     public static void reservationtojava(Document document, Map<String, Reservation> reservationMap) throws ParseException {
        if (document != null) {

            String username = document.getString("username");
            List<Integer> reservedRooms = document.getList("reservedRooms", Integer.class);
            String startDate = document.getString("startDate");
            String endDate = document.getString("endDate");
            double totalPrice = document.getDouble("totalPrice");
            System.out.println(startDate );

            Reservation reservation = new Reservation(username,reservedRooms,MongoFunctions.stringVersDate(startDate), MongoFunctions.stringVersDate(endDate), totalPrice);
            reservationMap.put(username, reservation);
            System.out.println("Reservation ajoutée à la Map: " + username);
        } else {
            System.out.println("Document null");
        }
    }
}