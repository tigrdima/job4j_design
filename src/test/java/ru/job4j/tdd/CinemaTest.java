package ru.job4j.tdd;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertNull;

public class CinemaTest {

    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        MatcherAssert.assertThat(ticket, Matchers.is(new Ticket3D()));
    }

    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        MatcherAssert.assertThat(sessions, Matchers.is(List.of(new Session3D())));
    }

    @Test
    public void whenNotFind() {
        Cinema cinema = new Cinema3D();
        List<Session> sessions = cinema.find(session -> false);
        MatcherAssert.assertThat(sessions, Matchers.is(List.of(new Session3D())));
    }

    @Test
    public void whenSeatIsTaken() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 2, date);
        assertNull(ticket);
        Ticket ticket2 = cinema.buy(account, 1, 6, date);
        MatcherAssert.assertThat(ticket2, Matchers.is(new Ticket3D()));
    }
}
