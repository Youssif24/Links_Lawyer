package com.saad.youssif.arabiclawyer.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.saad.youssif.arabiclawyer.Other.ClientCustomDialog;
import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.Model.ClientDB;
import com.saad.youssif.arabiclawyer.R;
import com.saad.youssif.arabiclawyer.View.Update_View;

import java.util.List;

import static com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype.SlideBottom;
import static com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype.Slidetop;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> {
    private  List<ClientDB> clientList;
    Context ctx;
    DBHelper dbHelper;
    public static String name,phone,id,type;
    Update_View update_view;
    public ClientAdapter(List<ClientDB> clientList, Context context){
        this.clientList = clientList;
        this.ctx=context;
        dbHelper=new DBHelper(ctx);

    }


    public ClientAdapter() {
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.client_recycler_layout,viewGroup,false);
        ClientViewHolder viewHolder = new ClientViewHolder(layoutView);
        return viewHolder;
    }
    public void OnClickListener(Update_View update_view){
        this.update_view=update_view;
    }

    @Override
    public void onBindViewHolder(@NonNull final ClientViewHolder clientViewHolder, int i) {
        ClientDB clientDB=clientList.get(i);
        clientViewHolder.client_item_name.setText(clientDB.getName());
        //cartViewHolder.client_item_type.setText(clientDB.getType());
        clientViewHolder.client_item_phone.setText(String.valueOf(clientDB.getPhone()));


        clientViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        clientViewHolder.clientOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int position = clientViewHolder.getAdapterPosition(); //get position which is swipe
                //creating a popup menu
                PopupMenu popup = new PopupMenu(ctx, clientViewHolder.clientOption);
                //inflating menu from xml resource
                popup.inflate(R.menu.client_options);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.client_edit_option:
                                               /* dbHelper.Delete(clientList.get(position).getId());
                                                clientList.remove(position);
                                                notifyItemRemoved(position);
                                                notifyDataSetChanged();*/
                               /* final Dialog dialog = new Dialog(ctx);
                                dialog.setContentView(R.layout.update_client_custom_dialog);
                               Button editDialogButton = dialog.findViewById(R.id.btn_yes);
                                Button cancelDialogButton = dialog.findViewById(R.id.btn_no);
                                final EditText newName=dialog.findViewById(R.id.client_dialog_name);
                                final EditText newPhone=dialog.findViewById(R.id.client_dialog_phone);
                                newName.setText(clientList.get(position).getName().toString());
                                newPhone.setText(clientViewHolder.client_item_phone.getText().toString());

                                // if button is clicked, close the custom dialog
                                editDialogButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        if(dbHelper.updateData(clientList.get(position).getId(),newName.getText().toString()
                                        ,clientList.get(position).getType(),newPhone.getText().toString()))
                                        {
                                            Toast.makeText(ctx,"تم تعديل البيانات",Toast.LENGTH_SHORT).show();
                                            notifyDataSetChanged();

                                            ClientAdapter.this.notifyDataSetChanged();
                                            notifyItemChanged(position);
                                        }
                                        else
                                        {
                                            Toast.makeText(ctx,"لم يتم تعديل البيانات",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                cancelDialogButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();

                                    }
                                });
                                dialog.show();*/

                                   update_view.list(clientList.get(position).getId(),clientList.get(position).getType(),
                                            clientList.get(position).getPhone(),clientList.get(position).getName(),position);
                                    break;


                            case R.id.client_delete_option:
                                //sqldatabase.execSQL("delete from " + TABLE_NAME + " where _id='" + (position + 1) + "'"); //query for delete

                                final NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(ctx);

                                dialogBuilder
                                        .withTitle("حذف موكل")
                                        .withTitleColor("#FFFFFF")
                                        .withDividerColor("#11000000")
                                        .withMessage("هل تريد حذف هذا الموكل ؟")
                                        .withMessageColor("#FFFFFFFF")
                                        .withDialogColor("#3D5069")
                                        .withIcon(R.drawable.delete_forever_24dp)
                                        .withDuration(600)
                                        .withEffect(Slidetop)
                                        .withButton1Text("لا")
                                        .withButton2Text("نعم")
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
                                                dbHelper.Delete(clientList.get(position).getId());
                                                clientList.remove(position);
                                                notifyItemRemoved(position);
                                                notifyDataSetChanged();
                                                dialogBuilder.dismiss();
                                                return;
                                            }
                                        })
                                        .show();

                                break;

                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public static class ClientViewHolder extends RecyclerView.ViewHolder {
        TextView client_item_name,client_item_type,client_item_phone,clientOption;

        public ClientViewHolder(View item) {
            super(item);
            client_item_name=item.findViewById(R.id.client_item_name);
            //client_item_type=item.findViewById(R.id.client_item_type);
            client_item_phone=item.findViewById(R.id.client_item_phone);
            clientOption=item.findViewById(R.id.textViewOptions);
        }


    }
}

