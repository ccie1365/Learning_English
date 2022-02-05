package com.javad_mozaffari.grammar.Room_Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(version =1,exportSchema = false,entities ={com.javad_mozaffari.grammar.Room_Database.NameModel.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;
    public static AppDatabase getAppDatabase(Context context) {

        if(appDatabase==null)
            appDatabase= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"db_name")
                    .allowMainThreadQueries()
                    .build();

        return appDatabase;
    }

    public abstract com.javad_mozaffari.grammar.Room_Database.NameDao nameDao();


}
