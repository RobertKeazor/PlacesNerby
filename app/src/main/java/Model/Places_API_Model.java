package Model;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.api.GoogleApiClient;


public interface Places_API_Model {

     GoogleApiClient LoadClient(FragmentActivity context,GoogleApiClient.OnConnectionFailedListener listener,GoogleApiClient.ConnectionCallbacks callbacks);
     void okConnect ();
   void okDisconnect ();
    void okPlacePicker (Activity activity);
    void onePointIntent(Activity activity,String lat,String lon);
}
