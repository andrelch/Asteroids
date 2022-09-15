import javax.swing.*;
import java.awt.*;

//template for game objects
public abstract class GameObject extends JPanel {

    //accessible within the class and its subclasses
    protected int posX, posY;
    protected double velX, velY;
    protected ID id;

    //constructor that set variables ^^^
    public GameObject (int posX, int posY, ID id, Setup setup, Handler handler){
        this.posX = posX;
        this.posY = posY;
    }

    //methods that don't require implementation for its declaration (no body)
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    //getter and setter methods
    public void setPosX(int posX){
        this.posX = posX;
    }

    public void setPosY(int posY){
        this.posY = posY;
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public void setVelX(double velX){
        this.velX = velX;
    }

    public void setVelY(double velY){
        this.velY = velY;
    }

    public double getVelX(){
        return velX;
    }

    public double getVelY(){
        return velY;
    }

    public void setID(ID id){
        this.id = id;
    }

    public ID getID(){
        return id;
    }
}
