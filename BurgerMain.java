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
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class BurgerMain extends Activity {
    /** Called when the activity is first created. */

 DBManager dManager;
 DBManager2 dManager2;
 BurgerDBManager dManager3;
 SQLiteDatabase db, db2, db3;
 private static final String DATABASE_NAME = "character.db";
 private static final String DATABASE_NAME2 = "calendar.db";
 int d_day = 1;
 String str, myStr = "";
 CountDownTimer timer;
 int value = 31;
 boolean time = false;
 String d_strength, d_money;  // DB에서 가져온 지수값
 int s_v, m_v = 0; 
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
        
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
        
        dManager3 = new BurgerDBManager(this, "burger.db", null, 1);
        onOutput();
                
        timer = new CountDownTimer(32 * 1000, 1000) {
        	
        	@Override   
        	public void onTick(long millisUntilFinished) {
        	 	value--;   
        	}
        	
        	@Override
        	public void onFinish() {    
        	 	time = true;
        	 	Toast.makeText(BurgerMain.this, "The End!", Toast.LENGTH_SHORT).show();
        	}   
        };
              
        timer.start();                
    }
    
    public void onOutput(){
     
     db3 = dManager3.getWritableDatabase();  
         
     String sql = "SELECT bur_order FROM burgergame WHERE num =" + (BurgerShow.rand + 1) + ";"; 
     
     try{   
      
      Cursor cur = db3.rawQuery(sql, null);
     
      while(cur.moveToNext()){   
       String in_order = cur.getString(cur.getColumnIndex("bur_order"));
       str = in_order; 
      }
      
     }catch (SQLException se) {
      // TODO: handle exception      
     }          
  
        db3.close(); 
        //Toast.makeText(BurgerMain.this, "순서 = " + str, Toast.LENGTH_SHORT).show();
        
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
     
     if(s < 0) s = 0;
     String str_s = String.valueOf(s);
     String str_m = String.valueOf(m);
     
    String sql1, sql2 = "";    
    db = dManager.getWritableDatabase(); 
  
     sql1 = "update character set strength = '" + str_s + "';"; 
        sql2 = "update character set money = '" + str_m + "';";  
    
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.close();
   
   }
    
    //----------------------------------
    //  MyView     
    //----------------------------------
    class MyView extends View {
     
     int width, height;   // View 크기
     int cx, cy;     // View 중심     
     Bitmap imgTarget1_1, imgTarget2_2, imgTarget3_3, imgTarget4_4, imgTarget5_5, imgTarget6_6, imgTarget7_7, imgTarget8_8, imgTarget9_9, imgTarget10_10, imgTarget11_11, imgTarget12_12, imgTarget13_13, imgTarget14_14, imgTarget15_15;      
     Bitmap imgBack, imgTarget1, imgTarget2, imgTarget3, imgTarget4, imgTarget5, imgTarget6, imgTarget7, imgTarget8, imgTarget9, imgTarget10, imgTarget11, imgTarget12, imgTarget13, imgTarget14, imgTarget15; // 배경, 아이콘     
     Bitmap imgTarget1_1_1, imgTarget2_2_2, imgTarget3_3_3, imgTarget4_4_4, imgTarget5_5_5, imgTarget6_6_6, imgTarget7_7_7, imgTarget8_8_8, imgTarget9_9_9, imgTarget10_10_10, imgTarget11_11_11, imgTarget12_12_12, imgTarget13_13_13, imgTarget14_14_14, imgTarget15_15_15;
     Rect mRect[] = new Rect[1]; // 영역지정
     int selX1, selX2, selX3, selX4, selX5, selX6, selX7, selX8, selX9, selX10, selX11, selX12, selX13, selX14, selX15, lastX, dx;
     int selY1, selY2, selY3, selY4, selY5, selY6, selY7, selY8, selY9, selY10, selY11, selY12, selY13, selY14, selY15, lastY, dy;
     
     int count = 0;
     int picH[] = new int[10];
     
     int iX1[] = new int[10]; int iY1[] = new int[10];
     int iX2[] = new int[10]; int iY2[] = new int[10];
     int iX3[] = new int[10]; int iY3[] = new int[10];
     int iX4[] = new int[10]; int iY4[] = new int[10];
     int iX5[] = new int[10]; int iY5[] = new int[10];
     int iX6[] = new int[10]; int iY6[] = new int[10];
     int iX7[] = new int[10]; int iY7[] = new int[10];
     int iX8[] = new int[10]; int iY8[] = new int[10];
     int iX9[] = new int[10]; int iY9[] = new int[10];
     int iX10[] = new int[10]; int iY10[] = new int[10];
     int iX11[] = new int[10]; int iY11[] = new int[10];
     int iX12[] = new int[10]; int iY12[] = new int[10];
     int iX13[] = new int[10]; int iY13[] = new int[10];
     int iX14[] = new int[10]; int iY14[] = new int[10];
     int iX15[] = new int[10]; int iY15[] = new int[10];

     int num[] = new int[10];
     Bitmap pic[] = new Bitmap[10];
     int check = 0;     
     Rect iRect[] = new Rect[15];
     
     boolean move1 = false; boolean move2 = false; boolean move3 = false; boolean move4 = false;
     boolean move5 = false; boolean move6 = false; boolean move7 = false; boolean move8 = false;
     boolean move9 = false; boolean move10 = false; boolean move11 = false; boolean move12 = false;
     boolean move13 = false; boolean move14 = false; boolean move15 = false; 
     
     boolean stop1 = false; boolean stop2 = false; boolean stop3 = false; boolean stop4 = false;
     boolean stop5 = false; boolean stop6 = false; boolean stop7 = false; boolean stop8 = false;
     boolean stop9 = false; boolean stop10 = false; boolean stop11 = false; boolean stop12 = false;
     boolean stop13 = false; boolean stop14 = false; boolean stop15 = false;

         
     //----------------------------------
        //  생성자(Constructor)     
        //----------------------------------
  public MyView(Context context) {
   super(context);
   
   Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
   width = display.getWidth();  // View의 가로 폭
   height = display.getHeight();   // View의 세로 높이
   
   cx = width / 2; 
   cy = height / 2; 
   
   // 배경 이미지를 읽고 View 크기에 맞게 늘려줌
   imgBack = BitmapFactory.decodeResource(context.getResources(), R.drawable.back);
   imgBack = Bitmap.createScaledBitmap(imgBack, width, height, true);
      
	//왼쪽 View에 있는 이미지 설정
   imgTarget1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.a);
   imgTarget1 = Bitmap.createScaledBitmap(imgTarget1, 80, 80, true);     
   imgTarget2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.b);
   imgTarget2 = Bitmap.createScaledBitmap(imgTarget2, 80, 80, true);   
   imgTarget3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.c);
   imgTarget3 = Bitmap.createScaledBitmap(imgTarget3, 80, 80, true);    
   imgTarget4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.d);
   imgTarget4 = Bitmap.createScaledBitmap(imgTarget4, 80, 80, true);   
   imgTarget5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.e);
   imgTarget5 = Bitmap.createScaledBitmap(imgTarget5, 80, 80, true);   
   imgTarget6 = BitmapFactory.decodeResource(context.getResources(), R.drawable.f);
   imgTarget6 = Bitmap.createScaledBitmap(imgTarget6, 80, 80, true);    
   imgTarget7 = BitmapFactory.decodeResource(context.getResources(), R.drawable.g);
   imgTarget7 = Bitmap.createScaledBitmap(imgTarget7, 80, 80, true); 
   imgTarget8 = BitmapFactory.decodeResource(context.getResources(), R.drawable.h);
   imgTarget8 = Bitmap.createScaledBitmap(imgTarget8, 80, 80, true); 
   imgTarget9 = BitmapFactory.decodeResource(context.getResources(), R.drawable.i);
   imgTarget9 = Bitmap.createScaledBitmap(imgTarget9, 80, 80, true); 
   imgTarget10 = BitmapFactory.decodeResource(context.getResources(), R.drawable.j);
   imgTarget10 = Bitmap.createScaledBitmap(imgTarget10, 80, 80, true); 
   imgTarget11 = BitmapFactory.decodeResource(context.getResources(), R.drawable.k);
   imgTarget11 = Bitmap.createScaledBitmap(imgTarget11, 80, 80, true); 
   imgTarget12 = BitmapFactory.decodeResource(context.getResources(), R.drawable.l);
   imgTarget12 = Bitmap.createScaledBitmap(imgTarget12, 80, 80, true); 
   imgTarget13 = BitmapFactory.decodeResource(context.getResources(), R.drawable.m);
   imgTarget13 = Bitmap.createScaledBitmap(imgTarget13, 80, 80, true);   
   imgTarget14 = BitmapFactory.decodeResource(context.getResources(), R.drawable.n);
   imgTarget14 = Bitmap.createScaledBitmap(imgTarget14, 80, 80, true);
   imgTarget15 = BitmapFactory.decodeResource(context.getResources(), R.drawable.o);
   imgTarget15 = Bitmap.createScaledBitmap(imgTarget15, 80, 80, true);
      
	  //각각의 그림이미지 폭과 넓이 얻어옴
   int tarX1 = imgTarget1.getWidth() / 2; int tarY1 = imgTarget1.getHeight() / 2;
   int tarX2 = imgTarget2.getWidth() / 2; int tarY2 = imgTarget2.getHeight() / 2;
   int tarX3 = imgTarget3.getWidth() / 2; int tarY3 = imgTarget3.getHeight() / 2;
   int tarX4 = imgTarget4.getWidth() / 2; int tarY4 = imgTarget4.getHeight() / 2;
   int tarX5 = imgTarget5.getWidth() / 2; int tarY5 = imgTarget5.getHeight() / 2;     
   int tarX6 = imgTarget6.getWidth() / 2; int tarY6 = imgTarget6.getHeight() / 2;   
   int tarX7 = imgTarget7.getWidth() / 2; int tarY7 = imgTarget7.getHeight() / 2;    
   int tarX8 = imgTarget8.getWidth() / 2; int tarY8 = imgTarget8.getHeight() / 2;
   int tarX9 = imgTarget9.getWidth() / 2; int tarY9 = imgTarget9.getHeight() / 2;
   int tarX10 = imgTarget10.getWidth() / 2; int tarY10 = imgTarget10.getHeight() / 2;
   int tarX11 = imgTarget11.getWidth() / 2; int tarY11 = imgTarget11.getHeight() / 2; 
   int tarX12 = imgTarget12.getWidth() / 2; int tarY12 = imgTarget12.getHeight() / 2; 
   int tarX13 = imgTarget13.getWidth() / 2; int tarY13 = imgTarget13.getHeight() / 2;
   int tarX14 = imgTarget14.getWidth() / 2; int tarY14 = imgTarget14.getHeight() / 2;
   int tarX15 = imgTarget15.getWidth() / 2; int tarY15 = imgTarget15.getHeight() / 2;
   
   imgTarget1_1 = Bitmap.createScaledBitmap(imgTarget1, tarX1 * 8, 35, true);
   imgTarget2_2 = Bitmap.createScaledBitmap(imgTarget2, tarX2 * 8, 35, true);
   imgTarget3_3 = Bitmap.createScaledBitmap(imgTarget3, tarX3 * 8, 35, true);
   imgTarget4_4 = Bitmap.createScaledBitmap(imgTarget4, tarX4 * 8, 35, true);
   imgTarget5_5 = Bitmap.createScaledBitmap(imgTarget5, tarX5 * 8, 35, true);
   imgTarget6_6 = Bitmap.createScaledBitmap(imgTarget6, tarX6 * 8, 35, true);
   imgTarget7_7 = Bitmap.createScaledBitmap(imgTarget7, tarX7 * 8, 35, true);
   imgTarget8_8 = Bitmap.createScaledBitmap(imgTarget8, tarX8 * 8, 35, true);
   imgTarget9_9 = Bitmap.createScaledBitmap(imgTarget9, tarX9 * 8, 35, true);
   imgTarget10_10 = Bitmap.createScaledBitmap(imgTarget10, tarX10 * 8, 35, true);
   imgTarget11_11 = Bitmap.createScaledBitmap(imgTarget11, tarX11 * 8, 35, true);
   imgTarget12_12 = Bitmap.createScaledBitmap(imgTarget12, tarX12 * 8, 35, true);
   imgTarget13_13 = Bitmap.createScaledBitmap(imgTarget13, tarX13 * 8, 35, true);
   imgTarget14_14 = Bitmap.createScaledBitmap(imgTarget14, tarX14 * 8, 35, true);   
   imgTarget15_15 = Bitmap.createScaledBitmap(imgTarget15, tarX15 * 8, 35, true); 

	//오른쪽 뷰로 드래그했을 때 보여줄 이미지 설정
   imgTarget1_1_1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.a1);
   imgTarget1_1_1 = Bitmap.createScaledBitmap(imgTarget1_1_1, tarX1 * 8, 35, true);
   imgTarget2_2_2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.b1);
   imgTarget2_2_2 = Bitmap.createScaledBitmap(imgTarget2_2_2, tarX1 * 8, 35, true);
   imgTarget3_3_3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.c1);
   imgTarget3_3_3 = Bitmap.createScaledBitmap(imgTarget3_3_3, tarX1 * 8, 35, true);
   imgTarget4_4_4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.d1);
   imgTarget4_4_4 = Bitmap.createScaledBitmap(imgTarget4_4_4, tarX1 * 8, 35, true);
   imgTarget5_5_5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.e1);
   imgTarget5_5_5 = Bitmap.createScaledBitmap(imgTarget5_5_5, tarX1 * 8, 35, true);      
   imgTarget6_6_6 = BitmapFactory.decodeResource(context.getResources(), R.drawable.f1);
   imgTarget6_6_6 = Bitmap.createScaledBitmap(imgTarget6_6_6, tarX1 * 8, 35, true);   
   imgTarget7_7_7 = BitmapFactory.decodeResource(context.getResources(), R.drawable.g1);
   imgTarget7_7_7 = Bitmap.createScaledBitmap(imgTarget7_7_7, tarX1 * 8, 35, true);
   imgTarget8_8_8 = BitmapFactory.decodeResource(context.getResources(), R.drawable.h1);
   imgTarget8_8_8 = Bitmap.createScaledBitmap(imgTarget8_8_8, tarX1 * 8, 35, true);   
   imgTarget9_9_9 = BitmapFactory.decodeResource(context.getResources(), R.drawable.i1);
   imgTarget9_9_9 = Bitmap.createScaledBitmap(imgTarget9_9_9, tarX1 * 8, 35, true);
   imgTarget10_10_10 = BitmapFactory.decodeResource(context.getResources(), R.drawable.j1);
   imgTarget10_10_10 = Bitmap.createScaledBitmap(imgTarget10_10_10, tarX1 * 8, 35, true);
   imgTarget11_11_11 = BitmapFactory.decodeResource(context.getResources(), R.drawable.k1);
   imgTarget11_11_11 = Bitmap.createScaledBitmap(imgTarget11_11_11, tarX1 * 8, 35, true);   
   imgTarget12_12_12 = BitmapFactory.decodeResource(context.getResources(), R.drawable.l1);
   imgTarget12_12_12 = Bitmap.createScaledBitmap(imgTarget12_12_12, tarX1 * 8, 35, true);
   imgTarget13_13_13 = BitmapFactory.decodeResource(context.getResources(), R.drawable.m1);
   imgTarget13_13_13 = Bitmap.createScaledBitmap(imgTarget13_13_13, tarX1 * 8, 35, true);   
   imgTarget14_14_14 = BitmapFactory.decodeResource(context.getResources(), R.drawable.n1);
   imgTarget14_14_14 = Bitmap.createScaledBitmap(imgTarget14_14_14, tarX1 * 8, 60, true);   
   imgTarget15_15_15 = BitmapFactory.decodeResource(context.getResources(), R.drawable.underbread);
   imgTarget15_15_15 = Bitmap.createScaledBitmap(imgTarget15_15_15, tarX1 * 8, 60, true);
   
   int x1 = cx; int y1 = 0; int x2 = cx * 2; int y2 = cy * 2;
   
	 // 드래그 영역 설정
     mRect[0] = new Rect(x1, y1, x2, y2);           
      
     iRect[0] = new Rect(0, 0, tarX1 * 2, tarY1 * 2);
     iRect[1] = new Rect(100, 0, 100 + (tarX2 * 2), tarY2 * 2);
     iRect[2] = new Rect(200, 0, 200 + (tarX3 * 2), tarY3 * 2);
     iRect[3] = new Rect(300, 0, 300 + (tarX4 * 2), tarY4 * 2);
     
     iRect[4] = new Rect(0, 100, tarX5 * 2, 100 + (tarY5 * 2));
     iRect[5] = new Rect(100, 100, 100 + (tarX6 * 2), 100 + (tarY6 * 2));
     iRect[6] = new Rect(200, 100, 200 + (tarX7 * 2), 100 + (tarY7 * 2));
     iRect[7] = new Rect(300, 100, 300 + (tarX8 * 2), 100 + (tarY8 * 2));
     
     iRect[8] = new Rect(0, 200, tarX9 * 2, 200 + (tarY9 * 2));
     iRect[9] = new Rect(100, 200, 100 + (tarX10 * 2), 200 + (tarY10 * 2));
     iRect[10] = new Rect(200, 200, 200 + (tarX11 * 2), 200 + (tarY11 * 2));
     iRect[11] = new Rect(300, 200, 300 + (tarX12 * 2), 200 + (tarY12 * 2));
     
     iRect[12] = new Rect(0, 300, tarX13 * 2, 300 + (tarY13 * 2));
     iRect[13] = new Rect(100, 300, 100 + (tarX14 * 2), 300 + (tarY14 * 2));
     iRect[14] = new Rect(200, 300, 200 + (tarX15 * 2), 300 + (tarY15 * 2));
      
		//오른쪽 뷰로 드래그 했을 때 높이값 설정
     for(int i = 0; i < 10 ; i++){
    	 picH[i] = (2 * cy) - 140 - (i * 10);
    	 num[i] = i;
     }
      
  }  
        //----------------------------------
        //  onDraw     
        //----------------------------------
  public void onDraw(Canvas canvas) {
	  
	  Paint p1 = new Paint();
	  p1.setAntiAlias(true);
	  p1.setColor(Color.BLACK);
	  p1.setTextSize(40);
	  	  
	  Paint paint = new Paint();
	  paint.setColor(Color.WHITE);
	  paint.setTextSize(50);
	  paint.setAntiAlias(true);
      
	  int x1 = cx; int y1 = 0; int x2 = cx * 2; int y2 = cy * 2;         
	  canvas.drawRect(x1, y1, x2, y2, paint);
         
		 //onDraw 호출 시 뷰에서 나타나는 이미지 
	  canvas.drawBitmap(imgTarget1, 0, 0, null); 
	  canvas.drawBitmap(imgTarget2, 100, 0, null);
	  canvas.drawBitmap(imgTarget3, 200, 0, null);
	  canvas.drawBitmap(imgTarget4, 300, 0, null);
   
	  canvas.drawBitmap(imgTarget5, 0, 100, null);
	  canvas.drawBitmap(imgTarget6, 100, 100, null);
	  canvas.drawBitmap(imgTarget7, 200, 100, null);
	  canvas.drawBitmap(imgTarget8, 300, 100, null);
   
	  canvas.drawBitmap(imgTarget9, 0, 200, null);
	  canvas.drawBitmap(imgTarget10, 100, 200, null);
	  canvas.drawBitmap(imgTarget11, 200, 200, null);
	  canvas.drawBitmap(imgTarget12, 300, 200, null);   
   
	  canvas.drawBitmap(imgTarget13, 0, 300, null);
	  canvas.drawBitmap(imgTarget14, 100, 300, null);
	  canvas.drawBitmap(imgTarget15, 200, 300, null);
   
	  /*if(myStr.equalsIgnoreCase("")){Toast.makeText(BurgerMain.this, "밑빵을 먼저 쌓으세요", Toast.LENGTH_SHORT).show();}
    	 else{
    		 	
    		  */
	  
		// 오른쪽 뷰로 드래그했을 때 쌓여지는 부분

	  for(int i : num){
		  		  
		  if(iX1[i]!=0)canvas.drawBitmap(imgTarget1_1_1, iX1[i] - 120, iY1[i], null);		  
		  if(iX2[i]!=0)canvas.drawBitmap(imgTarget2_2_2, iX2[i] + 100 - 120, iY2[i], null);		  
		  if(iX3[i]!=0)canvas.drawBitmap(imgTarget3_3_3, iX3[i] + 200 - 120, iY3[i], null);  
		  if(iX4[i]!=0)canvas.drawBitmap(imgTarget4_4_4, iX4[i] + 300 - 120, iY4[i], null); 		  
		  if(iX5[i]!=0)canvas.drawBitmap(imgTarget5_5_5, iX5[i] - 120, iY5[i] + 100, null);		  
		  if(iX6[i]!=0)canvas.drawBitmap(imgTarget6_6_6, iX6[i] + 100 - 120, iY6[i] + 100, null);		  
		  if(iX7[i]!=0)canvas.drawBitmap(imgTarget7_7_7, iX7[i] + 200 - 120, iY7[i] + 100, null);    
		  if(iX8[i]!=0)canvas.drawBitmap(imgTarget8_8_8, iX8[i] + 300 - 120, iY8[i] + 100, null);
		  if(iX9[i]!=0)canvas.drawBitmap(imgTarget9_9_9, iX9[i] - 120, iY9[i] + 200, null);
		  if(iX10[i]!=0)canvas.drawBitmap(imgTarget10_10_10, iX10[i] + 100 - 120, iY10[i] + 200, null);    
		  if(iX11[i]!=0)canvas.drawBitmap(imgTarget11_11_11, iX11[i] + 200 - 120, iY11[i] + 200, null);
		  if(iX12[i]!=0)canvas.drawBitmap(imgTarget12_12_12, iX12[i] + 300 - 120, iY12[i] + 200, null);    
		  if(iX13[i]!=0)canvas.drawBitmap(imgTarget13_13_13, iX13[i] - 120, iY13[i] + 300, null);
		  if(iX14[i]!=0)canvas.drawBitmap(imgTarget14_14_14, iX14[i] + 100 - 120, iY14[i] + 270, null);
		  if(iX15[i]!=0)canvas.drawBitmap(imgTarget15_15_15, iX15[i] + 200 - 120, iY15[i] + 300, null);
		   		  		    
	  }
   
	  canvas.drawText("Time : " + value, cx + 20, 40, p1);
	  canvas.drawText("쌓은 갯수 : " + count + "/10", cx + 20, 80, p1);   
   
	  if(count == 10){
		  compare(myStr);
	  }else{
		  invalidate();
	  }          
   
  } // onDraw

  //------------------------------------
        //      onTouch Event
        //------------------------------------
	
  @Override
  public boolean onTouchEvent(MotionEvent event) {
   
   int thisX = (int) event.getX();
   int thisY = (int) event.getY();
   
   switch (event.getAction()) {
    
	//1. 각각의 이미지 영역을 눌렀을 때 영역이 맞는지 비교한후 영역이 맞으면 true로 변경

   case MotionEvent.ACTION_DOWN:
	   
    if(iRect[0].contains(thisX, thisY) == true){
     	selX1 = thisX; 
    	selY1 = thisY;   // 처음 좌표값 설정
        move1 = true;         
    }
    if(iRect[1].contains(thisX, thisY) == true){
    	selX2 = thisX; 
    	selY2 = thisY;   // 처음 좌표값 설정
    	move2 = true;    	
    }
    if(iRect[2].contains(thisX, thisY) == true){
    	selX3 = thisX; 
    	selY3 = thisY;   // 처음 좌표값 설정
    	move3 = true;
    }
    if(iRect[3].contains(thisX, thisY) == true){
    	selX4 = thisX; 
    	selY4 = thisY;   // 처음 좌표값 설정
    	move4 = true;    	
    }
    if(iRect[4].contains(thisX, thisY) == true){
    	selX5 = thisX; 
    	selY5 = thisY;   // 처음 좌표값 설정
    	move5 = true;     
    }
    if(iRect[5].contains(thisX, thisY) == true){
    	selX6 = thisX; 
    	selY6 = thisY;   // 처음 좌표값 설정
    	move6 = true;    	
    }
    if(iRect[6].contains(thisX, thisY) == true){
    	selX7 = thisX; 
    	selY7 = thisY;   // 처음 좌표값 설정
    	move7 = true;    	
    }
    if(iRect[7].contains(thisX, thisY) == true){
    	selX8 = thisX; 
    	selY8 = thisY;   // 처음 좌표값 설정
    	move8 = true;    	
    }
    if(iRect[8].contains(thisX, thisY) == true){
    	selX9 = thisX; 
    	selY9 = thisY;   // 처음 좌표값 설정
    	move9 = true;   
    }
    if(iRect[9].contains(thisX, thisY) == true){
    	selX10 = thisX; 
    	selY10 = thisY;   // 처음 좌표값 설정
    	move10 = true;    	
    }
    if(iRect[10].contains(thisX, thisY) == true){
    	selX11 = thisX; 
    	selY11 = thisY;   // 처음 좌표값 설정
    	move11 = true;     
    }
    if(iRect[11].contains(thisX, thisY) == true){
    	selX12 = thisX; 
    	selY12 = thisY;   // 처음 좌표값 설정
    	move12 = true;    	
    }
    if(iRect[12].contains(thisX, thisY) == true){
    	selX13 = thisX; 
    	selY13 = thisY;   // 처음 좌표값 설정
    	move13 = true;    	
    }
    if(iRect[13].contains(thisX, thisY) == true){
    	selX14 = thisX; 
    	selY14 = thisY;   // 처음 좌표값 설정
    	move14 = true;	
    }
    if(iRect[14].contains(thisX, thisY) == true){        
        selX15 = thisX; 
        selY15 = thisY;   // 처음 좌표값 설정
        move15 = true;
    }    
    break;
     
    // 2. ACTION_DOWN 했을 때 true로 변경된 이미지만 배열에 좌표값을 줌
   case MotionEvent.ACTION_MOVE:


    if(mRect[0].contains(thisX, thisY) == true) {
    	
     if(move1 == true){
      iX1[count] = cx + (int)(cx * 0.5) - 20;
      iY1[count] = picH[count];
      stop1 = true;
     }
     if(move2 == true){
      iX2[count] = cx - 100 + (int)(cx * 0.5) - 20;
      iY2[count] = picH[count];
      stop2 = true;  
     }
     if(move3 == true){
      iX3[count] = cx - 200 + (int)(cx * 0.5) - 20;
      iY3[count] = picH[count];
      stop3 = true;  
     }
     if(move4 == true){
      iX4[count] = cx - 300 + (int)(cx * 0.5) - 20;
      iY4[count] = picH[count];
      stop4 = true;  
     }     
     if(move5 == true){
      iX5[count] = cx + (int)(cx * 0.5) - 20;
      iY5[count] = picH[count] - 100;
      stop5 = true;  
     }
     if(move6 == true){
      iX6[count] = cx - 100 + (int)(cx * 0.5) - 20;
      iY6[count] = picH[count] - 100;
      stop6 = true;  
     }
     if(move7 == true){
      iX7[count] = cx - 200 + (int)(cx * 0.5) - 20;
      iY7[count] = picH[count] - 100;
      stop7 = true;  
     }
     if(move8 == true){
      iX8[count] = cx - 300 + (int)(cx * 0.5) - 20;
      iY8[count] = picH[count] - 100;
      stop8 = true;  
     }
     if(move9 == true){
      iX9[count] = cx + (int)(cx * 0.5) - 20;
      iY9[count] = picH[count] - 200;
      stop9 = true;  
     }
     if(move10 == true){
      iX10[count] = cx - 100 + (int)(cx * 0.5) - 20;
      iY10[count] = picH[count] - 200;
      stop10 = true;  
     }
     if(move11 == true){
      iX11[count] = cx - 200 + (int)(cx * 0.5) - 20;
      iY11[count] = picH[count] - 200;
      stop11 = true;  
     }
     if(move12 == true){
      iX12[count] = cx - 300 + (int)(cx * 0.5) - 20;
      iY12[count] = picH[count] - 200;
      stop12 = true;  
     }
     if(move13 == true){
      iX13[count] = cx + (int)(cx * 0.5) - 20;
      iY13[count] = picH[count] - 300;
      stop13 = true;  
     }
     if(move14 == true){
      iX14[count] = cx - 100 + (int)(cx * 0.5) - 20;
      iY14[count] = picH[count] - 300;
      stop14 = true;  
     }     
     
     if(move15 == true){
         iX15[count] = cx - 200 + (int)(cx * 0.5) - 20;
         iY15[count] = picH[count] - 300;
         stop15 = true;  
        }
    
    }
    
    else {
     
     if(move1 == true){
      dx = thisX - selX1;          
      dy = thisY - selY1;       


      iX1[count] = dx;
      iY1[count] = dy;
    
      lastX = thisX; 
      lastY = thisY;  
     }
     if(move2 == true){
      dx = thisX - selX2;          
      dy = thisY - selY2;       


      iX2[count] = dx;
      iY2[count] = dy;
      
      lastX = thisX; 
      lastY = thisY;  
     }
     if(move3 == true){
      dx = thisX - selX3;          
      dy = thisY - selY3;       


      iX3[count] = dx;
      iY3[count] = dy;
      
      lastX = thisX; 
      lastY = thisY;  
     }
     if(move4 == true){
      dx = thisX - selX4;          
      dy = thisY - selY4;       


      iX4[count] = dx;
      iY4[count] = dy;
      
      lastX = thisX; 
      lastY = thisY;  
     }
     if(move5 == true){
      dx = thisX - selX5;          
      dy = thisY - selY5;       


      iX5[count] = dx;
      iY5[count] = dy;
      
      lastX = thisX; 
      lastY = thisY;  
     } 
     if(move6 == true){
      dx = thisX - selX6;          
      dy = thisY - selY6;       


      iX6[count] = dx;
      iY6[count] = dy;
      
      lastX = thisX; 
      lastY = thisY;  
     }
     if(move7 == true){
      dx = thisX - selX7;          
      dy = thisY - selY7;       


      iX7[count] = dx;
      iY7[count] = dy;
      
      lastX = thisX; 
      lastY = thisY;  
     } 
     if(move8 == true){
      dx = thisX - selX8;          
      dy = thisY - selY8;
      
      iX8[count] = dx;
      iY8[count] = dy;
      
      lastX = thisX; 
      lastY = thisY;  
     }
     if(move9 == true){
      dx = thisX - selX9;          
      dy = thisY - selY9;  
      
      iX9[count] = dx;
      iY9[count] = dy;
      
      lastX = thisX; 
      lastY = thisY;  
     } 
     if(move10 == true){
      dx = thisX - selX10;          
      dy = thisY - selY10;
      
      iX10[count] = dx;
      iY10[count] = dy;
      
      lastX = thisX; 
      lastY = thisY;  
     }
     if(move11 == true){
      dx = thisX - selX11;          
      dy = thisY - selY11;       


      iX11[count] = dx;
      iY11[count] = dy;
      
      lastX = thisX; 
      lastY = thisY;  
     }
     if(move12 == true){
      dx = thisX - selX12;          
      dy = thisY - selY12;       


      iX12[count] = dx;
      iY12[count] = dy;
      
      lastX = thisX; 
      lastY = thisY;  
     }
     if(move13 == true){
      dx = thisX - selX13;          
      dy = thisY - selY13;       


      iX13[count] = dx;
      iY13[count] = dy;
      
      lastX = thisX; 
      lastY = thisY;  
     }
     if(move14 == true){
      dx = thisX - selX14;          
      dy = thisY - selY14; 

      iX14[count] = dx;
      iY14[count] = dy;
      
      lastX = thisX; 
      lastY = thisY;  
     }
     if(move15 == true){
         dx = thisX - selX15;          
         dy = thisY - selY15;

         iX15[count] = dx;
         iY15[count] = dy;
         
         lastX = thisX; 
         lastY = thisY;  
        }
    }
    
    // 좌표값 차이 계산해서 iconX, iconY값 정하기
    
    break;
    
	// 3. 오른쪽 뷰의 해당 영역안으로 드래그 했을 때 count가 증가하고 해당 이미지에 맞는 문자열이 추가됨
   case MotionEvent.ACTION_UP:
    
    if(mRect[0].contains(thisX, thisY) == true) { 
     
     if(move1 == true){    	 
    	  	
    		  iX1[count] = cx + (int)(cx * 0.5) - 20;
    	      iY1[count] = picH[count];
    	      move1 = false;
    	      count++;
    	      myStr += "a";    		 
    	 
     }     
     if(move2 == true){
    	  
    		 iX2[count] = cx - 100 + (int)(cx * 0.5) - 20; 
    		 iY2[count] = picH[count];
    		 move2 = false;
    		 count++;
    		 myStr += "b";
    	 
     }
     if(move3 == true){
    	 
    		 iX3[count] = cx - 200 + (int)(cx * 0.5) - 20;
    		 iY3[count] = picH[count];
    		 move3 = false;
    		 count++;
    		 myStr += "c";
    	 
     }
     if(move4 == true){
    	     	   	 
    		 iX4[count] = cx - 300 + (int)(cx * 0.5) - 20;
    		 iY4[count] = picH[count];
    		 move4 = false;
    		 count++;
    		 myStr += "d";
    	 
     } 
     if(move5 == true){
    	     		 
    		 iX5[count] = cx + (int)(cx * 0.5) - 20;
    	     iY5[count] = picH[count] - 100;
    	     move5 = false;
    	     count++;
    	     myStr += "e";
    	 
     } 
     if(move6 == true){
    	  
    		 iX6[count] = cx - 100 + (int)(cx * 0.5) - 20;
    		 iY6[count] = picH[count] - 100;
    		 move6 = false;
    		 count++;
    		 myStr += "f";
    	 
     }
     if(move7 == true){
    		
    		  iX7[count] = cx - 200 + (int)(cx * 0.5) - 20;
    		  iY7[count] = picH[count] - 100;
    		  move7 = false;
    		  count++;
    		  myStr += "g";
    	 
     }
     if(move8 == true){
    	 
    	 	  iX8[count] = cx - 300 + (int)(cx * 0.5) - 20;
    		  iY8[count] = picH[count] - 100;
    		  move8 = false;
    		  count++;
    		  myStr += "h";
    	 
     }
     if(move9 == true){
    	 
    	 	 iX9[count] = cx + (int)(cx * 0.5) - 20;
    		 iY9[count] = picH[count] - 200;
    		 move9 = false;
    		 count++;
    		 myStr += "i";
    	 
     }
     if(move10 == true){
    	 
    	 	  iX10[count] = cx - 100 + (int)(cx * 0.5) - 20;
    		  iY10[count] = picH[count] - 200;
    		  move10 = false;
    		  count++;
    		  myStr += "j";
    	 
     }
     if(move11 == true){
    	 
    		  iX11[count] = cx - 200 + (int)(cx * 0.5) - 20;
    		  iY11[count] = picH[count] - 200;
    		  move11 = false;
    		  count++;
    		  myStr += "k";
    	 
     }
     if(move12 == true){
    		
    		  iX12[count] = cx - 300 + (int)(cx * 0.5) - 20;
    		  iY12[count] = picH[count] - 200;
    		  move12 = false;
    		  count++;
    		  myStr += "l";
    	 
     }
     if(move13 == true){
    	  	
    		  iX13[count] = cx + (int)(cx * 0.5) - 20;
    		  iY13[count] = picH[count] - 300;
    		  move13 = false;
    		  count++;
    		  myStr += "m";
    	 
     }
     if(move14 == true){
    	     		 	
    		  iX14[count] = cx - 100 + (int)(cx * 0.5) - 20;
    		  iY14[count] = picH[count] - 300;
    		  move14 = false;
    		  count++;
      
    		  compare(myStr);			// 윗빵을 쌓으면 종료되고 비교시작
    	       
     }
     if(move15 == true){
         iX15[count] = cx - 200 + (int)(cx * 0.5) - 20;
         iY15[count] = picH[count] - 300;
         move15 = false;
         count++; 
         myStr += "z";
     }     
    }     
    else {								// 드래그 영역안으로 들어오지 못했을 경우 

     iX1[count] = 0; iY1[count] = 0;
     iX2[count] = 0; iY2[count] = 0;
     iX3[count] = 0; iY3[count] = 0;
     iX4[count] = 0; iY4[count] = 0;
     iX5[count] = 0; iY5[count] = 0;
     iX6[count] = 0; iY6[count] = 0;
     iX7[count] = 0; iY7[count] = 0;
     iX8[count] = 0; iY8[count] = 0;
     iX9[count] = 0; iY9[count] = 0;
     iX10[count] = 0; iY10[count] = 0;
     iX11[count] = 0; iY11[count] = 0;
     iX12[count] = 0; iY12[count] = 0;
     iX13[count] = 0; iY13[count] = 0;
     iX14[count] = 0; iY14[count] = 0;
     iX15[count] = 0; iY15[count] = 0;
          
     move1 = false; move2 = false;
     move3 = false; move4 = false;
     move5 = false; move6 = false;
     move7 = false; move8 = false;
     move9 = false; move10 = false;
     move11 = false; move12 = false;
     move13 = false; move14 = false;
     move15 = false;
     
    }

    break;    
   }
   
   invalidate(); // onDraw() 호출 
   return true;
  }
  
  public void compare(String string){			// DB에서 가져온 해당 패티의 문자열과 뷰에서 만든 문자열 비교
	 
   if(str.equalsIgnoreCase(string)){    
	   
    if(time == false){
     
      timer.cancel();
      
      s_v = s_v - 10;
      m_v = m_v + 10;
      setValue(s_v, m_v);
      
      AlertDialog.Builder alertDlg = new AlertDialog.Builder(BurgerMain.this) ;   
      alertDlg.setTitle("결과");
      alertDlg.setMessage("\n" + "정답입니다!" + "\n" + "체력이 10 감소합니다." + "\n" + "돈 10만원을 받았습니다." + "\n" );   
      alertDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
       public void onClick(DialogInterface dialog, int whichButton) {
        d_day = d_day + 3;
        setDay(d_day);
        startActivity(new Intent(BurgerMain.this, Main.class));
       }    
      });
      alertDlg.show() ;
      
     //Toast.makeText(BurgerMain.this, "정답!", Toast.LENGTH_SHORT).show();
     
    }    
    else if(time == true){
     
      s_v = s_v - 10;
      m_v = m_v + 5;
      setValue(s_v, m_v);
     
      AlertDialog.Builder alertDlg = new AlertDialog.Builder(BurgerMain.this) ;   
      alertDlg.setTitle("결과");
      alertDlg.setMessage("\n" + "정답이지만 시간초과입니다!" + "\n" + "체력이 10 감소합니다." + "\n" + "돈 5만원을 받았습니다." + "\n");
      alertDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
       public void onClick(DialogInterface dialog, int whichButton) {
        d_day = d_day + 3;
        setDay(d_day);
        startActivity(new Intent(BurgerMain.this, Main.class));
       }    
      });
      alertDlg.show() ;
      
     //Toast.makeText(BurgerMain.this, "정답이지만 시간초과!", Toast.LENGTH_SHORT).show();
    }
   }   
   else {
    
    if(time == false){     

      timer.cancel();
      
      s_v = s_v - 10;
      m_v = m_v + 5;
      setValue(s_v, m_v);
      
      AlertDialog.Builder alertDlg = new AlertDialog.Builder(BurgerMain.this) ;   
      alertDlg.setTitle("결과");
      alertDlg.setMessage("\n" + "오답입니다!" + "\n" + "체력이 10 감소합니다." + "\n" + "돈 5만원을 받았습니다." + "\n");   
      alertDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
       public void onClick(DialogInterface dialog, int whichButton) {
        d_day = d_day + 3;
        setDay(d_day);
        startActivity(new Intent(BurgerMain.this, Main.class));
       }    
      });
      alertDlg.show() ;
      
     //Toast.makeText(BurgerMain.this, "오답!", Toast.LENGTH_SHORT).show();
     
    }   
    else if(time == true){
     
      s_v = s_v - 10;
      m_v = m_v + 5;
      setValue(s_v, m_v);
      
      AlertDialog.Builder alertDlg = new AlertDialog.Builder(BurgerMain.this) ;   
      alertDlg.setTitle("결과");
      alertDlg.setMessage("\n" + "오답, 시간초과입니다!" + "\n" + "체력이 10 감소합니다." + "\n" + "돈 5만원을 받았습니다." + "\n");   
      alertDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
       public void onClick(DialogInterface dialog, int whichButton) {
        d_day = d_day + 3;
        setDay(d_day);
        startActivity(new Intent(BurgerMain.this, Main.class));
       }    
      });
      alertDlg.show() ;
      
     //Toast.makeText(BurgerMain.this, "오답 + 시간초과!", Toast.LENGTH_SHORT).show();
    }
   }   
  }  
    } // MyView 
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	      
        return true;
       
    }
} // Activiry
