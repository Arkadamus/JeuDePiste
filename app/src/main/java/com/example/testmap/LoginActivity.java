package com.example.testmap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;

import java.io.File;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity {

    private Button btnValider;
    private Button btnEnregistrer;

    private EditText etText;
    private EditText etPassword;

    final String fileName = "SaveCluedOrleans.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Serialize.ResetFile(this,fileName);
        btnEnregistrer = (Button) findViewById(R.id.btnEnregistrer);
        btnValider = (Button) findViewById(R.id.btnValider);

        btnEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Enregistrer(v);
            }
        });
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login(v);
            }
        });

        etText = (EditText) findViewById(R.id.etNom);//cherche le editText sinon crash, si on le cherche avant le onCreate ça crash
        etPassword = (EditText) findViewById(R.id.etPassword);//cherche le editText sinon crash, si on le cherche avant le onCreate ça crash
    }

    ///Buttons

    //lance une instance du jeu en passant le nom du joueur en argument
    public void Login(View v) {
        CSave save = Serialize.Deserialization(this, "SaveCluedOrleans.ser");
        boolean temp = false;

        if (save != null) {
            for (CProfile profile : save.getM_listProfile()) {
                if (profile != null) {
                    if (profile.Login(etPassword.getText().toString())) {
                        CommencerJeu(v);
                        temp = true;
                    }
                }
            }
        }

        if (!temp)
            Toast.makeText(this, "Mot de passe incorrect", Toast.LENGTH_LONG).show();
    }


    //Enregistre un nouvel utilisateur
    public void Enregistrer(View v) {
        if (etText.getText().toString() != "" && etPassword.getText().toString() != "") {
            CProfile profile = new CProfile();
            profile.EnregistrerProfil(etText.getText().toString(), etPassword.getText().toString());

            CSave save = Serialize.Deserialization(this, fileName);
            if (save != null) {
                if (!save.getM_listProfile().contains(profile)) {
                    if (etPassword.length() > 2) {
                        save.getM_listProfile().add(profile);
                        Serialize.Serialization(this, save, fileName);

                        Toast.makeText(this, "Profil enregistré", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Veuillez saisir au moins 2 caractères pour le mot de passe", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Profil déjà enregistré", Toast.LENGTH_LONG).show();
                }
            } else {
                CSave temp = new CSave();
                temp.getM_listProfile().add(profile);
                Serialize.Serialization(this, temp, fileName);

                Toast.makeText(this, "Profil enregistré", Toast.LENGTH_LONG).show();
            }
        } else
            Toast.makeText(this, "Remplir les champs", Toast.LENGTH_LONG).show();
    }

    ///Autres méthodes
    public void CommencerJeu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("nom_du_joueur", etText.getText().toString());
        startActivityForResult(intent, 0);
    }
}
