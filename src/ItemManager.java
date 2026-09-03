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
        setItems();
    }

    public void setItems()
    {
        if (gp.lvl == 1)
        {
            Phaser phaser = new Phaser();
            phaser.posX = 11 * gp.tileSize;
            phaser.posY = 9 * gp.tileSize;
            items.add(phaser);
        }
    }

    public void checkCollision()
    {
        Point tLeft = new Point(-gp.worldX + gp.tileSize, gp.player.getPosY());
        Point bLeft = new Point(-gp.worldX + gp.tileSize, gp.player.getPosY() + gp.tileSize);
        Point tRight = new Point(gp.tileSize - gp.worldX + gp.tileSize, gp.player.getPosY());
        Point bRight = new Point(gp.tileSize - gp.worldX + gp.tileSize, gp.player.getPosY() + gp.tileSize);
        for (int i = 0; i < items.size(); i++)
        {
            int left = items.get(i).posX;
            int right = items.get(i).posX + gp.tileSize;
            int top = items.get(i).posY;
            int bottom = items.get(i).posY + gp.tileSize;
            if (left <= tLeft.x && right >= tLeft.x && top <= tLeft.y && bottom >= tLeft.y)
            {
                if (items.get(i) instanceof Phaser)
                {
                    gp.player.pEquipped = true;
                    gp.player.shotsLeft = 3;
                }
                items.remove(i);
                i--;
            }
            else if (left <= bLeft.x && right >= bLeft.x && top <= bLeft.y && bottom >= bLeft.y)
            {
                if (items.get(i) instanceof Phaser)
                {
                    gp.player.pEquipped = true;
                    gp.player.shotsLeft = 3;
                }
                items.remove(i);
                i--;
            }
            else if (left <= tRight.x && right >= tRight.x && top <= tRight.y && bottom >= tRight.y)
            {
                if (items.get(i) instanceof Phaser)
                {
                    gp.player.pEquipped = true;
                    gp.player.shotsLeft = 3;
                }
                items.remove(i);
                i--;
            }
            else if (left <= bRight.x && right >= bRight.x && top <= bRight.y && bottom >= bRight.y)
            {
                if (items.get(i) instanceof Phaser)
                {
                    gp.player.pEquipped = true;
                    gp.player.shotsLeft = 3;
                }
                items.remove(i);
                i--;
            }
        }
    }

    public void draw(Graphics2D g)
    {
        for (int i = 0; i < items.size(); i++)
        {
            Item item = items.get(i);
            g.drawImage(item.image, item.posX + gp.worldX + gp.screenWidth / 2 - gp.tileSize, item.posY, gp.tileSize, gp.tileSize, null);
        }
    }
}
