package Main;

import Entity.Player;

import javax.swing.*;

public class Pseudokodas {
    private boolean controllingPlayer = false;
    private Player player;

    // Konstruktorius, priimantis Player objektą
    public Pseudokodas(Player player) {
        this.player = player;
    }

    public void enableTerminalControl() {
        controllingPlayer = true;

        while (controllingPlayer) {
            String input = JOptionPane.showInputDialog(null, "Įveskite judėjimo komandą (v-į viršų, a-į apačią, d-dešinė, k-kairė) ir kartų skaičių, pvz. 'v 5', arba 'q' išjungti:", "Judėjimo kontrolė", JOptionPane.PLAIN_MESSAGE);

            if (input == null || input.equals("q")) {
                System.out.println("Exiting terminal control...");
                controllingPlayer = false;
                Game.main(null);
                break;
            }

            // Padalina įvestį į komandos dalis
            String[] parts = input.split(" ");
            if (parts.length < 1 || parts.length > 2) {
                JOptionPane.showMessageDialog(null, "Netinkama komanda! Naudokite v/a/d/k [kartų skaičius] arba 'q'.");
                continue;
            }

            String command = parts[0]; // Judėjimo kryptis (v/a/d/k)
            int times = 1; // Numatytoji reikšmė, jei vartotojas nenurodo skaičiaus

            // Patikrina, ar vartotojas įvedė skaičių
            if (parts.length == 2) {
                try {
                    times = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Kartų skaičius turi būti sveikas skaičius!");
                    continue;
                }
            }

            // Vykdo judėjimą kelis kartus
            for (int i = 0; i < times; i++) {
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
                    Thread.sleep(20); // Nedidelė pauzė tarp judėjimų, kad nebūtų per greita
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
