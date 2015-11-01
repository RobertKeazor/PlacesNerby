package Model;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.api.GoogleApiClient;


public interface Places_API_Model {

     GoogleApiClient LoadClient(FragmentActivity context,GoogleApiClient.OnConnectionFailedListener listener,GoogleApiClient.ConnectionCallbacks callbacks);
     void okConnect (GoogleApiClient mclient,Context context);
   void okDisconnect (GoogleApiClient mclient,Context context);
}
