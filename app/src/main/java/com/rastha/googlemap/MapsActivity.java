package com.rastha.googlemap;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng ktm = new LatLng(27.674804, 85.239202);
        mMap.addMarker(new MarkerOptions().position(ktm).title("Marker in Kathmandu"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ktm,12f));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ktm));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title("Pick Here"));
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));

                LatLng pos = marker.getPosition();
                Toast.makeText(MapsActivity.this, "Point coordinates: " + pos.latitude
                        + ", " + pos.longitude, Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MapsActivity.this,SignupActivity.class);
                intent.putExtra("Longitude",pos.longitude);
                intent.putExtra("Latitude",pos.latitude);
                startActivity(intent);
                finish();
            }

        });
    }
}
