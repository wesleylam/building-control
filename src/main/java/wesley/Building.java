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
        
        // add room according to room type
        if (room instanceof Apartment){
            apartments.add(room);
        }
        else if (room instanceof CommonRoom){
            commonRooms.add(room);
        }
        else {
            // throw exception when unspecified room type is passed
            throw new ClassNotFoundException();
        }
    }

    public Room[] getRooms(){
        Room[] allRooms = new Room[apartments.size() + commonRooms.size()];

        int i = 0;
        for (Room apt : apartments) {
            allRooms[i] = apt;
            i += 1;
        }
        for (Room cr : commonRooms) {
            allRooms[i] = cr;
            i += 1;
        }

        return allRooms;
    }
}
