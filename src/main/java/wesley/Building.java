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

    private Double roundDecimal(Double num, int decimalPlace){
        if (decimalPlace == 0){
            return (Math.round(num) * 1.0);
        }
        // multiply by factor, round and divide by factor (factor scale by num of decimal places)
        Double factor = Math.pow(10.0, decimalPlace);
        return (Double) (Math.round(num * factor) / factor);
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

    public void tempControl(Boolean print){
        // loop rooms
        for (Room room : this.getRooms()){

            // equal when rounds to 2 decimal
            if ( roundDecimal(room.temperature, 2).equals( roundDecimal(this.tempSetPoint, 2) ) ){
                // same or minor difference, no heating/cooling
                room.heatingOn = false;
                room.coolingOn = false;
            } else if (room.temperature < this.tempSetPoint){
                // heating on
                room.heatingOn = true;
                room.coolingOn = false;
            } else if (room.temperature > this.tempSetPoint){
                // cooling on
                room.heatingOn = false;
                room.coolingOn = true;
            }
            if (print) System.out.println(room.collectData());
        }
        if (print) System.out.println();
    }

}
