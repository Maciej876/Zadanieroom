package com.example.roomzadanie;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Ksiazki.class}, version = 1)
public abstract class KsiazkiDataBase extends RoomDatabase {

    public abstract KsiazkiDao zwrocKsiazkiDao();

    private static KsiazkiDataBase instancje;

    public static KsiazkiDataBase zwrocInstancjeBazyDanych(Context context){
        if (instancje == null) {
            instancje = Room.databaseBuilder(
                            context,
                            KsiazkiDataBase.class,
                            "ksiazki_db"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instancje;

    }
}