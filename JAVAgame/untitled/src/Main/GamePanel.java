package Main;

import Entity.Player;
import Enviroment.EnviromentManager;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements Runnable {

    private final int originalTileSize = 16;
    private final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48
    public final int maxScreenColumn = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenColumn;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldColumn = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldColumn;
    public final int worldHeight = tileSize * maxWorldRow;

    public int level = 1;
    int[][] mapTileNum;
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player;
    public SuperObject obj[] = new SuperObject[40];

    Pseudokodas ps;
    public EnviromentManager eManager;

    // Pridedame GUI komponentus
    private JTextField commandInput;
    private JButton enterButton;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(0x96DD95));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        // Sukuriame Player objektą
        player = new Player(this, keyH);

        // Sukuriame Pseudokodas objektą ir priskiriame jį Player klasei
        ps = new Pseudokodas(player);
        player.setPs(ps);
        eManager = new EnviromentManager(this);

        setupGame();
        setupGUI();
    }

    private void setupGUI() {
        // Sukuriame tekstinį lauką ir mygtuką
        commandInput = new JTextField(10);
        enterButton = new JButton("Įvesti");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Įveskite komandą (v/a/d/k):"));
        inputPanel.add(commandInput);
        inputPanel.add(enterButton);

        add(inputPanel, BorderLayout.SOUTH);

        // Nustatome mygtuko veiksmą
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processCommand(commandInput.getText());
                commandInput.setText(""); // Išvalo lauką po įvedimo
            }
        });
    }

    private void processCommand(String input) {
        if (input == null || input.isEmpty()) return;

        String[] parts = input.split(" ");
        if (parts.length < 1 || parts.length > 2) {
            JOptionPane.showMessageDialog(this, "Netinkama komanda! Naudokite v/a/d/k [kartų skaičius].");
            return;
        }

        String command = parts[0];
        int times = 1;

        if (parts.length == 2) {
            try {
                times = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Kartų skaičius turi būti sveikas skaičius!");
                return;
            }
        }

        for (int i = 0; i < times; i++)
        {
            // Pirmiausia atnaujiname `collisionOn`
            cChecker.checkTile(player); // Nebūtina grąžinti boolean, bet atnaujina `player.collisionOn`

            if (player.collisionOn) { // Jei įvyko kolizija, sustabdyti ciklą
                JOptionPane.showMessageDialog(null, "Susidūrėte su kliūtimi! Judėjimas sustabdytas.");
                break;
            }

            switch (command) {
                case "v": player.movePlayer(1); break;
                case "a": player.movePlayer(2); break;
                case "d": player.movePlayer(3); break;
                case "k": player.movePlayer(4); break;
                default:
                    JOptionPane.showMessageDialog(null, "Netinkama komanda! Naudokite v/a/d/k.");
                    return;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void setupGame() {
        aSetter.setObjects();
        //eManager.setup();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
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
    public void paintComponent(Graphics g2g) {
        super.paintComponent(g2g);
        Graphics2D g2 = (Graphics2D) g2g;

        tileM.draw(g2);

        for (SuperObject object : obj) {
            if (object != null) {
                object.draw(g2, this);
            }
        }

        eManager.draw(g2);
        player.draw(g2);
        g2.setColor(new Color(0x75A134));
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.drawString("Level: " + level, 20, 40);
        g2.dispose();
    }

    public void increaseLevel(int level) {
        tileM.loadLevel(level);
        aSetter.setObjects(); // Naudokime naują metodo pavadinimą

    }
}
