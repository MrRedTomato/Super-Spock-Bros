import java.awt.*;
import java.util.ArrayList;

public class EnemyManager {
    ArrayList<Enemy> enemies;
    GamePanel gp;

    public EnemyManager(GamePanel gp)
    {
        enemies = new ArrayList<>();

        Enemy enemy = new Enemy();
        enemy.posX = gp.tileSize * 32;
        enemy.posY = gp.tileSize * 9;
        enemies.add(enemy);

        this.gp = gp;
    }

    public void checkCollision()
    {
        for (int i = 0; i < enemies.size(); i++)
        {
            Enemy enemy = enemies.get(i);
            for (int j = 0; j < gp.bManager.bolts.size(); j++)
            {
                Bolt bolt = gp.bManager.bolts.get(j);
                int boltRight = bolt.x + gp.tileSize;
                int boltLeft = bolt.x;
                int enemyRight = enemy.posX + gp.tileSize;
                int enemyLeft = enemy.posX;

                if (bolt.y >= enemy.posY - gp.tileSize / 2 && bolt.y <= enemy.posY + gp.tileSize)
                {
                    if (bolt.direction.equals("right"))
                    {
                        if (boltRight <= enemyRight && boltRight + bolt.speed >= enemyRight)
                        {
                            enemies.remove(i);
                            gp.bManager.bolts.remove(j);
                            i--;
                            j--;
                        }
                    }
                    else
                    {
                        // TODO: fix collision timing here
                        if (boltLeft >= enemyRight && boltLeft - bolt.speed <= enemyRight)
                        {
                            enemies.remove(i);
                            gp.bManager.bolts.remove(j);
                            i--;
                            j--;
                        }
                    }
                }
            }
        }
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
