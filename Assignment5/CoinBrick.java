import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class CoinBrick extends Sprite
{
    BufferedImage image = null;
    Model model;

    // public CoinBrick()
    // {
    //     x = 50;
    //     y = 50;
    //     w = 50;
    //     h = 50;
    //     loadImage();
    // }

    public CoinBrick(int x1, int y1, Model m)
    {
        x = x1;
        y = y1;
        w = 10;
        h = 10;
        loadImage();
        model = m;
    }

    public CoinBrick(int x1, int y1, int w1, int h1, Model m)
    {
        x = x1;
        y = y1;
        w = w1;
        h = h1;
        loadImage();
        model = m;
    }

    public void endCoinBrick(int x2, int y2)
    {
        w = x2 - x;
        h = y2 - y;

        if(x2 < x)
            x = x2;
        if(y2 < y)
            y= y2;
            
        loadImage();
    }

    public CoinBrick(Json ob, Model m) 
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = (int)ob.getLong("w");
        h = (int)ob.getLong("h");
        loadImage();
        model = m;
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

    boolean update()
    {
        return true;
    }
    
    void loadImage()
    {
        if(image == null)
        {
            image = View.loadImage("coinBrick.png");
        }
    }

    void draw(Graphics g)
    {
        g.drawImage(image, x - model.mario.x + model.mario.marioScreenLocation, y, w, h, null);
    }

    @Override
    public String toString()
    {
        return "Coin Brick located at (" + x + ", " + y + ") with a width = " + w + " and a height = " + h;
    }

    @Override
    boolean isCoinBrick()
    {
        return true;
    }
}
