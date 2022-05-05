package ru.job4j.srp;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportProgrammers implements Report {
    private final Store store;

    public ReportProgrammers(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String ln = System.lineSeparator();
        StringBuilder text = new StringBuilder();
        text.append("<html>").append(ln).append("<body>").append(ln).append("<p>");
        text.append("Name; Hired; Fired; Salary").append("</p>").append(ln);
        for (Employee employee : store.findBy(filter)) {
            text.append("<p>").append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";").append("</p>")
                    .append(ln);
        }
        text.append("</body>").append(ln).append("</html>");
        return text.toString();
    }
}
