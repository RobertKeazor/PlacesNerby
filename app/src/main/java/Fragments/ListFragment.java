package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import Config.ListAdapter;
import EventHandler.BUS;
import Model.Results;
import Model.RetrofitModel;
import assignment.example.lifesafe.com.placesnerby.R;

/**
 * Created by rob2cool on 11/2/15.
 */
public class ListFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager manager;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
         v = inflater.inflate(R.layout.list_fragment, container, false);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        BUS.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BUS.getInstance().unregister(this);
    }
    @Subscribe
    public void LoadRecyclerView (RetrofitModel datamodel)
    {
        Toast.makeText(getActivity(),"LOOKING", Toast.LENGTH_SHORT).show();
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        mAdapter=new ListAdapter( datamodel.getResults(),getActivity());
        recyclerView.setAdapter(mAdapter);

    }
}
