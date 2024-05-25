package Controller;

import Controller.Exceptions.Exception_text;
import Controller.Exceptions.isValidPassword;
import Model.MongoFunctions;
import Vue.Client_view;
import com.mongodb.client.MongoCollection;

import javax.swing.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Personne{
    protected String nom;
    protected String prenom;
    protected String email;
    protected String telephone;
}
public class Client extends Personne {
    Scanner sc = new Scanner(System.in);
    private String username;
    private String motDePasse;
    private Boolean réservation_rejetée=null;

    public Boolean getRéservation_rejetée(){return this.réservation_rejetée;}
    public void setRéservation_rejetée(Boolean bool){this.réservation_rejetée = bool;}

    public String getUsername() {
        return username;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public Client(String nom, String prenom, String email, String telephone, String username, String motDePasse, Boolean réservation_rejetée){
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
        this.telephone=telephone;
        this.username=username;
        this.motDePasse=motDePasse;
        this.réservation_rejetée=réservation_rejetée;
    }

    //SIGN UP
    public static Boolean ClientSignUp(String nom, String prenom, String email, String telephone, String username, String motDePasse) throws Exception_text{
        Boolean validé = false;

        if(nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || telephone.isEmpty() || username.isEmpty() || motDePasse.isEmpty()){
            throw new Exception_text("Les informations ne sont pas complètes.");
        }

        //verifier l'email
        if(!isValidEmail(email)) {
            throw new Exception_text("L'adresse email est invalide (ça doit contenir @).");
        }

        if (!telephone.matches("[0-9]+")) {
            throw new Exception_text("Le numéro de téléphone n'est pas valide.");
        }
        //verifier le telephone
        if(telephone.length() != 10){
            throw new Exception_text("Le numéro de téléphone est invalide (ça doit contenir 10 caractères).");
        }
        else if(!telephone.startsWith("05") && !telephone.startsWith("06") && !telephone.startsWith("07")){
            throw new Exception_text("Le numéro de téléphone est invalide (ça doit commencé avec 05 , 06 ou bien 07).");
        }

        //verifier l'unicité du username
        if(Main.clients.containsKey(username)){
            throw new Exception_text("Le username est invalide (ce username : "+username+" est déjà utilisé).");
        }

        //verifier le mot de passe
        char[] passwordd = motDePasse.toCharArray();
        new isValidPassword(passwordd);
        motDePasse = String.valueOf(passwordd);

        //inserer le client dans la map et dans la bdd
        Client client = new Client(nom, prenom, email, telephone, username, motDePasse,null);
        Main.clients.put(username, client);
        Model.ClientOper.insertClient(nom , prenom , email , telephone , username , motDePasse);
        return validé=true;
    }
    static boolean isValidEmail(String email) {
        return email.contains("@");
    }
    public static char[] enterPassword() {
        return System.console().readPassword();
    }


    //LOGIN
    public static Boolean Login(String username, String motDePasse) throws Exception_text{
        Boolean validé=false;
        if(Main.clients.containsKey(username)){
            Client temp = Main.clients.get(username);
            if(Objects.equals(temp.motDePasse, motDePasse)){
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


    public static boolean isValidDate(String dateStr) {
        // Regular expression for the date format "dd/MM/yyyy"
        String datePattern = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([0-9]{4})$";
        Pattern pattern = Pattern.compile(datePattern);
        Matcher matcher = pattern.matcher(dateStr);

        if (!matcher.matches()) {
            return false;
        }

        return true;
    }

    public static void réserverChambre(String usernameClient, String Debut, String Fin, ArrayList<Chambre> chambres, int oper) throws Exception_text {
        if((Debut.isEmpty() || Fin.isEmpty())==true){
            throw new Exception_text("Choisissez les dates de votre réservation !");
        }
        if(!isValidDate(Debut) || !isValidDate(Fin)){
            throw new Exception_text("Suivez le format de la date (JJ/MM/AAAA)");
        }
        Date dateDebut = Date.stringVersDate(Debut);
        Date dateFin = Date.stringVersDate(Fin);

        Date.dateDebut_valide(dateDebut,Main.aujourdhui);
        Date.dateFin_valide(dateFin,dateDebut);
        if(chambres.isEmpty()){
            throw new Exception_text("Choisissez des chambres pour votre réservation !");
        }
        int durée = Date.nombreJours(dateDebut, dateFin);
        int p=0, prix=0;
        do{
            prix+=chambres.get(p).getPrix();
            p++;
        }while(p<chambres.size());
        System.out.println(prix);
        Double total = (double) (durée * prix);
        int i = JOptionPane.showConfirmDialog(null,"Le total de votre réservation est : "+total+" DA. Vous confirmer votre réservation ?","Confirmation de réservation",JOptionPane.YES_NO_OPTION);
        if(i==0){
            MongoCollection collection = MongoFunctions.mongconnect("TP_Hotel", "Chambres");
            for(int j=0;j<chambres.size();j++){
                Main.chambres.get(chambres.get(j).numéroChambre).setLibre(false);
                MongoFunctions.updateDoc(collection,"NumChambre",Main.chambres.get(chambres.get(j).numéroChambre).numéroChambre,"libre",false);
            }
            String start = Date.dateVersString(dateDebut);
            String end = Date.dateVersString(dateFin);

            ArrayList<Integer> chambress = new ArrayList<>();
            for(int y=0;y< chambres.size();y++){
                chambress.add(chambres.get(y).numéroChambre);
            }
            Model.ReservationOper.reserverChambres(usernameClient, chambress, start, end, total);
            if(oper==1){
                Client_view.reservationModifiee[0] = true;
            }
            JOptionPane.showMessageDialog(null,"Votre réservation est bien enregistrée, bienvenue chez nous !","Réservation avec succès",JOptionPane.INFORMATION_MESSAGE);
            Client_view.reservationAnnulee[0] = false;
            Client_view.reservationModifiee[0] = false;
        } else if(oper==0){
            JOptionPane.showMessageDialog(null,"On espère vous voir parmis nous un jour !");
        }
    }

    public static void modifierRéservation(String usernameClient) throws Exception_text {
        int i = JOptionPane.showConfirmDialog(null,"Etes vous sûr de vouloir modifier votre reservation ?");
        if(i!=0){
            JOptionPane.showMessageDialog(null,"Nous sommes ravis d'avoir entendus ça, nous vous verrons très prochainement !","Merci !",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            String choix = JOptionPane.showInputDialog("Choisissez votre modification : \n1- Changer les dates de votre réservation en gardant la/les même/s chambre/s?\n2- Changer les chambres de votre réservation ? \n3- Refaire une autre réservation carrément et supprimer l'ancienne ?");
            if(choix.equals("1")){
                Controller.Date date_Debut = Controller.Date.Date_Debut(Main.aujourdhui);
                Controller.Date date_Fin = Controller.Date.Date_Fin(date_Debut);
                int durée = Date.nombreJours(date_Debut, date_Fin);
                int p=0, prix=0;
                do{
                    prix+=Main.reservations.get(usernameClient).getChambre().get(p).getPrix();
                    p++;
                }while(p<Main.reservations.get(usernameClient).getChambre().size());
                System.out.println(prix);
                Double total = (double) (durée * prix);

                JOptionPane.showMessageDialog(null,"Le total de votre réservation est à : "+total+" DA","Attention",JOptionPane.WARNING_MESSAGE);

                i = JOptionPane.showConfirmDialog(null,"Vous confirmez la modification de votre reservation ?");
                if(i!=0){
                    JOptionPane.showMessageDialog(null,"Nous sommes ravis d'avoir entendus ça, votre réservation est maintenue dans son état initial !","Merci !",JOptionPane.INFORMATION_MESSAGE);
                } else{
                    Main.reservations.get(usernameClient).setDateDebut(date_Debut);
                    Main.reservations.get(usernameClient).setDateFin(date_Fin);
                    Main.reservations.get(usernameClient).setTotal(total);
                    MongoCollection collection = MongoFunctions.mongconnect("TP_Hotel", "Reservations");
                    MongoFunctions.updateDoc(collection,"username",usernameClient,"startDate",Controller.Date.dateVersString(date_Debut));
                    MongoFunctions.updateDoc(collection,"username",usernameClient,"endDate",Controller.Date.dateVersString(date_Fin));
                    MongoFunctions.updateDoc(collection,"username",usernameClient,"totalPrice",total);

                    JOptionPane.showMessageDialog(null,"Merci de nous avoir fait confiance une autre fois ! Votre modification a été effectué avec succès ! \nVeuillez attendre la confirmation de votre réservation ces jours là.","Merci !",JOptionPane.INFORMATION_MESSAGE);
                    Client_view.reservationModifiee[0] = true;
                }

            } else if (choix.equals("2")) {
                ArrayList<Integer> chambre_abondonnées = new ArrayList<Integer>();
                int p=0;
                do{
                    Main.reservations.get(usernameClient).getChambre().get(p).setLibre(true);
                    chambre_abondonnées.add(Main.reservations.get(usernameClient).getChambre().get(p).numéroChambre);
                    p++;
                }while(p<Main.reservations.get(usernameClient).getChambre().size());

                String affichage="";
                Iterator<Map.Entry<Integer, Chambre>> iterator = Main.chambres.entrySet().iterator();
                ArrayList<Integer> list = new ArrayList<Integer>();
                while(iterator.hasNext()){
                    Map.Entry<Integer, Chambre> actuel = iterator.next();
                    Chambre temp_chambre = actuel.getValue();
                    if(temp_chambre.getLibre()==true){
                        affichage+="\nLa chambre numéro : "+temp_chambre.numéroChambre+" de type : "+temp_chambre.type+".";
                        //pour garder les numeros des chambres libres seulement
                        list.add(temp_chambre.numéroChambre);
                    }
                }
                Boolean fini=false;
                ArrayList<Integer> chambre_réservées = new ArrayList<Integer>();
                JOptionPane.showMessageDialog(null,"Nous avons annuler les anciennes chambres de ta réservation.","Modification !",JOptionPane.INFORMATION_MESSAGE);
                do {
                    String chambre = JOptionPane.showInputDialog("Veuillez Choisir parmis ces chambre libres les quelles vous voulez réserver : "+affichage);
                    if(!chambre.matches("[0-9]+")){
                        throw new Exception_text("Vous ne pouvez pas choisir une autre chambre qu'en introduisant son numéro.");
                    }
                    int nbr = Integer.parseInt(chambre);
                    if(!list.contains(nbr)){
                        p=0;
                        do{
                            Main.reservations.get(usernameClient).getChambre().get(p).setLibre(false);
                            p++;
                        }while(p<Main.reservations.get(usernameClient).getChambre().size());
                        throw new Exception_text("Vous ne pouvez pas choisir une autre chambre que les chambres libres.");
                    }
                    list.remove(list.indexOf(nbr));
                    chambre_réservées.add(Main.chambres.get(nbr).numéroChambre);
                    int j = JOptionPane.showConfirmDialog(null,"Voulez vous réserver d'autres chambres ?");
                    if(j!=0){
                        fini = true;
                    } else{
                        fini=false;
                        affichage="";
                        int y=0;
                        while(y<list.size()){
                            affichage+="\nLa chambre numéro : "+Main.chambres.get(list.get(y)).numéroChambre+" de type : "+Main.chambres.get(list.get(y)).type+".";
                            y++;
                        }
                        if(chambre_réservées.size()>=list.size()){
                            JOptionPane.showConfirmDialog(null,"Plus aucune chambre est libre. Vous ne pouvez plus réserver des chambres.","Information",JOptionPane.INFORMATION_MESSAGE);
                            fini=true;
                        }
                    }

                }while(!fini);

                int o = JOptionPane.showConfirmDialog(null,"Vous confirmer votre modification de réservation ?");
                if(o!=0) {
                    p=0;
                    do{
                        Main.reservations.get(usernameClient).getChambre().get(p).setLibre(false);
                        p++;
                    }while(p<Main.reservations.get(usernameClient).getChambre().size());
                    throw new Exception_text("Votre réservation est maintenue comme elle l'était. Merci de nous avoir fait confiance.");
                } else {
                    MongoCollection collection = MongoFunctions.mongconnect("TP_Hotel", "Chambres");
                    p=0;
                    do{
                        Main.chambres.get(chambre_abondonnées.get(p)).setLibre(true);
                        MongoFunctions.updateDoc(collection,"NumChambre",chambre_abondonnées.get(p),"libre",true);
                        p++;
                    }while(p<chambre_abondonnées.size());
                    p=0;
                    do{
                        Main.chambres.get(chambre_réservées.get(p)).setLibre(false);
                        MongoFunctions.updateDoc(collection,"NumChambre",chambre_réservées.get(p),"libre",false);
                        p++;
                    }while(p<chambre_réservées.size());
                    collection = MongoFunctions.mongconnect("TP_Hotel", "Reservations");
                    MongoFunctions.updateDoc(collection,"username",usernameClient,"reservedRooms",chambre_réservées);
                    JOptionPane.showMessageDialog(null,"Merci de nous avoir fait confiance une autre fois ! Votre modification a été effectué avec succès ! \nVeuillez attendre la confirmation de votre réservation ces jours là.","Merci !",JOptionPane.INFORMATION_MESSAGE);
                    Client_view.reservationModifiee[0] = true;
                }
            } else if (choix.equals("3")) {
                Client_view.garderInfos(usernameClient);
                MongoCollection collection = MongoFunctions.mongconnect("TP_Hotel", "Chambres");
                for(int j=0;j<Main.reservations.get(usernameClient).getChambre().size();j++){
                    Main.reservations.get(usernameClient).getChambre().get(j).setLibre(true);
                    MongoFunctions.updateDoc(collection,"NumChambre",Main.reservations.get(usernameClient).getChambre().get(j).numéroChambre,"libre",true);
                }
                Main.clients.get(usernameClient).setRéservation_rejetée(false);
                collection = MongoFunctions.mongconnect("TP_Hotel", "Clients");
                MongoFunctions.updateDoc(collection,"username",usernameClient,"\n" + "réservation_rejetée",null);
                Main.reservations.remove(usernameClient);
                Model.ReservationOper.suppReservation(usernameClient);
            } else{
                throw new Exception_text("Vous ne pouvez pas entrer autre chose que '1', '2' ou '3'.");
            }
        }
    }

    public static void supprimerRéservation(String usernameClient){
        try {
            // Vérifiez si cet utilisateur a déjà annulé la réservation
            if (Client_view.reservationAnnulee[0]) {
                JOptionPane.showMessageDialog(null,"Vous avez déjà annulé votre réservation.","Erreur",JOptionPane.ERROR_MESSAGE);
                return; // Sortez de la méthode car l'annulation a déjà été faite
            }

            //vérifier si cet utilisateur a déjà une réservation
            Boolean trouvé = false;

            if(Main.reservations.containsKey(usernameClient)){
                trouvé=true;
            }

            if(trouvé==true) {
                int i = JOptionPane.showConfirmDialog(null,"Etes vous sûr de vouloir annuler votre reservation ?");
                if(i!=0){
                    JOptionPane.showMessageDialog(null,"Nous sommes ravis d'avoir entendus ça, nous vous verrons très prochainement !","Merci !",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    MongoCollection collection = MongoFunctions.mongconnect("TP_Hotel", "Chambres");
                    for(int j=0;j<Main.reservations.get(usernameClient).getChambre().size();j++){
                        Main.reservations.get(usernameClient).getChambre().get(j).setLibre(true);
                        MongoFunctions.updateDoc(collection,"NumChambre",Main.reservations.get(usernameClient).getChambre().get(j).numéroChambre,"libre",true);
                    }
                    Main.reservations.remove(usernameClient);
                    Model.ReservationOper.suppReservation(usernameClient);
                    JOptionPane.showMessageDialog(null,"Nous sommmes désolés d'entendre ça, nous esperons vous revoir très prochainement chez nous.","A la prochaine !",JOptionPane.INFORMATION_MESSAGE);
                    // Mettez à jour l'état de l'annulation de la réservation
                    Client_view.reservationAnnulee[0] = true;
                }
            } else{
                throw new Exception_text("Vous n'avez fait aucune reservation, vous ne pouvez rien annuler pour le moment.");
            }
        } catch (Exception_text ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }

}
