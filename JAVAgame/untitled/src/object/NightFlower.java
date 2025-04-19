package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class NightFlower extends SuperObject
{

    public NightFlower()
    {
        name = "NightFlower";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/NaktineGele.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        collision=true;
    }
}
