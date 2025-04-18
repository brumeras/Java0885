package Main;
/**
 * Ši klasė sukuria žaidimo langą, nustato lango parametrus.
 * Be to, paleidžia patį žaidimą.
 * "package Main" parodo, kad yra susieta su GamePanel.
 * "Main" parodo, kad priklauso šiam paketui.
 * @author Emilija Sankauskaitė, Programų sistemos VU, 5 grupė
 */

//Importuojama "Swing" biblioteka, kuri yra reikalinga kurti grafines vartotojo sąsajas

import javax.swing.*;

//Apibrėžiama klasė Game, kuri yra pagrindinė žaidimo klasė.
public class Game {

    //Tai yra pagrindinis programos metodas, kuris yra vykdomas paleidžiant programą.
    public static void main(String[] args) {

        //Sukuriamas naujas JFrame objektas
        JFrame frame = new JFrame();

        //Programa bus uždaryta, kai uždaromas langas.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Nustato lango pavadinimą.
        frame.setTitle("Cowtastic >;)");

        //Ši eilutė reikalinga, kad lango dydis nekistų, kad nekistų grafika.
        frame.setResizable(false);

        //Sukuriamas objektas GamePanel, kuris atsakingas už žaidimo panelę.
        GamePanel gamePanel = new GamePanel();

        //Į langą JFrame pridedamas žaidimo panelės komponentas
        frame.add(gamePanel);

        //Automatiškai pritaiko lango dydį pagal jo viduje esantį komponentą.
        frame.pack();

        //Lango centravimas
        frame.setLocationRelativeTo(null);

        //Lango matomumas
        frame.setVisible(true);

        //Paleidžia Thread, kuris atsakingas už žaidimo atnaujinimą ir grafiką.
        gamePanel.startGameThread();

    }
}