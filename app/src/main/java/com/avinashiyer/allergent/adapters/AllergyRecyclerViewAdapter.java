package com.avinashiyer.allergent.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avinashiyer.allergent.R;
import com.avinashiyer.allergent.utils.AllergyObject;

import java.util.List;

/**
 * Created by avinashiyer on 2/25/17.
 */

public class AllergyRecyclerViewAdapter extends RecyclerView.Adapter<AllergyViewHolder> {
    private List<AllergyObject> itemList;
    private Context context;
    private RecyclerItemClickListener listener;
    public AllergyRecyclerViewAdapter(Context context, List<AllergyObject> itemList,RecyclerItemClickListener listener) {
        this.itemList = itemList;
        this.context = context;
        this.listener = listener;
    }
    @Override
    public AllergyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.allergy_cardview_layout, null);
        AllergyViewHolder rcv = new AllergyViewHolder(layoutView);

        return rcv;
    }

    @Override
    public void onBindViewHolder(AllergyViewHolder holder, int position) {
//        holder.allergyName.setText(itemList.get(position).getName());
//        holder.allergyPhoto.setImageResource(itemList.get(position).getPhoto());
        holder.bind(itemList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }


    public interface RecyclerItemClickListener {
        void onRecyclerItemClick(AllergyObject item);
    }
}
