// Importaciones necesarias
import java.util.LinkedList;
import java.util.Queue;

// Clase base para arbol binario de busqueda
public class BSTree<E extends Comparable<E>> {
    // Raiz del arbol
    protected Node<E> root;

    // Constructor
    public BSTree() {
        // Inicializa arbol vacio
        this.root = null;
    }

    // Metodo para insertar elemento
    public void insert(E data) {
        // Llama al metodo recursivo
        root = insert(root, data);
    }

    // Metodo recursivo para insertar
    private Node<E> insert(Node<E> node, E data) {
        // Si nodo es nulo, crea nuevo nodo
        if (node == null) {
            return new Node<>(data);
        }

        // Compara dato con nodo actual
        int cmp = data.compareTo(node.data);
        
        // Inserta en subarbol izquierdo si es menor
        if (cmp < 0) {
            node.left = insert(node.left, data);
        } 
        // Inserta en subarbol derecho si es mayor
        else if (cmp > 0) {
            node.right = insert(node.right, data);
        }
        
        // Retorna nodo modificado
        return node;
    }

    // Metodo para buscar elemento
    public boolean search(E data) {
        // Llama al metodo recursivo
        return search(root, data);
    }

    // Metodo recursivo para buscar
    private boolean search(Node<E> node, E data) {
        // Si nodo es nulo, no encontrado
        if (node == null) {
            return false;
        }

        // Compara dato con nodo actual
        int cmp = data.compareTo(node.data);
        
        // Busca en subarbol izquierdo si es menor
        if (cmp < 0) {
            return search(node.left, data);
        } 
        // Busca en subarbol derecho si es mayor
        else if (cmp > 0) {
            return search(node.right, data);
        } 
        // Retorna true si encontrado
        else {
            return true;
        }
    }

    // Metodo para eliminar elemento
    public void remove(E data) {
        // Llama al metodo recursivo
        root = remove(root, data);
    }

    // Metodo recursivo para eliminar
    private Node<E> remove(Node<E> node, E data) {
        // Si nodo es nulo, no hay nada que eliminar
        if (node == null) {
            return null;
        }

        // Compara dato con nodo actual
        int cmp = data.compareTo(node.data);
        
        // Busca en subarbol izquierdo si es menor
        if (cmp < 0) {
            node.left = remove(node.left, data);
        } 
        // Busca en subarbol derecho si es mayor
        else if (cmp > 0) {
            node.right = remove(node.right, data);
        } 
        // Si encontrado, procede a eliminar
        else {
            // Caso 1: Nodo con un hijo o sin hijos
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            // Caso 2: Nodo con dos hijos
            // Encuentra sucesor inorden
            Node<E> temp = minValueNode(node.right);
            // Copia dato del sucesor
            node.data = temp.data;
            // Elimina el sucesor
            node.right = remove(node.right, temp.data);
        }
        return node;
    }

    // Metodo auxiliar para encontrar nodo minimo
    private Node<E> minValueNode(Node<E> node) {
        Node<E> current = node;
        // Recorre hasta el nodo mas izquierdo
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Metodo para obtener altura del arbol
    public int height() {
        // Llama al metodo recursivo
        return height(root);
    }

    // Metodo recursivo para calcular altura
    private int height(Node<E> node) {
        // Si nodo es nulo, altura -1
        if (node == null) {
            return -1;
        }
        // Calcula altura como 1 + max de subarboles
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // Metodo para imprimir inorden
    public void inOrder() {
        // Llama al metodo recursivo
        inOrder(root);
        System.out.println();
    }

    // Metodo recursivo para inorden
    private void inOrder(Node<E> node) {
        if (node != null) {
            // Recorre subarbol izquierdo
            inOrder(node.left);
            // Imprime nodo actual
            System.out.print(node.data + " ");
            // Recorre subarbol derecho
            inOrder(node.right);
        }
    }

    // Metodo para imprimir arbol jerarquico
    public void printTree() {
        // Llama al metodo recursivo
        printTree(root, 0);
    }

    // Metodo recursivo para imprimir arbol
    private void printTree(Node<E> node, int level) {
        // Si nodo es nulo, termina recursion
        if (node == null) {
            return;
        }

        // Primero subarbol derecho
        printTree(node.right, level + 1);
        
        // Imprime espacios para nivel
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        // Imprime nodo actual
        System.out.println(node.data);
        
        // Luego subarbol izquierdo
        printTree(node.left, level + 1);
    }
}
