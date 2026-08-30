import javax.imageio.ImageIO;
import java.io.IOException;

public class Enemy extends Entity
{
    public Enemy()
    {
        direction = "right";
        try
        {
            right = ImageIO.read(getClass().getResourceAsStream("/assets/klingon.png"));
        } catch (IOException e)
        {

        }

    }
}
