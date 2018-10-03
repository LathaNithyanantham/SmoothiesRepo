package com.example.latha.smoothiesapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public final class TableData extends SQLiteOpenHelper{
    public static final int dbVersion=1;
    public static final String dbName="MyDatabase1";
    public static final String tableName1="OrdersTables";
    public static final String tableName2="RegisterTable";
    public static final String fruitName="fruit";
    public static final String amount="amount";
    public static final String servings="servings";
    public static final String dbUserId="dbUserId";
    public static final String dbPassword="dbPassword";
    public static final String dbEmail="dbEmail";
    static int flag;

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " +tableName1 + " (" +
                     dbEmail+ " TEXT," +
                   fruitName + " TEXT," +
                   servings + " TEXT," +
                   amount + " TEXT)";
    static final String REGISTER_QUERRY =
            "CREATE TABLE " +tableName2 + " (" +
                    dbEmail + " TEXT PRIMARY KEY," +
                    dbUserId + " TEXT," +
                    dbPassword + " TEXT)";

    public TableData(Context context) {
        super(context,dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL(SQL_CREATE_ENTRIES);
     db.execSQL(REGISTER_QUERRY);
        Log.d("Table created","crreated successfully");
    }
    public  void putDataToDB(TableData td,String mail,String name,String serve,String amt)
    {   flag=1;
        SQLiteDatabase sd=td.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(dbEmail,mail);
        cv.put(fruitName,name);
        cv.put(amount,amt);
        cv.put(servings,serve);
        sd.insert(tableName1,null,cv);
        Log.d("ONE ROW INSERTED","sucessfully inserted a row");

    }
    public Cursor retrieveFromDB(TableData td,String email)
    {
        SQLiteDatabase sd=td.getReadableDatabase();
        String colums[]={fruitName,servings,amount};
        Cursor cr=sd.query(tableName1,colums,"dbEmail=?", new String[] { email },null,null,null,null);
        Log.d("FETCHED","FROM "+email);
        return  cr;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public  void register(TableData td,String em,String un ,String pass)
    {
        SQLiteDatabase sd=td.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(dbUserId,un);
        cv.put(dbPassword,pass);
        cv.put(dbEmail,em);
        sd.insert(tableName2,null,cv);
        Log.d("ONE ROW INSERTED","sucessfully inserted a  registered row");

    }
    public Cursor getRegisteredData(TableData td)
    {
        SQLiteDatabase sd=td.getReadableDatabase();
        String colums[]={dbEmail,dbUserId,dbPassword};
        Cursor c=sd.query(tableName2,colums,null,null,null,null,null);
        Log.d("FETCHED","#####################################################");
        return  c;


    }

}
