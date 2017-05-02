package com.android.progBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.widget.ImageView;

public class BurgerShow extends Activity {
    /** Called when the activity is first created. */

	int value = 6;
	ImageView image;
	CountDownTimer timer;	
	static int rand;
	
	public static final int array[] = {		
		R.drawable.burger1, R.drawable.burger2, R.drawable.burger3, R.drawable.burger4, R.drawable.burger5,
		R.drawable.burger6, R.drawable.burger7, R.drawable.burger8, R.drawable.burger9, R.drawable.burger10};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.burgershow);                
       
        image = (ImageView)findViewById(R.id.ImageView1);
        
        rand = (int)((Math.random() * 10));    	
    	image.setImageResource(array[rand]);
        
        timer = new CountDownTimer(8 * 1000, 1000) {
    	@Override
		public void onTick(long millisUntilFinished) {
			value--;        	      	   	
		}
		@Override
		public void onFinish() {				
			Start();
		}			
    };    	
    	
    	timer.start();
    }    
    
    public void Start(){
    	startActivity(new Intent(BurgerShow.this, BurgerMain.class));	    	
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	      
        return true;
       
    }
   
} // Activiry

