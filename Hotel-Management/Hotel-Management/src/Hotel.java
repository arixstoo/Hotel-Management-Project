public class Hotel {
    
    private static String name;
    private static String adresse;
    //things that has in it and the things u can do au alentours
    //we can do them f display direct and I need constructeur directement fih efayesna
    

    public Hotel(String name, String adresse){
        this.name=name;
        this.adresse=adresse;
    }
    
    public static void afficher(){
        System.out.println("L'Hôtel "+Hotel.name+" : Votre havre de paix au milieu de la ville\r\n" + //
                        "\r\n" + //
                        "Situé à "+Hotel.adresse+", l'Hôtel "+Hotel.name+" vous offre un séjour inoubliable dans un cadre élégant et raffiné. Que vous soyez en voyage d'affaires ou de loisirs, notre hôtel vous propose tout le confort et les services dont vous avez besoin pour vous détendre et profiter pleinement de votre séjour.\r\n" + //
                        "\r\n" + //
                        "Nos chambres:\r\n" + //
                        "\r\n" + //
                        "Spacieuses et confortables, nos chambres sont décorées avec goût et équipées de tout le nécessaire pour un séjour des plus agréables.\r\n" + //
                        "Vous profiterez d'une literie de qualité, d'une salle de bain privative, d'une connexion Wi-Fi gratuite et d'un coffre-fort.\r\n" + //
                        "Différentes catégories de chambres sont disponibles pour répondre à vos besoins et à votre budget.\r\n" + //
                        "Nos services:\r\n" + //
                        "\r\n" + //
                        "Notre hôtel met à votre disposition un large éventail de services pour vous garantir un séjour des plus agréables.\r\n" + //
                        "Vous pourrez profiter d'un restaurant gastronomique, d'une piscine extérieure, d'un centre de remise en forme et d'un sauna.\r\n" + //
                        "Un parking privé est également disponible sur place.\r\n" + //
                        "Notre situation:\r\n" + //
                        "\r\n" + //
                        "L'Hôtel "+Hotel.name+" est idéalement situé en plein cœur de la ville, à proximité des principales attractions touristiques et des restaurants.\r\n" + //
                        "Vous pourrez facilement vous déplacer à pied ou en transports en commun pour explorer la ville et ses environs.\r\n" + //
                        "Notre engagement:\r\n" + //
                        "\r\n" + //
                        "Notre équipe est dévouée à votre entière satisfaction et s'engage à vous offrir un service personnalisé et attentionné.\r\n" + //
                        "Nous sommes à votre disposition 24h/24 et 7j/7 pour répondre à vos questions et vous aider à organiser votre séjour.\r\n" + //
                        "L'Hôtel "+Hotel.name+", c'est l'assurance d'un séjour inoubliable dans un cadre élégant et raffiné.\r\n" + //
                        "\r\n" + //
                        "N'hésitez pas à nous contacter pour réserver votre chambre dès aujourd'hui !\r\n" + //
                        "\r\n" + //
                        "Informations complémentaires:\r\n" + //
                        "\r\n" + //
                        "Site web: "+Hotel.name.toLowerCase()+".com\r\n" + //
                        "Téléphone: 023 128 940 30\r\n" + //
                        "Email: "+Hotel.name.toLowerCase()+"@gmail.com\r\n" + //
                        "Réseaux sociaux:\r\n" + //
                        "\r\n" + //
                        "Facebook: "+Hotel.name+"\r\n" + //
                        "Instagram: "+Hotel.name.toLowerCase()+"\r\n" + //
                        "Twitter: "+Hotel.name);
    }
}
