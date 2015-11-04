package Presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;


public interface NerbyPlacesPresenter {

    void onCreate (FragmentActivity context);
    void onStart (Activity context);
    void callWebService(String query,String location);
}
