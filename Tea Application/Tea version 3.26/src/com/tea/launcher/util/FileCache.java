package com.tea.launcher.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.util.Log;


public class FileCache {
	private static FileCache fileCache; // 本类的引用
	private String strImgPath; // 图片保存的路径
	
	@SuppressLint("SdCardPath")
	public FileCache() {
		// this.context = context;
		String strPathHead = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			strPathHead = Environment.getExternalStorageDirectory().toString();
		} else
			strPathHead = Constants.PHONE_PATH;
		strImgPath = strPathHead + Constants.SDCARD_PATH;
	}
	
	public static FileCache getInstance() {
		if (null == fileCache) {
			fileCache = new FileCache();
		}
		return fileCache;
	}
	
	// 用图片的URL来命名图片，并保存图片
	public boolean savaBmpData(String imgurl, Bitmap bmp) {
		String imgName = imgurl.substring(
				imgurl.lastIndexOf('/') + 1,
				imgurl.length());
		File imgFileDirs = new File(strImgPath);
		if (!imgFileDirs.exists()) {
			Log.i("Existing3", strImgPath);
			imgFileDirs.mkdirs();
		}
		File fImg = new File(strImgPath + imgName);
		if (fImg.exists()) {
			fImg.delete();
		}
		this.writeToFile(bmp, fImg);
		return true;
	}
	
	private boolean writeToFile(Bitmap bmp, File file) {
		if (file.exists()) {
			file.delete();
		}
		String name = file.getName();
		String geShi = name.substring(name.lastIndexOf('.'), name.length());

		FileOutputStream fos = null;
		try {
			Log.i("File test7", geShi);
			fos = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			if (null != bmp) {
				if (".JPEG".equalsIgnoreCase(geShi)
						|| ".JPG".equalsIgnoreCase(geShi)) {
					bmp.compress(CompressFormat.JPEG, 100, bos);
					bos.flush();
					bos.close();
					Log.i("File test6", geShi);
				} else if (".PNG".equalsIgnoreCase(geShi)) {
					bmp.compress(CompressFormat.PNG, 100, bos);
					bos.flush();
					bos.close();
				}
				return true;
			} else
				bos.close();
			Log.i("File test4", "testing");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			Log.i("File test1", "testing");
			e.printStackTrace();
		} finally {
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					Log.i("File test2", "testing");
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.v("错误", "图片写入缓存文件错误");
				}
			}
		}
		return false;
	}

}
