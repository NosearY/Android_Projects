package com.tea.activity.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.tea.activity.aboutus.TabAboutusWidget;
import com.tea.activity.brand.TabBrandWidget;
import com.tea.activity.culture.TabCultureWidget;
import com.tea.activity.news.TabNewsWidget;
import com.tea.activity.product.TabProductWidget;
import com.tea.activity.sale.TabSaleWidget;
import com.tea.launcher.util.Constants;

public class AndroidDashboardDesignActivity extends Activity {
	public TextView tvTitle;
	public Button btnBack;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//setTheme(R.style.CustomTheme);
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.dashboard_layout);
        copyDatabaseToSDCard();
        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.);
        /**
         * 
         * for title bar
         */
        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.actionbar_layout);
		tvTitle = (TextView) findViewById(R.id.TitleText);

		btnBack = (Button) findViewById(R.id.TitleBackBtn);
		btnBack.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				KeyEvent newEvent = new KeyEvent(KeyEvent.ACTION_DOWN,
						KeyEvent.KEYCODE_BACK);
				onKeyDown(KeyEvent.KEYCODE_BACK, newEvent);
			}
		});
		
        /**
         * Creating all buttons instances
         * */
        // Dashboard News feed button
        Button btn_newsfeed = (Button) findViewById(R.id.btn_news_feed);
        
        // Dashboard Friends button
        Button btn_friends = (Button) findViewById(R.id.btn_friends);
        
        // Dashboard Messages button
        Button btn_messages = (Button) findViewById(R.id.btn_messages);
        
        // Dashboard Places button
        Button btn_places = (Button) findViewById(R.id.btn_places);
        
        // Dashboard Events button
        Button btn_events = (Button) findViewById(R.id.btn_events);
        
        // Dashboard Photos button
        Button btn_photos = (Button) findViewById(R.id.btn_photos);
        
        /**
         * Handling all button click events
         * */
        
        // Listening to News Feed button click
        btn_newsfeed.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Intent i = new Intent(getApplicationContext(), TabAboutusWidget.class);
				startActivity(i);
			}
		});
        
       // Listening Friends button click
        btn_friends.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Intent i = new Intent(getApplicationContext(), TabBrandWidget.class);
				startActivity(i);
			}
		});
        
        // Listening Messages button click
        btn_messages.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Intent i = new Intent(getApplicationContext(), TabNewsWidget.class);
				startActivity(i);
			}
		});
        
        // Listening to Places button click
        btn_places.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Intent i = new Intent(getApplicationContext(), TabProductWidget.class);
				startActivity(i);
			}
		});
        
        // Listening to Events button click
        btn_events.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Intent i = new Intent(getApplicationContext(), TabCultureWidget.class);
				startActivity(i);
			}
		});
        
        // Listening to Photos button click
        btn_photos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Intent i = new Intent(getApplicationContext(), TabSaleWidget.class);
				startActivity(i);
			}
		});
    }
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			// ���µ������BACK��ͬʱû���ظ�
			askForOut();

			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	private void askForOut() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("ȷ���˳�").setMessage("ȷ���˳���").setPositiveButton("ȡ��",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				}).setNegativeButton("ȷ��",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				}).setCancelable(false).show();
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