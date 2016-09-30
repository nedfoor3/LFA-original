package com.proyectofootball.titanes.lfa.adapter;

import android.app.Activity;
import android.content.Context;
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


    public DetailsTeamPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
            default:
                return new CalendarFragment();
            case 1:
                return new RosterFragment();
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
