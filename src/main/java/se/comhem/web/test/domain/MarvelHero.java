package se.comhem.web.test.domain;

import java.io.Serializable;

public class MarvelHero implements Hero, Serializable {
    private static final long serialVersionUID = -2544615669555295330L;
    private String name;
    private String weakness;
    private Gender gender;

    public MarvelHero() {
        super();
    }

    public MarvelHero(String name, String weakness, Gender gender) {
        this.name = name;
        this.weakness = weakness;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }
}
