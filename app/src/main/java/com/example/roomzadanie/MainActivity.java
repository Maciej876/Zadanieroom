package com.example.roomzadanie;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    KsiazkiDataBase KsiazkiDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        KsiazkiDatabase = KsiazkiDataBase.zwrocInstancjeBazyDanych(MainActivity.this);
        ListView listView = findViewById(R.id.listView);
        List<Ksiazki> wszystkieKsiazkiListy = KsiazkiDataBase.zwrocKsiazkiDao().zwrocWszystkieKsiazkiZBazy();
        ArrayAdapter<Ksiazki> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, wszystkieKsiazkiListy);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                KsiazkiDatabase.zwrocKsiazkiDao().usunZBazy(wszystkieKsiazkiListy.get(i));
                wszystkieKsiazkiListy.remove(i);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });
        EditText nazwa= findViewById(R.id.nazwa_ksiazki);
        EditText autor = findViewById(R.id.autor);
        EditText ilosc_stron = findViewById(R.id.ilosc_stron);
        EditText gatunek = findViewById(R.id.gatunek);
        Button dodaj = findViewById(R.id.dodaj);
        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KsiazkiDataBase.zwrocKsiazkiDao().wstawKsiazkiDoBazy(new Ksiazki(nazwa.getText().toString(), gatunek.getText().toString(), autor.getText().toString(), Integer.parseInt(ilosc_stron.getText().toString())));
                wszystkieKsiazkiListy.add(new Ksiazki(nazwa.getText().toString(), gatunek.getText().toString(), autor.getText().toString(), Integer.parseInt(ilosc_stron.getText().toString())));
                arrayAdapter.notifyDataSetChanged();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ksiazki ksiazki = wszystkieKsiazkiListy.get(i);
                nazwa.setText(ksiazki.getNazwa());
                autor.setText(ksiazki.getAutor());
                ilosc_stron.setText(ksiazki.getIloscStron());
                gatunek.setText(ksiazki.getGatunek());
            }
        })
    ;}
}

