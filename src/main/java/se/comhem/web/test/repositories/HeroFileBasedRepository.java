package se.comhem.web.test.repositories;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.comhem.web.test.domain.Hero;
import se.comhem.web.test.domain.MarvelHero;
import se.comhem.web.test.util.FileProperties;
import se.comhem.web.test.util.FileUtil;

/**
 * This class is responsible for loading 
 * and saving heroes into a file
 * @author Prashant Pathania
 */
@Repository("fileBasedRepository")
public class HeroFileBasedRepository implements HeroRepository {
    private static Logger logger = Logger.getLogger(HeroFileBasedRepository.class.getName());
    private Map<Integer,Hero> heroMap = new HashMap<Integer,Hero>();
    private File fileRepository;

    /**
     * Default constructor to load repository
     * in a map
     */
    public HeroFileBasedRepository() {
        List<Hero> heroList = null;
        fileRepository = FileUtil.getFile(FileProperties.HEROES_FILE_PATH);
        //read file and convert data
        if (fileRepository.exists()) {
            try {
            ObjectMapper objectMapper = new ObjectMapper();
            heroList = objectMapper.readValue(FileUtil.getFileContent(fileRepository), objectMapper.getTypeFactory().
                    constructCollectionType(List.class, MarvelHero.class));
            } catch (Exception ex) {
                logger.warning("error in converting data into entity list: " + ex.getMessage());
            }
        }

        if (heroList != null && !heroList.isEmpty()) {
            for (Hero hero : heroList) {
                heroMap.put(heroMap.size(), hero);
            }
        }
    }

    /* (non-Javadoc)
     * @see se.comhem.web.test.repositories.HeroRepository#list()
     */
    public Map<Integer, Hero> list() {
        return heroMap;
    }

    /* (non-Javadoc)
     * @see se.comhem.web.test.repositories.HeroRepository#get(java.lang.Integer)
     */
    public Hero get(Integer id) {
        return heroMap.get(id);
    }

    /* (non-Javadoc)
     * @see se.comhem.web.test.repositories.HeroRepository#save(se.comhem.web.test.domain.Hero)
     */
    public void save(Hero hero) {
        if (fileRepository.exists()) {
            List<Hero> heroList = new ArrayList<>(heroMap.values());
            heroList.add(hero);
            FileUtil.clearFileContent(fileRepository);
            FileUtil.convertAndWriteJsonToFile(heroList, fileRepository);
            heroMap.put(heroMap.size(), hero);
        }
    }
}
