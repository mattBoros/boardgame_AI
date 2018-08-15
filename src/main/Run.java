package main;

import commands.*;
import iterators.Iterator;
import iterators.Iterator_Type;
import field_objects.players.Bug;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Created by Matt on 5/8/2017.\
 */
public class Run {

    private static final int GRAPHIC_BOARD_SIZE = 30;

    private static final int MAX_DEPTH = 3;
    private static final int NUM_SAMPLES = 100;
    private static final int STEPS_PER_SAMPLE = 10;

    private static final int MAX_ALGORITHMS_TESTED = 100000;

    public static void main(String... args){
        Iterator iterator = Iterator.getIterator(Iterator_Type.COMMAND, 1, MAX_DEPTH);
//        new CmdWhileNot(Value.GREEN_VALUE, CmdGetTile.GET_HERE_TILE, CmdMove.MOVE_DOWN);
        IntStream.range(0, MAX_ALGORITHMS_TESTED)
                 .unordered()
                 .parallel()
                 .forEach(b -> {
                     count();
                     Base algorithm = null;
                     while(hasUseless(algorithm) && iterator.hasNext()){
                         algorithm = iterator.getCurrentBase();
                         iterator.iterate();
                     }
                     if(algorithm == null){
                         return;
                     }
                     PlayingField field = new PlayingField(GRAPHIC_BOARD_SIZE, GRAPHIC_BOARD_SIZE, false);
                     final double avgHealth = getAvgHealth(algorithm, field);
                     setMaxAvgHealth(avgHealth, algorithm);
                 });

//        bestBehavior = new CmdWhileNot(CmdGetTile.GET_HERE_TILE, Value.GREEN_VALUE, CmdMove.MOVE_RIGHT);
        System.out.println(" -- BEST BEHAVIOR -- ");
        Printer.print("\n" + bestBehavior.toString());

        waitForInput();

        int GRAPHIC_BOARD_SIZE = 50;
        // Run the best algorithm, and show what it does
        final PlayingField field = new PlayingField(GRAPHIC_BOARD_SIZE, GRAPHIC_BOARD_SIZE, true);
        Bug bug = new Bug(GRAPHIC_BOARD_SIZE / 2, GRAPHIC_BOARD_SIZE / 2, bestBehavior, null);
        field.repaint();
        sleep(1000);
        field.placePlayer(bug);
        field.repaint();
        sleep(2000);
        for (int step = 0; step < 10; step++) {
            bug.makeMove(field.getGround());
            field.repaint();
//            field.resetGround();
            sleep(1000);
            System.out.println("-end turn-");
        }
    }

    public static boolean hasUseless(Base algorithm){
        if(algorithm == null){
            return true;
        }
        if(algorithm.getType().equals("COMPOSITE")){
            CmdComposite composite = (CmdComposite) algorithm;
            if((!composite.cmd1.getType().equals("COMPOSITE") && !composite.cmd1.effectsObject())
               || (!composite.cmd2.getType().equals("COMPOSITE") && !composite.cmd2.effectsObject())){
                return true;
            }
            return hasUseless(composite.cmd1) || hasUseless(composite.cmd2);
        }
        return false;
    }

    static int count = 0;
    public static synchronized void count(){
        count += 1;
        if(count % 5000 == 0){
            System.out.println(count * 100.0 / MAX_ALGORITHMS_TESTED + "%");
        }
    }

    private static void waitForInput(){
        Scanner s = new Scanner(System.in);
        s.next();
        s.close();
    }

    private static void sleep(long milli){
        try {
            Thread.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static double getAvgHealth(Base algorithm, PlayingField field){
        if(!algorithm.effectsObject()){
            return -Double.MAX_VALUE;
        }
        double totalHealth = 0;
        for(int sample = 0; sample < NUM_SAMPLES; sample++){
            Bug bug = new Bug(GRAPHIC_BOARD_SIZE / 2, GRAPHIC_BOARD_SIZE / 2, algorithm, null);
            field.placePlayer(bug);
            for(int step = 0; step < STEPS_PER_SAMPLE; step++){
                bug.makeMove(field.getGround());
                field.updateHealths();
            }
            totalHealth += bug.getHealth();
            field.reset();
        }
        return totalHealth * 1.0 / NUM_SAMPLES;
    }

    private static Base bestBehavior = null;
    private static double maxAvgHealth = 0;
    public static synchronized void setMaxAvgHealth(double avgHealth, Base behavior) {
        if(avgHealth > maxAvgHealth || bestBehavior == null){
            maxAvgHealth = avgHealth;
            bestBehavior = behavior;
            Printer.print("--------------------");
            Printer.print(bestBehavior.toString());
            Printer.print("--------------------");
            try {
                Files.write(Paths.get("code.txt"), bestBehavior.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
