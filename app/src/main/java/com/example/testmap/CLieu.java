package com.example.testmap;

public class CLieu {
    private double m_latitude,m_longitude;
    private String m_nom, m_preuve;

    CLieu()
    {
        m_latitude = m_longitude = 0.0;
        m_nom = m_preuve = null;
    }

    CLieu(String nom, double latitude, double longitude, String preuve)
    {
        m_nom=nom;
        m_latitude=latitude;
        m_longitude=longitude;
        m_preuve=preuve;
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

    public String getM_preuve() {
        return m_preuve;
    }
}
