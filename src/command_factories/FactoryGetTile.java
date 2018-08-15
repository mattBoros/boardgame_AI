package command_factories;

import commands.*;
import iterators.Iterator_Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Matt on 5/13/2017.
 */
public class FactoryGetTile extends CommandFactory {
    public static final FactoryGetTile UP_INSTANCE = new FactoryGetTile(Direction.UP);
    public static final FactoryGetTile DOWN_INSTANCE = new FactoryGetTile(Direction.DOWN);
    public static final FactoryGetTile LEFT_INSTANCE = new FactoryGetTile(Direction.LEFT);
    public static final FactoryGetTile RIGHT_INSTANCE = new FactoryGetTile(Direction.RIGHT);
    public static final FactoryGetTile HERE_INSTANCE = new FactoryGetTile(Direction.HERE);

    public static List<CommandFactory> getAllInstances(){
        return new ArrayList<>(Arrays.asList(new FactoryGetTile[]{
                UP_INSTANCE,
                DOWN_INSTANCE,
                LEFT_INSTANCE,
                RIGHT_INSTANCE,
                HERE_INSTANCE
        }));
    }


    private final Direction dir;
    private FactoryGetTile(Direction dir) {
        this.dir = dir;
    }

    public Command createInstanceOfCommand(Base... args){
        return CmdGetTile.getInstance(dir);
    }

    private static final Iterator_Type[] TYPES = new Iterator_Type[]{};

    @Override
    public Iterator_Type[] getTypes() {
        return TYPES;
    }
}
