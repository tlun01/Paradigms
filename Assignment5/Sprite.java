import java.awt.Graphics;
import java.awt.image.BufferedImage;

abstract class Sprite 
{
    int y, w, h;

    abstract void update();
    abstract void draw(Graphics g);

    boolean isBrick()
    {
        return false;
    }

    boolean isMario()
    {
        return false;
    }
}
