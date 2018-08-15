package field_objects.ground;

import java.awt.*;

/**
 * Created by Matt on 5/13/2017.
 */
public class DeathSpot extends Ground {

    private static final DeathSpot spot = new DeathSpot();
    public static DeathSpot getInstance(){
        return spot;
    }

    private DeathSpot() {
        super(-500, Color.BLACK);
    }
}
