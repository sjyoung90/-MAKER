package com.android.progBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

public class PopShow extends Activity{
    /** Called when the activity is first created. */
    
 static LinearLayout first_iv; 
 int rand;
 public static final int array[]= { //그림 랜덤으로 가져오기
  R.drawable.pop1,R.drawable.pop2,R.drawable.pop3,R.drawable.pop4,
  R.drawable.pop5,R.drawable.pop6,R.drawable.pop7};
 
 	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popshow);
        
        first_iv = (LinearLayout)findViewById(R.id.imageView1);  
        rand = (int)((Math.random()*7)); //그림 랜덤으로 가져오기
        first_iv.setBackgroundResource((array[rand]));
 
 }
 
 public void onButton(View v){  
	 startActivity(new Intent(PopShow.this, PopMain.class));   
 }
 public boolean onKeyDown(int keyCode, KeyEvent event) {
     
     return true;
    
 }
}

