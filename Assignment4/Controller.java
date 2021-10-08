//Thomas Lunsford
//9-10-21
//Creating a "game" in which the user can control mario

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
	View view;
	Model model;
	Brick brick;
	boolean edit = false;
	
	Controller()
	{
	}

	public void actionPerformed(ActionEvent e)
	{

	}
	
	void setView(View v)
	{
		view = v;
	}
	
	Controller(Model m)
	{
		model = m;
	}
	
	public void mousePressed(MouseEvent e)
	   {
		if (edit)
			brick = new Brick(e.getX() + Mario.x - model.mario.marioScreenLocation, e.getY());
	   }
	
	public void mouseReleased(MouseEvent e)
	{
		if(edit)
		{
			brick.endBrick(e.getX() + Mario.x - model.mario.marioScreenLocation, e.getY());
			model.bricks.add(brick);
		}
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
			model.unmarshal(j);
			System.out.println("You have loaded your map!");
		}
		//edit
		if(c =='e' || c == 'E')
		{
			edit = !edit;
			System.out.println("Edit mode: " + edit);
		}
	 }

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		if(keyRight) 
		{
			model.mario.px = model.mario.x;
			model.mario.py = model.mario.y;
			model.mario.x += 5;
			model.mario.updateImageNum();
		}
		if(keyLeft)
		{
			model.mario.px = model.mario.x;
			model.mario.py = model.mario.y;
			model.mario.updateImageNum();
			model.mario.x -= 5;
		}
		//if(keyDown);
		if(keyUp)
		{
			model.mario.py = model.mario.y;
			if(model.mario.frameCounter < 5)
				model.mario.vert_vel -= 5.3;
		} 
	}
}
