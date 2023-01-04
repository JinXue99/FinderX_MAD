package com.example.finderx_mad;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SlideViewPageAdapter extends PagerAdapter {

    Context ctx;

    public SlideViewPageAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_screen, container, false);

        ImageView ivLogo1 = view.findViewById(R.id.ivLogo1);
        ImageView ivInd1 = view.findViewById(R.id.ivInd1);
        ImageView ivInd2 = view.findViewById(R.id.ivInd2);
        ImageView ivInd3 = view.findViewById(R.id.ivInd3);
        ImageView ivBack = view.findViewById(R.id.ivBack);
        ImageView ivNext = view.findViewById(R.id.ivNext);

        TextView tvFinderX = view.findViewById(R.id.tvFinderX);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvDesc = view.findViewById(R.id.tvDesc);
        Button btnGetStarted = view.findViewById(R.id.btnGetStarted);
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);
            }
        });
        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.example.finderx_mad.SlideActivity.viewPager.setCurrentItem(position+1);
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.example.finderx_mad.SlideActivity.viewPager.setCurrentItem(position-1);
            }
        });

        switch (position){
            case 0:
                ivLogo1.setImageResource(R.drawable.ic_logo1);
                ivInd1.setImageResource(R.drawable.selected);
                ivInd2.setImageResource(R.drawable.unselected);
                ivInd3.setImageResource(R.drawable.unselected);

                tvFinderX.setVisibility(View.GONE);
                tvTitle.setText("Find Group Quickly");
                tvDesc.setText("Team up with desired members and complete assignments as soon as possible");
                ivBack.setVisibility(View.GONE);
                ivNext.setVisibility(View.VISIBLE);
                btnGetStarted.setVisibility(View.GONE);
                break;
            case 1:
                ivLogo1.setImageResource(R.drawable.ic_logo2);
                ivInd1.setImageResource(R.drawable.unselected);
                ivInd2.setImageResource(R.drawable.selected);
                ivInd3.setImageResource(R.drawable.unselected);

                tvFinderX.setVisibility(View.GONE);
                tvTitle.setText("Monitor Group Easily");
                tvDesc.setText("Have the overall view of group distribution among students");
                ivBack.setVisibility(View.VISIBLE);
                ivNext.setVisibility(View.VISIBLE);
                btnGetStarted.setVisibility(View.GONE);
                break;
            case 2:
                ivLogo1.setImageResource(R.drawable.finderlogo);
                ivInd1.setImageResource(R.drawable.unselected);
                ivInd2.setImageResource(R.drawable.unselected);
                ivInd3.setImageResource(R.drawable.selected);

                tvFinderX.setVisibility(View.GONE);
                tvTitle.setText("No of us is smart as all of us");
                tvDesc.setText("All with FinderX");
                ivBack.setVisibility(View.VISIBLE);
                ivNext.setVisibility(View.GONE);
                btnGetStarted.setVisibility(View.VISIBLE);
                break;

        }



        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
