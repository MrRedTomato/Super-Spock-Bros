import java.awt.*;
import java.util.ArrayList;

public class EnemyManager {
    ArrayList<Enemy> enemies;
    GamePanel gp;

    public EnemyManager(GamePanel gp)
    {
        enemies = new ArrayList<Enemy>();

        Enemy enemy = new Enemy();
        enemy.posX = 0;
        enemy.posY = 100;
        enemies.add(enemy);

        this.gp = gp;
    }

    public void draw(Graphics2D g)
    {
        for (int i = 0; i < enemies.size(); i++)
        {
            Enemy enemy = enemies.get(i);
            g.drawImage(enemy.right, enemy.posX + gp.getWorldX() + gp.screenWidth / 2, enemy.posY, gp.tileSize, gp.tileSize, null);
        }
    }
}
