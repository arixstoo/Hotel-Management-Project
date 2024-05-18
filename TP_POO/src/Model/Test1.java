package Mongo;

import Code.Chambre;
import Code.Client;
import Code.Reservation;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class Test1 {
    public static HashMap<String, Client> clients = new HashMap<>();
    public static HashMap<Integer, Chambre> chambres = new HashMap<>();
    public static void main(String[] args) throws ParseException {

       /* String uri = "mongodb+srv://nassimhessas60:nassimhessas60@cluster0.3ye673x.mongodb.net/";
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("TP_Hotel");
        MongoCollection collection0 = mongoDatabase.getCollection("Chambres");*/

        //System.out.println("DataBase connected");
        //Scanner scanner = new Scanner(System.in);
        String A = "nassim";
        //System.out.println("entrer le prenom");
        //Object B = scanner.next();

      /*Document document = new Document("NumChambre", 101);
        document.append("Type", "Suite");
        document.append("libre", true);
        document.append("prix", A);

        Document doc = new Document("NumChambre",100)
                .append("Type","Double")
                        .append("libre",true)
                                .append("prix","3500");

      MongoFunctions.insertDoc(collection,document);*/

       // MongoCollection collection = MongoFunctions.mongconnect("TP_Hotel", "Amins");

        ///////////////////////////////////////////////////////////////////////////////////////////
        // MongoFunctions.dropDoc(collection,"Nom","mouh");
        // MongoFunctions.updateDoc(collection,"NumChambre",300,"libre",false);
        // MongoFunctions.ListDocs(collection);
        // MongoFunctions.insertChambre(300, "Double", 3500);
        // MongoFunctions.insertClient("mouh", "sidali", "mouhsid@gmail.com", "0679889765", "simouh", "qwerty1234@!");
        // MongoFunctions.suppClient("simouh");
        // MongoFunctions.updateClient("Chemli09","UserName" , "Chemli07");

       /* DatePoo date_d = new DatePoo(12, 11, 2024);
        DatePoo date_f = new DatePoo(16, 11, 2024);


        Document document = (Document) collection.find(Filters.eq("Nom", "Chemli")).first();
        ObjectId objectId = document.getObjectId("_id");

        ArrayList <Chambre> chambre = new ArrayList();
        Chambre ch1 = new Chambre(101, TypeChambre.Double , null);
        Chambre ch2 = new Chambre(102, TypeChambre.Individuel , null);
        chambre.add(ch1);
        chambre.add(ch2);

        Client client = new Client("nassim" ,"hessas","nassim5@gmail.com", "0789898989" , "Nass" , "123456789qA@" );

        MongoFunctions.reserver(objectId,ch1,date_d,date_f,12000,1);

        HashMap<Integer, Chambre> chambres = new HashMap<>();

        MongoCollection collection0 = MongoFunctions.mongconnect("TP_Hotel","Chambres");

       // for (Object document : collection0.find()) {
         //   MongoFunctions.Chambretojava((Document) document, chambres); }

           /* Map<Integer,Chambre> chambre = new HashMap<Integer,Chambre>();
            chambre.put(101,chambres.get(101));
            chambre.put(102,chambres.get(102));*/
        //MongoFunctions.reserver("rostom", chambres.get(101), date_d, date_f, 12000);


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Créer une HashMap pour stocker les chambres

        // Parcourir la collection et convertir chaque document
       /* for (Object document : collection.find()) {
            MongoFunctions.Chambretojava((Document) document, chambres);
        }

        for (Map.Entry<Integer, Chambre> entry : chambres.entrySet()) {
            int numChambre = entry.getKey();
            Chambre chambre = entry.getValue();

            System.out.println("Chambre " + numChambre + ": ");
            System.out.println("  Type: " + chambre.getType());
            System.out.println("  Libre: " + chambre.getLibre());
            System.out.println("  Prix: " + chambre.getPrix());
        }
        // Fermer la connexion
        mongoClient.close();
*/
        ////////////////////////////////////////////////////////////////////////////////////////////////

        MongoCollection<Document> collection = MongoFunctions.mongconnect("TP_Hotel","Clients");

        // Parcourir la collection et convertir chaque document
        for (Object document : collection.find()) {
            MongoFunctions.clienttojava((Document) document, clients);

    }
        for (Map.Entry<String, Client> entry : clients.entrySet()) {
            String userName = entry.getKey();
            Client client = entry.getValue();

            System.out.println("Client : " );
            System.out.println("  Nom : " + client.getUsername());
        }



      /*  Document reservationDocument = new Document();

        // Set customer username
        String username = "AkramZa3im";
        reservationDocument.put("username", username);

        // Set list of reserved rooms
        List<Integer> reservedRooms = new ArrayList<>();
        reservedRooms.add(104);
        reservedRooms.add(103);
        reservationDocument.put("reservedRooms", reservedRooms);

        // Set start and end dates
        DatePoo startDate = new DatePoo(12 , 2, 2024); // 2023-06-29
        DatePoo endDate = new DatePoo(15 , 2 , 2024); // 2023-06-30
        Document doc = new Document();
        doc.put("dateDebut",new DatePoo(2024 , 12 , 1));

        MongoFunctions.reserveRooms(username , reservedRooms , MongoFunctions.dateVersString(startDate) , MongoFunctions.dateVersString(endDate) , 12000);*/

       /* Date startDate = new Date(12 , 2, 2024); // 2023-06-29
        Date endDate = new Date(17 , 2 , 2024); // 2023-06-30
        ArrayList<Integer> reservedRooms = new ArrayList<>();
        reservedRooms.add(108);
        reservedRooms.add(107);
        MongoFunctions.reserver("Chemli09",reservedRooms,startDate,endDate,11111,1);
        }*/

        //HashMap<String, Reservation> reservations = new HashMap<>();
        /*for (Object document : collection.find()) {
            MongoFunctions.reservationttojava((Document) document, reservations);

        System.out.println("Reservations Map:");
        for (Map.Entry<String, Reservation> entry : reservations.entrySet()) {
            String username = entry.getKey();
            Reservation reservation = entry.getValue();
            System.out.println("Username: " + username);
            System.out.println("Reserved Rooms: " + reservation.getChambre());
            System.out.println("Start Date: " + reservation.getDateDebut());
            System.out.println("End Date: " + reservation.getDateFin());
            System.out.println("Total Price: " + reservation.getTotal());
            System.out.println("-----------------------------------------");
        }
    }*/

       /* DatePoo dd = new DatePoo(12,1,2024);
        DatePoo df = new DatePoo(15,1,2024);
        ArrayList<Integer> reservedRooms = new ArrayList<>();
        reservedRooms.add(108);
        reservedRooms.add(107);
        MongoFunctions.reserveRooms("Chemli07",reservedRooms,MongoFunctions.dateVersString(dd),MongoFunctions.dateVersString(df),12000);
*/
        collection = MongoFunctions.mongconnect("TP_Hotel","Reservations");

        HashMap<String, Reservation> reservations = new HashMap<>();
        for (Document document : collection.find()) {
            MongoFunctions.reservationtojava(document, reservations);
        }

        System.out.println("Reservations Map:");
        for (Map.Entry<String, Reservation> entry : reservations.entrySet()) {
            String username = entry.getKey();
            Reservation reservation = entry.getValue();
            System.out.println("Username: " + username);
            System.out.println("Reserved Rooms: " + reservation.getChambre());
            System.out.println("Start Date: " + reservation.getDateDebut().toString());
            System.out.println("End Date: " + reservation.getDateFin().toString());
            System.out.println("Total Price: " + reservation.getTotal());
            System.out.println("-----------------------------------------");
        }
}
    }
