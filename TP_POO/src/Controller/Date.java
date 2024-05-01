package Controller;

import java.util.Scanner;

public class Date {

    Scanner sc = new Scanner(System.in);
    private int annee;
    private int mois;
    private int jour;

    public Date(int jour, int mois, int annee) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }

    public Date(Date aujourdhui){
        int année, mois, jour;
        do{
            System.out.print("L'année (pas avant "+aujourdhui.annee+") : ");
            année = sc.nextInt();
            if(année<aujourdhui.annee){
                System.out.println("Vous ne pouvez pas entrer une année avant "+aujourdhui.annee);
            }
        }while(année<aujourdhui.annee);

        do{
            if(année==aujourdhui.annee){
                System.out.print("Le mois (pas avant "+aujourdhui.mois+"): ");
            }
            else{
                System.out.print("Le mois : ");
            }
            mois = sc.nextInt();
            if(mois<aujourdhui.mois && année==aujourdhui.annee){
                System.out.println("Vous ne pouvez pas entrer un mois avant "+aujourdhui.mois);
            }
            if(mois<1 || mois>12){
                System.out.println("Le mois doit être entre 1 et 12");
            }
        }while((mois<aujourdhui.mois && année==aujourdhui.annee) || (mois<1 || mois>12));

        do{
            System.out.print("Le jour : ");
            jour = sc.nextInt();
            if(jour<1 || jour>nombreJoursDansMois(mois, année)){
                System.out.println("Le jour doit être entre 1 et "+nombreJoursDansMois(mois, année));
            }
            else if(jour<aujourdhui.jour && (mois==aujourdhui.mois && année==aujourdhui.annee)){
                int temp = aujourdhui.jour+1;
                System.out.println("Le jour doit être entre "+temp+" et "+nombreJoursDansMois(mois, année));
            }
        }while(jour<1  || jour>nombreJoursDansMois(mois, année) || (jour<aujourdhui.jour && (mois==aujourdhui.mois && année==aujourdhui.annee)));
        
        this.annee=année;
        this.mois=mois;
        this.jour=jour;
    }

    public int getAnnee() {return annee;}
    public void setAnnee(int annee) {this.annee = annee;}

    public int getMois() {return mois;}
    public void setMois(int mois) {this.mois = mois;}

    public int getJour() {return jour;}
    public void setJour(int jour) {this.jour = jour;}

    // Fonction pour calculer le nombre de jours entre deux dates
    public static int nombreJours(Date date1, Date date2) {
        // On définit une date de référence
        Date dateReference = new Date(2000, 1, 1);
    
        // On calcule le nombre de jours depuis la date de référence pour chaque date
        int jours1 = joursDepuisReference(date1, dateReference);
        int jours2 = joursDepuisReference(date2, dateReference);
    
        // On retourne la différence entre les deux valeurs
        return Math.abs(jours2 - jours1);
    }
    
    // Fonction pour calculer le nombre de jours depuis une date de référence
    private static int joursDepuisReference(Date date, Date reference) {
        int nombreJoursTotal = 0;
    
        // On parcourt les années entre la date de référence et la date donnée
        for (int i = reference.annee; i < date.annee; i++) {
            nombreJoursTotal += estBissextile(i) ? 366 : 365;
        }
    
        // On parcourt les mois de l'année de la date de référence jusqu'au mois précédant la date donnée
        for (int i = reference.mois; i < date.mois; i++) {
            nombreJoursTotal += nombreJoursDansMois(i, date.annee);
        }
    
        // On ajoute les jours du mois de la date donnée
        nombreJoursTotal += date.jour;
    
        return nombreJoursTotal;
    }
    
    // Fonction pour savoir si une année est bissextile
    private static boolean estBissextile(int annee) {
        return (annee % 4 == 0 && (annee % 100 != 0 || annee % 400 == 0));
    }

    // Fonction pour obtenir le nombre de jours dans un mois
    private static int nombreJoursDansMois(int mois, int annee) {
        switch (mois) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return estBissextile(annee) ? 29 : 28;
            default:
                return 0;
        }
    }

    // Fonction pour savoir si une date est avant une autre pour l'annulation de la réservetion
    public static boolean estAvant(Date date1, Date date2) {
        if (date1.annee < date2.annee) {
            return true;
        } else if (date1.annee == date2.annee) {
            if (date1.mois < date2.mois) {
                return true;
            } else if (date1.mois == date2.mois) {
                return date1.jour < date2.jour;
            }
        }
        return false;
    }

    // Fonction pour savoir si une date est après une autre pour l'annulation de la réservetion
    public static boolean estApres(Date date1, Date date2) {
        return !estAvant(date1, date2) && !date1.equals(date2);
    }
    
    // Pour la modification de la résarvarion
    public Date ajouterJours(int nombreJours) {
        int jourNouveau = jour + nombreJours;
        int moisNouveau = mois;
        int anneeNouveau = annee;
    
        while (jourNouveau > nombreJoursDansMois(moisNouveau, anneeNouveau)) {
            jourNouveau -= nombreJoursDansMois(moisNouveau, anneeNouveau);

            moisNouveau++;
    
            // Si le mois dépasse 12, passer à la nouvelle année
            if (moisNouveau > 12) {
                moisNouveau = 1;
                anneeNouveau++;
            }
        }
        return new Date(anneeNouveau, moisNouveau, jourNouveau);
    }
    public void afficherDate(Date date){
        System.out.print(date.jour+"/"+date.mois+"/"+date.annee);
    }
}
