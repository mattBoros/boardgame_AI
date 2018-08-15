package field_objects.players;

import commands.Base;
import field_objects.ground.GroundArray;
import genetic_codes.GeneticCode;

import java.awt.*;
import java.util.Random;

/**
 * Created by Matt on 5/8/2017.
 */
public class Bug extends Player {

    private static final Random random = new Random();

    private final GeneticCode[] codes;
    private final Base behavior;

    public Bug(int x, int y, Base behavior, GeneticCode[] codes){
        super(x, y, Color.BLUE);
        this.behavior = behavior;
        this.codes = codes;
    }

    public Bug makeMove(final GroundArray ground){
        behavior.getValue(this, ground);
        return this;
    }

    public Bug mate(Bug otherBug){
        final GeneticCode[] newCodes = new GeneticCode[codes.length];

        for(int i = 0; i < newCodes.length; i++){
            if(random.nextBoolean()){
                newCodes[i] = codes[i];
            } else {
                newCodes[i] = otherBug.codes[i];
            }
        }

        return new Bug(this.getX(), this.getX(), behavior, newCodes);
    }

}
