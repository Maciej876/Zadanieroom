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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    KsiazkiDataBase ksiazkiDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ksiazkiDatabase = KsiazkiDataBase.zwrocInstancjeBazyDanych(this);

        ListView listView = findViewById(R.id.listView);

        EditText nazwa = findViewById(R.id.etNazwa);
        EditText autor = findViewById(R.id.etAutor);
        EditText iloscStron = findViewById(R.id.etIloscStron);
        EditText gatunek = findViewById(R.id.etGatunek);
        Button dodaj = findViewById(R.id.btnDodaj);

        List<Ksiazki> wszystkieKsiazkiListy =
                ksiazkiDatabase.zwrocKsiazkiDao().zwrocWszystkieKsiazkiZBazy();

        ArrayAdapter<Ksiazki> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, wszystkieKsiazkiListy);

        listView.setAdapter(arrayAdapter);

        // USUWANIE (long click)
        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            ksiazkiDatabase.zwrocKsiazkiDao().usunZBazy(wszystkieKsiazkiListy.get(i));
            wszystkieKsiazkiListy.remove(i);
            arrayAdapter.notifyDataSetChanged();
            return true;
        });

        // DODAWANIE
        dodaj.setOnClickListener(view -> {
            try {
                String nazwaTxt = nazwa.getText().toString();
                String autorTxt = autor.getText().toString();
                String gatunekTxt = gatunek.getText().toString();
                int ilosc = Integer.parseInt(iloscStron.getText().toString());

                Ksiazki ksiazka = new Ksiazki(nazwaTxt, gatunekTxt, autorTxt, ilosc);

                ksiazkiDatabase.zwrocKsiazkiDao().wstawKsiazkiDoBazy(ksiazka);
                wszystkieKsiazkiListy.add(ksiazka);

                arrayAdapter.notifyDataSetChanged();

                // czyszczenie pól
                nazwa.setText("");
                autor.setText("");
                gatunek.setText("");
                iloscStron.setText("");

            } catch (Exception e) {
                iloscStron.setError("Podaj poprawną liczbę");
            }
        });

        // KLIKNIĘCIE (uzupełnianie pól)
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Ksiazki ksiazki = wszystkieKsiazkiListy.get(i);

            nazwa.setText(ksiazki.getNazwa());
            autor.setText(ksiazki.getAutor());
            gatunek.setText(ksiazki.getGatunek());
            iloscStron.setText(String.valueOf(ksiazki.getIloscStron()));
        });
    }
}
