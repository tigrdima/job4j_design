package ru.job4j.collection;

import org.w3c.dom.Node;

import java.util.*;

public class SimpleLinkedList<E> implements ListLl<E> {
    private int modCount;
    private int size = 0;
    private Node<E> firstElement;
    private Node<E> lastElement;

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        public Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

    }

    @Override
    public void add(E value) {
        final Node<E> last = lastElement;
        final Node<E> newNode = new Node<>(last, value, null);
        lastElement = newNode;
        if (last == null) {
            firstElement = newNode;
        } else {
            last.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = firstElement;

        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<>() {
            int expectedModCount = modCount;
            SimpleLinkedList.Node<E> rsl = firstElement;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return rsl != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E item = rsl.item;
                rsl = rsl.next;
                return item;
            }
        };
    }
}
