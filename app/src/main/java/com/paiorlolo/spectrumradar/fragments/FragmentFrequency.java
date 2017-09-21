package com.paiorlolo.spectrumradar.fragments;

import android.os.Bundle;
import android.renderscript.Double2;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import com.paiorlolo.spectrumradar.R;
import com.paiorlolo.spectrumradar.utils.MathUtil;

import org.w3c.dom.Text;

import java.util.Set;

public class FragmentFrequency extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(container == null){
            return null;
        }

        View currentFragment = inflater.inflate(R.layout.page_fragment_frequency, container, false);
        TextView txt_value_Hz = (TextView)currentFragment.findViewById(R.id.txt_value_Hz);
        TextView txt_value_MHz = (TextView)currentFragment.findViewById(R.id.txt_value_MHz);
        TextView txt_value_KHz = (TextView)currentFragment.findViewById(R.id.txt_value_KHz);
        TextView txt_value_GHz = (TextView)currentFragment.findViewById(R.id.txt_value_GHz);

        String selectedUnit = getArguments().getString("selectedUnit");
        int color = R.color.colorIsertedValue;
        switch(selectedUnit){
            case "Hz":
                ((TableRow)txt_value_Hz.getParent()).setBackgroundResource(color);
                break;
            case "MHz":
                ((TableRow)txt_value_MHz.getParent()).setBackgroundResource(color);
                break;
            case "KHz":
                ((TableRow)txt_value_KHz.getParent()).setBackgroundResource(color);
                break;
            case "GHz":
                ((TableRow)txt_value_GHz.getParent()).setBackgroundResource(color);
                break;
        }

        // Hz
        String mainValue = getArguments().getString("calculatedFrequency");
        txt_value_Hz.setText(MathUtil.getSpannedText(this.getActivity(), mainValue, "Hz"));

        // MHz
        Double valor = MathUtil.valueFromHz(Double.valueOf(mainValue), "MHz");
        txt_value_MHz.setText(MathUtil.getSpannedText(this.getActivity(), valor, "MHz"));

        // GHz
        valor = MathUtil.valueFromHz(Double.valueOf(mainValue), "GHz");
        txt_value_GHz.setText(MathUtil.getSpannedText(this.getActivity(), valor, "GHz"));

        // KHz
        valor = MathUtil.valueFromHz(Double.valueOf(mainValue), "KHz");
        txt_value_KHz.setText(MathUtil.getSpannedText(this.getActivity(), valor, "KHz"));

        return currentFragment;
    }

}
