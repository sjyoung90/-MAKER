package com.android.progBar;

import java.io.Serializable;

public class point implements Serializable{

	int x, y;
	boolean isStart = false; 
		
	public point(int a, int b, boolean isStart){
		
		this.x = a;
		this.y = b;
		this.isStart = isStart;		
	}
}
