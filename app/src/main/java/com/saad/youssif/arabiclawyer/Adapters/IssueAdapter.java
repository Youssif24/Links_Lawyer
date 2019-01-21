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
import com.saad.youssif.arabiclawyer.Model.ClientDB;
import com.saad.youssif.arabiclawyer.Model.IssueDB;
import com.saad.youssif.arabiclawyer.R;
import com.saad.youssif.arabiclawyer.View.Update_View;

import java.util.List;

import static com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype.RotateBottom;
import static com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype.Slidetop;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.IssueViewHolder> {
    private List<IssueDB> issueList;
    Context ctx;
    DBHelper dbHelper;
    Update_View update_view;
    public IssueAdapter(List<IssueDB> issueList, Context context){
        this.issueList = issueList;
        this.ctx=context;
        dbHelper=new DBHelper(ctx);

    }


    public IssueAdapter() {
    }
    @NonNull
    @Override
    public IssueAdapter.IssueViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.issue_recycler_items,viewGroup,false);
        IssueViewHolder viewHolder = new IssueViewHolder(layoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final IssueViewHolder issueViewHolder, int i) {
        IssueDB issueDB=issueList.get(i);
        issueViewHolder.issue_client.setText(issueDB.getClient_name());
        issueViewHolder.issue_num.setText("رقم القضية: "+issueDB.getNum());
        issueViewHolder.issue_type.setText("نوع القضية: "+issueDB.getType());
        issueViewHolder.issue_opponent.setText("إسم الخصم: "+issueDB.getOpponent_name());
        issueViewHolder.issue_court.setText("إسم المحكمة: "+issueDB.getCourt_name());
        issueViewHolder.issue_token.setText("صفة الموكل: "+issueDB.getToken());
        issueViewHolder.issue_details.setText("موضوع القضية: "+issueDB.getDetails());

        issueViewHolder.deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int position = issueViewHolder.getAdapterPosition(); //get position which is swipe

                final NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(ctx);

                dialogBuilder
                        .withTitle("حذف قضية")
                        .withTitleColor("#FFFFFF")
                        .withDividerColor("#11000000")
                        .withMessage("هل تريد حذف هذه القضية ؟")
                        .withMessageColor("#FFFFFFFF")
                        .withDialogColor("#3D5069")
                        .withIcon(R.drawable.delete_forever_24dp)
                        .withDuration(600)
                        .withEffect(RotateBottom)
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
                                dbHelper.deleteIssue(issueList.get(position).getNum());
                                issueList.remove(position);
                                notifyItemRemoved(position);
                                notifyDataSetChanged();
                                dialogBuilder.dismiss();
                                return;
                            }
                        })
                        .show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return issueList.size();
    }

    public static class IssueViewHolder extends RecyclerView.ViewHolder {
        TextView issue_num,issue_client,issue_token,issue_opponent,issue_type,issue_court,issue_details;
        ImageView deleteImg;

        public IssueViewHolder(View item) {
            super(item);
           issue_num=item.findViewById(R.id.issue_num);
           issue_client=item.findViewById(R.id.issue_client_name);
           issue_token=item.findViewById(R.id.issue_token);
           issue_opponent=item.findViewById(R.id.issue_opponent);
           issue_type=item.findViewById(R.id.issue_type);
           issue_court=item.findViewById(R.id.issue_court);
           issue_details=item.findViewById(R.id.issue_details);
           deleteImg=item.findViewById(R.id.issue_deleteImg);

        }


    }
}
