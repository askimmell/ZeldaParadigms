/*
Andrew Kimmell
March 8th, 2022
Programming Paradigms, Assignment 4
*/

import java.awt.Graphics;
import java.awt.image.BufferedImage;

class Link extends Sprite
{
	int imageNum;
	int plusX;
	int plusY;
	boolean left, right, up, down;
	int dir;
	static BufferedImage[] images = null;
	
	View view;
	
	public Link()
	{
		this.x = x;
		this.y = y;
		x = 100;
		y = 100;
		dir = 0;
		
		imageNum = 0;
		
		if(images == null)
		{
			images = new BufferedImage[1000];
			images[0] = View.loadImage("cirno1.png");
			images[1] = View.loadImage("cirno2.png");
			images[2] = View.loadImage("cirno3.png");
			images[3] = View.loadImage("cirno4.png");
			images[4] = View.loadImage("cirno5.png");
			images[5] = View.loadImage("cirno6.png");
			images[6] = View.loadImage("cirno7.png");
			images[7] = View.loadImage("cirno8.png");
			images[8] = View.loadImage("cirno_back1.png");
			images[16] = View.loadImage("cirno_back2.png");
			images[24] = View.loadImage("cirno_back3.png");
			images[32] = View.loadImage("cirno_back4.png");
			images[40] = View.loadImage("cirno_back5.png");
			images[48] = View.loadImage("cirno_back6.png");
			images[56] = View.loadImage("cirno_back7.png");
			images[64] = View.loadImage("cirno_back8.png");
			images[9] = View.loadImage("cirno_idle1.png");
			images[18] = View.loadImage("cirno_idle2.png");
			images[27] = View.loadImage("cirno_idle3.png");
			images[36] = View.loadImage("cirno_idle4.png");
			images[45] = View.loadImage("cirno_idle5.png");
			images[54] = View.loadImage("cirno_idle6.png");
		}
	}
	
	void updateImageNum()
	{
		if(right)
		{
			dir = 1;
			imageNum++;
			if(imageNum > 7)
				imageNum = 0;
		}
		else if(left)
		{
			dir = 2;
			imageNum = (imageNum + 8) - (imageNum%8);
			if(imageNum > 56)
				imageNum = 8;
		}
		else if(up)
		{
			dir = 3;
			imageNum = (imageNum + 9) - (imageNum%9);
			if(imageNum > 56)
				imageNum = 9;
		}
		else if(down)
		{
			dir = 4;
			imageNum = (imageNum + 9) - (imageNum%9);
			if(imageNum > 56)
				imageNum = 9;
		}
	}
	
	public void update()
	{
		if(right)
		{
			right = false;
		}
		if(left)
		{
			left = false;
		}
		if(up)
		{
			up = false;
		}
		if(down)
		{
			down = false;
		}
	}
	
	@Override
	public boolean isLink()
	{
		return true;
	}
	
	public void draw(Graphics g, int c, int d)
	{
		if(x > 700)
			plusX = 700;
		else
			plusX = 0;
		if(y > 460)
			plusY = 460;
		else
			plusY = 0;
		
		g.drawImage(images[imageNum], x - plusX, y - plusY, null);
	}
	
	Json marshal()
	{
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		return ob;
	}
}