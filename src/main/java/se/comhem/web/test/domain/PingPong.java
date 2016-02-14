package se.comhem.web.test.domain;

import java.io.Serializable;

public class PingPong implements Serializable {
    private static final long serialVersionUID = 1210149414674312727L;
    private String message;
    private String input;

    public PingPong(String name, String surname) {
        this.message = name;
        this.input = surname;
    }

    public String getMessage() {
        return message;
    }

    public String getInput() {
        return input;
    }
}
