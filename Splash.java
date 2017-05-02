package com.android.progBar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;

public class Splash extends Activity {
    /** Called when the activity is first created. */
   
    @Override    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);             

        setContentView(R.layout.splash);
        
        Handler handler = new Handler(){
        	public void handleMessage(Message msg){
        		super.handleMessage(msg);
        		finish();
        	}
        };
        handler.sendEmptyMessageDelayed(0, 3000);
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	      
        return true;
       
    }
}

