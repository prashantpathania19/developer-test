package se.comhem.web.test.repositories;

import se.comhem.web.test.domain.Hero;
import se.comhem.web.test.exception.SaveHeroException;
import java.io.IOException;
import java.util.Map;

/**
 * This class represents basic structure for saving and loading
 * heroes.
 */
public interface HeroRepository {
    /**
     * This method returns the list of heroes.
     * @return - Map<Integer, Hero> representing a ID based 
     * list of heroes
     */
    Map<Integer, Hero> list();
    
    /**
     * This method returns the hero on the 
     * basis of ID
     * @param id - input representing hero ID
     * @return - Hero - object representing hero entity
     */
    Hero get(Integer id);
    
    /**
     * This method saves hero object 
     * @param hero - object representing hero entity
     * @throws IOException 
     * @throws SaveHeroException 
     */
    void save(Hero hero) throws SaveHeroException;
}
