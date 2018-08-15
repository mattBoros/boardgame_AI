package commands;

import main.Util;
import field_objects.ground.GroundArray;
import field_objects.players.Player;

/**
 * Created by Matt on 5/13/2017.
 */
public class CmdComposite extends Command {

    public final Command cmd1;
    public final Command cmd2;

    public CmdComposite(Command cmd1, Command cmd2){
        this.cmd1 = cmd1;
        this.cmd2 = cmd2;
    }

    @Override
    public Value run(final Player player, final GroundArray ground) {
        if(cmd1.effectsObject()){
            cmd1.run(player, ground);
        }
        if(cmd2.effectsObject()){
            cmd2.run(player, ground);
        }
        return Value.NULL_VALUE;
    }

    @Override
    public String toString(int numTabs) {
        return "(composite\n"
               + Util.getNumTabs(numTabs+1) + cmd1.toString(numTabs + 1) + "\n"
                      + Util.getNumTabs(numTabs + 1) + cmd2.toString(numTabs + 1) + ")";
    }

    private boolean determinedEffects = false;
    private boolean effectsObject = false;
    @Override
    public boolean effectsObject() {
        if(!determinedEffects){
            effectsObject = cmd1.effectsObject() || cmd2.effectsObject();
            determinedEffects = true;
        }
        return effectsObject;
    }

    @Override
    public String getType() {
        return "COMPOSITE";
    }


}
