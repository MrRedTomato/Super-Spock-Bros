import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable
{
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    final int gravity = 1;
    final int terminalV = 40;

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // Screen settings
    final int maxWorldCol = 50;
    final int maxWorldRow = 12;
    final int worldWidth = maxWorldCol * tileSize;
    final int worldHeight = maxWorldRow * tileSize;

    int FPS = 60;

    private int worldX;
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player player = new Player(keyHandler, this);
    TileManager tileM;
    public CollisionChecker cCheck;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        tileM = new TileManager(this);
        worldX = 0;
        cCheck = new CollisionChecker(this);
    }

    public int getTileSize()
    {
        return tileSize;
    }

    public int getWorldX()
    {
        return worldX;
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
            if (keyHandler.isLeftPressed())
            {
                if (worldX < 0)
                {
                    worldX += player.getSpeed();
                }
            }
            if (keyHandler.isRightPressed())
            {
                if (-worldX < maxWorldCol * tileSize)
                {
                    worldX -= player.getSpeed();
                }
            }
            player.update();
            repaint();
            try {
                Thread.sleep(1000 / FPS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}
