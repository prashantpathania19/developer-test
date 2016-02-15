package se.comhem.web.test.domain;

/**
 * Enum representing Gender
 */
public enum Gender {
    MAN("man"), WOMAN("woman");

    private final String name;

    /**
     * Default constructor taking name as paramter
     * @param name - represents a string value
     */
    private Gender(String name) {
        this.name = name;
    }

    /**
     * This method return Gender on the basis of its name
     * @param name - represents a string value
     * @return Gender
     */
    public static Gender getGenderByName(String name) {
        Gender gender = null;
        for (Gender type : values()) {
            if (type.getName().equals(name)) {
                gender = type;
            }
        }
        return gender;
    }

    /**
     * Simple getter for returning name
     * @return String
     */
    public String getName() {
        return name;
    }
}
