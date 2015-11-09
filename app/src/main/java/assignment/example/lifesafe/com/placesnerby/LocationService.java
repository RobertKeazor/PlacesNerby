package assignment.example.lifesafe.com.placesnerby;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;

import android.util.Log;
import android.widget.Toast;


import assignment.example.lifesafe.com.placesnerby.R;

public class LocationService extends Service implements Runnable {

    LocationManager mLocationManager;
    Location mLocation;
    MyLocationListener mLocationListener;
    Location currentLocation = null;
    static SharedPreferences settings;
    static SharedPreferences.Editor configEditor;
    boolean stopTheLoop;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        configEditor = settings.edit();
        stopTheLoop=false;
        writeSignalGPS();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {

        handler.removeCallbacksAndMessages(null);



        Log.v("Service Terminating","Good");
        super.onDestroy();
    }

    private void setCurrentLocation(Location loc) {
        currentLocation = loc;
    }

    private void writeSignalGPS() {
        Thread thread = new Thread(this);
        thread.start();

    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void run() {

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Looper.prepare();
            mLocationListener = new MyLocationListener();
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission
                    .ACCESS_FINE_LOCATION) != PackageManager
                    .PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission
                    .ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 1000, 0, mLocationListener);
            if (stopTheLoop){Looper.getMainLooper().quitSafely();}
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

                Log.v("Coordinates", loc.getLatitude() + "");
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