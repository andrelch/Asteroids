import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Setup setup;
    private Handler handler;
    private Random random = new Random();

    Display display;

    BufferStrategy bufferStrategy;

    STATE gameState = STATE.Menu;

    private Menu menu;

    boolean playButton = false;

    public Menu(Setup setup, Handler handler, Display display){
        this.setup = setup;
        this.handler = handler;
        this.display = display;
    }

    public Rectangle getBounds() {
        return new Rectangle(Setup.frameWidth/2-250, 300, 500, 70);
    }

    private boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height){

        if(mouseX > x && mouseX < x + width){
            if(mouseY > y && mouseY < y + height){
                return true;
            }else return false;
        }else return false;
    }

    public void mousePressed(MouseEvent e){
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (setup.gameState == STATE.Menu) {

            display.health = 200;
            display.score = 0;

            //play button
            if (mouseOver(mouseX, mouseY, Setup.frameWidth / 2 - 250, 300, 500, 70)) {
                setup.gameState = STATE.Game;

                playButton = true;
                handler.addObject(new Player(Setup.frameWidth / 2, Setup.frameHeight - 80, ID.Player, setup, handler));
                //int playerPosX = posX;

                //creates 50 objects and cycles through them
                //generates a random starting point within width and height
                for (int i = 0; i < 10; i++) {
                    handler.addObject(new Asteroids(random.nextInt(Setup.frameWidth), random.nextInt(Setup.frameHeight), ID.Asteroids, setup, handler));

                }
            }
            if (mouseOver(mouseX, mouseY, Setup.frameWidth / 2 - 250, 400, 500, 70)) {
                setup.gameState = STATE.Help;
            }
            if (mouseOver(mouseX, mouseY, Setup.frameWidth / 2 - 250, 500, 500, 70)) {
                System.exit(0);
            }
        }

        if (setup.gameState == STATE.Help) {
            if (mouseOver(mouseX, mouseY, Setup.frameWidth / 2 - 250, 500, 500, 70)) {
                setup.gameState = STATE.Menu;
            }
        }

        if (setup.gameState == STATE.End) {
            if (mouseOver(mouseX, mouseY, Setup.frameWidth / 2 - 250, 500, 500, 70)) {
                setup.gameState = STATE.Menu;

            }
        }
    }


    public void mouseReleased(MouseEvent e){

    }

    //checking whether the mouse pointer is over an object

    public void tick() {

    }

    public void render(Graphics g){
        if(setup.gameState == STATE.Menu) {

            g.setFont(new Font("arial", Font.PLAIN, 120));
            g.setColor(Color.red);
            g.drawString("ASTEROIDS", Setup.frameWidth / 2-350, 200);

            g.setColor(Color.white);
            g.drawRect(Setup.frameWidth / 2 - 250, 300, 500, 70);

            g.setFont(new Font("arial", Font.PLAIN, 40));
            g.setColor(Color.white);
            g.drawString("Play", Setup.frameWidth / 2 - 35, 350);

            g.setColor(Color.white);
            g.drawRect(Setup.frameWidth / 2 - 250, 400, 500, 70);

            g.setFont(new Font("arial", Font.PLAIN, 40));
            g.setColor(Color.white);
            g.drawString("Help", Setup.frameWidth / 2 - 35, 450);

            g.setColor(Color.white);
            g.drawRect(Setup.frameWidth / 2 - 250, 500, 500, 70);

            g.setFont(new Font("arial", Font.PLAIN, 40));
            g.setColor(Color.white);
            g.drawString("Quit", Setup.frameWidth / 2 - 35, 550);

        }else if (setup.gameState == STATE.Help){
            g.setColor(Color.white);
            g.drawRect(Setup.frameWidth / 2 - 250, 500, 500, 70);

            g.setFont(new Font("arial", Font.PLAIN, 30));
            g.setColor(Color.white);
            g.drawString("Use arrow keys to move the spaceship and dodge the asteroids", Setup.frameWidth / 2 - 430, Setup.frameHeight / 2 - 100);

            g.setColor(Color.white);
            g.fillRect(Setup.frameWidth / 2 - 250, 500, 500, 70);

            g.setFont(new Font("arial", Font.PLAIN, 30));
            g.setColor(Color.black);
            g.drawString("Main Menu", Setup.frameWidth / 2 - 70, 545);

        }else if (setup.gameState == STATE.End){
            g.setColor(Color.white);
            g.fillRect(Setup.frameWidth / 2 - 250, 500, 500, 70);

            g.setColor(Color.white);
            g.drawRect(Setup.frameWidth / 2 - 250, 500, 500, 70);

            g.setFont(new Font("arial", Font.PLAIN, 120));
            g.setColor(Color.red);
            g.drawString("GAME OVER", Setup.frameWidth / 2 - 370, Setup.frameHeight / 2 - 100);

            g.setFont(new Font("arial", Font.PLAIN, 50));
            g.setColor(Color.white);
            g.drawString("Score: " + display.getScore(), Setup.frameWidth / 2 - 130, Setup.frameHeight / 2 - 25);

            g.setFont(new Font("arial", Font.PLAIN, 30));
            g.setColor(Color.black);
            g.drawString("Main Menu", Setup.frameWidth / 2 - 70, 545);

        }
    }

}
