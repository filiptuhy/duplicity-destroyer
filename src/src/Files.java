package src;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author filipovsluha
 */
public class Files {
    private HashMap<String, String> filezzHashMap = new HashMap<String, String>();
    List<String> duplicatezzList = new ArrayList<>();
    private int mp3Number = 1;

    public void add(String path, String fileName) {
        filezzHashMap.put(path,fileName);
    }
    
    public int addAllFromFolder(File folder)
    {
         File[] listOfFiles = folder.listFiles();
        if(listOfFiles != null){
            for (File oneFile : listOfFiles) {
                if (oneFile.isDirectory()) 
                {                                    
                    addAllFromFolder(new File(oneFile.getAbsolutePath()));
                }
                else if(oneFile.getName().endsWith(".mp3"))
                     {
                        filezzHashMap.put(oneFile.getAbsolutePath(),oneFile.getName());
                        mp3Number++;
                     }
            }
        }
        return mp3Number;
    }

    public Object getNumberOfFiles() {
        return filezzHashMap.size();
    }
    
    public List<String> getDuplicates()
    {
        final Collection<String> values = filezzHashMap.values();
        List<String> tempTotallyBadWayList = new ArrayList<>();
        for(String tempoValue : values)
        {
            tempTotallyBadWayList.add(tempoValue);
        }
            for(String duplicate : findDuplicates(tempTotallyBadWayList))
            {
                for (Map.Entry<String, String> entry : filezzHashMap.entrySet()) 
                {
                    if (duplicate.equals(entry.getValue())) {
                        duplicatezzList.add(entry.getKey());                        
                    }
                }
            }
        return duplicatezzList;
    }
    
    public static Set<String> findDuplicates(List<String> listContainingDuplicates) 
    {
        final Set<String> setToReturn = new HashSet<String>();
	final Set<String> set1 = new HashSet<String>();
        for (String yourInt : listContainingDuplicates) 
        {
            if (!set1.add(yourInt)) 
            {
		setToReturn.add(yourInt);
            }
        }
	return setToReturn;
    }    
}
