/**
 * Ši klasė yra atsakinga už:
 * 1. Plytelių apibrėžimą. (getTileImage()).
 * 2. Žemėlapio duomenų įkėlimą (loadMap()).
 * 3. Žemėlapio pakeitimą, kai keičiasi lygis (loadLevel()).
 * 4. Plytelių piešimą pagal žaidėjo poziciją (draw()).
 *
 * @author Emilija Sankauskaitė, Programų sistemos VU, 5 grupė
 */

//Nurodo, kad priklauso tile paketui, kur yra viskas, kas yra susiję su žemėlapio sistema.
package tile;

//Importuoja klasę iš Main paketo
import Main.GamePanel;

//Klasė, kuri reikalinga plytelių atvaizdams.
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//Apibrėžiama klasė, kuri atsakinga už plytelių įkėlimą.
public class TileManager
{

    //Nuoroda į pagrindinę GamePanel klasę, kad būtų galima pasiekti kitas reikšmes.
    GamePanel gp;

    //Masyvas, kuriame saugomi plytelių objektai.
    public Tile[] tile;

    //Dvimatis masyvas, kuris pagal koordinates išdėsto žemėlapį.
    public int[][] mapTileNum;

    public TileManager(GamePanel gp)
    {
        //Nurodo, kuris objektas naudojamas
        this.gp = gp;

        //Saugo 10 skirtingų plytelių
        tile = new Tile[10];

        //Sukuriamas žemėlapio dydžio masyvas
        mapTileNum = new int[gp.maxWorldColumn][gp.maxWorldRow];

        loadMap("/maps/word01.txt");

        //Įkelia plytelių paveikslėlius
        getTileImage();
    }

    public void getTileImage() {
        try {
            //Sukuriama nauja plytelė.
            tile[0] = new Tile();
            //Įkeliamas paveiksliukas.
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2].collision = true;
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
        } catch (IOException e)
        {
            //Parodo klaidą, jei nepavyksta įkelti.
            e.printStackTrace();
        }
    }

    public void loadLevel(int level)
    {
        switch (level) {
            case 2:
                loadMap("/maps/word02.txt");
                break;
            case 3:
                loadMap("/maps/word03.txt");
                break;
            case 4:
                loadMap("/maps/word04.txt");
                break;
            case 5:
                loadMap("/maps/word05.txt");
                break;
                case 6:
                    loadMap("/maps/word06.txt");
                    break;
                    case 7:
                        loadMap("/maps/word01.txt");
                        break;
            default:
                System.out.println("No more levels!");
                return;
        }
    }

    public void loadMap(String filepath)
    {
        try {
            //Atidaro failą
            InputStream is = getClass().getResourceAsStream(filepath);
            //Perskaito duomenis
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col=0;
            int row=0;

            while(col < gp.maxWorldColumn && row < gp.maxWorldRow)
            {
                //Nuskaito eilutę iš failo.
                String line = br.readLine();

                while(col < gp.maxWorldColumn && row < gp.maxWorldRow) {

                    //Dalina eilutę į atskirus skaičius
                    String numbers[]= line.split(" ");

                    //Konvertuoja iš string į int
                    int num = Integer.parseInt(numbers[col]);

                    //Įrašomas eilutės numeris į masyvą.
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldColumn) {
                    col=0;
                    row++;
                }
            }
            //Failo uždarymas
            br.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2g) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldColumn && worldRow < gp.maxWorldRow)
        {

            //Gauna naują plytelę
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

            //Toks užrašymas padeda kamerai sekti žaidėją.
            //Tai rodomas plotas, kur jis tik yra
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //Piešia plyteles
            //If tikrina, ar plytelės nėra už ekrano ribų.
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                    && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                    && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                    && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2g.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            //Kai baigiasi stulpeliai pereinama prie naujos eilutės.
            if (worldCol == gp.maxWorldColumn) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
