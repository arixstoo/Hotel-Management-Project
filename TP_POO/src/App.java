import Controller.Date;

public class App{
    public static void main(String[] args){
        Date date= new Date(12,3,2024);
        System.out.println(Date.dateVersString(date));
        String datee = "12/03/2023";
        Date dateee = Date.stringVersDate(datee);
        System.out.println(dateee.getJour()+" "+dateee.getMois()+" "+ dateee.getAnnee());
    }
}