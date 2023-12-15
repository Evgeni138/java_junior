package lesson3;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Main {
    public static void main(String[] args) throws IOException {
        // Input
        Path path = Path.of("pom.xml");

        InputStream inputStream = Files.newInputStream(path);
        int read = inputStream.read();
        byte[] bytes = inputStream.readAllBytes();
        String content = new String(bytes);
        inputStream.close();

        byte[] firstSymbol = new byte[1];
        firstSymbol[0] = (byte) read;

        System.out.print(new String(firstSymbol));
        System.out.println(content);

        System.out.println();
        System.out.println();

        // example2
        BufferedInputStream inputStream2 = new BufferedInputStream(Files.newInputStream(path));
        int read2 = inputStream2.read();
        byte[] bytes2 = inputStream2.readAllBytes();
        String content2 = new String(bytes2);
        inputStream.close();

        byte[] firstSymbol2 = new byte[1];
        firstSymbol2[0] = (byte) read2;

        System.out.print(new String(firstSymbol2));
        System.out.println(content2);

        System.out.println();
        System.out.println();

        // Output
        Path path2 = Path.of("C:/Example/java_junior/src/main/java/lesson3/ser.txt");

        OutputStream outputStream = Files.newOutputStream(path2, StandardOpenOption.APPEND);
        outputStream.write("my content\n".getBytes());
        outputStream.close();

        InputStream inputStream3 = Files.newInputStream(path2);
        byte[] bytes3 = inputStream3.readAllBytes();
        String content3 = new String(bytes3);
        inputStream.close();

        System.out.println(content3);

        Path myFile = Path.of("C:/Example/java_junior/src/main/java/lesson3/myFile.txt");
        Files.createFile(myFile);

    }
}
