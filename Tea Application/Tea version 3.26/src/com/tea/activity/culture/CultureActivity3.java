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
	        //��Layout�����ListView  
	        ListView list = (ListView) findViewById(R.id.ListView01);  
	          
	        //���ɶ�̬���飬��������  
	        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();  
	        for(int i=0;i<7;i++)  
	        {
	        	if(i==0){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "��γ����ն���");  
	                map.put("ItemTime", "2013-04-27 11:55");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==1){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "��ӹ����г����");  
	                map.put("ItemTime", "2012-07-14 11:21");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==2){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "��ʫ�йأ�����й�");  
	                map.put("ItemTime", "2012-07-14 10:43");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==3){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "��ζ����");  
	                map.put("ItemTime", "2012-07-12 10:26");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==4){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "������-¬��");  
	                map.put("ItemTime", "2012-07-11 16:19");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else if(i==5){
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "�й����ա�����֮��");  
	                map.put("ItemTime", "2012-07-11 15:22");
	                map.put("LastImage", R.drawable.lastimage);  
	                listItem.add(map); 
	        	}else{
	        		HashMap<String, Object> map = new HashMap<String, Object>();  
	                map.put("ItemImage", R.drawable.arrow);//ͼ����Դ��ID  
	                map.put("ItemTitle", "�й����Ļ��γɼ�ʷ");  
	                map.put("ItemTime", "2012-07-10 15:45");
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
	            	Intent intent = new Intent(CultureActivity3.this, ContentActivity.class);
	            	Bundle myData = new Bundle();
					 myData.putInt("id", cultureFileName[arg2]);
					 myData.putString("name", "��ѧ��");
					 intent.putExtras(myData);
					 startActivityForResult(intent, cultureFileName[arg2]);	
	                //Toast.makeText(CultureActivity3.this,"�������"+ arg2 +"��Item  ",Toast.LENGTH_SHORT).show();
	            }  
	        });  
	    }  
	      
}