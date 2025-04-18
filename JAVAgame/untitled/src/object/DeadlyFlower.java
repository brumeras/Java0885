/**
 * Ši klasė sukurią objektą bei aprašo jį.
 * @author Emilija Sankauskaitė, Programų sistemos VU, 5 grupė
 */

package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class DeadlyFlower extends SuperObject
{

    public DeadlyFlower()
    {
        name = "DeadlyFlower";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/DeadlyFlower.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        solidArea.x=5;
        collision=true;
    }
}
