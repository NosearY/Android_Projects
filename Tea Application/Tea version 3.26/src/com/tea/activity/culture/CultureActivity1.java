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

public class CultureActivity1 extends Activity {
	private int[] cultureFileName = {R.raw.culture1_1, R.raw.culture1_2};
	 @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.activity_culture1);  
	        //绑定Layout里面的ListView  
	        ListView list = (ListView) findViewById(R.id.ListView01);  
	          
	        //生成动态数组，加入数据  
	        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();  
	        for(int i=0;i<2;i++)  
	        {
	        	if(i==0){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "老曼峨培训手册之一");  
	                map.put("ItemTime", "2012-07-14 11:04");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else{
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//图像资源的ID  
	                map.put("ItemTitle", "茶叶加盟店对茶叶品牌的选择");  
	                map.put("ItemTime", "2012-07-12 10:30");
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
	            	Intent intent = new Intent(CultureActivity1.this, ContentActivity.class);
	            	Bundle myData = new Bundle();
					 myData.putInt("id", cultureFileName[arg2]);
					 myData.putString("name", "茶学堂");
					 intent.putExtras(myData);
					 startActivityForResult(intent, cultureFileName[arg2]);	
	                //Toast.makeText(CultureActivity1.this,"您点击了"+ arg2 +"个Item  ",Toast.LENGTH_SHORT).show();
	            }  
	        });  
	    }  
	      
}