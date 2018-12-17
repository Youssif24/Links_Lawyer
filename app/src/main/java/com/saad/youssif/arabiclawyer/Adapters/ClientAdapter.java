package com.saad.youssif.arabiclawyer.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saad.youssif.arabiclawyer.Model.ClientDB;
import com.saad.youssif.arabiclawyer.R;

import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> {
    private final List<ClientDB> clientList;
    Context ctx;

    public ClientAdapter(List<ClientDB> clientList, Context context){
        this.clientList = clientList;
        this.ctx=context;
    }


    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.client_recycler_layout,viewGroup,false);
        ClientViewHolder viewHolder = new ClientViewHolder(layoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder cartViewHolder, int i) {
        ClientDB clientDB=clientList.get(i);
        cartViewHolder.client_item_name.setText(clientDB.getName());
        cartViewHolder.client_item_type.setText(clientDB.getType());
        cartViewHolder.client_item_phone.setText(clientDB.getPhone());

    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public static class ClientViewHolder extends RecyclerView.ViewHolder {
        TextView client_item_name,client_item_type,client_item_phone;

        public ClientViewHolder(View item) {
            super(item);
            client_item_name=item.findViewById(R.id.client_item_name);
            client_item_type=item.findViewById(R.id.client_item_type);
            client_item_phone=item.findViewById(R.id.client_item_phone);
        }


    }
}

