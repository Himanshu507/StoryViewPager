package com.himanshu.viewpager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import jp.shts.android.storiesprogressview.StoriesProgressView;

public class StoryFragment extends Fragment implements StoriesProgressView.StoriesListener {

    private TextView textView;
    private StoriesProgressView storiesProgressView;
    private static final int PROGRESS_COUNT = 6;
    int i = 0;
    private int counter = 0;
    ImageView story_img;
    List<String> img_urls = new ArrayList<>();
    View next_view, prev_view;

    private final long[] durations = new long[]{500L, 1000L, 1500L, 4000L, 5000L, 1000,};

    long pressTime = 0L;
    long limit = 500L;

    buttonClick click;

    interface buttonClick {
        void buttonClicked(View v);
    }


    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    pressTime = System.currentTimeMillis();
                    storiesProgressView.pause();
                    return false;
                case MotionEvent.ACTION_UP:
                    long now = System.currentTimeMillis();
                    storiesProgressView.resume();
                    return limit < now - pressTime;
            }
            return false;
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        click = (buttonClick) activity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_story, container, false);
        story_img = view.findViewById(R.id.image);
        next_view = view.findViewById(R.id.skip);
        prev_view = view.findViewById(R.id.reverse);
        storiesProgressView = view.findViewById(R.id.stories);
        String message = getArguments().getString("message");
        Toast.makeText(container.getContext(), message, Toast.LENGTH_SHORT).show();

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();

        next_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onNext();
                storiesProgressView.skip();
            }
        });
        next_view.setOnTouchListener(onTouchListener);

        prev_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onPrev();
                storiesProgressView.reverse();
            }
        });
        prev_view.setOnTouchListener(onTouchListener);

        img_urls.add("http://images.unsplash.com/photo-1529626455594-4ff0802cfb7e?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjEyMDd9");
        img_urls.add("https://oshiprint.in/image/data/poster/new/mqp1198.jpeg");
        img_urls.add("https://img.freepik.com/free-photo/travel-concept-close-up-portrait-young-beautiful-attractive-redhair-girl-wtih-trendy-hat-sunglass-smiling-blue-pastel-background-copy-space_1258-826.jpg?size=626&ext=jpg");
        img_urls.add("https://images.unsplash.com/photo-1504276048855-f3d60e69632f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80");
        img_urls.add("https://miscmedia-9gag-fun.9cache.com/images/thumbnail-facebook/1557216688.9396_YduXUP_n.jpg");
        img_urls.add("https://images.unsplash.com/photo-1514315384763-ba401779410f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60");


        storiesProgressView.setStoriesCount(img_urls.size()); // <- set stories
        storiesProgressView.setStoryDuration(5000L); // <- set a story duration
        storiesProgressView.setStoriesListener(this); // <- set listener
        storiesProgressView.startStories(); // <- start progress

        storiesProgressView.startStories(counter);
        Glide.with(this).load(img_urls.get(i)).into(story_img);

    }

    @Override
    public void onNext() {
        if (counter < (img_urls.size() - 1)) {
            counter++;
            Glide.with(this).load(img_urls.get(counter)).into(story_img);
        }
    }

    @Override
    public void onPrev() {
        if (counter > 0) {
            counter--;
            Glide.with(this).load(img_urls.get(counter)).into(story_img);
        }
    }

    @Override
    public void onComplete() {
        Toast.makeText(getContext(), "onComplete", Toast.LENGTH_SHORT).show();
        //viewPagerInterface.viewPagerUpdate(PageCounter.getCounter());
        click.buttonClicked(getView());

    }

}
