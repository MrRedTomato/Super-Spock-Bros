abstract class Character
{
    private int health, damage, posX, posY;
    public Character(int health, int damage)
    {
        this.health = health;
        this.damage = damage;
        posX = 0;
        posY = 0;
    }

    public Character(int health, int damage, int posX, int posY)
    {
        this.health = health;
        this.damage = damage;
        this.posX = posX;
        this.posY = posY;
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
}
