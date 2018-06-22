package gameFiles;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import utilities.Handler;
import utilities.UniversalVariables;
import utilities.Window;

public class Game extends Canvas implements Runnable
{
	private Thread thread;
	private boolean running = false;
	
	public static void main(String[] args)
	{
		new Game();
	}
	
	public Game()
	{
		new Window(UniversalVariables.WINDOWWIDTH, UniversalVariables.WINDOWHEIGHT, "Game Title", this);
		new Handler();
	}
	
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		long lastTime = System.nanoTime();
		double amountOfTicks = UniversalVariables.FPS;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1)
			{
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick()
	{
		
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, UniversalVariables.WINDOWWIDTH, UniversalVariables.WINDOWHEIGHT);
		
		g.dispose();
		bs.show();
	}
	
	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop()
	{
		try
		{
			thread.join();
			running = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
