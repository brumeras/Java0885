package Main; // Same package as GamePanel

import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        // Create a window
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Cowtastic >;)");
        frame.setResizable(false); // Prevent resizing

        // Create and add the game panel
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.pack(); // Adjusts the window size to fit the panel

        frame.setLocationRelativeTo(null); // Centers the window
        frame.setVisible(true); // Make the window visible


        gamePanel.startGameThread();

    }
}
