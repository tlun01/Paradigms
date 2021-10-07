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
	BufferedImage[] mario_images;
	int cameraPos;

	View(Controller c, Model m)
	{
		c.setView(this);
		model = m;
		cameraPos = 0;
	
		//this.mario_images = loadImage("mario1.png");

		// mario_images = new BufferedImage[5];
		// try{
		// mario_images[0] = ImageIO.read(new File("mario1.png"));
		// mario_images[1] = ImageIO.read(new File("mario2.png"));
		// mario_images[2] = ImageIO.read(new File("mario3.png"));
		// mario_images[3] = ImageIO.read(new File("mario4.png"));
		// mario_images[4] = ImageIO.read(new File("mario5.png"));
		// }catch(Exception e){
		// 	e.printStackTrace(System.err);
		// 	System.exit(1);
		// }
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
		//draw brick
		for(int i = 0; i < model.bricks.size(); i ++)
		{
			Brick b = model.bricks.get(i);
			g.setColor(new Color(0));
			g.fillRect(b.x - cameraPos, b.y, b.w, b.h);
			//b.draw(g);
		}
		//draw mario
		model.mario.draw(g);
	}
	
}
