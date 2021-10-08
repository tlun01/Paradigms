
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Mario 
{
    static int x;
    int y, px, py;
    final int w = 60;
    final int h = 95;
    double vert_vel;
    static BufferedImage[] images = null;
    int imageNum = 3;
    int marioScreenLocation;
    int frameCounter;


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
        if(vert_vel != 0.0)
        {
            frameCounter++;
        }
        else
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


        //       System.out.println("x, y = " + x + ", " + y +" px, py = " + px + ", " + py);
	}

    @Override
    public String toString()
    {
        return "Mario located at (" + x + ", " + y + ") with a width = " + w + "and a height = " + h;
    }

    void draw (Graphics g)
    {
        g.drawImage(images[imageNum], x - Mario.x + marioScreenLocation, y,null);
    }

    // void drawBackward (Graphics g)
    // {
    //     g.drawImage(images[imageNum], x - Mario.x + marioScreenLocation, y, -w, h,null);
    // }

    void updateImageNum()
    {
        imageNum++;
        if(imageNum > 4)
            imageNum = 0;
    }

}
