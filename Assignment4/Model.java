//Thomas Lunsford
//9-10-21
//Creating a "game" in which the user can control a turtle and move it around the screen with either the arrow keys or the mouse

import java.util.ArrayList;

public class Model 
{

	int cameraPos;
	ArrayList<Brick> bricks;
	Mario mario;

	Model()
	{
		bricks = new ArrayList<Brick>();
		mario = new Mario();

		Json j = Json.load("map.json");
		unmarshal(j);
		System.out.println("Map Loaded!");
	}

	public void update()
	{
		mario.update();
	}

	    // Marshals this object into a JSON DOM
    Json marshal()
    {
        Json ob = Json.newObject();

        Json tmpList = Json.newList();
        ob.add("bricks", tmpList);
        for(int i = 0; i < bricks.size(); i++)
            tmpList.add(bricks.get(i).marshal());
        return ob;
    }

	public void unmarshal(Json ob)
	{
       bricks = new ArrayList<Brick>();
       Json tmpList = ob.get("bricks");
       for(int i = 0; i < tmpList.size(); i++)
           bricks.add(new Brick(tmpList.get(i)));
	}
}
