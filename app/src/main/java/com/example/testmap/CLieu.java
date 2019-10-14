package com.example.testmap;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class CLieu implements Serializable {
    private double m_latitude, m_longitude;
    private String m_nom;
    private CPreuve m_preuve;

    CLieu() {
        m_latitude = m_longitude = 0.0;
        m_nom = null;
        m_preuve = new CPreuve();
    }

    CLieu(String nom, double latitude, double longitude, CPreuve preuve) {
        m_nom = nom;
        m_latitude = latitude;
        m_longitude = longitude;
        m_preuve = preuve;
    }

    public void GeneratePlace() {
        int rand = (int) Math.round(Math.random() * 10);
        switch (rand) {
            case 0://Cathédrale
                m_nom = "Cathédrale";
                m_latitude = 47.901773;
                m_longitude = 1.909026;
                break;

            case 1://Gare orléans
                m_nom = "Gare d'Orléans";
                m_latitude = 47.906922;
                m_longitude = 1.904904;
                break;

            case 2://Place de Gaulle
                m_nom = "Place de Gaulle";
                m_latitude = 47.901283;
                m_longitude = 1.902899;
                break;

            case 3://Place de Loire
                m_nom = "Place de Loire";
                m_latitude = 47.898448;
                m_longitude = 1.908806;
                break;

            case 4://Parc Pasteur
                m_nom = "Parc Pasteur";
                m_latitude = 47.907211;
                m_longitude = 1.911732;
                break;

            case 5://Ile Charlemagne
                m_nom = "Ile Charlemagne";
                m_latitude = 47.895222;
                m_longitude = 1.940746;
                break;

            case 6://Lab'O
                m_nom = "Lab'O";
                m_latitude = 47.893855;
                m_longitude = 1.894970;
                break;

            case 7://Parc Floral de la Source
                m_nom = "Parc Floral de la Source";
                m_latitude = 47.847492;
                m_longitude = 1.937771;
                break;

            case 8://Polytech Galilé
                m_nom = "Polytech Galilé";
                m_latitude = 47.843900;
                m_longitude = 1.929346;
                break;

            case 9://Zénith
                m_nom = "Zénith";
                m_latitude = 47.870430;
                m_longitude = 1.912569;
                break;

            default://Polytech Galilé
                m_nom = "Polytech Galilé";
                m_latitude = 47.843900;
                m_longitude = 1.929346;
                break;
        }
    }

    public void Addpreuve(CPreuve preuve)
    {
        m_preuve = preuve;
    }

    public String getM_nom() {
        return m_nom;
    }

    public double getM_latitude() {
        return m_latitude;
    }

    public double getM_longitude() {
        return m_longitude;
    }

    public CPreuve getM_preuve() {
        return m_preuve;
    }
}
