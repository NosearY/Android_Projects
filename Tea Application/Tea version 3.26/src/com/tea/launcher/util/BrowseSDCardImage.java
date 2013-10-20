package com.tea.launcher.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

public class BrowseSDCardImage {

	private String filename;
	private String imgurl;
	private String imgName;
	private List<String> imageNames;
	
	public BrowseSDCardImage(){
		initialFilename();
	}
	
	public BrowseSDCardImage(String imgurl){
		this.imgurl = imgurl;
		this.imgName = this.imgurl.substring(
				imgurl.lastIndexOf('/') + 1,
				imgurl.length());
		initialFilename();
	}
	
	public Bitmap getBitMap(){
		Bitmap bitmap = BitmapFactory.decodeFile(filename+imgName);
		return bitmap;
	}
    
    
	public boolean isExistImage() {

		Log.i("Do you come here", filename);
		File imgFileDirs = new File(filename);
		if (!imgFileDirs.exists()) {
			return false;
		}
		try{
			Log.i("Do you come here22222", filename + imgName);
			File fImg = new File(filename + imgName);
			if (fImg.exists()) {
				Log.i("testing633333333333333333333333", "true");
				return true;
			}
			
		}catch(Exception e){
			Log.i("testing61111111111111111111111111", "false");
			return false;
		}

		Log.i("testing65555555555555555555555", imgName);
		return false;
	}
	
    public List<String> getSDCard() {
        imageNames = new ArrayList<String>();
        try {
        	Log.i("NOWFILENAME",filename);
        	File file = new File(Environment.getExternalStorageDirectory(), Constants.SDCARD_PATH);
            File[] files = file.listFiles();
            for (File theFile : files) {
                if (matchImageFile(theFile.getPath())) {
                    imageNames.add(Constants.WEBURL_INDEX + Constants.SRC_IMG + theFile.getName());
                }
            }
        } catch (Exception e) {
            return imageNames;
        }
        return imageNames;
    }
	
    public boolean matchImageFile(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
        if (extension.equals("jpg") || extension.equals("png") || extension.equals("gif") || extension.equals("jpeg") || extension.equals("bmp")) {
            return true;
        }
        return false;
    }

    
	@SuppressLint("SdCardPath")
	public void initialFilename(){
		String strPathHead = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			strPathHead = Environment.getExternalStorageDirectory().toString();
		} else{
			strPathHead = Constants.PHONE_PATH;
		}
		filename = strPathHead + Constants.SDCARD_PATH;
	}
}
