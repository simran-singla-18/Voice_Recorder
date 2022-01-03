package com.example.voice_recorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TableLayout;

import com.example.voice_recorder.Adapters.MyAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout=(TabLayout)findViewById(R.id.tablayout);
        viewPager=(ViewPager2)findViewById(R.id.viewpager);

        tabLayout.addTab(tabLayout.newTab().setText("Recorder"));
        tabLayout.addTab(tabLayout.newTab().setText("Recording"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager.setAdapter(new MyAdapter(this, this,tabLayout.getTabCount()));


        new TabLayoutMediator(tabLayout,viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText("Tab"+(position+1));
               // viewPager.setCurrentItem(tab.position,true);
            }
        }).attach();


    }
}