package wesley;
import java.util.ArrayList;
import java.lang.ClassNotFoundException;

public class Building {
    public ArrayList<Room> apartments;
    public ArrayList<Room> commonRooms;
    public double tempSetPoint;
    private final static double DEFAULT_TEMP_SET_POINT = 20;

    public Building(double tempSetPoint){
        this.apartments = new ArrayList<Room>();
        this.commonRooms = new ArrayList<Room>();
        this.tempSetPoint = tempSetPoint;
    }    
    public Building(){
        this(DEFAULT_TEMP_SET_POINT);
    }

    public void addRoom(Room room) throws ClassNotFoundException{
        
    }

    public Room[] getRooms(){
        Room[] allRooms = new Room[apartments.size() + commonRooms.size()];

        return allRooms;
    }
}
