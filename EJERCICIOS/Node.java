// Clase base para nodos de arbol binario
public class Node<E> {
    // Dato almacenado en el nodo
    protected E data;
    
    // Referencia al hijo izquierdo
    protected Node<E> left;
    
    // Referencia al hijo derecho
    protected Node<E> right;

    // Constructor que inicializa el nodo
    public Node(E data) {
        // Asigna el dato al nodo
        this.data = data;
        // Inicializa hijos como nulos
        this.left = null;
        this.right = null;
    }

    // Metodo para convertir nodo a String
    @Override
    public String toString() {
        // Retorna representacion String del dato
        return data.toString();
    }
}
