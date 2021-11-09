import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class CoinBrick extends Sprite
{
    BufferedImage image = null;
    Model model;
    Brick brick;
    int coinCounter = 0;

    public CoinBrick(int x1, int y1, Model m)
    {
        x = x1;
        y = y1;
        loadImage();
        model = m;
        coinCounter = 0;
    }

    public void endCoinBrick(int x2, int y2)
    {
        w = x2 - x;
        h = y2 - y;

        if(x2 < x)
        x = x2;
        if(y2 < y)
        y = y2;

        loadImage();
        coinCounter = 0;
    }

    public CoinBrick(Json ob, Model m) 
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = (int)ob.getLong("w");
        h = (int)ob.getLong("h");
        loadImage();
        model = m;
        coinCounter = 0;
    }

    Json marshal()
    {

        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        ob.add("w", w);
        ob.add("h", h);
        coinCounter = 0;
        return ob;
    }

    boolean update()
    {
        //System.out.println("coin counter working: " + coinCounter);
        if(coinCounter >= 5)
        {
            // brick = new Brick(this.x + model.mario.x - model.mario.marioScreenLocation, this.y, model);
            // brick.endBrick(this.w + model.mario.x - model.mario.marioScreenLocation, this.h);
			// model.sprites.add(brick);
            return false;
        }
        else
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
