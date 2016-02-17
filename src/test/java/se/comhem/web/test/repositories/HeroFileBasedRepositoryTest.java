package se.comhem.web.test.repositories;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import se.comhem.web.test.domain.Gender;
import se.comhem.web.test.domain.Hero;
import se.comhem.web.test.domain.MarvelHero;
import se.comhem.web.test.exception.SaveHeroException;
import se.comhem.web.test.util.FileProperties;
import se.comhem.web.test.util.FileUtil;

/**
 * This class contains JUnit test cases for HeroFileBasedRepository
 * @author Prashant Pathania
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ FileUtil.class })
public class HeroFileBasedRepositoryTest {
    @Mock
    private File fileRepository;
    private HeroRepository heroRepository;
    private Hero hero;
    
    /**
     * This method is called before executing JUnit test case
     * @return void
     */
    @Before
    public void setUp() {
        PowerMockito.mockStatic(FileUtil.class);
        PowerMockito.when(FileUtil.getFile(FileProperties.HEROES_FILE_PATH)).thenReturn(fileRepository);
        heroRepository = PowerMockito.spy(new HeroFileBasedRepository());
        hero = new MarvelHero();
        hero.setName("Batman");
        hero.setWeakness("Girl");
        hero.setGender(Gender.MAN);
    }

    /**
     * Method Under test: save(Hero hero)
     * Scenario: when hero will be save
     * Expectation: it will be saved in file and heroMap. Also heroMap size will also increase.
     */
    @Test
    public void testSaveWhenFileIsAvailableAndthrowsException() throws SaveHeroException, IOException {
        PowerMockito.when(FileUtil.getFile(FileProperties.HEROES_FILE_PATH)).thenReturn(File.createTempFile("test-File", ".tmp"));
        Map<Integer,Hero> heroMap = heroRepository.list();
        int heroMapSize = heroMap.size();
        heroRepository.save(hero);
        Assert.assertNotSame(heroMapSize, heroRepository.list());
    }

    /**
     * This method is called after every JUnit test case to release memory
     * @return void
     */
    @After
    public void tearDown() {
        fileRepository = null;
        heroRepository = null;
        hero = null;
    }
}
