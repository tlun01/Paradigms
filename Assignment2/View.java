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
	JButton b1;
	BufferedImage turtle_image;
	Model model;
	
	View(Controller c, Model m)
	{
		b1 = new JButton("Press to Remove");
		b1.addActionListener(c);
		this.add(b1);
		c.setView(this);
		
		model = m;
		
		try
		{
			this.turtle_image = ImageIO.read(new File("turtle.png"));
		}catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		
	}
	

	void removeButton()
	{
		this.remove(b1);
		this.repaint();
	}
	
	
	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(128, 205, 255));
		g.fillRect(0,  0, this.getWidth(), this.getHeight());
		g.drawImage(this.turtle_image, model.turtle_x, model.turtle_y, null);
	}
}
