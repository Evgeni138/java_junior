package lesson3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SerializedClass {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Path path = Path.of("C:/Example/java_junior/src/main/java/lesson3/serializable-person.txt");

        SerializablePerson eugene = new SerializablePerson("Eugene", 42,  List.of("abc", "def"),
                new Department("gb"));

        System.out.println(eugene);
        System.out.println();

        OutputStream outputStream = Files.newOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(eugene);
        outputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path));
        Object deserializedEugene = (SerializablePerson) objectInputStream.readObject();
        System.out.println(deserializedEugene);

    }
}
