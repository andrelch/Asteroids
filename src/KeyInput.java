import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    public static boolean up, down, left, right, space, escape;

    private Handler handler;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        //gets ASCII number that corresponds to key
        int key = e.getKeyCode();

        //handler.object.size()???
        for(int i = 0; i < handler.object.size(); i++){
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                    up = true;
                    break;
                case KeyEvent.VK_DOWN:
                    down = true;
                    break;
                case KeyEvent.VK_LEFT:
                    left = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = true;
                    break;
                case KeyEvent.VK_SPACE:
                    space = true;
                    break;
                case KeyEvent.VK_ESCAPE:
                    escape = true;
                    break;
            }
            /*GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.Player){
                //key bindings for player movement
                if(key == KeyEvent.VK_UP) tempObject.setVelY(-1);
                if(key == KeyEvent.VK_DOWN) tempObject.setVelY(+1);
                if(key == KeyEvent.VK_LEFT) tempObject.setVelX(-1);
                if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(+1);

            }
            }*/
        }
    }

    public void keyReleased(KeyEvent e){
        //gets ASCII number that corresponds to key
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++){

            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                    up = false;
                    break;
                case KeyEvent.VK_DOWN:
                    down = false;
                    break;
                case KeyEvent.VK_LEFT:
                    left = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = false;
                    break;
                case KeyEvent.VK_SPACE:
                    space = false;
                    break;
                case KeyEvent.VK_ESCAPE:
                    escape = false;
                    break;
            }

            /*GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.Player){
                //key bindings for player movement
                if(key == KeyEvent.VK_UP) tempObject.setVelY(0);
                if(key == KeyEvent.VK_DOWN) tempObject.setVelY(0);
                if(key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
                if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
            }*/
        }
    }

}

