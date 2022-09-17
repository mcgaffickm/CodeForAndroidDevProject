package com.example.secretsofthecursedcastle;


import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DecodeDBUtility extends SQLiteOpenHelper {


    public DecodeDBUtility(@Nullable Context context)
    {
        super(context, "decode.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table decode (id int primary key autoincrement,input text,output text)";
        db.execSQL(sql);
    }

    public boolean insert(String input)
    {
        boolean insert = false;
        String output = solve(input);

        try{
            SQLiteDatabase db = getWritableDatabase();

            ContentValues myValues = new ContentValues();
            myValues.put("input", input);
            myValues.put("output", output);

            long result = db.insert("decode", null, myValues);

            if(result> 0 )
                insert = true;
        } catch (Exception e)
        {
        }

        return insert;
    }

    public String solve(String input)
    {
        if(input.toUpperCase().equals("ISLAND"))
        {
            return "TYPE";
        }
        if(input.toUpperCase().equals("HAT"))
        {
            return "RED";
        }
        if(input.toUpperCase().equals("MISTOOK"))
        {
            return "WRONG";
        }
        if(input.toUpperCase().equals("STAND"))
        {
            return "RHYMES";
        }
        if(input.toUpperCase().equals("OLIVER"))
        {
            return "FAKEORDER(bin)";
        }
        if(input.equals("0100"))
        {
            return "849-658-1239a";
        }
        return "NODATA";
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //No need for this
    }
}
