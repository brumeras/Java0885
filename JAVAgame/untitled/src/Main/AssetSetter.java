/**
 * Ši klasė sukuria objektus, kuriuos veikėjas rinks.
 * @author Emilija Sankauskaitė, Programų sistemos VU, 5 grupė
 */

package Main;

import object.DeadlyFlower;
import object.ObjKey;

public class AssetSetter
{
    GamePanel gp;
    public AssetSetter(GamePanel gp)
    {
        this.gp=gp;
    }
    public void setObject()
    {
        gp.obj[0]= new ObjKey();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1]= new ObjKey();
        gp.obj[1].worldX = 10 * gp.tileSize;
        gp.obj[1].worldY = 9 * gp.tileSize;

        gp.obj[2]= new ObjKey();
        gp.obj[2].worldX = 15 * gp.tileSize;
        gp.obj[2].worldY = 22 * gp.tileSize;

        gp.obj[3]= new ObjKey();
        gp.obj[3].worldX = 23 * gp.tileSize;
        gp.obj[3].worldY = 16 * gp.tileSize;

        gp.obj[4]= new ObjKey();
        gp.obj[4].worldX = 11 * gp.tileSize;
        gp.obj[4].worldY = 22 * gp.tileSize;

        gp.obj[5]= new ObjKey();
        gp.obj[5].worldX = 11 * gp.tileSize;
        gp.obj[5].worldY = 36 * gp.tileSize;

        gp.obj[6]= new ObjKey();
        gp.obj[6].worldX = 11 * gp.tileSize;
        gp.obj[6].worldY = 22 * gp.tileSize;

        gp.obj[7]= new DeadlyFlower();
        gp.obj[7].worldX = 45 * gp.tileSize;
        gp.obj[7].worldY = 10* gp.tileSize;

        gp.obj[8]= new DeadlyFlower();
        gp.obj[8].worldX = 10 * gp.tileSize;
        gp.obj[8].worldY = 46* gp.tileSize;

        gp.obj[7]= new DeadlyFlower();
        gp.obj[7].worldX = 45 * gp.tileSize;
        gp.obj[7].worldY =  3* gp.tileSize;

        gp.obj[7]= new DeadlyFlower();
        gp.obj[7].worldX = 11 * gp.tileSize;
        gp.obj[7].worldY = 13 * gp.tileSize;
    }
}
