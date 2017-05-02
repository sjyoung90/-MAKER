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
import android.view.View;

public class Menu extends Activity {
    /** Called when the activity is first created. */
    
	DBManager dManager;
	DBManager2 dManager2;
	DBManager3 dManager3;
	SQLiteDatabase db, db2, db3;
	private static final String DATABASE_NAME = "character.db";
	private static final String DATABASE_NAME2 = "calendar.db";
	private static final String DATABASE_NAME3 = "event.db";
	String d_pic;

	    @Override    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                
        setContentView(R.layout.menu);
          
        Intent intent = getIntent();
        boolean end = intent.getBooleanExtra("end", false);
        if(end == false) startActivity(new Intent(Menu.this, Splash.class));
        
        if (db == null) { 
        	db = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
		}
        
        if (db2 == null) { 
            db2 = openOrCreateDatabase(DATABASE_NAME2, Context.MODE_PRIVATE, null);
        }
        
        if (db3 == null) { 
            db3 = openOrCreateDatabase(DATABASE_NAME3, Context.MODE_PRIVATE, null);
        }
        
        dManager = new DBManager(this, "character.db", null, 1);
        db = dManager.getWritableDatabase();
        db.close();        
        
        dManager2 = new DBManager2(this, "calendar.db", null, 1);
        db2 = dManager2.getWritableDatabase();
        db2.close();
        
        dManager3 = new DBManager3(this, "event.db", null, 1);
        db3 = dManager3.getWritableDatabase();
        db3.close();
    }
    public void onButton1(View v){
    	
    	onOutput();
    	
    	if(d_pic != null){    		
    		//Menu.this.showDialog(first_DIALOG);    	
    		AlertDialog.Builder alertDlg = new AlertDialog.Builder(Menu.this) ;   
			alertDlg.setTitle("�˸�");
			alertDlg.setMessage("\n" + "�̹� ������ ĳ���Ͱ� �ֽ��ϴ�." + "\n" + "ĳ���͸� �����Ͻðڽ��ϱ�?" + "\n");
			alertDlg.setPositiveButton("��", new DialogInterface.OnClickListener() {
     		
     			public void onClick(DialogInterface dialog, int which) {         					
     				db = dManager.getWritableDatabase();
		    		db.delete("character", null, null);
		    		db.close();
		    						    		
		    		db2 = dManager2.getWritableDatabase();
		    		db2.delete("calendar", null, null);
		    		db2.close();
		    		
		    		db3 = dManager3.getWritableDatabase();
		    		db3.delete("event", null, null);				   
		    		db3.close();
		    		    
		    		d_pic = null;     				
     			}   
     		});
      
			alertDlg.setNegativeButton("�ƴϿ�", new DialogInterface.OnClickListener() {
     			public void onClick(DialogInterface dialog, int which) {
   
     				dialog.cancel();
     			}
     		});
     		
			alertDlg.show();        
    	}
    	else if(d_pic == null){
    		startActivity(new Intent(Menu.this, SelectCharacter.class));     		
    	}
    	  	
    }
    
    public void onButton2(View v){
    	
    	onOutput();
    	
    	if(d_pic != null){    		
    		startActivity(new Intent(Menu.this, Main.class));     	
    	}    	
    	else if(d_pic == null){    		
    		//Menu.this.showDialog(second_DIALOG);
    		AlertDialog.Builder alertDlg = new AlertDialog.Builder(Menu.this) ;   
			alertDlg.setTitle("�˸�");
			alertDlg.setMessage("\n" + "������ ĳ���Ͱ� �����ϴ�." + "\n" + "'�����ϱ�'���� ĳ���͸� ���弼��." + "\n");
			alertDlg.setPositiveButton("Ȯ��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			
			alertDlg.show(); 
    	}
    	
    }
    public void onButton3(View v){    	
    //	Toast.makeText(Menu.this, "���Ӽ���" , Toast.LENGTH_SHORT).show();
    	startActivity(new Intent(Menu.this, GameInfo.class));     	
    }
    public void onButton4(View v){
    	moveTaskToBack(true);
    	finish(); //Activity ����
    	android.os.Process.killProcess(android.os.Process.myPid()); //�޸� ���¿��� ������ ���Ḧ ���Ѵ�.    	   
    }
    
    public void onOutput(){ 
	  
    	  db = dManager.getWritableDatabase();   
    	  String sql = "SELECT distinct pic FROM character;";
    	  
    	  try{   
    		  
    	   Cursor cur = db.rawQuery(sql, null);
    	   
    	   while(cur.moveToNext()){
    		   d_pic = cur.getString(cur.getColumnIndex("pic"));
    	   }    	   
    	   
    	 }catch (SQLException se) {
    	   // TODO: handle exception    	  
    	 }     	
          db.close();  
    }
    /*
    @Override
    public Dialog onCreateDialog(int dialogId) {
    	Dialog dialog = null;
    	switch (dialogId) {
	    	case first_DIALOG :
				CustomDialog.Builder customBuilder = new
				CustomDialog.Builder(Menu.this);
				customBuilder.setTitle("�˸�")
					.setMessage("\n" + "�̹� ������ ĳ���Ͱ� �ֽ��ϴ�." + "\n" + "ĳ���͸� �����Ͻðڽ��ϱ�?" + "\n")
					.setNegativeButton("�ƴϿ�", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Menu.this.dismissDialog(first_DIALOG);
						}
					})  
					.setPositiveButton("��", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							
							db = dManager.getWritableDatabase();
				    		db.delete("character", null, null);
				    		db.close();
				    						    		
				    		db2 = dManager2.getWritableDatabase();
				    		db2.delete("calendar", null, null);
				    		db2.close();
				    		
				    		db3 = dManager3.getWritableDatabase();
				    		db3.delete("event", null, null);				   
				    		db3.close();
				    		    
				    		d_pic = null;
						}
					});  
	            dialog = customBuilder.create();
	    		break;
	    		
	    		
	    	case second_DIALOG :
	    		CustomDialog.Builder customBuilder2 = new
				CustomDialog.Builder(Menu.this);
				customBuilder2.setTitle("�˸�")
					.setMessage("\n" + "������ ĳ���Ͱ� �����ϴ�." + "\n" + "'�����ϱ�'���� ĳ���͸� ���弼��." + "\n")
					.setPositiveButton("Ȯ��", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
	            dialog = customBuilder2.create();
	    		break;
    	}
    	return dialog;
    }*/
}
