package wesley;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Abstract class Room for building
 */
public abstract class Room {
    private final static double randMinTemp = 10;
    private final static double randMaxTemp = 40;

    public int ID;
    public double temperature;
    public boolean heatingOn;
    public boolean coolingOn;

    public Room(int ID){
        this.ID = ID;
        this.temperature = ThreadLocalRandom.current().nextDouble(randMinTemp, randMaxTemp);
        this.heatingOn = false;
        this.coolingOn = false;
    }

    public abstract String collectData();
}
