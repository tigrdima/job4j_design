package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> prev;
    private int size;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);

        if (head == null) {
            head = node;
            return;
        }

        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        size++;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> node = head;
        T first = node.value;
        head = head.next;
        node.next = null;
        node.value = null;
        size--;
        return first;
    }

    public boolean revert() {
        boolean rsl = false;
        if (size > 1) {
            while (head != null) {
                Node<T> next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }
            head = prev;
            rsl = true;
        }
        return rsl;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ForwardLinked<Integer> a = new ForwardLinked<>();
        a.addFirst(1);
        a.addFirst(2);
        a.addFirst(3);

        for (Object integer : a) {
            System.out.println(integer);
        }
    }
}
