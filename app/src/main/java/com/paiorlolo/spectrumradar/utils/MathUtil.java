package com.paiorlolo.spectrumradar.utils;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;

import com.paiorlolo.spectrumradar.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MathUtil {

    private static int SIGNIFICANT_DECIMALS = 3;

    public static final Map<String, Double> lengthConversionRates = new HashMap<String, Double>() {{
        put("km", 1e3);
        put("m", 1e0);
        put("cm", 1e-2);
        put("mm", 1e-3);
        put("µm", 1e-6);
        put("nm", 1e-9);
        put("Å", 1e-10);
    }};

    public static final Map<String, Double> frequencyConversionRates = new HashMap<String, Double>() {{
        put("Hz", 1e0);
        put("KHz", 1e3);
        put("MHz", 1e6);
        put("GHz", 1e9);
    }};

    public static final Map<String, Double> velocityConversionRates = new HashMap<String, Double>() {{
        put("m/s", 1e0);
        put("km/h", 3.6e0);
        put("km/s", 1e-3);
    }};

    public static double calculateWavelength(double velocity, double frequency) {
        Log.i("calculateWavelength", "" + (velocity / frequency));
        return velocity / frequency;
    }

    public static double calculateFrequency(double velocity, double wavelength) {
        Log.i("calculateFrequency", "" + (velocity / wavelength));
        return velocity / wavelength;
    }

    public static String calculateWaveType(Context c, double frequencyInHz) {
        String type = "";
        Log.i("frequencyInHz", "" + frequencyInHz);
        if (frequencyInHz > 3e19) {
            type = c.getString(R.string.str_spectrum_gammaRay);
        }else if (frequencyInHz > 3e16 && frequencyInHz <= 3e19) {
            type = c.getString(R.string.str_spectrum_xRays);
        }else if (frequencyInHz > 7e14 && frequencyInHz < 3e16) {
            type = c.getString(R.string.str_spectrum_Ultraviolet);
        }else if (frequencyInHz >= 4e14 && frequencyInHz <= 7e14) {
            type = c.getString(R.string.str_spectrum_visible);
        }else if (frequencyInHz > 3e11 && frequencyInHz < 4e14) {
            type = c.getString(R.string.str_spectrum_infrared);
        }else if (frequencyInHz > 3e9 && frequencyInHz <= 3e11) {
            type = c.getString(R.string.str_spectrum_microwaves);
        } else if(frequencyInHz <= 3e9) {
            type = c.getString(R.string.str_spectrum_radio);
        }
        return type;
    }

    //-----------
    // LENGHTS
    //-----------
    public static double valueToMeters(double value, String unit) {
        switch (unit) {
            case "km":
                return km_to_m(value);
            case "cm":
                return cm_to_m(value);
            case "m":
                return value;
            case "mm":
                return mm_to_m(value);
            case "µm":
                return microm_to_m(value);
            case "nm":
                return nm_to_m(value);
            case "Å":
                return angstrong_to_m(value);
        }
        return 0;
    }

    public static double valueFromMeters(double value, String unit) {
        switch (unit) {
            case "km":
                return m_to_km(value);
            case "cm":
                return m_to_cm(value);
            case "m":
                return value;
            case "mm":
                return m_to_mm(value);
            case "µm":
                return m_to_microm(value);
            case "nm":
                return m_to_nm(value);
            case "Å":
                return m_to_angstrong(value);
        }
        return 0;
    }

    private static double m_to_cm(double value) {return value / lengthConversionRates.get("cm");
    }

    private static double m_to_km(double value) {
        return value / lengthConversionRates.get("km");
    }

    private static double m_to_mm(double value) {
        return value / lengthConversionRates.get("mm");
    }

    private static double m_to_microm(double value) {
        return value / lengthConversionRates.get("µm");
    }
    private static double m_to_nm(double value) {
        return value / lengthConversionRates.get("nm");
    }
    private static double m_to_angstrong(double value) {
        return value / lengthConversionRates.get("Å");
    }

    private static double km_to_m(double value) {
        return value * lengthConversionRates.get("km");
    }

    private static double cm_to_m(double value) {
        return value * lengthConversionRates.get("cm");
    }

    private static double mm_to_m(double value) {
        return value * lengthConversionRates.get("mm");
    }

    private static double microm_to_m(double value) {
        return value * lengthConversionRates.get("µm");
    }

    private static double nm_to_m(double value) {
        return value * lengthConversionRates.get("nm");
    }

    private static double angstrong_to_m(double value) {
        return value * lengthConversionRates.get("Å");
    }

    //-----------
    // FREQUENCY
    //-----------
    public static double valueToHz(double value, String unit) {
        Log.i("value", "" + value);
        Log.i("unit", "" + unit);
        switch (unit) {
            case "Hz":
                return value;
            case "MHz":
                return mhz_to_hz(value);
            case "GHz":
                return ghz_to_hz(value);
            case "KHz":
                return khz_to_hz(value);
        }
        return 0;
    }

    public static double valueFromHz(Double value, String unit) {
        switch (unit) {
            case "Hz":
                return value;
            case "MHz":
                return hz_to_mhz(value);
            case "GHz":
                return hz_to_ghz(value);
            case "KHz":
                return hz_to_khz(value);
        }
        return 0;
    }

    private static double mhz_to_hz(double value) {
        return value * frequencyConversionRates.get("MHz");
    }

    private static double ghz_to_hz(double value) {
        return value * frequencyConversionRates.get("GHz");
    }

    private static double khz_to_hz(double value) {
        return value * frequencyConversionRates.get("KHz");
    }

    private static double hz_to_mhz(double value) {
        return value / frequencyConversionRates.get("MHz");
    }

    private static double hz_to_ghz(double value) {
        return value / frequencyConversionRates.get("GHz");
    }

    private static double hz_to_khz(double value) {
        return value / frequencyConversionRates.get("KHz");
    }

    //-----------
    // VELOCITY
    //-----------
    public static double valueToMs(double value, String unit) {
        Log.i("value", "" + value);
        Log.i("unit", "" + unit);
        switch (unit) {
            case "m/s":
                return value;
            case "km/h":
                return kmh_to_ms(value);
            case "km/s":
                return kms_to_ms(value);
        }
        return 0;
    }

    private static double kmh_to_ms(double value) {
        return value * velocityConversionRates.get("km/h");
    }

    private static double kms_to_ms(double value) {
        return value * velocityConversionRates.get("km/s");
    }

    public static double valueFromMs(double value, String unit) {
        Log.i("value", "" + value);
        Log.i("unit", "" + unit);
        switch (unit) {
            case "m/s":
                return value;
            case "km/h":
                return ms_to_kmh(value);
            case "km/s":
                return ms_to_kms(value);
        }
        return 0;
    }

    private static double ms_to_kmh(double value) {
        return value / velocityConversionRates.get("km/h");
    }

    private static double ms_to_kms(double value) {
        return value / velocityConversionRates.get("km/s");
    }

    public static String calculateWaveSubType(Context c, double frequencyInHz) {
        String subType = "";
        Log.i("frequencyInHz", "" + frequencyInHz);
        // VISIBLE
        if (frequencyInHz >= 4e14 && frequencyInHz < 4.68e14) {
            subType = c.getString(R.string.str_subSpectrum_red);
        } else if (frequencyInHz >= 4.68e14 && frequencyInHz < 5e14) {
            subType = c.getString(R.string.str_subSpectrum_orange);
        } else if (frequencyInHz >= 5e14 && frequencyInHz < 5.26e14) {
            subType = c.getString(R.string.str_subSpectrum_yellow);
        } else if (frequencyInHz >= 5.26e14 && frequencyInHz < 6.12e14) {
            subType = c.getString(R.string.str_subSpectrum_green);
        } else if (frequencyInHz >= 6.12e14 && frequencyInHz < 6.97e14) {
            subType = c.getString(R.string.str_subSpectrum_blue);
        } else if (frequencyInHz >= 6.97e14 && frequencyInHz <= 7e14) {
            subType = c.getString(R.string.str_subSpectrum_violet);
        } //ULTRAVIOLET
        else  if (frequencyInHz > 7e14 && frequencyInHz <= 15e14) {
            subType = c.getString(R.string.str_subSpectrum_near);
        }else  if (frequencyInHz > 15e14 && frequencyInHz <= 3e16) {
            subType = c.getString(R.string.str_subSpectrum_extreme);
        } //INFRARED
        else  if (frequencyInHz > 3e11 && frequencyInHz <= 6e12) {
            subType = c.getString(R.string.str_subSpectrum_far);
        }else  if (frequencyInHz > 6e12 && frequencyInHz <= 120e12) {
            subType = c.getString(R.string.str_subSpectrum_medium);
        }else  if (frequencyInHz > 120e12 && frequencyInHz < 4e14) {
            subType = c.getString(R.string.str_subSpectrum_near);
        } //RADIO
        else  if (frequencyInHz > 3e8 && frequencyInHz <= 3e9) {
            subType = c.getString(R.string.str_subSpectrum_ultraHighFrequency);
        }else  if (frequencyInHz > 3e7 && frequencyInHz <= 3e8) {
            subType = c.getString(R.string.str_subSpectrum_veryHighFrequency);
        }else  if (frequencyInHz > 17e5 && frequencyInHz <= 3e7) {
            subType = c.getString(R.string.str_subSpectrum_shortWave);
        }else  if (frequencyInHz > 650e3 && frequencyInHz <= 17e5) {
            subType = c.getString(R.string.str_subSpectrum_mediumWave);
        }else  if (frequencyInHz > 3e4 && frequencyInHz <= 650e3) {
            subType = c.getString(R.string.str_subSpectrum_longWave);
        }else  if (frequencyInHz <= 3e4) {
            subType = c.getString(R.string.str_subSpectrum_veryLowFrequency);
        }

        Log.i("subtype",subType);
        return subType;
    }

    public static String getBaseAsString(String value){
        value = value.toLowerCase();
        if(value.contains("e")){
            return value.substring(0,value.indexOf("e"));
        }else{
            return value;
        }
    }

    public static String getBaseAsString(Double value){
        String str = String.valueOf(value);
        return getBaseAsString(str);
    }

    public static String getExpAsString(String value){
        value = value.toLowerCase();
        if(value.contains("e")){
            return value.substring(value.indexOf("e")+1,value.length());
        }else{
            return "0";
        }

    }

    public static String getExpAsString(Double value){
        String str = String.valueOf(value);
        return getExpAsString(str);
    }

    public static String joinBaseAndExp(String base, String exp){
        return base+"e"+exp;
    }

    public static Spanned getSpannedText(Context context, String valor, String unit){
        String valorFormateado = getFormattedValue(context, valor, unit);
        return Html.fromHtml(valorFormateado);
    }

    public static Spanned getSpannedText(Context context, Double valor, String unit){
        String strValor = String.valueOf(valor);
        String valorFormateado = getFormattedValue(context, strValor, unit);
        return Html.fromHtml(valorFormateado);
    }

    private static String getFormattedValue(Context context, String strValue, String unit){
        Double valor = Double.valueOf(strValue);
        DecimalFormat df = new DecimalFormat("##0.##E0");
        df.setMaximumFractionDigits(3);
        String strValor = df.format(valor);

        String txtBase = MathUtil.getBaseAsString(strValor);
        if(!strValor.toLowerCase().contains("e") || strValor.toLowerCase().contains("e0")){
            return context.getString(R.string.str_valueWithoutExp,txtBase,unit);
        }else{
            String txtExp = MathUtil.getExpAsString(strValor);
            return context.getString(R.string.str_valueWithUnit,txtBase,txtExp,unit);
        }

    }

}