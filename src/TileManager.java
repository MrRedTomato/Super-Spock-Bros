import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager
{
    private GamePanel gamePanel;
    private Tile[] tile;
    private int level[][];
    final int lvlLength = 22;

    public TileManager(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
        tile = new Tile[3];
        level = new int[gamePanel.maxScreenRow][lvlLength];
        getTileImage();
        loadLevel();
    }

    public void getTileImage()
    {
        try {
            tile[1] = new Tile();
            tile[1].setImage(ImageIO.read(getClass().getResourceAsStream("assets/platform.png")));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadLevel()
    {
        try
        {
            InputStream is = getClass().getResourceAsStream("assets/lvl1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int i = 0; i < gamePanel.maxScreenRow; i++)
            {
                String line = br.readLine();
                String[] row = line.split(" ");
                for (int j = 0; j < lvlLength; j++)
                {
                    level[i][j] = Integer.parseInt(row[j]);
                }
            }
            br.close();
        } catch (Exception e)
        {

        }
    }

    public void draw(Graphics2D g)
    {
        for (int i = 0; i < level.length; i++)
        {
            for (int j = 0; j < level[i].length; j++)
            {
                int num = level[i][j];
                if (num > 0)
                {
                    g.drawImage(tile[num].getImage(), j * gamePanel.tileSize + gamePanel.getDisplayX(), i * gamePanel.tileSize, gamePanel.tileSize, gamePanel.tileSize, null);
                }
            }
        }
    }
}
