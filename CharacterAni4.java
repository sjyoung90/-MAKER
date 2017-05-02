package com.android.progBar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;


public class CharacterAni4 extends Activity {
    /** Called when the activity is first created. */    		
	
	DBManager dManager;
	SQLiteDatabase db;
	private static final String DATABASE_NAME = "character.db";
	String d_university;
	TextView text;
	static boolean firstStart = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                
        setContentView(R.layout.characterani4);        
        
        text = (TextView)findViewById(R.id.textView1);
        
        if (db == null) { 
        	db = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
		}
        
        dManager = new DBManager(this, "character.db", null, 1);        
        db.close();
        onOutput();
        
        text.setText(d_university + "대학교의 입학을 축하드립니다! ");
    }

    public void onNext(View v){
    	firstStart = true;
    	startActivity(new Intent(CharacterAni4.this, Main.class));    	
    }
    
    public void onOutput(){ 
    	
    	  db = dManager.getWritableDatabase();   
    	  String sql = "SELECT distinct university FROM character ;";
    	  
    	  try{   
    		  
    	   Cursor cur = db.rawQuery(sql, null);
    	   
    	   while(cur.moveToNext()){
    		   
    		   d_university = cur.getString(cur.getColumnIndex("university"));
    		   
    	   }
    	   
    	 }catch (SQLException se) {
    	   // TODO: handle exception    	  
    	 }  
          db.close();    
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	      
        return true;
       
    }
}
