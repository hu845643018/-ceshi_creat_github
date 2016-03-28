package com.example.lunbodemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;


public class MainActivity extends Activity {
	//����һ��ͼ������ ���СԲͼ��
	private ImageView [] imageViews;
	
	private ViewPager viewPager;
	private AtomicInteger what=new AtomicInteger();
	private boolean isContinue=true;
	
	//-----------------------
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initViewPager();
	}

	//��ʼ�����
		private void initViewPager() {
			viewPager=(ViewPager)this.findViewById(R.id.viewpager);
			ViewGroup viewgroup=(ViewGroup)this.findViewById(R.id.viewgroup);
			
			//����ͼƬ�ϼ� ��ı���ͼ
			List<View> views=new ArrayList<View>();
			//���� ImageView ����
			ImageView image1=new ImageView(this);
			image1.setBackgroundResource(R.drawable.huodong1);
			views.add(image1);
			
			ImageView image2=new ImageView(this);
			image2.setBackgroundResource(R.drawable.huodong2);
			views.add(image2);
			
			ImageView image3=new ImageView(this);
			image3.setBackgroundResource(R.drawable.huodong3);
			views.add(image3);
			
			//����Сͼ�񼯺�
			imageViews=new ImageView[views.size()];
			for(int i=0;i<imageViews.length;i++){
				imageViews[i]=new ImageView(this);
				if (i==0) {
					imageViews[i].setBackgroundResource(R.drawable.lunbo1);
				} else {
					imageViews[i].setBackgroundResource(R.drawable.lunbo2);
				}
				viewgroup.addView(imageViews[i]);
			}
			viewPager.setAdapter(new MyAdapter(views));
			viewPager.setOnPageChangeListener(new MyOnPageChangedListener());
			viewPager.setOnTouchListener(new View.OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
					case MotionEvent.ACTION_MOVE:
						isContinue=true;
						break;
					case MotionEvent.ACTION_UP:
						isContinue=true;
						break;
					default:
						isContinue=true;
						break;
					}
					return false;
				}
			});
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(true){
						if(isContinue){
							handler.sendEmptyMessage(what.get());
							viewOption();
						}
					}
				}


			}).start();
		}
		/**
		 * ����ͼƬ��λ��
		 */
		private void viewOption() {
			what.incrementAndGet();
			if(what.get()>imageViews.length);
				what.addAndGet(-imageViews.length);
		
		try{
			Thread.sleep(3000);
		}catch(InterruptedException e){
			
		}
	}
	//����һ����Ϣ����
	private Handler handler=new Handler(){
		public void handleMessage(Message msg){
			viewPager.setCurrentItem(msg.what);
			super.handleMessage(msg);
		};
	};
		/**
		 * Сͼ�����ű����ƶ�
		 */
		class MyOnPageChangedListener implements OnPageChangeListener{

			@Override
			public void onPageScrollStateChanged(int arg0) {
				
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				for(int i=0;i<imageViews.length;i++){
					imageViews[i].setBackgroundResource(R.drawable.lunbo1);
				if(arg0!=i){
					imageViews[i].setBackgroundResource(R.drawable.lunbo2);
				}
				}
			}

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
		}
}
