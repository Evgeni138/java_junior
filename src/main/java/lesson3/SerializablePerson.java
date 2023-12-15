package lesson3;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class SerializablePerson implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String name;
    private int age;
    private List<String> tags;

    private transient Department department;

    public SerializablePerson(String name, int age, List<String> tags) {
        this.name = name;
        this.age = age;
        this.tags = tags;
    }

    public SerializablePerson(String name, Department department) {
        this(name, 42, List.of("abc", "def"), department);
    }

    public SerializablePerson(String name, int age, List<String> tags, Department department) {
        this.name = name;
        this.age = age;
        this.tags = tags;
        this.department = department;
    }

    public SerializablePerson(String name, int age) {
        this(name, age, List.of());
    }

    public SerializablePerson(String name) {
        this(name, 20);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getTags() {
        return tags;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "SerializablePerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", tags=" + tags +
                ", department=" + department +
                '}';
    }
}
