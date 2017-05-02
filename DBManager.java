package com.android.progBar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * DB ���� �� ���׷��̵带 �����ִ� ����� Ŭ���� �����
 *  - SQLiteOpenHelper �߻� Ŭ������ ��ӹ޾Ƽ� �����. - 
 */
public class DBManager extends SQLiteOpenHelper{
 
 public DBManager(Context context, String name, CursorFactory factory,
   int version) {
  super(context, name, factory, version);


 }
 //DB�� ������ �� ȣ��Ǵ� �޼ҵ�
 @Override
 public void onCreate(SQLiteDatabase db) {
  // TODO Auto-generated method stub
  //DB�� ���̺� �����ϱ�  
  String sql = "CREATE TABLE character"+"(cid INTEGER PRIMARY KEY AUTOINCREMENT,"+
     "knowledge TEXT, love TEXT, strength TEXT, money TEXT, name TEXT, university TEXT,major TEXT, pic TEXT, delay INTEGER, nonpayment INTEGER, nnonpayment INTEGER, stage TEXT, exam TEXT, examcnt INTEGER, studycnt INTEGER);";
  //sql�� �����ϱ�
  db.execSQL(sql);
  
 }


 //DB�� ���� ���� ���� ���� �ʿ䰡 ���� �� ȣ��Ǵ� �޼ҵ�
 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  // TODO Auto-generated method stub
  db.execSQL("DROP TABLE IF EXSITS character");
  //���� ������ �� �ֵ��� onCreate() �޼ҵ带 �����Ѵ�.
  onCreate(db);
 }


}

