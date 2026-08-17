import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager
{
    private GamePanel gp;
    public Tile[] tile;
    public int level[][];

    public TileManager(GamePanel gamePanel)
    {
        this.gp = gamePanel;
        tile = new Tile[3];
        level = new int[gamePanel.maxWorldRow][gamePanel.maxWorldCol];
        getTileImage();
        loadLevel("/assets/lvl1.txt");
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

    public void loadLevel(String filePath)
    {
        try
        {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int i = 0; i < gp.maxWorldRow; i++)
            {
                String line = br.readLine();
                String[] row = line.split(" ");
                for (int j = 0; j < gp.maxWorldCol; j++)
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
        // TODO: Optimize rendering efficiency
        for (int i = 0; i < level.length; i++)
        {
            for (int j = 0; j < level[i].length; j++)
            {
                int num = level[i][j];
                if (num > 0)
                {
                    g.drawImage(tile[num].getImage(), j * gp.tileSize + gp.getWorldX() + gp.screenWidth / 2, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }
}
