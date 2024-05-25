package Controller;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private static int ID=0;
    private Client client;
    private ArrayList<Chambre> chambre = new ArrayList<>();
    private Date dateDebut;
    private Date dateFin;
    private double total;
    private Boolean réservation_confirmée;

    public Reservation(String username, List<Integer> reservedRooms, Date dateDebut, Date dateFin, double totalPrice, Boolean réservation__confirmée) {
        this.client = Main.clients.get(username);
        for(int i=0;i<reservedRooms.size();i++){
            this.chambre.add(Main.chambres.get(reservedRooms.get(i)));
        }
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.total = totalPrice;
        this.réservation_confirmée=réservation__confirmée;
    }

    public Reservation() {}

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

}
