package com.android.progBar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Calendar extends Activity {
    /** Called when the activity is first created. */
       
 AlertDialog.Builder builder;
 AlertDialog alertDialog;
 DBManager dManager;
 DBManager2 dManager2;
 DBManager3 dManager3;
 SQLiteDatabase db, db2, db3;
 TextView month;
 int stage_v, knowledge_v;
 private static final String DATABASE_NAME = "character.db";
 private static final String DATABASE_NAME2 = "calendar.db";
 private static final String DATABASE_NAME3 = "event.db";
 private static final int first_DIALOG = 0;
 private static final int EXIT_DIALOG = 1;
 ImageView date1, date2, date3, date4, date5, date6, date7, date8, date9, date10,
  date11, date12, date13, date14, date15, date16, date17, date18, date19, date20,
  date21, date22, date23, date24, date25, date26, date27, date28, date29, date30;
 ImageView activity1, activity2, activity3, activity4, activity5, activity6, activity7, activity8;
 int count = 1, d_month; 
 String d_start, gameOrder = "", d_stage, d_strength, d_knowledge, d_order;
 CountDownTimer timer;
 static boolean stageUp = false;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                
        
        setContentView(R.layout.calendar);        
          
        timer = new CountDownTimer(1 * 500, 1000) {
        	@Override
			public void onTick(long millisUntilFinished) {	}
  
			@Override
			public void onFinish() {					
				Calendar.this.showDialog(first_DIALOG);						
			}			
        };
        
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
           
        month = (TextView)findViewById(R.id.textView1);
                     
        getDate();
        getValue();  
                     
        if(d_start.equalsIgnoreCase("1") && d_order.equalsIgnoreCase("0")){	 	//월(month)증가        
	         setMonth(d_month);
	         getDate();
	    }           
        if(d_month > 12){			//12월이 지나면 1월로 set
        	setMonth();
        	getDate();        
        }        
        if((d_month == 3) && (d_start.equalsIgnoreCase("1"))){			//3월마다 학년(stage)증가        	
        	
        	if(stage_v == 1 || stage_v == 2 || stage_v == 3) stageUp = true;
        	
        	stage_v = stage_v + 1;
        	setStage(stage_v);
        	resetEvent();        	
        }     
        
        getDate();
        month.setText("'" + d_month + "월'");
        
        date1 = (ImageView)findViewById(R.id.date1); date2 = (ImageView)findViewById(R.id.date2);
        date3 = (ImageView)findViewById(R.id.date3); date4 = (ImageView)findViewById(R.id.date4);
        date5 = (ImageView)findViewById(R.id.date5); date6 = (ImageView)findViewById(R.id.date6);
        date7 = (ImageView)findViewById(R.id.date7); date8 = (ImageView)findViewById(R.id.date8);
        date9 = (ImageView)findViewById(R.id.date9); date10 = (ImageView)findViewById(R.id.date10);
        date11 = (ImageView)findViewById(R.id.date11); date12 = (ImageView)findViewById(R.id.date12);
        date13 = (ImageView)findViewById(R.id.date13); date14 = (ImageView)findViewById(R.id.date14);
        date15 = (ImageView)findViewById(R.id.date15); date16 = (ImageView)findViewById(R.id.date16);
        date17 = (ImageView)findViewById(R.id.date17); date18 = (ImageView)findViewById(R.id.date18);
        date19 = (ImageView)findViewById(R.id.date19); date20 = (ImageView)findViewById(R.id.date20);
        date21 = (ImageView)findViewById(R.id.date21); date22 = (ImageView)findViewById(R.id.date22);
        date23 = (ImageView)findViewById(R.id.date23); date24 = (ImageView)findViewById(R.id.date24);
        date25 = (ImageView)findViewById(R.id.date25); date26 = (ImageView)findViewById(R.id.date26);
        date27 = (ImageView)findViewById(R.id.date27); date28 = (ImageView)findViewById(R.id.date28);
        date29 = (ImageView)findViewById(R.id.date29); date30 = (ImageView)findViewById(R.id.date30);
       
        activity1 = (ImageView)findViewById(R.id.imageView1); activity2 = (ImageView)findViewById(R.id.imageView2);
        activity3 = (ImageView)findViewById(R.id.imageView3); activity4 = (ImageView)findViewById(R.id.imageView4);
        activity5 = (ImageView)findViewById(R.id.imageView5); activity6 = (ImageView)findViewById(R.id.imageView6);
        activity7 = (ImageView)findViewById(R.id.imageView7); activity8 = (ImageView)findViewById(R.id.imageView8);
        
        if(d_start.equalsIgnoreCase("2")){
        	date1.setImageResource(R.drawable.x); date2.setImageResource(R.drawable.x);
        	date3.setImageResource(R.drawable.x); date4.setImageResource(R.drawable.x);
        	date5.setImageResource(R.drawable.x); date6.setImageResource(R.drawable.x);
        	date7.setImageResource(R.drawable.x); date8.setImageResource(R.drawable.x);
        	date9.setImageResource(R.drawable.x); date10.setImageResource(R.drawable.x);
        	date11.setImageResource(R.drawable.x); date12.setImageResource(R.drawable.x);
        	date13.setImageResource(R.drawable.x); date14.setImageResource(R.drawable.x);
        	date15.setImageResource(R.drawable.x);
        }
               
        Calendar.this.showDialog(first_DIALOG);       
        

    }
    
    public void setStage(int s){ //달력다녀와야 학년증가
    	
    	String str_stage = String.valueOf(s);
    	String sql = "";	    
	    db = dManager.getWritableDatabase(); 	    
	    
	    sql = "update character set stage = '" + str_stage + "';";		   	
			 
		db.execSQL(sql);
		db.close();
    }
    
    public void resetEvent(){ //메인에서 이벤트 중복되는거 피하기위해서
    	
    	db3 = dManager3.getWritableDatabase();
		String sql = "update event set exe = '0';";				    		
		db3.execSQL(sql);
		db3.close();   
		
    }
    
    public void getValue(){ //캐릭터디비에서 가져오는것   	
    	
    	db = dManager.getWritableDatabase();  	
    	String sql = "SELECT distinct stage, strength, knowledge FROM character;";

      try{   
       
       Cursor cur = db.rawQuery(sql, null);
       
       while(cur.moveToNext()){
       
        d_stage = cur.getString(cur.getColumnIndex("stage"));
        d_strength = cur.getString(cur.getColumnIndex("strength"));
        d_knowledge = cur.getString(cur.getColumnIndex("knowledge"));
             
       }       
       
     }catch (SQLException se) {
       // TODO: handle exception      
     }   
          db.close();
          
      stage_v = Integer.parseInt(d_stage);
      knowledge_v = Integer.parseInt(d_knowledge);
    	
    }
    
    public void setMonth(){ //1년지나고 달력날짜세팅
    	
    	String sql = "";	    
	    db2 = dManager2.getWritableDatabase(); 	    
	    
	    sql = "update calendar set day = '1';";		   	
			 
		db2.execSQL(sql);
		db2.close();
    	
    }
    
    public void setMonth(int m){ //달을 가져와서 디비에 저장
        
        if(m == 12){
         m = 1;
        }
        else m++;
        
        String sql = "";     
        db2 = dManager2.getWritableDatabase();      
        
        sql = "update calendar set month = '" + m + "';";      
       
        db2.execSQL(sql);
        db2.close();
        
       }
    
    public void setImage(int cnt, int tmp){ //3일씩 달력이미지 변경
           
     cnt = count;
     
     switch(cnt){
     
     case 1:  
    	 if(d_start.equalsIgnoreCase("1")){  // 첫주/둘째주 지난건지 아닌지 구분.  		 
    		 date1.setImageResource(tmp);
    		 date2.setImageResource(tmp);
    		 date3.setImageResource(tmp);
    		 count++;
    		 }
    	 else {     		 
    		 date16.setImageResource(tmp);
    		 date17.setImageResource(tmp);
    		 date18.setImageResource(tmp);
    		 count++;
    		 }
      break;     
     case 2:
    	 if(d_start.equalsIgnoreCase("1")){
    		 date4.setImageResource(tmp);
    		 date5.setImageResource(tmp);
    		 date6.setImageResource(tmp);
    		 count++;
    	 }
    	 else { 
    		 date19.setImageResource(tmp);
    		 date20.setImageResource(tmp);
    		 date21.setImageResource(tmp);
    		 count++;
    		 }
      break;      
     case 3:
    	 if(d_start.equalsIgnoreCase("1")){
    		 date7.setImageResource(tmp);
    		 date8.setImageResource(tmp);
    	     date9.setImageResource(tmp);
    	     count++;
    	 }
    	 else { 
    		 date22.setImageResource(tmp);
    		 date23.setImageResource(tmp);
    		 date24.setImageResource(tmp);
    		 count++;
    		 }
      
      break;        
     case 4:
    	 if(d_start.equalsIgnoreCase("1")){
    		 date10.setImageResource(tmp);
    		 date11.setImageResource(tmp);
    		 date12.setImageResource(tmp);      
    		 count++;
    	 }
    	 else { 
    		 date25.setImageResource(tmp);
    		 date26.setImageResource(tmp);
    		 date27.setImageResource(tmp);
    		 count++;
    		 }
      break;        
     case 5:
    	 if(d_start.equalsIgnoreCase("1")){
    		 date13.setImageResource(tmp);
    		 date14.setImageResource(tmp);
    		 date15.setImageResource(tmp);
    	     count++;
    	     d_start = "2";
    	     putDate(d_month, d_start); 
    		 putOrder(gameOrder); //스케줄순서
    		 startActivity(new Intent(Calendar.this, Main.class));
    	 }
    	 else { 
    		 date28.setImageResource(tmp);
    		 date29.setImageResource(tmp);
    		 date30.setImageResource(tmp);
    		 count++;    		    		 
    		 d_start = "1";	
    		 putDate(d_month, d_start);    		 
    		 putOrder(gameOrder);
    		 startActivity(new Intent(Calendar.this, Main.class));  
    		 }
      
      break;        
     	
      }
    }
    
    public void getDate(){
    	
    	 db2 = dManager2.getWritableDatabase();   
   	  	 String sql = "SELECT order1, start, month FROM calendar;";

     	  try{   
     		  
     	   Cursor cur = db2.rawQuery(sql, null);
     	   
     	   while(cur.moveToNext()){
     	   
     		   d_order = cur.getString(cur.getColumnIndex("order1"));
     		   d_start = cur.getString(cur.getColumnIndex("start"));
     		   d_month = cur.getInt(cur.getColumnIndex("month"));     		   
     		  
     	   }    	   
     	   
     	 }catch (SQLException se) {
     	   // TODO: handle exception    	  
     	 }   
           db2.close();          
    }
    
    public void putDate(int date, String s){ //스케쥴짜고 DB에 저장
    	
    	String sql = "", sql2 = "";
		db2 = dManager2.getWritableDatabase();    	
   		
		sql = "update calendar set month = '" + date + "';";
		sql2 = "update calendar set start = '" + s + "';";
		 
		db2.execSQL(sql);
		db2.execSQL(sql2);
		
		db2.close();    	
    	
    }
    
    public void putOrder(String order){ //스케쥴순서 DB에 저장
    	
    	String sql = "";
		db2 = dManager2.getWritableDatabase();    	
   		
		sql = "update calendar set order1 = '" + order + "';";
		 
		db2.execSQL(sql);    		    	
		db2.close();
    	
    }   
    
    public void onImageView1(View v){	//과외활동
    	
    	if(d_strength.equalsIgnoreCase("0")){       	
        	Toast.makeText(Calendar.this, "체력이 부족해 활동을 할 수 없습니다.", Toast.LENGTH_SHORT).show();
    	}	    	
    	else{
    		
    		if(knowledge_v < 50){
    			Toast.makeText(Calendar.this, "지식이 부족해 과외를 할 수 없습니다.", Toast.LENGTH_SHORT).show();
    		}
    		else if(knowledge_v >= 50){
    			gameOrder += "a"; // 게임순서 저장
    			timer.start();
    			int temp = getResources().getIdentifier("aaaa", "drawable", "com.android.progBar"); //과외그림삽입
    			//Toast.makeText(Calendar.this, "과외", Toast.LENGTH_SHORT).show();
    			alertDialog.cancel();
    			setImage(count, temp);
    		}
    	}
    }
    
    public void onImageView2(View v){	//패티쌓기
    	
    	if(d_strength.equalsIgnoreCase("0")){       	
        	Toast.makeText(Calendar.this, "체력이 부족해 활동을 할 수 없습니다.", Toast.LENGTH_SHORT).show();
    	}	
    	
    	else{
    	gameOrder += "b";
    	timer.start();
    	int temp = getResources().getIdentifier("bbbb", "drawable", "com.android.progBar");
    	//Toast.makeText(Calendar.this, "패티", Toast.LENGTH_SHORT).show();
    	alertDialog.cancel();
    	setImage(count,temp);
    	}
    }
    
    public void onImageView3(View v){	//고기굽기
    	if(d_strength.equalsIgnoreCase("0")){       	
        	Toast.makeText(Calendar.this, "체력이 부족해 활동을 할 수 없습니다.", Toast.LENGTH_SHORT).show();
    	}	
    	
    	else{
    	gameOrder += "c";
    	timer.start();
    	int temp = getResources().getIdentifier("cccc", "drawable", "com.android.progBar");
    	//Toast.makeText(Calendar.this, "고기", Toast.LENGTH_SHORT).show();
    	alertDialog.cancel();
    	setImage(count,temp);
    	}
    }
    
    public void onImageView4(View v){	//팝콘
    	if(d_strength.equalsIgnoreCase("0")){       	
        	Toast.makeText(Calendar.this, "체력이 부족해 활동을 할 수 없습니다.", Toast.LENGTH_SHORT).show();
    	}	
    	
    	else{
    	gameOrder += "d";
    	timer.start();
        int temp = getResources().getIdentifier("dddd", "drawable", "com.android.progBar");
        //Toast.makeText(Calendar.this, "팝콘", Toast.LENGTH_SHORT).show();
        alertDialog.cancel();
        setImage(count,temp);
    	}
    }
    
    public void onImageView5(View v){	//공부
    	if(d_strength.equalsIgnoreCase("0")){       	
        	Toast.makeText(Calendar.this, "체력이 부족해 활동을 할 수 없습니다.", Toast.LENGTH_SHORT).show();
    	}	
    	
    	else{
    	gameOrder += "e";
    	timer.start();
        int temp = getResources().getIdentifier("eeee", "drawable", "com.android.progBar");
       // Toast.makeText(Calendar.this, "공부", Toast.LENGTH_SHORT).show();
        alertDialog.cancel();
        setImage(count,temp);
    	}
    }
    
    public void onImageView6(View v){	//데이트(빼뺴로)
    	if(d_strength.equalsIgnoreCase("0")){       	
        	Toast.makeText(Calendar.this, "체력이 부족해 활동을 할 수 없습니다.", Toast.LENGTH_SHORT).show();
    	}	
    	
    	else{
    	gameOrder += "f";
    	timer.start();
        int temp = getResources().getIdentifier("ffff", "drawable", "com.android.progBar");
       // Toast.makeText(Calendar.this, "데이트", Toast.LENGTH_SHORT).show();
        alertDialog.cancel();
        setImage(count,temp);
    	}
    }
    
    public void onImageView7(View v){	//여가(영화&쇼핑)
    	gameOrder += "g";
    	timer.start();
        int temp = getResources().getIdentifier("gggg", "drawable", "com.android.progBar");
       // Toast.makeText(Calendar.this, "영화, 쇼핑", Toast.LENGTH_SHORT).show();
        alertDialog.cancel();
        setImage(count,temp);
    } 
    
    public void onImageView8(View v){	//여가(집에서휴식)
    	gameOrder += "h";
    	timer.start();
        int temp = getResources().getIdentifier("hhhh", "drawable", "com.android.progBar");
      //  Toast.makeText(Calendar.this, "집에서 휴식", Toast.LENGTH_SHORT).show();
        alertDialog.cancel();
        setImage(count,temp);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) { //BACK버튼 못하게 하는 함수
        switch(keyCode){
        case KeyEvent.KEYCODE_BACK:
         Calendar.this.showDialog(EXIT_DIALOG);
         break;
        }
        return true;
       }

    @Override
    protected Dialog onCreateDialog(int id) { //BACK버튼 못하게 하는 함수
        Dialog dialog = null;
        switch(id) {
            case first_DIALOG:
                dialog = getCustomDialog1();
                break;
            case EXIT_DIALOG :
    CustomDialog.Builder customBuilder = new
    CustomDialog.Builder(Calendar.this);
    customBuilder.setTitle("경고")
     .setMessage("\n" + "스케줄을 짜기 전엔 돌아갈 수 없습니다." + "\n")
     .setNegativeButton("확인", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int which) {
       Calendar.this
       .dismissDialog(EXIT_DIALOG);
       Calendar.this.showDialog(first_DIALOG);
      }
     });
             dialog = customBuilder.create();
       break;
        }
        return dialog;
    }
    
    private Dialog getCustomDialog1() { //스케쥴 아이콘 다이얼로그
        
        
        Context mContext = Calendar.this;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.custom_dialog1,(ViewGroup) findViewById(R.id.layout_root));
             
        Display display = ((WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
  
        int width = display.getWidth();  //크기에맞게
        int height = display.getHeight();   //크기에맞게
  
        layout.setMinimumWidth(width);
        layout.setMinimumHeight(height);  
          
        builder = new AlertDialog.Builder(mContext);
        builder.setView(layout);
     
        alertDialog = builder.create();
        
        return alertDialog;
    }
     
}
