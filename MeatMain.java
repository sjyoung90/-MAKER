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
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MeatMain extends Activity {
    /** Called when the activity is first created. */
 
 DBManager dManager;
 DBManager2 dManager2;
 SQLiteDatabase db, db2;
 private static final String DATABASE_NAME = "character.db";
 private static final String DATABASE_NAME2 = "calendar.db";
 int d_day = 1;
 int value = 31;
 int g1_value = 0, g2_value = 0, g3_value = 0, g4_value = 0, g5_value = 0, g6_value = 0, g7_value = 0, g8_value = 0, g9_value = 0, g10_value = 0, g11_value = 0, g12_value = 0;
    TextView textView, textView2;
    ImageView iv, start, finish, gogi1, gogi2, gogi3, gogi4, gogi5, gogi6, gogi7, gogi8, gogi9, gogi10, gogi11, gogi12;
    CountDownTimer timer, timer_gogi1, timer_gogi2, timer_gogi3, timer_gogi4, timer_gogi5, timer_gogi6, timer_gogi7, timer_gogi8, timer_gogi9, timer_gogi10, timer_gogi11, timer_gogi12;   
    int count = 0;
    int gogi1_t, gogi2_t, gogi3_t, gogi4_t, gogi5_t, gogi6_t, gogi7_t, gogi8_t, gogi9_t, gogi10_t, gogi11_t, gogi12_t;
    int total_gogi = 0;
    Animation an;     
    int t = 0;
    String d_knowledge, d_love, d_strength, d_money, d_stage;
    int k_v, l_v, s_v, m_v; 
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meatmain);
        
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
        
        an = AnimationUtils.loadAnimation(this, R.anim.spin);        
        
        textView = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView2.setText("잘 익은 고기" + "\n" + " : " + total_gogi + "개");
        
        iv = (ImageView)findViewById(R.id.imageView1);        
        start = (ImageView)findViewById(R.id.icon);
        finish = (ImageView)findViewById(R.id.finish);
        
        gogi1 = (ImageView)findViewById(R.id.gogi1); 
        gogi1.setVisibility(View.INVISIBLE);
        gogi2 = (ImageView)findViewById(R.id.gogi2);
        gogi2.setVisibility(View.INVISIBLE);
        gogi3 = (ImageView)findViewById(R.id.gogi3);
        gogi3.setVisibility(View.INVISIBLE);
        gogi4 = (ImageView)findViewById(R.id.gogi4);
        gogi4.setVisibility(View.INVISIBLE);        
        gogi5 = (ImageView)findViewById(R.id.gogi5);
        gogi5.setVisibility(View.INVISIBLE);
        gogi6 = (ImageView)findViewById(R.id.gogi6);
        gogi6.setVisibility(View.INVISIBLE);
        gogi7 = (ImageView)findViewById(R.id.gogi7);
        gogi7.setVisibility(View.INVISIBLE);
        gogi8 = (ImageView)findViewById(R.id.gogi8);
        gogi8.setVisibility(View.INVISIBLE);
        gogi9 = (ImageView)findViewById(R.id.gogi9);
        gogi9.setVisibility(View.INVISIBLE);
        gogi10 = (ImageView)findViewById(R.id.gogi10);
        gogi10.setVisibility(View.INVISIBLE);
        gogi11 = (ImageView)findViewById(R.id.gogi11);
        gogi11.setVisibility(View.INVISIBLE);
        gogi12 = (ImageView)findViewById(R.id.gogi12);
        gogi12.setVisibility(View.INVISIBLE);

        int totaltimer = 0;
        int ttotaltimer = 0;
        
        if(d_stage.equals("1")){ //난이도
        	
        	totaltimer = 10;
        	ttotaltimer = 21;
        	an.setDuration(21000);
       	 
        }
        else if(d_stage.equals("2")){
       	 
        	totaltimer = 9;
        	ttotaltimer = 21;
        	an.setDuration(21000);
        	
        }
        else if(d_stage.equals("3")){
        	totaltimer = 7;
        	ttotaltimer = 16;
        	an.setDuration(16000);
       	 
        }
        else if(d_stage.equals("4")){
        	totaltimer = 6;
        	ttotaltimer = 16;
        	an.setDuration(16000);
        }
        
        gogi1_t = totaltimer + (int)((Math.random() * 10)); //고기마다 랜덤시간 주어짐
        gogi2_t = totaltimer + (int)((Math.random() * 10));
        gogi3_t = totaltimer + (int)((Math.random() * 10));
        gogi4_t = totaltimer + (int)((Math.random() * 10));        
        gogi5_t = totaltimer + (int)((Math.random() * 15));
        gogi6_t = totaltimer + (int)((Math.random() * 15));
        gogi7_t = totaltimer + (int)((Math.random() * 15));
        gogi8_t = totaltimer + (int)((Math.random() * 15));
        gogi9_t = totaltimer + (int)((Math.random() * 15));
        gogi10_t = totaltimer + (int)((Math.random() * 10));        
        gogi11_t = totaltimer + (int)((Math.random() * 10));
        gogi12_t = totaltimer + (int)((Math.random() * 10));      
        
        value = ttotaltimer;
        //타이머 설정
        timer = new CountDownTimer(ttotaltimer * 1000, 1000) {
         @Override
         public void onTick(long millisUntilFinished) {
        	 value--;
        	 textView.setText("Time = " + value);               
         }

         @Override
         public void onFinish() {    
        	 //Toast.makeText(MeatMain.this, "End!", Toast.LENGTH_SHORT).show();  
	   	   	   
        	 timer_gogi1.cancel(); timer_gogi2.cancel();
        	 timer_gogi3.cancel(); timer_gogi4.cancel();   
        	 timer_gogi5.cancel(); timer_gogi6.cancel(); 
        	 timer_gogi7.cancel(); timer_gogi8.cancel();
        	 timer_gogi9.cancel(); timer_gogi10.cancel();
        	 timer_gogi11.cancel(); timer_gogi12.cancel();
	    
	    int m = 0;
	    
	    if(total_gogi == 12){ //성공갯수에 따른 지수변화
	     m = 6;
	     m_v = m_v + 6;
	      s_v = s_v - 10;
	      setValue(m_v,s_v);
	    }
	    else if(total_gogi == 11){
	     m = 4;
	     m_v = m_v + 4;
	      s_v = s_v - 10; 
	      setValue(m_v,s_v);
	    }
	    else if(total_gogi < 11){
	     m = 2;
	     m_v = m_v + 2;
	      s_v = s_v - 10;
	      setValue(m_v,s_v);
	    }
	    
	    AlertDialog.Builder alertDlg = new AlertDialog.Builder(MeatMain.this) ;   
	    alertDlg.setTitle("결과");
	    alertDlg.setMessage("\n" + "잘 익은 고기 : " + total_gogi  + "개" + "\n"
	         + "돈 "+m+"만원을 받았습니다. " + "\n"
	         + "체력은 10 감소합니다. " + "\n");   
	    alertDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
	       public void onClick(DialogInterface dialog, int whichButton) {

	      //  setValue(m_v,s_v);
	        d_day = d_day + 3;
	        setDay(d_day);
	        startActivity(new Intent(MeatMain.this, Main.class));
	       }    
	      });
	      alertDlg.show() ;
	   
   }   
        };
        
   timer_gogi1 = new CountDownTimer(gogi1_t * 1000, 1000) {//해당시간 지나면 고기바꿔줌
         @Override
  
	public void onTick(long millisUntilFinished) {
          
    g1_value++;    
    
    if(g1_value < (gogi1_t-5)){} 
    else if(g1_value == (gogi1_t-5)){gogi1.setImageResource(R.drawable.meat2);}
    else if(g1_value == (gogi1_t-3)){gogi1.setImageResource(R.drawable.meat3);}
                    
   }

   @Override
   public void onFinish() {
    gogi1.setVisibility(View.INVISIBLE);
   }   
        };
        
 timer_gogi2 = new CountDownTimer(gogi2_t * 1000, 1000) { 
         @Override
   public void onTick(long millisUntilFinished) {
          
    g2_value++;    
    
    if(g2_value < (gogi2_t-5)){}
    else if(g2_value == (gogi2_t-5)){gogi2.setImageResource(R.drawable.meat2);}
    else if(g2_value == (gogi2_t-3)){gogi2.setImageResource(R.drawable.meat3);}
                    
   }
     
   @Override
   public void onFinish() {
    gogi2.setVisibility(View.INVISIBLE);
   }   
        };
        
        timer_gogi3 = new CountDownTimer(gogi3_t * 1000, 1000) {
         @Override
   public void onTick(long millisUntilFinished) {
          
    g3_value++;    
    
    if(g3_value < (gogi3_t-5)){}
    else if(g3_value == (gogi3_t-5)){gogi3.setImageResource(R.drawable.meat2);}
    else if(g3_value == (gogi3_t-4)){gogi3.setImageResource(R.drawable.meat3);}
                    
   }

   @Override
   public void onFinish() {
    gogi3.setVisibility(View.INVISIBLE);    
   }   
        };
        
        timer_gogi4 = new CountDownTimer(gogi4_t * 1000, 1000) {
         @Override
   public void onTick(long millisUntilFinished) {
          
    g4_value++;    
    
    if(g4_value < (gogi4_t-5)){}
    else if(g4_value == (gogi4_t-5)){gogi4.setImageResource(R.drawable.meat2);}
    else if(g4_value == (gogi4_t-4)){gogi4.setImageResource(R.drawable.meat3);}
                    
   }

   @Override
   public void onFinish() {
    gogi4.setVisibility(View.INVISIBLE);    
   }   
        };
        
        timer_gogi5 = new CountDownTimer(gogi5_t * 1000, 1000) {
         @Override
   public void onTick(long millisUntilFinished) {
          
    g5_value++;    
    
    if(g5_value < (gogi5_t-5)){}
    else if(g5_value == (gogi5_t-5)){gogi5.setImageResource(R.drawable.meat2);}
    else if(g5_value == (gogi5_t-4)){gogi5.setImageResource(R.drawable.meat3);}
                    
   }

   @Override
   public void onFinish() {
    gogi5.setVisibility(View.INVISIBLE);    
   }   
        };
        
        timer_gogi6 = new CountDownTimer(gogi6_t * 1000, 1000) {
         @Override
   public void onTick(long millisUntilFinished) {
          
    g6_value++;    
    
    if(g6_value < (gogi6_t-5)){}
    else if(g6_value == (gogi6_t-5)){gogi6.setImageResource(R.drawable.meat2);}
    else if(g6_value == (gogi6_t-4)){gogi6.setImageResource(R.drawable.meat3);}
                    
   }

   @Override
   public void onFinish() {
    gogi6.setVisibility(View.INVISIBLE);    
   }   
        };
        
        timer_gogi7 = new CountDownTimer(gogi7_t * 1000, 1000) {
         @Override
   public void onTick(long millisUntilFinished) {
          
    g7_value++;    
    
    if(g7_value < (gogi7_t-5)){}
    else if(g7_value == (gogi7_t-5)){gogi7.setImageResource(R.drawable.meat2);}
    else if(g7_value == (gogi7_t-3)){gogi7.setImageResource(R.drawable.meat3);}
                    
   }

   @Override
   public void onFinish() {
    gogi7.setVisibility(View.INVISIBLE);    
   }   
        };
        
        timer_gogi8 = new CountDownTimer(gogi8_t * 1000, 1000) {
         @Override
   public void onTick(long millisUntilFinished) {
          
    g8_value++;    
    
    if(g8_value < (gogi8_t-5)){}
    else if(g8_value == (gogi8_t-5)){gogi8.setImageResource(R.drawable.meat2);}
    else if(g8_value == (gogi8_t-3)){gogi8.setImageResource(R.drawable.meat3);}
                    
   }

   @Override
   public void onFinish() {
    gogi8.setVisibility(View.INVISIBLE);    
   }   
        };
                
        timer_gogi9 = new CountDownTimer(gogi9_t * 1000, 1000) {
         @Override
   public void onTick(long millisUntilFinished) {
          
    g9_value++;    
    
    if(g9_value < (gogi9_t-5)){}
    else if(g9_value == (gogi9_t-5)){gogi9.setImageResource(R.drawable.meat2);}
    else if(g9_value == (gogi9_t-3)){gogi9.setImageResource(R.drawable.meat3);}
                    
   }

   @Override
   public void onFinish() {
    gogi9.setVisibility(View.INVISIBLE);    
   }   
        };
        
        timer_gogi10 = new CountDownTimer(gogi10_t * 1000, 1000) {
         @Override
   public void onTick(long millisUntilFinished) {
          
    g10_value++;    
    
    if(g10_value < (gogi10_t-5)){}
    else if(g10_value == (gogi10_t-5)){gogi10.setImageResource(R.drawable.meat2);}
    else if(g10_value == (gogi10_t-3)){gogi10.setImageResource(R.drawable.meat3);}
                    
   }

   @Override
   public void onFinish() {
    gogi10.setVisibility(View.INVISIBLE);    
   }   
        };
        
        timer_gogi11 = new CountDownTimer(gogi11_t * 1000, 1000) {
         @Override
   public void onTick(long millisUntilFinished) {
          
    g11_value++;    
    
    if(g11_value < (gogi11_t-4)){}
    else if(g11_value == (gogi11_t-4)){gogi11.setImageResource(R.drawable.meat2);}
    else if(g11_value == (gogi11_t-1)){gogi11.setImageResource(R.drawable.meat3);}
                    
   }

   @Override
   public void onFinish() {
    gogi11.setVisibility(View.INVISIBLE);    
   }   
        };
        
        timer_gogi12 = new CountDownTimer(gogi12_t * 1000, 1000) {
         @Override
   public void onTick(long millisUntilFinished) {
          
    g12_value++;    
    
    if(g12_value < (gogi12_t-4)){}
    else if(g12_value == (gogi12_t-4)){gogi12.setImageResource(R.drawable.meat2);}
    else if(g12_value == (gogi12_t-1)){gogi12.setImageResource(R.drawable.meat3);}
                    
   }

   @Override
   public void onFinish() {
    gogi12.setVisibility(View.INVISIBLE);    
   }   
        };
               
        start.setOnClickListener(onStartListener); //시작고기
        finish.setOnClickListener(onFinishListener); //마감고기
       
		gogi1.setOnClickListener(onGoGi1Listener); //고기눌렀을때
        gogi2.setOnClickListener(onGoGi2Listener);
        gogi3.setOnClickListener(onGoGi3Listener);
        gogi4.setOnClickListener(onGoGi4Listener);        
        gogi5.setOnClickListener(onGoGi5Listener);
        gogi6.setOnClickListener(onGoGi6Listener);
        gogi7.setOnClickListener(onGoGi7Listener);
        gogi8.setOnClickListener(onGoGi8Listener);
        gogi9.setOnClickListener(onGoGi9Listener);
        gogi10.setOnClickListener(onGoGi10Listener);
        gogi11.setOnClickListener(onGoGi11Listener);
        gogi12.setOnClickListener(onGoGi12Listener);   
        
    }   
    
    public void getValue(){
     
     db = dManager.getWritableDatabase();   
     String sql = "SELECT distinct strength, money, stage FROM character;";

       try{   
        
        Cursor cur = db.rawQuery(sql, null);
        
        while(cur.moveToNext()){
       
         d_strength = cur.getString(cur.getColumnIndex("strength"));
         d_money = cur.getString(cur.getColumnIndex("money"));
         d_stage = cur.getString(cur.getColumnIndex("stage"));
               
        }        
        
      }catch (SQLException se) {
        // TODO: handle exception       
      }   
          db.close();
          
      s_v = Integer.parseInt(d_strength);
      m_v = Integer.parseInt(d_money);
  }
    
    public void setValue(int m, int s){
     
     db = dManager.getWritableDatabase();
     //'"+ s_major +"'
     String sql = null,sql2 = null;
             
     if(s < 0){s = 0;}
     
     String m_v2 = String.valueOf(m);
     String s_v2 = String.valueOf(s);
       
     sql = "update character set money='"+ m_v2 +"';";
     sql2 = "update character set strength='"+ s_v2 +"';";
       
      db.execSQL(sql);
      db.execSQL(sql2);
      
      db.close();

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
    
  View.OnClickListener onStartListener = new View.OnClickListener() {
  
  public void onClick(View v) { //생고기눌렀을대
   // TODO Auto-generated method stub
	  			t++;
       //타이머 시작
	  		 if(t == 1){
             timer.start();
             iv.startAnimation(an);
       
    gogi1.setVisibility(View.VISIBLE);  //생고기가 나타난다. 고기굽기 시작!
    timer_gogi1.start();
   
    gogi2.setVisibility(View.VISIBLE); 
    timer_gogi2.start();
   
    gogi3.setVisibility(View.VISIBLE); 
    timer_gogi3.start();
   
    gogi4.setVisibility(View.VISIBLE); 
    timer_gogi4.start();
   
    gogi5.setVisibility(View.VISIBLE); 
    timer_gogi5.start();
   
    gogi6.setVisibility(View.VISIBLE); 
    timer_gogi6.start();
   
    gogi7.setVisibility(View.VISIBLE); 
    timer_gogi7.start();
   
    gogi8.setVisibility(View.VISIBLE); 
    timer_gogi8.start();
   
    gogi9.setVisibility(View.VISIBLE); 
    timer_gogi9.start();  
   
    gogi10.setVisibility(View.VISIBLE); 
    timer_gogi10.start();
   
    gogi11.setVisibility(View.VISIBLE); 
    timer_gogi11.start();   
   
    gogi12.setVisibility(View.VISIBLE); 
    timer_gogi12.start();
	  		 }
	  		 else {}
   }          
 };
 
 View.OnClickListener onFinishListener = new View.OnClickListener() {
  
  public void onClick(View v) { //완성된 고기
   // TODO Auto-generated method stub
        
	  timer.cancel();
	  iv.clearAnimation();
    
    timer_gogi1.cancel(); timer_gogi2.cancel();
    timer_gogi3.cancel(); timer_gogi4.cancel();   
    timer_gogi5.cancel(); timer_gogi6.cancel(); 
    timer_gogi7.cancel(); timer_gogi8.cancel();
    timer_gogi9.cancel(); timer_gogi10.cancel();
    timer_gogi11.cancel(); timer_gogi12.cancel();
    
    int m = 0;
    
    if(total_gogi == 12){
     m = 6;
     m_v = m_v + 6;
      s_v = s_v - 10;
      setValue(m_v,s_v);
    }
    else if(total_gogi == 11){
     m = 4;
     m_v = m_v + 4;
      s_v = s_v - 10; 
      setValue(m_v,s_v);
    }
    else if(total_gogi < 11){
     m = 2;
     m_v = m_v + 2;
      s_v = s_v - 10;
      setValue(m_v,s_v);
    }
    
    AlertDialog.Builder alertDlg = new AlertDialog.Builder(MeatMain.this) ;   
    alertDlg.setTitle("결과");
    alertDlg.setMessage("\n" + "잘 익은 고기 : " + total_gogi  + "개" + "\n"
         + "돈 "+m+"만원을 받았습니다. " + "\n"
         + "체력은 10 감소합니다. " + "\n");   
    alertDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
       public void onClick(DialogInterface dialog, int whichButton) {

      //  setValue(m_v,s_v);
        d_day = d_day + 3;
        setDay(d_day);
        startActivity(new Intent(MeatMain.this, Main.class));
       }    
      });
      alertDlg.show() ;
   }          
 };
    
 View.OnClickListener onGoGi1Listener = new View.OnClickListener() { //클릭했을때
  
  public void onClick(View v) {
   // TODO Auto-generated method stub   
 
   if(g1_value < (gogi1_t-5)){
    gogi1.setVisibility(View.INVISIBLE); //덜익은거   
   }
   else if(g1_value >= (gogi1_t-5) && g1_value < (gogi1_t-3)){
    total_gogi++;
    textView2.setText("잘 익은 고기" + "\n" + " : " + total_gogi + "개"); //완성된것을 계속 써줘야하니까
    gogi1.setVisibility(View.INVISIBLE); //완성
   }
   else if(g1_value >= (gogi1_t-3) && g1_value < (gogi1_t)){
    gogi1.setVisibility(View.INVISIBLE); //탄거
   }
  }
 };
 
 View.OnClickListener onGoGi2Listener = new View.OnClickListener() {
  
  public void onClick(View v) {
   // TODO Auto-generated method stub   
 
   if(g2_value < (gogi2_t-5)){
    gogi2.setVisibility(View.INVISIBLE); //덜익은거   
   }
   else if(g2_value >= (gogi2_t-5) && g2_value < (gogi2_t-3)){
    total_gogi++;
    textView2.setText("잘 익은 고기" + "\n" + " : " + total_gogi + "개");
    gogi2.setVisibility(View.INVISIBLE); //완성
   }
   else if(g2_value >= (gogi2_t-3) && g2_value < (gogi2_t)){
    gogi2.setVisibility(View.INVISIBLE); //탄거
   }
  }
 };     
 
 View.OnClickListener onGoGi3Listener = new View.OnClickListener() {
  
  public void onClick(View v) {
   // TODO Auto-generated method stub   
 
   if(g3_value < (gogi3_t-5)){
    gogi3.setVisibility(View.INVISIBLE); //덜익은거   
   }
   else if(g3_value >= (gogi3_t-5) && g3_value < (gogi3_t-4)){
    total_gogi++;
    textView2.setText("잘 익은 고기" + "\n" + " : " + total_gogi + "개");
    gogi3.setVisibility(View.INVISIBLE); //완성
   }
   else if(g3_value >= (gogi3_t-4) && g3_value < (gogi3_t)){
    gogi3.setVisibility(View.INVISIBLE); //탄거
   }
  }
 };
 
 View.OnClickListener onGoGi4Listener = new View.OnClickListener() {
  
  public void onClick(View v) {
   // TODO Auto-generated method stub   
 
   if(g4_value < (gogi4_t-5)){
    gogi4.setVisibility(View.INVISIBLE); //덜익은거   
   }
   else if(g4_value >= (gogi4_t-5) && g4_value < (gogi4_t-4)){
    total_gogi++;
    textView2.setText("잘 익은 고기" + "\n" + " : " + total_gogi + "개");
    gogi4.setVisibility(View.INVISIBLE); //완성
   }
   else if(g4_value >= (gogi4_t-4) && g4_value < (gogi4_t)){
    gogi4.setVisibility(View.INVISIBLE); //탄거
   }
  }
 };
 
 View.OnClickListener onGoGi5Listener = new View.OnClickListener() {
  
  public void onClick(View v) {
   // TODO Auto-generated method stub   
 
   if(g5_value < (gogi5_t-5)){
    gogi5.setVisibility(View.INVISIBLE); //덜익은거   
   }
   else if(g5_value >= (gogi5_t-5) && g5_value < (gogi5_t-4)){
    total_gogi++;
    textView2.setText("잘 익은 고기" + "\n" + " : " + total_gogi + "개");
    gogi5.setVisibility(View.INVISIBLE); //완성
   }
   else if(g5_value >= (gogi5_t-4) && g5_value < (gogi5_t)){
    gogi5.setVisibility(View.INVISIBLE); //탄거
   }
  }
 };
 
 View.OnClickListener onGoGi6Listener = new View.OnClickListener() {
  
  public void onClick(View v) {
   // TODO Auto-generated method stub   
 
   if(g6_value < (gogi6_t-5)){
    gogi6.setVisibility(View.INVISIBLE); //덜익은거   
   }
   else if(g6_value >= (gogi6_t-5) && g6_value < (gogi6_t-4)){
    total_gogi++;
    textView2.setText("잘 익은 고기" + "\n" + " : " + total_gogi + "개");
    gogi6.setVisibility(View.INVISIBLE); //완성
   }
   else if(g6_value >= (gogi6_t-4) && g6_value < (gogi6_t)){
    gogi6.setVisibility(View.INVISIBLE); //탄거
   }
  }
 };
 
 View.OnClickListener onGoGi7Listener = new View.OnClickListener() {
  
  public void onClick(View v) {
   // TODO Auto-generated method stub   
 
   if(g7_value < (gogi7_t-5)){
    gogi7.setVisibility(View.INVISIBLE); //덜익은거   
   }
   else if(g7_value >= (gogi7_t-5) && g7_value < (gogi7_t-4)){
    total_gogi++;
    textView2.setText("잘 익은 고기" + "\n" + " : " + total_gogi + "개");
    gogi7.setVisibility(View.INVISIBLE); //완성
   }
   else if(g7_value >= (gogi7_t-4) && g7_value < (gogi7_t)){
    gogi7.setVisibility(View.INVISIBLE); //탄거
   }
  }
 };
 
 View.OnClickListener onGoGi8Listener = new View.OnClickListener() {
  
  public void onClick(View v) {
   // TODO Auto-generated method stub   
 
   if(g8_value < (gogi8_t-5)){
    gogi8.setVisibility(View.INVISIBLE); //덜익은거   
   }
   else if(g8_value >= (gogi8_t-5) && g8_value < (gogi8_t-4)){
    total_gogi++;
    textView2.setText("잘 익은 고기" + "\n" + " : " + total_gogi + "개");
    gogi8.setVisibility(View.INVISIBLE); //완성
   }
   else if(g8_value >= (gogi8_t-4) && g8_value < (gogi8_t)){
    gogi8.setVisibility(View.INVISIBLE); //탄거
   }
  }
 };
 
 View.OnClickListener onGoGi9Listener = new View.OnClickListener() {
  
  public void onClick(View v) {
   // TODO Auto-generated method stub   
 
   if(g9_value < (gogi9_t-5)){
    gogi9.setVisibility(View.INVISIBLE); //덜익은거   
   }
   else if(g9_value >= (gogi9_t-5) && g9_value < (gogi9_t-3)){
    total_gogi++;
    textView2.setText("잘 익은 고기" + "\n" + " : " + total_gogi + "개");
    gogi9.setVisibility(View.INVISIBLE); //완성
   }
   else if(g9_value >= (gogi9_t-3) && g9_value < (gogi9_t)){
    gogi9.setVisibility(View.INVISIBLE); //탄거
   }
  }
 };
 
 View.OnClickListener onGoGi10Listener = new View.OnClickListener() {
  
  public void onClick(View v) {
   // TODO Auto-generated method stub   
 
   if(g10_value < (gogi10_t-5)){
    gogi10.setVisibility(View.INVISIBLE); //덜익은거   
   }
   else if(g10_value >= (gogi10_t-5) && g10_value < (gogi10_t-3)){
    total_gogi++;
    textView2.setText("잘 익은 고기" + "\n" + " : " + total_gogi + "개");
    gogi10.setVisibility(View.INVISIBLE); //완성
   }
   else if(g10_value >= (gogi10_t-3) && g10_value < (gogi10_t)){
    gogi10.setVisibility(View.INVISIBLE); //탄거
   }
  }
 };
 
 View.OnClickListener onGoGi11Listener = new View.OnClickListener() {
  
  public void onClick(View v) {
   // TODO Auto-generated method stub   
 
   if(g11_value < (gogi11_t-4)){
    gogi11.setVisibility(View.INVISIBLE); //덜익은거   
   }
   else if(g11_value >= (gogi11_t-4) && g11_value < (gogi11_t-1)){
    total_gogi++;
    textView2.setText("잘 익은 고기" + "\n" + " : " + total_gogi + "개");
    gogi11.setVisibility(View.INVISIBLE); //완성
   }
   else if(g11_value >= (gogi11_t-1) && g11_value < (gogi11_t)){
    gogi11.setVisibility(View.INVISIBLE); //탄거
   }
  }
 };
 
 View.OnClickListener onGoGi12Listener = new View.OnClickListener() {
  
  public void onClick(View v) {
   // TODO Auto-generated method stub   
 
   if(g12_value < (gogi12_t-4)){
     gogi12.setVisibility(View.INVISIBLE); //덜익은거   
   }
   else if(g12_value >= (gogi12_t-4) && g12_value < (gogi12_t-1)){
    total_gogi++;
    textView2.setText("잘 익은 고기" + "\n" + " : " + total_gogi + "개");
    gogi12.setVisibility(View.INVISIBLE); //완성
   }
   else if(g12_value >= (gogi12_t-1) && g12_value < (gogi12_t)){
    gogi12.setVisibility(View.INVISIBLE); //탄거
   }
  }
 };
 
 public boolean onKeyDown(int keyCode, KeyEvent event) {
     
     return true;
    
 }
 
}

