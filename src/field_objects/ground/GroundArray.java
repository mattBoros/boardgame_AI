package field_objects.ground;

import graphics.IntPoint;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by Matt on 5/13/2017.
 */
public class GroundArray {

    private static final Random random = new Random();

    private final int width;
    private final int height;
    private final Ground[][] arena;
    private final boolean isBadBoard;

    public GroundArray(int w, int h, boolean isBadBoard){
        width = w;
        height = h;
        this.isBadBoard = isBadBoard;
        arena = new Ground[width][height];
        arena[width/2][height/2] = BadSpot.getInstance();
    }

    public Ground get(int x, int y){
        x = Math.floorMod(x, width);
        y = Math.floorMod(y, height);
        if(arena[x][y] == null){
            arena[x][y] = getRandomGround(isBadBoard);
        }
        return arena[x][y];
    }

    public GroundArray reset(){
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                arena[x][y] = null;
            }
        }
        arena[width/2][height/2] = BadSpot.getInstance();
        return this;
    }

    private static Ground getRandomGround(boolean isBadBoard){
        final int l = random.nextInt(3);
        if(l == 0){
            return GoodSpot.getInstance();
        } else if(l == 1){
            if(isBadBoard){
                return BadSpot.getInstance();
            }
            return NeutralSpot.getInstance();
        } else {
            return BadSpot.getInstance();
        }
    }

}

