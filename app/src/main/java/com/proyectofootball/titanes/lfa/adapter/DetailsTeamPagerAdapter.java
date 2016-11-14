package com.proyectofootball.titanes.lfa.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.proyectofootball.titanes.lfa.R;
import com.proyectofootball.titanes.lfa.fragments.CalendarFragment;
import com.proyectofootball.titanes.lfa.fragments.RosterFragment;

/**
 * Created by Ricardo Rodriguez on 9/28/2016.
 */

public class DetailsTeamPagerAdapter extends FragmentPagerAdapter {


    String tabTitles[] = new String[]{"Calendario", "Roster"};
    Context context;
    String nombreEquipo;
    Bundle args = new Bundle();


    public DetailsTeamPagerAdapter(FragmentManager fm, Context context, String nombreEquipo) {
        super(fm);
        this.context = context;
        this.nombreEquipo = nombreEquipo;
        args.putString("nombreEquipo", nombreEquipo);

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
            default:
                Fragment calendarF = new CalendarFragment();
                calendarF.setArguments(args);
                return calendarF;
            case 1:
                Fragment rosterF = new RosterFragment();
                rosterF.setArguments(args);

                return rosterF;
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    public View getTabView(Activity activity, int position) {
        View tab = LayoutInflater.from(activity).inflate(R.layout.custom_tab, null);
        TextView tv = (TextView) tab.findViewById(R.id.custom_text);
        tv.setText(tabTitles[position]);
        return tab;
    }
}
