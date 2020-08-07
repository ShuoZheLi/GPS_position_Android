package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.OnNmeaMessageListener;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        final Location[] prevPos = {null};
        LocationListener locationListener = new LocationListener()
        {
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                // TODO Auto-generated method stub

            }
            @Override
            public void onProviderEnabled(String provider) {
                // TODO Auto-generated method stub

            }
            @Override
            public void onProviderDisabled(String provider) {
                // TODO Auto-generated method stub

            }
            @Override
            public void onLocationChanged(Location location) {
                // TODO Auto-generated method stub
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                if(prevPos[0] == null){
                    prevPos[0] = location;
                }else{
                    float distance = location.distanceTo(prevPos[0]);
                    prevPos[0] = location;
                    if(distance > 1){
                        Toast.makeText(MainActivity.this, "moved 1 meter",Toast.LENGTH_SHORT).show();
                    }
                }
                Log.d("Hauoli", "long" +location.getLongitude() +"\t"+ location.getLatitude());
                //Toast.makeText(MainActivity.this, "lo " +location.getLongitude() +" la "+ location.getLatitude(),Toast.LENGTH_SHORT).show();
            }
        };
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }
}
