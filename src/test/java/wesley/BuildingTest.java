package wesley;

import org.testng.annotations.*;
// exceptions
import java.lang.ClassNotFoundException;

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

}
