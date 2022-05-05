package ru.job4j.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportXML implements Report {
    private final Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) {
        String xml = "";
        List<Employee> em = store.findBy(filter);

        try (StringWriter writer = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(new Employees(em), writer);
            xml = writer.getBuffer().toString();

        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return xml;
    }

    public static void main(String[] args) {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        store.add(new Employee("Ivan", now, now, 10000.45));
        store.add(new Employee("Vova", now, now, 9056.40));

        Report report = new ReportXML(store);
        System.out.println(report.generate(em -> true));
    }
}
