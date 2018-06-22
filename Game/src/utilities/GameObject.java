package utilities;

import java.awt.Graphics;

public abstract class GameObject
{
	protected int X;
	protected int y;
	
	public GameObject(int x, int y)
	{
		this.X = x;
		this.y = y;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public void setX(int x)
	{
		this.X = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getX()
	{
		return this.X;
	}
	
	public int getY()
	{
		return this.y;
	}
}
