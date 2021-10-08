package wesley;
 
import org.testng.annotations.*;
import org.testng.Assert;
 
public class BuildingControlTest {
    
    @BeforeClass
    public void setUp() {
        
    }

    @Test
    public void sampleTest() {
        System.out.println("sample test");
        Assert.assertTrue(true);
    }
 
}
