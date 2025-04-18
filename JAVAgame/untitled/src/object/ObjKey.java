/**
 * Ši klasė sukurią objektą bei aprašo jį.
 * @author Emilija Sankauskaitė, Programų sistemos VU, 5 grupė
 */

package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjKey extends SuperObject
{

    public ObjKey()
    {
        name = "EdibleFlower";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/flower1.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        collision=true;
    }
}
