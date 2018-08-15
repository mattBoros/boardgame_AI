package iterators;

import command_factories.BaseFactory;
import command_factories.CommandFactory;
import commands.Base;
import commands.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Matt on 5/13/2017.
 */
public class BaseIterator extends Iterator {
    private static final BaseFactory[] baseData;
    static {
        List<BaseFactory> baseList = new ArrayList<>();
        baseList.addAll(Value.ALL_VALUES);
        baseList.addAll(CommandFactory.ALL_INSTANCES);
        baseData = baseList.toArray(new BaseFactory[baseList.size()]);
    }

    public BaseIterator(int currentDepth, int maxDepth){
        super(baseData, currentDepth, maxDepth);
    }

}
