import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class BoltManager
{
    ArrayList<Bolt> bolts;
    GamePanel gp;
    BufferedImage left;
    BufferedImage right;

    public BoltManager(GamePanel gp)
    {
        this.gp = gp;
        bolts = new ArrayList<>();
        try
        {
            right = ImageIO.read(getClass().getResourceAsStream("assets/bolt_right.png"));
            left = ImageIO.read(getClass().getResourceAsStream("assets/bolt_left.png"));
        } catch (IOException e)
        {

        }
    }

    public void moveBolts()
    {
        for (int i = 0; i < bolts.size(); i++)
        {
            if (bolts.get(i).x >= gp.maxWorldCol * gp.tileSize || bolts.get(i).x <= gp.tileSize)
            {
                bolts.remove(i);
                i--;
            }
            else
            {
                if (bolts.get(i).direction == "right")
                {
                    bolts.get(i).x += bolts.get(i).speed;
                }
                else
                {
                    bolts.get(i).x -= bolts.get(i).speed;
                }
            }
        }
    }

    public void genBolt()
    {
        Bolt bolt = new Bolt();
        bolt.y = gp.player.getPosY() + gp.tileSize / 2;
        if (gp.player.direction == "right")
        {
            bolt.direction = "right";
            bolt.x = 2 * gp.tileSize - gp.getWorldX();
        }
        else if (gp.player.direction == "left")
        {
            bolt.direction = "left";

            bolt.x = -gp.getWorldX();
        }
        bolts.add(bolt);
    }

    public void draw(Graphics2D g)
    {
        for (int i = 0; i < bolts.size(); i++)
        {
            Bolt bolt = bolts.get(i);
            BufferedImage img;
            if (bolt.direction == "right")
            {
                img = right;
            }
            else
            {
                img = left;
            }
            g.drawImage(img, bolt.x + gp.getWorldX() + gp.screenWidth / 2 - gp.tileSize,
                    bolt.y, gp.tileSize, gp.tileSize / 2, null);
        }
    }
}
