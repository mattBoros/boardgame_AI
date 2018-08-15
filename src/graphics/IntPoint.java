package graphics;

/**
 * Created by Matt on 8/21/2016.
 */
public class IntPoint {

    public final int x;
    public final int y;


    private static final IntPoint[][] SINGLETONS = new IntPoint[101][101];
    static {
        for(int xi = 0; xi < 101; xi++){
            for(int yi = 0; yi < 101; yi++){
                SINGLETONS[xi][yi] = new IntPoint(xi - 50, yi - 50);
            }
        }

    }
    public static IntPoint getInstance(int x, int y){
        if(x < -50 || y < -50 || x > 50 || y > 50){
            return new IntPoint(x, y);
        }
        return SINGLETONS[x + 50][y + 50];
    }

    private IntPoint(int x, int y){
        this.x = x;
        this.y = y;
    }

    public IntPoint subtract(IntPoint p){
        return new IntPoint(x - p.x, y - p.y);
    }

    public IntPoint add(IntPoint p) { return new IntPoint(x + p.x, y + p.y);}

    public boolean equals(IntPoint p){
        return x == p.x && y == p.y;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    private String asString = null;
    public String toString(){
        if(asString == null){
            asString = "(" + x  + ", " + y + ")";
        }
        return asString;
    }
}
