package com.example.roomzadanie;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface KsiazkiDao {
    @Insert
    public void wstawKsiazkiDoBazy(Ksiazki ksiazka);

    @Delete
    public void usunZBazy(Ksiazki ksiazka);

    @Update
    public void zaktualizuj(Ksiazki ksiazka);

    @Query("Select * from ksiazki_tabela")
    List<Ksiazki> zwrocWszystkieKsiazkiZBazy();
}
