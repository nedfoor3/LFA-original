package com.proyectofootball.titanes.lfa;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.util.DisplayMetrics;

import com.proyectofootball.titanes.lfa.R;

import java.util.Locale;
import java.util.prefs.PreferencesFactory;

/**
 * Created by Tonatiuh on 18/08/2016.
 */
public class Preferencias extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }
    private void setLocale(String lang) {
    /*SharedPreferences prefs = getActivity().getSharedPreferences(
              "com.example.app", Context.MODE_PRIVATE);*/

        SharedPreferences setPrefs = getApplicationContext().getSharedPreferences("com.example.app", MODE_PRIVATE);
        SharedPreferences.Editor editor = setPrefs.edit();
        editor.putString("language", "en");
        editor.commit();


        Locale myLocale = new Locale(lang);

        //myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this    , MainActivity.class);
        startActivity(refresh);

    /*prefs.edit().putString("language", "en").apply();
    String lan = prefs.getString("language", Locale.getDefault().getLanguage() );
    //setLocale(lan);   //Remove this. It will create infinite loop */

        SharedPreferences getPrefs = getApplicationContext().getSharedPreferences("com.example.app", MODE_PRIVATE);
        String lan = getPrefs.getString("language", "");
        System.out.println("-- locale from getPrefs " + lan);

    }
}