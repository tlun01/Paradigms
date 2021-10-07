
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Mario 
{
    int x;
    int y;
    double vert_vel;
    static BufferedImage[] images = null;
    int imageNum = 3;


    public Mario()
    {
        x = 200;
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
		vert_vel += 1.2;
		y += vert_vel;

        if(y > 400)
		{
			vert_vel = 0.0;
			y = 400; // snap back to the ground
		}
	}

    void draw (Graphics g)
    {
        g.drawImage(images[imageNum], x, y, null);
    }

    // void drawBackward (Graphics g)
    // {
    //     g.drawImage(images[imageNum], x - 40, y, null);
    // }

    void updateImageNum()
    {
        imageNum++;
        if(imageNum > 4)
            imageNum = 0;
    }

}
