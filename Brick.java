/*
Andrew Kimmell
March 8th, 2022
Programming Paradigms, Assignment 4
*/

import java.awt.Graphics;
import java.awt.image.BufferedImage;

class Brick extends Sprite
{
	int plusX;
	int plusY;
	int imageNum;
	
	View view;
	Link link;
	
	static BufferedImage[] bimages = null;
	
    public Brick()
    {
        x = 0;
        y = 0;
		
		imageNum = 0;
		
		if(bimages == null)
		{
			bimages = new BufferedImage[111];
			bimages[0] = View.loadImage("ice.png");
		}
    }
    
    public Brick(int x1, int y1)
	{
		x = x1;
		y = y1;
		
		if(bimages == null)
		{
			bimages = new BufferedImage[111];
			bimages[0] = View.loadImage("ice.png");
		}
	}
	
	public Brick(Json ob)
	{
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		if(bimages == null)
		{
			bimages = new BufferedImage[111];
			bimages[0] = View.loadImage("ice.png");
		}
	}

	Json marshal()
	{
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		return ob;
	}
	
	@Override 
	public String toString()
	{
		return "Brick (x,y) = (" + x + ", " + y + ")";
	}
	
	public void draw(Graphics g, int Lx, int Ly)
	{
		if(Lx > 700)
			plusX = 700;
		else
			plusX = 0;
		
		if(Ly > 460)
			plusY = 460;
		else
			plusY = 0;
		g.drawImage(bimages[imageNum], x - plusX, y - plusY, null);
	}
	
	//@Override
	public void update()
	{
		int i;
	}
	
	@Override
	public boolean isBrick()
	{
		return true;
	}
}