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

public class CultureActivity2 extends Activity {
	private int[] cultureFileName = {R.raw.culture2_1, R.raw.culture2_2, R.raw.culture2_3, R.raw.culture2_4, R.raw.culture2_5, R.raw.culture2_6};
	 @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.activity_culture1);  
	        //��Layout�����ListView  
	        ListView list = (ListView) findViewById(R.id.ListView01);  
	          
	        //���ɶ�̬���飬��������  
	        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();  
	        for(int i=0;i<6;i++)  
	        {
	        	if(i==0){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "��μ����ն����Ʒ��");  
	                map.put("ItemTime", "2012-07-09 15:49");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==1){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "��ô���ն��裬���ն��ķ���");  
	                map.put("ItemTime", "2012-07-09 15:44");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==2){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "��δ���ն���");  
	                map.put("ItemTime", "2012-07-09 15:26");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==3){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "�ն���Ĺ�Ч������");  
	                map.put("ItemTime", "2012-07-09 11:53");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==4){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "�ն�����η��ࣿ");  
	                map.put("ItemTime", "2012-07-09 11:38");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else{
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "ʲô���ն��裿");  
	                map.put("ItemTime", "2012-07-09 11:27");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}
	             
	        }  
	        //������������Item�Ͷ�̬�����Ӧ��Ԫ��  
	        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,// ����Դ   
	            R.layout.list_items,//ListItem��XMLʵ��  
	            //��̬������ImageItem��Ӧ������          
	            new String[] {"ItemImage","ItemTitle", "ItemTime", "LastImage"},   
	            //ImageItem��XML�ļ������һ��ImageView,����TextView ID  
	            new int[] {R.id.ItemImage,R.id.ItemTitle, R.id.ItemTime, R.id.last}  
	        );  
	         
	        //��Ӳ�����ʾ  
	        list.setAdapter(listItemAdapter);  
	          
	        //��ӵ��  
	        list.setOnItemClickListener(new OnItemClickListener() {  
	  
	            @Override  
	            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
	                    long arg3) {  
	            	Intent intent = new Intent(CultureActivity2.this, ContentActivity.class);
	            	Bundle myData = new Bundle();
					 myData.putInt("id", cultureFileName[arg2]);
					 myData.putString("name", "��ѧ��");
					 intent.putExtras(myData);
					 startActivityForResult(intent, cultureFileName[arg2]);	
	                //Toast.makeText(CultureActivity2.this,"�������"+ arg2 +"��Item  ",Toast.LENGTH_SHORT).show();
	            }  
	        });  
	    }  
	      
}