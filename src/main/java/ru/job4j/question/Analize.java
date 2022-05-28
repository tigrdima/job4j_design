package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
       int added;
       int changed = 0;
       int deleted = 0;

       Map<Integer, User> cur = new HashMap<>();

      for (User user : current) {
          cur.put(user.getId(), user);
      }

       for (User u : previous) {
           if (!cur.containsKey(u.getId())) {
               deleted++;
           } else if (!cur.get(u.getId()).equals(u)) {
               changed++;
           }
       }
        added = current.size() - previous.size() + deleted;

        return new Info(added, changed, deleted);
    }
}
