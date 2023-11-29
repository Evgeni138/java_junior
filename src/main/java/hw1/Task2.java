package hw1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * 2. Создать класс Employee (Сотрудник) с полями: String name, int age, double salary, String department
 * 2.1 Создать список из 10-20 сотрудников
 * 2.2 Вывести список всех различных отделов (department) по списку сотрудников
 * 2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
 * 2.4 * Из списка сотрудников с помощью стрима создать Map<String, List<Employee>> с отделами и
 * сотрудниками внутри отдела
 * 2.5 * Из списока сорудников с помощью стрима создать Map<String, Double> с отделами и средней
 * зарплатой внутри отдела
 */
public class Task2 {
    public static void main(String[] args) {

        Employee employee1 = new Employee("Inna", 20, 5000, "Accounting");
        Employee employee2 = new Employee("Anna", 23, 4000, "Commercial");
        Employee employee3 = new Employee("Olga", 32, 55000, "Planned");
        Employee employee4 = new Employee("Ira", 31, 66600, "Accounting");
        Employee employee5 = new Employee("Oksana", 19, 77000, "Commercial");
        Employee employee6 = new Employee("Kate", 24, 57000, "Commercial");
        Employee employee7 = new Employee("Zoya", 20, 43000, "Planned");
        Employee employee8 = new Employee("Tanya", 21, 44000, "Accounting");
        Employee employee9 = new Employee("Zina", 27, 47500, "Planned");
        Employee employee10 = new Employee("Valya", 29, 62000, "Commercial");

        List<Employee> employees = new ArrayList<>(List.of(employee1, employee2, employee3,
                employee4, employee5, employee6, employee7, employee8, employee9, employee10));

        List<String> uniqueDepartments = getUniqueDepartments(employees);
        uniqueDepartments.forEach(System.out::println);

        conversionSalary(employees);

        employees.forEach(System.out::println);

        // Вывод Map<String, List<Employee>>
        Map<String, List<Employee>> employeesByDepartment = sortByDepartments(employees);
        employeesByDepartment.forEach((department, employeeList) -> {
            System.out.println("Department: " + department);
            System.out.println("Employees: " + employeeList);
        });

        Map<String, Double> averageSalary = averageSalaryByDepartment(employees);
        averageSalary.forEach((department, salary) -> {
            System.out.println("Department: " + department);
            System.out.println("Average salary: " + salary);
        });

    }
    //Вывести список всех различных отделов (department) по списку сотрудников
    public static List<String> getUniqueDepartments(List<Employee> employees) {
        return employees.stream().map(Employee::getDepartment).distinct().collect(Collectors.toList());
    }

    //Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
    public static void conversionSalary(List<Employee> employees) {
        employees.stream().filter(it -> it.getSalary() < 10000).forEach(it -> it.setSalary(it.getSalary() * 1.2));
    }

    //Из списка сотрудников с помощью стрима создать Map<String, List<Employee>> с отделами и
    // сотрудниками внутри отдела
    public static Map<String, List<Employee>> sortByDepartments (List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    //Из списока сорудников с помощью стрима создать Map<String, Double> с отделами
    // и средней зарплатой внутри отдела
    public static Map<String, Double> averageSalaryByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));
    }
    public static class Employee {
        private String name;
        private int age;
        private double salary;
        private String department;

        public Employee(String name, int age, double salary, String department) {
            this.name = name;
            this.age = age;
            this.salary = salary;
            this.department = department;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return String.format("Employee %s from %s has %s salary", this.name, this.department, this.salary);
        }
    }
}