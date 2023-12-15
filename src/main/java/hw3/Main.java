package hw3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        Student student = new Student("Student Student", 18);
        writeObjectToFile(student);

        Path path = Path.of("C:/Example/java_junior/src/main/java/hw3/" +
                "Student_3a48c34b-a0ba-488f-b720-b9ee1902adf8.txt");

        Student readStudentFromFile = readObjectFromFile(path);
        System.out.println(readStudentFromFile);
    }

    static void writeObjectToFile(Object obj) {
        Path path = Path.of("C:/Example/java_junior/src/main/java/hw3/" +
                obj.getClass().getSimpleName() + "_" + UUID.randomUUID().toString() + ".txt");

        try (OutputStream outputStream = Files.newOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Student readObjectFromFile(Path path) {
        Student student = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path));
            student = (Student) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return student;
    }

}
