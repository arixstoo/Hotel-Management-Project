package Controller;

import Controller.Exceptions.Exception_text;
import Model.MongoFunctions;
import com.mongodb.client.MongoCollection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.util.Map.Entry;

public class Admin extends Personne {

    static Scanner sc = new Scanner(System.in);
    private String username_admin;
    private String motDePasse_admin;
    private static String code_admin="2024"; //code unique qu'aux admins

    public String getUsername_admin() {
        return username_admin;
    }

    //SIGN UP BSH MANUALLY 
    //Constructeur
    public Admin(String nom, String prenom, String email, String telephone, String username, String motDePasse){
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
        this.telephone=telephone;
        this.username_admin=username;
        this.motDePasse_admin=motDePasse;
        Admin.code_admin="2024";
        Main.admins.put(this.username_admin, this);
    }

    //Fonction de login
    public static Boolean Login(String username, String motDePasse, String code) throws Exception_text{
        Boolean validé=false;
        if(Main.admins.containsKey(username)){
            Admin temp = Main.admins.get(username);
            if(Objects.equals(temp.motDePasse_admin, motDePasse)){
                return validé=true;
            }
            else{
                throw new Exception_text("Mot de passe incorect, réesseyez.");
            }
        }
        else{
            throw new Exception_text("Nom d'utilisateur incorect, réesseyez.");
        }
    }

    public static List<Object[]> accesRéservation(){
        List<Object[]> data = new ArrayList<>();
        for (Map.Entry<String, Reservation> entry : Main.reservations.entrySet()) {
            Reservation reservation = entry.getValue();
            if(!reservation.getRéservation_confirmée()){
                StringBuilder rooms = new StringBuilder();
                for (int i = 0; i < reservation.getChambre().size(); i++) {
                    ArrayList<Chambre> chambres = reservation.getChambre();
                    rooms.append(chambres.get(i).numéroChambre).append(" de type ").append(Main.chambres.get(chambres.get(i).numéroChambre).getType()).append("\n");
                }
                String dateDebut = Controller.Date.dateVersString(reservation.getDateDebut());
                String dateFin = Controller.Date.dateVersString(reservation.getDateFin());
                data.add(new Object[]{
                        Main.clients.get(reservation.getClient().getUsername()).getNom() + " " + Main.clients.get(reservation.getClient().getUsername()).getPrenom(),
                        dateDebut,
                        dateFin,
                        reservation.getTotal()+" DA",
                        rooms.toString().trim(),
                });
            }
        }
        return data;
    }

    public static void accepterRéservation(DefaultTableModel model){
        for (int i = 0; i < model.getRowCount(); i++) {
            if(model.getValueAt(i, 5) !=null){
                if ((Boolean) model.getValueAt(i, 5)) {
                    int h=0;
                    System.out.println("Réservation acceptée : " + i);
                    Iterator<Map.Entry<String, Reservation>> it = Main.reservations.entrySet().iterator();
                    Map.Entry<String,Reservation> actuel = it.next();
                    while(h<i){h++;actuel = it.next();}
                    Main.clients.get(actuel.getValue().getClient().getUsername()).setRéservation_rejetée(false);
                    MongoCollection collection = MongoFunctions.mongconnect("TP_Hotel", "Clients");
                    MongoFunctions.updateDoc(collection,"UserName",actuel.getValue().getClient().getUsername(),"réservation_rejetée",false);

                    Main.reservations.get(actuel.getValue().getClient().getUsername()).setRéservation_confirmée(true);
                    collection = MongoFunctions.mongconnect("TP_Hotel", "Reservations");
                    MongoFunctions.updateDoc(collection,"username",actuel.getValue().getClient().getUsername(),"réservation_confirmée",true);
                }
            }
        }
    }

    public static void rejeterRéservation(DefaultTableModel model){
        for (int i = 0; i < model.getRowCount(); i++) {
            if(model.getValueAt(i, 5) !=null){
                if ((Boolean) model.getValueAt(i, 5)) {
                    int h=0;
                    System.out.println("Réservation rejetée : " + i);
                    Iterator<Map.Entry<String, Reservation>> it = Main.reservations.entrySet().iterator();
                    Map.Entry<String,Reservation> actuel = it.next();
                    while(h<i){h++;actuel = it.next();;}
                    String usernameClient = actuel.getValue().getClient().getUsername();
                    MongoCollection collection = MongoFunctions.mongconnect("TP_Hotel", "Chambres");
                    for(int j=0;j<Main.reservations.get(usernameClient).getChambre().size();j++){
                        Main.reservations.get(usernameClient).getChambre().get(j).setLibre(true);
                        MongoFunctions.updateDoc(collection,"NumChambre",Main.reservations.get(usernameClient).getChambre().get(j).numéroChambre,"libre",true);
                    }
                    Main.clients.get(actuel.getValue().getClient().getUsername()).setRéservation_rejetée(false);
                    collection = MongoFunctions.mongconnect("TP_Hotel", "Clients");
                    MongoFunctions.updateDoc(collection,"UserName",actuel.getValue().getClient().getUsername(),"réservation_rejetée",true);
                    Model.ReservationOper.suppReservation(usernameClient);
                }
            }
        }
    }

    public static void ajouterChambre(){
        try {
            String nbrChambre="";
            try{
                nbrChambre = JOptionPane.showInputDialog("Veuillez entrer le numéro de la chambre que vous voulez ajouter (ça doit être unique) : ");
                if(!nbrChambre.matches("[0-9]+")){
                    throw new Exception_text("Le numéro de la chambre doit être un entier.");
                }
            } catch(Exception_text ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            int numéroChambre = Integer.parseInt(nbrChambre);

            if(Main.chambres.containsKey(numéroChambre)==true){
                throw new Exception_text("La chambre "+numéroChambre+" existe déjà.");
            } else{
                String choixValide = JOptionPane.showInputDialog("Veuillez entrer le type de la nouvelle chambre que vous voulez ajouter numéro "+ numéroChambre+" :");
                Chambre temp_chambre;
                Boolean exception=true;
                TypeChambre type_temp=null;
                try {
                    type_temp = TypeChambre.valueOf(choixValide);
                } catch (IllegalArgumentException ex) {
                    exception=false;
                    JOptionPane.showMessageDialog(null, "Choix invalide. ce type n'existe pas.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                if(exception==true){
                    temp_chambre = new Chambre(numéroChambre,type_temp,true);
                    Main.chambres.put(numéroChambre, temp_chambre);
                    Model.ChambreOper.insertChambre(temp_chambre.numéroChambre, String.valueOf(temp_chambre.type),temp_chambre.getPrix());
                    JOptionPane.showMessageDialog(null, "Ajout réussi !", "Ajout réussi", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception_text ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void modifierChambre(){
        try {
            //vérifier si il y a des chambres libres ou bien toutes les chambres sont prises
            Boolean libre = false;

            if(!Main.chambres.isEmpty()){
                Iterator<Map.Entry<Integer, Chambre>> iterator = Main.chambres.entrySet().iterator();
                do{
                    Map.Entry<Integer, Chambre> actuel = iterator.next();
                    Chambre temp_chambre = actuel.getValue();
                    if(temp_chambre.getLibre()==true){
                        libre = true;
                    }
                }while(iterator.hasNext() && libre==false);
            }

            if(libre==true) {
                String nbrChambre="";
                try{
                    nbrChambre = JOptionPane.showInputDialog("Veuillez entrer le numéro de la chambre que vous voulez mofifier (ça doit exister) : ");
                    if(!nbrChambre.matches("[0-9]+")){
                        throw new Exception_text("Le numéro de la chambre doit être un entier.");
                    }
                } catch(Exception_text ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                int numéroChambre = Integer.parseInt(nbrChambre);
                Chambre temp_chambre;

                if(Main.chambres.containsKey(numéroChambre)!=true){
                    throw new Exception_text("La chambre "+numéroChambre+" n'existe pas. Vous ne pouvez rien modifier.");
                } else{
                    temp_chambre = Main.chambres.get(numéroChambre);
                    if(!temp_chambre.getLibre()){
                        throw new Exception_text("La chambre n'est pas libre. Vous ne pouvez rien modifier.");
                    }
                }
                temp_chambre = Main.chambres.get(numéroChambre);
                String choixValide = JOptionPane.showInputDialog("L'ancien type de la chambre "+ numéroChambre +" est : "+ temp_chambre.type+"\nVeuillez entrer le nouveau type de la chambre que vous voulez modifier (ça doit exister) : ");
                TypeChambre type_temp=null;
                try {
                    type_temp = TypeChambre.valueOf(choixValide);
                } catch (IllegalArgumentException ex) {}
                MongoCollection collection = MongoFunctions.mongconnect("TP_Hotel", "Chambres");
                if(temp_chambre.type==TypeChambre.Individuel){
                    if(type_temp==TypeChambre.Double){
                        temp_chambre.type = TypeChambre.Double;
                        temp_chambre.setPrix(3500);
                        MongoFunctions.updateDoc(collection,"NumChambre",temp_chambre.numéroChambre,"Type","Double");
                        MongoFunctions.updateDoc(collection,"NumChambre",temp_chambre.numéroChambre,"prix",temp_chambre.getPrix());
                        JOptionPane.showMessageDialog(null, "Modification réussi", "Modification réussi", JOptionPane.INFORMATION_MESSAGE);
                    } else if(type_temp==TypeChambre.Suite){
                        temp_chambre.type = TypeChambre.Suite;
                        temp_chambre.setPrix(5500);
                        MongoFunctions.updateDoc(collection,"NumChambre",temp_chambre.numéroChambre,"Type","Suite");
                        MongoFunctions.updateDoc(collection,"NumChambre",temp_chambre.numéroChambre,"prix",temp_chambre.getPrix());
                        JOptionPane.showMessageDialog(null, "Modification réussi", "Modification réussi", JOptionPane.INFORMATION_MESSAGE);
                    } else{
                        throw new Exception_text("Choix invalide. Vous ne pouvez pas entrer un type autre que 'Double' ou 'Suite' pour la modification de la chambre "+temp_chambre.numéroChambre);
                    }
                }
                else if(temp_chambre.type==TypeChambre.Double){
                    if(type_temp==TypeChambre.Individuel){
                        temp_chambre.type = TypeChambre.Individuel;
                        temp_chambre.setPrix(2000);
                        MongoFunctions.updateDoc(collection,"NumChambre",temp_chambre.numéroChambre,"Type","Individuel");
                        MongoFunctions.updateDoc(collection,"NumChambre",temp_chambre.numéroChambre,"prix",temp_chambre.getPrix());
                        JOptionPane.showMessageDialog(null, "Modification réussi", "Modification réussi", JOptionPane.INFORMATION_MESSAGE);
                    } else if(type_temp==TypeChambre.Suite){
                        temp_chambre.type = TypeChambre.Suite;
                        temp_chambre.setPrix(5500);
                        MongoFunctions.updateDoc(collection,"NumChambre",temp_chambre.numéroChambre,"Type","Suite");
                        MongoFunctions.updateDoc(collection,"NumChambre",temp_chambre.numéroChambre,"prix",temp_chambre.getPrix());
                        JOptionPane.showMessageDialog(null, "Modification réussi", "Modification réussi", JOptionPane.INFORMATION_MESSAGE);
                    } else{
                        throw new Exception_text("Choix invalide. Vous ne pouvez pas entrer un type autre que 'Individuel' ou 'Suite' pour la modification de la chambre "+temp_chambre.numéroChambre);
                    }
                }
                else if(temp_chambre.type==TypeChambre.Suite){
                    if(type_temp==TypeChambre.Double){
                        temp_chambre.type = TypeChambre.Double;
                        temp_chambre.setPrix(3500);
                        MongoFunctions.updateDoc(collection,"NumChambre",temp_chambre.numéroChambre,"Type","Double");
                        MongoFunctions.updateDoc(collection,"NumChambre",temp_chambre.numéroChambre,"prix",temp_chambre.getPrix());
                        JOptionPane.showMessageDialog(null, "Modification réussi", "Modification réussi", JOptionPane.INFORMATION_MESSAGE);
                    } else if(type_temp==TypeChambre.Individuel){
                        temp_chambre.type = TypeChambre.Individuel;
                        temp_chambre.setPrix(2000);
                        MongoFunctions.updateDoc(collection,"NumChambre",temp_chambre.numéroChambre,"Type","Individuel");
                        MongoFunctions.updateDoc(collection,"NumChambre",temp_chambre.numéroChambre,"prix",temp_chambre.getPrix());
                        JOptionPane.showMessageDialog(null, "Modification réussi", "Modification réussi", JOptionPane.INFORMATION_MESSAGE);
                    } else{
                        throw new Exception_text("Choix invalide. Vous ne pouvez pas entrer un type autre que 'Individuel' ou 'Double'  pour la modification de la chambre "+temp_chambre.numéroChambre);
                    }
                } else{
                    throw new Exception_text("Choix invalide, type de chambre n'existe pas");
                }
                //JOptionPane.showMessageDialog(null, "Veuillez choisir un des choix :\n1-\n2-\n3-");
            } else{
                throw new Exception_text("Aucune chambre n'est libre pour la modifier");
            }
        } catch (Exception_text ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void supprimerChambre(){
        try {
            //vérifier si il y a des chambres libres ou bien toutes les chambres sont prises
            Boolean libre = false;

            if(!Main.chambres.isEmpty()){
                Iterator<Map.Entry<Integer, Chambre>> iterator = Main.chambres.entrySet().iterator();
                do{
                    Map.Entry<Integer, Chambre> actuel = iterator.next();
                    Chambre temp_chambre = actuel.getValue();
                    if(temp_chambre.getLibre()==true){
                        libre = true;
                    }
                }while(iterator.hasNext() && libre==false);
            }

            if(libre==true) {
                String nbrChambre="";
                try{
                    nbrChambre = JOptionPane.showInputDialog("Veuillez entrer le numéro de la chambre que vous voulez supprimer (ça doit exister) : ");
                    if(!nbrChambre.matches("[0-9]+")){
                        throw new Exception_text("Le numéro de la chambre doit être un entier.");
                    }
                } catch(Exception_text ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                int numéroChambre = Integer.parseInt(nbrChambre);
                Chambre temp_chambre;

                if(Main.chambres.containsKey(numéroChambre)!=true){
                    throw new Exception_text("La chambre "+numéroChambre+" n'existe pas. Vous ne pouvez la supprimer.");
                } else{
                    temp_chambre = Main.chambres.get(numéroChambre);
                    if(!temp_chambre.getLibre()){
                        throw new Exception_text("La chambre n'est pas libre. Vous ne pouvez pas la supprimer.");
                    }
                }
                Main.chambres.remove(numéroChambre);
                Model.ChambreOper.suppChambre(numéroChambre);
                JOptionPane.showMessageDialog(null, "Suppression réussie !", "Suppression réussie", JOptionPane.INFORMATION_MESSAGE);
            } else{
                throw new Exception_text("Aucune chambre n'est libre pour la supprimer");
            }
        } catch (Exception_text ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

}
