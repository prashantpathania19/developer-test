/**
 * 
 */
package se.comhem.web.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Logger;    
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is utility class for
 * File operations
 * @author prashantpathania
 *
 */
public class FileUtil {
    private static Logger logger = Logger.getLogger(FileUtil.class.getName());
    /**
     * Method to return file object
     * @param filePath - refers to filePath
     * @return - File object
     */
    public static File getFile(String filePath) {
        return new File(filePath);        
    }

    /**
     * This method read the file and returns the 
     * data in String format
     * @param file - represents the file object
     * @return - string data from file
     */
    public static String getFileContent(File file) {
        StringBuilder fileContent = new StringBuilder();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            int content;
            while ((content = fileInputStream.read()) != -1) {
                // convert to char and display it
                fileContent.append((char) content);
            }
        } catch (Exception ex) {
            logger.warning("Error reading file: " + ex.getMessage());
        }
        return fileContent.toString();
    }

    /**
     * This method converts object to json and writes
     * to a file
     * @param object - a data holding object
     * @param targetFile - represents file to write
     */
    public static void convertAndWriteJsonToFile(Object object, File targetFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(targetFile, object);
        } catch (Exception ex) {
            logger.warning("Error writing json to file " + ex.getMessage());
        }
    }
}
