package lesson3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FlatMapEx {
    public static void main(String[] args) {
        List<Departments> departments = new ArrayList<>();

        List<Employee> list = departments.stream()
                .map(Departments::getEmployees)
                .flatMap(Collection::stream)
                .toList();
    }

    static class Departments {
        private List<Employee> employees;

        public List<Employee> getEmployees() {
            return employees;
        }
    }

    static class Employee {

    }
}
