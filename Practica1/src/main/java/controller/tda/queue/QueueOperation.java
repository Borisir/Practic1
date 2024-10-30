package controller.tda.queue;


import controller.tda.list.ListEmptyException;
import controller.tda.stack.FullStackException;

public class QueueOperation<E> {
    private Queue<E> tail;

    public QueueOperation(Integer size) {
        this.tail = new Queue<>(size);
    }

    public void queue(E info) throws ListEmptyException, FullStackException {
        tail.queue(info);
    }

    public E dequeue() throws ListEmptyException {
        return tail.dequeue();

    }

    public Integer size() {
        return tail.getSize();
    }

    public Boolean isFull() {
        return tail.isFull();
    }

    public void print() {
        System.out.println("STACK");
        System.out.println(tail.toString() + "\n");

    }

}
