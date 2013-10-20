package com.tea.activity.main;

import java.io.InputStream;

import org.apache.http.util.EncodingUtils;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.tea.activity.culture.TabCultureWidget;
import com.tea.activity.main.R;
import com.tea.launcher.util.*;

public class ContentActivity extends Activity {

	private TextView tv1;
	private int fileName;
	private String name;
	public TextView tvTitle;
	public Button btnBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Full Window
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_content);
        /*
         * 
         * for title
         */
        tvTitle = (TextView) findViewById(R.id.TitleText);
		

		btnBack = (Button) findViewById(R.id.TitleBackBtn);
		btnBack.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.i("MEDIA","In");
				KeyEvent newEvent = new KeyEvent(KeyEvent.ACTION_DOWN,
						KeyEvent.KEYCODE_BACK);
				onKeyDown(KeyEvent.KEYCODE_BACK, newEvent);
				ContentActivity.this.finish();
			}
		});
		Intent myLocalIntent = getIntent();
		Bundle myBundle = myLocalIntent.getExtras();
		fileName = myBundle.getInt("id");
		name = myBundle.getString("name");
		tvTitle.setText("-"+name);
		Log.i("TestingID here", "fileID:"+fileName);
		tv1 = (TextView)findViewById(R.id.textView1);
		tv1.setTextColor(Color.BLACK);
		tv1.setTextSize(18.0f);
		tv1.setText(getFromRaw());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.aboutus_activity2, menu);
		return true;
	}
	
	//从resources中的raw 文件夹中获取文件并读取数据
	public String getFromRaw(){
	    String result = "";
		    try {
				InputStream in = getResources().openRawResource(fileName);
				//获取文件的字节数
				int lenght = in.available();
				//创建byte数组
				byte[]  buffer = new byte[lenght];
				//将文件中的数据读到byte数组中
				in.read(buffer);
				result = EncodingUtils.getString(buffer, Constants.ENCODING);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
	}
}
