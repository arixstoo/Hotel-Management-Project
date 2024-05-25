package Controller;

import Vue.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class Main {
    // Map pour stocker les chambres
    public static Map<Integer, Chambre> chambres = new HashMap<Integer, Chambre>();

    // Map pour stocker les réservations
    public static Map<String, Reservation> reservations = new HashMap<String, Reservation>();

    // Map pour stocker les clients (the key is username pour faciliter la recherche)
    public static Map<String, Client> clients = new HashMap<String, Client>();

    // Map pour stocker les admins
    public static Map<String, Admin> admins = new HashMap<String, Admin>();

    // Pour la date de aujourd'hui
    public static Date aujourdhui = new Date(25,05,2024);

    public static void charger (){
        String uri = "mongodb+srv://nassimhessas60:nassimhessas60@cluster0.3ye673x.mongodb.net/";
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("TP_Hotel");

        MongoCollection collection = mongoDatabase.getCollection("Chambres");
        for (Object document : collection.find()) {
            Model.java_mongo.Chambretojava((Document) document, chambres);
        }

        collection = mongoDatabase.getCollection("Clients");
        // Parcourir la collection et convertir chaque document
        for (Object document : collection.find()) {
            Model.java_mongo.clienttojava((Document) document, clients);
        }

        collection = mongoDatabase.getCollection("Admins");
        // Parcourir la collection et convertir chaque document
        for (Object document : collection.find()) {
            Model.java_mongo.admintojava((Document) document, admins);
        }

        collection = mongoDatabase.getCollection("Reservations");
        // Parcourir la collection et convertir chaque document
        for (Object document : collection.find()) {
            Model.java_mongo.reservationtojava((Document) document, reservations);
        }
    }
    public static void main(String[] args) throws Exception {
        charger();
        //new Client_view("Chemli09").setVisible(true);
        new Client_Login().setVisible(true);
        //new Admin_Login().setVisible(true);
    }
}
