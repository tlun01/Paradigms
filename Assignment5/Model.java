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
		//System.out.println(sprites);
	}

	public void update()
	{
		//ArrayList<Sprite> coins = new ArrayList<Sprite>();
		Iterator<Sprite> iter = sprites.iterator();
		while(iter.hasNext())
		{
			Sprite s = iter.next();
			//s.update();
			if(!s.update())
			{
				iter.remove();
				continue;
			}
			if(s.isBrick() || s.isCoinBrick())
			{
				if(mario.checkCollision(s))
				{	
					if(mario.getOutOfTheObstacle(s))
					{
						((CoinBrick)s).coinCounter++;
						sprites.add(new Coin(s.x, s.y, this));
						break;
					}
				}
			}
		}

	}

	    // Marshals this object into a JSON DOM
    Json marshal()
    {
        Json ob = Json.newObject();
        Json tmpList = Json.newList();
		Json tmpList1 = Json.newList();
        ob.add("bricks", tmpList);
		ob.add("coinBricks", tmpList1);
        for(int i = 0; i < sprites.size(); i++)
		{
			Sprite s = sprites.get(i);
			if(s.isBrick())
				tmpList.add(((Brick)s).marshal());
			if(s.isCoinBrick())
				tmpList1.add(((CoinBrick)s).marshal());
		}
		
        return ob;
    }

	public void unmarshal(Json ob)
	{
    	Json tmpList = ob.get("bricks");
    	for(int i = 0; i < tmpList.size(); i++)
    		sprites.add(new Brick(tmpList.get(i), this));
		tmpList = ob.get("coinBricks");
		for(int i = 0; i < tmpList.size(); i++)
			sprites.add(new CoinBrick(tmpList.get(i), this));
	}
}
