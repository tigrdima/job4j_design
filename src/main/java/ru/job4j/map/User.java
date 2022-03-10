package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children) {
        this.name = name;
        this.children = children;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday
                + '}';
    }

    public static void main(String[] args) {
        User user1 = new User("Dima", 2);
        User user2 = new User("Dima", 2);
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());

            System.out.println(map);
        }
}
