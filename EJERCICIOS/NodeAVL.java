// Clase para nodos AVL que hereda de Node
public class NodeAVL<E> extends Node<E> {
    // Factor de balance del nodo
    protected int bf;

    // Constructor
    public NodeAVL(E data) {
        // Llama constructor de Node
        super(data);
        // Inicializa factor de balance en 0
        this.bf = 0;
    }

    // Metodo para convertir nodo a String
    @Override
    public String toString() {
        // Retorna dato y factor de balance
        return super.toString() + " (bf=" + bf + ")";
    }
}
