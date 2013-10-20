package com.tea.activity.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import com.tea.launcher.util.Constants;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class GuideViewDemoActivity extends Activity {
	
    private ViewPager viewPager;  
    private ArrayList<View> pageViews;  
    private ViewGroup main, group;  
    private ImageView imageView;  
    private ImageView[] imageViews; 
    private ImageButton button = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        LayoutInflater inflater = getLayoutInflater();  

        pageViews = new ArrayList<View>();  
        pageViews.add(inflater.inflate(R.layout.guide01, null));  
        pageViews.add(inflater.inflate(R.layout.guide02, null));  
        //pageViews.add(inflater.inflate(R.layout.guide03, null));   
  
        imageViews = new ImageView[pageViews.size()];  
        main = (ViewGroup)inflater.inflate(R.layout.guide, null);  
        
        // group是R.layou.main中的负责包裹小圆点的LinearLayout.  
        group = (ViewGroup)main.findViewById(R.id.viewGroup);  
  
        viewPager = (ViewPager)main.findViewById(R.id.guidePages);  
  
        for (int i = 0; i < pageViews.size(); i++) {  
            imageView = new ImageView(GuideViewDemoActivity.this);  
            imageView.setLayoutParams(new LayoutParams(20,20));  
            imageView.setPadding(20, 0, 20, 0);  
            imageViews[i] = imageView;  
            if (i == 0) {  
                //默认选中第一张图�?                imageViews[i].setBackgroundResource(R.drawable.page_indicator_focused);  
            } else {  
                imageViews[i].setBackgroundResource(R.drawable.guide_indicator);  
            }  
            group.addView(imageViews[i]);  
        }  
  
        setContentView(main);  
  
        viewPager.setAdapter(new GuidePageAdapter());  
        viewPager.setOnPageChangeListener(new GuidePageChangeListener());  
        
		button = (ImageButton) main.findViewById(R.id.imageButton1);
		button.setVisibility(View.INVISIBLE);
		button.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//searchDialog();
				Intent intent = new Intent();
				intent.setClass(GuideViewDemoActivity.this, AndroidDashboardDesignActivity.class);
				copyDatabaseToSDCard();
				startActivity(intent);				
				GuideViewDemoActivity.this.finish();
				//searchDialog.dismiss();
			}
		});
		pageViews.get(1).setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				button.setVisibility(View.VISIBLE);
				//Toast.makeText(GuideViewDemoActivity.this,"�������Item  ",Toast.LENGTH_SHORT).show();
				
			}
		});
    }
    
    /** 指引页面Adapter */
    class GuidePageAdapter extends PagerAdapter {  
    	  
        @Override  
        public int getCount() {  
            return pageViews.size();  
        }  
  
        @Override  
        public boolean isViewFromObject(View arg0, Object arg1) {  
            return arg0 == arg1;  
        }  
  
        @Override  
        public int getItemPosition(Object object) {  
            // TODO Auto-generated method stub  
            return super.getItemPosition(object);  
        }  
  
        @Override  
        public void destroyItem(View arg0, int arg1, Object arg2) {  
            // TODO Auto-generated method stub  
            ((ViewPager) arg0).removeView(pageViews.get(arg1));  
        }  
  
        @Override  
        public Object instantiateItem(View arg0, int arg1) {  
            // TODO Auto-generated method stub  
            ((ViewPager) arg0).addView(pageViews.get(arg1));  
            return pageViews.get(arg1);  
        }  
  
        @Override  
        public void restoreState(Parcelable arg0, ClassLoader arg1) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public Parcelable saveState() {  
            // TODO Auto-generated method stub  
            return null;  
        }  
  
        @Override  
        public void startUpdate(View arg0) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public void finishUpdate(View arg0) {  
            // TODO Auto-generated method stub  
  
        }  
    } 
    
    /** 指引页面改监听器 */
    class GuidePageChangeListener implements OnPageChangeListener {  
  
    	private int position;
    	public GuidePageChangeListener(){
    		super();
    		position = 0;
    	}
        @Override  
        public void onPageScrollStateChanged(int arg0) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public void onPageScrolled(int arg0, float arg1, int arg2) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public void onPageSelected(int arg0) {  
        	this.position = arg0;
        	button.setVisibility(View.INVISIBLE);
            for (int i = 0; i < imageViews.length; i++) {  
                imageViews[arg0]  
                        .setBackgroundResource(R.drawable.guide_indicator_focused);  
                if (arg0 != i) {  
                    imageViews[i]  
                            .setBackgroundResource(R.drawable.guide_indicator);  
                }  
            }
  
        }  
        
        public int getPosition(){
        	return this.position;
        }
  
    }  
	private void copyDatabaseToSDCard()
	{
		try
		{
			// ���dictionary.db�ļ��ľ���·��
			String database_path = this.getFilePath();
			Log.i("pahpaht2233333", database_path);
			String databaseFilename =  database_path + "/" + Constants.DATABASE_NAME;
			Log.i("paadfasd4444", databaseFilename);
			File dir = new File( database_path);
			// ���/sdcard/dictionaryĿ¼�д��ڣ��������Ŀ¼
			if (!dir.exists())
				dir.mkdir();
			// �����/sdcard/dictionaryĿ¼�в�����
			// dictionary.db�ļ������res\rawĿ¼�и�������ļ���
			// SD����Ŀ¼��/sdcard/dictionary��
			if (!(new File(databaseFilename)).exists())
			{
				// ��÷�װdictionary.db�ļ���InputStream����
				InputStream is = getResources().openRawResource(
						R.raw.veeko_tea);
				FileOutputStream fos = new FileOutputStream(databaseFilename);
				byte[] buffer = new byte[8192];
				int count = 0;
				// ��ʼ����dictionary.db�ļ�
				while ((count = is.read(buffer)) > 0)
				{
					fos.write(buffer, 0, count);
				}

				fos.close();
				is.close();
			}
		}
		catch (Exception e)
		{
			
		}
	}
	
	public String getFilePath(){
		String strDBPath; // ͼƬ�����·��
		String strPathHead = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			strPathHead = Environment.getExternalStorageDirectory().toString();
		} else
			strPathHead = Constants.PHONE_PATH;
		strDBPath = strPathHead + Constants.DATABASE_PATH;
		return strDBPath;
	}
}