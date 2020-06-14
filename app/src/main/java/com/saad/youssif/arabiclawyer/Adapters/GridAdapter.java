package com.saad.youssif.arabiclawyer.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.saad.youssif.arabiclawyer.R;

public class GridAdapter extends BaseAdapter {
    private Context mContext;

    // Constructor
    public GridAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView grid_img;
        TextView grid_text;

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.gridview_layout, null);
        }

            grid_img =convertView.findViewById(R.id.grid_img);
            grid_text=convertView.findViewById(R.id.grid_text);


        grid_img.setImageResource(mThumbIds[position]);
        grid_text.setText(gridTextList[position]);
        return convertView;
    }

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.ic_team, R.drawable.ic_sitting,
            R.drawable.ic_issue, R.drawable.ic_auth,
            R.drawable.ic_notebook, R.drawable.ic_logout
    };

    public String [] gridTextList={"الموكلين","الجلسات","القضايا","التوكيلات","الأجندة","خروج"};

}
