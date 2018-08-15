package command_factories;

/**
 * Created by Matt on 5/13/2017.
 */

import commands.Base;
import commands.Command;
import iterators.Iterator;
import iterators.Iterator_Type;

public interface BaseFactory {

    public Iterator_Type[] getTypes();

    public Base createInstanceBase(final Base... args);

}
