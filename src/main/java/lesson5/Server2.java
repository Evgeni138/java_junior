package lesson5;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server2 {
    public static final int PORT = 8181;
    private static long clientCounter = 1L;
    private static Map<Long, SocketWrapper> clients = new HashMap<>();
    private static Set<Long> admins = new HashSet<>();

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту: " + PORT);
            while (true) {
                final Socket client = server.accept();
                final long clientId = clientCounter++;

                SocketWrapper wrapper = new SocketWrapper(clientId, client);

                System.out.println("Подключился новый клиент: [" + wrapper + "]");

                clients.put(clientId, wrapper);

                new Thread(() -> {
                    try (Scanner input = wrapper.getInput();
                         PrintWriter output = wrapper.getOutput()) {
                        output.println("Подключение успешно установлено. Список всех клиентов: " + clients);

                        while (true) {
                            String clientInput = input.nextLine();

                            if (Objects.equals("q", clientInput)) {
                                clients.remove(clientId);
                                clients.values().forEach(it -> it.getOutput()
                                        .println("Клиент [" + clientId +"] отключился"));
                                wrapper.close(); // Закрыть сессию клиента
                                break;
                            }

                            if (clientInput.startsWith("/admin")) {
                                boolean isAdmin = clients.values().stream()
                                        .anyMatch(SocketWrapper::isAdmin);
                                if (!isAdmin) {
                                    wrapper.setAdmin(true);
                                    admins.add(clientId);
                                    output.println("Вы стали администратором.");
                                    clients.values().stream()
                                            .filter(it -> it.getId() != clientId)
                                            .forEach(it -> it.getOutput()
                                                    .println("клиент [" + clientId + "] стал админом"));
                                } else {
                                    wrapper.getOutput().println("Админ уже назначен!");
                                }
                            }else if (clientInput.charAt(0) == '@') {
                                List<String> splitMsg = Arrays.asList(clientInput.split(" "));
                                if (!splitMsg.get(0).isEmpty()) {
                                    long destinationId = Long.parseLong(splitMsg.get(0).substring(1));
                                    SocketWrapper destination = clients.get(destinationId);

                                    destination.getOutput().println(clientInput + " from " + clientId);
                                }

                            } else {
                                clients.values().stream()
                                        .filter(it -> it.getId() != clientId)
                                        .forEach(it -> it.getOutput().println(clientInput + " from " + clientId));
                            }

                            if (admins.contains(wrapper.getId()) && clientInput.startsWith("/kick")) {
                                try {
                                    long kickedClientId = Long.parseLong(clientInput.substring(6));
                                    SocketWrapper kickedClient = clients.get(kickedClientId);
                                    if (kickedClient != null) {
                                        kickedClient.getOutput().println("Вы были отключены администратором.");
                                        kickedClient.close(); // Закрыть сессию кикнутого клиента
                                        clients.remove(kickedClientId); // Удаление клиента из списка
                                    } else {
                                        output.println("Клиент с таким номером не найден.");
                                    }
                                } catch (NumberFormatException e) {
                                    output.println("Неверный формат команды кика.");
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Getter
    @Setter
    static class SocketWrapper implements AutoCloseable {
        private final long id;
        private final Socket socket;
        private final Scanner input;
        private final PrintWriter output;
        private boolean isAdmin;

        @Override
        public void close() throws IOException {
            socket.close();
        }

        @Override
        public String toString() {
            return String.format("%s", socket.getInetAddress().toString());
        }

        public SocketWrapper(long id, Socket socket) throws IOException {
            this.id = id;
            this.socket = socket;
            this.input = new Scanner(socket.getInputStream());
            this.output = new PrintWriter(socket.getOutputStream(), true);
        }
    }
}
