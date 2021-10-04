import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Brick 
{
    int x, y, w, h;

    public Brick(int x1, int y1)
    {
        x = x1;
        y = y1;
        w = 10;
        h = 10;
    }
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

    public void endBrick(int x2, int y2)
    {
        w = x2 - x;
        h = y2 - y;

        if(x2 < x)
            x = x2;
        if(y2 < y)
            y= y2;
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

}
