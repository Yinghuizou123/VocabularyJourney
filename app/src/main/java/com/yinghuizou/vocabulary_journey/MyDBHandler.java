package com.yinghuizou.vocabulary_journey;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteStatement;

public class MyDBHandler extends  SQLiteOpenHelper {
    //First version will be able to store as many
    //product and their ID
    private static final int DATABASE_VERSION = 1;

    //SAVE the data in file, and name it product.db must put db on the end
    //products.db
    private static final String DATABASE_NAME ="itemsXdxSMfDB.number";

    //Data base can have many different table, and this is the name of the table
    private static final String TABLE = "ITEMIMAGE";

    //table will have two column, one for id and one for name of the product
    private static final String COLUMN_ID = "Id";

    private static final String COLUMN_TITLE = "productname";

    private static final String COLUMN_DEFINITION = "productplace";

    private static final String COLUMN_EXAMPLE = "producttime";

    private static final String COLUMN_Image = "productimage";

    //itemDB.numbedr
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,DATABASE_NAME, factory, DATABASE_VERSION);
        // //SAVE the data in file  in DATABASE_NAME
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table and column
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " VARCHAR, " +
                COLUMN_DEFINITION + " VARCHAR, " +
                COLUMN_EXAMPLE + " VARCHAR, " + COLUMN_Image +  " BLOB)";
        db.execSQL(query);

    }




    public void addProduct (String title,String definition,String example,byte [] img){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO "+ TABLE +" VALUES (NULL, ?, ?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, title);
        statement.bindString(2, definition);
        statement.bindString(3, example);
        statement.bindBlob(4, img);
        statement.executeInsert();

    }



    public Cursor getdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        String quary = "Select * from " + TABLE;
        Cursor data  = db.rawQuery(quary,null);
        return data;


    }

    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM ITEMIMAGE WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }


    public Cursor getDataid(){
        SQLiteDatabase db = this.getWritableDatabase();
        String quary = "SELECT ID FROM " + TABLE ;
        Cursor data  = db.rawQuery(quary,null);
        return data;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }





}

