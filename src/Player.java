import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity
{
    private KeyHandler keyHandler;
    private GamePanel gamePanel;
    private boolean jumped;
    private boolean touchingPlatform;
    private int vY, jumpStrength;
    public Player(KeyHandler keyHandler, GamePanel gamePanel) {
        super(100, 10, gamePanel.screenWidth / 2, 200);
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        getPlayerImage();
        direction = "right";
        vY = 0;
        jumpStrength = 13;
        jumped = false;
    }

    public int getVY()
    {
        return vY;
    }

    public void setTouchingPlatform(boolean touchingPlatform)
    {
        this.touchingPlatform = touchingPlatform;
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
        if (vY < gamePanel.terminalV)
        {
            vY += gamePanel.gravity;
        }
        else
        {
            vY = gamePanel.terminalV;
        }
        if (getPosY() >= gamePanel.screenHeight - gamePanel.tileSize)
        {
            vY = 0;
            setPosY(gamePanel.screenHeight - gamePanel.tileSize);
            jumped = false;
        }
        if (keyHandler.isUpPressed() && !jumped)
        {
            direction = "jump";
            vY = jumpStrength * -1;
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
    }

    public void draw(Graphics2D g)
    {
        BufferedImage image = null;

        if (direction.equals("right"))
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
