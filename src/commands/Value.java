package commands;

import command_factories.BaseFactory;
import iterators.Iterator_Type;
import field_objects.ground.GroundArray;
import field_objects.players.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Matt on 5/13/2017.
 */
public class Value extends Base implements BaseFactory {

    public static final Value NULL_VALUE = new Value("NULL");
    public static final Value TRUE_VALUE = new Value("TRUE");
    public static final Value FALSE_VALUE = new Value("FALSE");
    public static final Value GREEN_VALUE = new Value("GREEN");
    public static final Value RED_VALUE = new Value("RED");
    public static final Value LIGHT_GRAY_VALUE = new Value("LIGHT_GRAY");

    public static final List<Value> ALL_VALUES = new ArrayList<Value>(Arrays.asList(new Value[]{
            GREEN_VALUE,
            RED_VALUE,
            LIGHT_GRAY_VALUE,
            NULL_VALUE,
    }));

    private final String value;

    private Value(String v){
        if(v == null){
            throw new NullPointerException("this shouldnt ");
        }
        value = v;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override
    public Value getValue(final Player player, final GroundArray ground) {
        return this;
    }

    @Override
    public boolean isValue(){
        return true;
    }

    @Override
    public String toString(int numTabs) {
        return value;
    }

    @Override
    public boolean effectsObject() {
        return false;
    }

    @Override
    public Iterator_Type[] getTypes() {
        return new Iterator_Type[]{};
    }

    @Override
    public Base createInstanceBase(Base... args) {
        return this;
    }

    @Override
    public String getType() {
        return value;
    }
}
