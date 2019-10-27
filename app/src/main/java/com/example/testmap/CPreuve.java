package com.example.testmap;

import java.io.Serializable;

public class CPreuve implements Serializable {
    private String m_nom, m_description, m_preuve;

    public enum enumPreuve //3 choix
    {
        Mouvement,
        Photo,
        Parler
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
    public void GeneratePreuve() {
        int rand = (int) Math.round(Math.random() * 3);
        rand = 1;
        m_nom = enumPreuve.values()[rand].toString();


        switch (rand) {
            case 0:
                m_preuve = GenerateMouvement();
                m_description = "Faire un mouvement en forme de " + m_preuve + " avec le portable";
                break;

/*            case 1:
                m_preuve = String.valueOf(GeneratePas());
                m_description = "Faire " + m_preuve + " pas";
                break;

            case 2:
                m_preuve = String.valueOf(GenerateDistance());
                m_description = "Faire " + m_preuve + " mètres";
                break;*/

            case 1:
                m_preuve = GeneratePhoto();
                m_description = "Prendre " + m_preuve + " en photo";
                break;

            case 2:
                m_preuve = GenerateParler();
                m_description = "Chanter : " + m_preuve;
                break;

            default:
                m_preuve = GenerateMouvement();
                m_description = "Faire un mouvement en forme de " + m_preuve + " avec le portable";
                break;
        }
    }

    private static String GenerateMouvement() {
        int rand = (int) Math.round(Math.random() * 3);
        String res = null;
        switch (rand) {
            case 0:
                res = "S";
                break;

            case 1:
                res = "X";
                break;

            case 2:
                res = "A";
                break;

            default:
                res = "S";
                break;
        }

        return res;
    }

/*
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
*/

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
                res = "Allons enfants de la Patrie le jour de gloire est arrivé contre nous de la tyrannie l'étendard sanglant est levé";
                break;

            case 1:
                res = "Wesh alors, wesh alors Sors ta beuh, ta plaquette J'suis dans l'game en claquettes Dans l'carré VIP en survêt' Viens pas me prendre la tête";
                break;

            case 2:
                res = "Ho ho ho, est-ce que tu m'entends, hey ho Est-ce que tu me sens, hey ho Touche-moi je suis là, hey ho ho ho ho S'il te plait réponds-moi, hey ho Un geste suffira, hey ho Est-ce que tu m'aperçois";
                break;

            case 3:
                res = "C'était un cordonnier, sans rien d'particulier Dans un village dont le nom m'a échappé Il faisait des souliers si jolis, si légers Que nos vies semblaient un peu moins lourdes à porter";
                break;

            case 4:
                res = "Terre brûlée au vent Des landes de pierres Autour des lacs, c'est pour les vivants Un peu d'enfer, le Connemara Des nuages noirs qui viennent du nord Colorent la terre, les lacs, les rivières";
                break;

            case 5:
                res = "Dans un voyage en Absurdie Que je fais lorsque je m'ennuie J'ai imaginé sans complexe Qu'un matin je changeais de sexe Que je vivais l'étrange drame D'être une femme";
                break;

            default:
                res = "Allons enfants de la Patrie le jour de gloire est arrivé contre nous de la tyrannie l'étendard sanglant est levé";
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

    public String getM_preuve() {
        return m_preuve;
    }
}

