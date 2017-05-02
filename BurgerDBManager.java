package com.android.progBar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * DB ���� �� ���׷��̵带 �����ִ� ����� Ŭ���� �����
 *  - SQLiteOpenHelper �߻� Ŭ������ ��ӹ޾Ƽ� �����. - 
 */
public class BurgerDBManager extends SQLiteOpenHelper{
 
 public BurgerDBManager(Context context, String name, CursorFactory factory,
   int version) {
  super(context, name, factory, version);


 }
 //DB�� ������ �� ȣ��Ǵ� �޼ҵ�
 @Override
 public void onCreate(SQLiteDatabase db) {
  // TODO Auto-generated method stub
  //DB�� ���̺� �����ϱ�
  String sql = "CREATE TABLE burgergame"+"(num INTEGER PRIMARY KEY AUTOINCREMENT,"+
     "bur_name TEXT, bur_order TEXT, bur_pic TEXT);";
  //sql�� �����ϱ�
  db.execSQL(sql);
  
  db.execSQL("insert into burgergame values(null, '�Ұ�����', 'zjcgmk', '�����,�Ұ��ҽ�,���,��Ŭ,�丶��');");        
  db.execSQL("insert into burgergame values(null, '�������', 'zljdfm', '����,�����,ȭ��Ʈ�ҽ�,����,��Ŭ');");        
  db.execSQL("insert into burgergame values(null, 'ġŲ����', 'zikejd', 'ġ��,�丶��,ġŲ,�����,ȭ��Ʈ�ҽ�');");        
  db.execSQL("insert into burgergame values(null, '���', 'zijghijcg', 'ġ��,�����,���,��,ġ��,�����,�Ұ��ҽ�,���');");        
  db.execSQL("insert into burgergame values(null, 'ġ�����', 'zigcm', 'ġ��,�Ұ��,�Ұ��ҽ�,��Ŭ');");
  db.execSQL("insert into burgergame values(null, '����ġ�����', 'zigcimjk', 'ġ��,�Ұ��,�Ұ��ҽ�,ġ��,��Ŭ,�����,�丶��');");        
  db.execSQL("insert into burgergame values(null, '�����̽û������', 'zdjifalkm', 'ȭ��Ʈ�ҽ�,�����,ġ��,������Ƽ,�ּҽ�,����,�丶��,��Ŭ');");        
  db.execSQL("insert into burgergame values(null, '�����̽úҰ�����', 'zcjmakla', '�Ұ��ҽ�,�����,��Ŭ,�ּҽ�,�丶��,����,�ּҽ�');");
  db.execSQL("insert into burgergame values(null, '����Ұ�����', 'zbmgjhlgc', '�Ұ��ҽ�,���,����,�߰���,�����,���,��Ŭ,�ӽ�Ÿ��ҽ�');");
  db.execSQL("insert into burgergame values(null, '�����̽�ġŲ����', 'ziajlemka', 'ġ��,�ּҽ�,�丶��,��Ŭ,ġŲ,����,�����,�ּҽ�');");
    
  
 }


 //DB�� ���� ���� ���� ���� �ʿ䰡 ���� �� ȣ��Ǵ� �޼ҵ�
 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  // TODO Auto-generated method stub
  db.execSQL("DROP TABLE IF EXSITS burgergame");
  //���� ������ �� �ֵ��� onCreate() �޼ҵ带 �����Ѵ�.
  onCreate(db);
 }


}

