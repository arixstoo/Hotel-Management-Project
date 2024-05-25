package Model;

import Controller.*;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.awt.*;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

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
            Boolean réservation_rejetée = document.getBoolean("réservation_rejetée");

            Client client = new Client(nom,prenom,email,telephone,username,mdp,réservation_rejetée);
            clientsMap.put(username, client);

            System.out.println("Client ajoutée à la Map: " + username);
        } else {
            System.out.println("Document null");
        }
    }

    public static void admintojava (Document document, Map<String, Admin> adminsMap) {
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

    public static void reservationtojava(Document document, Map<String, Reservation> reservationMap) {
        if (document != null) {
            String username = document.getString("username");
            List<Integer> reservedRooms = document.getList("reservedRooms", Integer.class);
            String startDate = document.getString("startDate");
            String endDate = document.getString("endDate");
            double totalPrice = document.getDouble("totalPrice");
            Boolean réservation_confirmée = document.getBoolean("réservation_confirmée");
            Reservation reservation = new Reservation(username,reservedRooms,Controller.Date.stringVersDate(startDate), Controller.Date.stringVersDate(endDate), totalPrice, réservation_confirmée);
            reservationMap.put(username, reservation);
            System.out.println("Reservation ajoutée à la Map: " + username);
        } else {
            System.out.println("Document null");
        }
    }

}
