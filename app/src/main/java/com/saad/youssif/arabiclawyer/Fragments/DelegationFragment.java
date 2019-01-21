package com.saad.youssif.arabiclawyer.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saad.youssif.arabiclawyer.Adapters.ClientAdapter;
import com.saad.youssif.arabiclawyer.Adapters.FragDelegationAdapter;
import com.saad.youssif.arabiclawyer.Adapters.FragSittingAdapter;
import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.Other.SharedPrefManager;
import com.saad.youssif.arabiclawyer.R;

public class DelegationFragment extends Fragment {

    private static View view;
    SharedPrefManager sharedPrefManager;
    FragDelegationAdapter fragDelegationAdapter;
    DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_delegation, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.frag_delegation_recycler);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        dbHelper=new DBHelper(getContext());
        sharedPrefManager= SharedPrefManager.getInstance(getContext());
        fragDelegationAdapter=new FragDelegationAdapter(dbHelper.getClient_frag_delegations(ClientAdapter.name),getContext());

        recyclerView.setAdapter(fragDelegationAdapter);

       /* CommentsPresenter commentsPresenter=new CommentsPresenter(getContext(),this);
        commentsPresenter.getComments(sharedPrefManager.getId());*/
        //Toast.makeText(getContext(),arg,Toast.LENGTH_LONG).show();
        return view;
    }
}
