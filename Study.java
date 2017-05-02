package com.android.progBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class Study extends Activity {
    /** Called when the activity is first created. */      
 
 DBManager dManager;
 DBManager2 dManager2;
 SQLiteDatabase db, db2;
 private static final String DATABASE_NAME = "character.db";
 private static final String DATABASE_NAME2 = "calendar.db";
 int d_day = 1;
 
 TextView kor1,kor2,eng1,eng2;
 SQLiteDatabase mDatabase, mDatabase2;
 String str1, str2;
 static int ti2;
 int num, ti1, ti3;
 static String vocaE[] = new String[1000];
 static String vocaK[] = new String[1000];
 static int numk[] = new int[1000];
 int count = 0;
 static int prev, pres;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                
        setContentView(R.layout.study);
        
        kor1 = (TextView)findViewById(R.id.kor1);
        kor2 = (TextView)findViewById(R.id.kor2);
        eng1 = (TextView)findViewById(R.id.eng1);
        eng2 = (TextView)findViewById(R.id.eng2);
        
        
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
        
        initialize(this);
        setAdaptor(); //단어 정보가져오기
        getValue();
        
    }
    
    public static final String ROOT_DIR = "/data/data/com.android.progBar/";
    private static final String DATABASE_NAME3 = "daneo.sqlite";
    public static final String TABLE_NAME = "voc";
 private static final String COLUMN_NUMBER = "num";
 private static final String COLUMN_ENGL = "eng";
 private static final String COLUMN_KORE = "kor";
 
 public static void initialize(Context ctx) {
  // check 
  File folder = new File(ROOT_DIR + "databases");
  folder.mkdirs();
  File outfile = new File(ROOT_DIR + "databases/" + DATABASE_NAME3);
  if (outfile.length() <= 0) {
   AssetManager assetManager = ctx.getResources().getAssets(); //파일읽어오기
   try {
    InputStream is = assetManager.open(DATABASE_NAME3, AssetManager.ACCESS_BUFFER);
    //파일열어서 is에 저장하기 
    long filesize = is.available();
    byte [] tempdata = new byte[(int)filesize];
    is.read(tempdata); 
    is.close();
    
    outfile.createNewFile(); //새파일생성
    FileOutputStream fo = new FileOutputStream(outfile); //생성한파일 아웃풋스트림
    fo.write(tempdata); //파일에다가 디비파일넣기
    fo.close();
   } catch (IOException e) {
    e.printStackTrace();
   }
  }
 }
    
 
 public void getValue(){
     
    db = dManager.getWritableDatabase();   
    String sql = "SELECT distinct examcnt, studycnt FROM character;";
      try{   
       
       Cursor cur = db.rawQuery(sql, null);
       
       while(cur.moveToNext()){


        prev = cur.getInt(cur.getColumnIndex("examcnt"));
        pres = cur.getInt(cur.getColumnIndex("studycnt"));
             
       }       
       
     }catch (SQLException se) {
       // TODO: handle exception      
     }   
          db.close();
  }
private void setAdaptor() {
     
  if (mDatabase == null) {
   mDatabase = openOrCreateDatabase(DATABASE_NAME3, Context.MODE_PRIVATE, null);
   //데이터베이스열기
  }


  Cursor cursor = null;
  //final CursorAdapter adaptor;
  String[] columns = new String[] {COLUMN_NUMBER, COLUMN_ENGL, COLUMN_KORE};   
  cursor = mDatabase.query(TABLE_NAME, columns, COLUMN_NUMBER +">=" + pres, null, null, null, null);  //ti2에 pres값이 저장되어있음 
  int i = 0;
  
  while(cursor.moveToNext()){


      int number = cursor.getInt(cursor.getColumnIndex("num"));
      String eng = cursor.getString(cursor.getColumnIndex("eng"));
      String kor = cursor.getString(cursor.getColumnIndex("kor"));
      
      str1 = eng;
      str2 = kor;
      num = number; 
      
      vocaE[i] = str1;
      vocaK[i] = str2;
      numk[i] = num;
      i++;
      
 }
  showV();
   


    }


 public void showV(){
 eng1.setText(vocaE[count]);
 eng2.setText(vocaE[count+1]);
 kor1.setText(vocaK[count]);
 kor2.setText(vocaK[count+1]);
  
 count+=2;
  
 }
 
 public void screenT(View v){
 if(count==10){
     Intent intent = new Intent(Study.this, StudyTest.class);
   startActivity(intent);
    }
 else showV();
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
   
   public boolean onKeyDown(int keyCode, KeyEvent event) {
	      
       return true;
      
   }
   
}
