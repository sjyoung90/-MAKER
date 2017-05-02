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
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Exam extends Activity {
    /** Called when the activity is first created. */      
 
 DBManager dManager;
 DBManager2 dManager2;
 SQLiteDatabase db, db2;
 private static final String DATABASE_NAME = "character.db";
 private static final String DATABASE_NAME2 = "calendar.db";
 int d_day = 1;
 String d_knowledge, d_exam;
 int k_v, pres, prev;
 
 SQLiteDatabase mDatabase, mDatabase2, mDatabase3; 
 String voca[] = new String[5];
 char test[] = new char[20];
 String show = "", str4 = "";
 static String score = "";
 String str, str2, str3;
 TextView eng, kor, ans, ans2;
 EditText eb;
 Button btn;
 int rand = 50;
 int num, tem, len, testR;
 int correct = 0, wrong = 0, count = 0;
 static int ti3, know;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                
        setContentView(R.layout.exam);        
        
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
        
       // Toast.makeText(Exam.this, "" + d_exam, Toast.LENGTH_SHORT).show();
        
        eng = (TextView) findViewById(R.id.text);
        kor = (TextView) findViewById(R.id.text2);
        ans = (TextView) findViewById(R.id.text3);  //
        ans2 = (TextView) findViewById(R.id.text4); //
        eb = (EditText) findViewById(R.id.editText1);
        btn = (Button) findViewById(R.id.button1);
  
        takeInfo(); 
        initialize(this);
        setAdaptor();
    }

    public void takeInfo() {
    	initialize2(this);

  if (mDatabase2 == null) {
	  mDatabase2 = openOrCreateDatabase(DATABASE_TOTAL, Context.MODE_PRIVATE, null);
	  // �����ͺ��̽�����
  }

  Cursor cursor = null;
  //final CursorAdapter adaptor;
  String[] columns = new String[] { "prev", "pres", "know" };
  cursor = mDatabase2.query(TABLE_NAME2, columns, null, null, null, null, null);

  while (cursor.moveToNext()) {
 
   int prev = cursor.getInt(cursor.getColumnIndex("prev"));
   int pres = cursor.getInt(cursor.getColumnIndex("pres"));
   int kno = cursor.getInt(cursor.getColumnIndex("know"));
   prev = prev;
   pres = pres;
   ti3 = kno;
  }
 }

 public void test() { //���õ� �ܾ��߿� �� ���ĺ��� �������� �������� ���� �Լ�
  double rand = Math.random();
  int ir = (int) (rand * 10);
  len = str.length();
  tem = ir % len;

  for (int i = 0; i < len; i++) {
   if (i == tem) {  //���õ� ���� ���� _�� ǥ��
    show += "_";  
    str4 += test[i];  // �������� �Ѱ���
   } else
    show += test[i];
  }
   eng.setText(show);
   kor.setText(str2);  
 }
 
 public static final String ROOT_DIR = "/data/data/com.android.progBar/";
 private static final String DATABASE_NAME3 = "daneo.sqlite";
 private static final String DATABASE_TOTAL = "saved1.sqlite";
 public static final String TABLE_NAME = "voc";
 public static final String TABLE_NAME2 = "saved1";
 private static final String COLUMN_NUMBER = "num";
 private static final String COLUMN_ENGL = "eng";
 private static final String COLUMN_KORE = "kor";
 
 public static void initialize(Context ctx) {  //DB�� ���Ϸθ���
  // check
  File folder = new File(ROOT_DIR + "databases");
  folder.mkdirs();
  File outfile = new File(ROOT_DIR + "databases/" + DATABASE_NAME3);
  if (outfile.length() <= 0) {
   AssetManager assetManager = ctx.getResources().getAssets(); // �����о����
   try {
    InputStream is = assetManager.open(DATABASE_NAME3,
      AssetManager.ACCESS_BUFFER);
    // ���Ͽ�� is�� �����ϱ�
    long filesize = is.available();
    byte[] tempdata = new byte[(int) filesize];
    is.read(tempdata);
    is.close();

    outfile.createNewFile(); // �����ϻ���
    FileOutputStream fo = new FileOutputStream(outfile); // ����������
                  // �ƿ�ǲ��Ʈ��
    fo.write(tempdata); // ���Ͽ��ٰ� ������ϳֱ�
    fo.close();
   } catch (IOException e) {
    e.printStackTrace();
   }
  }
 }

 public static void initialize2(Context ctx) { // DB�� ���Ϸ� ����� �Լ� 
  // check
  File folder = new File(ROOT_DIR + "databases");
  folder.mkdirs();
  File outfile = new File(ROOT_DIR + "databases/" + DATABASE_TOTAL);
  if (outfile.length() <= 0) {
   AssetManager assetManager = ctx.getResources().getAssets(); // �����о����
   try {
    InputStream is = assetManager.open(DATABASE_TOTAL,
      AssetManager.ACCESS_BUFFER);
    // ���Ͽ�� is�� �����ϱ�
    long filesize = is.available();
    byte[] tempdata = new byte[(int) filesize];
    is.read(tempdata);
    is.close();

    outfile.createNewFile(); // �����ϻ���
    FileOutputStream fo = new FileOutputStream(outfile); // ����������
                  // �ƿ�ǲ��Ʈ��
    fo.write(tempdata); // ���Ͽ��ٰ� ������ϳֱ�
    fo.close();
   } catch (IOException e) {
    e.printStackTrace();
   }
  }
 }

 private void setAdaptor() { //DB���� �о����

  if (mDatabase == null) {
   mDatabase = openOrCreateDatabase(DATABASE_NAME3,
     Context.MODE_PRIVATE, null);
   // �����ͺ��̽�����
  }

  double te = Math.random(); 
  int ran = (int) (te * 1000);
  tem = ran % (pres - prev) + prev + 1; //������� ������ �ܾ �������� �̾ƿ��� ���� TEM�̶�� �������� 
  String temp = String.valueOf(tem); 
  ans2.setText(temp);//

  Cursor cursor = null;  // DBĿ��
  //final CursorAdapter adaptor;
  String[] columns = new String[] { COLUMN_NUMBER, COLUMN_ENGL,
    COLUMN_KORE };
  // COLUMN_NUMBER +"=" + rand

  cursor = mDatabase.query(TABLE_NAME, columns, COLUMN_NUMBER + "=" + tem, null, null, null, null);  //TEM���� �ش��ϴ� DB�÷��� �����Ѵ�

  while (cursor.moveToNext()) { 
  
   int number = cursor.getInt(cursor.getColumnIndex("num")); 
   String eng = cursor.getString(cursor.getColumnIndex("eng"));
   String kor = cursor.getString(cursor.getColumnIndex("kor"));
   str = eng;
   str2 = kor;
   num = number;
  }
  for (int i = 0; i < str.length(); i++)
   test[i] = str.charAt(i);  
  test(); //��ĭ�־��ִ� �Լ�

 }

 public void check(View v) {  //��ư�Լ�
 
if (count < 9) {  //9��°���������� ��ư������ �������� �Ѿ 
   str3 = eb.getText().toString(); //�Է°�

   if (str3.equals(str4)) { //�Է°��� ���䰪 ��
    Toast.makeText(Exam.this, "�����Դϴ�!", Toast.LENGTH_SHORT).show();
    correct++;
   } else {
    Toast.makeText(Exam.this, "������ �ƴմϴ�!", Toast.LENGTH_SHORT).show();
    wrong++;
   }
   count++; //������
   rand++; 
   str4 = ""; //�ʱ�ȭ
   show = "";
   eb.setText("");
   setAdaptor(); // DB���� ���������θ��� �Լ�
  } else { //���������� 
   str3 = eb.getText().toString();

   if (str3.equals(str4)) {
    Toast.makeText(Exam.this, "�����Դϴ�!", Toast.LENGTH_SHORT).show();
    correct++;
   } else {
    Toast.makeText(Exam.this, "������ �ƴմϴ�!", Toast.LENGTH_SHORT).show();
    wrong++;
   }
   
   AlertDialog.Builder alertDlg = new AlertDialog.Builder(Exam.this);   
   alertDlg.setTitle("���");
   
   if (correct > 8) {
	    score = "A";
	    if(d_exam.equalsIgnoreCase("0")) d_exam = score;
	    else d_exam += score;
	    alertDlg.setMessage("\n" + "���� ���� : " + correct + "�� \n" + "����� ������ " + score + "�Դϴ�." + "\n");
	    }
	   else if (5 < correct && correct < 9) {
	    score = "B";
	    if(d_exam.equalsIgnoreCase("0")) d_exam = score;
	    else d_exam += score;
	    alertDlg.setMessage("\n" + "���� ���� : " + correct + "�� \n" + "����� ������ " + score + "�Դϴ�." + "\n");
	   }
	   else {
	    score = "F";
	    if(d_exam.equalsIgnoreCase("0")) d_exam = score;
	    else d_exam += score;
	    alertDlg.setMessage("\n" + "���� ���� : " + correct + "�� \n" + "����� ������ " + score + "�Դϴ�." + "\n"
	      + "������ 30 �����մϴ�." + "\n");
	    k_v -= 30;    
	    
	   }   
   
   alertDlg.setPositiveButton("Ȯ��", new DialogInterface.OnClickListener() {
    public void onClick(DialogInterface dialog, int whichButton) {    	
    	setValue(k_v, d_exam);
        d_day = d_day + 6;
        setDay(d_day);
        startActivity(new Intent(Exam.this, Main.class));    	
    }    
   });
   alertDlg.show() ;
   
  }
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
     String sql = "SELECT distinct knowledge, exam, examcnt, studycnt FROM character;";
    
       try{   
        
        Cursor cur = db.rawQuery(sql, null);
        
        while(cur.moveToNext()){
        
         d_knowledge = cur.getString(cur.getColumnIndex("knowledge"));
         d_exam = cur.getString(cur.getColumnIndex("exam"));
         prev = cur.getInt(cur.getColumnIndex("examcnt"));
         pres = cur.getInt(cur.getColumnIndex("studycnt"));         
              
        }       
        
      }catch (SQLException se) {
        // TODO: handle exception      
      }   
         db.close();          
      
       k_v = Integer.parseInt(d_knowledge);
   }
   
   public void setValue(int k, String s){
    
    if(k < 0) { k = 0; }
    String str_k = String.valueOf(k);
    
    String sql = "", sql2 = "", sql3 = "";
    db = dManager.getWritableDatabase();
    
    sql = "update character set knowledge = '" + str_k + "';";
    sql2 = "update character set exam = '" + s + "';";
    sql3 = "update character set examcnt = '" + pres + "' ;";
    
    db.execSQL(sql);
    db.execSQL(sql2);
    db.execSQL(sql3);
    
    db.close();
    }
   
   public boolean onKeyDown(int keyCode, KeyEvent event) {
	      
       return true;
      
   }
}
