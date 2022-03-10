package ru.job4j.map;

import java.util.*;

import static java.util.Calendar.*;
import static java.util.Objects.hash;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && name.equals(user.name) && birthday.equals(user.birthday);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static void main(String[] args) {
        GregorianCalendar birthdayUser1 = new GregorianCalendar(1979, JANUARY, 9, 0, 0, MILLISECOND);
        GregorianCalendar birthdayUser2 = new GregorianCalendar(1979, JANUARY, 9, 0, 0, MILLISECOND);

        User user1 = new User("Dima", 2, birthdayUser1);
        User user2 = new User("Dima", 2, birthdayUser2);

        Map<User, Object> map = new HashMap<>();

        map.put(user1, new Object());
        map.put(user2, new Object());

        System.out.println(user1.hashCode() + " " + user2.hashCode());
        System.out.println(map);

    }
}

