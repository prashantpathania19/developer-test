package se.comhem.web.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import se.comhem.web.test.domain.Gender;
import se.comhem.web.test.domain.Hero;
import se.comhem.web.test.domain.MarvelHero;
import se.comhem.web.test.services.HeroService;

import java.util.Map;

@Controller
@EnableWebMvc
@RequestMapping("heroes")
public class HeroController {

    @Autowired
    HeroService heroService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Map<Integer,Hero>> listHeroes() {

        return new ResponseEntity<Map<Integer,Hero>>(heroService.list(), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Hero> getHero(@PathVariable String id) {

        try {

            return new ResponseEntity<Hero>(heroService.get(Integer.parseInt(id)), HttpStatus.OK);

        } catch (NumberFormatException nfe) {

            return new ResponseEntity<Hero>(new MarvelHero(),HttpStatus.BAD_REQUEST);

        }

    }

    
    /**
     * This method saveHero to a file
     * @param name - refers to name of the hero
     * @param weakness - refers to weakness of the hero
     * @param gender - refers to gender of the hero
     * @return - string representing success
     */
    @RequestMapping(method = RequestMethod.POST, value = "/saveHero")
    public ResponseEntity<String> saveHero(@RequestParam(required=true) String name, @RequestParam(required=true) String weakness,
            @RequestParam(required=true) String gender) {
        Gender genderEnum = Gender.getGenderByName(gender);
        if (genderEnum == null) {
            return new ResponseEntity<String>("Please provide valid Gender", HttpStatus.OK);
        }
        Hero hero = new MarvelHero(name, weakness, genderEnum);
        heroService.save(hero);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
}
