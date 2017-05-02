package com.android.progBar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * DB ���� �� ���׷��̵带 �����ִ� ����� Ŭ���� �����
 *  - SQLiteOpenHelper �߻� Ŭ������ ��ӹ޾Ƽ� �����. - 
 */
public class DBManager2 extends SQLiteOpenHelper{
 
 public DBManager2(Context context, String name, CursorFactory factory,
   int version) {
  super(context, name, factory, version);


 }
 //DB�� ������ �� ȣ��Ǵ� �޼ҵ�
 @Override
 public void onCreate(SQLiteDatabase db) {
  // TODO Auto-generated method stub
  //DB�� ���̺� �����ϱ�
  String sql = "CREATE TABLE calendar"+"(cid INTEGER PRIMARY KEY AUTOINCREMENT,"+
     "order1 TEXT, ordercnt INTEGER, start TEXT, month INTEGER, day INTEGER);";
  //sql�� �����ϱ� 
  db.execSQL(sql);
    
 }

  
 //DB�� ���� ���� ���� ���� �ʿ䰡 ���� �� ȣ��Ǵ� �޼ҵ�
 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  // TODO Auto-generated method stub
  db.execSQL("DROP TABLE IF EXSITS calendar");
  //���� ������ �� �ֵ��� onCreate() �޼ҵ带 �����Ѵ�.
  onCreate(db);
 }


}

