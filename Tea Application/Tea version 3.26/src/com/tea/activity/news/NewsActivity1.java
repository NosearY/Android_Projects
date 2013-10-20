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

public class NewsActivity1 extends Activity {
	private int[] newsFileName = {R.raw.news1_1, R.raw.news1_2, R.raw.news1_3, R.raw.news1_4, R.raw.news1_5, R.raw.news1_6, R.raw.news1_7, R.raw.news1_8, R.raw.news1_9, R.raw.news1_10};
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
	                map.put("ItemTitle", "老曼峨“推陈出新，打造勐海‘名’片”");  
	                map.put("ItemTime", "2013-06-05 16:26");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==8){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "茶市剖析：导致普洱茶价格上涨的三大因素");  
	                map.put("ItemTime", "2013-06-04 13:25");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==7){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "“老曼峨”熟茶越陈越香荣获“云茶杯”金奖");  
	                map.put("ItemTime", "2013-05-14 17:45");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==6){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "2013上春茶——东莞春茶品茗会");  
	                map.put("ItemTime", "2013-05-12 16:08");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==5){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "天津茶博会 老曼峨上春茶");  
	                map.put("ItemTime", "2013-05-08 13:18");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==4){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "老曼峨·深圳茶友品茗会即将举行");  
	                map.put("ItemTime", "2012-12-17 14:50");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==3){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "老曼峨上春茶·深圳茶博会");  
	                map.put("ItemTime", "2012-06-24 17:28");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==2){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "2012第六届中国西安国际茶业博览会开幕");  
	                map.put("ItemTime", "2012-06-09 14:13");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==1){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "“伟大的茶叶之路”国际旅游论坛在俄罗斯开幕");  
	                map.put("ItemTime", "2012-06-08 17:50");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else{
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "沈阳茶博会·老曼峨上春茶");  
	                map.put("ItemTime", "2012-06-06 17:13");
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
	            	Intent intent = new Intent(NewsActivity1.this, ContentActivity.class);
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