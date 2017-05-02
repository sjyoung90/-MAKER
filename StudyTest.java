package com.android.progBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StudyTest extends Activity {
    /** Called when the activity is first created. */      
    
 DBManager dManager;
 DBManager2 dManager2;
 SQLiteDatabase db, db2;
 private static final String DATABASE_NAME = "character.db";
 private static final String DATABASE_NAME2 = "calendar.db";
 int d_day = 1;
 String d_knowledge, d_strength;
 int k_v, s_v;
  TextView tv1, left, right;
 SQLiteDatabase mDatabase, mDatabase2;
 String str = "";
 String str2 = "";
 int num2, rand;
 Button button1, button2, button3, button4;
 String k[] = new String[10];
 String e[] = new String[10];
 int n[] = new int[10];
 int numb[] = new int[12];
 int countT = 0; // 이 카운트를 db에 넣고 하면 됨
 int ir;
 String couple1, couple2;
 String temp1[] = new String[12];
 String temp2[] = new String[12];
 int countK = 0;
 MyView drawView = null;
 int count = 0;
 static int startX = 0, startY = 0, endX = 0, endY = 0;
 static int score = 0;
 int width, height;
 int lx, ly, btnSizehalf;
 static boolean next = false;
 int presa;
 int ran;
 CountDownTimer timer;
 private static final int result = 1;
 static int know, stren;
     @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                
        setContentView(R.layout.studytest);        
        
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
        
        initialize(this);
  button1 = (Button) findViewById(R.id.left1);
  Drawable alpha1 = button1.getBackground();
  alpha1.setAlpha(100);    //투명도
  button2 = (Button) findViewById(R.id.left2);
  Drawable alpha2 = button2.getBackground();
  alpha2.setAlpha(100); 
  button3 = (Button) findViewById(R.id.right1);
  Drawable alpha3 = button3.getBackground();
  alpha3.setAlpha(100);
  button4 = (Button) findViewById(R.id.right2);
  Drawable alpha4 = button4.getBackground();
  alpha4.setAlpha(100);
  drawView = (MyView) findViewById(R.id.drawView);
  
  width = getSizeWidth();
  height = getSizeHeight();
  lx = width / 2;
  ly = height / 2;
  initialize2(this); // db파일로 만들기
  
  presa = Study.pres + 10; //공부했던 거 가져오는거
  
  double te = Math.random();
  ran = (int) (te * 10);
  for (int i = 0; i < 10; i++) {
   e[i] = Study.vocaE[(ran + i) % 10]; //영어 저장하는 곳 (랜덤으로 보여줌)
   n[i] = Study.numk[(ran + i) % 10]; //한글 저장하는 곳 (랜덤으로 보여줌)
   setK(); //한글만 비교
   countK++;
  }
  button1.setText(e[0]);
  button2.setText(e[1]);
  button3.setText(k[ran % 2]);
  button4.setText(k[(ran + 1) % 2]);
 
  timer = new CountDownTimer(1 * 500, 1000) {
   @Override
   public void onTick(long millisUntilFinished) {
   }
   @Override
   public void onFinish() { 
    next();
    button1.setEnabled(true);
    button2.setEnabled(true);
    button3.setEnabled(true);
    button4.setEnabled(true);
   }
  };
 }
 
 public int getSizeWidth() {
  return ((WindowManager) getSystemService(Context.WINDOW_SERVICE))
    .getDefaultDisplay().getWidth();
 } // 가로 크기
 public int getSizeHeight() {
  return ((WindowManager) getSystemService(Context.WINDOW_SERVICE))
    .getDefaultDisplay().getHeight();
 }
 public static final String ROOT_DIR = "/data/data/com.android.progBar/";
 private static final String DATABASE_NAME3 = "daneo.sqlite";
 public static final String TABLE_NAME = "voc";
 private static final String COLUMN_NUMBER = "num";
 private static final String COLUMN_KORE = "kor";
 public static void initialize(Context ctx) {
  // check
  File folder = new File(ROOT_DIR + "databases");
  folder.mkdirs();
  File outfile = new File(ROOT_DIR + "databases/" + DATABASE_NAME3);
  if (outfile.length() <= 0) {
   AssetManager assetManager = ctx.getResources().getAssets(); // 파일읽어오기
   try {
    InputStream is = assetManager.open(DATABASE_NAME3,
      AssetManager.ACCESS_BUFFER);
    // 파일열어서 is에 저장하기
    long filesize = is.available();
    byte[] tempdata = new byte[(int) filesize];
    is.read(tempdata);
    is.close();
    outfile.createNewFile(); // 새파일생성
    FileOutputStream fo = new FileOutputStream(outfile); // 생성한파일
                  // 아웃풋스트림
    fo.write(tempdata); // 파일에다가 디비파일넣기
    fo.close();
   } catch (IOException e) {
    e.printStackTrace();
   }
  }
 }
 public void setK() {
  if (mDatabase == null) {
   mDatabase = openOrCreateDatabase(DATABASE_NAME3,
     Context.MODE_PRIVATE, null);
   // 데이터베이스열기
  }
  Cursor cursor = null;
  //final CursorAdapter adaptor;
  String[] columns = new String[] { COLUMN_KORE };
  cursor = mDatabase.query(TABLE_NAME, columns, COLUMN_NUMBER + "="
    + n[countK], null, null, null, null);
  while (cursor.moveToNext()) {
   //int num1 = cursor.getInt(0);
   String kor = cursor.getString(cursor.getColumnIndex("kor"));
   str2 = kor;
   k[countK] = str2;
  }
 }
 public void onLeft1(View v) { // onDraw()하기위해서
  if (count == 0) {
   startX = 0;
   startY = ly - (ly / 2);
   count = 1;
   // 생성한 객체를 배열에 담는다.
   point p = new point(startX, startY, true);
   MyView.list.add(p);
   if (mDatabase == null) {
    mDatabase = openOrCreateDatabase(DATABASE_NAME3,
      Context.MODE_PRIVATE, null);
    // 데이터베이스열기
   }
   Cursor cursor = null;
   //final CursorAdapter adaptor;
   String[] columns = new String[] { COLUMN_KORE };
   cursor = mDatabase.query(TABLE_NAME, columns, COLUMN_NUMBER + "="
     + n[0], null, null, null, null);
   while (cursor.moveToNext()) {
    //int num1 = cursor.getInt(0);
    String kor = cursor.getString(cursor.getColumnIndex("kor"));
    couple1 = kor;
   }
  }
  else if (count == 1) {
   
   MyView.list.clear();
   startX = 0;
   startY = ly - (ly / 2);
   count = 1;
   
   point p = new point(startX, startY, true);
   MyView.list.add(p);
   
  }
 }
 public void onLeft2(View v) {
  if (count == 0) {
   startX = 0;
   startY = ly + (ly / 4);
   count = 1;
   // 생성한 객체를 배열에 담는다.
   point p = new point(startX, startY, true);
   MyView.list.add(p);
   if (mDatabase == null) {
    mDatabase = openOrCreateDatabase(DATABASE_NAME3,
      Context.MODE_PRIVATE, null);
    // 데이터베이스열기
   }
   Cursor cursor = null;
   //final CursorAdapter adaptor;
   String[] columns = new String[] { COLUMN_KORE };
   cursor = mDatabase.query(TABLE_NAME, columns, COLUMN_NUMBER + "=" + n[1], null, null, null, null);
   while (cursor.moveToNext()) {    
    String kor = cursor.getString(cursor.getColumnIndex("kor"));
    couple1 = kor;
   }
  } else if (count == 1) {
   
   MyView.list.clear();
   startX = 0;
   startY = ly + (ly / 4);
   count = 1;
   point p = new point(startX, startY, true);
   MyView.list.add(p);
  }
 }
 public void onRight1(View v) {  //선그어지게
  if (count == 1) {
   endX = lx + (lx / 6000);
   endY = ly - (ly / 2);
   count = 0;
   // 생성한 객체를 배열에 담는다.
   point p = new point(endX, endY, false);
   MyView.list.add(p);
   drawView.invalidate();
   couple2 = k[ran % 2];
   countT++;
   if (couple1.equals(couple2)) score++;
    button1.setEnabled(false);
    button2.setEnabled(false);
    button3.setEnabled(false);
    button4.setEnabled(false);
    timer.start();
  } else if (count == 0) {
   Toast.makeText(StudyTest.this, "영어를 먼저 선택한 후 뜻을 선택해주세요", Toast.LENGTH_SHORT)
     .show();
  }
 }
 public void onRight2(View v) { //선그어지게
  if (count == 1) {
   endX = lx + (lx / 6000);
   endY = ly + (ly / 4);
   count = 0;
   // 생성한 객체를 배열에 담는다.
   point p = new point(endX, endY, false);
   MyView.list.add(p);
   drawView.invalidate();
   couple2 = k[(ran + 1) % 2];
   countT++;
   if (couple1.equals(couple2)) 
	   
	   score++;
   	   timer.start();
  } else if (count == 0) {
   Toast.makeText(StudyTest.this, "영어를 먼저 선택한 후 뜻을 선택해주세요", Toast.LENGTH_SHORT).show();
  }
 }
 public void next() { //다음 시험으로 넘어가기
  if (countT == 2) { //터치숫자
   String temp[] = new String[2];
   double te = Math.random();
   ran = (int) (te * 10);
   temp[0] = k[2];
   temp[1] = k[3];
   next = true;
   drawView.invalidate();
   button1.setText(e[2]);
   button2.setText(e[3]);
   button3.setText(temp[ran % 2]);
   button4.setText(temp[(ran + 1) % 2]);
   drawView.invalidate();
  }
  else if (countT == 4) {
   String temp[] = new String[2];
   double te = Math.random();
   ran = (int) (te * 10);
   temp[0] = k[4];
   temp[1] = k[5];
   next = true;
   drawView.invalidate();
   button1.setText(e[4]);
   button2.setText(e[5]);
   button3.setText(temp[ran % 2]);
   button4.setText(temp[(ran + 1) % 2]);
  } else if (countT == 6) {
   String temp[] = new String[2];
   double te = Math.random();
   ran = (int) (te * 10);
   temp[0] = k[6];
   temp[1] = k[7];
   next = true;
   drawView.invalidate();
   button1.setText(e[6]);
   button2.setText(e[7]);
   button3.setText(temp[ran % 2]);
   button4.setText(temp[(ran + 1) % 2]);
  } else if (countT == 8) {
   String temp[] = new String[2];
   double te = Math.random();
   ran = (int) (te * 10);
   temp[0] = k[8];
   temp[1] = k[9];
   next = true;
   drawView.invalidate();
   button1.setText(e[8]);
   button2.setText(e[9]);
   button3.setText(temp[ran % 2]);
   button4.setText(temp[(ran + 1) % 2]);
  } else if (countT == 10) {
   StudyTest.this.showDialog(result);   
  }
 }
 public Dialog onCreateDialog(int dialogId) {
  Dialog dialog = null;
  switch (dialogId) {  
  case result:
   
   String string = "";
   
   if(score >= 7){
    k_v = k_v + 2;
    string = "\n" + "정답 수 : " + score + "개" + "\n" +
       "지식이 2 증가합니다." + "\n" + "체력은 5 감소합니다." + "\n";      
   }
   else{
    string = "\n" + "정답 수 : " + score + "개" + "\n" +
      "학습량이 부족하여 지식이 오르지 않았습니다." + "\n" +
      "좀 더 열심히 공부하세요."+ "\n" + "체력은 5 감소합니다." + "\n";
   }
   s_v = s_v - 5;
   
   
   AlertDialog.Builder alertDlg = new AlertDialog.Builder(StudyTest.this) ;   
	alertDlg.setTitle("결과");
	alertDlg.setMessage(string);   
	alertDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
	public void onClick(DialogInterface dialog, int which) {
           score = 0;
           setValue(k_v, s_v);
           d_day = d_day + 3;
           setDay(d_day);
           startActivity(new Intent(StudyTest.this, Main.class));
         if (mDatabase2 == null) {
          mDatabase2 = openOrCreateDatabase(
            "saved1.sqlite",
            Context.MODE_PRIVATE, null);
         }
         // 데이터베이스열기
         mDatabase2.execSQL("UPDATE " + "saved1"
           + " set " + "pres" + " = " + presa
           + " where " + "num" + " = " + "1");
         mDatabase2.close();
        }
	       });
	alertDlg.show();
  }
  return dialog;
 }
 public static void initialize2(Context ctx) {
  // check
  File folder = new File(ROOT_DIR + "databases");
  folder.mkdirs();
  File outfile = new File(ROOT_DIR + "databases/" + "saved1.sqlite");
  if (outfile.length() <= 0) {
   AssetManager assetManager = ctx.getResources().getAssets(); // 파일읽어오기
   try {
    InputStream is = assetManager.open("saved1.sqlite",
      AssetManager.ACCESS_BUFFER);
    // 파일열어서 is에 저장하기
    long filesize = is.available();
    byte[] tempdata = new byte[(int) filesize];
    is.read(tempdata);
    is.close();
    outfile.createNewFile(); // 새파일생성
    FileOutputStream fo = new FileOutputStream(outfile); // 생성한파일
                  // 아웃풋스트림
    fo.write(tempdata); // 파일에다가 디비파일넣기
    fo.close();
   } catch (IOException e) {
    e.printStackTrace();
   }
  }
 }
  public void getValue(){
     
    db = dManager.getWritableDatabase();   
    String sql = "SELECT distinct knowledge, strength FROM character;";
      try{   
       
       Cursor cur = db.rawQuery(sql, null);
       
       while(cur.moveToNext()){               
        
        d_knowledge = cur.getString(cur.getColumnIndex("knowledge"));
        d_strength = cur.getString(cur.getColumnIndex("strength"));
             
       }       
       
     }catch (SQLException se) {
       // TODO: handle exception      
     }   
          db.close();
            
      k_v = Integer.parseInt(d_knowledge);
      s_v = Integer.parseInt(d_strength);
  }
   public void setValue(int k, int s){
  
  if(k > 100){ k = 100; }
  if(s < 0){ s = 0; }
    
  String str_k = String.valueOf(k);
  String str_s = String.valueOf(s);
      String sql = "", sql2 = "";
  db = dManager.getWritableDatabase();     
     
  sql = "update character set knowledge = '" + str_k + "';";
  sql2 = "update character set strength = '" + str_s + "';";
   
  db.execSQL(sql);
  db.execSQL(sql2);
  
  db.close();    
}   public void getDay(){
   
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
    
    String sql = "", sql2 = "";
    db = dManager.getWritableDatabase();
    db2 = dManager2.getWritableDatabase();    
    Study.pres +=10;
    sql2 = "update character set studycnt = '" + Study.pres +"';";
        
    if(d > 30){
     d = 1;
     sql = "update calendar set day = '" + d + "';"; 
    }   
    else sql = "update calendar set day = '" + d + "';";  
    
    db.execSQL(sql2);
    db.close();
    
  db2.execSQL(sql);
  db2.close();
   
  }
   
   public boolean onKeyDown(int keyCode, KeyEvent event) {
	      
       return true;
      }
}
