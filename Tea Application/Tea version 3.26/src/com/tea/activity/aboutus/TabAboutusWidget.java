package com.tea.activity.aboutus;


import com.tea.activity.main.R;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class TabAboutusWidget extends TabActivity {
	
	public TextView tvTitle;
	public TextView tv1;
	public TextView tv2;
	public TextView tv3;
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
		tvTitle.setText("-关于我们");
		
		btnBack = (Button) findViewById(R.id.TitleBackBtn);
		btnBack.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.i("MEDIA","In");
				KeyEvent newEvent = new KeyEvent(KeyEvent.ACTION_DOWN,
						KeyEvent.KEYCODE_BACK);
				onKeyDown(KeyEvent.KEYCODE_BACK, newEvent);
				TabAboutusWidget.this.finish();
			}
		});
        
		Resources res = getResources(); 
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, AboutusActivity1.class);

        //Chinese Version  
    	spec = tabHost.newTabSpec("Tab1").setIndicator( "走进老曼峨",null).setContent(intent);	
    	tabHost.addTab(spec);

    	// Do the same for the other tabs
    	intent = new Intent().setClass(this, AboutusActivity2.class);
    	spec = tabHost.newTabSpec("Tab2").setIndicator("企业文化", null).setContent(intent);
    	tabHost.addTab(spec);
    	intent = new Intent().setClass(this, AboutusActivity3.class);
    	spec = tabHost.newTabSpec("Tab3").setIndicator("茶园基地", null).setContent(intent);
    	tabHost.addTab(spec);

    	tabHost.setCurrentTab(0);
    
    	/***
    	 * For the appearance of tab widget
    	 */
    	initTabsAppearance(tabHost);
    	//tabHost.getTabWidget().setRightStripDrawable(R.drawable.actionbar_long);
    	
    	tabHost.setOnTabChangedListener(new OnTabChangeListener(){
    		@Override
    		public void onTabChanged(String tabId) {
    			TabHost tabHost = getTabHost();
    		
    		    if("Tab1".equals(tabId)) {
    		    	tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.actionbar_tab_bg);
    		    	tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.WHITE);
    		    	tabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.WHITE);
    		    }
    		    if("Tab2".equals(tabId)) {
    		    	tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.actionbar_tab_bg);
    		    	tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.WHITE);
    		    	tabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.WHITE);
    		    }
    		    if("Tab3".equals(tabId)) {
    		    	tabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.actionbar_tab_bg);
    		    	tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.WHITE);
    		    	tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.WHITE);
    		    }
    		    
    		}});
    }
	
	private void initTabsAppearance(TabHost tabHost) {
		// Change background
	    for(int i=0; i < tabHost.getTabWidget().getChildCount(); i++)
	    	tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
	    tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.actionbar_tab_bg);
	    //tabHost.getTabWidget().setRightStripDrawable(R.drawable.actionbar_long);
	    //tabHost.getTabWidget().setLeftStripDrawable(R.drawable.actionbar_long);
	    getTabHost().getTabWidget().setStripEnabled(false);
	}
}