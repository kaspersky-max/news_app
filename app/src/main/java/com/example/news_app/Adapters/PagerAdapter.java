package com.example.news_app.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.news_app.Fragments.AktuelnoFragment;
import com.example.news_app.Fragments.SportFragment;
import com.example.news_app.Fragments.TehnologijaFragment;
import com.example.news_app.Fragments.ZabavaFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    int tabCount;

    public PagerAdapter(@NonNull FragmentManager fm, int tabCount) {
        super(fm, tabCount);
        this.tabCount = tabCount;
    }

    public PagerAdapter(@NonNull FragmentManager fm, int behavior, int tabCount) {
        super(fm, behavior);
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new AktuelnoFragment();
            case 1:
                return new SportFragment();
            case 2:
                return new ZabavaFragment();
            case 3:
                return new TehnologijaFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
