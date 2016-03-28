package com.example.lunbodemo;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class MyAdapter extends PagerAdapter{

	private List<View> views;
	private List<String> titles;
	
	public MyAdapter(List<View> views){
		this.views=views;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return views.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	public CharSequence getPageTitle(int position){
		return titles.get(position);
	}
	public void destroyItem(ViewGroup container,int position,Object object){
		((ViewPager)container).removeView(views.get(position));
	}
	public Object instantiateItem(ViewGroup container,int position){
		((ViewPager)container).addView(views.get(position));
		return views.get(position);
	}
}
