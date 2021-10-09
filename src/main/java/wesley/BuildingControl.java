package wesley;
import java.util.Scanner;

public class BuildingControl{
    // program entry point
    public static void main(String[] args) throws ClassNotFoundException, NumberFormatException{
        Building building = new Building();

        // add all required rooms
        building.addRoom(new Apartment(101, "Wesley"));
        building.addRoom(new Apartment(102, "John"));
        building.addRoom(new CommonRoom(201, CommonRoom.RoomType.Gym));
        building.addRoom(new CommonRoom(202, CommonRoom.RoomType.Library));


        // loop rooms
        for (Room room : building.getRooms()){
            if (room.temperature < building.tempSetPoint){
                room.heatingOn = true;
                room.coolingOn = false;
            } else if (room.temperature > building.tempSetPoint){
                room.heatingOn = false;
                room.coolingOn = true;
            }

            System.out.println(room.collectData());
        }
    }
}