package com.himanshu.viewpager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class StoryCollectionAdapter extends FragmentStatePagerAdapter {

    public StoryCollectionAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        StoryFragment storyFragment = new StoryFragment();
        Bundle bundle = new Bundle();
        position = position+1;
        bundle.putString("message","Hello from page : "+position);
        storyFragment.setArguments(bundle);
        return storyFragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
