package com.android.progBar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * DB 생성 및 업그레이드를 도와주는 도우미 클래스 만들기
 *  - SQLiteOpenHelper 추상 클래스를 상속받아서 만든다. - 
 */
public class DBManager3 extends SQLiteOpenHelper{
 
 public DBManager3(Context context, String name, CursorFactory factory,
   int version) {
  super(context, name, factory, version);


 }
 //DB가 생성될 때 호출되는 메소드
 @Override
 public void onCreate(SQLiteDatabase db) {
  // TODO Auto-generated method stub
  //DB에 테이블 생성하기  
  String sql = "CREATE TABLE event"+"(cid INTEGER PRIMARY KEY AUTOINCREMENT,"+
    "event_order TEXT, month INTEGER, day INTEGER, speak TEXT, exe TEXT, exe2 TEXT);";
  //sql문 실행하기
  db.execSQL(sql);
 
  String a = "안녕? 반가워~ 난 앞으로 너의 대학생활 4년동안을 함께 보낼 요정이야. " +
  		"이제 대학생이 되었으니까 공부도 열심히하고 아르바이트도 열심히해서 매달 학자금대출을 갚아야해. 내가 너를 도와줄게, 앞으로 잘 지내보자!!";
  
  String b = "이번주도 스케쥴을 잘 정해봤니?"+ "\n" + "그럼 이번주도 활기차게 시작해보자!!"; //스케쥴짜고왔을때 반복
  
  String c1 = "수고했어 :)"; //스케쥴다녀왔을때(반복):
  String c2 = "다음 활동을 하러가자 "; //스케쥴다녀왔을때(반복):
  String c3 = "오늘 기분은 좀 어때?"; //스케쥴다녀왔을때(반복):
  String c4 = "오늘 비온대 :< " + "\n" + "스케쥴 갈 때 우산 꼭 챙겨 ~"; //스케쥴다녀왔을때(반복):
  String c5 = "오늘은 참 날씨가 좋다 :)"; //스케쥴다녀왔을때(반복):
  String c6 = "계획한 것들은 잘 되가고 있어?" + "\n" + "너무 무리하지마^^"; //스케쥴다녀왔을때(반복):
  
  //이주지나고 스케쥴짤때(16일)
  String d1 = "벌써 이주가 지났구나, 이번주는 어떻게 보냈어~?" + "\n" + "다음주 계획은 정했어?" + "\n" + 
		  	"이번달에 재밌는 영화가 개봉했다는데 보러가는게 어때?";
  String d2 = "새로 스케쥴을 짜야하는 날이야^^ "+ "\n" + "특별한 계획이 없다면, 기분전환할겸 쇼핑하는건 어때? ";  
  String d3 = "수고했어 :)" + "\n" + "스케쥴 짜러 가볼까 ~? ";
  String d4 = "수고했어 :)" + "\n" + "스케쥴 짜러 가자^^";    

  //스케쥴짜고 와서  
  String f1 = "스케쥴은  잘 정했어? " + "\n" + "남은 스케쥴도 화이팅팅팅 !!!";
  String f2 = "이번 주도 신나게 활동을 시작해볼까 :D";
  String f3 = "너무 무리하지 말고 남은 날도 힘내서 화이팅!" + "\n" + "아자아자 !!!";  
  String f4 = "스케쥴 GOGO!!";

// 한달이지나고  
  String g = "이번달도 힘차게 시작해보자~^^";
		 // "새로운 달이 되었구나, 대출금은 잘냈니? 빚이 많아서 힘들지?" + "\n" + 
      //"아직 갚아야할 돈이 많이 남았지만 힘을내자 ";
  
//맨마지막
  String h = "벌써 너와 함께한 4년이 지났구나, 대학은 재미있었니? 후회없는 대학생활이었기를 바라" + "\n" + 
      "졸업후 너의 모습은.... ";

// 새해
  String e1 = "새해가 밝았습니다." + "\n" + "새해 福 많이 받으세요.";

// 겨울휴가
  String e2 = "겨울휴가를 가시겠습니까?" + "\n";
  String e2y = "스키장으로 떠납니다." + "\n" + "휴가비용으로 10만원이 차감되고 체력이 +50 되었습니다.";
    
// 토익
  String e3 = "토익시험이 있는 날입니다." + "\n" + "응시료 5만원이 차감됩니다.";

// 장학금
  String e4 = "장학금을 받았습니다." + "\n" + "열심히 공부하셨군요." + "\n" +" 다음 학기에도 열심히해서 또 장학금을 타시기 바랍니다.";

 // 설날
  String e5 = " 새해 福 많이 받으세요. " + "\n" + " 까치 까치 설날은 어저께고요 ♪ " + "\n" + 
		  " 우리 우리 설날은 오늘이래요 ♬"+ "\n" +" 설빔으로 10만원을 받았습니다. ";

// 생일
  String e6 = " 생일축하합니다 !! " + "\n" + " 용돈으로 10만원을 받았습니다. ";

// 어버이날
  String e7 = " 나실 제 괴로움 다 잊으시고  ♪ " + "\n" + " 기르실때 밤낮으로 애쓰는 마음 ♪ "+ "\n" +
     " 어버이날입니다 ! 부모님께 효도하세요 :~) " + "\n" + " 부모님 선물로 5만원이 차감됩니다. "; 

// 중간고사
  String e8 = " 중간고사 기간입니다. " + "\n" + " 한 학기동안 공부한 내용을 잘 기억하며  " + "\n" + " 시험을 잘치르시기 바랍니다. ";
 
// 공모전
  String e9 = " 공모전에 참가하시겠습니까? ";
  String e9y = " 공모전에 참가하여 지식지수가 +5 되었습니다. ";   

//여름휴가
  String e10 = " 여름휴가를 가시겠습니까? ";
  String e10y = " 해수욕장으로 떠납니다. " + "\n" + " 휴가비용으로 10만원이 차감되고 체력이 +50 되었습니다.";   

//추석
  String e11 = " 즐겁고 풍요로운 한가위입니다." + "\n" + " 보름달을 보며 소원을 빌어보세요. "+ "\n"
    +" 오랜만에 뵌 친척어른들께 용돈으로 10만원을 받았습니다. "; 

// 이달의 직원
  String e12 = " 축하합니다 !! " + "\n" + " 아르바이트를 하는 가게에서 이달의 직원으로 뽑혔습니다. "+ "\n"
    + " 보너스로 5만원이 지급되고 애정지수가 +5 되었습니다. ";
 
//용돈
  String e13 = " 부모님께서 용돈으로 10만원을 주셨습니다. ";
     
// 기말고사
  String e14 = " 기말고사 기간입니다. " + "\n" + " 한 학기동안 공부한 내용을 잘 기억하며 " + "\n" + " 시험을 잘치르시기 바랍니다. ";

// 구세군
  String e15 = " 연말이 다가오고 있습니다. " + "\n" + " 추운겨울, 어려운 이웃들을 돕는데 동참하시겠습니까? ";
  String e15y = " 구세군 모금함에 5만원을 기부하였습니다. ";     

//크리스마스
  String e16 = " ★  메리 크리스마스 ★ " + "\n" + " 행복한 성탄절입니다. " + "\n" + " 친구들과 즐거운 크리스마스 파티를 하시겠습니까? ";
  String e16y = " 파티비용으로 10만원이 차감되고 애정지수가 +5되었습니다.";

// 학자금
  String e171 = " 매월 4일은 학자금 대출금 상환일 입니다." + "\n" +" 이번달 대출금을 납입하시겠습니까?";
  String e17y = " 통장에서 40만원이 차감됩니다. ";
  String e17n = " 다음달로 상환금이 이월됩니다. ";
  
//추가남부일
  String e181 = " 이번달 19일은 학자금 추가납부기간입니다. " + "\n" + " 추가납부를 하시겠습니까? ";
  
  
  db.execSQL("insert into event values(null,'a1', '2', '1', '" + a +"','0','0');");  
  db.execSQL("insert into event values(null,'a2', '3', '1', '" + null +"','0','0');");  
 
  db.execSQL("insert into event values(null,'c1', 'null', '4', '" + c1 +"','0','2');"); 
  db.execSQL("insert into event values(null,'c2', 'null', '7', '" + c2 +"','0','2');"); 
  db.execSQL("insert into event values(null,'c3', 'null', '10', '" + c3 +"','0','2');"); 
  db.execSQL("insert into event values(null,'c4', 'null', '13', '" + c4 +"','0','2');"); 
  db.execSQL("insert into event values(null,'c5', 'null', '16', '" + c1 +"','0','2');"); 
  db.execSQL("insert into event values(null,'c6', null, '19', '" + c6 +"','0','2');"); 
  db.execSQL("insert into event values(null,'c7', null, '22', '" + c2 +"','0','2');"); 
  db.execSQL("insert into event values(null,'c8', null, '25', '" + c5 +"','0','2');"); 
  db.execSQL("insert into event values(null,'c9', null, '28', '" + c1 +"','0','2');"); 

  db.execSQL("insert into event values(null,'d1', null, '16', '" + d1 +"','0','2');");  
  db.execSQL("insert into event values(null,'d2', null, '16', '" + d2 +"','0','2');");  
  db.execSQL("insert into event values(null,'d3', null, '16', '" + d3 +"','0','2');");  
  db.execSQL("insert into event values(null,'d4', null, '16', '" + d4 +"','0','2');");  
  
  db.execSQL("insert into event values(null,'f1', null, '16', '" + f1 +"','0','2');");  
  db.execSQL("insert into event values(null,'f2', null, '16', '" + f2 +"','0','2');");  
  db.execSQL("insert into event values(null,'f3', null, '16', '" + f3 +"','0','2');");  
  db.execSQL("insert into event values(null,'f4', null, '16', '" + f4 +"','0','2');");  

  db.execSQL("insert into event values(null,'g', null, '1', '" + g +"','0','2');");  
  db.execSQL("insert into event values(null,'h', '2', '30', '" + h +"','0','0');");   
  
  db.execSQL("insert into event values(null,'e1', '1', '1', '" + e1 +"', '0','0');");  //새해
  db.execSQL("insert into event values(null,'e2', '1', '28', '" + e2 +"', '0','0');"); //겨울여행  
  db.execSQL("insert into event values(null,'e3', '1', '25', '" + e3 +"', '0','0');");  //토익 
  db.execSQL("insert into event values(null,'e33', '3', '25', '" + e3 +"', '0','0');");  //토익
  db.execSQL("insert into event values(null,'e34', '4', '25', '" + e3 +"', '0','0');");  //토익 
  db.execSQL("insert into event values(null,'e37', '7', '25', '" + e3 +"', '0','0');");  //토익 
  db.execSQL("insert into event values(null,'e39', '9', '25', '" + e3 +"', '0','0');");  //토익 
  db.execSQL("insert into event values(null,'e311', '11', '25', '" + e3 +"', '0','0');");  //토익 
  db.execSQL("insert into event values(null,'e4', '2', '7', '" + e4 +"', '0','0');");   //장학금
  db.execSQL("insert into event values(null,'e5', '2', '13', '" + e5 +"', '0','0');");  //설날
  db.execSQL("insert into event values(null,'e6', '3', '22', '" + e6 +"', '0','0');");  //생일
  db.execSQL("insert into event values(null,'e7', '5', '7', '" + e7 +"', '0','0');");  //어버이날
  db.execSQL("insert into event values(null,'e8', '6', '25', '" + e8 +"', '0','0');");  //중간고사 25~30
  db.execSQL("insert into event values(null,'e9', '7', '10', '" + e9 +"', '0','0');");  //공모전
  db.execSQL("insert into event values(null,'e10', '8', '28', '" + e10 +"', '0','0');");  //여름휴가
  db.execSQL("insert into event values(null,'e11', '9', '22', '" + e11 +"', '0','0');");  //추석
  db.execSQL("insert into event values(null,'e12', '10', '4', '" + e12 +"', '0','0');");  //이달의 직원
  db.execSQL("insert into event values(null,'e13', '11', '16', '" + e13 +"', '0','0');");  //용돈
  db.execSQL("insert into event values(null,'e14', '12', '7', '" + e14 +"', '0','0');");  //기말고사 12.7~12
  db.execSQL("insert into event values(null,'e15', '12', '22', '" + e15 +"', '0','0');");  //구세군
  db.execSQL("insert into event values(null,'e16', '12', '25', '" + e16 +"', '0','0');");  //크리스마스
  db.execSQL("insert into event values(null,'e171', '1', '4', '" + e171 +"', '0','0');"); //학자금
  db.execSQL("insert into event values(null,'e172', '2', '4', '" + e171 +"', '0','0');"); //학자금
  db.execSQL("insert into event values(null,'e173', '3', '4', '" + e171 +"', '0','0');"); //학자금
  db.execSQL("insert into event values(null,'e174', '4', '4', '" + e171 +"', '0','0');"); //학자금
  db.execSQL("insert into event values(null,'e175', '5', '4', '" + e171 +"', '0','0');"); //학자금
  db.execSQL("insert into event values(null,'e176', '6', '4', '" + e171 +"', '0','0');"); //학자금
  db.execSQL("insert into event values(null,'e177', '7', '4', '" + e171 +"', '0','0');"); //학자금
  db.execSQL("insert into event values(null,'e178', '8', '4', '" + e171 +"', '0','0');"); //학자금
  db.execSQL("insert into event values(null,'e179', '9', '4', '" + e171 +"', '0','0');"); //학자금
  db.execSQL("insert into event values(null,'e1710', '10', '4', '" + e171 +"', '0','0');"); //학자금
  db.execSQL("insert into event values(null,'e1711', '11', '4', '" + e171 +"', '0','0');"); //학자금
  db.execSQL("insert into event values(null,'e1712', '12', '4', '" + e171 +"', '0','0');"); //학자금  
  db.execSQL("insert into event values(null,'e181', '4', '19', '" + e181 +"', '0','0');"); //학자금추가납부
  db.execSQL("insert into event values(null,'e182', '8', '19', '" + e181 +"', '0','0');"); //학자금추가납부
  db.execSQL("insert into event values(null,'e183', '11', '19', '" + e181 +"', '0','0');"); //학자금추가납부

  
 }

 //DB를 갈아 엎고 새로 만들 필요가 있을 때 호출되는 메소드
 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  // TODO Auto-generated method stub
  db.execSQL("DROP TABLE IF EXSITS event");
  //새로 생성될 수 있도록 onCreate() 메소드를 생성한다.
  onCreate(db);
 }

}

