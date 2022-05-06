package ru.job4j.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.function.Predicate;

public class ReportJson implements Report {
    private static final GsonBuilder JSONBUILDER = new GsonBuilder();
    private final Store store;

    public ReportJson(Store store) {
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) {
        Employees employees = new Employees();
        employees.setEmployees(store.findBy(filter));
        JSONBUILDER.registerTypeAdapter(Calendar.class, new CalendarAdapterJson());
        JSONBUILDER.registerTypeAdapter(GregorianCalendar.class, new CalendarAdapterJson());
        Gson gson = JSONBUILDER.create();
        return gson.toJson(employees);
    }
}
