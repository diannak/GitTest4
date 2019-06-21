package com.dog.android.happy_dog;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PageAdapter( FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem( int position){

        switch (position){
            case  0:
                return  new Fragment0(); // 메인 메뉴
            case  1:
                return  new Fragment1(); // 유기견 공고
            case  2:
                return  new Fragment2(); // 신고/제보
            case  3:
                return  new Fragment3(); // 봉사
            case  4:
                return  new Fragment4(); // 내 정보
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
