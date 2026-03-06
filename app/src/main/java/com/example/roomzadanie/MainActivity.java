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
    KsiazkiDataBase przepisyDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        przepisyDatabase = KsiazkiDataBase.zwrocInstancjeBazyDanych(MainActivity.this);
        /*przepisyDatabase.zwrocWypiekiDao().wstawWypiekDoBazy(new Wypiek(170, 120, "Sernik", "ser, ziemniaki, cukier, jajka"));
        przepisyDatabase.zwrocWypiekiDao().wstawWypiekDoBazy(new Wypiek(170, 15, "drożdzówki", "ser, drożdże, mąka, cukier, jajka"));
        przepisyDatabase.zwrocWypiekiDao().wstawWypiekDoBazy(new Wypiek(170, 60, "Chleb", "mąka, drożdże"));
        przepisyDatabase.zwrocWypiekiDao().zwrocWszystkieWypiekiZBazy();*/
        ListView listView = findViewById(R.id.listView);
        List<Ksiazki> wszystkieWypiekiListy = przepisyDatabase.zwrocKsiazkiDao().zwrocWszystkieKsiazkiZBazy();
        ArrayAdapter<Ksiazki> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, wszystkieWypiekiListy);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                przepisyDatabase.zwrocKsiazkiDao().usunZBazy(wszystkieWypiekiListy.get(i));
                wszystkieWypiekiListy.remove(i);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });
        EditText nazwaWypieku = findViewById(R.id.nazwa_ksiazki);
        EditText autor = findViewById(R.id.autor);
        EditText ilosc_stron = findViewById(R.id.ilosc_stron);
        EditText gatunek = findViewById(R.id.gatunek);
        Button dodaj = findViewById(R.id.dodaj);
        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                przepisyDatabase.zwrocWypiekiDao().wstawWypiekDoBazy(new Wypiek(Integer.parseInt(temperaturaPieczenia.getText().toString()), Integer.parseInt(ilosc_stron.getText().toString()), nazwaWypieku.getText().toString(), autor.getText().toString()));
                wszystkieWypiekiListy.add(new Wypiek(Integer.parseInt(temperaturaPieczenia.getText().toString()), Integer.parseInt(ilosc_stron.getText().toString()), nazwaWypieku.getText().toString(), autor.getText().toString()));
                arrayAdapter.notifyDataSetChanged();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Wypiek wypiek = wszystkieWypiekiListy.get(i);
                nazwaWypieku.setText(wypiek.getNazwaWypieku().toString());
                autor.setText(wypiek.getSkladniki().toString());
                gatunek.setText(Integer.toString(wypiek.getTemperaturaPieczenia()));
                ilosc_stron.setText(Integer.toString(wypiek.getCzasPieczenia()));
            }
        });
    }
}
    }
}