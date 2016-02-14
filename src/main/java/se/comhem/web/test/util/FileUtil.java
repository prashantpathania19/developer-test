/**
 * 
 */
package se.comhem.web.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

import se.comhem.web.test.domain.Hero;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is utility class for
 * File operations
 * @author Prashant Pathania
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
    public static void convertAndWriteJsonToFile(List<Hero> heroList, File targetFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(targetFile, heroList);
        } catch (Exception ex) {
            logger.warning("Error writing json to file " + ex.getMessage());
        }
    }

    /**
     * This method clears the content of the file
     * @param targetFile - refers to the file path
     */
    public static void clearFileContent(File targetFile) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(targetFile);
            writer.print("");
        } catch (Exception ex) {
            logger.warning("Error writing json to file " + ex.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
