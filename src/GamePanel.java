import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

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
    public int maxWorldCol = 50;
    final int maxWorldRow = 12;

    public BufferedImage gameOverScreen;
    public BufferedImage exitImage;

    int FPS = 60;

    public int worldX;
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player player = new Player(keyHandler, this);
    TileManager tileM;
    public CollisionChecker cCheck;
    public boolean gameOver = false;
    ItemManager iManager;
    BoltManager bManager;
    EnemyManager eManager;
    public int lvl;
    public Point[] exits;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        tileM = new TileManager(this);
        worldX = 0;
        lvl = 1;
        exits = new Point[2];
        cCheck = new CollisionChecker(this);
        iManager = new ItemManager(this);
        bManager = new BoltManager(this);
        eManager = new EnemyManager(this);

        exits[0] = new Point(53, 9);
        exits[1] = new Point(53, 9);

        try
        {
            gameOverScreen = ImageIO.read(getClass().getResourceAsStream("assets/gameover.png"));
            exitImage = ImageIO.read(getClass().getResourceAsStream("assets/exit.png"));
        } catch (IOException e) {}
    }

    public void reset()
    {
        worldX = 0;
        player.health = 3;
        player.posY = 200;
        player.shotsLeft = 0;
        eManager.setEnemies();
        iManager.setItems();
        if (lvl == 2)
        {
            tileM.loadLevel("assets/lvl2.txt");
        }
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null && !gameOver)
        {
            if (keyHandler.leftPressed && worldX < 0)
            {
                worldX += player.getSpeed();
            }
            if (keyHandler.rightPressed && -worldX < (maxWorldCol - 1) * tileSize)
            {
                worldX -= player.getSpeed();
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

        g2.drawImage(exitImage, exits[lvl - 1].x * tileSize + worldX + screenWidth / 2, exits[lvl - 1].y * tileSize, tileSize, tileSize, null);
        bManager.draw(g2);
        eManager.draw(g2);
        iManager.draw(g2);
        tileM.draw(g2);
        player.draw(g2);

        if (gameOver)
        {
            g2.drawImage(gameOverScreen, 0, 0, screenWidth, screenHeight, null);
        }

        g2.dispose();
    }
}
