abstract class Character
{
    private int health, damage, posX, posY, speed;

    public Character()
    {
        health = 100;
        damage = 10;
        posX = 0;
        posY = 0;
        speed = 4;
    }

    public Character(int health, int damage)
    {
        this.health = health;
        this.damage = damage;
        posX = 0;
        posY = 0;
        speed = 4;
    }

    public Character(int health, int damage, int posX, int posY)
    {
        this.health = health;
        this.damage = damage;
        this.posX = posX;
        this.posY = posY;
        speed = 4;
    }

    public void attack(Character other)
    {
        other.health -= damage;
    }
    public int getHealth()
    {
        return health;
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
}
