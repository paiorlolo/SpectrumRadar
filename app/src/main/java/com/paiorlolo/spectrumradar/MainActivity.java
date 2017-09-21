package com.paiorlolo.spectrumradar;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.paiorlolo.spectrumradar.utils.MathUtil;
import com.paiorlolo.spectrumradar.utils.PrefsManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    Boolean baseCompleted = false;
    Boolean radioCompleted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PrefsManager.init(this);
        retrieveCurrentVelocity();

        Button btnWavelength = (Button) findViewById(R.id.btn_wavelength);
        btnWavelength.setOnClickListener(showDialogFromButton);

        Button btnFrequency = (Button) findViewById(R.id.btn_frequency);
        btnFrequency.setOnClickListener(showDialogFromButton);

        Button btnWavenumber = (Button) findViewById(R.id.btn_wavenumber);
        btnWavenumber.setOnClickListener(showDialogFromButton);

        TextView unitsReference = (TextView) findViewById(R.id.txt_unitsReference);
        unitsReference.setText(Html.fromHtml(getText(R.string.str_references).toString()));
    }

    View.OnClickListener showDialogFromButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDialog((Button) v);
        }
    };

    private void showDialog(final Button callerButton) {
        // CONSTRUCTOR
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        final View mView = getLayoutInflater().inflate(R.layout.dialog_insert_value, null);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        baseCompleted = false;
        radioCompleted = false;

        // TITLE
        TextView txtTitle = (TextView) mView.findViewById(R.id.dialog_title);

        // DESCRIPTION
        TextView txtDescription =  (TextView) mView.findViewById(R.id.dialog_description);
        if(callerButton.getId() == R.id.btn_wavelength) {
            txtDescription.setText(R.string.str_wavelengthDescription);
            txtTitle.setText("f ⟹ λ");
        }else if(callerButton.getId() == R.id.btn_frequency){
            txtDescription.setText(R.string.str_frequencyDescription);
            txtTitle.setText("λ ⟹ f");
        }else if(callerButton.getId() == R.id.btn_wavenumber){
            txtDescription.setText(R.string.str_wavenumberDescription);
            txtTitle.setText("λ ⟹ ṽ");
        }
        // RADIO BUTTONS
        final RadioGroup rgroup = (RadioGroup) mView.findViewById(R.id.radioGrp_units);
        RadioButton rBtnUnit1,rBtnUnit2,rBtnUnit3;
        rBtnUnit1 = (RadioButton) mView.findViewById(R.id.rBtnUnit1);
        rBtnUnit2 = (RadioButton) mView.findViewById(R.id.rBtnUnit2);
        rBtnUnit3 = (RadioButton) mView.findViewById(R.id.rBtnUnit3);
        String unit1 = "", unit2 = "", unit3 = "";

        if(callerButton.getId() == R.id.btn_wavelength){
            Set<String> frequenciesSet = PrefsManager.getFrequencies(this);
            List<String> frequenciesArrayList = new ArrayList<String>(frequenciesSet);
            unit1 = frequenciesArrayList.size()>0?frequenciesArrayList.get(0):"";
            unit2 = frequenciesArrayList.size()>1?frequenciesArrayList.get(1):"";
            unit3 = frequenciesArrayList.size()>2?frequenciesArrayList.get(2):"";
        }else{
            Set<String> lengthsSet = PrefsManager.getLengths(this);
            List<String> lengthsArrayList = new ArrayList<String>(lengthsSet);
                unit1 = lengthsArrayList.size() > 0 ? lengthsArrayList.get(0) : "";
                unit2 = lengthsArrayList.size() > 1 ? lengthsArrayList.get(1) : "";
                unit3 = lengthsArrayList.size() > 2 ? lengthsArrayList.get(2) : "";
        }
        if(unit1.length()>0){
            rBtnUnit1.setText(unit1);
            rBtnUnit1.setVisibility(View.VISIBLE);
        }
        if(unit2.length()>0){
            rBtnUnit2.setText(unit2);
            rBtnUnit2.setVisibility(View.VISIBLE);
        }
        if(unit3.length()>0){
            rBtnUnit3.setText(unit3);
            rBtnUnit3.setVisibility(View.VISIBLE);
        }

        // BUTTON
        final Button btnCalculate = (Button) mView.findViewById(R.id.btn_calculate);
        final EditText base = (EditText) mView.findViewById(R.id.txt_base);
        final EditText exp = (EditText) mView.findViewById(R.id.txt_exp);



        btnCalculate.setEnabled(false);
        rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                Log.i("SE LO APRETO", ""+rgroup.getCheckedRadioButtonId());
                radioCompleted = true;
                if(radioCompleted && baseCompleted){
                    btnCalculate.setEnabled(true);
                }
            }
        });
        base.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                if(base.getText().length()>0){
                    baseCompleted = true;
                }else{
                    baseCompleted = false;
                }
                if(radioCompleted && baseCompleted){
                    btnCalculate.setEnabled(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rgroup.getCheckedRadioButtonId();
                RadioButton selectedRbtn = (RadioButton) mView.findViewById(rgroup.getCheckedRadioButtonId());
                String radioSelected = "";
                if(selectedRbtn!=null){
                    radioSelected = selectedRbtn.getText().toString();
                }

                Intent intent = new Intent(mView.getContext(), WaveResults.class);
                intent.putExtra("velocity",PrefsManager.getVelocity(v.getContext()));
                intent.putExtra("velocityUnit",PrefsManager.getVelocityUnit(v.getContext()));
                intent.putExtra("selectedUnit", radioSelected);
                intent.putExtra("selectedType", callerButton.getContentDescription());
                String expValue = exp.getText().length()>0?exp.getText().toString():"0";
                Log.i("Valor a mandar: ",MathUtil.joinBaseAndExp(base.getText().toString(), expValue));
                intent.putExtra("insertedValue",MathUtil.joinBaseAndExp(base.getText().toString(), expValue));
                dialog.dismiss();
                startActivity(intent);

            }
        });

        // SHOW
        dialog.show();
    }

    private void retrieveCurrentVelocity() {
        String currentVelocity = PrefsManager.getVelocity(this);
        Log.i("currentVelocity", currentVelocity);
        String velocityUnit = PrefsManager.getVelocityUnit(this);

        TextView txt_currentVelocity = (TextView) findViewById(R.id.txt_currentVelocity);
        String txtBase = MathUtil.getBaseAsString(currentVelocity);
        String txtExp = MathUtil.getExpAsString(currentVelocity);
        String txtCurrentVelocity = "";
        if(currentVelocity.toLowerCase().equals("2.998e8") && velocityUnit.toLowerCase().equals("m/s")){
            txtCurrentVelocity = getString(R.string.str_currentSpeedLight);
        }else{
            txtCurrentVelocity = getString(R.string.str_currentVelocity,txtBase,txtExp,velocityUnit);
        }

        txt_currentVelocity.setText(Html.fromHtml(txtCurrentVelocity));
    }

    @Override
    public void onResume(){
        super.onResume();
        retrieveCurrentVelocity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.btn_frequentUnits) {
            intent = new Intent(this, FrequentUnits.class);
            startActivity(intent);
        }else if(id == R.id.btn_showFullSpectrum){
            intent = new Intent(this, FullSpectrum.class);
            startActivity(intent);
        }else if(id == R.id.btn_defineVelocity){
            intent = new Intent(this, DefineVelocity.class);
            intent.putExtra("VELOCITY",PrefsManager.getVelocity(this));
            intent.putExtra("VELOCITY_UNIT",PrefsManager.getVelocityUnit(this));
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
