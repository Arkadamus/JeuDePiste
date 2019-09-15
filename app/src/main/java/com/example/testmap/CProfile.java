package com.example.testmap;

import java.util.List;

public class CProfile {
    private String m_nom, m_password;
    private double m_kmmarche;
    private List<CLieu> m_listLieu;

    public CProfile()
    {
        m_nom = m_password = null;
        m_kmmarche =0.0;
        m_listLieu = null;
    }

    public CProfile(String nom, String password, double kmmarche, List<CLieu> listLieu)
    {
        m_nom = nom;
        m_password = password;
        m_kmmarche = kmmarche;
        m_listLieu = listLieu;
    }

    public String getM_nom() {
        return m_nom;
    }

    public List<CLieu> getM_listLieu() {
        return m_listLieu;
    }

    public double getM_kmmarche() {
        return m_kmmarche;
    }

    public String getM_password() {
        return m_password;
    }
}
