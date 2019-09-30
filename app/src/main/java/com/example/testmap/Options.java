package com.example.testmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Options extends AppCompatActivity {

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
    }
}
