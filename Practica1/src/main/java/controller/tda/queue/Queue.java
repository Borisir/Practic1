package controller.tda.queue;

import controller.tda.list.LinkedList;
import controller.tda.list.ListEmptyException;
import controller.tda.stack.FullStackException;

public class Queue<E> extends LinkedList<E> {
    private Integer cima;

    public Queue(Integer tope) {
        this.cima = tope;
    }

    public Boolean isFull() {
        return getSize().intValue() >= cima.intValue();
    }

    public void queue(E info) throws ListEmptyException, FullStackException {
        if (isFull()) {
            throw new FullStackException("QUEUE FULL");
        } else {
            add(info);
        }
    }

    public E dequeue() throws ListEmptyException {

        E info = extractFirs();
        return info;

    }

}
