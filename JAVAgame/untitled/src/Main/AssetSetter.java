package Main;
import Main.GamePanel;
import object.*;

import java.util.Random;

public class AssetSetter {
    GamePanel gp;
    Random rand = new Random();

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        int index = 0; // Objektų masyvo indeksas

        index = generateObjects(index, 10, "ObjKey");        // Generuoja 5 raktus
        index = generateObjects(index, 3, "DeadlyFlower");  // Generuoja 3 pavojingas gėlytes
        index = generateObjects(index, 2, "HelperFlower");
        index = generateObjects(index, 4, "NightFlower");
        index = generateObjects(index, 4, "SunFlower");
    }

    private int generateObjects(int startIndex, int numObjects, String type) {
        for (int i = 0; i < numObjects; i++) {
            boolean validPosition = false;
            int x = 0, y = 0;

            while (!validPosition) {
                x = rand.nextInt(50) * gp.tileSize;
                y = rand.nextInt(50) * gp.tileSize;

                int col = x / gp.tileSize;
                int row = y / gp.tileSize;
                int tileNum = gp.tileM.mapTileNum[col][row];

                if (!gp.tileM.tile[tileNum].collision) {
                    validPosition = true;
                }
            }

            switch (type) {
                case "ObjKey": gp.obj[startIndex] = new ObjKey(); break;
                case "DeadlyFlower": gp.obj[startIndex] = new DeadlyFlower(); break;
                case "HelperFlower": gp.obj[startIndex] = new Gelbetoja(); break;
                case "NightFlower": gp.obj[startIndex] = new NightFlower(); break;
                case "SunFlower": gp.obj[startIndex] = new SunFlower(); break;
            }

            gp.obj[startIndex].worldX = x;
            gp.obj[startIndex].worldY = y;

            startIndex++; // Padidina indeksą, kad objektai neperrašytų vienas kito
        }
        return startIndex; // Grąžina atnaujintą indeksą kitam objektų tipui
    }
}
