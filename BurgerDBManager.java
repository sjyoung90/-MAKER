package com.android.progBar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * DB 생성 및 업그레이드를 도와주는 도우미 클래스 만들기
 *  - SQLiteOpenHelper 추상 클래스를 상속받아서 만든다. - 
 */
public class BurgerDBManager extends SQLiteOpenHelper{
 
 public BurgerDBManager(Context context, String name, CursorFactory factory,
   int version) {
  super(context, name, factory, version);


 }
 //DB가 생성될 때 호출되는 메소드
 @Override
 public void onCreate(SQLiteDatabase db) {
  // TODO Auto-generated method stub
  //DB에 테이블 생성하기
  String sql = "CREATE TABLE burgergame"+"(num INTEGER PRIMARY KEY AUTOINCREMENT,"+
     "bur_name TEXT, bur_order TEXT, bur_pic TEXT);";
  //sql문 실행하기
  db.execSQL(sql);
  
  db.execSQL("insert into burgergame values(null, '불고기버거', 'zjcgmk', '양상추,불고기소스,고기,피클,토마토');");        
  db.execSQL("insert into burgergame values(null, '새우버거', 'zljdfm', '양파,양상추,화이트소스,새우,피클');");        
  db.execSQL("insert into burgergame values(null, '치킨버거', 'zikejd', '치즈,토마토,치킨,양상추,화이트소스');");        
  db.execSQL("insert into burgergame values(null, '빅맥', 'zijghijcg', '치즈,양상추,고기,빵,치즈,양상추,불고기소스,고기');");        
  db.execSQL("insert into burgergame values(null, '치즈버거', 'zigcm', '치즈,불고기,불고기소스,피클');");
  db.execSQL("insert into burgergame values(null, '더블치즈버거', 'zigcimjk', '치즈,불고기,불고기소스,치즈,피클,양상추,토마토');");        
  db.execSQL("insert into burgergame values(null, '스파이시새우버거', 'zdjifalkm', '화이트소스,양상추,치즈,새우패티,핫소스,양파,토마토,피클');");        
  db.execSQL("insert into burgergame values(null, '스파이시불고기버거', 'zcjmakla', '불고기소스,양상추,피클,핫소스,토마토,양파,핫소스');");
  db.execSQL("insert into burgergame values(null, '더블불고기버거', 'zbmgjhlgc', '불고기소스,고기,양파,중간빵,양상추,고기,피클,머스타드소스');");
  db.execSQL("insert into burgergame values(null, '스파이시치킨버거', 'ziajlemka', '치즈,핫소스,토마토,피클,치킨,양파,양상추,핫소스');");
    
  
 }


 //DB를 갈아 엎고 새로 만들 필요가 있을 때 호출되는 메소드
 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  // TODO Auto-generated method stub
  db.execSQL("DROP TABLE IF EXSITS burgergame");
  //새로 생성될 수 있도록 onCreate() 메소드를 생성한다.
  onCreate(db);
 }


}

