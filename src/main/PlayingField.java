package main;

import field_objects.players.Player;
import graphics.Canvas;
import field_objects.ground.GroundArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 5/8/2017.
 */
public class PlayingField {

    private final int width;
    private final int height;
    private final boolean visible;
    private final Canvas canvas;
    private final GroundArray ground;
    private final List<Player> playersOnField = new ArrayList<>();

    private static final int MULTIPLIER = 12;

    public PlayingField(int w, int h, boolean visible, boolean isBadBoard){
        width = w;
        height = h;
        this.visible = visible;
        if(visible){
            canvas = Canvas.getCanvas("field", width*MULTIPLIER, height*MULTIPLIER, true);
        } else {
            canvas = null;
        }
        ground = new GroundArray(width, height, isBadBoard);
        reset();
    }

    public PlayingField(int w, int h, boolean visible){
        this(w, h, visible, false);
    }

    public PlayingField reset(){
        ground.reset();
        playersOnField.clear();
        this.repaint();
        return this;
    }

    public PlayingField resetGround(){
        ground.reset();
        return this;
    }

    public PlayingField placePlayer(Player p){
        if(!playersOnField.contains(p)){
           playersOnField.add(p);
        }
        return this;
    }

    public PlayingField updateHealths(){
        for(final Player p : playersOnField){
            p.changeHealth(ground.get(p.getX(), p.getY()).getHealthDelta());
        }
        return this;
    }

    public PlayingField repaint(){
        if(!visible){
            return this;
        }
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                for(int i = 0; i < MULTIPLIER; i++){
                    for(int j = 0; j < MULTIPLIER; j++){
                        canvas.setColorPixel(x*MULTIPLIER + i, y*MULTIPLIER + j, ground.get(x, y).getColor());
                    }
                }
            }
        }
        for(Player p : playersOnField){
            for(int x = 0; x < MULTIPLIER; x++){
                for(int y = 0; y < MULTIPLIER; y++){
                    canvas.setColorPixel(p.getX()*MULTIPLIER + x, p.getY()*MULTIPLIER + y, p.getColor());
                }
            }
        }
        canvas.repaint();
        return this;
    }

    public GroundArray getGround() {
        return ground;
    }

}
