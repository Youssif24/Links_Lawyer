package com.saad.youssif.arabiclawyer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.saad.youssif.arabiclawyer.Model.ClientDB;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBname="data.db";
    public DBHelper(Context context) {
        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Client ( id  INTEGER PRIMARY KEY,name TEXT,type TEXT, phone INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS mytable");
        onCreate(db);

    }
    //function to insert data (to get writale)
    public boolean insertClientData(String id,String name,String type,String phone){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",id);
        contentValues.put("name",name);
        contentValues.put("type",type);
        contentValues.put("phone",phone);


        long result=db.insert("Client",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    //function to reterieve data(to get readable)
    public ArrayList getAllrecord(){
        ArrayList<ClientDB> arrayList=new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from Client" ,null);
        cursor.moveToFirst();//move to the first field
        while (cursor.isAfterLast()==false)//last field
        {
            ClientDB clientDB=new ClientDB();
            clientDB.setId(cursor.getString(0));
            clientDB.setName(cursor.getString(1));
            clientDB.setType(cursor.getString(2));
            clientDB.setPhone(cursor.getString(3));
           /* String t1=cursor.getString(0);
            String t2=cursor.getString(1);
            String t3=cursor.getString(2);
            String t4=cursor.getString(3);*/
            arrayList.add(clientDB);
            cursor.moveToNext();

        }
        return arrayList;

    }
    //to get update on databae its look like addData function .
    public boolean updateData(String id,String name,String type,String phone){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("type",type);
        contentValues.put("phone",phone);
        db.update("Client",contentValues,"id= ?",new String[]{String.valueOf(id)});

        return true;
    }
    public Integer Delete(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("Client","id= ?",new String[]{String.valueOf(id)});


    }
    /*
    * RecyclerView recyclerView;
    DBHelper dbHelper;
    ArrayList<ClientDB> clientDBArrayList;
    ClientAdapter clientAdapter;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        dbHelper=new DBHelper(this);
        recyclerView=findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        clientDBArrayList=dbHelper.getAllrecord();
        clientAdapter=new ClientAdapter(clientDBArrayList,this);
        recyclerView.setAdapter(clientAdapter);

    }

    * */
}

