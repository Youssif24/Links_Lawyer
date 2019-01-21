package com.saad.youssif.arabiclawyer.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.Model.DelegationDB;
import com.saad.youssif.arabiclawyer.R;

import java.util.List;

import static com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype.Slit;

public class FragDelegationAdapter extends RecyclerView.Adapter<FragDelegationAdapter.DelegationViewHolder> {
    private List<DelegationDB> delegationsList;
    Context ctx;
    DBHelper dbHelper;

    public FragDelegationAdapter(List<DelegationDB> delegationsList, Context context){
        this.delegationsList = delegationsList;
        this.ctx=context;
        dbHelper=new DBHelper(ctx);


    }



    @NonNull
    @Override
    public FragDelegationAdapter.DelegationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frag_delegations_recycler_items,viewGroup,false);
        FragDelegationAdapter.DelegationViewHolder viewHolder = new FragDelegationAdapter.DelegationViewHolder(layoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FragDelegationAdapter.DelegationViewHolder delegationViewHolder, int i) {
        DelegationDB delegationDB=delegationsList.get(i);
        //delegationViewHolder.del_client_name.setText(delegationsList.get(i).getDel_client_name());
        delegationViewHolder.del_num.setText("رقم التوكيل:  "+delegationDB.getDel_num());
        delegationViewHolder.del_org.setText("جهة التوثيق :  "+delegationDB.getDel_org());
        delegationViewHolder.del_date.setText("تاريخ التوثيق:  "+delegationDB.getDel_date());
        // sittingViewHolder.sitting_judgment.setText("الحكم:  "+sittingDB.getJudgment());


    }


    @Override
    public int getItemCount() {
        return delegationsList.size();
    }

    public static class DelegationViewHolder extends RecyclerView.ViewHolder {
        TextView del_num,del_client_name,del_org,del_date;
        ImageView deleteImg;

        public DelegationViewHolder(View item) {
            super(item);
            del_num=item.findViewById(R.id.frag_item_del_num);
            //del_client_name=item.findViewById(R.id._item_del_client_name);
            del_org=item.findViewById(R.id.frag_item_del_org);
            del_date=item.findViewById(R.id.frag_item_del_date);
           // deleteImg=item.findViewById(R.id.delegation_deleteImg);

        }


    }
}