package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class EnergyTaker extends SuperObject
{

    public EnergyTaker()
    {
        name = "EnergyTaker";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/EnergyTaker.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        solidArea.x=5;
        collision=true;
    }
}
