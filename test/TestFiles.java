import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import src.Files;

/**
 *
 * @author filipovsluha
 */
public class TestFiles {
    
    public TestFiles() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void TestAddingFiles() 
     {
        Files files = new Files();
        files.add("path","fileName");
        assertEquals(1, files.getNumberOfFiles());
     }
}
