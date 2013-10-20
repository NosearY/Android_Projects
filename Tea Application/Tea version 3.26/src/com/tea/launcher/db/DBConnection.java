package com.tea.launcher.db;

import java.util.HashMap;
import java.util.List;

import com.tea.launcher.util.TeaProducts;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class DBConnection {
	SQLiteDatabase db;
	private String tableName;
	public DBConnection(String tableName){
		this.tableName = tableName;
		openDatabase();		//open (create if needed) database
		//dropTable();		//if needed drop table tblAmigos
		//createDB();	//create-populate tblAmigos
	}
	///////////////////////////////////////////////////////////////////
    public void openDatabase() {
      try {        	
          //------------------------------------------------------------------------------
          // WARNING: You must include in your manifest the following request
          //
          //  <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" /> 
          //        
          //------------------------------------------------------------------------------
          
          // this provides the 'real' path name to the SD card
          String SDcardPath = Environment.getExternalStorageDirectory().getPath();
          
          // other options for myDbPath are:
          // Using device's memory:    "data/data/cis493.sqldatabases/myfriendsDB"
          // Absolute path (1.5 &1.6)  "/sdcard/myfriendsDB"
          // ------------------------------------------------------------------------------
          String myDbPath = SDcardPath + "/tea/" + "veeko_tea.db";   
          Log.i("PATHSDCARD", myDbPath);
      	
      	db = SQLiteDatabase.openDatabase(
      			myDbPath,
  				null,
  				SQLiteDatabase.CREATE_IF_NECESSARY) ;       	
      	
      }
      catch (SQLiteException e) {      	
      }
  }//createDatabase
  
  ///////////////////////////////////////////////////////////////////
  public void createDB() {
  	//create table: tblAmigo
  	db.beginTransaction();
		try {
			//create table
			db.execSQL("create table "+tableName+" ("
					+ " id integer PRIMARY KEY autoincrement, " 
			        + " imageURL  text, " 
			        + " teaName  text, " 
			        + " href text );  ");
			//commit your changes
  		db.setTransactionSuccessful();
		} catch (SQLException e1) {			
			Log.i("error for creating database", "Exception");
		}
		finally {
  		db.endTransaction();
  	}
		
  }
  
  public void createNewsDB() {
	  	//create table: tblAmigo
	  	db.beginTransaction();
			try {
				//create table
				db.execSQL("create table "+tableName+" ("
						+ " id integer PRIMARY KEY autoincrement, " 
				        + " title  text, " 
				        + " time  text, " 
				        + " href text );  ");
				//commit your changes
	  		db.setTransactionSuccessful();
			} catch (SQLException e1) {			
				Log.i("error for creating database", "Exception");
			}
			finally {
	  		db.endTransaction();
	  	}
			
	  }
  
/////////////////////////////////////////////////////////////////
  public TeaProducts useCursor(String[] imagePathStr, String[] teaNameStr) {
	    TeaProducts products = new TeaProducts();
		try {
			// obtain a list of <recId, name, phone> from DB
			String[] columns = { "id", "imageURL", "teaName", "href"};
			Cursor c = db.query(tableName, columns, 
					            null, null, null, null, "id");
			
			
			//int idCol = c.getColumnIndex("id");
			int imageURLCol = c.getColumnIndex("imageURL");
			int teaNameCol = c.getColumnIndex("teaName");
			if(c.getCount() == 0){
				return null;
			}else{
				c.moveToLast();
				imagePathStr = insert(imagePathStr, c.getString(imageURLCol).toString().trim());
				teaNameStr = insert(teaNameStr, c.getString(teaNameCol));
				while (c.moveToPrevious()) {
					Log.i("TestingCOl2", c.getString(imageURLCol));
					imagePathStr = insert(imagePathStr, c.getString(imageURLCol));
					teaNameStr = insert(teaNameStr, c.getString(teaNameCol));
				}

				products.setImagePathStr(imagePathStr);
				products.setTeaNameStr(teaNameStr);
			}

			c.close();
			db.close();
			return products;
		} catch (Exception e) {

		}
		return null;
  }//useCursor1    
  
  /////////////////////////////////////////////////////////////////
  private void useRawQuery1() {
  	try {
  		//hard-coded SQL select with no arguments
			String mySQL ="select count(*) as Total from tblAMIGO";
			Cursor c1 = db.rawQuery(mySQL, null);
			
			//advance to the next record (first rec. if necessary)
			c1.moveToNext();

		} catch (Exception e) {
			
		}    	
  }//useRawQuery1
  
  public int getCountOfRecord() {
		try {
			// obtain a list of <recId, name, phone> from DB
			String[] columns = { "id", "message" };

			Cursor c = db.query(tableName, columns, 
					            null, null, null, null, "id");
			
			return c.getCount();
			

		} catch (Exception e) {

		}
		return 0;
  }//useCursor1   
  
  public boolean isRedundancy(String message){
		try {
			// obtain a list of <recId, name, phone> from DB
			String[] columns = { "id", "message" };

			Cursor c = db.query(tableName, columns, 
					            null, null, null, null, "id");
			
			int messageCol = c.getColumnIndex("message");

			while (c.moveToNext()) {
				if(c.getString(messageCol).equals(message))
					return false;
			}
			

		} catch (Exception e) {
			
		}
	  return true;
  }
/////////////////////////////////////////////////////////////////////
  public void dropTable(){
  	//(clean start) action query to drop table 
  	
		try {
			db.execSQL( " drop table " + tableName+"; "); 
		} catch (Exception e) {

		}  
  }

////////////////////////////////////////////////////////////////////
  public void useInsertMethod(String imageURL, String teaName, String href) {
  	// an alternative to SQL "insert into table values(...)"
  	// ContentValues is a dynamic row-like container
  	ContentValues initialValues = new ContentValues();

  	initialValues.put("imageURL", imageURL);
  	initialValues.put("teaName", teaName);
  	initialValues.put("href", href);
  	db.insert(tableName, null, initialValues);
  	
  }// useInsertMethod

  
  public void useInsertNews(String title, String time, String href) {
	  	// an alternative to SQL "insert into table values(...)"
	  	// ContentValues is a dynamic row-like container
	  	ContentValues initialValues = new ContentValues();

	  	initialValues.put("title", title);
	  	initialValues.put("time", time);
	  	initialValues.put("href", href);
	  	db.insert(tableName, null, initialValues);
	  	
	  }// useInsertMethod
  
/////////////////////////////////////////////////////////////////////
  public void useUpdateMethod() {
  	//using the update method to change name of selected friend
  	String [] whereArgs = {"2", "7"};	

  	ContentValues updValues = new ContentValues();
  	updValues.put("message", "Maria");

  	int recAffected =	db.update( tableName, 
  									updValues, 
  									"id > ? and id < ?", 
  									whereArgs );
  }

  
/////////////////////////////////////////////////////////////////////
   public void useDeleteMethod() {
  	//using the delete method to remove a group of friends
  	//whose id# is between 2 and 7
  	String [] whereArgs = {"2", "7"};

  	int recAffected = db.delete(tableName, 
  			"id > ? and id < ?", whereArgs);

  }    
  
	private static String[] insert(String[] arr, String str)
    {
        int size = arr.length;
        
        String[] tmp = new String[size + 1];
        
        System.arraycopy(arr, 0, tmp, 0, size);
        
        tmp[size] = str;
        
        return tmp;
    }
}
