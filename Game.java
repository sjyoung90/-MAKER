package com.android.progBar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class Game extends Activity {
    /** Called when the activity is first created. */    		
	
	DBManager dManager;
	DBManager2 dManager2;
	SQLiteDatabase db, db2;
	private static final String DATABASE_NAME = "character.db";
	private static final String DATABASE_NAME2 = "calendar.db";
	int d_day = 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                
        setContentView(R.layout.game);        
        
        if (db == null) { 
        	db = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
		}
        if (db2 == null) { 
        	db2 = openOrCreateDatabase(DATABASE_NAME2, Context.MODE_PRIVATE, null);
		}
        
        dManager = new DBManager(this, "character.db", null, 1);
        db = dManager.getWritableDatabase();
        db.close();
        
        dManager2 = new DBManager2(this, "calendar.db", null, 1);
        db2 = dManager2.getWritableDatabase();
        db2.close(); 
        
        getDay();        
    }

   public void onMain(View v){
	   d_day = d_day + 3;
	   setDay(d_day);
	   startActivity(new Intent(Game.this, Main.class));
   }
   
   public void getDay(){
	   
	   db2 = dManager2.getWritableDatabase();   
 	   String sql = "SELECT day FROM calendar;";

   	  try{   
   		  
   	   Cursor cur = db2.rawQuery(sql, null);
   	   
   	   while(cur.moveToNext()){
   		   
   		   d_day = cur.getInt(cur.getColumnIndex("day"));     		   
   		  
   	   }    	   
   	   
   	 }catch (SQLException se) {
   	   // TODO: handle exception    	  
   	 }   
         db2.close();          
	     
   }  
   
   public void setDay(int d){
 	   
	    String sql = "";	    
	    db2 = dManager2.getWritableDatabase(); 
	    
	    if(d > 30){
	    	d = 1;
	    	sql = "update calendar set day = '" + d + "';";	
	    }   
	    else sql = "update calendar set day = '" + d + "';";		
			 
		db2.execSQL(sql);
		db2.close();
	   
  }
}


