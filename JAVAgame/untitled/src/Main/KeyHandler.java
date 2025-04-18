/**
 * Šis klasės kodas skirtas fiksuoti klaviatūros klavišų paspaudimus ir atleidimus.
 * Jis naudoja KeyListener sąsają, kad reaguotų į klaviatūros įvykius.
 * Klasės funkcionalumas:
 * Ši klasė fiksuoja W,A,S,D klavišų paspaudimus bei atleidimus.
 * @author Emilija Sankauskaitė, Programų sistemos VU, 5 grupė
 */
package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Klasė implementuoja KeyListener sąsają, kuri leidžia apdoroti klaviatūros įvykius.
public class KeyHandler implements KeyListener {

    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_W)
        {
            upPressed=true;
        }
        if(code==KeyEvent.VK_S)
        {
            downPressed=true;
        }
        if(code==KeyEvent.VK_A)
        {
            leftPressed=true;
        }
        if(code==KeyEvent.VK_D)
        {
            rightPressed=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_W)
        {
            upPressed=false;
        }
        if(code==KeyEvent.VK_S)
        {
            downPressed=false;
        }
        if(code==KeyEvent.VK_A)
        {
            leftPressed=false;
        }
        if(code==KeyEvent.VK_D)
        {
            rightPressed=false;
        }

    }
}
