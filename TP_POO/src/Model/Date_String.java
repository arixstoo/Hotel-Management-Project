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

public class Date_String {

    // Fonction pour convertir la date en chaîne de caractères
    public static String dateToString(DatePoo datee) {
        return datee.getJour() + "/" + datee.getMois() + "/" + datee.getAnnee();
    }

    // Fonction pour convertir une chaîne de caractères en date
    public static DatePoo stringToDate(String dateEnString) throws ParseException {
        String[] parties = dateEnString.split("/");
        int jour = Integer.parseInt(parties[0]);
        int mois = Integer.parseInt(parties[1]);
        int annee = Integer.parseInt(parties[2]);
        return new DatePoo(jour, mois, annee);
    }
}