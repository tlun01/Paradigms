//Thomas Lunsford
//9-10-21
//Creating a "game" in which the user can control a turtle and move it around the screen with either the arrow keys or the mouse

import java.util.ArrayList;
import java.util.*;
import java.util.Iterator;

public class Model 
{
	//ArrayList<Brick> bricks;
	Mario mario;
	ArrayList<Sprite> sprites;

	Model()
	{
		sprites = new ArrayList<Sprite>();
		mario = new Mario();
		sprites.add(mario);
		Json j = Json.load("map.json");
		unmarshal(j);
		System.out.println("Map Loaded!");
	}

	public void update()
	{
		Iterator<Sprite> iter = sprites.iterator();
		while(iter.hasNext())
		{
			Sprite s = iter.next();
			s.update();
			if(s.isBrick())
			{
				if(mario.checkCollision(s))
					mario.getOutOfTheObstacle(s);
			}
		}
		// for(int i = 0; i < sprites.size(); i++)
		// {
		// 	sprites.get(i).update();
		// }
		//mario.update();
		//checkCollisions();
	}

	// void checkCollisions()
	// {
	// 	for(int i = 0; i < bricks.size(); i ++)
	// 	{
	// 		Brick b = bricks.get(i);
	// 		if(marioIsColliding(b))
	// 		{
	// 			// System.out.println("I'm colliding!");
	// 			// System.out.println(mario);
	// 			// System.out.println(b + "\n");
			
	// 			//on top of the brick
	// 			if(mario.py + mario.h <= b.y)
	// 			{
	// 				mario.y = b.y - mario.h;
	// 				mario.vert_vel = 0.0;
	// 			}	
	// 			//underneath the brick
	// 			else if(mario.py >= b.y + b.h)
	// 			{	
	// 				mario.y = b.y + b.h;
	// 				mario.frameCounter = 6;
	// 				mario.vert_vel += 5.3;
	// 			}			
	// 			//running into the left side of the brick
	// 			else if(mario.px <= b.x)
	// 				mario.x = b.x - mario.w;
	// 			//running into the right side of the brick 
	// 			else if(mario.px >= b.x + b.w)
	// 			{
	// 				mario.x = b.x + b.w;
	// 			}
	// 		}
	// 	}
	// }

	// boolean marioIsColliding(Brick b)
	// {
	// 	//Mario Right < Brick Left
	// 	if(mario.x + mario.w < b.x)
	// 		return false;
	// 	//Mario Left > Brick Right
	// 	if(mario.x > b.x + b.w)
	// 		return false;
	// 	//Mario Top underneath Brick Base
	// 	if(mario.y > b.y + b.h)
	// 		return false;
	// 	//Mario Base over Brick Top
	// 	if(mario.y + mario.h < b.y)
	// 		return false;

	// 	return true;
	// }
	    // Marshals this object into a JSON DOM
    Json marshal()
    {
        Json ob = Json.newObject();
        Json tmpList = Json.newList();
        ob.add("bricks", tmpList);
        for(int i = 0; i < sprites.size(); i++)
		{
			Sprite s = sprites.get(i);
			if(s.isBrick())
				tmpList.add(((Brick)s).marshal());
		}
        return ob;
    }

	public void unmarshal(Json ob)
	{
	   sprites = new ArrayList<Sprite>();
	   mario = new Mario();
	   sprites.add(mario);
       Json tmpList = ob.get("bricks");
       for(int i = 0; i < tmpList.size(); i++)
           sprites.add(new Brick(tmpList.get(i), this));
		
		// Iterator<Brick> iter = bricks.iterator();
		// while(iter.hasNext())
		// 	System.out.println(iter.next() + "\n");
	}
}
