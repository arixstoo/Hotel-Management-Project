package Controller;

import Controller.Exceptions.Exception_text;

import javax.swing.*;
import java.text.ParseException;
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

    public static Date Date_Debut(Date aujourdhui) throws Exception_text {
        String anneeDebut = JOptionPane.showInputDialog("Veuillez entrer la date début de votre réservation : \nL'année :  (pas avant "+Main.aujourdhui.getAnnee()+") : ");
        int annee_Debut = Integer.parseInt(anneeDebut);
        if(annee_Debut<Main.aujourdhui.getAnnee()){
            throw new Exception_text("Vous ne pouvez pas entrer une année avant "+Main.aujourdhui.getAnnee());
        }

        String moisDebut;
        if(annee_Debut==Main.aujourdhui.getAnnee()){
            moisDebut = JOptionPane.showInputDialog("Veuillez entrer la date début de votre réservation : \nLe mois :  (pas avant "+Main.aujourdhui.getMois()+") : ");
        }
        else{
            moisDebut = JOptionPane.showInputDialog("Veuillez entrer la date début de votre réservation : \nLe mois : ");
        }
        int mois_Debut = Integer.parseInt(moisDebut);
        if(mois_Debut<Main.aujourdhui.getMois() && annee_Debut==Main.aujourdhui.getAnnee()){
            throw new Exception_text("Vous ne pouvez pas entrer un mois avant "+Main.aujourdhui.getMois());
        }
        if(mois_Debut<1 || mois_Debut>12){
            throw new Exception_text("Le mois doit être entre 1 et 12");
        }

        String jourDebut;
        if(annee_Debut==Main.aujourdhui.getAnnee() && mois_Debut== Main.aujourdhui.getMois()){
            jourDebut = JOptionPane.showInputDialog("Veuillez entrer la date début de votre réservation : \nLe jour :  (pas avant "+Main.aujourdhui.getJour()+") : ");
        }
        else{
            jourDebut = JOptionPane.showInputDialog("Veuillez entrer la date début de votre réservation : \nLe jour : ");
        }
        int jour_Debut = Integer.parseInt(jourDebut);
        if(jour_Debut<1 || jour_Debut>Controller.Date.nombreJoursDansMois(mois_Debut, annee_Debut)){
            throw new Exception_text("Le jour doit être entre 1 et "+Controller.Date.nombreJoursDansMois(mois_Debut, annee_Debut));
        }
        else if(jour_Debut<Main.aujourdhui.getJour() && (mois_Debut==Main.aujourdhui.getMois() && annee_Debut==Main.aujourdhui.getAnnee())){
            int temp = Main.aujourdhui.getJour()+1;
            throw new Exception_text("Le jour doit être entre "+temp+" et "+Controller.Date.nombreJoursDansMois(mois_Debut, annee_Debut));
        }
        return new Date(jour_Debut,mois_Debut,annee_Debut);
    }

    public static Date Date_Fin(Date aujourdhui) throws Exception_text {
        String anneeDebut = JOptionPane.showInputDialog("Veuillez entrer la date fin de votre réservation : \nL'année :  (pas avant "+aujourdhui.getAnnee()+") : ");
        int annee_Debut = Integer.parseInt(anneeDebut);
        if(annee_Debut<aujourdhui.getAnnee()){
            throw new Exception_text("Vous ne pouvez pas entrer une année avant "+aujourdhui.getAnnee());
        }

        String moisDebut;
        if(annee_Debut==aujourdhui.getAnnee()){
            moisDebut = JOptionPane.showInputDialog("Veuillez entrer la date fin de votre réservation : \nLe mois :  (pas avant "+aujourdhui.getMois()+") : ");
        }
        else{
            moisDebut = JOptionPane.showInputDialog("Veuillez entrer la date fin de votre réservation : \nLe mois : ");
        }
        int mois_Debut = Integer.parseInt(moisDebut);
        if(mois_Debut<aujourdhui.getMois() && annee_Debut==aujourdhui.getAnnee()){
            throw new Exception_text("Vous ne pouvez pas entrer un mois avant "+aujourdhui.getMois());
        }
        if(mois_Debut<1 || mois_Debut>12){
            throw new Exception_text("Le mois doit être entre 1 et 12");
        }

        String jourDebut;
        if(annee_Debut==aujourdhui.getAnnee() && mois_Debut== aujourdhui.getMois()){
            jourDebut = JOptionPane.showInputDialog("Veuillez entrer la date fin de votre réservation : \nLe jour :  (pas avant "+aujourdhui.getJour()+") : ");
        }
        else{
            jourDebut = JOptionPane.showInputDialog("Veuillez entrer la date fin de votre réservation : \nLe jour : ");
        }
        int jour_Debut = Integer.parseInt(jourDebut);
        if(jour_Debut<1 || jour_Debut>Controller.Date.nombreJoursDansMois(mois_Debut, annee_Debut)){
            throw new Exception_text("Le jour doit être entre 1 et "+Controller.Date.nombreJoursDansMois(mois_Debut, annee_Debut));
        }
        else if(jour_Debut<aujourdhui.getJour() && (mois_Debut==aujourdhui.getMois() && annee_Debut==aujourdhui.getAnnee())){
            int temp = aujourdhui.getJour()+1;
            throw new Exception_text("Le jour doit être entre "+temp+" et "+Controller.Date.nombreJoursDansMois(mois_Debut, annee_Debut));
        }
        return new Date(jour_Debut,mois_Debut,annee_Debut);
    }

    public static void dateDebut_valide(Date dateDebut, Date aujourdhui) throws Exception_text {
        if(dateDebut.getAnnee()<aujourdhui.getAnnee()){
            throw new Exception_text("Vous ne pouvez pas entrer une année avant "+aujourdhui.getAnnee()+" dans le Check-in de votre réservation.");
        }
        if(dateDebut.getMois()<aujourdhui.getMois() && dateDebut.getAnnee()==aujourdhui.getAnnee()){
            throw new Exception_text("Vous ne pouvez pas entrer un mois avant "+aujourdhui.getMois()+" dans le Check-in de votre réservation.");
        }
        if(dateDebut.getMois()<1 || dateDebut.getMois()>12){
            throw new Exception_text("Le mois doit être entre 1 et 12 dans le Check-in de votre réservation.");
        }
        if(dateDebut.getJour()<1 || dateDebut.getJour()>Controller.Date.nombreJoursDansMois(dateDebut.getMois(), dateDebut.getAnnee())){
            throw new Exception_text("Le jour doit être entre 1 et "+Controller.Date.nombreJoursDansMois(dateDebut.getMois(), dateDebut.getAnnee())+" dans le Check-in de votre réservation.");
        }
        else if(dateDebut.getJour()<Main.aujourdhui.getJour() && (dateDebut.getMois()==Main.aujourdhui.getMois() && dateDebut.getAnnee()==aujourdhui.getAnnee())){
            int temp = aujourdhui.getJour()+1;
            throw new Exception_text("Le jour doit être entre "+temp+" et "+Controller.Date.nombreJoursDansMois(dateDebut.getMois(), dateDebut.getAnnee())+" dans le Check-in de votre réservation.");
        }
    }

    public static void dateFin_valide(Date dateDebut, Date aujourdhui) throws Exception_text {
        if(dateDebut.getAnnee()<aujourdhui.getAnnee()){
            throw new Exception_text("Vous ne pouvez pas entrer une année avant "+aujourdhui.getAnnee()+" dans le Check-out de votre réservation.");
        }
        if(dateDebut.getMois()<aujourdhui.getMois() && dateDebut.getAnnee()==aujourdhui.getAnnee()){
            throw new Exception_text("Vous ne pouvez pas entrer un mois avant "+aujourdhui.getMois()+" dans le Check-out de votre réservation.");
        }
        if(dateDebut.getMois()<1 || dateDebut.getMois()>12){
            throw new Exception_text("Le mois doit être entre 1 et 12 dans le Check-out de votre réservation.");
        }
        if(dateDebut.getJour()<1 || dateDebut.getJour()>Controller.Date.nombreJoursDansMois(dateDebut.getMois(), dateDebut.getAnnee())){
            throw new Exception_text("Le jour doit être entre 1 et "+Controller.Date.nombreJoursDansMois(dateDebut.getMois(), dateDebut.getAnnee())+" dans le Check-out de votre réservation.");
        }
        else if(dateDebut.getJour()<aujourdhui.getJour() && (dateDebut.getMois()==aujourdhui.getMois() && dateDebut.getAnnee()==aujourdhui.getAnnee())){
            int temp = aujourdhui.getJour()+1;
            throw new Exception_text("Le jour doit être entre "+temp+" et "+Controller.Date.nombreJoursDansMois(dateDebut.getMois(), dateDebut.getAnnee())+" dans le Check-out de votre réservation.");
        }
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
    public static int nombreJoursDansMois(int mois, int annee) {
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

    // Fonction pour convertir la date en chaîne de caractères
    public static String dateVersString(Date datee) {
        return datee.getJour() + "/" + datee.getMois() + "/" + datee.getAnnee();
    }

    // Fonction pour convertir une chaîne de caractères en date
    public static Date stringVersDate(String dateEnString) {
        String[] parties = dateEnString.split("/");
        int jour = Integer.parseInt(parties[0]);
        int mois = Integer.parseInt(parties[1]);
        int annee = Integer.parseInt(parties[2]);
        return new Date(jour, mois, annee);
    }

    public void afficherDate(Date date){
        System.out.print(date.jour+"/"+date.mois+"/"+date.annee);
    }
}
