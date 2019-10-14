package com.example.testmap;

import java.io.Serializable;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

public class CSave implements Serializable {
    private List<CProfile> m_listProfile;

    public CSave() {
        m_listProfile = new ArrayList<>();
    }

    public CSave(List<CProfile> listProfile) {
        m_listProfile = listProfile;
    }

    public List<CProfile> getM_listProfile() {
        return m_listProfile;
    }
}
