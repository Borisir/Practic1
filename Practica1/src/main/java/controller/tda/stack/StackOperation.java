package controller.tda.stack;

import controller.tda.list.ListEmptyException;

public class StackOperation<E> {
    private Stack<E> stack;

    public StackOperation(Integer length) {
        this.stack = new Stack<>(length);
    }

    public void push(E info) throws ListEmptyException, FullStackException {
        stack.push(info);
    }

    public E pop() throws ListEmptyException {
        return stack.pop();

    }

    public Integer lenght() {
        return stack.getSize();
    }

    public Boolean isFull() {
        return stack.isFull();
    }

    public void print() {
        System.out.println("STACK");
        System.out.println(stack.toString() + "\n");

    }

}
