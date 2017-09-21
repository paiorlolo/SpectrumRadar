package com.paiorlolo.spectrumradar.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.paiorlolo.spectrumradar.R;

import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments;
    Context c;

    public PagerAdapter(FragmentManager fm, List<Fragment> fragments, Context c){
        super(fm);
        this.fragments = fragments;
        this.c = c;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return c.getString(R.string.str_wavelength);
        }else if(position == 1){
            return c.getString(R.string.str_frequency);
        }else if(position == 2){
            return c.getString(R.string.str_wavenumber);
        }else{
            return "";
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
