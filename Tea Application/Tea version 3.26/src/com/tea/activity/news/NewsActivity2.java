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
	        //��Layout�����ListView  
	        ListView list = (ListView) findViewById(R.id.ListView01);  
	          
	        //���ɶ�̬���飬��������  
	        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();  
	        for(int i=newsFileName.length-1; i>=0; i--)  
	        {
	        	if(i==9){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "ף���ൺ�лƵ�����Ӫ�����");  
	                map.put("ItemTime", "2013-07-29 15:10");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==8){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "������ 2013������Ʒ������");  
	                map.put("ItemTime", "2013-07-15 11:28");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==7){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "������2013�²��ƹ㡪�����ڴ�����ҵ������");  
	                map.put("ItemTime", "2013-07-07 13:50");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==6){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "2013�����������ڴ����販�ᣬ��һ�챨��");  
	                map.put("ItemTime", "2013-07-03 20:59");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==5){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "ף���������ɽ�����������̳������ѵ���ҵ");  
	                map.put("ItemTime", "2013-07-01 15:00");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==4){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "2013���ڴ����販�ἴ�������Ļ");  
	                map.put("ItemTime", "2013-06-28 12:31");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==3){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "������Զ����������лNicky���������֧��");  
	                map.put("ItemTime", "2013-06-20 15:40");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==2){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "ף�ع����а�������Ӫ�����");  
	                map.put("ItemTime", "2013-06-19 17:44");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==1){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "�����롪��ɽ�����販��Բ������");  
	                map.put("ItemTime", "2013-06-18 12:02");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else{
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "�������롱������ɽ�����販��Բ������");  
	                map.put("ItemTime", "2013-06-04 14:20");
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
	            	Intent intent = new Intent(NewsActivity2.this, ContentActivity.class);
	            	Bundle myData = new Bundle();
					 myData.putInt("id", newsFileName[newsFileName.length-arg2-1]);
					 myData.putString("name", "���Ż");
					 intent.putExtras(myData);
					 startActivityForResult(intent, newsFileName[newsFileName.length-arg2-1]);	
	                //Toast.makeText(CultureActivity1.this,"�������"+ arg2 +"��Item  ",Toast.LENGTH_SHORT).show();
	            }  
	        });  
	    }  
	      
}