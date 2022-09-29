/*
Andrew Kimmell
March 8th, 2022
Programming Paradigms, Assignment 4
*/

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.Color;

class View extends JPanel
{
	int scrollPosX;
	int scrollPosY;
	Model model;
	
	public void paintComponent(Graphics g)
	{
        g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(int i = 0; i < model.sprites.size(); i++)
		{
			int x = model.link.x;
			int y = model.link.y;
			Sprite b = model.sprites.get(i);
			b.draw(g,x,y);
		}
	}

	View(Controller c, Model m)
	{
		c.setView(this);
        model = m;
	}
	
	static BufferedImage loadImage(String filename)
	{
		BufferedImage im = null;
		try
		{
			im = ImageIO.read(new File(filename));
		}
		catch(Exception e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return im;
	}
}
