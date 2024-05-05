package Controller;

import Model.Test;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private static int ID=1;
    private Client client;
    private ArrayList<Chambre> chambre = new ArrayList<Chambre>();
    private Date dateDebut;
    private Date dateFin;
    private double total;
    private Boolean réservation_confirmée;

    public Reservation(Client client, ArrayList<Chambre> chambre, Date datePooDebut, Date datePooFin, double total){
        this.client = client;
        this.chambre = chambre;
        this.dateDebut = datePooDebut;
        this.dateFin = datePooFin;
        this.total = total;
        this.réservation_confirmée=false;
        Reservation.ID = Reservation.ID++;
    }

    public Reservation(String username, List<Integer> reservedRooms, Date dateDebut, Date dateFin, double totalPrice) {
        this.client = Main.clients.get(username);

        for(int i=0;i<reservedRooms.size();i++){
            this.chambre.add(Main.chambres.get(reservedRooms.get(i)));
        }
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.total = totalPrice;
        this.réservation_confirmée=false;
    }

    public static int getID(){return Reservation.ID;}

    public double getTotal(){return this.total;}
    public void setTotal(double total){this.total=total;}
    
    public Client getClient(){return this.client;}
    public void setClient(Client client){this.client=client;}
    
    public ArrayList<Chambre> getChambre(){return this.chambre;}
    public void setChambre(ArrayList<Chambre> chambre){this.chambre=chambre;}
    
    public Date getDateDebut(){return this.dateDebut;}
    public void setDateDebut(Date dateDebut){this.dateDebut = dateDebut;}
    
    public Date getDateFin(){return this.dateFin;}
    public void setDateFin(Date dateFin){this.dateFin = dateFin;}
    
    //pour l'admin
    public Boolean getRéservation_confirmée(){return this.réservation_confirmée;}
    public void setRéservation_confirmée(Boolean bool){this.réservation_confirmée = bool;}

    public void annuler_réservation() {
        this.client=null;
        this.chambre=null;
        this.dateDebut =null;
        this.dateFin =null;
    }

    public void afficher(){
        System.out.println("\nAffichage les infos de votre réservation :");
        System.out.println("Votre nom comlet : "+this.client.nom+" "+this.client.prenom);
        System.out.println("Votre numéro de téléphone : "+this.client.telephone);
        System.out.print("Vos chambre réservée : ");
        int i=0;
        do{
            System.out.print("      "+this.chambre.get(i).numéroChambre+" est de type "+this.chambre.get(i).type);
            i++;
        }while(i<this.chambre.size());
        System.out.print("DatePoo de début de la réservation: "); this.dateDebut.afficherDate(this.dateDebut);
        System.out.print("\nDatePoo de fin de la réservation: "); this.dateDebut.afficherDate(this.dateDebut);
        System.out.println("\nLe total à payer : "+this.total+" DA\n");
    }

    public void détails(){
        System.out.println("\nAffichage des infos de la réservation :");
        System.out.println("Le nom comlet : "+this.client.nom+" "+this.client.prenom);
        System.out.println("Le numéro de téléphone : "+this.client.telephone);
        System.out.println("Vos chambre réservée : ");
        int i=0;
        do{
            System.out.print(this.chambre.get(i).numéroChambre+" est de type "+this.chambre.get(i).type+"      ");
            i++;
        }while(i<this.chambre.size());
        System.out.print("DatePoo de début de la réservation: "); this.dateDebut.afficherDate(this.dateDebut);
        System.out.print("\nDatePoo de fin de la réservation: "); this.dateDebut.afficherDate(this.dateDebut);
        System.out.println("\nLe total : "+this.total+" DA\n");
    }
}
