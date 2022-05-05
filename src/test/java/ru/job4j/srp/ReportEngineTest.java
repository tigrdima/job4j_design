package ru.job4j.srp;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.serialization.json.Car;

import javax.xml.bind.*;
import java.io.StringReader;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
        Employee worker = new Employee("Ivan", now, now, 10000.45);
        Employee worker2 = new Employee("Vova", now, now, 9056.40);
        Employee worker3 = new Employee("Kolya", now, now, 20400.20);
        store.add(worker);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportProgrammers(store);
        String ln = System.lineSeparator();
        StringBuilder expect = new StringBuilder();
        expect.append("<html>").append(ln).append("<body>").append(ln);
        expect.append("<p>").append("Name; Hired; Fired; Salary").append("</p>").append(ln);
        for (Employee em : store.findBy(em -> true)) {
            expect.append("<p>").append(em.getName()).append(";")
                    .append(em.getHired()).append(";")
                    .append(em.getFired()).append(";")
                    .append(em.getSalary()).append(";").append("</p>").append(ln);
        }
        expect.append("</body>").append(ln).append("</html>");

        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenReportAccounting() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 10000.45);
        Employee worker2 = new Employee("Vova", now, now, 9056.40);
        Employee worker3 = new Employee("Kolya", now, now, 20400.20);
        store.add(worker);
        store.add(worker2);
        store.add(worker3);
        double rate = 75.6;
        Report engine = new ReportAccounting(store);
        String ln = System.lineSeparator();
        StringBuilder expect = new StringBuilder();
        expect.append("Name; Hired; Fired; Salary(EUR);").append(ln);
        for (Employee em : store.findBy(em -> true)) {
            expect.append(em.getName()).append(";")
                    .append(em.getHired()).append(";")
                    .append(em.getFired()).append(";")
                    .append((int) (em.getSalary() / rate)).append(";")
                    .append(ln);
        }
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
        List<Employee> employees = store.findBy(em -> true);
        employees.sort((o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary()));
        String ln = System.lineSeparator();
        StringBuilder expect = new StringBuilder();
        expect.append("Name; Salary;").append(ln);
        for (Employee em : employees) {
            expect.append(em.getName()).append(";")
                    .append(em.getSalary()).append(";")
                    .append(ln);
        }
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Ignore
    @Test
    public void whenReportJson() {
        MemStore store = new MemStore();
        Calendar now = new GregorianCalendar(2020, Calendar.NOVEMBER, 10);
        Employee worker = new Employee("Ivan", now, now, 10000.45);
        Employee worker2 = new Employee("Vova", now, now, 9056.40);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportJson(store);

        String expect = "["
                + "{"
                + "\"name\":\"Ivan\","
                + "\"hired\":"
                + "{"
                + "\"year\":2020,"
                + "\"month\":10,"
                + "\"dayOfMonth\":10,"
                + "\"hourOfDay\":0,"
                + "\"minute\":0,"
                + "\"second\":0"
                + "},"
                + "\"fired\":"
                + "{"
                + "\"year\":2020,"
                + "\"month\":10,"
                + "\"dayOfMonth\":10,"
                + "\"hourOfDay\":0,"
                + "\"minute\":0,"
                + "\"second\":0"
                + "},"
                + "\"salary\":10000.45"
                + "},"
                + "{"
                + "\"name\":\"Vova\","
                + "\"hired\":"
                + "{"
                + "\"year\":2020,"
                + "\"month\":10,"
                + "\"dayOfMonth\":10,"
                + "\"hourOfDay\":0,"
                + "\"minute\":0,"
                + "\"second\":0"
                + "},"
                + "\"fired\":"
                + "{"
                + "\"year\":2020,"
                + "\"month\":10,"
                + "\"dayOfMonth\":10,"
                + "\"hourOfDay\":0,"
                + "\"minute\":0,"
                + "\"second\":0"
                + "},"
                + "\"salary\":9056.4"
                + "}"
                + "]";

        assertThat(engine.generate(em -> true), is(expect));
    }

    @Ignore
    @Test
    public void whenReportXML() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = new GregorianCalendar(2020, Calendar.NOVEMBER, 10);
        Employee worker = new Employee("Ivan", now, now, 10000.45);
        Employee worker2 = new Employee("Vova", now, now, 9056.40);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportJson(store);
        String xml = engine.generate(em -> true);

        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringReader reader = new StringReader(xml)) {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Employees employees = (Employees) unmarshaller.unmarshal(reader);
            List<Employee> expect = employees.getEmployees();

            assertThat(expect.get(0), is(worker));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}