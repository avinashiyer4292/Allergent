package com.avinashiyer.allergent.adapters;

import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avinashiyer.allergent.R;
import com.avinashiyer.allergent.utils.AllergyObject;

/**
 * Created by avinashiyer on 2/25/17.
 */

public class AllergyViewHolder extends RecyclerView.ViewHolder{
    public TextView allergyName;
    public ImageView allergyPhoto;


    public AllergyViewHolder(View v){
        super(v);
        //v.setOnClickListener(this);
        allergyName = (TextView)itemView.findViewById(R.id.allergyCategory);
        allergyPhoto = (ImageView)itemView.findViewById(R.id.allergyBackgroundImage);


    }
    public void bind(final AllergyObject item, final AllergyRecyclerViewAdapter.RecyclerItemClickListener listener) {
        allergyName.setText(item.getName());
        allergyPhoto.setImageResource(item.getPhoto());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onRecyclerItemClick(item);
            }
        });
    }


}

