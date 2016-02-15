/**
 * 
 */
package se.comhem.web.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import se.comhem.web.test.domain.Hero;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is utility class for
 * File operations
 * @author Prashant Pathania
 *
 */
public class FileUtil {
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
    public static String getFileContent(File file) throws FileNotFoundException, IOException {
        StringBuilder fileContent = new StringBuilder();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            int content;
            while ((content = fileInputStream.read()) != -1) {
                // convert to char and display it
                fileContent.append((char) content);
            }
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
        return fileContent.toString();
    }

    /**
     * This method converts object to json and writes
     * to a file
     * @param object - a data holding object
     * @param targetFile - represents file to write
     */
    public static void convertAndWriteJsonToFile(List<Hero> heroList, File targetFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(targetFile, heroList);
    }

    /**
     * This method clears the content of the file
     * @param targetFile - refers to the file path
     */
    public static void clearFileContent(File targetFile) throws IOException {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(targetFile);
            writer.print("");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
