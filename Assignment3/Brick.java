public class Brick 
{
    int x, y, w, h;

    public Brick(int x1, int y1, int w1, int h1)
    {
        x = x1;
        y = y1;
        w = w1;
        h = h1;
    }
    Json marshal()
    {

        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        ob.add("w", w);
        ob.add("h", h);
        return ob;
    }
}
