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
	
	View(Controller c, Model m)
	{
		model = m;
	}
		
	public void paintComponent(Graphics g)
	{
		int x;
		int y;
		int w;
		int h;

		g.setColor(new Color(128, 255, 255));
		g.fillRect(0,  0, this.getWidth(), this.getHeight());
		for(int i = 0; i < model.bricks.size(); i ++)
		{
			Brick b = model.bricks.get(i);
			g.setColor(new Color(0));
			g.fillRect(b.x, b.y, b.w, b.h);
		}
		x = model.dest_x;
		y = model.dest_y;
		w = model.dest_x1;
		h = model.dest_y1;
		g.setColor(new Color(0));
		g.fillRect(x, y, w, h);
	}
	
}
