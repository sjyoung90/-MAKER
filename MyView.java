package com.android.progBar;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
	
	static ArrayList<point> list; 

	//�ڹ� �ҽ� �ڵ忡�� �� Ŭ������ ������ �� �ݵ�� �־�� �ϴ� ������
	public MyView(Context context) {		
		super(context);
		// TODO Auto-generated constructor stub
		init(); 
	}
	
	//XML �ڵ忡�� �� Ŭ������ ��ġ�� �� �ݵ�� �־�� �ϴ� ������ 
	public MyView(Context context, AttributeSet attrs){			
		super(context, attrs);
		// TODO Auto-generated constructor stub
		 init();
	}
	
	//XML �ڵ忡�� �� Ŭ������ theme �� ����� �� �ݵ�� �־�� �ϴ� ������
	public MyView(Context context, AttributeSet attrs, int defStyle)
	{            
	    super(context, attrs, defStyle);
	    // TODO Auto-generated constructor stub
	    init();
	}
	
	//�ʱ�ȭ 
	public void init(){
		  //arraylist ��ü �����ϱ�
		list = new ArrayList<point>();
	}	
	   
	public void onDraw(Canvas canvas) {
		
		Paint p1 = new Paint();
		p1.setAntiAlias(true);
		p1.setColor(Color.RED);
		p1.setTextSize(30);
		canvas.drawText("���� : " + StudyTest.score + "��", this.getWidth() / 2, 40, p1);		
		
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(10);
		paint.setStrokeCap(Cap.ROUND);
		
		if(StudyTest.next == false){
		for(int i = 0 ;i < list.size(); i++){			 
			   //�迭���� i��° �ε����� �ִ� Point ��ü�� ���´�.
			   point p = list.get(i);
			   
			   if(!(p.isStart)){ //�ش� ���ؽ��� isStart ���� true�� �ƴ϶��
			    //�������� �ƴϹǷ� �ٷ� �� point ��ü�� �����Ѵ�.
			    canvas.drawLine(list.get(i-1).x, list.get(i-1).y, 
			    		list.get(i).x, list.get(i).y, paint);		    
			    
		      }
		}
		}
		else if(StudyTest.next == true){
			
			list.clear();
			StudyTest.next = false;
			
		}
	}
}
