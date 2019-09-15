package com.example.testmap;

public class CPreuve {
    private String m_nom, m_description;
    private int m_numPreuve;

    public CPreuve()
    {
        m_nom = m_description = null;
        m_numPreuve = -1;
    }

    public CPreuve(String nom, String description , int numPreuve )
    {
        m_nom=nom;
        m_description=description;

        if(numPreuve < 1)
            m_numPreuve = -1;
        else
            m_numPreuve = numPreuve;
    }

    public int getM_numPreuve() {
        return m_numPreuve;
    }

    public String getM_nom() {
        return m_nom;
    }

    public String getM_description() {
        return m_description;
    }
}

