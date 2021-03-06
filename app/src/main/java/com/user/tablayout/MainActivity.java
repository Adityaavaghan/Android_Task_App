package com.user.tablayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;



import android.os.Bundle;
import android.view.WindowManager;


import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    TabLayout tabLayout;
    ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LOCAL_FOCUS_MODE,WindowManager.LayoutParams.FLAG_LOCAL_FOCUS_MODE);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Feed");
        arrayList.add("Saved");

        prepareViewPage(viewPager,arrayList);

        tabLayout.setupWithViewPager(viewPager);

        //getSupportFragmentManager().beginTransaction().replace(R.id.maincontainer,new MainFragment()).commit();
    }

    private void prepareViewPage(ViewPager viewPager, ArrayList<String> arrayList)
    {
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        MainFragment fragment = new MainFragment();
        for (int i = 0;i<arrayList.size();i++)
        {
            Bundle bundle = new Bundle();
            bundle.putString("title",arrayList.get(i));
            fragment.setArguments(bundle);
            adapter.addFragment(fragment,arrayList.get(i));
            fragment = new MainFragment();

        }
        viewPager.setAdapter(adapter);


    }

    private class MainAdapter extends FragmentPagerAdapter
    {
        ArrayList<String>arrayList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();

        public void addFragment(Fragment fragment,String title)
        {
            arrayList.add(title);
            fragmentList.add(fragment);

        }

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position)
        {

            return fragmentList.get(position);

        }

        @Override
        public int getCount()
        {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position)
        {
            return arrayList.get(position);
        }
    }
}