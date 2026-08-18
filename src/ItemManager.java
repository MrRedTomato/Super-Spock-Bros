import java.awt.*;
import java.util.ArrayList;

public class ItemManager
{
    ArrayList<Item> items;
    GamePanel gp;

    public ItemManager(GamePanel gp)
    {
        this.gp = gp;
        items = new ArrayList<>();
        Phaser phaser = new Phaser();
        items.add(phaser);
    }

    public void getItemImages()
    {
        for (int i = 0; i < items.size(); i++)
        {
            if (items.get(i).direction.equals("right"))
            {
                items.get(i).setImage("/assets/phaser_right.png");
            }
        }
    }

    public void draw(Graphics2D g)
    {
        getItemImages();
        for (int i = 0; i < items.size(); i++)
        {
            Item item = items.get(i);
            g.drawImage(item.image, item.posX + gp.getWorldX(), item.posY, gp.tileSize, gp.tileSize, null);
        }
    }
}
