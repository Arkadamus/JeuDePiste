package com.example.testmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CProfile implements Serializable {
    private String m_nom, m_password;
    private List<CLieu> m_listLieu;

    public CProfile() {
        m_nom = m_password = null;
        m_listLieu = new ArrayList<>();
    }

    public CProfile(String nom, String password, List<CLieu> listLieu) {
        m_nom = nom;
        m_password = password;
        m_listLieu = listLieu;
    }

    public boolean Login(String password)
    {
        boolean res = false;
        if(m_password.equals(password))
            res = true;

        return res;
    }

    public void EnregistrerProfil(String nom, String password)
    {
        m_nom = nom;
        m_password = password;
    }

    public String getM_nom() {
        return m_nom;
    }

    public List<CLieu> getM_listLieu() {
        return m_listLieu;
    }

    public String getM_password() {
        return m_password;
    }
}
