package com.android.progBar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * DB 생성 및 업그레이드를 도와주는 도우미 클래스 만들기
 *  - SQLiteOpenHelper 추상 클래스를 상속받아서 만든다. - 
 */
public class DBManager extends SQLiteOpenHelper{
 
 public DBManager(Context context, String name, CursorFactory factory,
   int version) {
  super(context, name, factory, version);


 }
 //DB가 생성될 때 호출되는 메소드
 @Override
 public void onCreate(SQLiteDatabase db) {
  // TODO Auto-generated method stub
  //DB에 테이블 생성하기  
  String sql = "CREATE TABLE character"+"(cid INTEGER PRIMARY KEY AUTOINCREMENT,"+
     "knowledge TEXT, love TEXT, strength TEXT, money TEXT, name TEXT, university TEXT,major TEXT, pic TEXT, delay INTEGER, nonpayment INTEGER, nnonpayment INTEGER, stage TEXT, exam TEXT, examcnt INTEGER, studycnt INTEGER);";
  //sql문 실행하기
  db.execSQL(sql);
  
 }


 //DB를 갈아 엎고 새로 만들 필요가 있을 때 호출되는 메소드
 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  // TODO Auto-generated method stub
  db.execSQL("DROP TABLE IF EXSITS character");
  //새로 생성될 수 있도록 onCreate() 메소드를 생성한다.
  onCreate(db);
 }


}

