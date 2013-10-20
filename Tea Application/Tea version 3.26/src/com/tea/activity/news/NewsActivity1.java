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
	        //��Layout�����ListView  
	        ListView list = (ListView) findViewById(R.id.ListView01);  
	          
	        //���ɶ�̬���飬��������  
	        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();  
	        for(int i=newsFileName.length-1; i>=0; i--)  
	        {
	        	if(i==9){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "�����롰�Ƴ³��£������º�������Ƭ��");  
	                map.put("ItemTime", "2013-06-05 16:26");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==8){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "���������������ն���۸����ǵ���������");  
	                map.put("ItemTime", "2013-06-04 13:25");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==7){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "�������롱���Խ��Խ���ٻ��Ʋ豭����");  
	                map.put("ItemTime", "2013-05-14 17:45");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==6){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "2013�ϴ��衪����ݸ����Ʒ����");  
	                map.put("ItemTime", "2013-05-12 16:08");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==5){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "���販�� �������ϴ���");  
	                map.put("ItemTime", "2013-05-08 13:18");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==4){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "�����롤���ڲ���Ʒ���ἴ������");  
	                map.put("ItemTime", "2012-12-17 14:50");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==3){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "�������ϴ��衤���ڲ販��");  
	                map.put("ItemTime", "2012-06-24 17:28");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==2){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "2012�������й��������ʲ�ҵ�����ῪĻ");  
	                map.put("ItemTime", "2012-06-09 14:13");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==1){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "��ΰ��Ĳ�Ҷ֮·������������̳�ڶ���˹��Ļ");  
	                map.put("ItemTime", "2012-06-08 17:50");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else{
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "�����販�ᡤ�������ϴ���");  
	                map.put("ItemTime", "2012-06-06 17:13");
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
	            	Intent intent = new Intent(NewsActivity1.this, ContentActivity.class);
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