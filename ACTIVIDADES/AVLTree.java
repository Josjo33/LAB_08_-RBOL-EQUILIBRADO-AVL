public class AVLTree<E extends Comparable<E>> {

    private Node<E> root;  // Nodo raíz

    // Clase Node que representa un nodo genérico del árbol
    private static class Node<E> {
        E data;  // Dato del nodo
        Node<E> left, right;  // Referencias a los hijos izquierdo y derecho
        int bf;  // Factor de equilibrio

        // Constructor del nodo
        public Node(E data) {
            this.data = data;
            this.left = this.right = null;
            this.bf = 0;  // Inicializamos el factor de equilibrio en 0
        }
    }

    // Inserción de un elemento
    public void insert(E data) {
        root = insert(data, root);
    }

    // Método recursivo de inserción
    private Node<E> insert(E data, Node<E> node) {
        if (node == null) {
            return new Node<>(data);  // Creamos un nuevo nodo
        }

        int comparison = data.compareTo(node.data);
        if (comparison < 0) {
            node.left = insert(data, node.left);  // Insertamos en el subárbol izquierdo
        } else if (comparison > 0) {
            node.right = insert(data, node.right);  // Insertamos en el subárbol derecho
        } else {
            return node;  // No permitimos duplicados
        }

        // Después de la inserción, actualizamos el factor de balance y balanceamos el árbol
        updateBalance(node);
        return balance(node);
    }

    // Actualiza el factor de equilibrio de un nodo
    private void updateBalance(Node<E> node) {
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        node.bf = rightHeight - leftHeight;  // Calculamos el factor de balance
    }

    // Calcula la altura de un subárbol
    private int height(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // Balancea el árbol
    private Node<E> balance(Node<E> node) {
        if (node.bf == 2) {  // Desbalanceo hacia la derecha
            if (node.right.bf < 0) {  // Rotación doble (RDR)
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);  // Rotación simple a la izquierda
        } else if (node.bf == -2) {  // Desbalanceo hacia la izquierda
            if (node.left.bf > 0) {  // Rotación doble (RDL)
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);  // Rotación simple a la derecha
        }
        return node;  // Devolvemos el nodo balanceado
    }

    // Rotación simple a la izquierda
    private Node<E> rotateLeft(Node<E> node) {
        Node<E> newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        updateBalance(node);
        updateBalance(newRoot);
        return newRoot;
    }

    // Rotación simple a la derecha
    private Node<E> rotateRight(Node<E> node) {
        Node<E> newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        updateBalance(node);
        updateBalance(newRoot);
        return newRoot;
    }

    // Método para imprimir el árbol (solo con fines de prueba)
    public void printTree() {
        printTree(root, "", true);
    }

    private void printTree(Node<E> node, String indent, boolean last) {
        if (node != null) {
            System.out.println(indent + "+- " + node.data + " (BF: " + node.bf + ")");
            indent += (last ? "   " : "|  ");
            printTree(node.left, indent, false);
            printTree(node.right, indent, true);
        }
    }
}
