package field_objects.ground;

import java.awt.*;

/**
 * Created by Matt on 5/8/2017.
 */
public class NeutralSpot extends Ground {

    private static final NeutralSpot spot = new NeutralSpot();
    public static NeutralSpot getInstance(){
        return spot;
    }

    private NeutralSpot(){
        super(0, Color.LIGHT_GRAY);
    }

}
