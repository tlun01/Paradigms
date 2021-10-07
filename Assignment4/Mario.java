
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Mario 
{
    int x;
    int y;
    double vert_vel;
    static BufferedImage image = null;


    public Mario()
    {
        x = 200;
        y = 0;
        vert_vel = 0;

        if(image == null)
        {
            image = View.loadImage("mario1.png");
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
        g.drawImage(image, x, y, null);
    }

}
