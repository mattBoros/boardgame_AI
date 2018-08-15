package command_factories;

import commands.Base;
import commands.CmdComposite;
import commands.Command;
import iterators.Iterator_Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Matt on 5/13/2017.
 */
public class FactoryComposite extends CommandFactory {
    public static final FactoryComposite INSTANCE = new FactoryComposite();
    public static List<CommandFactory> getAllInstances(){
        return new ArrayList<>(Arrays.asList(INSTANCE));
    }

    private FactoryComposite() {
    }

    public Command createInstanceOfCommand(Base... args){
        return new CmdComposite(args[0].toCommand(), args[1].toCommand());
    }

    private static final Iterator_Type[] TYPES = new Iterator_Type[]{Iterator_Type.COMMAND,
                                                                     Iterator_Type.COMMAND};
    @Override
    public Iterator_Type[] getTypes() {
        return TYPES;
    }
}
