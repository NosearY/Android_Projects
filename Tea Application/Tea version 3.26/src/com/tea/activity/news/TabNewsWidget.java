package com.tea.activity.news;


import com.tea.activity.main.R;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;

@SuppressWarnings("deprecation")
public class TabNewsWidget extends TabActivity {
	
	public TextView tvTitle;
	public Button btnBack;
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		//Full Window
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        
        /*
         * 
         * for title
         */
        tvTitle = (TextView) findViewById(R.id.TitleText);
		tvTitle.setText("-新闻活动");

		btnBack = (Button) findViewById(R.id.TitleBackBtn);
		btnBack.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.i("MEDIA","In");
				KeyEvent newEvent = new KeyEvent(KeyEvent.ACTION_DOWN,
						KeyEvent.KEYCODE_BACK);
				onKeyDown(KeyEvent.KEYCODE_BACK, newEvent);
				TabNewsWidget.this.finish();
			}
		});
        
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, NewsActivity1.class);

        //Chinese Version
    	spec = tabHost.newTabSpec("Tab1").setIndicator("行业新闻", null).setContent(intent);
    	tabHost.addTab(spec);

    	// Do the same for the other tabs
    	intent = new Intent().setClass(this, NewsActivity2.class);
    	spec = tabHost.newTabSpec("Tab2").setIndicator("企业动态", null).setContent(intent);
    	tabHost.addTab(spec);
 

    	tabHost.setCurrentTab(0);
    	/***
    	 * For appearance of tab widget
    	 */
    	initTabsAppearance(tabHost);
    	tabHost.setOnTabChangedListener(new OnTabChangeListener(){
    		@Override
    		public void onTabChanged(String tabId) {
    			TabHost tabHost = getTabHost();
    		    if("Tab1".equals(tabId)) {
    		    	tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.actionbar_tab_bg);
    		    	tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.WHITE);
    		    }
    		    if("Tab2".equals(tabId)) {
    		    	tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.actionbar_tab_bg);
    		    	tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.WHITE);
    		    }  
    		}});
    }
	
	private void initTabsAppearance(TabHost tabHost) {
		// Change background
	    for(int i=0; i < tabHost.getTabWidget().getChildCount(); i++)
	    	tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
	    tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.actionbar_tab_bg);
	    getTabHost().getTabWidget().setStripEnabled(false);
	}
	
	
}