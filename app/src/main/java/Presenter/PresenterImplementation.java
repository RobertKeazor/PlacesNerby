package Presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import Config.Key;
import EventHandler.BUS;
import Model.ClientSingleton;
import Model.Places_API_Model;
import Model.Places_API_Model_Implementatin;
import Model.RetroFace;
import Model.RetrofitModel;
import Presenter.NerbyPlacesPresenter;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class PresenterImplementation implements NerbyPlacesPresenter,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, Callback<RetrofitModel> {

  Places_API_Model apiModel= new Places_API_Model_Implementatin();
    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onCreate(FragmentActivity context) {
       ClientSingleton.getInstance().setClient(apiModel.LoadClient(context, this, this));

    }

    @Override
    public void onStart(Activity context) {
    apiModel.okPlacePicker(context);
    }

  @Override
  public void callWebService(String query,String location) {


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Key.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RetroFace apiCall =retrofit.create(RetroFace.class);
    Call<RetrofitModel> call = apiCall.loadApi(query,location);
    call.enqueue(this);
  }

  @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

  @Override
  public void onResponse(Response<RetrofitModel> response, Retrofit retrofit) {
    if (response.isSuccess()) {

      RetrofitModel results = response.body();


      BUS.getInstance().post(results);
    }
   else {
      Log.e("Error","ResponseFailed");

    }
  }

  @Override
  public void onFailure(Throwable t) {
    BUS.getInstance().post(t);

  }
}
