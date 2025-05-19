// Importaciones necesarias
import java.util.LinkedList;
import java.util.Queue;

// Clase AVLTree que hereda de BSTree
public class AVLTree<E extends Comparable<E>> extends BSTree<E> {
    // Indicador de cambio de altura
    private boolean heightChanged;

    // Metodo para insertar en AVL
    @Override
    public void insert(E data) {
        // Inicializa indicador
        heightChanged = false;
        // Llama al metodo recursivo
        root = insert((NodeAVL<E>)root, data);
    }

    // Metodo recursivo para insertar
    private NodeAVL<E> insert(NodeAVL<E> node, E data) {
        // Si nodo es nulo, crea nuevo nodo
        if (node == null) {
            heightChanged = true;
            return new NodeAVL<>(data);
        }

        // Compara dato con nodo actual
        int cmp = data.compareTo(node.data);
        
        // Inserta en subarbol izquierdo
        if (cmp < 0) {
            node.left = insert((NodeAVL<E>)node.left, data);
            // Ajusta balance si hubo cambio
            if (heightChanged) {
                node.bf--;
                // Verifica si necesita balanceo
                if (node.bf == -2) {
                    node = balanceToRight(node);
                    heightChanged = false;
                } else if (node.bf == 0) {
                    heightChanged = false;
                }
            }
        } 
        // Inserta en subarbol derecho
        else if (cmp > 0) {
            node.right = insert((NodeAVL<E>)node.right, data);
            // Ajusta balance si hubo cambio
            if (heightChanged) {
                node.bf++;
                // Verifica si necesita balanceo
                if (node.bf == 2) {
                    node = balanceToLeft(node);
                    heightChanged = false;
                } else if (node.bf == 0) {
                    heightChanged = false;
                }
            }
        }
        return node;
    }

    // Metodo para balancear a izquierda
    private NodeAVL<E> balanceToLeft(NodeAVL<E> node) {
        NodeAVL<E> rightChild = (NodeAVL<E>)node.right;
        
        // Determina tipo de rotacion necesaria
        switch(rightChild.bf) {
            case 1:
                // Rotacion simple izquierda
                node.bf = 0;
                rightChild.bf = 0;
                node = rotateSL(node);
                break;
            case -1:
                // Rotacion doble derecha
                NodeAVL<E> grandChild = (NodeAVL<E>)rightChild.left;
                switch(grandChild.bf) {
                    case -1:
                        node.bf = 0;
                        rightChild.bf = 1;
                        break;
                    case 0:
                        node.bf = 0;
                        rightChild.bf = 0;
                        break;
                    case 1:
                        node.bf = -1;
                        rightChild.bf = 0;
                        break;
                }
                grandChild.bf = 0;
                node.right = rotateSR(rightChild);
                node = rotateSL(node);
        }
        return node;
    }

    // Metodo para balancear a derecha
    private NodeAVL<E> balanceToRight(NodeAVL<E> node) {
        NodeAVL<E> leftChild = (NodeAVL<E>)node.left;
        
        // Determina tipo de rotacion necesaria
        switch(leftChild.bf) {
            case -1:
                // Rotacion simple derecha
                node.bf = 0;
                leftChild.bf = 0;
                node = rotateSR(node);
                break;
            case 1:
                // Rotacion doble izquierda
                NodeAVL<E> grandChild = (NodeAVL<E>)leftChild.right;
                switch(grandChild.bf) {
                    case 1:
                        node.bf = 0;
                        leftChild.bf = -1;
                        break;
                    case 0:
                        node.bf = 0;
                        leftChild.bf = 0;
                        break;
                    case -1:
                        node.bf = 1;
                        leftChild.bf = 0;
                        break;
                }
                grandChild.bf = 0;
                node.left = rotateSL(leftChild);
                node = rotateSR(node);
        }
        return node;
    }

    // Metodo para rotacion simple izquierda
    private NodeAVL<E> rotateSL(NodeAVL<E> node) {
        NodeAVL<E> newRoot = (NodeAVL<E>)node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        return newRoot;
    }

    // Metodo para rotacion simple derecha
    private NodeAVL<E> rotateSR(NodeAVL<E> node) {
        NodeAVL<E> newRoot = (NodeAVL<E>)node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        return newRoot;
    }

    // Metodo para eliminar en AVL
    @Override
    public void remove(E data) {
        // Inicializa indicador
        heightChanged = false;
        // Llama al metodo recursivo
        root = remove((NodeAVL<E>)root, data);
    }

    // Metodo recursivo para eliminar
    private NodeAVL<E> remove(NodeAVL<E> node, E data) {
        // Si nodo es nulo, termina recursion
        if (node == null) {
            heightChanged = false;
            return null;
        }

        // Compara dato con nodo actual
        int cmp = data.compareTo(node.data);
        
        // Busca en subarbol izquierdo
        if (cmp < 0) {
            node.left = remove((NodeAVL<E>)node.left, data);
            // Ajusta balance si hubo cambio
            if (heightChanged) {
                node.bf++;
                // Verifica si necesita balanceo
                if (node.bf == 2) {
                    node = balanceToLeft(node);
                    heightChanged = node.bf != 0;
                } else if (node.bf == 0) {
                    heightChanged = true;
                } else {
                    heightChanged = false;
                }
            }
        } 
        // Busca en subarbol derecho
        else if (cmp > 0) {
            node.right = remove((NodeAVL<E>)node.right, data);
            // Ajusta balance si hubo cambio
            if (heightChanged) {
                node.bf--;
                // Verifica si necesita balanceo
                if (node.bf == -2) {
                    node = balanceToRight(node);
                    heightChanged = node.bf != 0;
                } else if (node.bf == 0) {
                    heightChanged = true;
                } else {
                    heightChanged = false;
                }
            }
        } 
        // Si encontrado, procede a eliminar
        else {
            // Caso 1: Nodo con un hijo o sin hijos
            if (node.left == null) {
                heightChanged = true;
                return (NodeAVL<E>)node.right;
            }
            if (node.right == null) {
                heightChanged = true;
                return (NodeAVL<E>)node.left;
            }
            
            // Caso 2: Nodo con dos hijos
            // Encuentra sucesor inorden
            NodeAVL<E> temp = (NodeAVL<E>)node.right;
            while (temp.left != null) {
                temp = (NodeAVL<E>)temp.left;
            }
            // Copia dato del sucesor
            node.data = temp.data;
            // Elimina el sucesor
            node.right = remove((NodeAVL<E>)node.right, temp.data);
            
            // Ajusta balance si hubo cambio
            if (heightChanged) {
                node.bf--;
                // Verifica si necesita balanceo
                if (node.bf == -2) {
                    node = balanceToRight(node);
                    heightChanged = node.bf != 0;
                } else if (node.bf == 0) {
                    heightChanged = true;
                } else {
                    heightChanged = false;
                }
            }
        }
        return node;
    }

    // Metodo para recorrido por niveles (BFS)
    public void breadthFirstTraversal() {
        // Si arbol vacio, termina
        if (root == null) return;
        
        // Crea cola para BFS
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);
        
        // Procesa nodos por nivel
        while (!queue.isEmpty()) {
            Node<E> current = queue.poll();
            System.out.print(current.data + " ");
            
            // Agrega hijos a la cola
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }

    // Metodo para recorrido preorden
    public void preOrderTraversal() {
        // Llama al metodo recursivo
        preOrder((NodeAVL<E>)root);
    }
    
    // Metodo recursivo para preorden
    private void preOrder(NodeAVL<E> node) {
        // Si nodo es nulo, termina recursion
        if (node == null) return;
        
        // Visita nodo actual
        System.out.print(node.data + " ");
        // Recorre subarbol izquierdo
        preOrder((NodeAVL<E>)node.left);
        // Recorre subarbol derecho
        preOrder((NodeAVL<E>)node.right);
    }
}
