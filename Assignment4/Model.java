//Thomas Lunsford
//9-10-21
//Creating a "game" in which the user can control a turtle and move it around the screen with either the arrow keys or the mouse

import java.util.ArrayList;

public class Model 
{
	int dest_x;
	int dest_y;
	int dest_x1;
	int dest_y1;
	int cameraPos;
	ArrayList<Brick> bricks;
	Mario mario;

	Model()
	{
		bricks = new ArrayList<Brick>();
		mario = new Mario();
	}

	public void update()
	{
		mario.update();
	}

	public void setDestination(int x, int y)
	{
		this.dest_x = x;
		this.dest_y = y;
		System.out.println("beg. " + x + " " + y);
	}

	public void setDestination1(int x, int y)
	{
		this.dest_x1 = x;
		this.dest_y1 = y;
		System.out.println("end " + x + " " + y);
		bricks.add(new Brick(dest_x + cameraPos, dest_y, dest_x1 - dest_x, dest_y1 - dest_y));
	}

	// public void marioDest(int x)
	// {
	// 	this.mario_x = x;
	// }

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
