package com.example.secretsofthecursedcastle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class HintPagerAdapter extends PagerAdapter {

    //Declares the variables needed
    List<String> titles;
    List<String> descriptions;
    List<Integer> images;
    Context myContext;

    public HintPagerAdapter(List<String> titles, List<String> descriptions, List<Integer> images, Context myContext) {
        this.titles = titles;
        this.descriptions = descriptions;
        this.images = images;
        this.myContext = myContext;
    }
    //Not used by me
    @Override
    public int getCount() {
        return titles.size();
    }

    //Not used by me
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view ==  o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(myContext).inflate(R.layout.sample_hint_pager, container, false);
        //Declares and finds widgets in the sample list
        ImageView img = v.findViewById(R.id.imgHint);
        TextView txt = v.findViewById(R.id.txtHintName);
        TextView txt2 = v.findViewById(R.id.txtHintDescription);

        img.setImageResource(images.get(position));
        txt.setText(titles.get(position));
        txt2.setText(descriptions.get(position));

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
