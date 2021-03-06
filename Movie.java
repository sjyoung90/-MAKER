package com.android.progBar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class Movie extends Activity {
    /** Called when the activity is first created. */    		
	
	DBManager dManager;
	DBManager2 dManager2;
	SQLiteDatabase db, db2;
	private static final String DATABASE_NAME = "character.db";
	private static final String DATABASE_NAME2 = "calendar.db";
	int d_day = 1;
	String d_strength, d_money;
	int s_v, m_v;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                
        setContentView(R.layout.movie);        
        
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
        getValue();
    }
    
    public void getValue(){
    	
    	  db = dManager.getWritableDatabase();   
    	  String sql = "SELECT distinct strength, money FROM character;";

      	  try{   
      		  
      	   Cursor cur = db.rawQuery(sql, null);
      	   
      	   while(cur.moveToNext()){
      	       		   
      		   d_strength = cur.getString(cur.getColumnIndex("strength"));
      		   d_money = cur.getString(cur.getColumnIndex("money"));
      		     		  
      	   }    	   
      	   
      	 }catch (SQLException se) {
      	   // TODO: handle exception    	  
      	 }   
            db.close();
            
        s_v = Integer.parseInt(d_strength);
        m_v = Integer.parseInt(d_money);
    }
  	
  	public void setValue(int s, int m){
  		
  			if(s > 100){ s = 100; }  			
  			
  			String str_s = String.valueOf(s);
  			String str_m = String.valueOf(m);
  		
  	    	String sql = "", sql2 = "";
  			db = dManager.getWritableDatabase();    	
  	   		
  			sql = "update character set strength = '" + str_s + "';";
  			sql2 = "update character set money = '" + str_m + "';";
  			 
  			db.execSQL(sql);
  			db.execSQL(sql2);
  			
  			db.close();  
  			
  	}

   public void onMain(View v){
	   
	   AlertDialog.Builder alertDlg = new AlertDialog.Builder(Movie.this) ;   
		  alertDlg.setTitle("결과");
		  alertDlg.setMessage("\n" + "돈이 3만원 차감되었습니다." + "\n" + "체력이 15 증가합니다." + "\n");   
		  alertDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {				  
				   s_v = s_v + 15;
				   m_v = m_v - 3;
				   setValue(s_v, m_v);
				   d_day = d_day + 3;
				   setDay(d_day);
				   startActivity(new Intent(Movie.this, Main.class));			 
			  }				
		  });  
		  alertDlg.show();
	   
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
   
   public boolean onKeyDown(int keyCode, KeyEvent event) {
	      
       return true;
      
   }
}


