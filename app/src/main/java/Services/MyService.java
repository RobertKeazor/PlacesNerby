package Services;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.widget.Toast;

import assignment.example.lifesafe.com.placesnerby.R;

public class MyService extends Service implements Runnable {

    LocationManager mLocationManager;
    Location mLocation;
    MyLocationListener mLocationListener;
    Location currentLocation = null;
    static SharedPreferences settings;
    static SharedPreferences.Editor configEditor;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        configEditor = settings.edit();
        writeSignalGPS();
    }

    private void setCurrentLocation(Location loc) {
        currentLocation = loc;
    }

    private void writeSignalGPS() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Looper.prepare();
            mLocationListener = new MyLocationListener();
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                    .PERMISSION_GRANTED && checkSelfPermission(Manifest.permission
                    .ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 1000, 0, mLocationListener);
            Looper.loop();
            //Looper.myLooper().quit();
        } else {
            Toast.makeText(getBaseContext(),
                    getResources().getString(R.string.gps_signal_not_found),
                    Toast.LENGTH_LONG).show();
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (currentLocation != null) {
                configEditor.putString("mylatitude", "" + currentLocation.getLatitude());
                configEditor.putString("mylongitude", "" + currentLocation.getLongitude());
                configEditor.commit();
            }
        }
    };

    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location loc) {
            if (loc != null) {
                Toast.makeText(getBaseContext(),
                        getResources().getString(R.string.gps_signal_found),
                        Toast.LENGTH_LONG).show();
                setCurrentLocation(loc);
                handler.sendEmptyMessage(0);
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }
}