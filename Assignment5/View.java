//Thomas Lunsford
//9-10-21
//Creating a "game" in which the user can control a turtle and move it around the screen with either the arrow keys or the mouse

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;


class View extends JPanel
{
	Model model;
	BufferedImage background = null;
	BufferedImage floor = null;
	int backgroundLocation;

	View(Controller c, Model m)
	{
		c.setView(this);
		model = m;
	}
		
	static BufferedImage loadImage(String filename)
	{
		BufferedImage im = null;
		
		try{
			im = ImageIO.read(new File(filename));
		}catch(Exception e){
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return im;
	}

	public void paintComponent(Graphics g)
	{
		//background color
		g.setColor(new Color(128, 255, 255));
		//draw background
		g.fillRect(0,  0, this.getWidth(), this.getHeight());
		if(background == null)
			background =loadImage("cloudbackground.jpg");
		g.drawImage(background, -300 - Mario.x + backgroundLocation, 0, 1500, this.getHeight(), null);
		//draw ground
		g.setColor(new Color(0, 0, 0));
		g.drawLine(0,496,2000,496);
		if(floor == null)
			floor =loadImage("floor.png");
		g.drawImage(floor, -300 - Mario.x + model.mario.marioScreenLocation, 486, 1500, 100, null);
		//draw brick
		for(int i = 0; i < model.bricks.size(); i ++)
		{
			Brick b = model.bricks.get(i);
			b.draw(g, model.mario.marioScreenLocation);
		}
		//draw mario
		model.mario.draw(g, Mario.direction);
		
	}
	
}
