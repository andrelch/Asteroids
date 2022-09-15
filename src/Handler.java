import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {
//mantains and updates all of the gameobjects
    LinkedList<GameObject> object = new LinkedList <GameObject>();

    public void tick() {
        //loops through every game object
        //object.size = amount of objects in game
        for(int i = 0; i < object.size(); i++) {
            //setting tempobject to get id of what object the list is at
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
        //System.out.println("LinkedList: " + object);
    }

    public void render(Graphics g) {
        for(int i = 0; i < object.size(); i++) {
            //setting tempobject to get id of what object the list is at
            GameObject tempObject = object.get(i);
            tempObject.render(g);

        }
    }

    //add game objects to linked list
    public void addObject(GameObject object){
        this.object.add(object);
    }

    //remove game objects from linked list
    public void removeObject(GameObject object){
               this.object.remove(object);
    }

}