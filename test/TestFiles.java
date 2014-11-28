import java.util.ArrayList;
import java.util.List;
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
     
     @Test
     public void TestFindDuplicatesReturnCorrectNumberOfThem() 
     {
        List<String> duplicatezzList = new ArrayList<>();
        Files files = new Files();
        
        files.add("path01","fileName01");
        files.add("path02","fileName02");
        files.add("path03","dupliciteFilename");
        
        duplicatezzList = files.getDuplicates();
        assertEquals(0, duplicatezzList.size());
        
        files.add("path04","dupliciteFilename");
        duplicatezzList = files.getDuplicates();
        
        assertEquals(2, duplicatezzList.size());
        assertEquals(4, files.getNumberOfFiles());
     }
     
     public void TestFindDuplicatesReturnCorrectDuplicates() 
     {
        List<String> duplicatezzList = new ArrayList<>();
        Files files = new Files();
        String dupliciteFilename = "dupliciteFilename";
        
        files.add("path01","fileName01");
        files.add("path02","fileName02");
        files.add("path03", dupliciteFilename);
        files.add("path04", dupliciteFilename);
        duplicatezzList = files.getDuplicates();
        
        assertEquals(2, duplicatezzList.size());
        assertEquals(duplicatezzList.get(1),duplicatezzList.get(0));
        assertEquals(dupliciteFilename,duplicatezzList.get(0));
        assertEquals(dupliciteFilename,duplicatezzList.get(1));
        assertEquals(4, files.getNumberOfFiles());
     }
}
