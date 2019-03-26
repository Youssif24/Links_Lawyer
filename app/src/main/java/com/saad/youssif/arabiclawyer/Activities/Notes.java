package com.saad.youssif.arabiclawyer.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        Toolbar toolbar = findViewById(R.id.notesToolbar);
        setSupportActionBar(toolbar);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);

        MenuItem searchItem=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                notesAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

}
