package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class EnergyBooster extends SuperObject
{

    public EnergyBooster()
    {
        name = "EnergyBooster";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/EnergyGiver.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        solidArea.x=5;
        collision=true;
    }
}
