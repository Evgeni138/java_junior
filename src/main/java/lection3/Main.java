package lection3;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String str = "Всем привет!";
        FileOutputStream fileOutputStream = new FileOutputStream(
                "C:/Example/java_junior/src/main/java/lection3/ser.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(str);
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(
                "C:/Example/java_junior/src/main/java/lection3/ser.txt"
        );
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        String s = (String) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(s);
    }
}
