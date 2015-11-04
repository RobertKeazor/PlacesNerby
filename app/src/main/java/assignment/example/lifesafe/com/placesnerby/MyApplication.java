package assignment.example.lifesafe.com.placesnerby;

import android.app.Application;
import android.content.Intent;

public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, LocationService.class));
    }
}