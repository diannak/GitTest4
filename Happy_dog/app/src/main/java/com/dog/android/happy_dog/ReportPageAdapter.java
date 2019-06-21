package com.dog.android.happy_dog;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ReportPageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ReportPageAdapter( FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem( int position){

        switch (position){
            case  0:
                return  new ReportFragment0(); // 세부내용
            case  1:
                return  new ReportFragment1(); // 위치확인
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
