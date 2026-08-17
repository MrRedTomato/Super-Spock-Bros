import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity
{
    private KeyHandler keyHandler;
    private GamePanel gamePanel;
    private boolean jumped = false;
    private int vX, vY, jumpStrength;
    public Player(KeyHandler keyHandler, GamePanel gamePanel) {
        super(100, 10, 100, 100);
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        getPlayerImage();
        direction = "right";
        vY = 0;
        jumpStrength = 13;
    }

    public void getPlayerImage()
    {
        try {
            right = ImageIO.read(getClass().getResourceAsStream("assets/pixel_spock_right.png"));
            left = ImageIO.read(getClass().getResourceAsStream("assets/pixel_spock_left.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update()
    {
        changePosY(vY);
        vY += gamePanel.gravity;
        if (getPosY() >= gamePanel.screenHeight - gamePanel.tileSize)
        {
            vY = 0;
            setPosY(gamePanel.screenHeight - gamePanel.tileSize);
            jumped = false;
        }
        if (keyHandler.isLeftPressed())
        {
            direction = "left";
            changePosX(getSpeed() * -1);
        }
        if (keyHandler.isRightPressed())
        {
            direction = "right";
            changePosX(getSpeed());
        }
        if (keyHandler.isUpPressed() && !jumped)
        {
            direction = "jump";
            vY = jumpStrength * -1;
            jumped = true;
        }
    }

    public void draw(Graphics2D g)
    {
        BufferedImage image = null;

        if (direction == "right")
        {
            image = right;
        }
        else
        {
            image = left;
        }

        g.drawImage(image, getPosX(), getPosY(), gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
