package Controller;

public class Chambre {
    public int numéroChambre;
    public TypeChambre type;
    private Boolean libre;
    private int prix;

    public Chambre (int numéroChambre, TypeChambre type, Boolean libre){
        this.numéroChambre = numéroChambre;
        this.type = type;
        this.libre = libre;
        if(this.type==TypeChambre.Individuel){
            this.prix = 2000;
        }
        if(this.type==TypeChambre.Double){
            this.prix = 3500;
        }
        if(this.type==TypeChambre.Suite){
            this.prix = 5500;
        }
    }

    public void setLibre(Boolean a){this.libre=a;}
    public Boolean getLibre(){return this.libre;}

    public int getPrix(){return this.prix;}
    public void setPrix(int prix){this.prix = prix;}

    public TypeChambre getType(){return this.type;}

    public void afficherChambre(){
        System.out.println("La chambre "+this.numéroChambre+" de type "+this.type+" est libre et coûte "+this.prix+" DA pour la nuit.");
    }
}
