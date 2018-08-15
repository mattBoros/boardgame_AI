package iterators;

import command_factories.BaseFactory;
import commands.Base;

/**
 * Created by Matt on 5/13/2017.
 */
public abstract class Iterator {

    public static Iterator getIterator(Iterator_Type type, int currentDepth, int maxDepth){
        if(currentDepth >= maxDepth){
            return getLonelyIterator(type, currentDepth, maxDepth);
        }
        return getNormalIterator(type, currentDepth, maxDepth);
    }

    private static Iterator getLonelyIterator(Iterator_Type type, int currentDepth, int maxDepth){
        if(type.equals(Iterator_Type.BASE)){
            return new LonelyBaseIterator(currentDepth, maxDepth);
        } else {
            return new LonelyCommandIterator(currentDepth, maxDepth);
        }
    }

    private static Iterator getNormalIterator(Iterator_Type type, int currentDepth, int maxDepth){
        if(type.equals(Iterator_Type.BASE)){
            return new BaseIterator(currentDepth, maxDepth);
        } else {
            return new CommandIterator(currentDepth, maxDepth);
        }
    }

    private Base currentBase = null;
    private final BaseFactory[] data;
    private Iterator[] subIterators;
    private BaseFactory currentFactory;
    private int i;
    private final int currentDepth;
    private final int maxDepth;

    Iterator(BaseFactory[] data, int currentDepth, int maxDepth){
        this.data = data;
        this.currentDepth = currentDepth;
        this.maxDepth = maxDepth;
        reset();
    }

    private void updateSubIterators(){
        currentFactory = data[i];
        subIterators = new Iterator[currentFactory.getTypes().length];
        for(int j = 0; j < subIterators.length; j++){
            subIterators[j] = Iterator.getIterator(currentFactory.getTypes()[j],
                                                   currentDepth + 1,
                                                   maxDepth);
        }
    }

    public Iterator reset(){
        currentBase = null;
        i = 0;
        updateSubIterators();
        return this;
    }

    public boolean hasNext(){
        return i < data.length - 1;
    }

    private boolean allIteratorsDone(){
        for(final Iterator sub : subIterators){
            if(sub.hasNext()){
                return false;
            }
        }
        return true;
    }

    public synchronized Iterator iterate(){
        currentBase = null;
        if(allIteratorsDone()){
            i++;
            updateSubIterators();
            if(!hasNext()){
                return this;
            }
        } else {
            int j = subIterators.length - 1;
            while(!subIterators[j].hasNext()){
                subIterators[j].reset();
                j--;
            }
            subIterators[j].iterate();
            return this;
        }
        return this;
    }

    public synchronized Base getCurrentBase(){
        if(currentBase != null){
            return currentBase;
        }
        final Base[] args = new Base[subIterators.length];
        for(int j = 0; j < args.length; j++){
            args[j] = subIterators[j].getCurrentBase();
        }
        currentBase = data[i].createInstanceBase(args);
        return currentBase;
    }

}
