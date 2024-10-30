package controller.tda.stack;

import controller.tda.list.LinkedList;
import controller.tda.list.ListEmptyException;

public class Stack<E> extends LinkedList<E> {
    private Integer tope;

    public Stack(Integer tope) {
        this.tope = tope;
    }

    public Boolean isFull() {
        return getSize().intValue() >= tope.intValue();
    }

    public void push(E info) throws ListEmptyException, FullStackException {
        if (isFull()) {
            throw new FullStackException("STACK FULL");
        } else {
            add(info, 0);

        }

    }

    public E pop() throws ListEmptyException {

        E info = extractFirs();
        return info;

    }

}