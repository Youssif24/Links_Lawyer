package com.saad.youssif.arabiclawyer.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.saad.youssif.arabiclawyer.Model.ClientDB;
import com.saad.youssif.arabiclawyer.Model.IssueDB;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBname="data.db";
    public DBHelper(Context context) {
        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Client ( id  INTEGER PRIMARY KEY,name TEXT,type TEXT, phone TEXT)");
        db.execSQL("Create table Issue (issue_num INTEGER PRIMARY KEY,issue_type TEXT,issue_token TEXT,issue_client_name TEXT,issue_opponent_name TEXT,court_name TEXT,issue_details TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Client");
        db.execSQL("DROP TABLE IF EXISTS Issue");

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
    public ArrayList getAllClients(){
        ArrayList<String> arrayList=new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from Client" ,null);
        cursor.moveToFirst();//move to the first field
        while (cursor.isAfterLast()==false)//last field
        {
            arrayList.add(cursor.getString(1));
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
        return db.delete("Client","id= ?",new String[]{id});


    }


    //add new issue method
    public boolean insertIssueData(String issue_num,String issue_type,String issue_token,String issue_client_name
            ,String issue_opponent_name,String court_name,String issue_details){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("issue_num",issue_num);
        contentValues.put("issue_type",issue_type);
        contentValues.put("issue_token",issue_token);
        contentValues.put("issue_client_name",issue_client_name);
        contentValues.put("issue_opponent_name",issue_opponent_name);
        contentValues.put("court_name",court_name);
        contentValues.put("issue_details",issue_details);



        long result=db.insert("Issue",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    //get all issues
    public ArrayList getAllIssues(){
        ArrayList<IssueDB> arrayList=new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from Issue" ,null);
        cursor.moveToFirst();//move to the first field
        while (cursor.isAfterLast()==false)//last field
        {
            IssueDB issueDB=new IssueDB();
            issueDB.setNum(cursor.getString(0));
            issueDB.setType(cursor.getString(1));
            issueDB.setToken(cursor.getString(2));
            issueDB.setClient_name(cursor.getString(3));
            issueDB.setOpponent_name(cursor.getString(4));
            issueDB.setCourt_name(cursor.getString(5));
            issueDB.setDetails(cursor.getString(6));

            arrayList.add(issueDB);
            cursor.moveToNext();

        }
        return arrayList;

    }

    //update an issue
    public boolean updateIssue(String issue_num,String issue_type,String issue_token,String issue_client_name
            ,String issue_opponent_name,String court_name,String issue_details){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("issue_type",issue_type);
        contentValues.put("issue_token",issue_token);
        contentValues.put("issue_client_name",issue_client_name);
        contentValues.put("issue_opponent_name",issue_opponent_name);
        contentValues.put("court_name",court_name);
        contentValues.put("issue_details",issue_details);


        db.update("Issue",contentValues,"issue_num= ?",new String[]{String.valueOf(issue_num)});

        return true;
    }

    //delete an issue
    public Integer deleteIssue(String issue_num){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("Issue","issue_num= ?",new String[]{issue_num});


    }

}

