package com.example.voice_recorder.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.voice_recorder.Fragments.RecorderFragment;
import com.example.voice_recorder.Fragments.RecordingFragment;


public class MyAdapter extends FragmentStateAdapter {
    Context mycontext;
    int totaltabs;

    public MyAdapter(Context context, FragmentActivity fm,int totaltabs) {
        super(fm);
        mycontext=context;
        this.totaltabs=totaltabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch (position)
       {
           case 0:
               RecorderFragment recorderFragment=new RecorderFragment();
               return  recorderFragment;
           case 1:
               RecordingFragment recordingFragment=new RecordingFragment();
               return  recordingFragment;

           default:
               return null;
       }
    }

    @Override
    public int getItemCount() {
        return totaltabs;
    }
}
