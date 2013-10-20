/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.tea.activity.sale;

import com.tea.activity.main.R;
import com.tea.launcher.photoview.PhotoViewAttacher;
import com.tea.launcher.photoview.PhotoViewAttacher.OnMatrixChangedListener;
import com.tea.launcher.photoview.PhotoViewAttacher.OnPhotoTapListener;

import android.app.Activity;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

public class SaleActivity1 extends Activity {

	static final String PHOTO_TAP_TOAST_STRING = "请连续点击两次图片进行缩放！";

	private ImageView mImageView;
	//private TextView mCurrMatrixTv;

	private PhotoViewAttacher mAttacher;

	private Toast mCurrentToast;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_brand2);

		mImageView = (ImageView) findViewById(R.id.iv_photo);
		//mCurrMatrixTv = (TextView) findViewById(R.id.tv_current_matrix);
//
//		Drawable bitmap = getResources().getDrawable(R.drawable.wallpaper);
//		mImageView.setImageDrawable(bitmap);
		mImageView.setImageResource(R.drawable.sale1);
		// The MAGIC happens here!
		mAttacher = new PhotoViewAttacher(mImageView);
	
		// Lets attach some listeners, not required though!
		//mAttacher.setOnMatrixChangeListener((OnMatrixChangedListener) new MatrixChangeListener());
		mAttacher.setOnPhotoTapListener((OnPhotoTapListener) new PhotoTapListener());
	}

	private class PhotoTapListener implements OnPhotoTapListener {

		@Override
		public void onPhotoTap(View view, float x, float y) {

			if (null != mCurrentToast) {
				mCurrentToast.cancel();
			}

			mCurrentToast = Toast.makeText(SaleActivity1.this,
					String.format(PHOTO_TAP_TOAST_STRING, null, null), Toast.LENGTH_SHORT);
			mCurrentToast.show();
		}
	}

	private class MatrixChangeListener implements OnMatrixChangedListener {

		@Override
		public void onMatrixChanged(RectF rect) {
			//mCurrMatrixTv.setText(rect.toString());
		}
	}

}
