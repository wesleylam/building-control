package wesley;

import org.testng.annotations.*;
// exceptions
import java.lang.ClassNotFoundException;
import java.lang.IllegalArgumentException;

public class BuildingTest {
    private Building building;
    private static double temp = 35.5;
    private static Apartment[] sampleApartments = new Apartment[]{
        new Apartment(1, "Wesley"),
        new Apartment(2, "Tim"),
        new Apartment(3, "Leonardo"),
    };
    private static CommonRoom[] sampleCommonRooms = new CommonRoom[]{
        new CommonRoom(1, CommonRoom.RoomType.Gym),
        new CommonRoom(2, CommonRoom.RoomType.Laundry),
        new CommonRoom(3, CommonRoom.RoomType.Library),
    };

    // extra room class for testing
    private class unknownRoom extends Room{
        unknownRoom(){
            super(0);
        }
        public String collectData(){
            return "";
        }
    }

    // Construction
    /** 
     * Test building construction with custom temperature set point having valid initial settings
     */
    @Test
    public void construct1() {
        building = new Building(temp);

        assert building.tempSetPoint == temp;
        assert building.apartments.size() == 0;
        assert building.commonRooms.size() == 0;
    }
    /** 
     * Test building construction with default temperature set point having valid initial settings
     */
    @Test
    public void construct2() {
        building = new Building();

        assert building.tempSetPoint == 20;
        assert building.apartments.size() == 0;
        assert building.commonRooms.size() == 0;
    }


    // Add room
    /** 
     * test adding apartment to building
     * @throws ClassNotFoundException
     */
    @Test
    public void addApartment() throws ClassNotFoundException{
        building = new Building();
        building.addRoom(new Apartment(101, "Wesley"));
    }

    /** 
     * test adding common room to building
     * @throws ClassNotFoundException
     */
    @Test
    public void addCommonRoom() throws ClassNotFoundException{
        building = new Building();
        building.addRoom(new CommonRoom(101, CommonRoom.RoomType.Laundry));
    }

    /** 
     * Test adding room with unknown class extends room
     * @throws ClassNotFoundException
     */
    @Test(expectedExceptions = { ClassNotFoundException.class })
    public void addUnknownRoom() throws ClassNotFoundException{
        building = new Building();
        building.addRoom(new unknownRoom());
    }


    // Get room
    /** 
     * test getting empty rooms
     */
    @Test
    public void getEmptyRoom(){
        building = new Building(); 
        Room[] rooms = building.getRooms();

        assert rooms.length == 0;
    }

    /** 
     * test getting rooms with only apartments
     * @throws ClassNotFoundException
     */
    @Test
    public void getRoomsWithApartments() throws ClassNotFoundException{
        building = new Building();
        building.addRoom(sampleApartments[0]);
        building.addRoom(sampleApartments[1]);
        building.addRoom(sampleApartments[2]);

        Room[] rooms = building.getRooms();
        assert rooms[0] == sampleApartments[0];
        assert rooms[1] == sampleApartments[1];
        assert rooms[2] == sampleApartments[2];
        assert rooms.length == 3;
    }
    /** 
     * test getting rooms with only common rooms
     * @throws ClassNotFoundException
     */
    @Test
    public void getRoomsWithCommonRooms() throws ClassNotFoundException{
        building = new Building();
        building.addRoom(sampleCommonRooms[0]);
        building.addRoom(sampleCommonRooms[1]);
        building.addRoom(sampleCommonRooms[2]);

        Room[] rooms = building.getRooms();
        assert rooms[0] == sampleCommonRooms[0];
        assert rooms[1] == sampleCommonRooms[1];
        assert rooms[2] == sampleCommonRooms[2];
        assert rooms.length == 3;
    }
    /** 
     * test getting rooms with apartments and common rooms
     * @throws ClassNotFoundException
     */
    @Test
    public void getRoomsAllType() throws ClassNotFoundException{
        building = new Building();
        building.addRoom(sampleApartments[0]);
        building.addRoom(sampleCommonRooms[1]);
        building.addRoom(sampleApartments[2]);

        Room[] rooms = building.getRooms();

        // apartments shown first, then common rooms
        assert rooms[0] == sampleApartments[0];
        assert rooms[1] == sampleApartments[2];
        assert rooms[2] == sampleCommonRooms[1];
        assert rooms.length == 3;
    }

    /** 
     * helper function to determine if a room is in the correct cooling/heating state
     */
    private Boolean isSuitableControl(Room room, double buildingTemp){        
        // treat as equal if equal when rounds to 2 decimal
        Double roundedRoomTemp = Math.round(room.temperature * 100.0) / 100.0;
        Double roundedBuildingTemp = Math.round(buildingTemp * 100.0) / 100.0;

        if ( roundedRoomTemp.equals(roundedBuildingTemp) ){
            return ! room.heatingOn && ! room.coolingOn;
        } else if (room.temperature > buildingTemp){
            return room.coolingOn && ! room.heatingOn;
        } else if (room.temperature < buildingTemp){
            return room.heatingOn && ! room.coolingOn;
        }
        throw new IllegalArgumentException("Should be unreachable");
    }
    /** 
     * test rooms temperature control with: 
     * (1) cooler temperature; (heating on) 
     * (2) low difference temperature; (nothing on)
     * (3) same temperature; (nothing on)
     * (4) hotter temperature; (cooling on)
     * @throws ClassNotFoundException
     */
    @Test
    public void tempControl() throws ClassNotFoundException{
        building = new Building();
        sampleApartments[0].temperature = building.tempSetPoint - 1;
        building.addRoom(sampleApartments[0]);
        sampleCommonRooms[1].temperature = building.tempSetPoint + 0.001;
        building.addRoom(sampleCommonRooms[1]);
        sampleCommonRooms[2].temperature = building.tempSetPoint;
        building.addRoom(sampleCommonRooms[2]);
        sampleApartments[2].temperature = building.tempSetPoint + 1;
        building.addRoom(sampleApartments[2]);

        building.tempControl();

        for (Room room : building.getRooms()){
            assert isSuitableControl(room, building.tempSetPoint);
        }

    }
}
