package com.android.progBar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class InsertCharacter extends Activity {
    /** Called when the activity is first created. */
    
	DBManager dManager;
	DBManager2 dManager2;
	DBManager3 dManager3;
	SQLiteDatabase db, db2, db3;
	private static final String DATABASE_NAME = "character.db";
	private static final String DATABASE_NAME2 = "calendar.db";
	private static final String DATABASE_NAME3 = "event.db";
	EditText editName, editUniv, editMajor;
	String name, univ, s_major;
	String sel_pic;
	Spinner major;
	ImageView pic;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                
        setContentView(R.layout.insertcharacter);        
        
        Intent intent = getIntent();
        sel_pic = intent.getStringExtra("sel_pic");
        
        editName = (EditText)findViewById(R.id.editName);
        editUniv = (EditText)findViewById(R.id.editUniv);
        major = (Spinner) findViewById(R.id.spinner1);
        pic = (ImageView)findViewById(R.id.a);
        
        if(sel_pic.equalsIgnoreCase("a")){pic.setImageResource(R.drawable.character1);}
        else if(sel_pic.equalsIgnoreCase("b")){pic.setImageResource(R.drawable.character2);}
        
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.majors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        major.setAdapter(adapter);
        
        if (db == null) { 
        	db = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
		}
        if (db2 == null) { 
        	db2 = openOrCreateDatabase(DATABASE_NAME2, Context.MODE_PRIVATE, null);
		}
        if (db3 == null) { 
        	db3 = openOrCreateDatabase(DATABASE_NAME3, Context.MODE_PRIVATE, null);
		}
        
        dManager = new DBManager(this, "character.db", null, 1);        
        db.close();
        
        dManager2 = new DBManager2(this, "calendar.db", null, 1);        
        db2.close();
        
        dManager3 = new DBManager3(this, "event.db", null, 1);        
        db3.close();
        
        major.setOnItemSelectedListener(majorListener);

    }
    
    private OnItemSelectedListener majorListener = new OnItemSelectedListener(){
    	   public void onNothingSelected(AdapterView<?> parent){
               Toast.makeText(getApplicationContext(), "�����ϼ���", Toast.LENGTH_SHORT).show();
             }

    	
    	public void onItemSelected(android.widget.AdapterView<?> arg0, android.view.View arg1, int arg2, long arg3) {
    		
    		s_major = major.getSelectedItem().toString();

    	};
    };    
   
  	public void onSaveDB(){ 
  		  		  		
  			String sql = "", sql2 = "";
        	db = dManager.getWritableDatabase();
        	db2 = dManager2.getWritableDatabase();
       		
    		if(sel_pic.equalsIgnoreCase("a")){
    			sql = "insert into character values(null, '20', '50', '90', '40', '" + name +"', '" + univ +"', '"+ s_major +"', '" + sel_pic + "', '0','0','500','0','0','1','1');";
    			//cid INTEGER PRIMARY KEY AUTOINCREMENT,knowledge,love,strength,money, name, university ,major, pic, delay, nonpayment, nnonpayment, stage, exam, examcnt, studycnt);";
    		  	sql2 = "insert into calendar values(null, '0', '0', '1', '2', '1');";
    		}
    		else if(sel_pic.equalsIgnoreCase("b")){
    			sql = "insert into character values(null, '10', '50', '100', '40', '" + name +"', '" + univ +"', '"+ s_major +"', '" + sel_pic + "', '0','0','500','0','0','1','1');";
    			sql2 = "insert into calendar values(null, '0', '0', '1', '2', '1');";
    		}
    		          
    		db.execSQL(sql);    		
    		db.close();
    		db2.execSQL(sql2);
    		db2.close();   		
    		
    		onSetEvent();
       		
	}
  	
    public void onNext(View v){
    	
    	name = editName.getText().toString();
        univ = editUniv.getText().toString();  
        
    	if(name.equalsIgnoreCase("") || univ.equalsIgnoreCase("") || s_major.equalsIgnoreCase("")){        	

            if(name.equalsIgnoreCase("")){Toast.makeText(InsertCharacter.this, "�̸��� �Է��ϼ���.", Toast.LENGTH_SHORT).show();}
            if(univ.equalsIgnoreCase("")){Toast.makeText(InsertCharacter.this, "���б��� �Է��ϼ���.", Toast.LENGTH_SHORT).show();}
            
        }       
        else{
        	
        	onSaveDB();
        	startActivity(new Intent(InsertCharacter.this, CharacterAni1.class));
        }
    	
    }
    
    public void onSetEvent(){
    	
    		db3 = dManager3.getWritableDatabase();    	
    	
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
    		  
    			  
    			  db3.execSQL("insert into event values(null,'a1', '2', '1', '" + a +"','0','0');");  
    			  db3.execSQL("insert into event values(null,'a2', '3', '1', '" + null +"','0','0');");  
    			 
    			  db3.execSQL("insert into event values(null,'c1', 'null', '4', '" + c1 +"','0','2');"); 
    			  db3.execSQL("insert into event values(null,'c2', 'null', '7', '" + c2 +"','0','2');"); 
    			  db3.execSQL("insert into event values(null,'c3', 'null', '10', '" + c3 +"','0','2');"); 
    			  db3.execSQL("insert into event values(null,'c4', 'null', '13', '" + c4 +"','0','2');"); 
    			  db3.execSQL("insert into event values(null,'c5', 'null', '16', '" + c1 +"','0','2');"); 
    			  db3.execSQL("insert into event values(null,'c6', null, '19', '" + c6 +"','0','2');"); 
    			  db3.execSQL("insert into event values(null,'c7', null, '22', '" + c2 +"','0','2');"); 
    			  db3.execSQL("insert into event values(null,'c8', null, '25', '" + c5 +"','0','2');"); 
    			  db3.execSQL("insert into event values(null,'c9', null, '28', '" + c1 +"','0','2');"); 

    			  db3.execSQL("insert into event values(null,'d1', null, '16', '" + d1 +"','0','2');");  
    			  db3.execSQL("insert into event values(null,'d2', null, '16', '" + d2 +"','0','2');");  
    			  db3.execSQL("insert into event values(null,'d3', null, '16', '" + d3 +"','0','2');");  
    			  db3.execSQL("insert into event values(null,'d4', null, '16', '" + d4 +"','0','2');");  
    			  
    			  db3.execSQL("insert into event values(null,'f1', null, '16', '" + f1 +"','0','2');");  
    			  db3.execSQL("insert into event values(null,'f2', null, '16', '" + f2 +"','0','2');");  
    			  db3.execSQL("insert into event values(null,'f3', null, '16', '" + f3 +"','0','2');");  
    			  db3.execSQL("insert into event values(null,'f4', null, '16', '" + f4 +"','0','2');");  

    			  db3.execSQL("insert into event values(null,'g', null, '1', '" + g +"','0','2');");  
    			  db3.execSQL("insert into event values(null,'h', '2', '30', '" + h +"','0','0');");   
    			  
    			  db3.execSQL("insert into event values(null,'e1', '1', '1', '" + e1 +"', '0','0');");  //����
    			  db3.execSQL("insert into event values(null,'e2', '1', '28', '" + e2 +"', '0','0');"); //�ܿ￩��  
    			  db3.execSQL("insert into event values(null,'e3', '1', '25', '" + e3 +"', '0','0');");  //���� 
    			  db3.execSQL("insert into event values(null,'e33', '3', '25', '" + e3 +"', '0','0');");  //����
    			  db3.execSQL("insert into event values(null,'e34', '4', '25', '" + e3 +"', '0','0');");  //���� 
    			  db3.execSQL("insert into event values(null,'e37', '7', '25', '" + e3 +"', '0','0');");  //���� 
    			  db3.execSQL("insert into event values(null,'e39', '9', '25', '" + e3 +"', '0','0');");  //���� 
    			  db3.execSQL("insert into event values(null,'e311', '11', '25', '" + e3 +"', '0','0');");  //���� 
    			  db3.execSQL("insert into event values(null,'e4', '2', '7', '" + e4 +"', '0','0');");   //���б�
    			  db3.execSQL("insert into event values(null,'e5', '2', '13', '" + e5 +"', '0','0');");  //����
    			  db3.execSQL("insert into event values(null,'e6', '3', '22', '" + e6 +"', '0','0');");  //����
    			  db3.execSQL("insert into event values(null,'e7', '5', '7', '" + e7 +"', '0','0');");  //����̳�
    			  db3.execSQL("insert into event values(null,'e8', '6', '25', '" + e8 +"', '0','0');");  //�߰���� 25~30
    			  db3.execSQL("insert into event values(null,'e9', '7', '10', '" + e9 +"', '0','0');");  //������
    			  db3.execSQL("insert into event values(null,'e10', '8', '28', '" + e10 +"', '0','0');");  //�����ް�
    			  db3.execSQL("insert into event values(null,'e11', '9', '22', '" + e11 +"', '0','0');");  //�߼�
    			  db3.execSQL("insert into event values(null,'e12', '10', '4', '" + e12 +"', '0','0');");  //�̴��� ����
    			  db3.execSQL("insert into event values(null,'e13', '11', '16', '" + e13 +"', '0','0');");  //�뵷
    			  db3.execSQL("insert into event values(null,'e14', '12', '7', '" + e14 +"', '0','0');");  //�⸻��� 12.7~12
    			  db3.execSQL("insert into event values(null,'e15', '12', '22', '" + e15 +"', '0','0');");  //������
    			  db3.execSQL("insert into event values(null,'e16', '12', '25', '" + e16 +"', '0','0');");  //ũ��������
    			  db3.execSQL("insert into event values(null,'e171', '1', '4', '" + e171 +"', '0','0');"); //���ڱ�
    			  db3.execSQL("insert into event values(null,'e172', '2', '4', '" + e171 +"', '0','0');"); //���ڱ�
    			  db3.execSQL("insert into event values(null,'e173', '3', '4', '" + e171 +"', '0','0');"); //���ڱ�
    			  db3.execSQL("insert into event values(null,'e174', '4', '4', '" + e171 +"', '0','0');"); //���ڱ�
    			  db3.execSQL("insert into event values(null,'e175', '5', '4', '" + e171 +"', '0','0');"); //���ڱ�
    			  db3.execSQL("insert into event values(null,'e176', '6', '4', '" + e171 +"', '0','0');"); //���ڱ�
    			  db3.execSQL("insert into event values(null,'e177', '7', '4', '" + e171 +"', '0','0');"); //���ڱ�
    			  db3.execSQL("insert into event values(null,'e178', '8', '4', '" + e171 +"', '0','0');"); //���ڱ�
    			  db3.execSQL("insert into event values(null,'e179', '9', '4', '" + e171 +"', '0','0');"); //���ڱ�
    			  db3.execSQL("insert into event values(null,'e1710', '10', '4', '" + e171 +"', '0','0');"); //���ڱ�
    			  db3.execSQL("insert into event values(null,'e1711', '11', '4', '" + e171 +"', '0','0');"); //���ڱ�
    			  db3.execSQL("insert into event values(null,'e1712', '12', '4', '" + e171 +"', '0','0');"); //���ڱ�  
    			  db3.execSQL("insert into event values(null,'e181', '4', '19', '" + e181 +"', '0','0');"); //���ڱ��߰�����
    			  db3.execSQL("insert into event values(null,'e182', '8', '19', '" + e181 +"', '0','0');"); //���ڱ��߰�����
    			  db3.execSQL("insert into event values(null,'e183', '11', '19', '" + e181 +"', '0','0');"); //���ڱ��߰�����

    			  db3.close();    	
    	
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	      
        return true;
       
    }
}


