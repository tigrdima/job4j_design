package ru.job4j.lsperror;

public class Lsp3 {

    public static Force power() {
        return new Man2();
    }

    public static void main(String[] args) {
        System.out.println(power().getLiftWeight(80));
    }
}

class Force {
    public String rsl = "Слабый человек";

    public String getLiftWeight(int weight) {
       return weight == 100 ?  "Сильный человек" :  "Слабый человек";
    }
}

class Man extends Force {
    @Override
    public String getLiftWeight(int weight) {
        return weight > 60 && weight <= 100 ?  "Сильный человек" :  "Слабый человек";
    }
}

class Man2 extends Force {
    @Override
    public String getLiftWeight(int weight) {
        return weight > 100 ?  "Сильный человек" :  "Слабый человек";
    }

}

