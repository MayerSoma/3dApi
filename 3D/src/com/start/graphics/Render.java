package com.start.graphics;

import com.start.Display;

public class Render {
	public final int width;
	public final int height;
	public final int[]pixels;
	
	private Display display;
	
	public Render(int width, int height)
	{
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
	}
	
	public void draw(Render render, int xOffsett, int yOffsett)
	{
		for(int y = 0; y < render.height;y++)
		{
			int yPix = y + yOffsett;
			if(yPix < 0 || yPix >= display.HEIGHT)
			{
				continue;
			}
				
			for(int x = 0; x < render.width;x++)
			{
				int xPix = x + xOffsett;
				if(xPix < 0 || xPix >= display.WIDTH)
				{
					continue;
				}
				
				pixels[xPix + yPix * width] = render.pixels[x + y * render.width];
			}
		}
	}

}
