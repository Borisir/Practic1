package controller.tda.stack;

public class FullStackException extends Exception {

    /**
     * Creates a new instance of <code>FullStackException</code> without detail
     * message.
     */
    public FullStackException() {
    }

    /**
     * Constructs an instance of <code>FullStackException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FullStackException(String msg) {
        super(msg);
    }
}