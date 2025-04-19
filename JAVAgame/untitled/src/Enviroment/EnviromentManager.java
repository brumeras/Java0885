package Enviroment;

import Entity.Player;
import Main.GamePanel;

import java.awt.*;

public class EnviromentManager {
    GamePanel gp; // Naudojame GamePanel tiesiogiai
    public Lighting lighting;

    public EnviromentManager(GamePanel gp) { // Perdavimo būdas pakeistas į GamePanel
        this.gp = gp;
    }

    public void setup() {
        lighting = new Lighting(gp, 350); // Naudojame gp be klaidos
    }

    public void draw(Graphics2D g2d) {
        if (lighting != null) {
            lighting.draw(g2d);
        }
    }
}
