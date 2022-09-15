import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Setup extends Canvas implements Runnable{

    //game state enum and initialisation
    protected STATE state;

    STATE gameState = STATE.Menu;

    private Menu menu;

    //public boolean playButtonPressed = menu.playButton();

    //Window Dimension Fields
    public static final int frameWidth = 1280, frameHeight = 720;
    private Thread thread;
    private boolean running = false;

    //instance of handler
    private Handler handler;

    //randomiser
    private Random random;

    //HUD
    private Display display;

    public BufferStrategy bufferStrategy;

    //FPS Fields
    private static long lastFPSCheck = 0;
    public static int currentFPS = 0;
    private static int totalFrames = 0;

    public int handlerAsteroidsSize = 10;
    public int handlerBulletSize = handlerAsteroidsSize +1;

    public static int clamp(int pos, int min, int max){
        if(pos >= max)
            return pos = max;
        else if(pos <= min)
            return pos = min;
        else
            return pos;
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    double deltaTime;

    public void run() {
        this.requestFocus();
        //tick fields
        /*long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        deltaTime = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;*/

        //FPS limiter fields
        long startTime;
        long URDTimeMillis;
        long waitTime;
        long totalTime = 0;

        int frameCount = 0;
        int maxFrameCount = 60;
        int targetFPS = 34;

        long targetTime = 1000 / targetFPS;
        while (running) {
            tick();
            render();

            //increase totalFrames by 1
            totalFrames++;

            //tick method
            /*long now = System.nanoTime();
            deltaTime += (now - lastTime) / ns;
            lastTime = now;
            while (deltaTime >= 1) {
                tick();
                deltaTime--;
            }*/

            //FPS Limiter
            startTime = System.nanoTime();
            URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - URDTimeMillis;

            try {
                Thread.sleep(waitTime);
            } catch (Exception e) {
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == maxFrameCount) {
                frameCount = 0;
                totalTime = 0;
            }

            //FPS counter
            if (System.nanoTime() > lastFPSCheck + 1000000000) {
                lastFPSCheck = System.nanoTime();
                currentFPS = totalFrames;
                totalFrames = 0;
                System.out.println("FPS: " + currentFPS);
            }

        }
    }

    public double deltaTime(){
        return deltaTime;
    }

    private void tick(){
        handler.tick();

        if(gameState == STATE.Game){
            display.tick();
            collision();
            spawner();
            //gameover();

            if (KeyInput.space) {
                handler.addObject(new Bullet(handler.object.get(0).getPosX(), handler.object.get(0).getPosY(), ID.Bullet, this, handler));
                handlerBulletSize += 1;
            }

            if (display.health == 0) {

                for (int i = 1; i < handler.object.size(); i++) {
                    GameObject Asteroids = handler.object.get(i);
                    handler.object.clear();
                }

                //display.health = 200;

                gameState = STATE.End;

            }
        } else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End){
            menu.tick();
        }

        /*if (KeyInput.escape == true){
            pause(100);
        }*/
    }
    public void render() {
       bufferStrategy = this.getBufferStrategy();
       if (bufferStrategy == null){
           this.createBufferStrategy(3);
           return;
       }

       Graphics g = bufferStrategy.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, frameWidth, frameHeight);

        g.setColor(Color.white);
        g.drawString("V1.0", 1240, 680);

        g.setColor(Color.white);

        g.drawString(String.valueOf(currentFPS + " FPS"), 10, 20);

       //main display (bottom)
       handler.render(g);

        if(gameState == STATE.Game){
            //secondary display (top)
            display.render(g);
        } else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End){
            menu.render(g);
        }

       g.dispose();
       bufferStrategy.show();
   }

   //what makes this method loop?
    public Setup(){
        //adds game objects with parameters (posX, posY, id)
        handler = new Handler();

        display = new Display();

        //initialises handler with menu initialisation?
        menu = new Menu(this, handler, display);

        this.addKeyListener(new KeyInput(handler));

        this.addMouseListener(menu);

        Window window = new Window(frameWidth, frameHeight, "Asteroids", this);

        random = new Random();


    }

    public void spawner(){
        // modulo operator, if 1000 has a remainder of 0, increase the level

        if(display.score % 1000 == 0) display.level++;{
        }

        if (display.score % 1000 == 0){

            int gameLevel = display.getLevel();

            System.out.println("Level " + gameLevel);

            display.health += 20;

            for(int i = 0; i < 5; i++){
                handler.addObject(new Asteroids(random.nextInt(frameWidth), random.nextInt(frameHeight), ID.Asteroids, this, handler));
            }

            handlerAsteroidsSize += 5;
        }

        /*if (KeyInput.space) {
            handler.addObject(new Bullet(handler.object.get(0).getPosX(), handler.object.get(0).getPosY(), ID.Bullet, this, handler));
        }*/
    }

    public void collision(){

        int playerPosX = handler.object.get(0).getPosX();

        int playerPosY = handler.object.get(0).getPosY();

        Rectangle playerRectangle = new Rectangle(playerPosX, playerPosY, 32, 32);

        for(int i = 1; i < handlerAsteroidsSize; i++) {
            int asteroidsPosX = handler.object.get(i).getPosX();

            int asteroidsPosY = handler.object.get(i).getPosY();

            Rectangle asteroidsRectangle = new Rectangle(asteroidsPosX, asteroidsPosY, 32, 32);

            if (playerRectangle.intersects(asteroidsRectangle)){
                System.out.println("GAME OVER");
                display.health-=2;
            }

            for(int i2 = handlerAsteroidsSize; i2 < handlerBulletSize; i2++){
                int bulletPosX = handler.object.get(i2).getPosX();

                int bulletPosY = handler.object.get(i2).getPosY();

                Rectangle bulletRectangle = new Rectangle(bulletPosX, bulletPosY, 16, 16);

                if (asteroidsRectangle.intersects(bulletRectangle)){
                    System.out.println("Hit");
                    // handler.removeObject();
                }
            }
        }

    }

    public void gameover(){
        if (display.health == 0){
            for(int i = 1; i < handler.object.size(); i++) {
                GameObject Asteroids = handler.object.get(i);
                handler.object.clear();
            }

            gameState = STATE.End;

            Graphics g = bufferStrategy.getDrawGraphics();
            //g.clearRect(0, 0, frameWidth, frameHeight);

            g.setColor(Color.black);
            g.fillRect(0, 0, frameWidth, frameHeight);

            g.setColor(Color.red);
            g.drawString("Press space to play again", frameWidth/2-75, frameHeight/2-5);
            //handler.removeObject();

            if(KeyInput.space){
                gameState = STATE.Menu;
            }

            /*for(int i = 0; i < 10; i++){
                handler.removeObject(new Asteroids(random.nextInt(frameWidth), random.nextInt(frameHeight), ID.Asteroids, handler));
            }*/

            bufferStrategy.show();

        }
    }

    private void pause(int time) throws InterruptedException {
        Thread.sleep(time);
    }

    /*
    private void pause(int time){
        try{
            Thread.sleep(time);
        } catch (InterruptedException ignored)
    }
    */

    public static void main(String args[]){
        Setup go = new Setup();
        new Thread(go).start();
    }

}