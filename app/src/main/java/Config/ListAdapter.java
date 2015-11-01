package Config;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import Model.DataObj;
import assignment.example.lifesafe.com.placesnerby.R;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements View
        .OnClickListener {
    public ArrayList<DataObj> mItemTypes;
    Context context;

    TextView nameView;



    public ListAdapter(ArrayList<DataObj> mItemTypes, Context context) {
        this.mItemTypes = new ArrayList<>(mItemTypes);
        this.context = context;

    }
    public void addItem(int position,DataObj name) {
        if (position > mItemTypes.size()) return;

        mItemTypes.add(position, name);
        notifyItemInserted(position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simplelistitem,
                parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


    }

    @Override
    public int getItemCount() {
        return mItemTypes.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameView;

        public ViewHolder(View itemView) {
            super(itemView);




        }

    }

}
