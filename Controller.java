/*
Andrew Kimmell
March 8th, 2022
Programming Paradigms, Assignment 4
*/

import java.util.Iterator;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
    boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean keyControl;
	boolean edit = false;
	View view;
    Model model;
	Brick b;
	Reimu reimu;
	Pot p;
	Link link;
	boolean brickYes;
	
    public void mousePressed(MouseEvent e)
    {
		if(edit)
		{
			int ex = e.getX() - (e.getX() % 50) + view.scrollPosX;
			int why = e.getY() - (e.getY() % 50) + view.scrollPosY;
			
			brickYes = false;
			
			// iff a brick is already there
			for(int i = 0; i < model.sprites.size(); i++)
			{
				int exx = model.sprites.get(i).x;
				int whyy = model.sprites.get(i).y;
				
				if(ex == exx && why == whyy)
				{
					brickYes = true;
					model.sprites.remove(i);
				}
				else if(ex != exx && why != whyy)
				{
					// b = new Brick();
					// b.x = ex;
					// b.y = why;
					reimu = new Reimu();
					reimu.x = ex;
					reimu.y = why;
				}
			}
		}
    }

    public void mouseReleased(MouseEvent e) 
	{
		if(edit)
		{
			if(brickYes)
				model.sprites.remove(b);
			else
				model.sprites.add(reimu);
		}
	}
    public void mouseEntered(MouseEvent e) {    }
    public void mouseExited(MouseEvent e) {    }
    public void mouseClicked(MouseEvent e) 
	{    
		if(e.getY() < 100)
		{
			System.out.println("break here");
		}
	}

	void setView(View v)
	{
		view = v;
	}

	Controller()
	{
	}

	public void actionPerformed(ActionEvent e)
	{
	}
    
    Controller(Model m)
	{
		model = m;
	}
    
    public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_CONTROL: keyControl = true; break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_CONTROL: keyControl = false; break;
            case KeyEvent.VK_ESCAPE: System.out.println("Exiting game...");System.exit(0); break;
		}
		
		char x = e.getKeyChar();
		if(x == 's' || x == 'S')
		{
			model.marshal().save("map.json");
			System.out.println("Map Saved");
		}
		if(x == 'l' || x == 'L')
		{
			model.unmarshal();
			System.out.println("Map Loaded");
		}
		if(x == 'e' || x == 'E')
		{
			if(!edit)
			{
				System.out.println("Edit Mode");
				edit = true;
				
			}	
			else if(edit)
			{
				System.out.println("Play Mode");
				edit = false;
			}
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		if(keyRight) 
		{
			model.testvar = 1;
			model.link.x += 15;
			model.link.right = true;
			model.link.updateImageNum();
			if(model.link.x > 700 && view.scrollPosX != 700)
				view.scrollPosX = view.scrollPosX + 700;
		}
		if(keyLeft) 
		{
			model.testvar = 2;
			model.link.x -= 15;
			model.link.left = true;
			model.link.updateImageNum();
			if(model.link.x < 700 && view.scrollPosX != 0)
				view.scrollPosX = view.scrollPosX - 700;
		}
		if(keyDown) 
		{
			model.testvar = 3;
			model.link.y += 15;
			model.link.down = true;
			model.link.updateImageNum();
			if(model.link.y > 460 && view.scrollPosY != 460)
				view.scrollPosY = view.scrollPosY + 460;
		}
		if(keyUp) 
		{
			model.testvar = 4;
			model.link.y -= 15;
			model.link.up = true;
			model.link.updateImageNum();
			if(model.link.y < 460 && view.scrollPosY != 0)
				view.scrollPosY = view.scrollPosY - 460;
		}
		if(keyControl)
		{
			for(int i = 1; i < model.sprites.size(); i++)
			{
				Sprite b = model.sprites.get(i);
				if(!b.isPot())
					model.addBoomerang();
			}
		}
	}
}
