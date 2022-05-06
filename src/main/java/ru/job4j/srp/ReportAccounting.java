package ru.job4j.srp;

import java.util.function.Predicate;

public class ReportAccounting implements Report {
    public static final double RATE = 75.6;
    private final Store store;

    public ReportAccounting(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String ln = System.lineSeparator();
        StringBuilder text = new StringBuilder();

        text.append("Name; Hired; Fired; Salary(EUR);").append(ln);
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append((int) (employee.getSalary() / RATE)).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
