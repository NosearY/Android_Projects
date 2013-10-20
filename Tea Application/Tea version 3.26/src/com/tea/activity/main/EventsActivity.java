package com.tea.activity.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EventsActivity extends Activity {
	 /** Called when the activity is first created. */
	public TextView tvTitle;
	public Button btnBack;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.events_layout);
        
        /*
         * 
         * for title
         */
        tvTitle = (TextView) findViewById(R.id.TitleText);
		tvTitle.setText("-≤Ë—ßÃ√");

		btnBack = (Button) findViewById(R.id.TitleBackBtn);
		btnBack.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.i("MEDIA","In");
				KeyEvent newEvent = new KeyEvent(KeyEvent.ACTION_DOWN,
						KeyEvent.KEYCODE_BACK);
				onKeyDown(KeyEvent.KEYCODE_BACK, newEvent);
				EventsActivity.this.finish();
			}
		});
    }
}
