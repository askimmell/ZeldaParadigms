import java.awt.Graphics;
import java.awt.image.BufferedImage;

abstract class Sprite
{
	int x, y;
	int Lx, Ly;
	abstract public void draw(Graphics g, int x, int y);
	abstract public void update();
	
	abstract Json marshal();
	
	public boolean isLink()
	{
		return false;
	}
	
	public boolean isBrick()
	{
		return false;
	}
	
	public boolean isPot()
	{
		return false;
	}
	
	public boolean isReimu()
	{
		return false;
	}
}