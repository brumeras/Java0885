/**
 * Šis GamePanel kodas sudaro pagrindinę žaidimo sistemą,
 * atsakingą už žaidimo lango, grafikų ir logikos valdymą.
 * @author Emilija Sankauskaitė, Programų sistemos VU, 5 grupė
 */

package Main;

import Entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

//GamePanel yra Jpanel tipo klasė
public class GamePanel extends JPanel implements Runnable {

    //Pradinės plytelės dydis yra 16x16 pikselių
    private final int originalTileSize = 16;

    //Padidinamas plytelės dydis, kad jos būtų matomos
    private final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48

    public final int maxScreenColumn = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenColumn;
    public final int screenHeight = tileSize * maxScreenRow;

    //Pasaulio nustatymai
    public final int maxWorldColumn = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldColumn;
    public final int worldHeight = tileSize * maxWorldRow;

    public int level = 1;
    int[][] mapTileNum;

    //Žaidimas atnaujinamas 60 kartų per sekundę
    int FPS = 60;

    //Plytelių vaizdavimui
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];

    public GamePanel()
    {
        //Nustatomas žaidimo panelės dydis
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(0x96DD95));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        //Iškviečiamas metodas, kuris sudėlioja objektus
        setupGame();
    }

    public void setupGame()
    {
        aSetter.setObject();
    }

    //Pradedama nauja žaidimo gija.
    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    //Atsakingas už žaidimo būsenos atnaujinimus
    public void run()
    {
        //Atnaujinimo intervalas
        //Skaičiuoja, kiek laiko turi praieti iki kiekvieno atnaujinimo
        double drawInterval = 1000000000 / FPS; // 0.01666 seconds

        //System.nanoTime() grąžina dabartinį laiką
        //nextDrawTime nustato, kada bus reikalingas naujas atnaujinimas
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null)
        {
            update();
            repaint();

            try {
                //Skaičiuoja likusį laiką iki kito piešimo
                double remainingTime = nextDrawTime - System.nanoTime();
                //Likęs laikas skaičiuojamas nano sekundėmis
                remainingTime = remainingTime / 1000000;

                //Jei likęs laikas mažesnis už 0, paverčia jį 0
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                //Sumažinama procesoriaus apkrova. Ilsimasi.
                Thread.sleep((long) remainingTime);

                //Perkeliama į kitą atnaujinimo momentą
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
    }

    @Override
    //JPanel metodo perrašymas
    public void paintComponent(Graphics g2g) {

        //Iškviečiama superklasės JPanel versija
        super.paintComponent(g2g);

        //Objektas konvertuojamas, nes taip bus daugiau funkcionalumo.
        Graphics2D g2 = (Graphics2D) g2g;

        //Piešia plyteles
        tileM.draw(g2);

        //Ciklas iteruoja per visus objektus, esančius masyve.
        for (SuperObject object : obj) {
            if (object != null) {
                object.draw(g2, this);
            }
        }

        player.draw(g2);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.drawString("Level: " + level, 20, 40);

        //Atlaisvinamas grafikos kontekstas
        g2.dispose();
    }
    public void increaseLevel(int level) {

        tileM.loadLevel(level);

        aSetter.setObject();
    }

} 