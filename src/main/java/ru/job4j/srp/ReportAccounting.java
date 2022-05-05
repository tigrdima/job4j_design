package ru.job4j.srp;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportAccounting implements Report {

    private final Store store;

    public ReportAccounting(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String ln = System.lineSeparator();
        double rate = 75.6;
        StringBuilder text = new StringBuilder();

        text.append("Name; Hired; Fired; Salary(EUR);").append(ln);
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append((int) (employee.getSalary() / rate)).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
