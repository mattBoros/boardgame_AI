package command_factories;

import commands.*;
import iterators.Iterator_Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Matt on 5/13/2017.
 */
public class FactoryIfAndWhiles extends CommandFactory {
    public static final FactoryIfAndWhiles IF_INSTANCE = new FactoryIfAndWhiles(Type.IF);
    public static final FactoryIfAndWhiles IF_NOT_INSTANCE = new FactoryIfAndWhiles(Type.IF_NOT);
    public static final FactoryIfAndWhiles WHILE_INSTANCE = new FactoryIfAndWhiles(Type.WHILE);
    public static final FactoryIfAndWhiles WHILE_NOT_INSTANCE = new FactoryIfAndWhiles(Type.WHILE_NOT);

    public static List<CommandFactory> getAllInstances(){
        return new ArrayList<>(Arrays.asList(IF_INSTANCE,
                                             IF_NOT_INSTANCE,
                                             WHILE_INSTANCE,
                                             WHILE_NOT_INSTANCE));
    }

    private enum Type {
        IF, IF_NOT,
        WHILE, WHILE_NOT
    }

    private final Type type;

    private FactoryIfAndWhiles(Type type) {
        this.type = type;
    }

    public Command createInstanceOfCommand(Base... args){
        if(type == Type.IF){
            return new CmdIf(args[0], args[1], args[2].toCommand());
        } else if(type == Type.IF_NOT){
            return new CmdIfNot(args[0], args[1], args[2].toCommand());
        } else if(type == Type.WHILE){
            return new CmdWhile(args[0], args[1], args[2].toCommand());
        } else if (type == Type.WHILE_NOT){
            return new CmdWhileNot(args[0], args[1], args[2].toCommand());
        }
        return null;
    }

    private static final Iterator_Type[] TYPES = new Iterator_Type[]{Iterator_Type.BASE,
                                                                     Iterator_Type.BASE,
                                                                     Iterator_Type.COMMAND};
    @Override
    public Iterator_Type[] getTypes() {
        return TYPES;
    }
}
