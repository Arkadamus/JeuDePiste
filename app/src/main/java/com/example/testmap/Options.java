package com.example.testmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Options extends AppCompatActivity {

    private CProfile cProfile;

    private Button btnChangerLieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        btnChangerLieu = (Button) findViewById(R.id.btnChangerLieu);

        btnChangerLieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Nouveau lieu";
                Intent intent=new Intent();
                intent.putExtra("NvLieu",message);
                setResult(1,intent);//pour dire de générer un nouveau cLieu
                finish();
            }
        });

        String temp = getIntent().getStringExtra("nomJoueur");
        CSave save = Serialize.Deserialization(this, "SaveCluedOrleans.ser");
        if (save != null && temp != null)
            for (CProfile profile : save.getM_listProfile()) {
                if (profile.equals(temp))
                    cProfile = profile;
            }
    }
}
