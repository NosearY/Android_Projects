package com.tea.activity.news;

import java.util.ArrayList;
import java.util.HashMap;

import com.tea.activity.main.ContentActivity;
import com.tea.activity.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class NewsActivity2 extends Activity {
	private int[] newsFileName = {R.raw.news2_1, R.raw.news2_2, R.raw.news2_3, R.raw.news2_4, R.raw.news2_5, R.raw.news2_6, R.raw.news2_7, R.raw.news2_8, R.raw.news2_9, R.raw.news2_10};
	 @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.activity_culture1);  
	        //绑定Layout里面的ListView  
	        ListView list = (ListView) findViewById(R.id.ListView01);  
	          
	        //生成动态数组，加入数据  
	        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();  
	        for(int i=newsFileName.length-1; i>=0; i--)  
	        {
	        	if(i==9){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "祝贺青岛市黄岛区经营店成立");  
	                map.put("ItemTime", "2013-07-29 15:10");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==8){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "老曼峨 2013第三届品茗大赛");  
	                map.put("ItemTime", "2013-07-15 11:28");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==7){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "老曼峨2013新茶推广——深圳春季茶业博览会");  
	                map.put("ItemTime", "2013-07-07 13:50");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==6){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "2013年老曼峨深圳春季茶博会，第一天报道");  
	                map.put("ItemTime", "2013-07-03 20:59");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==5){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "祝贺老曼峨佛山禅城区经销商成立—友道茶业");  
	                map.put("ItemTime", "2013-07-01 15:00");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==4){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "2013深圳春季茶博会即将拉开帷幕");  
	                map.put("ItemTime", "2013-06-28 12:31");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==3){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "有朋自远方来——感谢Nicky对老曼峨的支持");  
	                map.put("ItemTime", "2013-06-20 15:40");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==2){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "祝贺广州市白云区经营店成立");  
	                map.put("ItemTime", "2013-06-19 17:44");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==1){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "老曼峨—佛山春季茶博会圆满结束");  
	                map.put("ItemTime", "2013-06-18 12:02");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else{
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "“老曼峨”——中山春季茶博会圆满结束");  
	                map.put("ItemTime", "2013-06-04 14:20");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}
	             
	        }  
	        //生成适配器的Item和动态数组对应的元素  
	        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,// 数据源   
	            R.layout.list_items,//ListItem的XML实现  
	            //动态数组与ImageItem对应的子项          
	            new String[] {"ItemImage","ItemTitle", "ItemTime", "LastImage"},   
	            //ImageItem的XML文件里面的一个ImageView,两个TextView ID  
	            new int[] {R.id.ItemImage,R.id.ItemTitle, R.id.ItemTime, R.id.last}  
	        );  
	         
	        //添加并且显示  
	        list.setAdapter(listItemAdapter);  
	          
	        //添加点击  
	        list.setOnItemClickListener(new OnItemClickListener() {  
	  
	            @Override  
	            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
	                    long arg3) {  
	            	Intent intent = new Intent(NewsActivity2.this, ContentActivity.class);
	            	Bundle myData = new Bundle();
					 myData.putInt("id", newsFileName[newsFileName.length-arg2-1]);
					 myData.putString("name", "新闻活动");
					 intent.putExtras(myData);
					 startActivityForResult(intent, newsFileName[newsFileName.length-arg2-1]);	
	                //Toast.makeText(CultureActivity1.this,"您点击了"+ arg2 +"个Item  ",Toast.LENGTH_SHORT).show();
	            }  
	        });  
	    }  
	      
}