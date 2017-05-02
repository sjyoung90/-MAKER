package com.android.progBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class SelectCharacter extends Activity {
    /** Called when the activity is first created. */
 
 ImageView sel_a, sel_b;
 String sel_pic;
 boolean select = false;
      
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                

        setContentView(R.layout.selectcharacter);
        
        sel_a = (ImageView)findViewById(R.id.a);
        sel_b = (ImageView)findViewById(R.id.b);
        sel_a.setOnClickListener(AclickListener);
        sel_b.setOnClickListener(BclickListener);
        
    }
    
    View.OnClickListener AclickListener = new View.OnClickListener() {
  
  @Override
  public void onClick(View v) {
   // TODO Auto-generated method stub
   sel_a.setImageResource(R.drawable.character1_b);
   sel_b.setImageResource(R.drawable.character2); 
   sel_pic = "a";
   select = true;
  }
 };
 
 View.OnClickListener BclickListener = new View.OnClickListener() {
  
  @Override
  public void onClick(View v) {
   // TODO Auto-generated method stub
   sel_a.setImageResource(R.drawable.character1);
   sel_b.setImageResource(R.drawable.character2_b);
   sel_pic = "b";
   select = true;
  }
 };

    public void onNext(View v){
     
    	if(select == false){ Toast.makeText(SelectCharacter.this, "캐릭터를 선택하세요.", Toast.LENGTH_SHORT).show();}
    	else {
    		Intent intent = new Intent(SelectCharacter.this, InsertCharacter.class);
    		intent.putExtra("sel_pic", sel_pic);   //선택이미지를 다음 액티비티로 옯겨줌             
            startActivity(intent);     
         
    	}     
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	      
        return true;
       
    }
}

