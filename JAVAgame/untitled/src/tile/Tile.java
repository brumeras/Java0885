/**
 * Ši klasė yra skirta plytelėms, kurios sudaro žemėlapį.
 * @author Emilija Sankauskaitė, Programų sistemos VU, 5 grupė
 */

//Priklauso paketui tile, ši klasė logiškai priskirta plytelėms, kurios sudaro žemėlapį.
package tile;

//Importuojama klasė, kuri saugo plyteliį paveikslėlius.
import java.awt.image.BufferedImage;

//Apibrėžiama klasė, kuri yra atsakinga už vieną plytelę žaidime.
public class Tile
{

    //Saugo paveikslėlį.
    public BufferedImage image;

    //Kintamasis, kuris nurodo, ar plytelė kieta ar praeinama
    public boolean collision = false;
}
