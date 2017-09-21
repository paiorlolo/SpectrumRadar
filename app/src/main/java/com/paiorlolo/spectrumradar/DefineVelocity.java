package com.paiorlolo.spectrumradar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.paiorlolo.spectrumradar.utils.MathUtil;
import com.paiorlolo.spectrumradar.utils.PrefsManager;

import java.util.ArrayList;
import java.util.List;

public class DefineVelocity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define_velocity);

        final EditText txtBase = (EditText) findViewById(R.id.txt_base);
        final EditText txtExp = (EditText) findViewById(R.id.txt_exp);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner_units);
        //final TextView lastValueHidden = new TextView(this);

        final List<String> spinnerArray = new ArrayList<String>(MathUtil.velocityConversionRates.keySet());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
/*
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = parent.getItemAtPosition(position).toString();
                String lastSelection = lastValueHidden.getText().toString();

                String strJoinedValue = MathUtil.joinBaseAndExp(txtBase.getText().toString(), txtExp.getText().toString());
                Double value = Double.valueOf(strJoinedValue);

                if(lastSelection.equals(selection)){
                    return;
                }

                Double valueInMs = null;
                if(lastSelection.equals("m/s")) {
                    valueInMs = value;
                }else{
                    valueInMs = MathUtil.valueToMs(value, lastSelection);
                }

                Double calculatedValue = MathUtil.valueFromMs(valueInMs, selection);
                txtBase.setText(MathUtil.getBaseAsString(calculatedValue));
                txtExp.setText(MathUtil.getExpAsString(calculatedValue));
                lastValueHidden.setText(selection);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
*/
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            String velocity = PrefsManager.getVelocity(this);
            txtBase.setText(MathUtil.getBaseAsString(velocity));
            txtExp.setText(MathUtil.getExpAsString(velocity));
            spinner.setSelection(spinnerArray.indexOf(PrefsManager.getVelocityUnit(this)));
        }
        //lastValueHidden.setText(PrefsManager.getVelocityUnit(this));
        Button btnSpeedOfLight = (Button) findViewById(R.id.btn_speedOfLight);
        Button btnSaveSpeed = (Button) findViewById(R.id.btn_saveSpeed);

        btnSpeedOfLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBase.setText("2.998");
                txtExp.setText("8");
                spinner.setSelection(spinnerArray.indexOf("m/s"));
                //lastValueHidden.setText("m/s");
            }
        });

        btnSaveSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefsManager.setVelocity(v.getContext(), MathUtil.joinBaseAndExp(txtBase.getText().toString(),txtExp.getText().toString()));
                PrefsManager.setVelocityUnit(v.getContext(), spinner.getSelectedItem().toString());
                finish();
            }
        });

    }


}
