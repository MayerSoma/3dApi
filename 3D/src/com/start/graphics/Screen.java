package com.start.graphics;

import java.util.Random;

public class Screen extends Render{
	
	private Render test;
	private static final int testSize = 256;

	public Screen(int width, int height) {
		super(width, height);
		Random random = new Random();
		test = new Render(testSize,testSize);
		for(int i = 0; i < testSize*testSize ; i++)
		{
			test.pixels[i]= random.nextInt();
		}
		//Mouth
		for(int i = 0; i < 3000 ; i++)
		{
			test.pixels[50000+i]=100;
		}
		
		
		
		
	}
	
	
	
	
	
	public void render()
	{
		//Delete pixels
		for(int i = 0; i <width*height;i++)
		{
			pixels[i]=0;
		}
		for(int i = 0; i <100;i++)
		{
		int anim = (int) (Math.sin((System.currentTimeMillis() + i*10) % 2000.0 / 2000 * Math.PI*2)*200);
		int anim2 = (int) (Math.cos((System.currentTimeMillis() + i*10) % 2000.0 / 2000 * Math.PI*2)*200);
		draw(test,(width-256) /2 + anim ,(height-256) /2+ anim2 );
		}
	}

}
