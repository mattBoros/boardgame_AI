package field_objects.ground;

import java.awt.*;

/**
 * Created by Matt on 5/8/2017.
 */
public class BadSpot extends Ground {

    private static final BadSpot spot = new BadSpot();
    public static BadSpot getInstance(){
        return spot;
    }

    private BadSpot(){
        super(-1, Color.RED);
    }

}
