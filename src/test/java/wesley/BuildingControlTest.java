package wesley;
 
import org.testng.annotations.*;
 
public class BuildingControlTest {

    /** 
     * test main function with too many arguments
     * @throws ClassNotFoundException
     */
    @Test (expectedExceptions = { IllegalArgumentException.class })
    public void testMainTooManyArgs() throws ClassNotFoundException{
        String[] args = {"25", "extra"};
        BuildingControl.main(args);
    }
}
    
