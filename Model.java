/*
Andrew Kimmell
March 8th, 2022
Programming Paradigms, Assignment 4
*/

import java.util.Iterator;
import java.util.ArrayList;

class Model
{
	Link link;
	Pot pot;
	Reimu reimu;
	View view;
	int prevX;
	int prevY;
	int j;
	int testvar;
	int boomvar;
	
	int breakFrame;
	boolean isBroken;
	boolean isReallyBroken;
	
	ArrayList<Sprite> sprites;
	

	Model()
	{
		link = new Link();
		pot = new Pot();
		reimu = new Reimu();
		sprites = new ArrayList<Sprite>();
		sprites.add(pot);
		sprites.add(link);
		sprites.add(reimu);
		
		breakFrame = 0;
		isBroken = false;
		isReallyBroken = false;
	}

	public void update()
	{
		Iterator<Sprite> it = sprites.iterator();
		while(it.hasNext())
		{
			Sprite s = it.next();
			s.update();
		}
		checkCollisions();
		prevX = link.x;
		prevY = link.y;
		
		if(isBroken)
		{
			if(breakFrame < 30)
				breakFrame += 1;
			else
			{
				isReallyBroken = true;
				breakFrame = 0;
			}
		}
	}
	
	// Using collision detection to stop link from passing thru bricks
	void checkCollisions()
	{
		for(int i = 1; i < sprites.size(); i++)
		{
			int j;
			j = sprites.size();
			Sprite l = sprites.get(0);
			Sprite b = sprites.get(i);
			Sprite v = sprites.get(j-1);
			
			if(linkIsColliding(b, l))
			{
				if(b.isBrick())
				{
					link.x = prevX;
					link.y = prevY;
				}
				if(b.isReimu())
				{
					pot.updateImageNum();
					if(this.testvar == 1)
						b.x = b.x+=15;
					else if(this.testvar == 2)
						b.x = b.x-=15;
					else if(this.testvar == 3)
						b.y = b.y+=15;
					else if(this.testvar == 4)
						b.y = b.y-=15;
				}
			}
			if(v.isPot())
			{
				if(linkIsColliding(b, v))
				{
					if(b.isBrick())
					{
						sprites.remove(v);
					}
					if(b.isReimu())
					{
						sprites.remove(b);
						sprites.remove(v);
					}
				}
			}
		}
	}
	
	// Basic collision detection logic taken from class slides
	boolean linkIsColliding(Sprite b, Sprite l)
	{
		int right, left, top, bottom;
		right = 100;
		left = 50;
		top = 150;
		bottom = 50;
		
		if(l.isPot()){
			right = 50;
			top = 50;}
		// link right going into brick left
		if(l.x + right < b.x){
			return false;}
		// link left going into brick right
		if(l.x > b.x + left)
			return false;
		// link bottom going into brick top
		if(l.y + top < b.y)
			return false;
		// link top going into brick bottom
		if(l.y > b.y + bottom)
			return false;
		return true;
	}
	
	Json marshal()
    {
        Json ob = Json.newObject();
        Json tmpListBricks = Json.newList();
        ob.add("bricks", tmpListBricks);
		Json tmpListLink = Json.newList();
		ob.add("link", tmpListLink);
		Json tmpListPot = Json.newList();
		ob.add("pot", tmpListPot);
		Json tmpListReimu = Json.newList();
		ob.add("reimu", tmpListReimu);
        for(int i = 0; i < sprites.size(); i++)
		{
			if(sprites.get(i).isBrick())
				tmpListBricks.add(sprites.get(i).marshal());
            if(sprites.get(i).isLink())
				tmpListLink.add(sprites.get(i).marshal());
			if(sprites.get(i).isPot())
				tmpListPot.add(sprites.get(i).marshal());
			if(sprites.get(i).isReimu())
				tmpListReimu.add(sprites.get(i).marshal());
		}	
        return ob;
    }
	
	void unmarshal()
	{
		Json ob = Json.load("map.json");
		sprites = new ArrayList<Sprite>();
		sprites.add(link);
		Json tmpList = ob.get("bricks");
		Json tmpListReimu = ob.get("reimu");
		for(int i = 0; i < tmpList.size(); i++)
			sprites.add(new Brick(tmpList.get(i)));
		for(int i = 0; i < tmpListReimu.size(); i++)
			sprites.add(new Reimu(tmpListReimu.get(i)));
	}
	
	void addBoomerang()
	{
		int i = sprites.size();
		Sprite b = sprites.get(i-1);
		if(!b.isPot())
		{
			pot = new Pot();
			pot.dir = link.dir;
			pot.x = link.x+25;
			pot.y = link.y+50;
			sprites.add(pot);
		}
	}
}
