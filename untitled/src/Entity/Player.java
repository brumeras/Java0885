package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    //kur bus veikejas
    //nesikeis per visa zaidimas nes yra final kintamieji
    public final int screenX;
    public final int screenY;

    public int hasFlower = 0;

    public Player(GamePanel gp, KeyHandler keyH)
    {
        this.gp=gp;
        this.keyH=keyH;

        screenX = gp.screenWidth/2 -(gp.tileSize/2);
        screenY = gp.screenHeight/2 -(gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();

    }
    public void setDefaultValues()
    {
        //veikejo pozicija zaidime

        //pradzios pozicija
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed=4;
        direction="up";

    }
    public void getPlayerImage()
    {
        try
        {
            up1 = ImageIO.read(getClass().getResource("/player/back1.png"));
            up2 = ImageIO.read(getClass().getResource("/player/back2.png"));

            down1 = ImageIO.read(getClass().getResource("/player/front1.png"));
            down2 = ImageIO.read(getClass().getResource("/player/front2.png"));

            left1 = ImageIO.read(getClass().getResource("/player/left1.png"));
            left2 = ImageIO.read(getClass().getResource("/player/left2.png"));

            right1 = ImageIO.read(getClass().getResource("/player/right1.png"));
            right2 = ImageIO.read(getClass().getResource("/player/right2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update()
    {
        if(keyH.upPressed==true || keyH.downPressed==true || keyH.leftPressed==true || keyH.rightPressed==true)
        {
            if(keyH.upPressed == true)
            {
                direction="up";
            }
            else if(keyH.downPressed == true)
            {
                direction="down";
            }
            else if(keyH.leftPressed == true)
            {
                direction="left";
            }
            else if(keyH.rightPressed == true)
            {
                direction="right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);
            //Check object collision
            int objIndex = gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);

            //if collision is false, player can move

            if(collisionOn == false)
            {
                switch(direction)
                {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            //si metoda iskviecia 60 kartu per sekunde
            //kiekviena karta kai iskviecia, padidina counteri
            spriteCounter++;
            if(spriteCounter > 12)
            {
                if(spriteNum == 1)
                {
                    spriteNum = 2;
                }
                else if(spriteNum == 2)
                {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;

            switch (objectName)
            {
                case "EdibleFlower":

                    hasFlower++;
                    gp.obj[i] = null;
                    //System.out.println("Edible flowers: " + hasFlower);

                    if (hasFlower > 5)
                    {
                        gp.level++;
                        //gp.loadLevel(gp.level);
                        hasFlower = 0; // Reset the flower counter
                    }
                    break;

                case "DeadlyFlower":
                    hasFlower = 0;
                    gp.obj[i] = null;
                    System.out.println("You hit a deadly flower!");
                    Main.Game.main(null);
                    break;
            }
        }
    }

    public void draw(Graphics2D g2g)
    {
        //g2g.setColor(Color.white);
        //g2g.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch(direction)
        {
            case "up":
                if(spriteNum==1)
                {
                    image = up1;
                }
                if(spriteNum==2)
                {
                    image = up2;
                }
                break;
                case "down":
                    if(spriteNum==1)
                    {
                        image = down1;
                    }
                    if(spriteNum==2)
                    {
                        image = down2;
                    }
                    break;

                    case "left":
                        if(spriteNum==1)
                        {
                            image = left1;
                        }
                        if(spriteNum==2)
                        {
                            image = left2;
                        }
                        break;
                        case "right":
                            if(spriteNum==1)
                            {
                                image = right1;
                            }
                            if(spriteNum==2)
                            {
                                image = right2;
                            }
                            break;
        }
        g2g.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}
