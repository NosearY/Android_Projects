package com.tea.activity.product;

import com.tea.activity.main.R;
import com.tea.activity.main.R.layout;
import com.tea.activity.main.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ProductActivity3 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.product_activity3, menu);
		return true;
	}

}
