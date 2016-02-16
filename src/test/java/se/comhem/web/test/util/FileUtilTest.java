/**
 * 
 */
package se.comhem.web.test.util;

import java.io.File;
import java.io.FileNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * This class contains JUnit test cases for FileUtil
 * @author Prashant Pathania
 */
@RunWith(MockitoJUnitRunner.class)
public class FileUtilTest {
    private String FILE_PATH = "test";

    /**
     * This method is called before executing JUnit test case
     * @return void
     */
    @Before
    public void setUp() {}

    /**
     * Method Under test: getFile(String filePath)
     * Scenario: When file is available
     * Expectation: file exists method will be true
     */
    @Test
    public void testGetFileWhenFileIsAvailable() {
        File file = FileUtil.getFile(FileProperties.HEROES_FILE_PATH);
        Assert.assertTrue(file.exists());
    }

    /**
     * Method Under test: getFile(String filePath)
     * Scenario: When file is not available
     * Expectation: file exists method will be false
     */
    @Test
    public void testGetFileWhenFileIsNotAvailable() {
        File file = FileUtil.getFile(FILE_PATH);
        Assert.assertFalse(file.exists());
    }

    /**
     * Method Under test: getFileContent(File file)
     * Scenario: When file is available
     * Expectation: method will return the content of file in a string
     */
    @Test
    public void testGetFileContentWhenFileIsAvailable() throws Exception {
        File file = FileUtil.getFile(FileProperties.HEROES_FILE_PATH);
        String fileContent = FileUtil.getFileContent(file);
        Assert.assertNotNull(fileContent);
    }

    /**
     * Method Under test: getFileContent(File file)
     * Scenario: When file is not available
     * Expectation: method will throw FileNotFoundException
     */
    @Test (expected=FileNotFoundException.class)
    public void testGetFileContentWhenFileIsNotAvailable() throws Exception {
        File file = FileUtil.getFile(FILE_PATH);
        FileUtil.getFileContent(file);
    }

    /**
     * This method is called after every JUnit test case to release memory
     * @return void
     */
    @After
    public void tearDown() {}
}
