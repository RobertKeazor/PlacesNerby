package Config;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Model.DataObj;
import Model.Results;
import Model.RetrofitModel;
import assignment.example.lifesafe.com.placesnerby.R;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements View
        .OnClickListener {
    public ArrayList<Results> mItemTypes;
    Context context;

    TextView nameView;



    public ListAdapter(ArrayList<Results> mItemTypes, Context context) {
        this.mItemTypes = new ArrayList<>(mItemTypes);
        this.context = context;

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
     holder.nameView.setText(mItemTypes.get(position).getName());
     holder.addressView.setText(mItemTypes.get(position).getFormatted_address());
        try {

            Picasso.with(context).
                    load(Key.PHOTOS_URL_BASE + mItemTypes.get(position).photos.get(0)
                            .getPhoto_reference()).into(holder.image);
        }catch (Exception e){
            holder.image.setImageResource(R.drawable.noimage);
        }
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
        TextView addressView;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
         nameView = (TextView) itemView.findViewById(R.id.name);
            addressView= (TextView) itemView.findViewById(R.id.address);
            image= (ImageView) itemView.findViewById(R.id.img_thumbnail);


        }

    }

}
