package me.std.base.widget.toptab.adapter;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Roger Huang on 2019/1/17.
 */

public abstract class CYViewPagerAdapter extends FragmentPagerAdapter {
    List<String> mTitles;

    public CYViewPagerAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
