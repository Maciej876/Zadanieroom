package com.example.roomzadanie;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Ksiazki.class}, version = 1)
public abstract class KsiazkiDataBase extends RoomDatabase {

    private static KsiazkiDataBase instancja;

    // DAO (TO JEST KLUCZOWE)
    public abstract KsiazkiDao zwrocKsiazkiDao();

    public static KsiazkiDataBase zwrocInstancjeBazyDanych(Context context){
        if (instancja == null) {
            instancja = Room.databaseBuilder(
                            context.getApplicationContext(),
                            KsiazkiDataBase.class,
                            "ksiazki_db"
                    )
                    .allowMainThreadQueries() // tylko do nauki!
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instancja;
    }
}
