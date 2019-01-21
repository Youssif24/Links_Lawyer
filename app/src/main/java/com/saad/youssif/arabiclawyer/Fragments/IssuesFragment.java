package com.saad.youssif.arabiclawyer.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.saad.youssif.arabiclawyer.Adapters.ClientAdapter;
import com.saad.youssif.arabiclawyer.Adapters.FragIssueAdpater;
import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.Other.SharedPrefManager;
import com.saad.youssif.arabiclawyer.R;

public class IssuesFragment extends Fragment {
    private static View view;
    SharedPrefManager sharedPrefManager;
    FragIssueAdpater fragIssueAdpater;
    DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =inflater.inflate(R.layout.fragment_issues, container, false);
       RecyclerView recyclerView=view.findViewById(R.id.frag_issue_recycler);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
       dbHelper=new DBHelper(getContext());
        sharedPrefManager=SharedPrefManager.getInstance(getContext());
        fragIssueAdpater=new FragIssueAdpater(dbHelper.getClient_frag_issues(ClientAdapter.name),getContext());

        recyclerView.setAdapter(fragIssueAdpater);

       /* CommentsPresenter commentsPresenter=new CommentsPresenter(getContext(),this);
        commentsPresenter.getComments(sharedPrefManager.getId());*/
        //Toast.makeText(getContext(),arg,Toast.LENGTH_LONG).show();
        return view;


    }
}
