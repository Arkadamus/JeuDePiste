package com.example.testmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Options extends AppCompatActivity {

    private CProfile cProfile;

    private Button btnChangerLieu;
    private ListView lvProfil;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        btnChangerLieu = (Button) findViewById(R.id.btnChangerLieu);
        lvProfil = (ListView) findViewById(R.id.lvProfil);
        tvName = (TextView) findViewById(R.id.tvName);

        btnChangerLieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangerLieu();
            }
        });

        String tmpNom = getIntent().getStringExtra("nomJoueur");
        CSave save = Serialize.Deserialization(this, "SaveCluedOrleans.ser");
        if (save != null && tmpNom != null) {
            for (CProfile profile : save.getM_listProfile()) {
                if (profile.getM_nom().equals(tmpNom))
                    cProfile = profile;
            }

            if (cProfile != null) {
                tvName.setText(cProfile.getM_nom());
                SetListView();
            }
        }
    }

    ///Buttons
    public void ChangerLieu() {
        String message = "Nouveau lieu";
        Intent intent = new Intent();
        intent.putExtra("NvLieu", message);
        setResult(1, intent);//pour dire de générer un nouveau cLieu
        finish();
    }

    ///Méthodes autres
    public void SetListView() {
        ArrayList<CLieu> listLieu = (ArrayList<CLieu>) cProfile.getM_listLieu();
        CLieuAdapter adapter = new CLieuAdapter(this, listLieu);
        lvProfil.setAdapter(adapter);
    }
}


