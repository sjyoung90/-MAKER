package com.android.progBar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Bbebbero extends Activity {
    /** Called when the activity is first created. */      
 
 DBManager dManager;
 DBManager2 dManager2;
 SQLiteDatabase db, db2;
 private static final String DATABASE_NAME = "character.db";
 private static final String DATABASE_NAME2 = "calendar.db";
 int d_day = 1;
 int time;
 String d_love, d_strength, d_money, d_stage;
 int k_v, l_v, s_v, m_v, st_v;
 
 CountDownTimer timer;
 boolean success = false;
 int value;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                
        setContentView(new BView(this));        
        
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
        
        if (st_v == 1) {
         time = 11;
         }
        else if (st_v == 2) {
         time = 9;
        }
        else if (st_v == 3) {
         time = 7;
        }
        else {
         time = 6;
        }
        
        timer = new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {          
              value = (int)(millisUntilFinished/1000);
              value--;
              if(value == 0) {
               onDialog();
              }
             //System.out.println(value);
      }
            @Override
            public void onFinish() {
            }   
        };
        
        timer.start();
    }
    
    public void onDialog(){
     if(success == false){
          
      l_v = l_v - 2;
      m_v = m_v - 5;
      setValue(l_v, s_v, m_v);
      
      AlertDialog.Builder alertDlg = new AlertDialog.Builder(Bbebbero.this) ;   
      alertDlg.setTitle("결과");
      alertDlg.setMessage("\n" + "실패하셨습니다!" + "\n"
                    + "애정이 2 감소합니다." + "\n"
                    + "5만원 차감됩니다." + "\n");   
      alertDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
       public void onClick(DialogInterface dialog, int whichButton) {
        d_day = d_day + 3;
        setDay(d_day);
        startActivity(new Intent(Bbebbero.this, Main.class));
       }    
      });
      alertDlg.show();
     }
     
    }
    
    
    
    class BView extends View {
     int width, height, start;   // View 크기
     int cx, cy;     // View 중심
     int iconX, iconY; // icon 위치
     int iconX2, iconY2;
     Bitmap imgBack, imgTarget, imgTarget2, sigye; // 배경, 아이콘     
     BitmapDrawable bd;
     Rect mRect[] = new Rect[1]; // 영역지정
     int selX, lastX, dx;
     int selY, lastY, dy;
     int count = 0;
     int countT = 0;
     int picH[] = new int[40];
     Button startB;
     Rect iRect[] = new Rect[2];
     boolean move = false;
     int rand;
     
  public BView(Context context) {
   super(context);
   
   Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
   width = display.getWidth();  // View의 가로 폭
   height = display.getHeight();   // View의 세로 높이
   
   cx = width / 2;
   cy = height / 2; 
   iconY = cy/3;
   start = width-250;
   
   imgBack = BitmapFactory.decodeResource(context.getResources(), R.drawable.bbe_back);
   imgBack = Bitmap.createScaledBitmap(imgBack, width, height, true);  
   
   imgTarget = BitmapFactory.decodeResource(context.getResources(), R.drawable.bbebbeboy); 
   imgTarget2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.bbebbegirl);
      
   int tarX = imgTarget.getWidth() / 2;
   int tarY = imgTarget.getHeight() / 2;
   
      int x1 = 0;
      int y1 = 0;
      int x2 = cx;
      int y2 = cy * 2;
      mRect[0] = new Rect(x1, y1, x2 + 20, y2);      
      
      iRect[0] = new Rect(0, 0, tarX * 2, tarY * 2);
      
      for(int i = 0; i< 40 ; i++)
      picH[i] = i * 25;
      
      double temp = Math.random();
      rand = 23 + (int)(temp*10)%10; 
      
  }   
  
  public void onDraw(Canvas canvas) {
   Paint paint = new Paint();
   paint.setTextSize(40);
   
   canvas.drawBitmap(imgBack, 0, 0, null); 
  // canvas.drawBitmap(imgTarget, 0, cy, null); 
   canvas.drawBitmap(imgTarget, iconX, iconY, null);
   
   if(picH[rand] > width) picH[rand] = width - 20;
   
   canvas.drawBitmap(imgTarget2, picH[rand], cy/3, null);
   
   canvas.drawText("시간 : " + value, 10, 50, paint);
   invalidate();
  } 
  
  @Override
  public boolean onTouchEvent(MotionEvent event) {
   
   int thisX = (int) event.getX();
   int thisY = (int) event.getY();
   
   switch (event.getAction()) {
   
   case MotionEvent.ACTION_DOWN:
     
    if(iRect[0].contains(thisX, thisY) == true){
     selX = thisX; 
     selY = thisY;   // 처음 좌표값 설정
     move = true;
    }    
    break;
     
   case MotionEvent.ACTION_MOVE:


    if(mRect[0].contains(thisX, thisY) == true){
     iconX = picH[count];
     iconY = cy/3; 
    }    
    else{
     if(move == true){
      dx = thisX - selX;  
      dy = thisY - selY; 
      
      iconX = dx;
      iconY = dy;
    
      lastX = thisX; 
      lastY = thisY;  
     }    
    }    
    break;
    
   case MotionEvent.ACTION_UP:
    
    if(mRect[0].contains(thisX, thisY) == true) {  
     iconX = picH[count];
     iconY = cy/3;
     count++;
     countT++;
    
       //if(countT == rand){
        
     if((iconX + imgTarget.getWidth()) > (picH[rand] - 10)
       && (iconX + imgTarget.getWidth()) < (picH[rand] + 10)){
        success = true;
             
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(Bbebbero.this) ;   
        alertDlg.setTitle("결과");
        alertDlg.setMessage("\n" + "성공하셨습니다!" + "\n"
                + "애정이 5 증가합니다." + "\n"
                      + "체력이 10 증가합니다." + "\n"
                      + "5만원 차감됩니다." + "\n");
        alertDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
         d_day = d_day + 3;
         setDay(d_day);
         l_v = l_v + 5;
         s_v = s_v + 10;
         m_v = m_v - 5;
         setValue(l_v, s_v, m_v);


         startActivity(new Intent(Bbebbero.this, Main.class));
        }    
       });
       alertDlg.show();
        }      
    }
     
    else { 
     iconX = 0; iconY = cy/3;
     move = false;
    }




    break;
    
   }
   
   invalidate(); // onDraw() 호출 
   return true;
  }




    } 




   /*
    *  d_day = d_day + 3;
   setDay(d_day);
   startActivity(new Intent(Bbebbero.this, Main.class));
   
    */
    
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
   
   public void getValue(){
    
    db = dManager.getWritableDatabase();   
    String sql = "SELECT distinct love, strength, money, stage FROM character;";




      try{   
       
       Cursor cur = db.rawQuery(sql, null);
       
       while(cur.moveToNext()){
       
        d_love = cur.getString(cur.getColumnIndex("love"));
        d_strength = cur.getString(cur.getColumnIndex("strength"));
        d_money = cur.getString(cur.getColumnIndex("money"));
        d_stage = cur.getString(cur.getColumnIndex("stage"));
             
       }       
       
     }catch (SQLException se) {
       // TODO: handle exception      
     }   
          db.close();          
     
      l_v = Integer.parseInt(d_love);
      s_v = Integer.parseInt(d_strength);
      m_v = Integer.parseInt(d_money);
     st_v = Integer.parseInt(d_stage);
  } 
   
   public void setValue(int l, int s, int m){
   
   if(s > 100) s = 100;
   if(l > 100) l = 100;
   String str_l = String.valueOf(l);
   String str_s = String.valueOf(s);
   String str_m = String.valueOf(m);
   
   String sql1 = "";
   String sql2 = "";
   String sql3 = "";
   db = dManager.getWritableDatabase();
   
   sql1 = "update character set love = '" + str_l + "';";
   sql2 = "update character set strength = '" + str_s + "';";
   sql3 = "update character set money = '" + str_m + "';";
   
   db.execSQL(sql1);
   db.execSQL(sql2);
   db.execSQL(sql3);
   db.close();
   
   } 
   
   public boolean onKeyDown(int keyCode, KeyEvent event) {	      
       return true;
   }

   
}
