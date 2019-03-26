package com.saad.youssif.arabiclawyer.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saad.youssif.arabiclawyer.Adapters.ClientAdapter;
import com.saad.youssif.arabiclawyer.Helpers.DBHelper;
import com.saad.youssif.arabiclawyer.Model.ClientDB;
import com.saad.youssif.arabiclawyer.R;
import com.saad.youssif.arabiclawyer.View.Update_View;

import java.util.ArrayList;

public class ClientActivity extends AppCompatActivity implements Update_View {

   public  RecyclerView recyclerView;
    DBHelper dbHelper;
    ArrayList<ClientDB> clientDBArrayList;
    ClientAdapter clientAdapter;
    RecyclerView.LayoutManager layoutManager;
    FloatingActionButton floatingActionButton;



     @Override
    protected void attachBaseContext(android.content.Context newBase) {
        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("قائمة الموكلين");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dbHelper=new DBHelper(this);
        recyclerView=findViewById(R.id.recyclerClient);
        floatingActionButton=findViewById(R.id.fab);
       // refreshLayout=findViewById(R.id.clientSwipe);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
       showData();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Intent intent=new Intent(ClientActivity.this,NewClient.class);
              startActivity(intent);
            }
        });



    }

    public void showData()
    {
         clientDBArrayList=dbHelper.getAllrecord();
        clientAdapter=new ClientAdapter(clientDBArrayList,ClientActivity.this);
        clientAdapter.OnClickListener(this);
        recyclerView.setAdapter(clientAdapter);
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
                clientAdapter.getFilter().filter(s);
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

    @Override
    protected void onResume() {
        super.onResume();
        showData();
    }


    @Override
    public void list(final String id, final String tybe, String phone, final String name, int position) {
        final Dialog dialog = new Dialog(ClientActivity.this);
        dialog.setContentView(R.layout.update_client_custom_dialog);

        Button editDialogButton = dialog.findViewById(R.id.btn_yes);
        Button cancelDialogButton = dialog.findViewById(R.id.btn_no);
        final EditText newName=dialog.findViewById(R.id.client_dialog_name);
        final EditText newPhone=dialog.findViewById(R.id.client_dialog_phone);
        newName.setText(name);
        newPhone.setText(phone);

        // if button is clicked, close the custom dialog
        editDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.updateData(id,newName.getText().toString().trim(),tybe,newPhone.getText().toString().trim()))
                {
                    if(dbHelper.updateSingleClient(name,newName.getText().toString().trim()))
                    {
                        showData();
                        Toast.makeText(ClientActivity.this,"تم التعديل ",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(ClientActivity.this,"لم يتم تعديل البيانات",Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        cancelDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        dialog.show();

    }
}
