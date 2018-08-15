package commands;

import field_objects.ground.GroundArray;
import field_objects.players.Player;

/**
 * Created by Matt on 5/13/2017.
 */
public abstract class Base {

    public abstract Value getValue(final Player player, final GroundArray ground);

    public boolean isValue(){
        return false;
    }

    public Value toValue(){
        return (Value) this;
    }

    public Command toCommand(){
        return (Command) this;
    }

    public abstract String toString(int numTabs);

    public abstract boolean effectsObject();

    public String toString(){
        return toString(0);
    }

    public abstract String getType();

}
