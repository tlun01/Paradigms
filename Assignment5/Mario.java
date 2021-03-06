import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.*;

public class Mario extends Sprite
{
    int px, py;
    double vert_vel;
    static BufferedImage[] images = null;
    int imageNum;
    int marioScreenLocation;
    int frameCounter;
    static boolean direction = true;
    CoinBrick coinBrick;
    boolean iHitMyHead = false;

    public Mario()
    {
        marioScreenLocation = 150;
        x = 0;
        y = 0;
        w = 60;
        h = 95;
        vert_vel = 0;

        if(images == null)
        {
            images = new BufferedImage[5];
            images[0] = View.loadImage("mario1.png");
            images[1] = View.loadImage("mario2.png");
            images[2] = View.loadImage("mario3.png");
            images[3] = View.loadImage("mario4.png");
            images[4] = View.loadImage("mario5.png");
        }
    }

    boolean update()
	{
        if(vert_vel != 0.0) //if not standing on ground or brick start counting frames
        {
            frameCounter++;
        }
        else    //once landed set frames back to zero
        {
            frameCounter = 0;
        }

        vert_vel += 1.2;
		y += vert_vel;

        if(y > 400)
		{
			vert_vel = 0.0;
			y = 400; // snap back to the ground
		}
        return true;
	}

    boolean getOutOfTheObstacle(Sprite s)
    {
        if(this.x + this.w > s.x && this.px + this.w <= s.x) //collision with left side of brick
            this.x = s.x - this.w;
        if(this.x < s.x + s.w && this.px >= s.x + s.w) //collision with right side of brick     
                this.x = s.x +s.w;
        if(this.y + this.h > s.y && this.py + this.h <= s.y)   //collision with top of brick
            {
                this.y = s.y - this.h;
                vert_vel = 0.0;
            }
        if(this.y < s.y + s.h && this.py >= s.y + s.h) //collision with bottom of brick
            {
                this.y = s.y + s.h + 5;
                frameCounter = 6;
                vert_vel = 0.1;
                if(s.isCoinBrick())
                {
                    return true;
                }
            }
        return false;
    }

    void savePreviousCoords()
    {
        px = x;
        py = y;
    }

    @Override
    public String toString()
    {
        return "Mario located at (" + x + ", " + y + ") with a width = " + w + "and a height = " + h;
    }

    void updateImageNum()
    {
        imageNum++;
        if(imageNum > 4)
            imageNum = 0; 
    }

    @Override
    void draw (Graphics g)  //, boolean direction
    {
        //if(direction == true)   //facing right
            g.drawImage(images[imageNum], marioScreenLocation, y,null);
        // else if(direction == false) //facing left
        //     g.drawImage(images[imageNum], marioScreenLocation + w, y, -w, h, null);

    }
 
    @Override
    boolean isMario()
    {
        return true;
    }

}
