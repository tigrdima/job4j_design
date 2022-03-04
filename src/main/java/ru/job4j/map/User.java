package ru.job4j.map;

import java.util.*;

import static java.util.Objects.hash;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children) {
        this.name = name;
        this.children = children;
    }

    public static void main(String[] args) {
        User user1 = new User("Dima", 2);
        User user2 = new User("Dima", 2);
        Map<User, Object> map = new HashMap<>(16);
        map.put(user1, new Object());
        map.put(user2, new Object());

        for (Map.Entry<User, Object> m : map.entrySet()) {
            System.out.println((hash(m.getKey().hashCode()) & map.size() - 1) + " " + m.getKey().hashCode() + " " + m.getValue().toString());
        }
}
}