import java.util.Scanner;

public class Admin extends Person {

    Scanner sc = new Scanner(System.in);
    private String username;
    private String motDePasse;
    private String code; //pour accéder comme admin

    //constructeur zaema les admins on les ajoute manuellement donc makaleh constructeur b scanner et tout hna ndekhlo les info directement même le code et le mot de passe
    //SIGN UP BSH MANUALLY 
    //HAD L FONCTION MARANICH SUR MENHA HATA TVALIDIWHA NTOUMA
    public Admin(String nom, String prenom, String email, String adresse, String telephone, String username, String motDePasse, String code){
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
        this.adresse=adresse;
        this.telephone=telephone;
        this.username=username;
        this.motDePasse=motDePasse;
        this.code=code;
    }

    //ou bien boolean jsp
    public void Login(String username, String motDePasse, String code){
        if(Main.admins.containsKey(username)){
            Admin temp = Main.admins.get(username);
            if(temp.motDePasse==motDePasse){
                if(code!=temp.code){
                    System.out.println("Code incorect, réesseyez.");
                    String password;
                    do{
                        password = sc.nextLine();
                        if(password!=temp.code){
                            System.out.println("Code incorect, réesseyez.");
                        }
                    }while(password!=temp.code); 
                }
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
                if(code!=temp.code){
                    System.out.println("Code incorect, réesseyez.");
                    do{
                        password = sc.nextLine();
                        if(password!=temp.code){
                            System.out.println("Code incorect, réesseyez.");
                        }
                    }while(password!=temp.code); 
                }
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

            Admin temp = Main.admins.get(username);
            if(temp.motDePasse==motDePasse){
                if(code!=temp.code){
                    System.out.println("Code incorect, réesseyez.");
                    String password;
                    do{
                        password = sc.nextLine();
                        if(password!=temp.code){
                            System.out.println("Code incorect, réesseyez.");
                        }
                    }while(password!=temp.code); 
                }
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
                if(code!=temp.code){
                    System.out.println("Code incorect, réesseyez.");
                    do{
                        password = sc.nextLine();
                        if(password!=temp.code){
                            System.out.println("Code incorect, réesseyez.");
                        }
                    }while(password!=temp.code); 
                }
                System.out.println("Successfully accessed !");
            }
        }

    }
}