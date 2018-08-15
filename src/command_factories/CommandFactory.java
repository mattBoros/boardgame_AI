package command_factories;

import commands.Base;
import commands.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 5/13/2017.
 */
public abstract class CommandFactory implements BaseFactory {

    public static final List<CommandFactory> ALL_INSTANCES = new ArrayList<>();
    static {
        ALL_INSTANCES.addAll(FactoryComposite.getAllInstances());
        ALL_INSTANCES.addAll(FactoryIfAndWhiles.getAllInstances());
        ALL_INSTANCES.addAll(FactoryMove.getAllInstances());
        ALL_INSTANCES.addAll(FactoryGetTile.getAllInstances());
        }

    public static final List<CommandFactory> ALL_LONELY_INSTANCES = new ArrayList<>();
    static {
        ALL_LONELY_INSTANCES.addAll(FactoryGetTile.getAllInstances());
        ALL_LONELY_INSTANCES.addAll(FactoryMove.getAllInstances());
    }

    public abstract Command createInstanceOfCommand(Base... args);

    public Base createInstanceBase(Base... args){
        return createInstanceOfCommand(args);
    }
}
