import java.awt.*;
import java.util.Random;

public class Asteroids extends GameObject{

    Random random = new Random();
    Handler handler = new Handler();

    public Asteroids (int posX, int posY, ID id, Setup setup, Handler handler){
        //refers to parent class constructor (GameObject)
        super(posX, posY, id, setup, handler);

        velX = random.nextInt(5) + 1;
        velY = random.nextInt(5) + 1;
    }

    public Rectangle getBounds() {
        return new Rectangle(posX, posY, 16, 16);
    }

    /*public int asteroidsPositionX(){
        int asteroidsPosX = posX;
        int asteroidsPosY = posY;

        System.out.println(posX);
        System.out.println(posY);

        return asteroidsPosX;

    }*/

    public void tick(){
        posX += velX;
        posY += velY;

        if(posX <= 0 || posX >= Setup.frameWidth - 16) velX *= -1;
        if(posY <= 0 || posY >= Setup.frameHeight - 36) velY *= -1;

        //System.out.println(posX);

    }

    public void render(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.green);
        g2d.draw(getBounds());

        g.setColor(Color.gray);
        g.fillRect(posX, posY, 16, 16);
    }

}
