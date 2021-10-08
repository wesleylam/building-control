package wesley;

import org.testng.annotations.*;
// exceptions
import java.lang.IllegalArgumentException;

public class ApartmentTest {
    private Apartment apartment;
    private static int ID = 101;
    private static String owner = "Wesley";

    /** 
     * Test apartment construction with valid initial settings
     */
    @Test
    public void construct1() {
        apartment = new Apartment(ID, owner);
        
        assert apartment.ID == ID;
        assert apartment.owner == owner;
        assert apartment.temperature >= 10;
        assert apartment.temperature <= 40;
        assert ! apartment.heatingOn;
        assert ! apartment.coolingOn;
    }

    /** 
     * Test apartment constructor with negative ID
     * @throws IllegalArgumentException
     */
    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void construct2() {
        apartment = new Apartment(-101, "Wesley");
    }
    
    /** 
     * Test apartment constructor with empty string
     * @throws IllegalArgumentException
     */
    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void construct3() {
        apartment = new Apartment(101, "");
    }

    /** 
     * Test apartment collect data function
     */
    @Test
    public void collectData() {
        apartment = new Apartment(ID, owner);
        assert apartment.collectData().equals("Apartment: ID - " + String.valueOf(ID) + ", Owner: " + owner);
    }
}
