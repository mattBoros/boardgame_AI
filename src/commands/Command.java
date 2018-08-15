package commands;

import field_objects.ground.GroundArray;
import field_objects.players.Player;

/**
 * Created by Matt on 5/13/2017.
 */
public abstract class Command extends Base {

    public abstract Value run(final Player player, final GroundArray ground);

    @Override
    public Value getValue(final Player player, final GroundArray ground) {
        return run(player, ground);
    }

}
