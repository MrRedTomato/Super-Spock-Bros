import java.awt.*;
import java.util.ArrayList;

public class EnemyManager {
    ArrayList<Enemy> enemies;
    GamePanel gp;

    public EnemyManager(GamePanel gp)
    {
        enemies = new ArrayList<>();
        this.gp = gp;
        setEnemies();
    }

    public void setEnemies()
    {
        if (gp.lvl == 1)
        {
            Enemy enemy1 = new Enemy();
            Enemy enemy2 = new Enemy();
            enemy1.posX = gp.tileSize * 35;
            enemy1.posY = gp.tileSize * 9;
            enemy2.posX = gp.tileSize * 2;
            enemy2.posY = gp.tileSize * 9;
            enemies.add(enemy1);
            enemies.add(enemy2);
        }
    }

    public void moveEnemies()
    {
        for (Enemy enemy : enemies)
        {
            if (!checkRight(enemy) && !checkLeft(enemy))
            {
                enemy.direction = "none";
            }
            else if (enemy.direction.equals("right") && !checkRight(enemy))
            {
                enemy.direction = "left";
            }
            else if (enemy.direction.equals("left") && !checkLeft(enemy))
            {
                enemy.direction = "right";
            }

            if (enemy.direction.equals("right"))
            {
                enemy.posX += enemy.speed;
            }
            else if (enemy.direction.equals("left"))
            {
                enemy.posX -= enemy.speed;
            }
        }
    }

    private boolean checkRight(Enemy enemy)
    {
        int col = enemy.posX / gp.tileSize;
        int row = enemy.posY / gp.tileSize;
        return gp.tileM.level[row + 1][col + 1] == 1;
    }

    private boolean checkLeft(Enemy enemy)
    {
        int col = enemy.posX / gp.tileSize;
        int row = enemy.posY / gp.tileSize;
        return gp.tileM.level[row + 1][col] == 1;
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

    public void attackPlayer()
    {
        int playerTop = gp.player.posY;
        int playerBottom = gp.player.posY + gp.tileSize;
        int playerRight = gp.player.posX + gp.tileSize - gp.worldX - gp.screenWidth / 2;
        int playerLeft = gp.player.posX - gp.worldX - gp.screenWidth / 2;

        boolean touchingPlayer = false;
        for (Enemy enemy : enemies)
        {
            if (playerTop >= enemy.posY - gp.tileSize &&
                    playerBottom <= enemy.posY + gp.tileSize && playerRight >= enemy.posX &&
                    playerLeft <= enemy.posX + gp.tileSize)
            {
                touchingPlayer = true;
            }
        }

        if (!gp.player.wasAttacked && touchingPlayer)
        {
            gp.player.health--;
            gp.player.wasAttacked = true;
        }
        else if (!touchingPlayer)
        {
            gp.player.wasAttacked = false;
        }
    }

    public void draw(Graphics2D g)
    {
        for (int i = 0; i < enemies.size(); i++)
        {
            Enemy enemy = enemies.get(i);
            g.drawImage(enemy.right, enemy.posX + gp.worldX + gp.screenWidth / 2, enemy.posY, gp.tileSize, gp.tileSize, null);
        }
    }
}
