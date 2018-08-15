package command_factories;

import commands.*;
import iterators.Iterator_Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Matt on 5/13/2017.
 */
public class FactoryMove extends CommandFactory {
    public static final FactoryMove UP_INSTANCE = new FactoryMove(Direction.UP);
    public static final FactoryMove DOWN_INSTANCE = new FactoryMove(Direction.DOWN);
    public static final FactoryMove LEFT_INSTANCE = new FactoryMove(Direction.LEFT);
    public static final FactoryMove RIGHT_INSTANCE = new FactoryMove(Direction.RIGHT);
    public static final FactoryMove HERE_INSTANCE = new FactoryMove(Direction.HERE);

    public static List<CommandFactory> getAllInstances(){
        return new ArrayList<>(Arrays.asList(new CommandFactory[]{
                UP_INSTANCE,
                DOWN_INSTANCE,
                LEFT_INSTANCE,
                RIGHT_INSTANCE,
                HERE_INSTANCE
        }));
    }

    private final Direction dir;
    private FactoryMove(Direction dir) {
        this.dir = dir;
    }

    public Command createInstanceOfCommand(Base... args){
        return CmdMove.getInstance(dir);
    }

    private static final Iterator_Type[] TYPES = new Iterator_Type[]{};
    @Override
    public Iterator_Type[] getTypes() {
        return TYPES;
    }
}
