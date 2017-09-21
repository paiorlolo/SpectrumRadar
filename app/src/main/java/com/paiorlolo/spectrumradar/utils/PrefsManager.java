package com.paiorlolo.spectrumradar.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class PrefsManager {
    public static final String USER_PREFS = "userPrefs";

    private static final String key_LENGTHS = "LENGTHS";
    private static final String key_FREQUENCIES = "FREQUENCIES";
    private static final String key_VELOCITY = "VELOCITY";
    private static final String key_VELOCITY_UNIT = "VELOCITY_UNIT";

    private static Set<String> lengths;
    private static Set<String> frequencies;
    private static String velocity;
    private static String velocityUnit;

    private PrefsManager(){}

    public static void init(Context context){
        SharedPreferences pref = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);

        lengths = pref.getStringSet(key_LENGTHS ,new HashSet<String>(Arrays.asList("m", "cm", "mm")));
        frequencies = pref.getStringSet(key_FREQUENCIES ,new HashSet<String>(Arrays.asList("Hz", "MHz", "GHz")));
        velocity = pref.getString(key_VELOCITY, "3e8");
        velocityUnit = pref.getString(key_VELOCITY_UNIT, "m/s");
    }

    public static Set<String> getLengths(Context context) {
        SharedPreferences pref = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
        return pref.getStringSet(key_LENGTHS ,new HashSet<String>(Arrays.asList("m", "cm", "mm")));
    }

    public static void setLengths(Context context, Set<String> lengths) {
        SharedPreferences pref = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
        pref.edit().putStringSet(key_LENGTHS, lengths).apply();
    }

    public static Set<String> getFrequencies(Context context) {
        SharedPreferences pref = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
        return pref.getStringSet(key_FREQUENCIES ,new HashSet<String>(Arrays.asList("Hz", "MHz", "GHz")));
    }

    public static void setFrequencies(Context context, Set<String> frequencies) {
        SharedPreferences pref = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
        pref.edit().putStringSet(key_FREQUENCIES, frequencies).apply();
    }

    public static String getVelocity(Context context) {
        SharedPreferences pref = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
        return pref.getString(key_VELOCITY, "3e8");
    }

    public static void setVelocity(Context context, String velocity) {
        SharedPreferences pref = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
        pref.edit().putString(key_VELOCITY, velocity).apply();
    }

    public static String getVelocityUnit(Context context) {
        SharedPreferences pref = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
        return pref.getString(key_VELOCITY_UNIT, "m/s");
    }

    public static void setVelocityUnit(Context context, String velocityUnit) {
        SharedPreferences pref = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
        pref.edit().putString(key_VELOCITY_UNIT, velocityUnit).apply();
    }
}
