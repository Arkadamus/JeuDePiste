package com.example.testmap;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Math.abs;

public class AcceleroActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    double valAccX;
    double valAccY;
    double valAccZ;

    int axeAccMaxBuf; // valeur précédente de axeAccMax
    int axeAccMax; // X=1  Y=2  Z=3
    int cmptAccMax = 0; // comptabilise le nombre de mesure où l'orientation principale ne change pas

    boolean gameOver;

    private char lettre_dessiner;
    int etape_dessin = 1;

    TextView TextViewLettre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelero);

        Intent intent = getIntent();

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, 100000);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, 100000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            valAccX = event.values[0];
            valAccY = event.values[1];
            valAccZ = event.values[2];
        }

        axeAccMaxBuf = axeAccMax;

        //axeAccMax: X=1  Y=2  Z=3
        if (abs(valAccX) >= abs(valAccY) && abs(valAccX) >= abs(valAccZ)) axeAccMax = 1;
        if (abs(valAccY) >= abs(valAccZ)) axeAccMax = 2;
        else axeAccMax = 3;

        //si l'accélération max change d'axe on réinitialise le compteur
        if (axeAccMaxBuf != axeAccMax) cmptAccMax = 0;
/*
        //incrémente le compteur de conservation de l'orientation en évitant l'overflow
        if(axeAccMaxBuf==axeAccMax){
            cmptAccMax%=1000000;
            cmptAccMax++;
        }
*/
        checkMouvement(); //getCurrentFocus()?
        if (cmptAccMax >= 200) {
            etape_dessin++;
        }
        if (gameOver) finDuJeu();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void checkMouvement() {
        //int cmpt; //réinitialise le compteur pour valider le mouvement

        if (lettre_dessiner == 'L') {
            //si on en est à la première étape du dessin et que l'acceleration est max selon Z on incrémente
            if (etape_dessin == 1 && axeAccMax == 3 && valAccZ > 10) {
                cmptAccMax++;
            }

            if (etape_dessin == 2 && axeAccMax == 1 && valAccX > 10) {
                cmptAccMax++;
            }

            if (etape_dessin == 3) {
                gameOver = true;
            }
        }

        if (lettre_dessiner == 'I') {
            if (etape_dessin == 1 && axeAccMax == 3 && valAccZ > 10) {
                cmptAccMax++;
            }

            if (etape_dessin == 2) {
                gameOver = true;
            }
        }

        if (lettre_dessiner == 'T') {
            if (etape_dessin == 1 && axeAccMax == 1 && valAccZ > 10) {
                cmptAccMax++;
            }

            if (etape_dessin == 2 && axeAccMax == 3 && valAccX > 10) {
                cmptAccMax++;
            }

            if (etape_dessin == 3) {
                gameOver = true;
            }
        }

        if (lettre_dessiner == 'U') {
            if (etape_dessin == 1 && axeAccMax == 3 && valAccZ > 10) {
                cmptAccMax++;
            }

            if (etape_dessin == 2 && axeAccMax == 1 && valAccX > 10) {
                cmptAccMax++;
            }

            if (etape_dessin == 3 && axeAccMax == 3 && valAccX < 10) {
                cmptAccMax++;
            }

            if (etape_dessin == 4) {
                gameOver = true;
            }
        }
    }

    private void finDuJeu() {
        mSensorManager.unregisterListener(this);
        Toast.makeText(getApplicationContext(), "Gagné!", Toast.LENGTH_SHORT).show();
    }
}
