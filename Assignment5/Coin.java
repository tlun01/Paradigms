import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Coin extends Sprite
{
    Model model;
    double vert_vel;
    BufferedImage image = null;
    Random rand = new Random();
    int upperbound = 20;
    int int_random = rand.nextInt(upperbound);


    public Coin(int x1, int y1, Model m)
    {
        x = x1;
        y = y1;
        vert_vel = -10.0;
        loadImage();
        model = m;
    }

    public boolean update()
    {
        y += vert_vel;
        x += (int_random - 10);
        vert_vel += 1.2;

        if(this.y > 500)
            return false;
        else
            return true;
    }

    void loadImage()
    {
        if(image == null)
        {
            image = View.loadImage("coin.png");
            this.w = image.getWidth();
            this.h = image.getHeight();
        }
    }
   
    void draw(Graphics g)
    {
        g.drawImage(image, x - model.mario.x + model.mario.marioScreenLocation, y, w, h, null);
    }

    @Override
    boolean isCoin()
    {
        return true;
    }
    
    @Override
    public String toString()
    {
        return "Coin located at (" + x + ", " + y + ") with a width = " + w + " and a height = " + h;
    }

}
