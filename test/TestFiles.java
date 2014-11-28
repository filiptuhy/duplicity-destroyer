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
     
     @Test
     public void TestFindDuplicatesReturnCorrectDuplicates() 
     {
        List<String> duplicatezzList = new ArrayList<>();
        Files files = new Files();
        String dupliciteFilename = "dupliciteFilename";

         
        files.add("path01","fileName01");
        files.add("path02","fileName02");       
        files.add("path03"+dupliciteFilename, dupliciteFilename);
        files.add("path04"+dupliciteFilename, dupliciteFilename);
        
        duplicatezzList = files.getDuplicates();
    //TODO FIX THIS TESTS
        assertEquals(2, duplicatezzList.size());
        assertEquals("path03"+dupliciteFilename,duplicatezzList.get(0));
        assertEquals("path04"+dupliciteFilename,duplicatezzList.get(1));
        assertEquals(4, files.getNumberOfFiles());
     }
     
     @Test
     public void TestDeleFile() 
     {
        List<String> duplicatezzList = new ArrayList<>();
        Files files = new Files();
        String dupliciteFilename = "dupliciteFilename";
        
        files.add("path01","fileName01");
        String fileToBeDeleted = "path02";
        files.add(fileToBeDeleted,"fileName02");
        files.add("path03", dupliciteFilename);
        files.add("path04", dupliciteFilename);
        duplicatezzList = files.getDuplicates();
        
        assertEquals(4, files.getNumberOfFiles());
        files.deleteByPath(fileToBeDeleted);
        assertEquals(3, files.getNumberOfFiles());        
     }
     
     @Test
     public void TestDeleWontDeleteIfNoDuplicates() 
     {
        Files files = new Files();
         
        files.add("path01","fileName01");
        String fileToBeDeleted = "path02";
        files.add(fileToBeDeleted,"fileName02");
        
        assertEquals(2, files.getNumberOfFiles()); 
        
        files.deleteByPath(fileToBeDeleted);
        assertEquals(2, files.getNumberOfFiles());        
     }
     
      @Test
     public void TestDeleAll() 
     {
        Files files = new Files();
        files.add("path","fileName");
        files.add("path01","fileName01");
        assertEquals(2, files.getNumberOfFiles());  
        files.deleteAll();
        assertEquals(0, files.getNumberOfFiles());  
     }
}
