package com.paiorlolo.spectrumradar;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.paiorlolo.spectrumradar.adapters.PagerAdapter;
import com.paiorlolo.spectrumradar.fragments.FragmentFrequency;
import com.paiorlolo.spectrumradar.fragments.FragmentWavelength;
import com.paiorlolo.spectrumradar.fragments.FragmentWavenumber;
import com.paiorlolo.spectrumradar.utils.MathUtil;

import java.util.List;
import java.util.Vector;

public class WaveResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_results);

        TextView waveType = (TextView) findViewById(R.id.txt_waveType);
        TextView waveSubType = (TextView) findViewById(R.id.txt_waveSubType);
        TextView tvInsertedValue = (TextView) findViewById(R.id.txt_insertedValue);

        String calculatedWaveType = "", calculatedWaveSubType = "";
        Double wavelengthInMeters = 0.0;
        Double frequencyInHz = 0.0;
        Double velocityInMeters = MathUtil.valueToMs(Double.valueOf(getIntent().getStringExtra("velocity")), getIntent().getStringExtra("velocityUnit"));
        Double receivedValue = Double.valueOf(getIntent().getStringExtra("insertedValue"));
        String receivedUnit = getIntent().getStringExtra("selectedUnit");
        Log.i("receivedValue", getIntent().getStringExtra("insertedValue"));
        Log.i("receivedUnit", receivedUnit);
        Log.i("selectedType", getIntent().getStringExtra("selectedType"));

        if(getIntent().getStringExtra("selectedType").equals(getText(R.string.str_wavelength))){
            // WAVELENGTH
            frequencyInHz = MathUtil.valueToHz(receivedValue, receivedUnit);
            wavelengthInMeters = MathUtil.calculateWavelength(velocityInMeters, frequencyInHz);
        }else if(getIntent().getStringExtra("selectedType").equals(getText(R.string.str_frequency))) {
            // FREQUENCY
            wavelengthInMeters = MathUtil.valueToMeters(receivedValue, receivedUnit);
        }else if(getIntent().getStringExtra("selectedType").equals(getText(R.string.str_wavenumber))) {
            // WAVENUMBER
            wavelengthInMeters = MathUtil.valueToMeters(receivedValue, receivedUnit);
        }

        if(frequencyInHz == 0.0){
            frequencyInHz = MathUtil.calculateFrequency(velocityInMeters, wavelengthInMeters);
        }
        calculatedWaveType = MathUtil.calculateWaveType(this, frequencyInHz);
        waveType.setText(calculatedWaveType);
        calculatedWaveSubType = MathUtil.calculateWaveSubType(this, frequencyInHz);
        waveSubType.setText(calculatedWaveSubType);

        Log.i("getString naranja", this.getString(R.string.str_subSpectrum_orange));
        if(this.getString(R.string.str_subSpectrum_red).equals(calculatedWaveSubType)){
            waveSubType.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        }else if(this.getString(R.string.str_subSpectrum_orange).equals(calculatedWaveSubType)){
            waveSubType.setTextColor(getResources().getColor(android.R.color.holo_orange_light));
        }else if(this.getString(R.string.str_subSpectrum_yellow).equals(calculatedWaveSubType)){
            waveSubType.setTextColor(getResources().getColor(R.color.yellow));
        }else if(this.getString(R.string.str_subSpectrum_green).equals(calculatedWaveSubType)){
            waveSubType.setTextColor(getResources().getColor(android.R.color.holo_green_light));
        }else if(this.getString(R.string.str_subSpectrum_blue).equals(calculatedWaveSubType)){
            waveSubType.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        }else if(this.getString(R.string.str_subSpectrum_violet).equals(calculatedWaveSubType)){
            waveSubType.setTextColor(getResources().getColor(android.R.color.holo_purple));
        }
        String strInsertedValue =  getIntent().getStringExtra("insertedValue");
        String txtBase = MathUtil.getBaseAsString(strInsertedValue);
        String txtExp = MathUtil.getExpAsString(strInsertedValue);
        String strInsertedValueMessage = getString(R.string.str_insertedValue);
        String valorFormateado = "";
        if(!strInsertedValue.toLowerCase().contains("e") || strInsertedValue.toLowerCase().contains("e0")){
            valorFormateado = getString(R.string.str_valueWithoutExp,txtBase,getIntent().getStringExtra("selectedUnit"));
        }else{
            valorFormateado = getString(R.string.str_valueWithUnit,txtBase,txtExp,getIntent().getStringExtra("selectedUnit"));
        }
        tvInsertedValue.setText(Html.fromHtml(strInsertedValueMessage+" "+valorFormateado));

        // COMPLETAR LOS FRAGMENTS

        List<Fragment> fragments = new Vector<>();
        FragmentWavelength fragmentWavelength = new FragmentWavelength();
        fragments.add(fragmentWavelength);
        FragmentFrequency fragmentFrequency = new FragmentFrequency();
        fragments.add(fragmentFrequency);
        FragmentWavenumber fragmentWavenumber = new FragmentWavenumber();
        fragments.add(fragmentWavenumber);

        Bundle bundle = new Bundle();
        bundle.putString("calculatedFrequency",String.valueOf(MathUtil.calculateFrequency(velocityInMeters, wavelengthInMeters)));
        bundle.putString("calculatedWavelength",String.valueOf(wavelengthInMeters));
        bundle.putString("selectedUnit",getIntent().getStringExtra("selectedUnit"));
        fragmentFrequency.setArguments(bundle);
        fragmentWavelength.setArguments(bundle);
        fragmentWavenumber.setArguments(bundle);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), fragments, this);
        final ViewPager pager = (ViewPager) findViewById(R.id.pager);

        pager.setAdapter(adapter);

        if(getIntent().getStringExtra("selectedType").equals(getText(R.string.str_wavelength))){
            // WAVELENGTH
           pager.setCurrentItem(0);
        }else if(getIntent().getStringExtra("selectedType").equals(getText(R.string.str_frequency))) {
            // FREQUENCY
            pager.setCurrentItem(1);
        }else if(getIntent().getStringExtra("selectedType").equals(getText(R.string.str_wavenumber))) {
            // WAVENUMBER
            pager.setCurrentItem(2);
        }

    }
}
