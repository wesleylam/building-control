package wesley;

import org.testng.annotations.*;
// exceptions
import java.lang.IllegalArgumentException;

public class CommonRoomTest {
    private CommonRoom commonRoom;
    private static int ID = 101;
    private static CommonRoom.RoomType roomType = CommonRoom.RoomType.Gym;

    /** 
     * Test common room construction with valid initial settings
     */
    @Test
    public void construct1() {
        commonRoom = new CommonRoom(ID, roomType);
        
        assert commonRoom.ID == ID;
        assert commonRoom.roomType == roomType;
        assert commonRoom.temperature >= 10;
        assert commonRoom.temperature <= 40;
        assert ! commonRoom.heatingOn;
        assert ! commonRoom.coolingOn;
    }

    /** 
     * Test common room constructor with negative ID
     * @throws IllegalArgumentException
     */
    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void construct2() {
        commonRoom = new CommonRoom(-101, roomType);
    }


    // collect Data
    /** 
     * Test common room collect data function without cooling/heating
     */
    @Test
    public void collectData1() {
        commonRoom = new CommonRoom(ID, roomType);
        
        String expected = (
            "Common Room: ID - " + String.valueOf(ID) + 
            ", Type: " + roomType + 
            String.format(", Temperature: %.2f", commonRoom.temperature)
        );
        assert commonRoom.collectData().equals(expected);
    }
    /** 
     * Test common room collect data function with cooling
     */
    @Test
    public void collectData2() {
        commonRoom = new CommonRoom(ID, roomType);
        commonRoom.coolingOn = true;
        
        String expected = (
            "Common Room: ID - " + String.valueOf(ID) + 
            ", Type: " + roomType + 
            String.format(", Temperature: %.2f", commonRoom.temperature) + 
            ", Cooling: On"
        );
        assert commonRoom.collectData().equals(expected);
    }

    /** 
     * Test common room collect data function with heating
     */
    @Test
    public void collectData3() {
        commonRoom = new CommonRoom(ID, roomType);
        commonRoom.heatingOn = true;
        
        String expected = (
            "Common Room: ID - " + String.valueOf(ID) + 
            ", Type: " + roomType + 
            String.format(", Temperature: %.2f", commonRoom.temperature) + 
            ", Heating: On"
        );
        assert commonRoom.collectData().equals(expected);
    }

    /** 
     * Test common room collect data function with cooling and heating
     */
    @Test
    public void collectData4() {
        commonRoom = new CommonRoom(ID, roomType);
        commonRoom.heatingOn = true;
        commonRoom.coolingOn = true;

        String expected = (
            "Common Room: ID - " + String.valueOf(ID) + 
            ", Type: " + roomType + 
            String.format(", Temperature: %.2f", commonRoom.temperature) + 
            ", Heating: On" + 
            ", Cooling: On"
        );
        assert commonRoom.collectData().equals(expected);
    }
}
