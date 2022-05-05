package ru.job4j.srp;

import java.util.List;
import java.util.function.Predicate;

public class ReportHr implements Report {
    private final Store store;

    public ReportHr(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String ln = System.lineSeparator();

        List<Employee> employees = store.findBy(filter);
        employees.sort((o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary()));

        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;").append(ln);
        for (Employee employee : employees) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(ln);
        }
        return text.toString();
    }
}
