import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable
{
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player player = new Player();

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null)
        {
            update();
            repaint();
            try {
                Thread.sleep(1000 / FPS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update()
    {
        if (keyHandler.isDownPressed())
        {
            player.changePosY(player.getSpeed());
        }
        else if (keyHandler.isLeftPressed())
        {
            player.changePosX(player.getSpeed() * -1);
        }
        else if (keyHandler.isRightPressed())
        {
            player.changePosX(player.getSpeed());
        }
        else if (keyHandler.isUpPressed())
        {
            player.changePosY(player.getSpeed() * -1);
        }
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);

        g2.fillRect(player.getPosX(), player.getPosY(), tileSize, tileSize);

        g2.dispose();
    }
}
