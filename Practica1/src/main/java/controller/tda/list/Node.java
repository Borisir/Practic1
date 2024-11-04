package controller.tda.list;
// " T" DATO GENERICO NORMAL
// "K,V"" PARA DICCIONARIOS"

public class Node <E>{
    private E info;
    private Node <E> next;
    
// Getter para info
public E getInfo() {
    return info; // Devuelve la información del nodo
}

// Setter para info
public void setInfo(E info) {
    this.info = info; // Establece la información del nodo
}

// Getter para next
public Node<E> getNext() {
    return next; // Devuelve la referencia al siguiente nodo
}

// Setter para next
public void setNext(Node<E> next) {
    this.next = next; // Establece la referencia al siguiente nodo
}

public Node(E info){
    this.info = info;
    this.next = null;
}
public Node(E info, Node<E> next){
    this.info = info;
    this.next = next;
}
}
