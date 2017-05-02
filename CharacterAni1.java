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


public class CharacterAni1 extends Activity {
    /** Called when the activity is first created. */    		
	
	DBManager dManager;
	SQLiteDatabase db;
	private static final String DATABASE_NAME = "character.db";
	String d_name, d_university, d_major;
	TextView text;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                
        setContentView(R.layout.characterani1);        
        
        text = (TextView)findViewById(R.id.textView1);
        
        if (db == null) { 
        	db = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
		}
        
        dManager = new DBManager(this, "character.db", null, 1);
        db = dManager.getWritableDatabase();
        db.close();
        onOutput();
        
        text.setText("축하합니다! " + "\n" + d_name + "님은 " + "\n" + d_university + "대학교 " + d_major + "에" + "\n" + "합격하셨습니다!" + "\n");
    }

    public void onNext(View v){
    	
    	startActivity(new Intent(CharacterAni1.this, CharacterAni2.class));
    	
    }
    
    public void onOutput(){     

    	  db = dManager.getWritableDatabase();   
    	  String sql = "SELECT distinct name,university,major FROM character;";
    	  
    	  try{   
    		  
    	   Cursor cur = db.rawQuery(sql, null);
    	   
    	   while(cur.moveToNext()){

    		   //int num = cur.getInt(0);
    		   d_name = cur.getString(cur.getColumnIndex("name"));
    		   d_university = cur.getString(cur.getColumnIndex("university"));
    		   d_major = cur.getString(cur.getColumnIndex("major"));
    		   
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


