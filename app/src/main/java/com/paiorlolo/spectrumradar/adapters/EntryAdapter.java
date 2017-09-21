package com.paiorlolo.spectrumradar.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.paiorlolo.spectrumradar.R;
import com.paiorlolo.spectrumradar.lists.EntryItem;
import com.paiorlolo.spectrumradar.lists.Item;
import com.paiorlolo.spectrumradar.lists.SectionItem;
import com.paiorlolo.spectrumradar.utils.PrefsManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EntryAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList items;
    private LayoutInflater vi;
    private static List<String> selectedFrequencies;
    private static List<String> selectedLengths;

    public static List<String> getSelectedFrequencies() {
        return selectedFrequencies;
    }

    public static List<String> getSelectedLengths() {
        return selectedLengths;
    }

    public EntryAdapter(Context context, ArrayList items) {
        super(context,0, items);
        this.context = context;
        this.items = items;
        Set<String> frequenciesSet, lengthsSet;

        frequenciesSet = PrefsManager.getFrequencies(context);
        lengthsSet = PrefsManager.getLengths(context);

        selectedLengths = new ArrayList<String>(lengthsSet);
        selectedFrequencies = new ArrayList<String>(frequenciesSet);

        for(int i = 0;i<items.size();i++){
            Item item = (Item) items.get(i);
            if (item != null) {
                if (!item.isSection()) {
                    EntryItem ei = (EntryItem)item;
                    if(ei.isLength()){
                        if(hasString(selectedLengths,ei.getTitle())){
                            ei.setSelected(true);
                        }
                    }else{
                        if(hasString(selectedFrequencies,ei.getTitle())){
                            ei.setSelected(true);
                        }
                    }

                }
            }
        }

        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final Item i = (Item) items.get(position);
        if (i != null) {
            if(i.isSection()){
                SectionItem si = (SectionItem)i;
                v = vi.inflate(R.layout.list_section, null);
                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);
                final TextView sectionView =
                        (TextView) v.findViewById(R.id.list_item_section_text);
                sectionView.setText(si.getTitle());
            } else {
                final EntryItem ei = (EntryItem)i;
                v = vi.inflate(R.layout.list_entry, null);
                final TextView title =
                        (TextView)v.findViewById(R.id.list_item_section_text);
                final TextView subtitle =
                        (TextView)v.findViewById(R.id.list_item_entry_summary);
                final CheckBox check = (CheckBox)v.findViewById(R.id.list_item_selected);

                check.setChecked(ei.getSelected());

                check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ei.getSelected()){
                            ei.setSelected(false);
                            if(ei.isLength()){
                                selectedLengths.remove(title.getText().toString());
                            }else{
                                selectedFrequencies.remove(title.getText().toString());
                            }
                        }else {
                            if(ei.isLength()){
                                if(selectedLengths.size()<3){
                                    selectedLengths.add(title.getText().toString());
                                    ei.setSelected(true);
                                }else{
                                    Toast toast = Toast.makeText(context, R.string.str_maxThree, Toast.LENGTH_SHORT);
                                    toast.show();
                                }

                            }else{
                                if(selectedFrequencies.size()<3){
                                    selectedFrequencies.add(title.getText().toString());
                                    ei.setSelected(true);
                                }else{
                                    Toast toast = Toast.makeText(context, R.string.str_maxThree, Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            }
                            check.setChecked(ei.getSelected());
                        }
                    }
                });
                if (title != null) title.setText(ei.getTitle());
                if(subtitle != null) subtitle.setText(ei.getSubTitle());
            }
        }
        return v;
    }

    private boolean hasString(List<String> list, String str){
        for(String s: list){
            if(s.equals(str)){
                return true;
            }
        }
        return false;
    }

}