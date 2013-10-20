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

	
	//��resources�е�raw �ļ����л�ȡ�ļ�����ȡ����
	public String getFromRaw(){
	    String result = "";
		    try {
				InputStream in = getResources().openRawResource(R.raw.aboutus1_1);
				//��ȡ�ļ����ֽ���
				int lenght = in.available();
				//����byte����
				byte[]  buffer = new byte[lenght];
				//���ļ��е����ݶ���byte������
				in.read(buffer);
				result = EncodingUtils.getString(buffer, Constants.ENCODING);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
	}
}
