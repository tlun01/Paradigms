//Thomas Lunsford
//9-22-21
//A program that allows a user to create "bricks" and can save/load the map

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
	
	Controller()
	{
	}

	public void actionPerformed(ActionEvent e)
	{
	}
	
	View view;
	
	void setView(View v)
	{
		view = v;
	}
	
	Model model;
	
	Controller(Model m)
	{
		model = m;
	}
	
	public void mousePressed(MouseEvent e)
	{
		model.setDestination(e.getX(), e.getY());
	}
	
	public void mouseReleased(MouseEvent e)
	{
		model.setDestination1(e.getX(), e.getY());
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP : keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
		
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
			case KeyEvent.VK_ESCAPE: System.out.println("Exiting now..."); System.exit(0); break;
		}

		char c = e.getKeyChar();
		//save
		if(c == 's' || c == 'S')
		{
			model.marshal().save("map.json");
			System.out.println("Your map has been saved!");
		}
		//load
		if(c == 'l' || c == 'L')
		{
			Json j = Json.load("map.json");
			j.save("temp.json");
			model.unmarshal(j);
			System.out.println("You have loaded your map!");
		}
	 }

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		if(keyRight) model.cameraPos+=4;
		if(keyLeft) model.cameraPos-=4;
		if(keyDown) model.dest_y+=4;
		if(keyUp) model.dest_y-=4;
	}
}
