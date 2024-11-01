package controller.tda.queue;


public class Queue<E> {
    private QueueOperation <E> queueOperation;
    public Queue(Integer cant){
        this.queueOperation = new QueueOperation<>(cant);
    }
    public void queue(E dato) throws Exception {
        queueOperation.queue(dato);
    }
    public Integer getSize() {
        return this.queueOperation.getSize();
    }
    public void clear() {
        this.queueOperation.reset();
    }
    public Integer getTop() {
        return this.queueOperation.getTop();
    }
    public void print(){
        System.out.println("COLA");
        System.out.println(queueOperation.toString());
        System.out.println("*******");
    }
    public E dequeue() throws Exception {
        return queueOperation.dequeue();
    }
    

}
