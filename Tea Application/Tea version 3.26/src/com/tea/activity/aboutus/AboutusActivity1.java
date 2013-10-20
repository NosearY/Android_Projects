package com.tea.activity.aboutus;

import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

import com.tea.activity.main.R;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.TextView;

import com.tea.launcher.util.*;

public class AboutusActivity1 extends Activity {
	private TextView tv1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aboutus1);
		tv1 = (TextView)findViewById(R.id.textView1);
		tv1.setTextColor(Color.BLACK);
		tv1.setTextSize(17.0f);
		tv1.setText(getFromRaw());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.aboutus_activity1, menu);
		return true;
	}

	
	//从resources中的raw 文件夹中获取文件并读取数据
	public String getFromRaw(){
	    String result = "";
		    try {
				InputStream in = getResources().openRawResource(R.raw.aboutus1_1);
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
