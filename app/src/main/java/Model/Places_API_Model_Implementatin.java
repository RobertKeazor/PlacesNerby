package Model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.Locale;


public class Places_API_Model_Implementatin implements Places_API_Model {
    private static final int REQUEST_PLACE_PICKER = 1;

    @Override
    public GoogleApiClient LoadClient(FragmentActivity context,GoogleApiClient.OnConnectionFailedListener listener,GoogleApiClient.ConnectionCallbacks callbacks) {
      GoogleApiClient  mGoogleApiClient = new GoogleApiClient
                .Builder( context)
                .enableAutoManage( context, 0, listener )
                .addApi( Places.GEO_DATA_API )
                .addApi( Places.PLACE_DETECTION_API )
                .addConnectionCallbacks(callbacks )
                .addOnConnectionFailedListener( listener )
                .build();
        return mGoogleApiClient;
    }

    @Override
    public void okConnect() {
        if( ClientSingleton.getInstance().getClient() != null )
            ClientSingleton.getInstance().getClient().connect();

    }

    @Override
    public void okDisconnect() {
        if (ClientSingleton.getInstance().getClient() != null && ClientSingleton.getInstance().getClient().isConnected()) {
            ClientSingleton.getInstance().getClient().disconnect();

        }
    }

    @Override
    public void okPlacePicker(Activity activity) {
        try {

            PlacePicker.IntentBuilder intentBuilder =
                    new PlacePicker.IntentBuilder();
            Intent intent = intentBuilder.build(activity);
            // Start the intent by requesting a result,
            // identified by a request code.
            activity.startActivityForResult(intent, REQUEST_PLACE_PICKER);

        } catch (GooglePlayServicesRepairableException e) {
            // ...
        } catch (GooglePlayServicesNotAvailableException e) {
            // ...
        }
    }
    public void onePointIntent(Activity activity,String lat,String lon){

        float latitude = Float.parseFloat(lat);
        float longitude=Float.parseFloat(lon);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format(Locale.US, "geo:%" +
                ".8f,%.8f", latitude, longitude)));
        intent.setPackage("com.google.android.apps.maps");

        activity.startActivity(Intent.createChooser(intent, "Select an application"));
    }
}
