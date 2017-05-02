package com.android.progBar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * DB ���� �� ���׷��̵带 �����ִ� ����� Ŭ���� �����
 *  - SQLiteOpenHelper �߻� Ŭ������ ��ӹ޾Ƽ� �����. - 
 */
public class DBManager3 extends SQLiteOpenHelper{
 
 public DBManager3(Context context, String name, CursorFactory factory,
   int version) {
  super(context, name, factory, version);


 }
 //DB�� ������ �� ȣ��Ǵ� �޼ҵ�
 @Override
 public void onCreate(SQLiteDatabase db) {
  // TODO Auto-generated method stub
  //DB�� ���̺� �����ϱ�  
  String sql = "CREATE TABLE event"+"(cid INTEGER PRIMARY KEY AUTOINCREMENT,"+
    "event_order TEXT, month INTEGER, day INTEGER, speak TEXT, exe TEXT, exe2 TEXT);";
  //sql�� �����ϱ�
  db.execSQL(sql);
 
  String a = "�ȳ�? �ݰ���~ �� ������ ���� ���л�Ȱ 4�⵿���� �Բ� ���� �����̾�. " +
  		"���� ���л��� �Ǿ����ϱ� ���ε� �������ϰ� �Ƹ�����Ʈ�� �������ؼ� �Ŵ� ���ڱݴ����� ���ƾ���. ���� �ʸ� �����ٰ�, ������ �� ��������!!";
  
  String b = "�̹��ֵ� �������� �� ���غô�?"+ "\n" + "�׷� �̹��ֵ� Ȱ������ �����غ���!!"; //������¥������� �ݺ�
  
  String c1 = "�����߾� :)"; //������ٳ������(�ݺ�):
  String c2 = "���� Ȱ���� �Ϸ����� "; //������ٳ������(�ݺ�):
  String c3 = "���� ����� �� �?"; //������ٳ������(�ݺ�):
  String c4 = "���� ��´� :< " + "\n" + "������ �� �� ��� �� ì�� ~"; //������ٳ������(�ݺ�):
  String c5 = "������ �� ������ ���� :)"; //������ٳ������(�ݺ�):
  String c6 = "��ȹ�� �͵��� �� �ǰ��� �־�?" + "\n" + "�ʹ� ����������^^"; //������ٳ������(�ݺ�):
  
  //���������� ������©��(16��)
  String d1 = "���� ���ְ� ��������, �̹��ִ� ��� ���¾�~?" + "\n" + "������ ��ȹ�� ���߾�?" + "\n" + 
		  	"�̹��޿� ��մ� ��ȭ�� �����ߴٴµ� �������°� �?";
  String d2 = "���� �������� ¥���ϴ� ���̾�^^ "+ "\n" + "Ư���� ��ȹ�� ���ٸ�, �����ȯ�Ұ� �����ϴ°� �? ";  
  String d3 = "�����߾� :)" + "\n" + "������ ¥�� ������ ~? ";
  String d4 = "�����߾� :)" + "\n" + "������ ¥�� ����^^";    

  //������¥�� �ͼ�  
  String f1 = "��������  �� ���߾�? " + "\n" + "���� �����쵵 ȭ�������� !!!";
  String f2 = "�̹� �ֵ� �ų��� Ȱ���� �����غ��� :D";
  String f3 = "�ʹ� �������� ���� ���� ���� ������ ȭ����!" + "\n" + "���ھ��� !!!";  
  String f4 = "������ GOGO!!";

// �Ѵ���������  
  String g = "�̹��޵� ������ �����غ���~^^";
		 // "���ο� ���� �Ǿ�����, ������� �߳´�? ���� ���Ƽ� ������?" + "\n" + 
      //"���� ���ƾ��� ���� ���� �������� �������� ";
  
//�Ǹ�����
  String h = "���� �ʿ� �Բ��� 4���� ��������, ������ ����־���? ��ȸ���� ���л�Ȱ�̾��⸦ �ٶ�" + "\n" + 
      "������ ���� �����.... ";

// ����
  String e1 = "���ذ� ��ҽ��ϴ�." + "\n" + "���� �� ���� ��������.";

// �ܿ��ް�
  String e2 = "�ܿ��ް��� ���ðڽ��ϱ�?" + "\n";
  String e2y = "��Ű������ �����ϴ�." + "\n" + "�ް�������� 10������ �����ǰ� ü���� +50 �Ǿ����ϴ�.";
    
// ����
  String e3 = "���ͽ����� �ִ� ���Դϴ�." + "\n" + "���÷� 5������ �����˴ϴ�.";

// ���б�
  String e4 = "���б��� �޾ҽ��ϴ�." + "\n" + "������ �����ϼ̱���." + "\n" +" ���� �б⿡�� �������ؼ� �� ���б��� Ÿ�ñ� �ٶ��ϴ�.";

 // ����
  String e5 = " ���� �� ���� ��������. " + "\n" + " ��ġ ��ġ ������ ��������� �� " + "\n" + 
		  " �츮 �츮 ������ �����̷��� ��"+ "\n" +" �������� 10������ �޾ҽ��ϴ�. ";

// ����
  String e6 = " ���������մϴ� !! " + "\n" + " �뵷���� 10������ �޾ҽ��ϴ�. ";

// ����̳�
  String e7 = " ���� �� ���ο� �� �����ð�  �� " + "\n" + " �⸣�Ƕ� �㳷���� �־��� ���� �� "+ "\n" +
     " ����̳��Դϴ� ! �θ�Բ� ȿ���ϼ��� :~) " + "\n" + " �θ�� ������ 5������ �����˴ϴ�. "; 

// �߰����
  String e8 = " �߰���� �Ⱓ�Դϴ�. " + "\n" + " �� �б⵿�� ������ ������ �� ����ϸ�  " + "\n" + " ������ ��ġ���ñ� �ٶ��ϴ�. ";
 
// ������
  String e9 = " �������� �����Ͻðڽ��ϱ�? ";
  String e9y = " �������� �����Ͽ� ���������� +5 �Ǿ����ϴ�. ";   

//�����ް�
  String e10 = " �����ް��� ���ðڽ��ϱ�? ";
  String e10y = " �ؼ��������� �����ϴ�. " + "\n" + " �ް�������� 10������ �����ǰ� ü���� +50 �Ǿ����ϴ�.";   

//�߼�
  String e11 = " ��̰� ǳ��ο� �Ѱ����Դϴ�." + "\n" + " �������� ���� �ҿ��� �������. "+ "\n"
    +" �������� �� ģô��鲲 �뵷���� 10������ �޾ҽ��ϴ�. "; 

// �̴��� ����
  String e12 = " �����մϴ� !! " + "\n" + " �Ƹ�����Ʈ�� �ϴ� ���Կ��� �̴��� �������� �������ϴ�. "+ "\n"
    + " ���ʽ��� 5������ ���޵ǰ� ���������� +5 �Ǿ����ϴ�. ";
 
//�뵷
  String e13 = " �θ�Բ��� �뵷���� 10������ �ּ̽��ϴ�. ";
     
// �⸻���
  String e14 = " �⸻��� �Ⱓ�Դϴ�. " + "\n" + " �� �б⵿�� ������ ������ �� ����ϸ� " + "\n" + " ������ ��ġ���ñ� �ٶ��ϴ�. ";

// ������
  String e15 = " ������ �ٰ����� �ֽ��ϴ�. " + "\n" + " �߿�ܿ�, ����� �̿����� ���µ� �����Ͻðڽ��ϱ�? ";
  String e15y = " ������ ����Կ� 5������ ����Ͽ����ϴ�. ";     

//ũ��������
  String e16 = " ��  �޸� ũ�������� �� " + "\n" + " �ູ�� ��ź���Դϴ�. " + "\n" + " ģ����� ��ſ� ũ�������� ��Ƽ�� �Ͻðڽ��ϱ�? ";
  String e16y = " ��Ƽ������� 10������ �����ǰ� ���������� +5�Ǿ����ϴ�.";

// ���ڱ�
  String e171 = " �ſ� 4���� ���ڱ� ����� ��ȯ�� �Դϴ�." + "\n" +" �̹��� ������� �����Ͻðڽ��ϱ�?";
  String e17y = " ���忡�� 40������ �����˴ϴ�. ";
  String e17n = " �����޷� ��ȯ���� �̿��˴ϴ�. ";
  
//�߰�������
  String e181 = " �̹��� 19���� ���ڱ� �߰����αⰣ�Դϴ�. " + "\n" + " �߰����θ� �Ͻðڽ��ϱ�? ";
  
  
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
  
  db.execSQL("insert into event values(null,'e1', '1', '1', '" + e1 +"', '0','0');");  //����
  db.execSQL("insert into event values(null,'e2', '1', '28', '" + e2 +"', '0','0');"); //�ܿ￩��  
  db.execSQL("insert into event values(null,'e3', '1', '25', '" + e3 +"', '0','0');");  //���� 
  db.execSQL("insert into event values(null,'e33', '3', '25', '" + e3 +"', '0','0');");  //����
  db.execSQL("insert into event values(null,'e34', '4', '25', '" + e3 +"', '0','0');");  //���� 
  db.execSQL("insert into event values(null,'e37', '7', '25', '" + e3 +"', '0','0');");  //���� 
  db.execSQL("insert into event values(null,'e39', '9', '25', '" + e3 +"', '0','0');");  //���� 
  db.execSQL("insert into event values(null,'e311', '11', '25', '" + e3 +"', '0','0');");  //���� 
  db.execSQL("insert into event values(null,'e4', '2', '7', '" + e4 +"', '0','0');");   //���б�
  db.execSQL("insert into event values(null,'e5', '2', '13', '" + e5 +"', '0','0');");  //����
  db.execSQL("insert into event values(null,'e6', '3', '22', '" + e6 +"', '0','0');");  //����
  db.execSQL("insert into event values(null,'e7', '5', '7', '" + e7 +"', '0','0');");  //����̳�
  db.execSQL("insert into event values(null,'e8', '6', '25', '" + e8 +"', '0','0');");  //�߰���� 25~30
  db.execSQL("insert into event values(null,'e9', '7', '10', '" + e9 +"', '0','0');");  //������
  db.execSQL("insert into event values(null,'e10', '8', '28', '" + e10 +"', '0','0');");  //�����ް�
  db.execSQL("insert into event values(null,'e11', '9', '22', '" + e11 +"', '0','0');");  //�߼�
  db.execSQL("insert into event values(null,'e12', '10', '4', '" + e12 +"', '0','0');");  //�̴��� ����
  db.execSQL("insert into event values(null,'e13', '11', '16', '" + e13 +"', '0','0');");  //�뵷
  db.execSQL("insert into event values(null,'e14', '12', '7', '" + e14 +"', '0','0');");  //�⸻��� 12.7~12
  db.execSQL("insert into event values(null,'e15', '12', '22', '" + e15 +"', '0','0');");  //������
  db.execSQL("insert into event values(null,'e16', '12', '25', '" + e16 +"', '0','0');");  //ũ��������
  db.execSQL("insert into event values(null,'e171', '1', '4', '" + e171 +"', '0','0');"); //���ڱ�
  db.execSQL("insert into event values(null,'e172', '2', '4', '" + e171 +"', '0','0');"); //���ڱ�
  db.execSQL("insert into event values(null,'e173', '3', '4', '" + e171 +"', '0','0');"); //���ڱ�
  db.execSQL("insert into event values(null,'e174', '4', '4', '" + e171 +"', '0','0');"); //���ڱ�
  db.execSQL("insert into event values(null,'e175', '5', '4', '" + e171 +"', '0','0');"); //���ڱ�
  db.execSQL("insert into event values(null,'e176', '6', '4', '" + e171 +"', '0','0');"); //���ڱ�
  db.execSQL("insert into event values(null,'e177', '7', '4', '" + e171 +"', '0','0');"); //���ڱ�
  db.execSQL("insert into event values(null,'e178', '8', '4', '" + e171 +"', '0','0');"); //���ڱ�
  db.execSQL("insert into event values(null,'e179', '9', '4', '" + e171 +"', '0','0');"); //���ڱ�
  db.execSQL("insert into event values(null,'e1710', '10', '4', '" + e171 +"', '0','0');"); //���ڱ�
  db.execSQL("insert into event values(null,'e1711', '11', '4', '" + e171 +"', '0','0');"); //���ڱ�
  db.execSQL("insert into event values(null,'e1712', '12', '4', '" + e171 +"', '0','0');"); //���ڱ�  
  db.execSQL("insert into event values(null,'e181', '4', '19', '" + e181 +"', '0','0');"); //���ڱ��߰�����
  db.execSQL("insert into event values(null,'e182', '8', '19', '" + e181 +"', '0','0');"); //���ڱ��߰�����
  db.execSQL("insert into event values(null,'e183', '11', '19', '" + e181 +"', '0','0');"); //���ڱ��߰�����

  
 }

 //DB�� ���� ���� ���� ���� �ʿ䰡 ���� �� ȣ��Ǵ� �޼ҵ�
 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  // TODO Auto-generated method stub
  db.execSQL("DROP TABLE IF EXSITS event");
  //���� ������ �� �ֵ��� onCreate() �޼ҵ带 �����Ѵ�.
  onCreate(db);
 }

}

