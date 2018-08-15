package field_objects;

import java.awt.*;

/**
 * Created by Matt on 5/8/2017.
 */
public abstract class FieldObject {

    private final Color color;
    public FieldObject(Color c){
        color = c;
    }

    public Color getColor(){
        return color;
    }

}
