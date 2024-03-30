import java.util.Scanner;

class Person{
    protected String nom;
    protected String prenom;
    protected String email;
    protected String adresse;
    protected String telephone;
}
public class Client extends Person {
    
    Scanner sc = new Scanner(System.in);
    private String username;
    private String motDePasse;

    //SIGN UP
    public Client(){
        
        System.out.print("Veuillez entrer votre nom : ");
        this.nom = sc.nextLine();
        System.out.print("Veuillez entrer votre prénom : ");
        this.prenom = sc.nextLine();
        System.out.print("Veuillez entrer votre email : ");
        do{
            this.email = sc.nextLine();
            if(isValidEmail(email)==false) {
                System.out.println("L'adresse email est invalide (ça doit contenir @ et .com).");
            }

        }while(isValidEmail(email)==false);
        System.out.print("Veuillez entrer votre adresse : ");
        this.adresse = sc.nextLine();
        System.out.print("Veuillez entrer votre telephone : ");
        Boolean validé=true;
        do{
            this.telephone = sc.nextLine();
            if(this.telephone.length()<10 || this.telephone.length()>10){
                validé=false;
                System.out.println("Le numéro de téléphone est invalide (ça doit contenir 10 caractères).");
            }
            if(this.telephone.startsWith("05") && this.telephone.startsWith("06") && this.telephone.startsWith("07")){
                validé=false;
                System.out.println("Le numéro de téléphone est invalide (ça doit commencé avec 05 , 06 ou bien 07).");
            }
        }while(validé==false);
        System.out.print("Veuillez entrer votre username : ");
        //Il faut ajouter la connection avec la base de données pour être unique
        do{
            this.username = sc.nextLine();
            if(Main.clients.containsKey(this.username)==true){
                System.out.println("Le username est invalide (ce username : "+this.username+" est déjà utilisé).");
            }

        }while(Main.clients.containsKey(this.username));
        System.out.print("Veuillez entrer votre mot de passe : ");
        do{
            this.motDePasse = sc.nextLine();
            if(this.motDePasse.length()<8){
                System.out.println("Le mot de passe est invalide (le mot de passe doit contenir au minimum 8 caractères).");
            }
        }while(this.motDePasse.length()<8);
    }
    boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".com");
    }

    //ou bien boolean jsp
    public void Login(String username, String motDePasse){
        if(Main.clients.containsKey(username)){
            Client temp = Main.clients.get(username);
            if(temp.motDePasse==motDePasse){
                System.out.println("Successfully accessed !");
            }
            else{
                System.out.println("Mot de passe incorect, réesseyez.");
                String password;
                do{
                    password = sc.nextLine();
                    if(password!=temp.motDePasse){
                        System.out.println("Mot de passe incorect, réesseyez.");
                    }
                }while(password!=temp.motDePasse);
                System.out.println("Successfully accessed !");
            }
        }
        else{
            System.out.println("Username incorect, réesseyez.");
            String name;
            do{
                name = sc.nextLine();
                if(Main.clients.containsKey(name)==false){
                   System.out.println("Username incorect, réesseyez.");
                }
            }while(Main.clients.containsKey(name)==false);
            Client temp = Main.clients.get(username);
            if(temp.motDePasse==motDePasse){
                System.out.println("Successfully accessed !");
            }
            else{
                System.out.println("Mot de passe incorect, réesseyez.");
                String password;
                do{
                    password = sc.nextLine();
                    if(password!=temp.motDePasse){
                        System.out.println("Mot de passe incorect, réesseyez.");
                    }
                }while(password!=temp.motDePasse);
                System.out.println("Successfully accessed !");
            }
        }

    }
    
    public void afficher(){
        System.out.println("Affichege de vos infos :");
        System.out.println("Votre nom : "+this.nom);
        System.out.println("Votre prénom : "+this.prenom);
        System.out.println("Votre email : "+this.email);
        System.out.println("Votre adresse : "+this.adresse);
        System.out.println("Votre numéro de téléphone : "+this.telephone);
        System.out.println("Votre username : "+this.username);
    }
}