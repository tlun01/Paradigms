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
		ArrayList<Sprite> coins = new ArrayList<Sprite>();
		Iterator<Sprite> iter = sprites.iterator();
		while(iter.hasNext())
		{
			Sprite s = iter.next();
			s.update();
			if(!s.update())
			{
				iter.remove();
			}
			else if(s.isBrick())
			{
				if(mario.checkCollision(s))
				{
					mario.getOutOfTheObstacle(s);
					//coins.add()
					//adding into an iterator is hard!
				}
			}
		}
	}

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
