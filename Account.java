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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Account extends Activity {
    /** Called when the activity is first created. */

	DBManager dManager;
	DBManager3 dManager3;
	SQLiteDatabase db, db3;
	private static final String DATABASE_NAME = "character.db";
	private static final String DATABASE_NAME3 = "event.db";
	String d_name, d_money;
	int d_nnonpayment,d_nonpayment;
	TextView nnonpayment, nonpayment, money;
	EditText input_nonpayment;	
	int m_v;
	int int_input = 0;
	boolean event; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                
        setContentView(R.layout.account);        
             
        nnonpayment = (TextView)findViewById(R.id.nnonpayment1);
        nonpayment = (TextView)findViewById(R.id.nonpayment1);
        money = (TextView)findViewById(R.id.money);        
        input_nonpayment = (EditText)findViewById(R.id.editText1);
        
        Intent intent = getIntent();
        event = intent.getBooleanExtra("event", false);
                
        if (db == null) { 
        	db = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
		}
        if (db3 == null) { 
            db3 = openOrCreateDatabase(DATABASE_NAME3, Context.MODE_PRIVATE, null);
        }        
       
        dManager = new DBManager(this, "character.db", null, 1);       
        db.close();
        
        dManager3 = new DBManager3(this, "event.db", null, 1);        
        db3.close();
        
        getValue();        
        nnonpayment.setText("-" + d_nnonpayment + "만원  ");
        nonpayment.setText(d_nonpayment + "만원  ");
        money.setText(d_money + "만원");                
        
    }
    
    public void setValue(int m, int n, int nn){
        
        db = dManager.getWritableDatabase();
       
        String sql = null, sql6 = null, sql7 = null;
        String m_v2 = String.valueOf(m);
          
        sql = "update character set money='"+ m_v2 +"';";
        sql6 = "update character set nonpayment='"+ n +"';";
        sql7 = "update character set nnonpayment='"+ nn +"';";
          
         db.execSQL(sql);
         db.execSQL(sql6);
         db.execSQL(sql7);
         
         db.close();
      }
    
    
    public void getValue(){
    	
    	  db = dManager.getWritableDatabase();   
    	  String sql = "SELECT distinct money, nonpayment, nnonpayment FROM character;";
      	  try{   
      		  
      	   Cursor cur = db.rawQuery(sql, null);
      	   
      	   while(cur.moveToNext()){
      	   
      		   d_money = cur.getString(cur.getColumnIndex("money"));
      		   d_nonpayment = cur.getInt(cur.getColumnIndex("nonpayment"));
      		   d_nnonpayment = cur.getInt(cur.getColumnIndex("nnonpayment"));
      		     		  
      	   }    	   
      	   
      	 }catch (SQLException se) {
      	   // TODO: handle exception    	  
      	 }   
            db.close();
            
        m_v = Integer.parseInt(d_money);
    }    
    
    public void onNext(View v){    	
    	
    	if(event == true){    		
    	    		
    	String input = input_nonpayment.getText().toString();
    	if(input.equalsIgnoreCase("")) Toast.makeText(Account.this, "납부할 돈을 입력하세요.", Toast.LENGTH_SHORT).show();    	
    	else 
    		{
    			int_input = Integer.parseInt(input);
    			
    	AlertDialog.Builder alertDlg = new AlertDialog.Builder(Account.this) ;   
		alertDlg.setTitle("추가납부");
		alertDlg.setMessage("\n"  + input_nonpayment.getText().toString() +"만원을 납부하시겠습니까?"  + "\n");   
		alertDlg.setPositiveButton("네", new DialogInterface.OnClickListener() {
 		
 			public void onClick(DialogInterface dialog, int which) {  					
				
 				if(m_v >= int_input && d_nonpayment >= int_input){
 				
 				m_v = m_v - int_input;				
 				d_nonpayment = d_nonpayment - int_input; 				
				d_nnonpayment = d_nnonpayment - int_input;
				
				setValue(m_v, d_nonpayment, d_nnonpayment);				
				getValue();				
				nnonpayment.setText("-" + d_nnonpayment + "만원  ");
		        nonpayment.setText(d_nonpayment + "만원  ");
		        money.setText(d_money + "만원");
		        input_nonpayment.setText("");
							
 				}
 				else{
 					 					
 					AlertDialog.Builder alertDlg = new AlertDialog.Builder(Account.this) ;   
 					alertDlg.setTitle("추가납부");
 					alertDlg.setMessage("\n"  + "돈이 부족합니다. 다시입력해주세요."  + "\n");   
 					alertDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
 			 		
 			 			public void onClick(DialogInterface dialog, int which){ 			 				
 			 				dialog.cancel();
 			 				input_nonpayment.setText("");
 			 			}
 			 		});
 					
 					alertDlg.show();
 				}         	
 			}   
 		});
  
		alertDlg.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
 			public void onClick(DialogInterface dialog, int which) {
 				dialog.cancel(); 				
 			}
 		}); 		
			alertDlg.show();
    	}    
    	}
    	
    	else if(event == false){
    		Toast.makeText(Account.this, "추가납부기간이 아닙니다.", Toast.LENGTH_SHORT).show();
    	}    
	}    
    	
    public void onMain(View v){    	
    	startActivity(new Intent(Account.this, Main.class));    	
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	      
        return true;
       
    }
    
}


