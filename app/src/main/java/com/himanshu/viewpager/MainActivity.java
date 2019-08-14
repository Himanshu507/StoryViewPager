package com.himanshu.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements StoryFragment.buttonClick {

    ViewPager viewPager;
    StoryCollectionAdapter storyCollectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(0);

        storyCollectionAdapter = new StoryCollectionAdapter(getSupportFragmentManager());
        viewPager.setAdapter(storyCollectionAdapter);

    }

    public void buttonClicked(View v){
        //get your viewPager var
        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
    }
}
