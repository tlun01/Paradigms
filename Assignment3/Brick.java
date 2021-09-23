//Thomas Lunsford
//9-22-21
//A program that allows a user to create "bricks" and can save/load the map

public class Brick 
{
    int x, y, w, h;
    
    public Brick(int x1, int y1, int w1, int h1)
    {
        x = x1;
        y = y1;
        w = w1;
        h = h1;
    }

    public Brick(Json ob) 
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = (int)ob.getLong("w");
        h = (int)ob.getLong("h");
    }

    Json marshal()
    {

        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        ob.add("w", w);
        ob.add("h", h);
        return ob;
    }

//    public void unmarshal(Json ob)
//    {
//     x = (int)ob.getLong("x");
//     y = (int)ob.getLong("y");
//     w = (int)ob.getLong("w");
//     h = (int)ob.getLong("h");
        
//    }
}
