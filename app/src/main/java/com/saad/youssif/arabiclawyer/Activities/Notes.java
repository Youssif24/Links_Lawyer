package com.saad.youssif.arabiclawyer.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.View;

import com.saad.youssif.arabiclawyer.Adapters.NotesAdapter;
import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.Model.NoteDB;
import com.saad.youssif.arabiclawyer.R;

import java.util.ArrayList;
import java.util.List;

public class Notes extends AppCompatActivity {

    FloatingActionButton notesFab;
    RecyclerView recyclerView;
    DBHelper dbHelper;
    NotesAdapter notesAdapter;
    List<NoteDB> noteDBList;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void attachBaseContext(android.content.Context newBase) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mozkrat);
        notesFab=findViewById(R.id.noteFab);
        recyclerView=findViewById(R.id.recyclerNotes);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        dbHelper=new DBHelper(this);
       showData();



        notesFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Notes.this,NewNote.class));
            }
        });
    }

    public void showData()
    {
        noteDBList=dbHelper.getAllNotes();
        notesAdapter=new NotesAdapter(this,noteDBList);
        recyclerView.setAdapter(notesAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showData();
    }
}
