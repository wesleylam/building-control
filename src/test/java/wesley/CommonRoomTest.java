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
     * Test apartment constructor with negative ID
     * @throws IllegalArgumentException
     */
    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void construct2() {
        commonRoom = new CommonRoom(-101, roomType);
    }

    /** 
     * Test apartment collect data function
     */
    @Test
    public void collectData() {
        commonRoom = new CommonRoom(ID, roomType);
        assert commonRoom.collectData().equals("Common Room: ID - " + String.valueOf(ID) + ", Type: " + roomType);
    }
}
