package com.example.testmap;

public class CPreuve {
    private String m_nom, m_description;
    private int m_enumPreuve;

    public enum typePreuve //7 choix
    {
        Mouvement,
        Pas,
        Distance,
        Photo,
        Video,
        Parler,
        Enregistrer

    }

    public CPreuve()
    {
        m_nom = m_description = null;
        m_enumPreuve = -1;
    }

    public CPreuve(String nom, String description , int enumPreuve )
    {
        m_nom=nom;
        m_description=description;

        if(enumPreuve < 1)
            m_enumPreuve = -1;
        else
            m_enumPreuve = enumPreuve;
    }

    public static int GeneratePas()
    {
        int res = (int) Math.round((Math.random()*750+250));
        return res;
    }

    public int getM_numPreuve() {
        return m_enumPreuve;
    }

    public String getM_nom() {
        return m_nom;
    }

    public String getM_description() {
        return m_description;
    }
}

