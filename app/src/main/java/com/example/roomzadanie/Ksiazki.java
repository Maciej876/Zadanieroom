package com.example.roomzadanie;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Ksiazki_tabela")
public class Ksiazki {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nazwa;
    private String gatunek;
    @ColumnInfo(name = "autor")
    private String autor;
    @ColumnInfo(name = "ilosc_stron,")
    private int iloscStron;

    public Ksiazki(String nazwa, String gatunek, String autor, int iloscStron) {
        id = 0;
        this.nazwa = nazwa;
        this.gatunek = gatunek;
        this.autor = autor;
        this.iloscStron = iloscStron;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getGatunek() {
        return gatunek;
    }

    public void setGatunek(String gatunek) {
        this.gatunek = gatunek;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getIloscStron() {
        return iloscStron;
    }

    public void setIloscStron(int iloscStron) {
        this.iloscStron = iloscStron;
    }
}

