package com.rastha.googlemap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShowMap extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent i = getIntent();
        String lat1 = i.getStringExtra("latitude");
        String lng1 = i.getStringExtra("longitude");
        String name = i.getStringExtra("name");
        final Double lat= Double.parseDouble(lat1);
        final Double lng= Double.parseDouble(lng1);
        // Add a marker in Sydney and move the camera
        LatLng ktm = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(ktm).title("User:"+name));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ktm,12f));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ktm));

    }
}
