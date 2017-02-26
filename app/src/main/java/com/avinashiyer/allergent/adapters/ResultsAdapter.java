package com.avinashiyer.allergent.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.avinashiyer.allergent.R;

import java.util.ArrayList;

/**
 * Created by avinashiyer on 2/25/17.
 */

public class ResultsAdapter extends BaseAdapter {

    private ArrayList<String> allergies;
    private Context context;
    public ResultsAdapter(Context context, ArrayList<String> allergies){
        this.context = context;
        this.allergies = allergies;
    }
    @Override
    public int getCount() {
        return allergies.size();
    }

    @Override
    public Object getItem(int i) {
        return allergies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = mInflater.inflate(R.layout.result_list_item, null);
            holder = new ViewHolder();
            holder.tv = (TextView) view.findViewById(R.id.resultText);

            holder.img= (ImageView) view.findViewById(R.id.resultImage);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }



        return view;
    }

    class ViewHolder
    {
        TextView tv;
        ImageView img;

    }
}
