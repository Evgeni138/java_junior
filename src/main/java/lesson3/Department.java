package lesson3;

import java.io.Serializable;

public class Department implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public Department(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                '}';
    }
}
