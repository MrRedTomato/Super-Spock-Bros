public class CollisionChecker
{
    GamePanel gp;

    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }

    public void checkTile(Entity entity)
    {
        int entityBottom = entity.getPosY() + gp.tileSize;
        int entityLeft = -gp.getWorldX();
        int entityRight = gp.tileSize - gp.getWorldX();

        int entityBottomRow = entityBottom / gp.tileSize;
        int entityLeftCol = entityLeft / gp.tileSize;
        int entityRightCol = entityRight / gp.tileSize;

        int tileNum1, tileNum2;

        if (entity.getvY() > 0)
        {
            if ((entityBottom + entity.getvY()) / gp.tileSize < gp.maxScreenRow)
                entityBottomRow = (entityBottom + entity.getvY()) / gp.tileSize;
            tileNum1 = gp.tileM.level[entityBottomRow][entityLeftCol];
            tileNum2 = gp.tileM.level[entityBottomRow][entityRightCol];
            if ((tileNum1 > 0 || tileNum2 > 0) && (entityBottom + entity.getvY()) <= (entityBottomRow * gp.tileSize + 16))
            {
                //System.out.println("Bottom: " + entityBottomRow + ", Left: " + entityLeftCol + ", Right: " + entityRightCol);
                entity.collision = true;
            }
        }
    }
}
