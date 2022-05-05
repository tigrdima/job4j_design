package ru.job4j.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportJson implements Report {
    static final Gson JSONCAR = new GsonBuilder().create();
    private final Store store;

    public ReportJson(Store store) {
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        return JSONCAR.toJson(employees);
        }

    public static void main(String[] args) {
        MemStore store = new MemStore();
        Calendar now = new GregorianCalendar(2020, Calendar.NOVEMBER, 10);

        store.add(new Employee("Ivan", now, now, 10000.45));
          store.add(new Employee("Vova", now, now, 9056.40));

        Report report = new ReportJson(store);
        System.out.println(report.generate(em -> true));

    }
}
