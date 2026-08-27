import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Item
{
    public BufferedImage image;
    public int posX, posY;

    public void setImage(String filePath)
    {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(filePath));
        } catch (IOException e)
        {
        }
    }
}
