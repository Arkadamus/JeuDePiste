package com.example.testmap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CProfile implements Serializable {
    private String m_nom, m_password;
    private double m_kmmarche;
    private List<CLieu> m_listLieu;

    public CProfile() {
        m_nom = m_password = null;
        m_kmmarche = 0.0;
        m_listLieu = new ArrayList<>();
    }

    public CProfile(String nom, String password, double kmmarche, List<CLieu> listLieu) {
        m_nom = nom;
        m_password = password;
        m_kmmarche = kmmarche;
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

    public double getM_kmmarche() {
        return m_kmmarche;
    }

    public String getM_password() {
        return m_password;
    }
}
