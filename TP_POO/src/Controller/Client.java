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
        Client client = new Client(nom, prenom, email, telephone, username, motDePasse);
        Main.clients.put(username, client);
        MongoFunctions.insertClient(nom , prenom , email , telephone , username , motDePasse);
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


    
    public void afficher(){
        System.out.println("\nAffichage de vos infos :");
        System.out.println("Votre nom : "+this.nom);
        System.out.println("Votre prénom : "+this.prenom);
        System.out.println("Votre email : "+this.email);
        System.out.println("Votre numéro de téléphone : "+this.telephone);
        System.out.println("Votre username : "+this.username);
    }

}
