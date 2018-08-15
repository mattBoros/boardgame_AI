package commands;

import graphics.IntPoint;
import main.Util;
import field_objects.ground.Ground;
import field_objects.ground.GroundArray;
import field_objects.players.Player;

import java.awt.*;

/**
 * Created by Matt on 5/13/2017.
 */
public class CmdGetTile extends Command {

    public static final CmdGetTile GET_UP_TILE = new CmdGetTile(Direction.UP);
    public static final CmdGetTile GET_DOWN_TILE = new CmdGetTile(Direction.DOWN);
    public static final CmdGetTile GET_LEFT_TILE = new CmdGetTile(Direction.LEFT);
    public static final CmdGetTile GET_RIGHT_TILE = new CmdGetTile(Direction.RIGHT);
    public static final CmdGetTile GET_HERE_TILE = new CmdGetTile(Direction.HERE);

    public static CmdGetTile getInstance(Direction dir){
        if(dir == Direction.UP){
            return GET_UP_TILE;
        } else if(dir == Direction.DOWN){
            return GET_DOWN_TILE;
        } else if(dir == Direction.LEFT){
            return GET_LEFT_TILE;
        } else if(dir == Direction.RIGHT){
            return GET_RIGHT_TILE;
        } else if(dir == Direction.HERE){
            return GET_HERE_TILE;
        }
        return null;
    }


    private final Direction dir;

    private CmdGetTile(Direction direction){
        dir = direction;
    }

    private Value convertToVal(Ground g){
        if(g.getColor().equals(Color.GREEN)){
            return Value.GREEN_VALUE;
        } else if(g.getColor().equals(Color.RED)){
            return Value.RED_VALUE;
        } else if(g.getColor().equals(Color.LIGHT_GRAY)){
            return Value.LIGHT_GRAY_VALUE;
        }
        return Value.NULL_VALUE;
    }

    @Override
    public Value run(final Player player, final GroundArray ground) {
        IntPoint i = Util.directionToDelta(dir);
        i = i.add(IntPoint.getInstance(player.getX(), player.getY()));
        return convertToVal(ground.get(i.x, i.y));
    }

    @Override
    public String toString(int numTabs) {
        return "GET_TILE_" + dir;
    }

    @Override
    public boolean effectsObject() {
        return false;
    }

    @Override
    public String getType() {
        return "GET_TILE";
    }
}
