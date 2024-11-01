package controller.tda.queue;


import controller.tda.list.ListEmptyException;
import controller.exception.OverFlowException;
import controller.tda.list.LinkedList;

public class QueueOperation<E> extends LinkedList<E>{
    private Integer top;

    public QueueOperation(Integer top) {
        this.top = top;
    }

    public boolean verify () {
        return getSize().intValue() <= top.intValue();
    }

    public void queue (E dato) throws Exception {
        if (verify()) {
            add(dato, getSize());
        } else {
            throw new OverFlowException("Cola llena");
        }
    }

    public E dequeue() throws Exception {
        if (isEmpty()) {
            throw new ListEmptyException("Cola vacia");
        } else {
            return deleteFirst();
        }
        
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }
}
