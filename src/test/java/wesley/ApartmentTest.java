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


    // collect Data
    /** 
     * Test apartment collect data function without cooling/heating
     */
    @Test
    public void collectData1() {
        apartment = new Apartment(ID, owner);

        String expected = (
            "Apartment: ID - " + String.valueOf(ID) + 
            ", Owner: " + owner + 
            String.format(", Temperature: %.2f", apartment.temperature)
        );
        assert apartment.collectData().equals(expected);
    }
    /** 
     * Test apartment collect data function with cooling
     */
    @Test
    public void collectData2() {
        apartment = new Apartment(ID, owner);
        apartment.coolingOn = true;
        
        String expected = (
            "Apartment: ID - " + String.valueOf(ID) + 
            ", Owner: " + owner + 
            String.format(", Temperature: %.2f", apartment.temperature) + 
            ", Cooling: On"
        );
        assert apartment.collectData().equals(expected);
    }

    /** 
     * Test apartment collect data function with heating
     */
    @Test
    public void collectData3() {
        apartment = new Apartment(ID, owner);
        apartment.heatingOn = true;
        
        String expected = (
            "Apartment: ID - " + String.valueOf(ID) + 
            ", Owner: " + owner + 
            String.format(", Temperature: %.2f", apartment.temperature) + 
            ", Heating: On"
        );
        assert apartment.collectData().equals(expected);
    }

    /** 
     * Test apartment collect data function with cooling and heating
     */
    @Test
    public void collectData4() {
        apartment = new Apartment(ID, owner);
        apartment.heatingOn = true;
        apartment.coolingOn = true;

        String expected = (
            "Apartment: ID - " + String.valueOf(ID) + 
            ", Owner: " + owner + 
            String.format(", Temperature: %.2f", apartment.temperature) + 
            ", Heating: On" + 
            ", Cooling: On"
        );
        assert apartment.collectData().equals(expected);
    }


    // pass time
    /** 
     * Test apartment temperature after time passes when cooling
     */
    @Test
    public void passTimeCool() {
        apartment = new Apartment(ID, owner);
        apartment.coolingOn = true;
        double oldTemp = apartment.temperature;
        apartment.passTime();

        assert apartment.temperature == oldTemp - 0.01;
    }

    /** 
     * Test apartment temperature after time passes when heating
     */
    @Test
    public void passTimeHeat() {
        apartment = new Apartment(ID, owner);
        apartment.heatingOn = true;
        double oldTemp = apartment.temperature;
        apartment.passTime();

        assert apartment.temperature == oldTemp + 0.01;
    }

    /** 
     * Test apartment temperature after time passes when no heating/cooling enabled
     */
    @Test
    public void passTimeNone() {
        apartment = new Apartment(ID, owner);
        double oldTemp = apartment.temperature;
        apartment.passTime();

        assert apartment.temperature == oldTemp;
    }
}
