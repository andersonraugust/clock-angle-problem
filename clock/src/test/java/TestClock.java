
import com.clock.model.Clock;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestClock {
    
    public TestClock() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    
    public void testClock(){
        Clock clock = new Clock(3, 0);
        assertEquals(clock.getAngle(), 90);
        
        clock.setHour(6);
        assertEquals(clock.getAngle(), 180);
    }
}
