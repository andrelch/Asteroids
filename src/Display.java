import java.awt.*;

public class Display {

    Handler handler;

    public static int health = 200;
    public static int score = 0;
    public static int level = 0;

    private int greenValue = 255;

    public void tick(){
        score ++;

        health = Setup.clamp(health, 0, 200);

        greenValue = Setup.clamp(greenValue, 0, 255);

        greenValue = health;

        //System.out.println(health);
        //collision();
    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(1065, 15, 200, 32);

        g.setColor(new Color(100, greenValue, 0));
        g.fillRect(1065, 15, health, 32);

        g.setColor(Color.white);
        g.drawString("Score: " + score,980, 30);

        g.setColor(Color.white);
        g.drawString("Level: " + level, 980, 45);
    }

    //getter and setter methods for score
    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return score;
    }

    public void setLevel(){
        this.level = level;
    }

    public int getLevel(){
        return level;
    }

    public void collision(){
        //handler = new Handler();

        /*int astPosX = handler.object.get(0).getPosX();
        System.out.println("byere: " + astPosX);*/
        //Asteroids asteroids = new Asteroids(0, 0, ID.Asteroids, null);

        /*Asteroids.asteroidsPositionX();
        int astPosX = Asteroids.asteroidsPositionX();
        System.out.println("hi my name is " + astPosX);*/

    }
}
