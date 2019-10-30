package com.start;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.start.graphics.Render;
import com.start.graphics.Screen;

public class Display extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String Title = "2 and 3D application Beta version: 1.0";
	
	private Thread thread;
	private Screen screen;
	private Render render;
	private BufferedImage img;
	private boolean running = false;
	private int[] pixels;
	
	public Display()
	{
		screen =  new Screen(WIDTH,HEIGHT);
		img = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
	}
	
	private void start()
	{
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
		System.out.println("Start method lauched!");
	}
	private void stop()
	{
		if(!running)
			return;
		running = false;
		System.out.println("Stop method lauched!");
		try {
			thread.join();
			}
		catch (Exception e)
			{
			e.printStackTrace();
			System.exit(0);
			}
	}
	public void run()
	{
		int frames = 0;
		double unprocessedSeconds = 0;
		long previousTime = System.nanoTime();
		double secondPerTick = 1 / 60.0;
		int tickCount = 0;
		boolean ticked = false;
		while(running=true)
		{
			long currentTime = System.nanoTime();
			long passedTime = currentTime - previousTime;
			previousTime = currentTime;
			unprocessedSeconds += passedTime / 1000000000.0;
			
			while(unprocessedSeconds > secondPerTick)
			{
				tick();
				unprocessedSeconds -= secondPerTick;
				ticked = true;
				tickCount++;
				if(tickCount%60==0)
				{
					System.out.println(frames+" FPS");
					previousTime += 1000;
					frames = 0;
				}
			}
			if(ticked)
			{
				render();
				frames++;
			}
			render();
			frames++;
		}
	}
	
	private void tick()
	{
		
	}
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null)
		{
			createBufferStrategy(3);
			return;
		}
		screen.render();
		for(int i = 0; i < WIDTH * HEIGHT;i++)
		{
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
		g.dispose();
		bs.show();
	}
	
	
	public static void main(String[] args)
	{
		Display game = new Display();
		JFrame frame = new JFrame();
		frame.add(game);
		frame.pack();
		frame.setTitle(Title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH,HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		System.out.println("Main Args Lauched!");
		game.start();
		
		
		
		
		
		
	}

}
