import java.awt.Graphics;
import java.awt.image.BufferedImage;

class Pot extends Sprite
{
	View view;
	Link link;
	
	int plusX;
	int plusY;
	int imageNum;
	int dir;
	static BufferedImage[] bimages = null;
	
	public Pot()
    {
        x = 0;
        y = 0;
		dir = 0;
		
		imageNum = 0;
		
		if(bimages == null)
		{
			bimages = new BufferedImage[111];
			bimages[0] = View.loadImage("test.jpg");
		}
    }
    
    public Pot(int x1, int y1, int image)
	{
		x = x1;
		y = y1;
		imageNum = image;
		
		if(bimages == null)
		{
			bimages = new BufferedImage[111];
			bimages[0] = View.loadImage("test.jpg");
		}
	}
	
	public Pot(Json ob)
	{
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		imageNum = (int)ob.getLong("imageNum");
		if(bimages == null)
		{
			bimages = new BufferedImage[111];
			bimages[0] = View.loadImage("test.jpg");
			bimages[1] = View.loadImage("ice.png");
		}
	}
	
	public void update()
	{
		if(dir == 1)
			x += 25;
		else if(dir == 2)
			x -= 25;
		else if(dir == 3)
			y -= 25;
		else if(dir == 4)
			y += 25;
	}
	
	Json marshal()
	{
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		ob.add("imageNum", imageNum);
		return ob;
	}
	
	@Override
	public boolean isPot()
	{
		return true;
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
	
	public void startingPoint()
	{
		this.x = Lx;
		this.y = Ly;
	}
	
	void updateImageNum()
	{
		imageNum = 2;
		// if(right)
		// {
			// dir = 1;
			// imageNum++;
			// if(imageNum > 7)
				// imageNum = 0;
		// }
		// else if(left)
		// {
			// dir = 2;
			// imageNum = (imageNum + 8) - (imageNum%8);
			// if(imageNum > 56)
				// imageNum = 8;
		// }
		// else if(up)
		// {
			// dir = 3;
			// imageNum = (imageNum + 9) - (imageNum%9);
			// if(imageNum > 56)
				// imageNum = 9;
		// }
		// else if(down)
		// {
			// dir = 4;
			// imageNum = (imageNum + 9) - (imageNum%9);
			// if(imageNum > 56)
				// imageNum = 9;
		// }
	}
}