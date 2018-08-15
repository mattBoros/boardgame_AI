package field_objects.ground;

import field_objects.FieldObject;

import java.awt.*;

/**
 * Created by Matt on 5/8/2017.
 */
public abstract class Ground extends FieldObject {

    private final int healthDelta;

    public Ground(int healthD, Color c){
        super(c);
        healthDelta = healthD;
    }

    public int getHealthDelta(){
        return healthDelta;
    }

}
