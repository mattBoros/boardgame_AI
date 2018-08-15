package field_objects.ground;

import java.awt.*;

/**
 * Created by Matt on 5/8/2017.
 */
public class GoodSpot extends Ground {

    private static final GoodSpot spot = new GoodSpot();
    public static GoodSpot getInstance(){
        return spot;
    }

    private GoodSpot(){
        super(1, Color.GREEN);
    }

}
