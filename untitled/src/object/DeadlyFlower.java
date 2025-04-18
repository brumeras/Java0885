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
