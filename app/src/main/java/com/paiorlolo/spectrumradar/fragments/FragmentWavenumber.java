package com.paiorlolo.spectrumradar.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paiorlolo.spectrumradar.R;
import com.paiorlolo.spectrumradar.utils.MathUtil;

public class FragmentWavenumber extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(container == null){
            return null;
        }

        View currentFragment = inflater.inflate(R.layout.page_fragment_wavenumber, container, false);

        TextView txt_unit_km = (TextView)currentFragment.findViewById(R.id.txt_unit_km);
        txt_unit_km.setText(Html.fromHtml(txt_unit_km.getText()+getString(R.string.str_valueMinusOne)));
        TextView txt_unit_m = (TextView)currentFragment.findViewById(R.id.txt_unit_m);
        txt_unit_m.setText(Html.fromHtml(txt_unit_m.getText()+getString(R.string.str_valueMinusOne)));
        TextView txt_unit_cm = (TextView)currentFragment.findViewById(R.id.txt_unit_cm);
        txt_unit_cm.setText(Html.fromHtml(txt_unit_cm.getText()+getString(R.string.str_valueMinusOne)));
        TextView txt_unit_mm = (TextView)currentFragment.findViewById(R.id.txt_unit_mm);
        txt_unit_mm.setText(Html.fromHtml(txt_unit_mm.getText()+getString(R.string.str_valueMinusOne)));
        TextView txt_unit_microm = (TextView)currentFragment.findViewById(R.id.txt_unit_microm);
        txt_unit_microm.setText(Html.fromHtml(txt_unit_microm.getText()+getString(R.string.str_valueMinusOne)));
        TextView txt_unit_nm = (TextView)currentFragment.findViewById(R.id.txt_unit_nm);
        txt_unit_nm.setText(Html.fromHtml(txt_unit_nm.getText()+getString(R.string.str_valueMinusOne)));
        TextView txt_unit_angstrom = (TextView)currentFragment.findViewById(R.id.txt_unit_angstrom);
        txt_unit_angstrom.setText(Html.fromHtml(txt_unit_angstrom.getText()+getString(R.string.str_valueMinusOne)));

        TextView txt_value_km = (TextView)currentFragment.findViewById(R.id.txt_value_km);
        TextView txt_value_m = (TextView)currentFragment.findViewById(R.id.txt_value_m);
        TextView txt_value_cm = (TextView)currentFragment.findViewById(R.id.txt_value_cm);
        TextView txt_value_mm = (TextView)currentFragment.findViewById(R.id.txt_value_mm);
        TextView txt_value_microm = (TextView)currentFragment.findViewById(R.id.txt_value_microm);
        TextView txt_value_nm = (TextView)currentFragment.findViewById(R.id.txt_value_nm);
        TextView txt_value_angstrom = (TextView)currentFragment.findViewById(R.id.txt_value_angstrom);

        // m
        String mainValue = getArguments().getString("calculatedWavelength");
        txt_value_m.setText(MathUtil.getSpannedText(this.getActivity(), 1/Double.valueOf(mainValue), "m"+getString(R.string.str_valueMinusOne)));

        // cm
        Double valor = MathUtil.valueFromMeters(Double.valueOf(mainValue), "cm");
        txt_value_cm.setText(MathUtil.getSpannedText(this.getActivity(), 1/valor, "cm"+getString(R.string.str_valueMinusOne)));

        // km
        valor = MathUtil.valueFromMeters(Double.valueOf(mainValue), "km");
        txt_value_km.setText(MathUtil.getSpannedText(this.getActivity(), 1/valor, "km"+getString(R.string.str_valueMinusOne)));

        // mm
        valor = MathUtil.valueFromMeters(Double.valueOf(mainValue), "mm");
        txt_value_mm.setText(MathUtil.getSpannedText(this.getActivity(), 1/valor, "mm"+getString(R.string.str_valueMinusOne)));

        // µm
        valor = MathUtil.valueFromMeters(Double.valueOf(mainValue), "µm");
        txt_value_microm.setText(MathUtil.getSpannedText(this.getActivity(), 1/valor, "µm"+getString(R.string.str_valueMinusOne)));

        // nm
        valor = MathUtil.valueFromMeters(Double.valueOf(mainValue), "nm");
        txt_value_nm.setText(MathUtil.getSpannedText(this.getActivity(), 1/valor, "nm"+getString(R.string.str_valueMinusOne)));

        // Å
        valor = MathUtil.valueFromMeters(Double.valueOf(mainValue), "Å");
        txt_value_angstrom.setText(MathUtil.getSpannedText(this.getActivity(), 1/valor, "Å"+getString(R.string.str_valueMinusOne)));

        return currentFragment;
    }
}
