import java.awt.image.BufferedImage;

abstract class Entity
{
    private int health, damage, posX, posY, vY, speed;

    public BufferedImage right, left;
    public String direction;
    public boolean collision;

    public Entity()
    {
        health = 100;
        damage = 10;
        posX = 0;
        posY = 0;
        speed = 4;
        vY = 0;
        collision = false;
    }

    public Entity(int health, int damage)
    {
        this.health = health;
        this.damage = damage;
        posX = 0;
        posY = 0;
        speed = 4;
        vY = 0;
        collision = false;
    }

    public Entity(int health, int damage, int posX, int posY)
    {
        this.health = health;
        this.damage = damage;
        this.posX = posX;
        this.posY = posY;
        speed = 4;
        vY = 0;
        collision = false;
    }

    public void attack(Entity other)
    {
        other.health -= damage;
    }
    public int getHealth()
    {
        return health;
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
    public int getDamage()
    {
        return damage;
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
