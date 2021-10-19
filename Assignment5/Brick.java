import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

class Brick extends Sprite
{
    BufferedImage image = null;
    Model model;


    public Brick(int x1, int y1, Model m)
    {
        x = x1;
        y = y1;
        w = 10;
        h = 10;
        loadImage();
        model = m;
    }
    public Brick(int x1, int y1, int w1, int h1, Model m)
    {
        x = x1;
        y = y1;
        w = w1;
        h = h1;
        loadImage();
        model = m;
    }

    public Brick(Json ob, Model m) 
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = (int)ob.getLong("w");
        h = (int)ob.getLong("h");
        loadImage();
        model = m;
    }

    public void endBrick(int x2, int y2)
    {
        w = x2 - x;
        h = y2 - y;

        if(x2 < x)
            x = x2;
        if(y2 < y)
            y= y2;
            
        loadImage();

    }
    
    void loadImage()
    {
        if(image == null)
            image = View.loadImage("brick.png");
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

    @Override
    public String toString()
    {
        return "Brick located at (" + x + ", " + y + ") with a width = " + w + " and a height = " + h;
    }

    boolean update()
    {
        return true;
    }

    void draw(Graphics g)
    {
        g.drawImage(image, x - model.mario.x + model.mario.marioScreenLocation, y, w, h, null);
    }

    @Override
    boolean isBrick()
    {
        return true;
    }
}
