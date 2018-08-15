package commands;

import main.Util;
import field_objects.ground.GroundArray;
import field_objects.players.Player;

/**
 * Created by Matt on 5/13/2017.
 */
public class CmdWhileNot extends Command {

    private final Base conditionCommand;
    private final Base conditionToEqual;
    private final Command commandToRun;

    public CmdWhileNot(Base cmd, Base equal, Command toRun){
        conditionCommand = cmd;
        conditionToEqual = equal;
        commandToRun = toRun;
    }

    @Override
    public Value run(final Player player, final GroundArray ground) {
        if(commandToRun.effectsObject()){
            int counter = 0;
            while(!conditionCommand.getValue(player, ground).equals(conditionToEqual.getValue(player, ground))){
                commandToRun.run(player, ground);
                counter++;
                if(counter >= 10){
                    break;
                }
            }
        }
        return Value.NULL_VALUE;
    }

    @Override
    public String toString(int numTabs) {
        return "(WHILE " + conditionCommand.toString() + " != " + conditionToEqual.toString() + "\n"
               + Util.getNumTabs(numTabs + 1) + commandToRun.toString(numTabs + 1) + ")";
    }

    @Override
    public boolean effectsObject() {
        return true; // this might change
    }

    @Override
    public String getType() {
        return "WHILE_NOT";
    }
}
