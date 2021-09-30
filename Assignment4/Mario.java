
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Mario 
{
    int x;
    int y;
    double vert_vel;

    public Mario()
    {
        x = 200;
        y = 0;
        vert_vel = 0;
    }

    void update()
	{
		vert_vel += 1.2;
		y += vert_vel;

        if(y > 500)
		{
			vert_vel = 0.0;
			y = 500; // snap back to the ground
		}
	}
}
