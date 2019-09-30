package com.example.testmap;

public class CPreuve {
    private String m_nom, m_description, m_preuve;

    public enum enumPreuve //6 choix
    {
        Mouvement,
        Pas,
        Distance,
        Photo,
        Parler,
        Enregistrer
    }

    public CPreuve() {
        m_nom = m_description = m_preuve = null;
    }

    public CPreuve(String nom, String description, String preuve) {
        m_nom = nom;
        m_description = description;
        m_preuve = preuve;
    }

    ///Methodes pour générer le random dans les preuves
    public static CPreuve GeneratePreuve() {
        int rand = (int) Math.round(Math.random() * 6);
        enumPreuve enumpreuve = enumPreuve.values()[rand];

        String description = null;
        String preuve = null;

        switch (rand) {
            case 0:
                preuve = GenerateMouvement();
                description = "Faire un mouvement en forme de " + preuve + " avec le portable";
                break;

            case 1:
                preuve = String.valueOf(GeneratePas());
                description = "Faire " + preuve + " pas";
                break;

            case 2:
                preuve = String.valueOf(GenerateDistance());
                description = "Faire " + preuve + " mètres";
                break;

            case 3:
                preuve = GeneratePhoto();
                description = "Prendre " + preuve + " en photo";
                break;

            case 4:
                preuve = GenerateParler();
                description = "Chanter " + preuve;
                break;

            case 5:
                preuve = GenerateEnregistrer();
                description = "Enregistrer le bruit de " + preuve;
                break;

            default:
                preuve = GenerateMouvement();
                description = "Faire un mouvement en forme de " + preuve + " avec le portable";
                break;
        }

        CPreuve cPreuve = new CPreuve(enumpreuve.toString(), description, preuve);

        return cPreuve;
    }

    private static String GenerateMouvement() {
        int rand = (int) Math.round(Math.random() * 4);
        String res = null;
        switch (rand) {
            case 0:
                res = "S";
                break;

            case 1:
                res = "X";
                break;

            case 2:
                res = "O";
                break;

            case 3:
                res = "A";
                break;

            default:
                res = "S";
                break;
        }

        return res;
    }

    private static int GeneratePas() {
        //pour faire entre 250 et 1000 pas
        int res = (int) Math.round((Math.random() * 750 + 250));
        return res;
    }

    private static int GenerateDistance() {
        //pour faire entre 100 et 500 m
        int res = (int) Math.round((Math.random() * 400 + 100));
        return res;
    }

    private static String GeneratePhoto() {
        int rand = (int) Math.round(Math.random() * 6);
        String res = null;
        switch (rand) {
            case 0:
                res = "chat";
                break;

            case 1:
                res = "chien";
                break;

            case 2:
                res = "arbre";
                break;

            case 3:
                res = "construction";
                break;

            case 4:
                res = "voiture";
                break;

            case 5:
                res = "personne";
                break;

            default:
                res = "chat";
                break;
        }

        return res;
    }

    private static String GenerateParler() {
        int rand = (int) Math.round(Math.random() * 6);
        String res = null;
        switch (rand) {
            case 0:
                res = "Allons enfants de la Patrie\n" +
                        "Le jour de gloire est arrivé\n" +
                        "Contre nous de la tyrannie\n" +
                        "L'étendard sanglant est levé";
                break;

            case 1:
                res = "Wesh alors, wesh alors\n" +
                        "Wesh alors, wesh alors\n" +
                        "Wesh alors\n" +
                        "Sors ta beuh, ta plaquette\n" +
                        "J'suis dans l'game en claquettes\n" +
                        "Dans l'carré VIP en survêt'\n" +
                        "Viens pas me prendre la tête";
                break;

            case 2:
                res = "Ho ho ho, est-ce que tu m'entends, hey ho\n" +
                        "Est-ce que tu me sens, hey ho\n" +
                        "Touche-moi je suis là, hey ho ho ho ho\n" +
                        "S'il te plait réponds-moi, hey ho\n" +
                        "Un geste suffira, hey ho\n" +
                        "Est-ce que tu m'aperçois, hey ho ho ho ho ho";
                break;

            case 3:
                res = "C'était un cordonnier, sans rien d'particulier\n" +
                        "Dans un village dont le nom m'a échappé\n" +
                        "Il faisait des souliers si jolis, si légers\n" +
                        "Que nos vies semblaient un peu moins lourdes à porter";
                break;

            case 4:
                res = "Terre brûlée au vent\n" +
                        "Des landes de pierres\n" +
                        "Autour des lacs, c'est pour les vivants\n" +
                        "Un peu d'enfer, le Connemara\n" +
                        "Des nuages noirs qui viennent du nord\n" +
                        "Colorent la terre, les lacs, les rivières\n" +
                        "C'est le décor du Connemara";
                break;

            case 5:
                res = "Dans un voyage en Absurdie\n" +
                        "Que je fais lorsque je m'ennuie\n" +
                        "J'ai imaginé sans complexe\n" +
                        "Qu'un matin je changeais de sexe\n" +
                        "Que je vivais l'étrange drame\n" +
                        "D'être une femme";
                break;

            default:
                res = "Allons enfants de la Patrie\n" +
                        "Le jour de gloire est arrivé\n" +
                        "Contre nous de la tyrannie\n" +
                        "L'étendard sanglant est levé";
                break;
        }

        return res;
    }

    private static String GenerateEnregistrer() {
        int rand = (int) Math.round(Math.random() * 4);
        String res = null;
        switch (rand) {
            case 0:
                res = "Voitures";
                break;

            case 1:
                res = "Klaxons";
                break;

            case 2:
                res = "Animaux";
                break;

            case 3:
                res = "Travaux";
                break;


            default:
                res = "Voiture";
                break;
        }
        return res;
    }


    ///Methode Get
    public String getM_nom() {
        return m_nom;
    }

    public String getM_description() {
        return m_description;
    }
}

