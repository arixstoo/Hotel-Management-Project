package Controller;

import Controller.Exceptions.Exception_text;
import Controller.Exceptions.isValidPassword;
import Model.MongoFunctions;

import java.util.*;
import java.util.Map.Entry;

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

    public Client(String nom, String prenom, String email, String telephone, String username, String motDePasse){
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
        this.telephone=telephone;
        this.username=username;
        this.motDePasse=motDePasse;
    }

}