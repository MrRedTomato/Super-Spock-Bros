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
    public Player(KeyHandler keyHandler, GamePanel gamePanel) {
        super(gamePanel.screenWidth / 2, 200);
        this.gp = gamePanel;
        this.keyHandler = keyHandler;
        getPlayerImage();
        direction = "right";
        jumpStrength = 12;
        jumped = false;
        pEquipped = false;
    }

    public void getPlayerImage()
    {
        try {
            right = ImageIO.read(getClass().getResourceAsStream("assets/pixel_spock_right.png"));
            left = ImageIO.read(getClass().getResourceAsStream("assets/pixel_spock_left.png"));
            pRight = ImageIO.read(getClass().getResourceAsStream("assets/phaser_right.png"));
            pLeft = ImageIO.read(getClass().getResourceAsStream("assets/phaser_left.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update()
    {
        gp.iManager.checkCollision();
        if (getPosY() + getvY() >= gp.screenHeight - gp.tileSize || collision)
        {
            setvY(0);
            jumped = false;
        }
        else
        {
            changePosY(getvY());
            if (getvY() < gp.terminalV)
            {
                setvY(getvY() + gp.gravity);
            }
            else
            {
                setvY(gp.terminalV);
            }
        }
        if (keyHandler.isUpPressed() && !jumped)
        {
            direction = "jump";
            setvY(jumpStrength * -1);
            jumped = true;
        }
        if (keyHandler.isRightPressed())
        {
            direction = "right";
        }
        if (keyHandler.isLeftPressed())
        {
            direction = "left";
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
    }
}
