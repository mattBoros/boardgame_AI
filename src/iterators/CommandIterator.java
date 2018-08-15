package iterators;

import command_factories.BaseFactory;
import command_factories.CommandFactory;
import commands.Base;
import commands.Command;
import commands.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 5/13/2017.
 */
public class CommandIterator extends Iterator {
    private static final CommandFactory[] commandData;
    static {
        List<CommandFactory> baseList = new ArrayList<>();
        baseList.addAll(CommandFactory.ALL_INSTANCES);
        commandData = baseList.toArray(new CommandFactory[baseList.size()]);
    }

    public CommandIterator(int currentDepth, int maxDepth){
        super(commandData, currentDepth, maxDepth);
    }

}
