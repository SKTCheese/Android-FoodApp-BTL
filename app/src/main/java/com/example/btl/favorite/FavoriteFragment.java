package com.example.btl.favorite;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.btl.R;
import com.example.btl.fragments.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

public class FavoriteFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    FragmentAdapter fragmentAdapter;
    private FragmentActivity myContext;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.favorite_fragment, container, false);

        tabLayout = root.findViewById(R.id.tab_layout);
        viewPager2 = root.findViewById(R.id.view_pager2);

        FragmentManager fm = getActivity().getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapter(fm, getLifecycle());

        viewPager2.setAdapter(fragmentAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("FEATURED"));
        tabLayout.addTab(tabLayout.newTab().setText("POPULAR"));
        tabLayout.addTab(tabLayout.newTab().setText("NEW"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }

        });

        return root;
    }
}