package ru.job4j.srperror;

public class Question {
   private static String answer;

    public static void answer(String question) {
        if (question.equals("A")) {
            answer = "a";
        } else if (question.equals("B")) {
            answer = "b";
        } else if (question.equals("C")) {
            answer = "c";
        } else {
            answer = "d";
        }
    }

    public static void main(String[] args) {
         answer("A");
        System.out.println(answer);
    }
}
