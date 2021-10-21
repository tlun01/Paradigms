import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Coin extends Sprite
{
    Model model;
    double vert_vel;
    BufferedImage image = null;

    public Coin()
    {
        x = 50;
        y = 50;
        vert_vel = -10.0;
        loadImage();

    }

    public boolean update()
    {
        y += vert_vel;
        x += 5;
        vert_vel += 1.2;
        return true;
    }

    void loadImage()
    {
        System.out.println("loaded");

        if(image == null)
        {
            image = View.loadImage("coin.png");
            this.w = image.getWidth();
            this.h = image.getHeight();
        }
    }
   
    void draw(Graphics g)
    {
        g.drawImage(image, x, y, w, h, null);
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
