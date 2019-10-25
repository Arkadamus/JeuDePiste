package com.example.testmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends AppCompatActivity implements LocationListener {

    //Pour la permission
    private static final int PERMS_CALL_ID = 1234;

    private LocationManager locationManager;
    private MapFragment mapFragment;
    private GoogleMap googleMap;
    private Marker marker = null;

    private CLieu cLieu;
    private CProfile cProfile;
    private String nomJoueur;

    private Button btnRealiserTache;
    private Button btnOption;

    //region Chargement et methodes pour map
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.app.FragmentManager fragmentManager = getFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);

        btnOption = (Button) findViewById(R.id.btnOptions);
        btnRealiserTache = (Button) findViewById(R.id.btnRealiserTache);

        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickOption(v);
            }
        });

//        btnRealiserTache.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GenerateTache();
//            }
//        });

        nomJoueur = getIntent().getStringExtra("nom_du_joueur");

        CSave save = Serialize.Deserialization(this, "SaveCluedOrleans.ser");
        if (save != null && nomJoueur != null)
            for (CProfile profile : save.getM_listProfile()) {
                if (profile.equals(nomJoueur))
                    cProfile = profile;
            }
    }

    @Override
    // permet de passer outre le checkpermission mais c'est pas fou
    //@SuppressWarnings("MissingPermission")
    protected void onResume() {
        super.onResume();
        CheckPermissions();
    }

    //pour demander les permissions si elle ne les a pas
    private void CheckPermissions() {
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, PERMS_CALL_ID);
            return;
        }

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
        }

        if (locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 10000, 0, this);
        }

        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, this);
        }

        loadMap();
    }

    //force la permission si jms
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMS_CALL_ID) {
            CheckPermissions();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 1)
            GenerateTache();

        if (requestCode == 2) {
            String textVoice = data.getStringExtra("EXTRA_TEXTVOICE");

            if (ValidVoice(textVoice)) {
                ValidTache();
                Toast.makeText(this, "La tâche est accomplie", Toast.LENGTH_SHORT).show();
                GenerateTache();
            } else
                Toast.makeText(this, "La tâche n'est pas accomplie, veuillez essayer à nouveau", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }

    @SuppressWarnings("MissingPermission")
    private void loadMap() {
        //creer une interface
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                //capture l'objet de carto
                MainActivity.this.googleMap = googleMap;

                googleMap.setMyLocationEnabled(true);

                getCurrentLocation();
                //set la camera sur la position de l'utilisateur
                if (locationManager != null) {
                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    CameraPosition cameraPosition = new CameraPosition(latLng, 15, 0, 0);//latlng/zoom/0/0
                    googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//            TextView tvDescription = (TextView) findViewById(R.id.tvDescription);
////            tvDescription.setText("x : " + location.getLatitude() + "y : "+location.getLongitude() );
////            tvDescription.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        if (cLieu == null)
            GenerateTache();

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        if (NearLocation(latitude, longitude)) {
            btnRealiserTache.setEnabled(true);
        }
    }
    //endregion

    //region Boutons
    public void onClickOption(View v) {
        Intent intent = new Intent(this, Options.class);
        intent.putExtra("nomJoueur", nomJoueur);
        startActivityForResult(intent, 1);
    }
    //endregion


    public void onClickMicro(View v) {
        Intent intent = new Intent(this, Micro.class);
        startActivityForResult(intent, 0);
    }

    ///Methodes autres
    public void ValidTache() {
        //Ajouter le code pour l'inclure dans une list de CLieu accomplis dans une class CProfil
    }

    public void GenerateTache() {
        //btnRealiserTache.setEnabled(false);
        CPreuve cPreuve = CPreuve.GeneratePreuve();
        cLieu = new CLieu();
        cLieu.GeneratePlace();
        cLieu.Addpreuve(cPreuve);

        if (marker != null)
            marker.remove();

        if (googleMap != null)
            marker = googleMap.addMarker(new MarkerOptions().title(cLieu.getM_nom()).position(new LatLng(cLieu.getM_latitude(), cLieu.getM_longitude())));

        TextView tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvDescription.setText(cLieu.getM_preuve().getM_description());
        tvDescription.setMovementMethod(new ScrollingMovementMethod());
    }

    public boolean NearLocation(double latitude, double longitude) {
        boolean res = false;

        if (cLieu != null) {
            if (cLieu.getM_latitude() - latitude < Math.abs(0.00013) && cLieu.getM_longitude() - longitude < Math.abs(0.00013)) {
                res = true;
            }
        }
        return res;
    }

    //donne ? la donn?e locationManager la position
    public void getCurrentLocation() {
        try {
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
    //endregion

    public boolean ValidVoice(String textVoice) {
        boolean res = false;
        if (cLieu.getM_preuve().getM_preuve().toLowerCase() == textVoice.toLowerCase())
            res = true;

        return res;
    }


    ///Méthodes inutilisées mais insupprimables
    @Override
    public void onProviderDisabled(String s) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    //    @SuppressWarnings("MissingPermission")
    ////    public double[] CreateLieu(double rayon) {
    ////        double rayon = 0.013;//Rayon pour g?n?rer le lieu ( 1m=0.000013 (exp?rimental))
    ////        getCurrentLocation();
    ////        if (locationManager != null) {
    ////            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    ////
    ////            double latitude = location.getLatitude();
    ////            double longitude = location.getLongitude();
    ////
    ////
    ////            double x = Math.random();
    ////            x = x * rayon * 2 - rayon;//g?n?re un nombre en [-rayon;+rayon] sur l'axe x
    ////
    ////            double y = Math.random();
    ////            y = y * (rayon + 0.005) * 2 - (rayon + 0.005);//g?n?re un nombre en [-rayon;+rayon] sur l'axe y. Petit boost sur la longiotude pour avoir un meilleur cercle ???
    ////
    ////            double angle = Math.random();
    ////            angle = angle * 2 - 1;//g?n?re un nombre [-1;1] pour cr?er un angle et avoir un cercle
    ////            latitude += x * Math.cos(angle);
    ////            longitude += y * Math.sin(angle);
    ////
    ////            double res[] = {latitude, longitude};
    ////
    ////            //CLieu lieu = new CLieu(nom,latitude,longitude,preuve);
    ////            //Toast.makeText(this,"Location : " + location.getLatitude() + "/" + location.getLongitude(), Toast.LENGTH_LONG).show();
    ////
    ////            if (marker != null)
    ////                marker.remove();
    ////
    ////            marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));
    ////
    ////            return res;
    ////        }
    ////        return null;
    ////    }
    //endregion
}