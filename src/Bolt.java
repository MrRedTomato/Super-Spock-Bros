import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bolt
{
    public int x, y, speed;
    public String direction;
    public Bolt()
    {
        speed = 15;
        x = 0;
        y = 0;
        direction = "";
    }
}
