package Model;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;

/**
 * Created by rob2cool on 11/1/15.
 */
public class Places_API_Model_Implementatin implements Places_API_Model {
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
    public void okConnect(GoogleApiClient mClient, Context context) {
        if( mClient != null )
            mClient.connect();

    }

    @Override
    public void okDisconnect(GoogleApiClient mClient, Context context) {
        if (mClient != null && mClient.isConnected()) {
            mClient.disconnect();

        }
    }
}
