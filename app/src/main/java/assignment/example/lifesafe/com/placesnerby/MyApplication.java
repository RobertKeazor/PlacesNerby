package assignment.example.lifesafe.com.placesnerby;

import android.app.Application;
import android.content.Intent;

import Services.LocationService;

public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, LocationService.class));
    }
}