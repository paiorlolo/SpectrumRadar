package com.paiorlolo.spectrumradar;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.paiorlolo.spectrumradar.adapters.EntryAdapter;
import com.paiorlolo.spectrumradar.lists.EntryItem;
import com.paiorlolo.spectrumradar.lists.SectionItem;
import com.paiorlolo.spectrumradar.utils.PrefsManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FrequentUnits extends AppCompatActivity {

    ListView listView ;
    Button btnSave;
    EntryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequent_units);

        listView = (ListView) findViewById(R.id.list_units);
        btnSave = (Button) findViewById(R.id.btn_saveUnits);

        final ArrayList items = new ArrayList();
        items.add(new SectionItem("Frequencies"));
        items.add(new EntryItem("Hz", "1 Hz", false));
        items.add(new EntryItem("KHz", "1,000 Hz | 1e3 Hz", false));
        items.add(new EntryItem("MHz", "1,000,000 Hz | 1e6 Hz", false));
        items.add(new EntryItem("GHz", "1,000,000,000 | 1e9 Hz", false));
        items.add(new SectionItem("Lengths"));
        items.add(new EntryItem("km", "1,000 m | 1e3 m", true));
        items.add(new EntryItem("m", "1 m", true));
        items.add(new EntryItem("cm", "0.01 m | 1e-2 m", true));
        items.add(new EntryItem("mm", "0.001 m | 1e-3 m", true));
        items.add(new EntryItem("µm", "0.000001 m | 1e-6 m", true));
        items.add(new EntryItem("nm", "0.000000001 m | 1e-10 m", true));
        items.add(new EntryItem("Å", "0.1 nm | 1e-10 m", true));
        adapter = new EntryAdapter(this, items);
        listView.setAdapter(adapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<String> lengthsSet = new HashSet<String>();
                Set<String> frequenciesSet = new HashSet<String>();

                for(int i = 0;i<adapter.getSelectedLengths().size();i++){
                    String len = adapter.getSelectedLengths().get(i);
                    lengthsSet.add(len);
                }
                PrefsManager.setLengths(v.getContext(), lengthsSet);
                for(int i = 0;i<adapter.getSelectedFrequencies().size();i++){
                    String freq = adapter.getSelectedFrequencies().get(i);
                    frequenciesSet.add(freq);
                }

                PrefsManager.setFrequencies(v.getContext(), frequenciesSet);
                finish();
            }
        });
    }
}
