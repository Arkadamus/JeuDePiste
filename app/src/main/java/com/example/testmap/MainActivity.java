package com.example.testmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.TokenWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements LocationListener {

    //Pour la permission
    private static final int PERMS_CALL_ID = 1234;

    private LocationManager lm;
    private MapFragment mapFragment;
    private GoogleMap googleMap;

    ///Chargement et méthodes pour map

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.app.FragmentManager fragmentManager = getFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);

        Button btnOption = (Button) findViewById(R.id.btnOptions);

        btnOption.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickOption(v);
            }
        });

    }

    @Override
    // permet de passer outre le checkpermission mais c'est pas fou
    //@SuppressWarnings("MissingPermission")
    protected void onResume() {
        super.onResume();

        CheckPermissions();
    }

    //pour demander les permissions si elle ne les a pas
    private void CheckPermissions(){
        if((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)){

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, PERMS_CALL_ID);
            return;
        }

        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if(lm.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000,0,this);
        }

        if(lm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)){
            lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 10000,0,this);
        }

        if(lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000,0,this);
        }

        loadMap();
    }

    //force la permission si jms
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode == PERMS_CALL_ID){
            CheckPermissions();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if( lm != null)
        {
            lm.removeUpdates(this);
        }
    }

    @SuppressWarnings("MissingPermission")
    private void loadMap()
    {
        //creer une interface
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                //capture l'objet de carto
                MainActivity.this.googleMap = googleMap;

                googleMap.moveCamera(CameraUpdateFactory.zoomBy(15));
                googleMap.setMyLocationEnabled(true);
                googleMap.addMarker(new MarkerOptions().position( new LatLng(45, 2)).title("test"));
            }
        });
    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        //messagebox.show()
        Toast.makeText(this,"Location : " + latitude + "/" + longitude, Toast.LENGTH_LONG).show();
        if(googleMap != null)
        {
            LatLng googleLocation = new LatLng(latitude,longitude);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(googleLocation));
        }
    }

    ///Boutons
    public void onClickOption(View v)
    {
        Intent intent = new Intent(this,Options.class);
        startActivity(intent);
    }

    ///Methodes autres
    public CLieu CreateLieu(double distance)
    {
        Random random = new Random();
        int randInt = random.nextInt();

        return null;
    }

}

