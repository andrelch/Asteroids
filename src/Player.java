import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

public class Player extends GameObject {

    private Setup setup;
    private Handler handler;

    private static final int SIZE = 256;
    private static double DELTA_THETA = Math.PI / 90;

    ActionListener actionListener;

    public Vector position;
    public Vector velocity;

    public double rotation = 0;

    public Player(int posX, int posY, ID id, Setup setup, Handler handler) {
        //refers to parent class constructor (GameObject)
        super(posX, posY, id, setup, handler);
        this.setup = setup;
        this.handler = handler;
        this.position = new Vector();
        this.velocity = new Vector();
    }

    /*public boolean isOverlapping(Rectangle Player) {
        return true;
    }*/

    public Rectangle getBounds() {
        return new Rectangle(posX, posY, 32, 32);
        //System.out.println(Display.health);
    }

    public void tick() {
        posX += velX;
        posY += velY;

        //this.position.add(this.velocity.posX*setup.deltaTime(),this.velocity.posY*setup.deltaTime());

        //posX = Setup.clamp(posX, 4, Setup.frameWidth - 42);
        //posY = Setup.clamp(posY, 8, Setup.frameHeight - 68);

        if (KeyInput.up) {
            posY -= 4;
            //velocity.setLength(50);

        }

        if (KeyInput.down) {
            posY += 4;
        }

        if (KeyInput.left) {
            posX -= 4;
            //rotation -= 2;

        }

        if (KeyInput.right) {
            posX += 4;
            //rotation += 2;
        }

        //collision();
    }

    /*@Override
    public int getPosX() {
        return this.posX;
        int playerPosX = this.posX;
        System.out.println(this.posX);
    }*/

/*
    public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Asteroids) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //collision code
                    Display.health -= 5;
                }
            }

            /*if(Player.posX < Asteroids.posX){
                System.out.println("omg");
            }
        }

        //Asteroids asteroids = new Asteroids(0, 0, ID.Asteroids, handler);
        //asteroids.asteroidsPosition();
        //System.out.println(asteroidsPosX);

    }
 */

    public void render(Graphics g) {

        /*Graphics2D g2d = (Graphics2D) g;

        Rectangle r = new Rectangle(posX, posY);

        AffineTransform old = g2d.getTransform();

        AffineTransform at = AffineTransform.getRotateInstance(32,32);
        //at.setToRotation(rotation, posX, posY);
        at.rotate(Math.toRadians(rotation), posX, posY);
        g2d.setTransform(AffineTransform.getRotateInstance(Math.toRadians(rotation), posX, posY));
        g2d.draw(at.createTransformedShape(r));
        g2d.setColor(Color.red);
        g2d.fillRect(posX, posY, 32, 32);*.

        /*AffineTransform tx = new AffineTransform();
        tx.rotate(0.5);
        Rectangle shape = new Rectangle(1, 1, 1, 1);
        Shape newShape = tx.createTransformedShape(shape);*/

       /* g2d.rotate(Math.toRadians(rotation));
        g.setColor(Color.green);
        g2d.draw(getBounds());*/


        //g2d.rotate(Math.toRadians(rotation), posX +16, posY +16);
        //g2d.setTransform(at);
        //g.setColor(Color.white);
        //g.fillRect(posX, posY, 32, 32);

       //g2d.setTransform(old);


//things you draw after here will not be rotated

        int[] xpoints = {posX+15, posX, posX+30};
        int[] ypoints = {posY, posY+25, posY+25};
        int nPoints = 3;

        //((Graphics2D) g).rotate(10);
        g.fillPolygon(xpoints, ypoints, nPoints);
    }
}


    /*@Override
    public void keyTyped(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == 37){
            posX += 10;
            velX += 10;
        }

        if(key == KeyEvent.VK_UP) setVelY(-1);
        if(key == KeyEvent.VK_DOWN) setVelY(+1);
        if(key == KeyEvent.VK_LEFT) setVelX(-1);
        if(key == KeyEvent.VK_RIGHT) setVelX(+1);
    }
*/
