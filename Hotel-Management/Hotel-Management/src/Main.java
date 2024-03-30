import java.util.HashMap;
import java.util.Map;

public class Main {
    // Map pour stocker les chambres
    static Map<Integer, Chambre> chambres = new HashMap<>();

    // Map pour stocker les réservations
    static Map<Integer, Reservation> reservations = new HashMap<>();

    // Map pour stocker les clients (the key is username pour faciliter la recherche)
    static Map<String, Client> clients = new HashMap<>();

    // Map pour stocker les admins
    static Map<String, Admin> admins = new HashMap<>();
    public static void main(String[] args) throws Exception {
        Hotel hotel = new Hotel("Said","Alger");
        hotel.afficher();
        
    }
}
