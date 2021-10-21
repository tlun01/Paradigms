import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class CoinBrick extends Sprite
{
    BufferedImage image = null;
    Model model;

    public CoinBrick()
    {
        x = 50;
        y = 50;
        loadImage();

    }

    boolean update()
    {
        return true;
    }
    
    void loadImage()
    {
        if(image == null)
        {
            image = View.loadImage("block1.png");
            this.w = image.getWidth();
            this.h = image.getHeight();
        }
    }

    void draw(Graphics g)
    {
        g.drawImage(image, x, y, w, h, null);
    }

    @Override
    boolean isCoinBrick()
    {
        return true;
    }
}
