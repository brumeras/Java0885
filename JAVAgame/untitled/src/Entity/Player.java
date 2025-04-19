/**
 * Šis kodo fragmentas apibrėžia žaidėjo klasę, kuri yra paveldėta iš Entity.
 * Ši klasė yra naudojama veikėjo pozicijoms, animacijoms, objektų rinkimui ir sąveikai valdyti.
 * @author Emilija Sankauskaitė, Programų sistemos VU, 5 grupė
 */
package Entity;

import Main.Game;
import Main.GamePanel;
import Main.KeyHandler;
import Main.Pseudokodas;
import Enviroment.EnviromentManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    private Pseudokodas ps;
    //Sukuriamas Player objektas, kuris turi nuorodas į:
    //Žaidimo pagrindinį langą
    GamePanel gp;

    //Klaviatūros valdymo klasę
    KeyHandler keyH;

    //Parodo, kur bus veikėjas. Šiems kintamiesiems įgavus reikšmę ji neisikeis, nes jie yra final.
    public final int screenX;
    public final int screenY;

    //Surinktų gėlyčių skaičiavimo kintamasis
    public int hasFlower = 0;
    public int superFlower = 0;

    //Player konstruktorius
    public Player(GamePanel gp, KeyHandler keyH)
    {
        //Priskiriama žaidimo panelė ir klaviatūros valdymo klasė Player objektui.
        this.gp = gp;
        this.keyH=keyH;

        screenX = gp.screenWidth/2 -(gp.tileSize/2);
        screenY = gp.screenHeight/2 -(gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;

        //Nurodo numatytą susidūrimo zonos poziciją.
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();

    }
    public void setPs(Pseudokodas ps) { // Metodas ps priskyrimui
        this.ps = ps;
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

            //Patikrina, ar įvyko kolizija
            int objIndex = gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);

            //Jei kolizija neįvyko, veikėjas gali toliau eiti.
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

            //Šį metodą iškviečia 60 kartų per sekundę.
            //Kiekvieną kartą kai iškviečia, didina counter.
            spriteCounter++;
            //Valdyti animacijos keitimą galima tik tada, jei pasiekiama 12
            //Kai pasiekia 12, reikia keisti paveikslėlį.
            if(spriteCounter > 12)
            {
                //Jei šiuo metu rodomas paveikslėlis yra 1, tai keičiamas į 2.
                //Ir atvirkščiai.
                if(spriteNum==1)
                {
                    spriteNum=2;
                }
                else if(spriteNum==2)
                {
                    spriteNum=1;
                }
                //Nunulinamas, kai pakeičiama animacija.
                spriteCounter=0;
            }
        }
    }

    public void pickUpObject(int i)
    {
        if (i != 999) {
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "EdibleFlower":
                    hasFlower++;
                    gp.obj[i] = null;

                    if(hasFlower==5)
                    {
                        gp.level++;
                        gp.increaseLevel(gp.level);
                        hasFlower = 0;
                        setDefaultValues();
                    }
                    break;

                case "DeadlyFlower":
                    hasFlower = 0;
                    gp.obj[i] = null;
                    System.out.println("You hit a deadly flower!");

                    if (ps != null) {
                        new Thread(() -> ps.enableTerminalControl()).start();
                    } else {
                        System.err.println("ERROR: ps objektas nebuvo inicializuotas!");
                    }
                    break;
                case "HelperFlower":
                    superFlower++;
                    System.out.println("Palietėte Helper Flower! Grįžtame prie klaviatūros valdymo.");

                    if (ps != null) {
                        ps.controllingPlayer = false; // Išjungia pseudokodo valdymą
                    }

                    gp.obj[i] = null; // Pašalina gėlę iš žaidimo
                    if(superFlower==1)
                    {
                        gp.level++;
                        gp.increaseLevel(gp.level);
                        superFlower = 0;
                        setDefaultValues();
                    }
                    break;
                case "NightFlower":
                    System.out.println("Palietėte NightFlower! Uždedamas tamsos efektas.");
                    gp.eManager.setup(); // Įjungia Lighting efektą
                    gp.obj[i] = null; // Pašalina gėlę
                    break;
            }
        }
    }

    public void draw(Graphics2D g2g)
    {
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

        //Piešia pasirinktą paveikslėlį
        //image-paveikslėlis.
        //screenX ir screenY-pozicija, kur paveikslėlis turi būti piešiamas ekrane.
        //null-nurodo, kad naudojamas standartinis vaizdo stebėjimas.
        g2g.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
    public void movePlayer(int a) {
        collisionOn = false; // Atstatome kolizijos būseną kiekvieno žingsnio pradžioje
        gp.cChecker.checkTile(this); // Tikriname, ar veikėjas susiduria su kliūtimi

        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);

        if (!collisionOn) {
            // Jei kolizijos nėra, veikėjas juda pasirinkta kryptimi
            switch (a) {
                case 1: worldY -= (speed * 2); break; // Į viršų
                case 2: worldY += (speed * 2); break; // Žemyn
                case 3: worldX += (speed * 2); break; // Į dešinę
                case 4: worldX -= (speed * 2); break; // Į kairę
            }
        } else {
            // Jei veikėjas susiduria su kliūtimi, leidžiama pajudėti atgal
            switch (a) {
                case 1: worldY += speed; break; // Jei negali judėti į viršų, pastumiamas žemyn
                case 2: worldY -= speed; break; // Jei negali judėti žemyn, pastumiamas aukštyn
                case 3: worldX -= speed; break; // Jei negali judėti į dešinę, pastumiamas į kairę
                case 4: worldX += speed; break; // Jei negali judėti į kairę, pastumiamas į dešinę
            }

            System.out.println("Susidūrėte su kliūtimi! Bandykite kita kryptimi.");

            // Vėl tikriname koliziją po pastūmimo
            gp.cChecker.checkTile(this);
            if (!collisionOn) {
                System.out.println("Kolizija panaikinta, galima judėti toliau.");
            } else {
                collisionOn = false; // Jei vis dar užstrigęs, rankiniu būdu panaikiname koliziją
            }
        }

        gp.repaint(); // Atnaujina grafiką po judėjimo
    }

}