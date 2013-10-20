package com.tea.activity.culture;

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

public class CultureActivity3 extends Activity {
	private int[] cultureFileName = {R.raw.culture3_1, R.raw.culture3_2, R.raw.culture3_3, R.raw.culture3_4, R.raw.culture3_5, R.raw.culture3_6, R.raw.culture3_7};
	 @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.activity_culture1);  
	        //绑定Layout里面的ListView  
	        ListView list = (ListView) findViewById(R.id.ListView01);  
	          
	        //生成动态数组，加入数据  
	        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();  
	        for(int i=0;i<7;i++)  
	        {
	        	if(i==0){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "如何冲泡普洱茶");  
	                map.put("ItemTime", "2013-04-27 11:55");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==1){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "中庸、和谐与茶道");  
	                map.put("ItemTime", "2012-07-14 11:21");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==2){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "与诗有关，与茶有关");  
	                map.put("ItemTime", "2012-07-14 10:43");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==3){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "茶味人生");  
	                map.put("ItemTime", "2012-07-12 10:26");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==4){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "七碗茶歌-卢仝");  
	                map.put("ItemTime", "2012-07-11 16:19");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==5){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "中国茶艺——茶之境");  
	                map.put("ItemTime", "2012-07-11 15:22");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else{
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "中国茶文化形成简史");  
	                map.put("ItemTime", "2012-07-10 15:45");
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
	            	Intent intent = new Intent(CultureActivity3.this, ContentActivity.class);
	            	Bundle myData = new Bundle();
					 myData.putInt("id", cultureFileName[arg2]);
					 myData.putString("name", "茶学堂");
					 intent.putExtras(myData);
					 startActivityForResult(intent, cultureFileName[arg2]);	
	                //Toast.makeText(CultureActivity3.this,"您点击了"+ arg2 +"个Item  ",Toast.LENGTH_SHORT).show();
	            }  
	        });  
	    }  
	      
}