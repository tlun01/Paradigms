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
	CoinBrick coinBrick;
	boolean edit = false;
	boolean coinBrickDraw = false;
	
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
		{
			if(coinBrickDraw)
				coinBrick = new CoinBrick(e.getX() + model.mario.x - model.mario.marioScreenLocation, e.getY(), model);	
			else
				brick = new Brick(e.getX() + model.mario.x - model.mario.marioScreenLocation, e.getY(), model);
		}
	}
	
	public void mouseReleased(MouseEvent e)
	{
		if(edit)
		{
			if(coinBrickDraw)
			{
				coinBrick.endCoinBrick(e.getX() + model.mario.x - model.mario.marioScreenLocation, e.getY());
				model.sprites.add(coinBrick);
			}
			else
			{
				brick.endBrick(e.getX() + model.mario.x - model.mario.marioScreenLocation, e.getY());
				model.sprites.add(brick);
			}
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
			case KeyEvent.VK_SPACE: keyUp = true; break;
		
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
			case KeyEvent.VK_SPACE: keyUp = false; break;
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
		//swap from brick to coin brick
		if(c == 'c' || c == 'C')
		{
			if(edit)
			{
			coinBrickDraw = !coinBrickDraw;
			System.out.println("Coin brick drawing mode: " + coinBrickDraw);
			}
		}
	 }

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		model.mario.savePreviousCoords();
		if(keyRight) 
		{
			Mario.direction = true;
			model.mario.x += 5;
			view.backgroundLocation += 2;
			model.mario.updateImageNum();
		}
		if(keyLeft)
		{
			Mario.direction = false;
			model.mario.updateImageNum();
			model.mario.x -= 5;
			view.backgroundLocation -= 2;
		}
		//if(keyDown);
		if(keyUp)
		{
			if(model.mario.frameCounter < 5)
				model.mario.vert_vel -= 5.3;
		} 
	}
}
