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

        // Populate items array
        Phaser phaser = new Phaser();
        items.add(phaser);
        items.get(0).posX = 10 * gp.tileSize;
        items.get(0).posY = 9 * gp.tileSize;
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

    public void checkCollision()
    {
        int playerRight = gp.player.getPosX() + gp.tileSize;
        int playerLeft = gp.player.getPosX();
//        int playerTop = gp.player
        for (int i = 0; i < items.size(); i++)
        {
            if (gp.player.direction == "right" && gp.player.getPosX() + gp.player.getSpeed() )
        }
    }

    public void draw(Graphics2D g)
    {
        getItemImages();
        for (int i = 0; i < items.size(); i++)
        {
            Item item = items.get(i);
            g.drawImage(item.image, item.posX + gp.getWorldX() + gp.screenWidth / 2, item.posY, gp.tileSize, gp.tileSize, null);
        }
    }
}
