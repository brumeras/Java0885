/**
 * Ši klasė nupiešia objektus, jei jie neperžengia ekrano ribų.
 * @author Emilija Sankauskaitė, Programų sistemos VU, 5 grupė
 */

package object;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    //public int WorldX, WorldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public int worldY;
    public int worldX;

    public void draw(Graphics2D g2g, GamePanel gp)
    {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // Draw only if the tile is within the screen bounds
        if (worldX + gp.tileSize> gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
        {
            g2g.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
