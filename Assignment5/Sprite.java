import java.awt.Graphics;
import java.awt.image.BufferedImage;

abstract class Sprite 
{
    int x, y, w, h;

    abstract boolean update();
    abstract void draw(Graphics g);

    boolean isBrick()
    {
        return false;
    }

    boolean isMario()
    {
        return false;
    }

    boolean isCoin()
    {
        return false;
    }

    boolean isCoinBrick()
    {
        return false;
    }

    public boolean checkCollision(Sprite s)
    {
        if(this.x + this.w <= s.x)  //this right < sprite's left
            return false;
		if(this.x >= s.x + s.w)  //this left > sprite's right
            return false;
        if(this.y >= s.y + s.h)  //this top underneath sprite base
            return false;
        if(this.y + this.h <= s.y)   //this base over sprite's top
            return false;
        //System.out.println("collison");
        return true;
    }
}
