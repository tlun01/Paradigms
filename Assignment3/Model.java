//Thomas Lunsford
//9-22-21
//A program that allows a user to create "bricks" and can save/load the map

import java.util.ArrayList;

public class Model 
{
	int dest_x;
	int dest_y;
	int dest_x1;
	int dest_y1;
	int cameraPos;
	ArrayList<Brick> bricks;

	Model()
	{
		bricks = new ArrayList<Brick>();
	}

	public void update()
	{
	}

	public void setDestination(int x, int y)
	{
		this.dest_x = x;
		this.dest_y = y;
		System.out.println(x + " " + y);
	}

	public void setDestination1(int x, int y)
	{
		this.dest_x1 = x;
		this.dest_y1 = y;
		System.out.println(x + " " + y);
		bricks.add(new Brick(dest_x + cameraPos, dest_y, dest_x1 - dest_x, dest_y1 - dest_y));
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
