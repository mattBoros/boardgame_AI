package iterators;

import command_factories.BaseFactory;
import command_factories.CommandFactory;
import commands.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 5/13/2017.
 */
public class LonelyBaseIterator extends Iterator {
    private static final BaseFactory[] lonelyBaseData;
    static {
        List<BaseFactory> baseList = new ArrayList<>();
        baseList.addAll(Value.ALL_VALUES);
        baseList.addAll(CommandFactory.ALL_LONELY_INSTANCES);
        lonelyBaseData = baseList.toArray(new BaseFactory[baseList.size()]);
    }

    public LonelyBaseIterator(int currentDepth, int maxDepth){
        super(lonelyBaseData, currentDepth, maxDepth);
    }

}
