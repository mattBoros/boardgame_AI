package main;

/**
 * Created by Matt on 5/13/2017.
 */
public class Printer {

    public static final boolean PRINT_ON = true;

    public static void print(Object o){
        if(PRINT_ON){
            System.out.println(o);
        }
    }

}
