package assignment.example.lifesafe.com.placesnerby;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.squareup.otto.Subscribe;

import EventHandler.BUS;
import Fragments.ListFragment;
import Model.Coordinates;
import Model.Places_API_Model;
import Model.Places_API_Model_Implementatin;
import Model.RetrofitModel;
import Presenter.NerbyPlacesPresenter;
import Presenter.PresenterImplementation;
import butterknife.Bind;
import butterknife.ButterKnife;

public class NerbyPlace extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final int REQUEST_PLACE_PICKER = 1;
    private SearchView mSearchView;
    private SharedPreferences pref;
    private MenuItem searchMenuItem;
    private String coordinates;
    ActionBar actionbar;
    NerbyPlacesPresenter mPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nerby_place);
        mPlaces = new PresenterImplementation();
        mPlaces.onCreate(this);
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction transaction =fragmentManager.beginTransaction();
        ListFragment mGridViewFragment= new ListFragment();
        transaction.add(R.id.container,mGridViewFragment,"frag1");
        transaction.commit();
        actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.background));
    }

    @Override
    protected void onPause() {
        BUS.getInstance().unregister(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BUS.getInstance().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        searchMenuItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchMenuItem.getActionView();
        mSearchView.setOnQueryTextListener(this);
        return true;

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
       pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        coordinates= pref.getString("mylatitude","40.748817")+","+pref.getString("mylongitude","-73.985428");
        Toast.makeText(NerbyPlace.this, pref.getString("mylatitude","Sorry"), Toast.LENGTH_SHORT).show();
        mPlaces.callWebService(query, coordinates);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


    @Subscribe

    public void onReceive(RetrofitModel data) {

        Toast.makeText(NerbyPlace.this, data.results.get(1).toString(), Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onReceive (Coordinates coordinates){
        Toast.makeText(NerbyPlace.this, coordinates.getLat()+""+"Welll well", Toast.LENGTH_SHORT).show();

        Places_API_Model placepicker= new Places_API_Model_Implementatin();
        placepicker.onePointIntent(this,coordinates.getLat(),coordinates.getLon());

    }


}