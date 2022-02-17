package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    Node<T> tail;
    private int size;
    private  int index;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        size++;
        if (head == null) {
            head = node;
            return;
        }

        tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addFirst(T value) {
        Node<T> node = new Node<>(value, null);
        size++;
        if (head == null) {
            head = node;
            return;
        }
        node.next = head;
        head = node;
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
        if (size > 1) {
            Node<T> prev = null;
            Node<T> current = head;
            while (current != null) {
                Node<T> next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            head = prev;
            return true;
        }
        return false;
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
}
