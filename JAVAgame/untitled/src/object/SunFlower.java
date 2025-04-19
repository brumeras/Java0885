package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class SunFlower extends SuperObject
{

    public SunFlower()
    {
        name = "SunFlower";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/SunFlower.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        collision=true;
    }
}
