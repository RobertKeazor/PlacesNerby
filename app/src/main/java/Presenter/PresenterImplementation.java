package Presenter;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import Presenter.NerbyPlacesPresenter;

/**
 * Created by rob2cool on 11/1/15.
 */
public class PresenterImplementation implements NerbyPlacesPresenter,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {
    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onCreate(Context context) {

    }

    @Override
    public void onStart(Context context) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
