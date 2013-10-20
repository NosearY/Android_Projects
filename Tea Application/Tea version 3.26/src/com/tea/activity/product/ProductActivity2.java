package com.tea.activity.product;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.tea.activity.main.R;
import com.tea.launcher.db.DBConnection;
import com.tea.launcher.scroll.*;
import com.tea.launcher.scroll.TryRefreshableView.RefreshListener;
import com.tea.launcher.util.BrowseSDCardImage;
import com.tea.launcher.util.Constants;
import com.tea.launcher.util.FileCache;
import com.tea.launcher.util.TeaProducts;
import com.tea.launcher.util.WebContentOfProduct;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
public class ProductActivity2 extends Activity
{
	/** Called when the activity is first created. */
	private LinearLayout layout01;
	private LinearLayout layout02;
	private LinearLayout layout03;
	private List<View> layouts;
	private String[] imagePathStr = {};//such as http://www.XXX.com, just list of URL
	private String[] teaNameStr = {};//list of tea name
	private int windowWidth = -1;
	private int mark = 0;
	private int whichlayout = 0;
	private ProgressDialog searchDialog = null;
	private Context mContext = null;
	
	private ScrollView sv;
	private TryRefreshableView rv;
	//private DisplayImageOptions options;

	private TeaProducts products = null;
	private int numberOfLoading = 0;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		manageException();
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_product1);
		
		mContext = ProductActivity2.this;
		layouts = new ArrayList<View>();
		
		layout01 = (LinearLayout) findViewById(R.id.layout01);
		layout02 = (LinearLayout) findViewById(R.id.layout02);
		layout03 = (LinearLayout) findViewById(R.id.layout03);
		
		Display display = getWindowManager().getDefaultDisplay();
		windowWidth = display.getWidth();
		
		//searchDialog();
		setImagePathStr();//initial the website URL into the array of imagePathStr
		
		
		//Log.i("Path str length", imagePathStr.length+"   "+imagePathStr[0]+"   "+teaNameStr[0]);
		sv = (ScrollView) findViewById(R.id.trymySv);
		rv = (TryRefreshableView) findViewById(R.id.trymyRV);
		rv.mfooterView = (View) findViewById(R.id.tryrefresh_footer);
		rv.sv = sv;
		
		//隐藏mfooterView
		rv.mfooterViewText = (TextView) findViewById(R.id.tryrefresh_footer_text);
		
		//监听是否加载刷新
		rv.setRefreshListener(new RefreshListener() {

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				if (rv.mRefreshState == 4) {
					Toast.makeText(mContext,"没有新内容！",Toast.LENGTH_SHORT).show();
					new GetHeaderDataTask().execute();
				} else if (rv.mfooterRefreshState == 4) {
					//new GetFooterDataTask().execute();
					
					if(products == null){
						Toast.makeText(mContext,"请确保网络连接的情况下再试！",Toast.LENGTH_SHORT).show();
					}else{
						numberOfLoading ++;
						//new GetFooterDataTask().execute();

						//BrowseSDCardImage browsing = new BrowseSDCardImage();
						
						//imageNamesOfSDCard = browsing.getSDCard();
						for(; mark < (numberOfLoading+1)*Constants.MAXIMUM_COUNT; mark++){
							if(mark == imagePathStr.length){
								new GetFooterDataTask().execute();
								Toast.makeText(mContext,"已加载完毕！",Toast.LENGTH_SHORT).show();
								break;
							}
							new GetFooterDataTask2(mark, whichlayout).execute(imagePathStr);
							whichlayout ++;
							if(whichlayout == 3){
								whichlayout = 0;
							}
						}
						
					}

				}

			}
		});
	}
	
	/*
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View fragmentView = inflater.inflate(R.layout.tab1_fragment_layout, container, false);
		return fragmentView;
	}
	*/
	public void manageException() {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork() // or
																		// .detectAll()
																		// for
																		// all
																		// detectable
																		// problems
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
				.build());
	}
	
	
	public void setImagePathStr(){

		
		products = new TeaProducts();
		new WebContentOfProduct();
		DBConnection dbConnection = new DBConnection("all_teas");
		products = dbConnection.useCursor(imagePathStr, teaNameStr);
		if(products == null){
			Toast.makeText(mContext,"请确保网络连接的情况下再试！",Toast.LENGTH_SHORT).show();
		}else{
			//products = webcontent.load(imagePathStr, teaNameStr);
			//webcontent.getNextURL();
			imagePathStr = products.getImagePathStr();
			teaNameStr = products.getTeaNameStr();

			//BrowseSDCardImage browsing = new BrowseSDCardImage();
			
			//imageNamesOfSDCard = browsing.getSDCard();
			
			for(; mark < Constants.MAXIMUM_COUNT; mark++){
				if(mark == imagePathStr.length){
					new GetFooterDataTask().execute();
					Toast.makeText(mContext,"已加载完毕！",Toast.LENGTH_SHORT).show();
					break;
				}
				new LoadImageViews(mark, whichlayout).execute(imagePathStr);
				whichlayout ++;
				if(whichlayout == 3){
					whichlayout = 0;
				}
			}
		}


	}
	
	class LoadImageViews extends AsyncTask<String[], Void, Drawable>
	{
		private int imageIndex;
		private int layoutIndex;
		
		public LoadImageViews(){
			
		}
		
		public LoadImageViews(int imageIndex, int layoutIndex){
			this.imageIndex = imageIndex;
			this.layoutIndex = layoutIndex;
			Log.i("gggfuck", "adsfas");
			initial(this.layoutIndex, this.imageIndex);
			Log.i("hhhfuck2", "adsfas");
		}

		@Override
		protected Drawable doInBackground(String[]... params)
		{
			Drawable drawable = null;
			BrowseSDCardImage checkImage = new BrowseSDCardImage(params[0][this.imageIndex]);
			String imgURL = Constants.WEBURL_INDEX + params[0][this.imageIndex];
			//if(imageNamesOfSDCard.contains(params[0][this.imageIndex])){
			Log.i("nonononononono", imgURL);
			if(checkImage.isExistImage()){
				Log.i("nonononononono", "tea");
				Bitmap bitmap = checkImage.getBitMap();
				@SuppressWarnings("deprecation")
				BitmapDrawable bd = new BitmapDrawable(bitmap);
				drawable = bd;
			}else{
				try
				{
					Log.i("nonononononono2222", "tea");
					URL url = new URL(imgURL);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setDoInput(true);
					conn.connect();
					InputStream inputStream = conn.getInputStream();
					Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
					
					int width = bitmap.getWidth();
					int height = bitmap.getHeight();
					float scaleWidth = (float) windowWidth/2.5f/width;
		        	Matrix matrix = new Matrix();
		        	matrix.postScale(scaleWidth, scaleWidth);
		        	
		        	bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
		        	FileCache.getInstance().savaBmpData(imagePathStr[imageIndex], bitmap);
		        	
					@SuppressWarnings("deprecation")
					BitmapDrawable bd = new BitmapDrawable(bitmap);
					drawable = bd;
				} catch (MalformedURLException e)
				{
					e.printStackTrace();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}

			return drawable;
		}

		
		
		@Override
		protected void onPostExecute(Drawable result)
		{
			super.onPostExecute(result);
			//searchDialog.dismiss();
			if (result != null){
				updateBitMapToImage(result, imageIndex);
			}
		}

		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
	}

	// 添加imageview 到layout
	//@SuppressWarnings("deprecation")
	public void initial(int layoutIndex, final int imageIndex)
	{
		Log.i("asdfadsfsdagadsgasdgsag1", "ateat");
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
		Log.i("asdfadsfsdagadsgasdgsag000", "ateat");
		View layout = inflater.inflate(R.layout.infos_list,null);
		Log.i("asdfadsfsdagadsgasdgsag333", "ateat");
		TextView text = (TextView) layout.findViewById(R.id.news_title);
		Log.i("asdfadsfsdagadsgasdgsag444", "ateat");
		text.setText(teaNameStr[imageIndex]);
		layouts.add(layout);
		Log.i("asdfadsfsdagadsgasdgsag666", "ateat");
		if (layoutIndex == 0){
			
			layout01.addView(layout);
			
		} else if (layoutIndex == 1){
			
			layout02.addView(layout);
			
		} else if (layoutIndex == 2){
			
			layout03.addView(layout);
			
		}
		
		layout.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				//Toast.makeText(mContext,"您点击了"+ imageIndex+"个Item  ",Toast.LENGTH_SHORT).show();
				
			}
		});
		
	}
	
	//@SuppressWarnings("deprecation")
	public void updateBitMapToImage(Drawable result, int imageIndex){
		ImageView imageView = (ImageView) ((View)(layouts.get(imageIndex)).findViewById(R.id.news_cover));
		imageView.setImageDrawable(result);
		ProgressBar progressBar = (ProgressBar) ((View)(layouts.get(imageIndex)).findViewById(R.id.progressBar1));
		progressBar.setVisibility(View.INVISIBLE);
	}
	
	// 查询提示对话框
	private void searchDialog(){
		// 读取模板对话框
		searchDialog = new ProgressDialog(this);
		searchDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		searchDialog.setMessage(this.getText(R.string.searching));
		searchDialog.show();
	}
	
	
	private class GetHeaderDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {

			}
			return null;
		}

		@Override
		protected void onPostExecute(String[] result) {

			
			rv.finishRefresh();

			super.onPostExecute(result);
		}

	}

	private class GetFooterDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {

			}
			return null;
		}

		@Override
		protected void onPostExecute(String[] result) {

			
			rv.finishRefresh();

			super.onPostExecute(result);
		}

	}
	
	@SuppressLint("NewApi")
	private class GetFooterDataTask2 extends AsyncTask<String[], Void, Drawable> {

		private int imageIndex;
		private int layoutIndex;
		
		public GetFooterDataTask2(int imageIndex, int layoutIndex){
			this.imageIndex = imageIndex;
			this.layoutIndex = layoutIndex;
			initial(this.layoutIndex, this.imageIndex);
		}

		@Override
		protected Drawable doInBackground(String[]... params)
		{
			Drawable drawable = null;
			BrowseSDCardImage checkImage = new BrowseSDCardImage(params[0][this.imageIndex]);
			String imgURL = Constants.WEBURL_INDEX + params[0][this.imageIndex];
			//if(imageNamesOfSDCard.contains(params[0][this.imageIndex])){
			Log.i("nonononononono", imgURL);
			if(checkImage.isExistImage()){
				Log.i("nonononononono", "tea");
				Bitmap bitmap = checkImage.getBitMap();
				@SuppressWarnings("deprecation")
				BitmapDrawable bd = new BitmapDrawable(bitmap);
				drawable = bd;
			}else{
				try
				{
					Log.i("nonononononono2222", "tea");
					URL url = new URL(imgURL);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setDoInput(true);
					conn.connect();
					InputStream inputStream = conn.getInputStream();
					Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
					
					int width = bitmap.getWidth();
					int height = bitmap.getHeight();
					float scaleWidth = (float) windowWidth/2.5f/width;
		        	Matrix matrix = new Matrix();
		        	matrix.postScale(scaleWidth, scaleWidth);
		        	
		        	bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
		        	FileCache.getInstance().savaBmpData(imagePathStr[imageIndex], bitmap);
		        	
					@SuppressWarnings("deprecation")
					BitmapDrawable bd = new BitmapDrawable(bitmap);
					drawable = bd;
				} catch (MalformedURLException e)
				{
					e.printStackTrace();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}

			return drawable;
		}
		
		@Override
		protected void onPostExecute(Drawable result)
		{
			rv.finishRefresh();
			super.onPostExecute(result);
			//searchDialog.dismiss();
			if (result != null){
				updateBitMapToImage(result, imageIndex);
			}
		}

		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
	}
}