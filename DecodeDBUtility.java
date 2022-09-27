package com.example.secretsofthecursedcastle;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DecodeDBUtility extends SQLiteOpenHelper {


    //Constructor
    public DecodeDBUtility(@Nullable Context context)
    {
        super(context, "decode.db", null, 1);
    }


    //Table creation
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table decode (id Integer primary key autoincrement,input text,output text)";
        db.execSQL(sql);
    }

    public boolean insert(String input)
    {
        //Variable
        boolean insert = false;
        String output = solve(input);

        try{
            //Creates the database and adds he the values as needed
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
        //Puts in the outputs based on the decoding
        //Some are the underlined books, some are required for best ending, some are easter eggs
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
            return "849-658-12397";
        }
        if(input.equals("MICHAEL"))
        {
            return "MCGAFFICK";
        }
        if(input.equals("2+2"))
        {
            return "4";
        }
        if(input.equals("CASTLE"))
        {
            return "CURSED";
        }
        if(input.equals("SECRET"))
        {
            return "SECRET";
        }
        return "NODATA";
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //No need for this
    }
}
