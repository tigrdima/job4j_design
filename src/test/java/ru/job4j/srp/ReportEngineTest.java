package ru.job4j.srp;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenReportProgrammers() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 10000.5);
        Employee worker2 = new Employee("Vova", now, now, 9056.4);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportProgrammers(store);
        String ln = System.lineSeparator();

        StringBuilder expect = new StringBuilder();
        expect.append("<html>").append(ln).append("<body>").append(ln);
        expect.append("<p>").append("Name; Hired; Fired; Salary").append("</p>").append(ln);
        expect.append("<p>").append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";").append("</p>").append(ln);
        expect.append("<p>").append(worker2.getName()).append(";")
                .append(worker2.getHired()).append(";")
                .append(worker2.getFired()).append(";")
                .append(worker2.getSalary()).append(";").append("</p>").append(ln);
        expect.append("</body>").append(ln).append("</html>");

        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenReportAccounting() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 10000.45);
        Employee worker2 = new Employee("Vova", now, now, 9056.40);
        store.add(worker);
        store.add(worker2);

        Report engine = new ReportAccounting(store);
        String ln = System.lineSeparator();

        StringBuilder expect = new StringBuilder();
        expect.append("Name; Hired; Fired; Salary(EUR);").append(ln);
        expect.append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append((int) (worker.getSalary() / ReportAccounting.RATE)).append(";")
                .append(ln);
        expect.append(worker2.getName()).append(";")
                .append(worker2.getHired()).append(";")
                .append(worker2.getFired()).append(";")
                .append((int) (worker2.getSalary() / ReportAccounting.RATE)).append(";")
                .append(ln);

        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenReportHr() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();

        Employee worker = new Employee("Ivan", now, now, 10000.45);
        Employee worker2 = new Employee("Vova", now, now, 9056.40);
        Employee worker3 = new Employee("Kolya", now, now, 20400.20);
            store.add(worker);
            store.add(worker2);
            store.add(worker3);
        Report engine = new ReportHr(store);
        String ln = System.lineSeparator();

        StringBuilder expect = new StringBuilder();
        expect.append("Name; Salary;").append(ln);
        expect.append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(ln);
        expect.append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(ln);
        expect.append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(ln);

        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenReportJson() {
        MemStore store = new MemStore();
        Calendar now = new GregorianCalendar(2020, Calendar.NOVEMBER, 10);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss X");
        String date = formatter.format(now.getTime());
        Employee worker = new Employee("Ivan", now, now, 10000.4);
        Employee worker2 = new Employee("Vova", now, now, 9056.3);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportJson(store);
        String temple = "{\"name\":\"%s\",\"hired\":\"%s\",\"fired\":\"%s\",\"salary\":%.1f}";

        StringBuilder expect = new StringBuilder();
        expect.append("{\"employees\":[");
        expect.append(String.format(Locale.ROOT, temple + ",", worker.getName(), date, date, worker.getSalary()));
        expect.append(String.format(Locale.ROOT, temple, worker2.getName(), date, date, worker2.getSalary()));
        expect.append("]}");

        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenReportXML() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        now.set(2020, Calendar.NOVEMBER, 10);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss X");
        String date = format.format(now.getTime());
        Employee worker = new Employee("Ivan", now, now, 10000.4);
        Employee worker2 = new Employee("Vova", now, now, 9056.3);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportXML(store);
        StringBuilder expect = new StringBuilder();
        String rsl = engine.generate(em -> true).replaceAll("\\n\\s*", "");
        String template = "<employee name=\"%s\" hired=\"%s\" fired=\"%s\" salary=\"%.1f\"/>";

        expect.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append("<employees>");
        expect.append(String.format(Locale.ROOT, template, worker.getName(), date, date, worker.getSalary()));
        expect.append(String.format(Locale.ROOT, template, worker2.getName(), date, date, worker2.getSalary()));
        expect.append("</employees>");

        assertThat(rsl, is(expect.toString()));
    }
}