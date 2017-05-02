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
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PopMain extends Activity implements SensorEventListener{
    /** Called when the activity is first created. */
    
 DBManager dManager;
 DBManager2 dManager2;
 SQLiteDatabase db, db2;
 private static final String DATABASE_NAME = "character.db";
 private static final String DATABASE_NAME2 = "calendar.db";
 int d_day = 1;
 
 String d_strength, d_money, d_stage;
 int s_v, m_v;
 
 LinearLayout iv, iv2;
 ImageView iv_time;
 static int game_count = 0;
 int total = 0;
 int timer_value = 0;
 int timer_total = 22;
 
 private long lastTime;
 private float speed;
 private float lastX;
 private float lastY;
 private float lastZ;
 
 int count=0; 
 
 private float x,y,z;
 private static final int SHAKE_THRESHOLD = 3000;
 
 private static final int DATA_X = SensorManager.DATA_X;
 private static final int DATA_Y = SensorManager.DATA_Y;
 private static final int DATA_Z = SensorManager.DATA_Z;
 
 private SensorManager sensorManager;
 private Sensor acclerormeterSensor;
 CountDownTimer timer;
 boolean time = true;
 Animation an;
 
 TextView x1,y1,z1;
 
 @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popmain);
          
        iv = (LinearLayout)findViewById(R.id.imageView1);
        iv2 = (LinearLayout)findViewById(R.id.imageView1);
        iv_time = (ImageView)findViewById(R.id.time);
        
        iv_time.setVisibility(View.VISIBLE);
        an = AnimationUtils.loadAnimation(this, R.anim.spin);
        iv_time.startAnimation(an);
        
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
        
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        acclerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        timer = new CountDownTimer(timer_total * 1000, 1000) {
         @Override
   public void onTick(long millisUntilFinished) {                
          timer_value++;          
   }

   @Override
   public void onFinish() {    //완성될때
    Toast.makeText(PopMain.this, "The End", Toast.LENGTH_SHORT).show();
    time = false;
    
     compare(iv);       
      
      int m = 0;
      
      
      if(total == 1){
       m = 6;
       s_v = s_v - 10;
       m_v = m_v + 6;
      }
      else {
       m = 4;
       s_v = s_v - 10;
       m_v = m_v + 4;
      }       
    
      AlertDialog.Builder alertDlg = new AlertDialog.Builder(PopMain.this) ;   
      alertDlg.setTitle("결과");
      alertDlg.setMessage("\n"  +
             "돈 " + m +"만원을 받았습니다." + "\n" + 
             "체력은 10 감소합니다." + "\n");   
      alertDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
       public void onClick(DialogInterface dialog, int whichButton) {
        
        setValue(s_v, m_v);
        d_day = d_day + 3;
        setDay(d_day);
        startActivity(new Intent(PopMain.this, Main.class));
       }    
      });
      alertDlg.show() ;
    
    
   }   
        };
        
        timer.start();
      
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
 
 public void setValue(int s, int m){
  
   if(s < 0){ s = 0; }  
   
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
 
 @Override 
 public void onStart(){
  super.onStart();
  
  if(acclerormeterSensor != null)
   sensorManager.registerListener(this,acclerormeterSensor,SensorManager.SENSOR_DELAY_GAME);
  
 }
 
 @Override
 public void onStop(){
  super.onStop();
  if(sensorManager != null){
   sensorManager.unregisterListener(this);
  }
 }
 
 public void onAccuracyChanged(Sensor sensor,int accuracy){
  
 }
 

 
 public void onSensorChanged(SensorEvent event){
  if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
   long currentTime = System.currentTimeMillis();
   long gabOfTime = (currentTime - lastTime);
   
   
   if(gabOfTime > 100){
    
    lastTime = currentTime;
    
    x = event.values[SensorManager.DATA_X];
    y = event.values[SensorManager.DATA_Y];
    z = event.values[SensorManager.DATA_Z];
    
    
    speed = Math.abs(x+y+z-lastX-lastY-lastZ)/gabOfTime*10000;
    
    if(speed > SHAKE_THRESHOLD){ 
     
     count++;     
     
     int shaking = 0; //횟수를 나타냄, 횟수에따라 그림이 바뀜
     
     if(d_stage.equals("1")){ //난이도
    	 shaking = 6;
     }
     else if(d_stage.equals("2")){
    	 shaking = 4;
     }
     else if(d_stage.equals("3")){
    	 shaking = 3;
     }
     else if(d_stage.equals("4")){
    	 shaking = 2;
     }
     
    /* switch(d_stage){
     
     case "1" :
    	 shaking = 6;
    	 break;
    	 
     case "2" :
    	 shaking = 4;
    	 break;
    	 
     case "3" :
    	 shaking = 3;
    	 break;
    	 
     case "4" :
    	 shaking = 2;
    	 break;
   
     }*/
     
     if(count == shaking){ //흔드는 값이랑 해당 학년 count값이랑 같으면 팝콘 그림 변경
     iv.setBackgroundResource(R.drawable.pop2);
    // Toast.makeText(PopMain.this, "" + count, Toast.LENGTH_SHORT).show();
     }
     else if(count == shaking + 2){
     iv.setBackgroundResource(R.drawable.pop3);
    // Toast.makeText(PopMain.this, "" + count, Toast.LENGTH_SHORT).show();
     }
     else if(count ==shaking + 5){
      iv.setBackgroundResource(R.drawable.pop4);
     // Toast.makeText(PopMain.this, "" + count, Toast.LENGTH_SHORT).show();
      }
     else if(count == shaking + 9){
      iv.setBackgroundResource(R.drawable.pop5);
     // Toast.makeText(PopMain.this, "" + count, Toast.LENGTH_SHORT).show();
      }
     else if(count == shaking + 14){
      iv.setBackgroundResource(R.drawable.pop6);
    //  Toast.makeText(PopMain.this, "" + count, Toast.LENGTH_SHORT).show();
      }
     else if(count == shaking + 20){
      iv.setBackgroundResource(R.drawable.pop7);
     // Toast.makeText(PopMain.this, "" + count, Toast.LENGTH_SHORT).show();
      }         
    
    }
    lastX = event.values[DATA_X];
    lastY = event.values[DATA_Y];
    lastZ = event.values[DATA_Z];
   }
  }
 }
 public void onButton1(View v){ //완성버튼 눌렀을때
  
  
     compare(iv);   
  
     
     int m = 0;
     
     
     if(total == 1){ //성공
      m = 6;
      s_v = s_v - 10;
      m_v = m_v + 6;
     }
     else { //실패
      m = 4;
      s_v = s_v - 10;
      m_v = m_v + 4;
     }       
   
     AlertDialog.Builder alertDlg = new AlertDialog.Builder(PopMain.this) ;   
     alertDlg.setTitle("결과");
     alertDlg.setMessage("\n"  +
            "돈 " + m +"만원을 받았습니다." + "\n" + 
            "체력은 10 감소합니다." + "\n");   
     alertDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int whichButton) {
       
       setValue(s_v, m_v);
       d_day = d_day + 3;
       setDay(d_day);
       startActivity(new Intent(PopMain.this, Main.class));
      }    
     });
     alertDlg.show() ;
   
   //Toast.makeText(PopMain.this, "정답 수 : "+ total, Toast.LENGTH_SHORT).show();   
  }
  

 
 public void compare(LinearLayout view){ //처음 팝콘 이미지와 현재이미지 비교 
  Drawable temp = view.getBackground(); //현재 이미지뷰
  Drawable temp1 = PopShow.first_iv.getBackground(); //비교할이미지뷰(첫번째액티비티)
  
  Bitmap tmpBitmap = ((BitmapDrawable)temp).getBitmap();
  Bitmap tmpBitmap1 = ((BitmapDrawable)temp1).getBitmap();
  
  if(tmpBitmap.equals(tmpBitmap1)){
    
   if(time == true){   //시간안에 팝콘완성
    total++;
    Toast.makeText(PopMain.this, "정답입니다 ! ", Toast.LENGTH_SHORT).show();   
    timer.cancel();
    iv_time.clearAnimation();    
   }   
   else if(time == false){
    Toast.makeText(PopMain.this, "정답이지만 시간초과 되었습니다! ", Toast.LENGTH_SHORT).show(); 
   }
  }else{   
   Toast.makeText(PopMain.this, "오답입니다 !", Toast.LENGTH_SHORT).show();
   timer.cancel();   
   iv_time.clearAnimation();   
  }
  
 }

 public boolean onKeyDown(int keyCode, KeyEvent event) {
     
     return true;
    
 }
 
}

 
