import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity
{
    private KeyHandler keyHandler;
    private GamePanel gp;
    private boolean jumped;
    private int jumpStrength;
    public boolean pEquipped;
    public BufferedImage pRight, pLeft;
    public BufferedImage heart;
    public BufferedImage crystal;
    public boolean phaserFired;
    public boolean wasAttacked;
    public int shotsLeft;
    public int health;
    public int dilithium;

    public Player(KeyHandler keyHandler, GamePanel gamePanel) {
        super(gamePanel.screenWidth / 2, 200);
        this.gp = gamePanel;
        this.keyHandler = keyHandler;
        getPlayerImage();
        direction = "right";
        jumpStrength = 12;
        jumped = false;
        pEquipped = false;
        phaserFired = false;
        shotsLeft = 3;
        wasAttacked = false;
        health = 3;
        dilithium = 0;
    }

    public void getPlayerImage()
    {
        try {
            right = ImageIO.read(getClass().getResourceAsStream("assets/pixel_spock_right.png"));
            left = ImageIO.read(getClass().getResourceAsStream("assets/pixel_spock_left.png"));
            pRight = ImageIO.read(getClass().getResourceAsStream("assets/phaser_right.png"));
            pLeft = ImageIO.read(getClass().getResourceAsStream("assets/phaser_left.png"));
            heart = ImageIO.read(getClass().getResourceAsStream("assets/heart.png"));
            crystal = ImageIO.read(getClass().getResourceAsStream("assets/dilithium.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update()
    {
        gp.iManager.checkCollision();
        gp.eManager.checkCollision();
        gp.eManager.moveEnemies();
        gp.eManager.attackPlayer();
        gp.bManager.moveBolts();

        if (health < 1)
        {
            gp.gameOver = true;
            return;
        }
        if (posX - gp.worldX - gp.screenWidth / 2 >= gp.exits[gp.lvl - 1].x * gp.tileSize - gp.scale
                && posX - gp.worldX - gp.screenWidth / 2 <= gp.exits[gp.lvl - 1].x * gp.tileSize + gp.scale
                && posY >= gp.exits[gp.lvl - 1].y * gp.tileSize - gp.scale
                && posY <= gp.exits[gp.lvl - 1].y * gp.tileSize + gp.scale)
        {
            gp.lvl++;
            gp.reset();
        }
        if (getPosY() + getvY() >= gp.screenHeight - gp.tileSize)
        {
            gp.gameOver = true;
            return;
        }
        if (collision)
        {
            setvY(0);
            jumped = false;
        }
        else
        {
            changePosY(getvY());
            if (vY < gp.terminalV)
            {
                vY += gp.gravity;
            }
            else
            {
                vY = gp.terminalV;
            }
        }
        if (keyHandler.upPressed && !jumped)
        {
            setvY(jumpStrength * -1);
            jumped = true;
        }
        if (keyHandler.rightPressed)
        {
            direction = "right";
        }
        if (keyHandler.leftPressed)
        {
            direction = "left";
        }
        if (pEquipped && keyHandler.spacePressed && shotsLeft > 0)
        {
            if (!phaserFired)
            {
                gp.bManager.genBolt();
                phaserFired = true;
                shotsLeft--;
            }
        }
        if (!keyHandler.spacePressed)
        {
            phaserFired = false;
        }
        if (shotsLeft == 0)
        {
            pEquipped = false;
        }

        collision = false;
        gp.cCheck.checkTile(this);
    }

    public void draw(Graphics2D g)
    {
        BufferedImage image = null;

        if (direction.equals("right"))
        {
            if (pEquipped)
            {
                image = pRight;
            }
            else
            {
                image = right;
            }
        }
        else
        {
            if (pEquipped)
            {
                image = pLeft;
            }
            else
            {
                image = left;
            }
        }

        g.drawImage(image, getPosX(), getPosY(), gp.tileSize, gp.tileSize, null);

        for (int i = 0; i < health; i++)
        {
            g.drawImage(heart, gp.scale + i * (gp.tileSize + gp.scale), 0, gp.tileSize, gp.tileSize, null);
        }

        g.drawImage(crystal, (gp.maxScreenCol - 1) * gp.tileSize - gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
    }
}
