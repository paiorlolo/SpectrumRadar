package com.paiorlolo.spectrumradar.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

public class FragmentWavelength extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(container == null){
            return null;
        }

        View currentFragment = inflater.inflate(R.layout.page_fragment_wavelength, container, false);

        TextView txt_value_km = (TextView)currentFragment.findViewById(R.id.txt_value_km);
        TextView txt_value_m = (TextView)currentFragment.findViewById(R.id.txt_value_m);
        TextView txt_value_cm = (TextView)currentFragment.findViewById(R.id.txt_value_cm);
        TextView txt_value_mm = (TextView)currentFragment.findViewById(R.id.txt_value_mm);
        TextView txt_value_microm = (TextView)currentFragment.findViewById(R.id.txt_value_microm);
        TextView txt_value_nm = (TextView)currentFragment.findViewById(R.id.txt_value_nm);
        TextView txt_value_angstrom = (TextView)currentFragment.findViewById(R.id.txt_value_angstrom);

        String selectedUnit = getArguments().getString("selectedUnit");
        int color = R.color.colorIsertedValue;
        switch(selectedUnit){
            case "km":
                ((TableRow)txt_value_km.getParent()).setBackgroundResource(color);
                break;
            case "m":
                ((TableRow)txt_value_m.getParent()).setBackgroundResource(color);
                break;
            case "cm":
                ((TableRow)txt_value_cm.getParent()).setBackgroundResource(color);
                break;
            case "mm":
                ((TableRow)txt_value_mm.getParent()).setBackgroundResource(color);
                break;
            case "µm":
                ((TableRow)txt_value_microm.getParent()).setBackgroundResource(color);
                break;
            case "nm":
                ((TableRow)txt_value_nm.getParent()).setBackgroundResource(color);
                break;
            case "Å":
                ((TableRow)txt_value_angstrom.getParent()).setBackgroundResource(color);
                break;
        }

        // m
        String mainValue = getArguments().getString("calculatedWavelength");
        txt_value_m.setText(MathUtil.getSpannedText(this.getActivity(), mainValue, "m"));

        // cm
        Double valor = MathUtil.valueFromMeters(Double.valueOf(mainValue), "cm");
        txt_value_cm.setText(MathUtil.getSpannedText(this.getActivity(), valor, "cm"));

        // km
        valor = MathUtil.valueFromMeters(Double.valueOf(mainValue), "km");
        txt_value_km.setText(MathUtil.getSpannedText(this.getActivity(), valor, "km"));

        // mm
        valor = MathUtil.valueFromMeters(Double.valueOf(mainValue), "mm");
        txt_value_mm.setText(MathUtil.getSpannedText(this.getActivity(), valor, "mm"));

        // µm
        valor = MathUtil.valueFromMeters(Double.valueOf(mainValue), "µm");
        txt_value_microm.setText(MathUtil.getSpannedText(this.getActivity(), valor, "µm"));

        // nm
        valor = MathUtil.valueFromMeters(Double.valueOf(mainValue), "nm");
        txt_value_nm.setText(MathUtil.getSpannedText(this.getActivity(), valor, "nm"));

        // Å
        valor = MathUtil.valueFromMeters(Double.valueOf(mainValue), "Å");
        txt_value_angstrom.setText(MathUtil.getSpannedText(this.getActivity(), valor, "Å"));

        return currentFragment;
    }
}
