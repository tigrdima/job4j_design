package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> findByParent = findBy(parent);

        if (findByParent.isPresent() && findBy(child).isEmpty()) {
            Node<E> parents = findByParent.get();
            parents.children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> predicateFindBy = s -> s.value.equals(value);
        return findByPredicate(predicateFindBy);
    }

    @Override
    public boolean isBinary() {
        Predicate<Node<E>> predicateIsBinary = s -> s.children.size() > 2;
        return findByPredicate(predicateIsBinary).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
