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


public class CharacterAni3 extends Activity {
    /** Called when the activity is first created. */    		
	
	DBManager dManager;
	SQLiteDatabase db;
	private static final String DATABASE_NAME = "character.db";
	String d_name, d_payment;
	TextView text;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                
        setContentView(R.layout.characterani3);        
        
        text = (TextView)findViewById(R.id.textView1);
        
        if (db == null) { 
        	db = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
		}
        
        dManager = new DBManager(this, "character.db", null, 1);       
        db.close();
        onOutput();
        
        text.setText(d_name + "¥‘¿« ≈Î¿Â");
    }

    public void onNext(View v){    	
    	startActivity(new Intent(CharacterAni3.this, CharacterAni4.class));    	
    }
    
    public void onOutput(){  
    	
    	  db = dManager.getWritableDatabase();       	 
    	  String sql = "SELECT distinct name, nnonpayment FROM character;";
    	  
    	  try{   
    		  
    	   Cursor cur = db.rawQuery(sql, null);
    	   
    	   while(cur.moveToNext()){

    		   d_name = cur.getString(cur.getColumnIndex("name"));
    		   d_payment = cur.getString(cur.getColumnIndex("nnonpayment"));
    		   
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

