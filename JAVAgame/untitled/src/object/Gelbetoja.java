package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Gelbetoja extends SuperObject
{

    public Gelbetoja()
    {
        name = "HelperFlower";

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Gelbetoja.png"));
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        solidArea.x=5;
        collision=true;
    }
}
