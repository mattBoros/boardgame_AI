package main;

import java.util.*;

/**
 * Created by Matt on 10/8/2016.
 */
public class Timer {

    private static final double nanoDivider = 1_000_000_000;

    private static final Map<String, Long> lastTimes = new HashMap<>();
    private static final Map<String, Long> totalTimes = new HashMap<>();

    private static final Map<String, Integer> calls = new HashMap<>();

    public static double getTime(){
        return System.currentTimeMillis() / 1000.0;
    }

    private static long getNanoTime(){
        return System.nanoTime();
    }

    public static void start(String name){
        if(!calls.keySet().contains(name)){
            calls.put(name, 0);
        }
        calls.put(name, 1 + calls.get(name));
        if(!totalTimes.containsKey(name)){
            totalTimes.put(name, 0L);
            lastTimes.put(name, 0L);
        }
        if(lastTimes.get(name) != 0){
            return;
        }
        lastTimes.put(name, getNanoTime());
    }

    public static void end(String name){
        calls.put(name, -1 + calls.get(name));
        if(calls.get(name) == 0){
            long delta = getNanoTime() - lastTimes.get(name);
            totalTimes.put(name, totalTimes.get(name) + delta);
            lastTimes.put(name, 0L);
        }
    }

    public static void endAllLoops(){
        for(Map.Entry<String, Long> entry : totalTimes.entrySet()){
            double time = entry.getValue() / nanoDivider;
            time = 0.01 * Math.floor(time * 100.0);
            String s = entry.getKey() + " time took " + time + " seconds";
            Printer.print(s);
        }
    }

}
