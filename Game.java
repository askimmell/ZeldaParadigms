/*
Andrew Kimmell
March 8th, 2022
Programming Paradigms, Assignment 4
*/

import java.util.Iterator;
import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
    Model model;
    View view;
    Controller controller;
    
	public Game()
	{
        model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		this.setTitle("A4 - Cirno Game");
		this.setSize(700, 500);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		view.addMouseListener(controller);
        this.addKeyListener(controller);
		model.unmarshal();
		System.out.println("Map Loaded");
	}
    public void run()
    {
        while(true)
        {
            controller.update();
            model.update();
            view.repaint(); // Indirectly calls View.paintComponent
            Toolkit.getDefaultToolkit().sync(); // Updates screen
			

            // Go to sleep for 30 miliseconds
			// Changed this to 30 from 25 for smoother animation
            try
            {
                Thread.sleep(30);
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

	public static void main(String[] args)
	{
		Game g = new Game();
        g.run();
	}
}
