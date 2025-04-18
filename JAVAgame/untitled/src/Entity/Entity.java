/**
 * Ši klasė naudojama apibrėžti pagrindines vaikėjo savybes.
 * Pavyzdžiui, poziciją, kryptį, paveikslėlius.
 * Klasė leidžia valdyti veikėjo animaciją ir kryptį keičiant spriteNum bei direction.
 * solidArea ir collisionON leidžia nustatyti, ar veikėjas susiduria su objektu.
 * @author Emilija Sankauskaitė, Programų sistemos VU, 5 grupė
 */

//Nudoro, kad priklauso paketui, kuris atsakingas už veikėją.
package Entity;

//Importuoja java paketą, kuriame yra grafikos elementai
import java.awt.*;
//Importuoja BufferedImage klasę, kuri leidžia saugoti ir manipuliuoti paveikslėlius
import java.awt.image.BufferedImage;

//Apibrėžia pagrindinę klasę, kuri skirta veikėjams
public class Entity {

    public int worldX,worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    //Skirtas animacijai valdyti. Stebi, kada paveikslėlis turi būti pakeistas.
    public int spriteCounter = 0;
    //Nurodo, kuris paveikslėlis šiuo metu yra rodomas
    public int spriteNum = 1;

    //Objektas, skirtas apibrėžti veikėjo kietąją zoną
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    //Nurodo, ar veikėjas šiuo metu susiduria su objektu.
    public boolean collisionOn = false;

}
