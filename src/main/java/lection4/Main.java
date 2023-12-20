package lection4;


import java.sql.*;

public class Main {
    public static void main(String[] args) {

//        Package pkg = org.h2.Driver.class.getPackage();
//        String version = pkg.getImplementationVersion();
//        System.out.println("Версия JDBC-драйвера H2: " + version);

        try (Connection connection = DriverManager.getConnection(
                "jdbc:h2:mem:database.db")) {

            prepareTables(connection);
            insertData(connection);
            selectData(connection);
            System.out.println();
            updateData(connection);
            deleteData(connection);
            selectData(connection);

            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id, name from users where name = ?"
            )) {
                preparedStatement.setString(1, "Eugene #1");
                int counter = 0;
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");

                    System.out.println("[" + counter + "] id = " + id + ", name = " + name);
                }
            };
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void prepareTables(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                    create table users (
                    id bigint,
                    name varchar(255)
                    )
                    """);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertData(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            int updateRows = statement.executeUpdate("""
                    insert into users(id, name) 
                    values(1, 'Eugene #1'),
                        (2, 'Eugene #2'),
                        (3, 'Eugene #3'),
                        (4, 'Eugene #4')
                    """);
            System.out.println(updateRows);
//            statement.execute("insert into users(id, name) values(1, 'Eugene #1')");
//            statement.execute("insert into users(id, name) values(2, 'Eugene #2')");
//            statement.execute("insert into users(id, name) values(3, 'Eugene #3')");
//            statement.execute("insert into users(id, name) values(4, 'Eugene #4')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateData(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            int updateRows = statement.executeUpdate("""
                    update users set name = 'unknown' where id > 3 
                    """);
//            System.out.println(updateRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteData(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            int updateRows = statement.executeUpdate("""
                    delete from users where id > 3 
                    """);
//            System.out.println(updateRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void selectData(Connection connection) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select id, name from users where id > 2")) {
            int counter = 0;

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                System.out.println("[" + counter + "] id = " + id + ", name = " + name);
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
