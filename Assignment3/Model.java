//Thomas Lunsford
//9-10-21
//Creating a "game" in which the user can control a turtle and move it around the screen with either the arrow keys or the mouse

import java.util.ArrayList;

public class Model 
{
	int turtle_x;
	int turtle_y;
	int dest_x;
	int dest_y;
	ArrayList<Brick> bricks;

	Model()
	{
		bricks = new ArrayList<Brick>();
		Brick b = new Brick(400, 300, 200, 100);
		bricks.add(b);
	}

	public void update()
	{
		// Move the turtle
		if(this.turtle_x < this.dest_x)
			this.turtle_x += Math.min(4, dest_x - turtle_x);
		else if(this.turtle_x > this.dest_x)
			this.turtle_x -= Math.max(4, dest_x - turtle_x);
		if(this.turtle_y < this.dest_y)
			this.turtle_y += Math.min(4, dest_y - turtle_y);
		else if(this.turtle_y > this.dest_y)
			this.turtle_y -= Math.max(4, dest_y - turtle_y);
	}

	public void setDestination(int x, int y)
	{
		this.dest_x = x;
		this.dest_y = y;
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

}
