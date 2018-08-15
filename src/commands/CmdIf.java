package commands;

import main.Util;
import field_objects.ground.GroundArray;
import field_objects.players.Player;

/**
 * Created by Matt on 5/13/2017.
 */
public class CmdIf extends Command {

    private final Base conditionCommand;
    private final Base conditionToEqual;
    private final Command commandToRun;

    public CmdIf(Base cmd, Base equal, Command toRun){
        conditionCommand = cmd;
        conditionToEqual = equal;
        commandToRun = toRun;
    }

    @Override
    public Value run(final Player player, final GroundArray ground) {
        final Value conditionResult = conditionCommand.getValue(player, ground);
        final Value toEqualResult = conditionToEqual.getValue(player, ground);
        if(commandToRun.effectsObject() && conditionResult.equals(toEqualResult)){
            commandToRun.run(player, ground);
        }
        return Value.NULL_VALUE;
    }

    @Override
    public String toString(int numTabs) {
        return "(IF " + conditionCommand.toString() + " == " + conditionToEqual.toString() + "\n"
               + Util.getNumTabs(numTabs + 1) + commandToRun.toString(numTabs + 1) + ")";
    }

    private boolean determinedEffects = false;
    private boolean effectsObject = false;
    @Override
    public boolean effectsObject() {
        if(!determinedEffects){
            effectsObject = conditionCommand.effectsObject() ||
                            conditionToEqual.effectsObject() ||
                            commandToRun.effectsObject();
            determinedEffects = true;
        }
        return effectsObject;
    }

    @Override
    public String getType() {
        return "IF";
    }
}
