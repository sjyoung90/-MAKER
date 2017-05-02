package com.android.progBar;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


/*
 * DB 생성 및 업그레이드를 도와주는 도우미 클래스 만들기
 *  - SQLiteOpenHelper 추상 클래스를 상속받아서 만든다. -
 */
public class DBManager4 extends SQLiteOpenHelper{
 
 public DBManager4(Context context, String name, CursorFactory factory,
   int version) {
  super(context, name, factory, version);




 }
 //DB가 생성될 때 호출되는 메소드
 @Override
 public void onCreate(SQLiteDatabase db) {
  // TODO Auto-generated method stub
  //DB에 테이블 생성하기  
  String sql = "CREATE TABLE result"+"(rowid INTEGER PRIMARY KEY AUTOINCREMENT,"+"rid TEXT, job TEXT, final TEXT, point TEXT, major TEXT);";


  //sql문 실행하기
  db.execSQL(sql);
 
  //교육대
  //선생님AA
  String a = "임용고시에 붙어 훌륭한 선생님이 되었다."+"\n"+
             "학교생활을 열심히 했기 때문에 빚도 많이 갚아 빚이 이제 얼마 남지 않았다."+"\n"+
             "현재 남고에서 수업도 잘하고 얼굴과 몸매도 이쁜 인기선생님으로 남학생들의 열렬한 지지를 받고 있다"+"\n"+
             "남자친구와의 연애도 순조롭다. 학생들이 열심히 강의를 듣는 모습을 보면 선생님이 되길 잘했다는 생각이 든다."+"\n"+
             "모두가 존경하는 선생님이 되도록 더 노력하자!";
  //노량진 인기강사AA
  String b = "실력있는 노량진 인기강사가 되었다."+"\n"+ 
             "전국 1위 유명강사가 되어 돈을 많이 벌고 있어 남은 빚도 곧 다 갚을 수 있다."+"\n"+
             "인강도 TOP 1위를 놓치지 않는 탑클래스 인기강사이다."+"\n"+  
             "뛰어난 미모로 주위에 남자들도 많다."+"\n"+ 
             "남자친구와의 연애도 잘되어가는 중이다. "+"\n"+
             "돈을 많이 모아서 좋은 집과 차도 샀다. "+"\n"+
             "지금 현재 나 자신에게 만족하지 않고 계속 노력한다면 전국 1위 미모의 유명강사 자리를 계속 유지할 수 있을 것이다.";
  
  //선생님AB 
  String c = "임용고시에 붙어 훌륭한 선생님이 되었다."+"\n"+ 
             "아직 빚이 많이 남아있지만 이대로라면 앞으로 충분히 갚아나갈 수 있을 것이다."+"\n"+ 
             "돈을 많이 모으고 싶은 꿈이 있지만 빚이 많이 남아 돈을 모으려면 시간이 좀 걸릴 것 같다."+"\n"+ 
             "현재 여고에서 수업 잘하는 선생님으로 유명하지만, 뛰어난 미모와 몸매로 여학생들의 시기와 질투를 받고 있다."+"\n"+ 
             "그 동안 못해본 연애를 해보려고 여기저기서 소개팅을 받고 있다."+"\n"+
             " 현재 자금난에 조금 힘들더라도 시간이 지나면 여유로운 삶을 살 수 있을 것이다.";
  
 //노량진 인기강사 ab
  String d = "실력있는 노량진 인기강사가 되었다."+"\n"+
             "남은 빚이 많지만 인기강사가 되어 많은 돈을 받고 있다."+"\n"+ 
             "겉으로 보기에는 돈이 많아 화려한 삶을 사는 것 같지만 현실은 빚 갚는 데에 돈이 다 나가버려서 모이는 돈이 없다."+"\n"+
             "막 시작한 연애는 순조롭게 진행중이다."+"\n"+ 
             "지금은 자금난에 고생하고 있지만 이 시기가 지나면 돈을 많이 모아서 여유롭고 풍족한 삶을 살게 될 것이다.";
 //학원강사 BA
  String e = "동네 학원의 강사가 되었다."+"\n"+ 
             "빚은 많이 갚아서 남은 빚은 얼마 없지만 현재 동기들은 다들 유명한 선생님으로 활약 하고 있다."+"\n"+ 
             "잘 나가는 친구들이 부럽기만 하다."+"\n"+
             "학생들의 불타는 학구열로 나를 비롯한 선생님들은 밤낮이 바뀌어 연애도 못하고 돈도 많이 못벌고 하루하루 반복되는 재미없는 생활 중이다."+"\n"+ 
             "더 좋은 조건에서 일할 수 있게 더 노력하길...";
   
  //임용준비BA
  String f = "임용고시생이 되었다."+"\n"+ 
             "빚은 많이 갚아서 남은 빚은 얼마 없지만 계속 임용고시에 떨어져 어제도 오늘도 매일매일 머리를 싸매고 공부중이다."+"\n"+  
             "연애는 생각조차 못한다."+"\n"+ 
             "하루하루가 힘든 나날이지만 앞으로의 날들을 생각하며 포기하지 않는다면 남들보다 늦더라도 임용고시에 붙어 선생님이 될 수 있을 것이다.";


  //의과대
  //의사aa
  String g = "열심히 공부한 결과, 국가 고시를 한방에 합격하고 현재 촉망받는 레지전트로 일하고 있다."+"\n"+ 
    "명인대학병원의 장민준의사 밑에서 이쁨 받으며 차근차근 엘리트 코스를 밟고 있다."+"\n"+
      "비록 몸은 힘들지만 자신의 꿈을 위해 힘을 내고 있다.";


  //연구원aa
  String h = "4년동안 남들 놀 때 열심히 공부한 결과, 대학원 생활도 성공적으로 마쳐 연구원이 되었다."+"\n"+ 
    "일년 내내 쪽잠을 자며 다이어트약인 졸윈다를 개발하였다."+"\n"+ 
    "전 세계적으로 주목받는 이 약은 미국의 골치병이었던 비만을 해결하였으며 어린 나이에 노벨상 후보에 오르는 영광을 주었다."+"\n"+
    "헐리우드 영화배우 잭화이트가 다이어트에 성공했다며 시상식에서 공개 청혼을 해 세계인들의 관심을 받고 있다.";


  //의사ab
  String i = "열심히 공부한 결과, 의사가 되었지만 레지던트로서 본인도 바쁘지만 핸드폰도 쉴 새 없이 바쁘다."+"\n"+ 
      "학자금을 다 갚지 못해 대부업체의 힘을 빌려쓴 댓가로 하루가 멀다하고 돈을 갚으라는 독촉 전화가 쇄도하기 때문이다."+"\n"+ 
      "열심히 일하며 돈을 벌지만 돈이 다 빚을 갚는데 사용되기 때문에 점점 힘이 빠진다."+"\n"+ 
      "그러나 빚 청산하는 날이 하루하루 다가오기 때문에 오늘도 힘을 내어 일을 한다.";


  //연구원ab
  String j = "뛰어난 성적으로 졸업해 연구소에서 일을 하게 되었다."+"\n"+ 
             "좋은 직업을 가졌지만 빨리 빚을 갚겠다는 일념하에 신약을 개발하여 대박을 터트릴 생각만 가지고 있다."+"\n"+ 
             "신약 연구에 몰두해 연구소를 집삼아 생활하였고 연구와 돈에 찌들어 아직도 모태솔로를 벗어나질 못하고 있다.";


  //국가고시생ba
  String k = "돈을 갚는데 너무 열중한 나머지 학업을 다소 소홀히 하여 아직도 국가 고시를 준비하고 있다."+"\n"+ 
             "빚은 다 갚았지만 친구들은 모두 국가고시에 합격하여 레지던트를 하고 있는데 홀로 고시생이라 동창회에도 못나가는 신세이다."+"\n"+
             "비록 좀 늦겠지만 후에 의사가 될 자신의 모습을 상상하면서 오늘도 공부를 하고 있다.";


  //불법시술업자ba
  String l = "돈을 좋아했던 학창시절, 의대생이라는 것을 이용해 불법 시술 아르바이트를 통해서 돈을 모아 빚도 다갚고 제법 여유로운 생활을 하였다."+"\n"+
             "그러나 돈의 맛을 보는 바람에 불법 시술의 늪에서 벗어나질 못하고 좀 더 다양한 불법 시술을 시행하였다."+"\n"+ 
             "그 결과 현재 어느 친구들보다도 풍족한 삶을 살지만 어두운 곳에서 벗어나질 못하는 신세가 되었다.";


//  공과대


  //대기업 (AA)
  String m = "대학시절, 비록 공대 아름이는 되지 못했지만 높은 비율의 남자들 사이에서 열심히 공부하였다."+"\n"+
        "좋은 학점과 다수의 아르바이트로 대기업 취직에 성공!"+"\n"+
        "머지않아 내손으로 등록금을 갚아나갈 것이다.";
   
  //연구원 (AA)
  String n = "대학시절, 비록 공대 아름이는 되지 못했지만 높은 비율의 남자들 사이에서 열심히 공부하였다."+"\n"+
        "신기술 개발로 최연소 노벨상 후보에 오르며 승승장구하였다."+"\n"+
             "하바드 공대의 요청으로 미국의 개인 연구실에서 일하고 있다."+"\n"+
             "조금 남아있던 등록금도 하바드에서 해결해주었다.";
   
  //대기업 (AB)
  String o = "대학시절, 비록 공대 아름이는 되지 못했지만 높은 비율의 남자들 사이에서 열심히 공부하였다."+"\n"+
      "커피와 술, 담배가 내 절친이 되는 바람에 유흥비로 돈을 탕진하고 빌붙는 신세가 되었다."+"\n"+
      "취직은 했지만 등록금을 갚으려면 앞이 캄캄하다.";


 // 연구원 (AB)
  String p = "대학시절, 비록 공대 아름이는 되지 못했지만 높은 비율의 남자들 사이에서 열심히 공부하였다."+"\n"+
           "성적은 좋았지만 커피와 술, 담배가 내 절친이 되는 바람에 유흥비로 돈을 탕진하고 빌붙는 신세가 되었다."+"\n"+
           "자금 부족으로 해외 대학원에 진학하지 못하고 연구실에서 반복되는 실험에 찌들어 있다.";


  //치킨집 사장님 (BA)
  String q = "햄버거집, 커피숍, 과외 등 닥치는 대로 아르바이트를 했다."+"\n"+
    "공부는 뒷전, 돈이 내 삶의 전부였다."+"\n"+
             "그렇게 모은 돈과 직접 고안한 치킨 튀기기 알고리즘을 가지고 동네에 치킨집을 오픈했다."+"\n"+
             "소문난 동네맛집이 되어 다음달 강남에 2호점을 오픈할 예정이다."+"\n"+
             "가끔 대학생들이 C언어 코드를 해결해달라는 전화가 온다.";


  //중소기업 (BA)
  String r = "아르바이트를 성실히 해서 등록금은 많이 해결했다."+"\n"+
             "성적이 낮아 결국 중소기업에 취직했다."+"\n"+
             "사장님과 함께하는 야근으로 코딩실력은 점점 늘어나고 있다."+"\n"+
             "그러나 기업의 미래가 불투명한 상태라 불안감이 높아지고 있어 사장님 몰래 대기업으로의 이직을 준비중이다.";


  //인문대
  //대기업AA
  String s = "대기업에 취직하여 높은연봉을 받으며 여유로운 생활을 할 수 있게 되었다."+"\n"+
    "학교생활을 열심히 했기 때문에 빚도 많이 갚아 빚이 이제 얼마 남지 않았다."+"\n"+
             "지금까지 해왔던 대로만 한다면 회사에서 인정을 받으면서 고속승진도 노려볼만하다.";


  //아나운서AA
  String t = "공중파인 DDBC에 아나운서로 입사하였다."+"\n"+
    "학교생활을 열심히 했기 때문에 빚도 많이 갚아 빚이 이제 얼마 남지 않았다."+"\n"+
    "사람다운 9시뉴스 앵커로 활약하며 많은 시청자들의 사랑을 받고 있으며, 훌륭한 성품으로 아나운서 사이에서도 인기가 많다.";
   
  //대기업AB
  String u = "대기업에 취직하여 높은연봉을 받으며 생활을 할 수 있게 되었다."+"\n"+
    "학교생활을 열심히 했지만 빚을 많이 갚지않아 남아있는 빚이 많다."+"\n"+
             "대기업에 취직했지만 남아있는 빚을 청산하느라 돈을 벌어도 나가는게 더 많아서 하루하루가 고되고 힘들다."+"\n"+
             "이 생활을 3년이나 더 해야되는게 힘들긴 하지만 대기업 입사에 행복해 하시는 부모님을 보며 또 하루를 버텨본다.";


  //아나운서AB
  String v = "공중파인 DDBC에 아나운서로 입사하였다."+"\n"+
    "학교생활은 열심히 했지만 빚을 많이 갚지않아 남아있는 빚이 많다."+"\n"+
             "직장에서 만큼은 싹싹하고 똑부러지는 막내로 생활하고있지만, 빚 때문에 마음이 무거운 탓에 얼굴빛이 여전히 어두워 아직 자신의 프로그램은 없다."+"\n"+
             "퇴근직후에는 소주와 오징어를 씹으며 회사와는 다른 이중생활을 하고있는 신세이다.";


  //작가BA
  String w = "작가가 되었다"+"\n"+
    "학교생활은 열심히 하지않았지만 빚은 잘 갚았던 터라 남아있는 빚이 얼마없지만 그것도 너무 힘들다."+"\n"+ 
             "해를 품은 달의 작가처럼 대박을 노리고 있는 당찬 신인 작가지만 현실은 머리를 짜내고 짜내도 나오지않아 요즘은 거의 맨정신으로 글을 쓴 적이 없다."+"\n"+ 
             "그때는 대박이 터질 원고라고 생각하지만 술이 깨고 보면 오타만 늘어진 글을 보면서 또 술을먹는다."+"\n"+
             "비록 술이 깨어있는 시간은 별로 없지만 한 순간도 노트북 손에서 놓아 본 적이 없는 열정을 가지고 있다.";


  //고시생BA
  String x = "공무원이 되기위해 공부를 하고 있는 고시생이다."+"\n"+
             "학교생활은 열심히 하지않았지만 빚은 잘 갚았던 터라 적은액수의 빚이지만 공부만 하고있는 현실이 너무 힘들다."+"\n"+
             "한 칸 남짓한 방안에서 피터지게 공부하지만 성적이 잘 오르지 않는다."+"\n"+
             "요즘엔 성적보다 옆방사람들과의 반찬전쟁에 진이 빠지고 있다."+"\n"+
             "빨간 츄리닝에 슬리퍼를 신고 소세지를 사러 편의점에 가면 늘 로또가 희망을 속삭인다."+"\n"+
             "그렇게 오늘도 로또 한장을 사며 인생 한방의 희망을 걸어본다.";


  //예체대
  //대스타<A,A> 
  String y = "열심히 공부한 결과, 스티붕 스필버거가 제작한 우먼인블랙에 캐스팅되어 헐리우드에 진출하게 되었다."+"\n"+
    "개런티로 받은 $10000000000으로 빚도 모두 청산했다."+"\n"+ 
    "함께 출연한 롸발트 패틴슨과는 결혼을 약속했다."; 
   
  //국가대표<A,A>
  String z = "대학 4년 내내 열심히 연습한 결과, 올림픽, 세계선수권대회, 그랑프리파이널, 4대륙 선수권대회에서 모두 금메달을 획득해서 세계 최초로 그랜드 슬램을 달성하였다."+"\n"+ 
    "한국을 세계에 널린 알린 국민적 영웅이 되어 cf퀸이 되었고 빚도 모두 갚았다.";
    
  //대스타<A,B>
  String aa = "배우가 되기 위해 열심히 공부한 결과, 충무로의 유명한 배우가 되었다."+"\n"+ 
        "하지만 등록금도 값지 못한 채 데뷔를 하는 바람에 성형수술을 하지 못하였고 미녀 배우가 아닌 개성파 배우가 되었다."; 


  //국가대표<A,B>
  String bb = "우여곡절 끝에 국가대표가 되어 오늘도 금메달을 따기위해 노력중이다."+"\n"+ 
            "올림픽에서 금메달만 따면 등록금으로 인한 빚을 갚을 수 있다는 생각에 하루도 연습을 게을리하지 않고 있다."; 


  //학원원장<B,A>
  String cc = "대학시절 갖은 아르바이트를 한 결과, 자금을 마련해서 동네에 작은 피아노 학원을 차렸다."+"\n"+ 
        "하지만 학창시절 열심히 연습하지 않아서 실력이 부족했기 때문에 실력있는 선생님을 스카웃하기위해 많은 돈을 투자해야만 했다.";


  //거리의 예술인<B,A>
  String dd = "대학 4년 내내 공부와 담을 쌓은 결과, 젊음의 거리 뽕대 앞에서 비스켓 비스켓으로 활동하며 돈을 모았다."+"\n"+
              "공연비로 등록금을 다 갚은 채 현재도 길거리 공연을 다니고 있다.";


  //공통
 // 백수<bb>
  String ee = "취업을 못해 놀고 먹는 백조가 되었다."+"\n"+ 
           "비싼 학자금 대출을 받아 학교를 다니면서 학업에도 충실하지 않았고 알바도 열심히 하지 않아서 빚도 많이 남아있는 상태이다."+"\n"+  
              "학교생활을 좀 더 열심히 했었더라면... 이제와서 후회해봤자 소용없다."+"\n"+ 
              "하지만 인생이 끝난건 아니니까 마음 고쳐먹고 지금부터라도 열심히 살아가길...";


 // 날라리 (BB)
  String ff = "취업을 못해 날라리가 되었다."+"\n"+ 
              "공부에도 흥미가 없고, 일을 하기도 싫고 직장인 남자친구의 돈으로 클럽을 가는게 일상이다."+"\n"+ 
              "가끔 돈이 궁할 땐 중고등학교 앞으로 가면 짭짤한 수입이 생긴다."+"\n"+ 
              "먹고 싶은 것 먹고, 놀고 싶을 때 노는 이런게 행복이 아닌가 싶다.";




 // 회사원<B,B>
  String gg = "취업난에 시달린 끝에 취직에 성공은 했지만 자신이 원하던 직장엔 취업하지 못하였다."+"\n"+  
              "하루하루 반복되는 일상속에서 오늘도 부장님께 쓴 소리를 들었다."+"\n"+ 
              "원하지 않는 업무로 스트레스를 받지만 등록금 대출을 갚으려면 참고 일을 해야하는 상황이다.";




 // 만년알바생BB
  String hh = "꿈도 희망도 없는 만년 알바생이 되었다."+"\n"+ 
              "학교생활은 열심히 하지도 빚도 잘 갚지 않아 하루벌어 하루먹고 사는 신세가 되었다."+"\n"+  
              "무엇이 되고 싶다는 꿈도 모두 잊은 채 하루하루를 보내고 있는 자신과는 다르게 꿈을 이뤄 겨울엔 따뜻하고 여름엔 시원한 장소에서 일하고 있는 친구들과는 연락을 끊은 지도 오래다."+"\n"+  
              "알바를 제외하곤 집에서 나가지 않는 은둔형 외톨이가 되어버렸다.";  


 // 결혼 bb
  String ii = "공부도 안하고 아르바이트도 소홀히하며 클럽 VVIP로 클럽의 여신이었던 학창시절을 보냈다."+"\n"+ 
              "그 곳에서 만난 남자와 연애를 하다 그만 넘지 말아야 할 선을 넘어서 어린 나이에 엄마가 되었다."+"\n"+ 
              "사랑하기보단 아이때문에 부부의 연을 맺은 두 사람은 하루하루 갈등만 깊어지고 참다 못해 TV프로그램 <미우니깐 다시 한번>에 출연하여 학창 시절 끼를 발산해 우승을 하였고 사이좋은 부부로 변하였다.";


  
 //sql = "insert into character values(null, '10', '50', '10', '40', '" + name +"', '" + univ +"', '"+ s_major +"', '" + sel_pic + "', '20120301','1');";
 // String sql = "CREATE TABLE event"+"(cid INTEGER PRIMARY KEY AUTOINCREMENT,"+
 // "event_order TEXT, month INTEGER, day INTEGER, speak TEXT);";
 


//교육대
db.execSQL("insert into result values(null,'a1','선생님','" + a +"', 'AA', '교육대');"); 
db.execSQL("insert into result values(null,'a2','노량진 인기강사','" + b +"', 'AA', '교육대');");  
db.execSQL("insert into result values(null,'a3','선생님','" + c +"', 'AB', '교육대');");  
db.execSQL("insert into result values(null,'a4','노량진 인기강사','" + d +"', 'AB', '교육대');");  
db.execSQL("insert into result values(null,'a5','학원강사','" + e +"', 'BA', '교육대');");  
db.execSQL("insert into result values(null,'a6','임용고시생','" + f +"', 'BA', '교육대');");
db.execSQL("insert into result values(null,'a7','백수','" + ee +"', 'BB', '교육대');"); 
db.execSQL("insert into result values(null,'a8','날나리','" + ff +"', 'BB', '교육대');");  
db.execSQL("insert into result values(null,'a9','회사원','" + gg +"', 'BB', '교육대');");  
db.execSQL("insert into result values(null,'a10','만년 알바생','" + hh +"', 'BB', '교육대');");  
db.execSQL("insert into result values(null,'a11','결혼','" + ii +"', 'BB', '교육대');");
  
//의과대
db.execSQL("insert into result values(null,'b1','의사','" + g +"', 'AA', '의과대');"); 
db.execSQL("insert into result values(null,'b2','연구원','" + h +"', 'AA', '의과대');");  
db.execSQL("insert into result values(null,'b3','의사','" + i +"', 'AB', '의과대');");  
db.execSQL("insert into result values(null,'b4','연구원','" + j +"', 'AB', '의과대');");  
db.execSQL("insert into result values(null,'b5','국가고시생','" + k +"', 'BA', '의과대');");  
db.execSQL("insert into result values(null,'b6','불법 시술업자','" + l +"', 'BA', '의과대');"); 
db.execSQL("insert into result values(null,'b7','백수','" + ee +"', 'BB', '의과대');"); 
db.execSQL("insert into result values(null,'b8','날나리','" + ff +"', 'BB', '의과대');");  
db.execSQL("insert into result values(null,'b9','회사원','" + gg +"', 'BB', '의과대');");  
db.execSQL("insert into result values(null,'b10','만년 알바생','" + hh +"', 'BB', '의과대');");  
db.execSQL("insert into result values(null,'b11','결혼','" + ii +"', 'BB', '의과대');");


//공과대
db.execSQL("insert into result values(null,'c1','대기업 신입사원','" + m +"', 'AA', '공과대');"); 
db.execSQL("insert into result values(null,'c2','연구원','" + n +"', 'AA', '공과대');");  
db.execSQL("insert into result values(null,'c3','대기업 신입사원','" + o +"', 'AB', '공과대');");  
db.execSQL("insert into result values(null,'c4','연구원','" + p +"', 'AB', '공과대');");  
db.execSQL("insert into result values(null,'c5','치킨집 사장','" + q +"', 'BA', '공과대');");  
db.execSQL("insert into result values(null,'c6','중소기업 신입사원','" + r +"', 'BA', '공과대');");
db.execSQL("insert into result values(null,'c7','백수','" + ee +"', 'BB', '공과대');"); 
db.execSQL("insert into result values(null,'c8','날나리','" + ff +"', 'BB', '공과대');");  
db.execSQL("insert into result values(null,'c9','회사원','" + gg +"', 'BB', '공과대');");  
db.execSQL("insert into result values(null,'c10','만년 알바생','" + hh +"', 'BB', '공과대');");  
db.execSQL("insert into result values(null,'c11','결혼','" + ii +"', 'BB', '공과대');");


//인문대
db.execSQL("insert into result values(null,'d1','대기업 신입사원','" + s +"', 'AA', '인문대');"); 
db.execSQL("insert into result values(null,'d2','아나운서','" + t +"', 'AA', '인문대');");  
db.execSQL("insert into result values(null,'d3','대기업 신입사원','" + u +"', 'AB', '인문대');");  
db.execSQL("insert into result values(null,'d4','아나운서','" + v +"', 'AB', '인문대');");  
db.execSQL("insert into result values(null,'d5','작가','" + w +"', 'BA', '인문대');");  
db.execSQL("insert into result values(null,'d6','고시생','" + x +"', 'BA', '인문대');");
db.execSQL("insert into result values(null,'d7','백수','" + ee +"', 'BB', '인문대');"); 
db.execSQL("insert into result values(null,'d8','날나리','" + ff +"', 'BB', '인문대');");  
db.execSQL("insert into result values(null,'d9','회사원','" + gg +"', 'BB', '인문대');");  
db.execSQL("insert into result values(null,'d10','만년 알바생','" + hh +"', 'BB', '인문대');");  
db.execSQL("insert into result values(null,'d11','결혼','" + ii +"', 'BB', '인문대');");


//예체대
db.execSQL("insert into result values(null,'e1','대스타','" + y +"', 'AA', '예체대');"); 
db.execSQL("insert into result values(null,'e2','국가대표','" + z +"', 'AA', '예체대');");  
db.execSQL("insert into result values(null,'e3','대스타','" + aa +"', 'AB', '예체대');");  
db.execSQL("insert into result values(null,'e4','국가대표','" + bb +"', 'AB', '예체대');");  
db.execSQL("insert into result values(null,'e5','학원원장','" + cc +"', 'BA', '예체대');");  
db.execSQL("insert into result values(null,'e6','거리의 예술인','" + dd +"', 'BA', '예체대');"); 
db.execSQL("insert into result values(null,'e7','백수','" + ee +"', 'BB', '예체대');"); 
db.execSQL("insert into result values(null,'e8','날나리','" + ff +"', 'BB', '예체대');");  
db.execSQL("insert into result values(null,'e9','회사원','" + gg +"', 'BB', '예체대');");
db.execSQL("insert into result values(null,'e10','만년 알바생','" + hh +"', 'BB', '예체대');");  
db.execSQL("insert into result values(null,'e11','결혼','" + ii +"', 'BB', '예체대');");


}




 //DB를 갈아 엎고 새로 만들 필요가 있을 때 호출되는 메소드
 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  // TODO Auto-generated method stub
  db.execSQL("DROP TABLE IF EXSITS result");
  //새로 생성될 수 있도록 onCreate() 메소드를 생성한다.
  onCreate(db);
 }


}