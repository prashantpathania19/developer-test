package se.comhem.web.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.comhem.web.test.domain.Hero;
import se.comhem.web.test.exception.SaveHeroException;
import se.comhem.web.test.repositories.HeroRepository;
import java.io.IOException;
import java.util.Map;

@Service
public class HeroService {

    @Autowired
    HeroRepository fileBasedRepository;

    public Map<Integer, Hero> list() {
        return fileBasedRepository.list();
    }

    public Hero get(Integer id) {
        return fileBasedRepository.get(id);
    }

    public void save(Hero hero) throws SaveHeroException {
        fileBasedRepository.save(hero);
    }
}
