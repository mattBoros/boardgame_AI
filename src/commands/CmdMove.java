package commands;

import field_objects.ground.GroundArray;
import field_objects.players.Player;

/**
 * Created by Matt on 5/13/2017.
 */
public class CmdMove extends Command {

    public static final CmdMove MOVE_UP = new CmdMove(Direction.UP);
    public static final CmdMove MOVE_DOWN = new CmdMove(Direction.DOWN);
    public static final CmdMove MOVE_LEFT = new CmdMove(Direction.LEFT);
    public static final CmdMove MOVE_RIGHT = new CmdMove(Direction.RIGHT);
    public static final CmdMove MOVE_HERE = new CmdMove(Direction.HERE);

    public static CmdMove getInstance(Direction dir){
        if(dir == Direction.UP){
            return MOVE_UP;
        } else if(dir == Direction.DOWN){
            return MOVE_DOWN;
        } else if(dir == Direction.LEFT){
            return MOVE_LEFT;
        } else if(dir == Direction.RIGHT){
            return MOVE_RIGHT;
        } else if(dir == Direction.HERE){
            return MOVE_HERE;
        }
        return null;
    }

    private final Direction dir;
    
    private CmdMove(Direction direction){
        dir = direction;
    }

    @Override
    public Value run(final Player player, final GroundArray ground) {
        player.moveDirection(dir);
        return Value.NULL_VALUE;
    }

    @Override
    public String toString(int numTabs) {
        return "MOVE_" + dir;
    }

    @Override
    public boolean effectsObject() {
        return true;
    }

    @Override
    public String getType() {
        return "MOVE";
    }
}
