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
import android.widget.ImageView;
import android.widget.TextView;

public class Information extends Activity {
    /** Called when the activity is first created. */
    
 DBManager dManager;
 DBManager2 dManager2;
 SQLiteDatabase db, db2;
 private static final String DATABASE_NAME = "character.db";
 private static final String DATABASE_NAME2 = "calendar.db";
 
 ImageView character;
 TextView tv_name, tv_univ, tv_major, tv_stage;
 String d_name, d_univ, d_major, d_stage, d_pic;
 
    @Override    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                

        setContentView(R.layout.information);
        
        character = (ImageView)findViewById(R.id.character);
        
        tv_name = (TextView)findViewById(R.id.name);
        tv_univ = (TextView)findViewById(R.id.univ);
        tv_major = (TextView)findViewById(R.id.major);
        tv_stage = (TextView)findViewById(R.id.stage);
             
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
                
        getValue();
        
        if(d_pic.equalsIgnoreCase("a")){ character.setImageResource(R.drawable.character1); }
        else if(d_pic.equalsIgnoreCase("b")){ character.setImageResource(R.drawable.character2); }        
        
        tv_name.setText("이름 : " + d_name + "\n");
        tv_univ.setText("학교 : " + d_univ + "대학교" + "\n" );
        tv_major.setText("전공 : " + d_major + "\n");
        tv_stage.setText("학년 : " + d_stage + "학년" + "\n");
        
    }

    
    public void getValue(){
        
        db = dManager.getWritableDatabase();   
        String sql = "SELECT distinct name, university, major, pic, stage FROM character;";

          try{   
           
           Cursor cur = db.rawQuery(sql, null);
           
           while(cur.moveToNext()){
           
            d_name = cur.getString(cur.getColumnIndex("name"));
            d_univ = cur.getString(cur.getColumnIndex("university"));
            d_major = cur.getString(cur.getColumnIndex("major"));
            d_pic = cur.getString(cur.getColumnIndex("pic"));
            d_stage = cur.getString(cur.getColumnIndex("stage"));
   
           }       
           
         }catch (SQLException se) {
           // TODO: handle exception      
         }   
              db.close();     
         
      }    
    
    public void onMain(View v){
    	
     startActivity(new Intent(Information.this, Main.class));     
     
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	      
        return true;
       
    }
}



