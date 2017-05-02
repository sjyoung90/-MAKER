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

	//자바 소스 코드에서 이 클래스를 생성할 때 반드시 있어야 하는 생성자
	public MyView(Context context) {		
		super(context);
		// TODO Auto-generated constructor stub
		init(); 
	}
	
	//XML 코드에서 이 클래스를 배치할 때 반드시 있어야 하는 생성자 
	public MyView(Context context, AttributeSet attrs){			
		super(context, attrs);
		// TODO Auto-generated constructor stub
		 init();
	}
	
	//XML 코드에서 이 클래스에 theme 를 사용할 때 반드시 있어야 하는 생성자
	public MyView(Context context, AttributeSet attrs, int defStyle)
	{            
	    super(context, attrs, defStyle);
	    // TODO Auto-generated constructor stub
	    init();
	}
	
	//초기화 
	public void init(){
		  //arraylist 객체 생성하기
		list = new ArrayList<point>();
	}	
	   
	public void onDraw(Canvas canvas) {
		
		Paint p1 = new Paint();
		p1.setAntiAlias(true);
		p1.setColor(Color.RED);
		p1.setTextSize(30);
		canvas.drawText("정답 : " + StudyTest.score + "개", this.getWidth() / 2, 40, p1);		
		
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(10);
		paint.setStrokeCap(Cap.ROUND);
		
		if(StudyTest.next == false){
		for(int i = 0 ;i < list.size(); i++){			 
			   //배열에서 i번째 인덱스에 있는 Point 객체를 얻어온다.
			   point p = list.get(i);
			   
			   if(!(p.isStart)){ //해당 인텍스의 isStart 값이 true가 아니라면
			    //시작점이 아니므로 바로 전 point 객체와 연결한다.
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
