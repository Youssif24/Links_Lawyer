package com.saad.youssif.arabiclawyer.Adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.saad.youssif.arabiclawyer.Fragments.DelegationFragment;
import com.saad.youssif.arabiclawyer.Fragments.IssuesFragment;
import com.saad.youssif.arabiclawyer.Fragments.SittingFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {



    private String title[] = {"القضايا","الجلسات","التوكيلات"};
    final int PAGE_COUNT = 3;

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }


    @Override
    public Fragment getItem(int i) {
        if(i==0)
        {
            Fragment fragment1=new IssuesFragment();
            return fragment1;
        }
        else if (i==1)
        {
            Fragment fragment2=new SittingFragment();
            return fragment2;
        }
        else
        {
            Fragment fragment3=new DelegationFragment();
            return fragment3;
        }
    }



    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return title[position];
    }
}