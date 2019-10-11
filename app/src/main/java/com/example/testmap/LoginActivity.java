package com.example.testmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText =(EditText)findViewById(R.id.editText_nom);//cherche le editText sinon crash, si on le cherche avant le onCreate ça crash
    }

    //lance une instance du jeu en passant le nom du joueur en argument
    public void commencerJeu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("nom_du_joueur",editText.getText().toString());
        startActivityForResult(intent, 0);
    }
}
