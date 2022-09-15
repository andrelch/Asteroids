import java.awt.*;

public class Bullet extends GameObject {

    Handler handler = new Handler();

    public int bulPosX;
    public int bulPosY;

    public Bullet(int posX, int posY, ID id, Setup setup, Handler handler){
        super(posX, posY, id, setup, handler);
    }

    public void tick() {
        posY-=8;
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(posX+8, posY-10, 16,16);

    }

    public Rectangle getBounds() {
        return null;
    }


}
