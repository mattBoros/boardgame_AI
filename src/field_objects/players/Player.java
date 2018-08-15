package field_objects.players;

import commands.Direction;
import field_objects.FieldObject;

import java.awt.*;

/**
 * Created by Matt on 5/8/2017.
 */
public abstract class Player extends FieldObject {

    private int health = 0;
    private int x;
    private int y;

    public Player(int x, int y, Color c) {
        super(c);
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    };

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public Player moveDirection(Direction direction){
        if(direction == Direction.UP){
            y++;
        } else if(direction == Direction.DOWN){
            y--;
        } else if(direction == Direction.LEFT){
            x--;
        } else if(direction == Direction.RIGHT){
            x++;
        } else if(direction == Direction.HERE){

        }
        return this;
    }

    public int getHealth(){
        return health;
    }

    public Player changeHealth(int delta){
        health += delta;
        return this;
    }
}
