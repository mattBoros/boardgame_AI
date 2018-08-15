package iterators;

import command_factories.CommandFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 5/13/2017.
 */
public class LonelyCommandIterator extends Iterator {
    private static final CommandFactory[] lonelyCommandData;
    static {
        List<CommandFactory> baseList = new ArrayList<>();
        baseList.addAll(CommandFactory.ALL_LONELY_INSTANCES);
        lonelyCommandData = baseList.toArray(new CommandFactory[baseList.size()]);
    }

    public LonelyCommandIterator(int currentDepth, int maxDepth){
        super(lonelyCommandData, currentDepth, maxDepth);
    }

}
