package com.tea.launcher.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EncodingUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.tea.launcher.db.DBConnection;


public class WebContentOfProduct{

	private Document doc;
	private String webURLOfIndex = Constants.WEBURL_INDEX;//index
	private String webURLofProduct = "http://www.ynbzc.cn/index.php?_m=mod_product&_a=prdlist&p=1&cap_id=91";//new product
	private String webURLofProduct2 = "http://www.ynbzc.cn/index.php?_m=mod_product&_a=prdlist&p=1&cap_id=92";//all product
	private String webURLofNew1 = "http://www.ynbzc.cn/index.php?_m=mod_article&_a=fullist&caa_id=26";
	
	private TeaProducts products = null;
	//private DBConnection db = null;
	public WebContentOfProduct(){
		products = new TeaProducts();
		//db = new DBConnection();
	}
	
	public TeaProducts load(String[] imagePathStr, String[] teaNameStr, String[] hrefStr) {
		// TODO Auto-generated method stub
		
		try {
			if(!products.getIsStartLoading()){
				Log.i("Next URL", "OK");
				setNextURL();
			}
			products.setIsStartLoading(false);
			String myString = null;
			myString = posturl(webURLofProduct);
			// 设置屏幕显示
			String html = myString;
			doc = Jsoup.parse(html);
			Elements es = doc.getElementsByClass("prod_list_pic");
			String imageLink = "";
			String teaName = "";
			String href = "";
			for (Element e : es) {
				
				imageLink = e.getElementsByTag("img").attr("src");
				teaName = e.getElementsByTag("a").attr("title");
				href =  e.getElementsByTag("a").attr("href");
				//db.useInsertMethod(imageLink, teaName);
				imagePathStr = insert(imagePathStr, imageLink);
				teaNameStr = insert(teaNameStr, teaName);
				hrefStr = insert(hrefStr, href);
				Log.i("Lv cha2222", href);
			}
			
			products.setImagePathStr(imagePathStr);
			products.setTeaNameStr(teaNameStr);
			products.setHrefStr(hrefStr);
			Log.i("Can you go here3333", "testing");
		} catch (Exception e) {
			Log.i("Can you go here22222", "testing");
			e.printStackTrace();
		}
		Log.i("Can you go here344444", "testing");
		return products;
		
	}
	
	
	public TeaProducts load2(String[] imagePathStr, String[] teaNameStr, String[] hrefStr) {
		// TODO Auto-generated method stub
		
		try {
			if(!products.getIsStartLoading()){
				Log.i("Next URL", "OK");
				setNextURL2();
			}
			products.setIsStartLoading(false);
			String myString = null;
			myString = posturl(webURLofProduct2);
			// 设置屏幕显示
			String html = myString;
			doc = Jsoup.parse(html);
			Elements es = doc.getElementsByClass("prod_list_pic");
			String imageLink = "";
			String teaName = "";
			String href = "";
			for (Element e : es) {
				
				imageLink = e.getElementsByTag("img").attr("src");
				teaName = e.getElementsByTag("a").attr("title");
				href = e.getElementsByTag("a").attr("href");
				//db.useInsertMethod(imageLink, teaName);
				imagePathStr = insert(imagePathStr, imageLink);
				teaNameStr = insert(teaNameStr, teaName);
				hrefStr = insert(hrefStr, href);
				Log.i("Lv cha33333", href);
			}
			
			products.setImagePathStr(imagePathStr);
			products.setTeaNameStr(teaNameStr);
			products.setHrefStr(hrefStr);
			Log.i("Can you go here3333", "testing");
		} catch (Exception e) {
			Log.i("Can you go here22222", "testing");
			e.printStackTrace();
		}
		Log.i("Can you go here344444", "testing");
		return products;
		
	}
	
	public void loadNews(String newsURL, String dbname, DBConnection dbconnection) {
		// TODO Auto-generated method stub
		
		try {
			String myString = null;
			myString = posturl(newsURL);
			// 设置屏幕显示
			String html = myString;
			doc = Jsoup.parse(html);
			Elements es = doc.getElementsByClass("art_list_con");
			es = es.select("li");
			for (Element e : es) {
				String content = e.text().trim();
				String s_title = content.substring(0, content.length()-16).trim();
				String s_time = content.substring(content.length()-16, content.length()).trim();
				String s_href = e.getElementsByTag("a").attr("href");
				dbconnection.useInsertNews(s_title, s_time, s_href);
			}
			
		} catch (Exception e) {
			Log.i("WHy exception","sfasf");
			e.printStackTrace();
		}
		
	}
	
	
	public void initialNewsDB(String newsURL, String dbname){
		String nextURL = this.getNextURL(newsURL);
		DBConnection dbconnection = new DBConnection(dbname);
		dbconnection.dropTable();
		dbconnection.createNewsDB();
		loadNews(newsURL, dbname, dbconnection);
		while(true){
			if(nextURL.equals(newsURL)){
				break;
			}
			loadNews(nextURL, dbname, dbconnection);
			newsURL = nextURL;
			nextURL = this.getNextURL(nextURL);
		}
	}
	/**
	 * 
	 * 这个方法主要是定义的数组，动态向数组添加数据
	 * @param arr: The list of array
	 * @param str: The element is add into the arr
	 * @return
	 */
	private static String[] insert(String[] arr, String str)
    {
        int size = arr.length;
        
        String[] tmp = new String[size + 1];
        
        System.arraycopy(arr, 0, tmp, 0, size);
        
        tmp[size] = str;
        
        return tmp;
    }

	/**
	 * @param urlString
	 * @return
	 */
	public String getHtmlString(String urlString) {
		try {
			URL url = null;
			url = new URL(urlString);

			URLConnection ucon = null;
			ucon = url.openConnection();

			InputStream instr = null;
			instr = ucon.getInputStream();

			BufferedInputStream bis = new BufferedInputStream(instr);

			ByteArrayBuffer baf = new ByteArrayBuffer(500);
			int current = 0;
			while ((current = bis.read()) != -1) {
				baf.append((byte) current);
			}
			return EncodingUtils.getString(baf.toByteArray(), "gbk");
		} catch (Exception e) {
			return "";
		}
	}

	
	public String setNextURL(){
		try {
			String myString = null;
			myString = posturl(webURLofProduct);
			// 设置屏幕显示
			String html = myString;
			doc = Jsoup.parse(html);
			Element pagerTable = doc.getElementById("pager");
			Elements next = pagerTable.select("td");
			
			for (Element e : next) {
				if(e.getElementsByTag("a").attr("title").equals("Next")){
					webURLofProduct = webURLOfIndex + e.getElementsByTag("a").attr("href");
					return webURLofProduct;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return webURLofProduct;
	}
	
	public String setNextURL2(){
		try {
			String myString = null;
			myString = posturl(webURLofProduct2);
			// 设置屏幕显示
			String html = myString;
			doc = Jsoup.parse(html);
			Element pagerTable = doc.getElementById("pager");
			Elements next = pagerTable.select("td");
			
			for (Element e : next) {
				if(e.getElementsByTag("a").attr("title").equals("Next")){
					webURLofProduct2 = webURLOfIndex + e.getElementsByTag("a").attr("href");
					return webURLofProduct2;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return webURLofProduct;
	}
	
	
	public String getNextURL(String currentURL){
		try {
			String myString = null;
			myString = posturl(currentURL);
			// 设置屏幕显示
			String html = myString;
			doc = Jsoup.parse(html);
			Element pagerTable = doc.getElementById("pager");
			Elements next = pagerTable.select("td");
			
			for (Element e : next) {
				if(e.getElementsByTag("a").attr("title").equals("Next")){
					currentURL = webURLOfIndex + e.getElementsByTag("a").attr("href");
					return currentURL;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return currentURL;
	}
	
	/**
	 * 获取参数指定的网页代码，将其返回给调用者，由调用者对其解析 返回String
	 */
	public String posturl(String url) {
		InputStream is = null;
		String result = "";

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (Exception e) {
			return "Fail to establish http connection!" + e.toString();
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "utf-8"));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();

			result = sb.toString();
		} catch (Exception e) {
			return "Fail to convert net stream!";
		}

		return result;
	}
	
}