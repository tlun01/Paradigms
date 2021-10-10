
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.*;


public class Mario 
{
    static int x;
    int y, px, py;
    final int w = 60;
    final int h = 95;
    double vert_vel;
    static BufferedImage[] images = null;
    int imageNum = 0;
    int marioScreenLocation;
    int frameCounter;
    static boolean direction = true;

   


    public Mario()
    {
        marioScreenLocation = 150;
        x = 0;
        y = 0;
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

    void update()
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

	}

    @Override
    public String toString()
    {
        return "Mario located at (" + x + ", " + y + ") with a width = " + w + "and a height = " + h;
    }

    void draw (Graphics g, boolean direction)
    {
        if(direction == true)   //facing right
            g.drawImage(images[imageNum], x - Mario.x + marioScreenLocation, y,null);
        else if(direction == false) //facing left
            g.drawImage(images[imageNum], x - Mario.x + marioScreenLocation + w, y, -w, h, null);

    }

    void updateImageNum()
    {
        // imageNum++;
        // if(imageNum > 4)
        //     imageNum = 0; 
        
        ArrayList<Integer> num = new ArrayList<Integer>();
        num.add(0);
        num.add(1);
        num.add(2);
        num.add(3);
        num.add(4);
        Iterator<Integer> iter = num.iterator();


        if(!iter.hasNext())
            iter = num.iterator();
        while(iter.hasNext())
        System.out.println(iter.next() + "\n");
        imageNum = iter.next();
  
    }

}
