package main;

import commands.Direction;
import graphics.IntPoint;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matt on 5/13/2017.
 */
public class Util {

    public static IntPoint directionToDelta(Direction dir){
        if(dir == Direction.UP){
            return IntPoint.getInstance(0, 1);
        } else if(dir == Direction.DOWN){
            return IntPoint.getInstance(0, -1);
        } else if(dir == Direction.LEFT){
            return IntPoint.getInstance(-1, 0);
        } else if(dir == Direction.RIGHT){
            return IntPoint.getInstance(1, 0);
        } else if(dir == Direction.HERE){
            return IntPoint.getInstance(0, 0);
        }
        return null;
    }

    public static String getNumTabs(int n){
        String s = "";
        for(int i = 0; i < n; i++){
            s += "\t";
        }
        return s;
    }

}
