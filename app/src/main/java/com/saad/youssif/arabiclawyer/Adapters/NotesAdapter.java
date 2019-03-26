package com.saad.youssif.arabiclawyer.Adapters;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.Model.ClientDB;
import com.saad.youssif.arabiclawyer.Model.NoteDB;
import com.saad.youssif.arabiclawyer.R;

import java.util.ArrayList;
import java.util.List;

import static com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype.RotateBottom;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> implements Filterable {

    Context context;
    List<NoteDB> noteDBList;
    List<NoteDB>noteDBListFull;
    DBHelper dbHelper;

    public NotesAdapter (Context context,List<NoteDB> noteDBList)
    {
        this.context=context;
        this.noteDBList=noteDBList;
        this.dbHelper=new DBHelper(context);
        noteDBListFull=new ArrayList<>(noteDBList);
    }


    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notes_recycler_items, viewGroup, false);
        NotesViewHolder viewHolder = new NotesViewHolder(layoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final NotesViewHolder notesViewHolder, int i) {
        NoteDB noteDB=noteDBList.get(i);
        notesViewHolder.court.setText(noteDB.getCourt_name());
        notesViewHolder.client.setText("الموكل و صفته:  "+noteDB.getClient());
        notesViewHolder.opponent.setText("الخصم و صفته:  "+noteDB.getOpponent());
        notesViewHolder.prevSitting.setText("الجلسة السابقة:  "+noteDB.getPrev_sitting());
        notesViewHolder.decision.setText("القرار:  "+noteDB.getDecision());
        notesViewHolder.date.setText(noteDB.getDate());

        notesViewHolder.deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int position = notesViewHolder.getAdapterPosition(); //get position which is swipe

                final NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(context);

                dialogBuilder
                        .withTitle("حذف الأجندة")
                        .withTitleColor("#FFFFFF")
                        .withDividerColor("#11000000")
                        .withMessage("هل تريد حذف هذه الأجندة ؟")
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
                                dbHelper.deleteNotes(String.valueOf(noteDBList.get(position).getId()));
                                noteDBList.remove(position);
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
        return noteDBList.size();
    }



    public class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView court,client,opponent,prevSitting,decision,date;
        ImageView deleteImg;


        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            court=itemView.findViewById(R.id.note_court_name);
            client=itemView.findViewById(R.id.note_client);
            opponent=itemView.findViewById(R.id.note_opponent);
            prevSitting=itemView.findViewById(R.id.note_prev_sitting);
            decision=itemView.findViewById(R.id.note_decision);
            deleteImg=itemView.findViewById(R.id.note_deleteImg);
            date=itemView.findViewById(R.id.note_date);
        }
    }

    @Override
    public Filter getFilter() {
        return ClientFilter;
    }
    private Filter ClientFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<NoteDB> filteredList=new ArrayList<>();

            if(charSequence==null || charSequence.length()==0)
            {
                filteredList.addAll(noteDBListFull);
            }
            else
            {
                String filterString=charSequence.toString().trim();
                for(NoteDB item : noteDBListFull)
                {
                    if(item.getDate().contains(filterString))
                    {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            noteDBList.clear();
            noteDBList.addAll((List)filterResults.values);
            notifyDataSetChanged();

        }
    };
}
