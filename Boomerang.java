import java.awt.Graphics;
import java.awt.image.BufferedImage;

class Boomerang extends Sprite
{
	View view;
	Link link;
	
	int plusX;
	int plusY;
	int imageNum;
	static BufferedImage[] bimages = null;
	
	public Boomerang()
    {
		this.x = x;
		this.y = y;
        x = 300;
        y = 300;
		
		imageNum = 0;
		
		if(bimages == null)
		{
			bimages = new BufferedImage[111];
			bimages[0] = View.loadImage("reimu.png");
		}
    }
    
    public Boomerang(int x1, int y1)
	{
		x = x1;
		y = y1;
		
		if(bimages == null)
		{
			bimages = new BufferedImage[111];
			bimages[0] = View.loadImage("reimu.png");
		}
	}
	
	public Boomerang(Json ob)
	{
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		if(bimages == null)
		{
			bimages = new BufferedImage[111];
			bimages[0] = View.loadImage("reimu.png");
		}
	}
	
	public void update()
	{
		x += 25;
		//System.out.println(x);
	}
	
	Json marshal()
	{
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		return ob;
	}
	
	@Override
	public boolean isBoomerang()
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
	
	public int getCount()
	{
		return x;
	}
}