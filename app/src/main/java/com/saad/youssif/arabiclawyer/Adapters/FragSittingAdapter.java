package com.saad.youssif.arabiclawyer.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.Model.SittingDB;
import com.saad.youssif.arabiclawyer.R;

import java.util.List;


public class FragSittingAdapter extends RecyclerView.Adapter<FragSittingAdapter.SittingViewHolder> {
    private List<SittingDB> sittingList;
    Context ctx;

    public FragSittingAdapter(List<SittingDB> sittingList, Context context){
        this.sittingList = sittingList;
        this.ctx=context;

    }



    @NonNull
    @Override
    public FragSittingAdapter.SittingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frag_sitting_recycler_items,viewGroup,false);
        FragSittingAdapter.SittingViewHolder viewHolder = new FragSittingAdapter.SittingViewHolder(layoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FragSittingAdapter.SittingViewHolder sittingViewHolder, int i) {
        SittingDB sittingDB=sittingList.get(i);
       // sittingViewHolder.sitting_client.setText(sittingList.get(i).getClient_name());
        sittingViewHolder.issue_num.setText("رقم القضية:  "+sittingDB.getSitting_issue_num());
        sittingViewHolder.sitting_opponent.setText("إسم الخصم :  "+sittingDB.getOpponent_name());
        sittingViewHolder.sitting_brol.setText("رقم البرول:  "+sittingDB.getBrol_num());
       // sittingViewHolder.sitting_delay.setText("تاريخ التأجيل:  "+sittingDB.getDelay_date());
       // sittingViewHolder.sitting_judgment.setText("الحكم:  "+sittingDB.getJudgment());

    }


    @Override
    public int getItemCount() {
        return sittingList.size();
    }

    public static class SittingViewHolder extends RecyclerView.ViewHolder {
        TextView issue_num,sitting_client,sitting_opponent,sitting_brol,sitting_delay,sitting_judgment;
        ImageView deleteImg;

        public SittingViewHolder(View item) {
            super(item);
            issue_num=item.findViewById(R.id.frag_sitting_issue_num);
           // sitting_client=item.findViewById(R.id.sitting_client_name);
            sitting_opponent=item.findViewById(R.id.frag_sitting_opponent);
            sitting_brol=item.findViewById(R.id.frag_sitting_brol);
           // sitting_delay=item.findViewById(R.id.sitting_delay);
            //sitting_judgment=item.findViewById(R.id.sitting_judgment);
           // deleteImg=item.findViewById(R.id.sitting_deleteImg);

        }


    }
}
