package ru.job4j.srperror;

public class Srp2 {
    private String name;
    private int age;
    private int idAccount;
    private String account;
    private int balanceAccount;
    private String car;

    public Srp2(String name, int age, int idAccount, String account, int balanceAccount, String car) {
        this.name = name;
        this.age = age;
        this.idAccount = idAccount;
        this.account = account;
        this.balanceAccount = balanceAccount;
        this.car = car;
    }

    public void output(int idAccount, String account, int balanceAccount) {
        System.out.printf("%d,%s,%d", idAccount, account, balanceAccount);
    }
}
