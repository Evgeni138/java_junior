package lesson5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client3 {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", Server2.PORT);
        AtomicBoolean isKicked = new AtomicBoolean(false);

        // Чтение
        Thread readThread = new Thread(() -> {
            try (Scanner input = new Scanner(client.getInputStream())) {
                while (!isKicked.get() && input.hasNextLine()) {
                    String serverResponse = input.nextLine();
                    System.out.println(serverResponse);

                    if (serverResponse.equals("Вы были отключены администратором.")) {
                        isKicked.set(true);
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Отключились");
            } finally {
                if (isKicked.get()) {
                    System.exit(0); // Выход из программы
                }
            }
        });
        readThread.start();

        // Запись
        try (PrintWriter output = new PrintWriter(client.getOutputStream(), true);
             Scanner consoleScanner = new Scanner(System.in)) {
            while (!isKicked.get()) {
                String consoleInput = consoleScanner.nextLine();
                output.println(consoleInput);
                if (Objects.equals("q", consoleInput)) {
                    isKicked.set(true);
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            readThread.interrupt(); // Завершение потока чтения
        }
    }
}
