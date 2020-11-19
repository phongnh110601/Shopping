package com.nhpva.shopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.nhpva.shopping.R;

import java.util.List;

public class PageIndicatorAdapter extends PagerAdapter {
    private List<String> imageEvents;
    private Context context;

    public PageIndicatorAdapter(List<String> imageEvents, Context context) {
        this.imageEvents = imageEvents;
        this.context = context;
    }

    public PageIndicatorAdapter() {
    }

    @Override
    public int getCount() {
        return imageEvents.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vp_event, container, false);
        ImageView imgEvent = view.findViewById(R.id.img_event);
        Glide.with(imgEvent).load(imageEvents.get(position)).into(imgEvent);
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
