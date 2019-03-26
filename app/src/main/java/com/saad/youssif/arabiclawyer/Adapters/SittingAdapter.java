package com.saad.youssif.arabiclawyer.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.saad.youssif.arabiclawyer.Activities.DelaySitting;
import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.Model.IssueDB;
import com.saad.youssif.arabiclawyer.Model.SittingDB;
import com.saad.youssif.arabiclawyer.R;
import com.saad.youssif.arabiclawyer.View.Update_View;

import java.util.List;

import static com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype.Fliph;
import static com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype.Slidetop;

public class SittingAdapter extends RecyclerView.Adapter<SittingAdapter.SittingViewHolder> {
    private List<SittingDB> sittingList;
    Context ctx;
    DBHelper dbHelper;

    public SittingAdapter(List<SittingDB> sittingList, Context context){
        this.sittingList = sittingList;
        this.ctx=context;
        dbHelper=new DBHelper(ctx);

    }



    @NonNull
    @Override
    public SittingAdapter.SittingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sitting_recycler_items,viewGroup,false);
        SittingViewHolder viewHolder = new SittingViewHolder(layoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SittingAdapter.SittingViewHolder sittingViewHolder, int i) {
        SittingDB sittingDB=sittingList.get(i);
        sittingViewHolder.sitting_client.setText(sittingList.get(i).getClient_name());
        sittingViewHolder.issue_num.setText("رقم القضية:  "+sittingDB.getSitting_issue_num());
        sittingViewHolder.sitting_opponent.setText("إسم الخصم :  "+sittingDB.getOpponent_name());
        sittingViewHolder.sitting_brol.setText("رقم الرول:  "+sittingDB.getBrol_num());
        //sittingViewHolder.sitting_delay.setText("تاريخ التأجيل:  "+sittingDB.getDelay_date());
        sittingViewHolder.sitting_judgment.setText("الحكم:  "+sittingDB.getJudgment());

        sittingViewHolder.deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int position = sittingViewHolder.getAdapterPosition(); //get position which is swipe

                final NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(ctx);

                dialogBuilder
                        .withTitle("حذف جلسة")
                        .withTitleColor("#FFFFFF")
                        .withDividerColor("#11000000")
                        .withMessage("هل تريد حذف هذه الجلسة ؟")
                        .withMessageColor("#FFFFFFFF")
                        .withDialogColor("#3D5069")
                        .withIcon(R.drawable.delete_forever_24dp)
                        .withDuration(600)
                        .withEffect(Fliph)
                        .withButton1Text("إلغاء")
                        .withButton2Text("حذف")
                        .isCancelableOnTouchOutside(false)

                        //.setCustomView(R.layout.alert_dialog_custom_view,v.getContext())         //.setCustomView(View or ResId,context)
                        .setButton1Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialogBuilder.dismiss();
                                return;
                            }
                        })
                        .setButton2Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dbHelper.deleteSitting(String.valueOf(sittingList.get(position).getId()));
                                dbHelper.deleteDelaySitting(String.valueOf(sittingList.get(position).getId()));
                                sittingList.remove(position);
                                notifyItemRemoved(position);
                                notifyDataSetChanged();
                                dialogBuilder.dismiss();
                                return;
                            }
                        })
                        .show();
            }
        });

        sittingViewHolder.delayImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int position2 = sittingViewHolder.getAdapterPosition(); //get position which is swipe
                Intent delayIntent=new Intent(ctx, DelaySitting.class);
                delayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                delayIntent.putExtra("id",String.valueOf(sittingList.get(position2).getId()));
                delayIntent.putExtra("d_date",String.valueOf(sittingList.get(position2).getDelay_date()));
                ctx.getApplicationContext().startActivity(delayIntent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return sittingList.size();
    }

    public static class SittingViewHolder extends RecyclerView.ViewHolder {
        TextView issue_num,sitting_client,sitting_opponent,sitting_brol,sitting_delay,sitting_judgment;
        ImageView deleteImg,delayImg;

        public SittingViewHolder(View item) {
            super(item);
            issue_num=item.findViewById(R.id.sitting_issue_num);
            sitting_client=item.findViewById(R.id.sitting_client_name);
            sitting_opponent=item.findViewById(R.id.sitting_opponent);
            sitting_brol=item.findViewById(R.id.sitting_brol);
            //sitting_delay=item.findViewById(R.id.sitting_delay);
            sitting_judgment=item.findViewById(R.id.sitting_judgment);
            deleteImg=item.findViewById(R.id.sitting_deleteImg);
            delayImg=item.findViewById(R.id.sitting_updateImg);

        }


    }
}
