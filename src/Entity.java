import java.awt.image.BufferedImage;

abstract class Entity
{
    private int posX, posY, vY, speed;

    public BufferedImage right, left;
    public String direction;
    public boolean collision;

    public Entity()
    {
        posX = 0;
        posY = 0;
        speed = 4;
        vY = 0;
        collision = false;
    }

    public Entity(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;
        speed = 4;
        vY = 0;
        collision = false;
    }

    public String getDirection()
    {
        return direction;
    }
    public String getPos()
    {
        return "" + posX + ", " + posY;
    }
    public int getPosX()
    {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
    public int getSpeed()
    {
        return speed;
    }
    public void changePosX(int dX)
    {
        posX += dX;
    }
    public void changePosY(int dY)
    {
        posY += dY;
    }
    public void setPosY(int posY)
    {
        this.posY = posY;
    }
    public int getvY()
    {
        return vY;
    }
    public void setvY(int vY)
    {
        this.vY = vY;
    }
}
