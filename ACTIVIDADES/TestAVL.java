public class TestAVL {

    public static void main(String[] args) {
        AVLTree<Integer> avlTree = new AVLTree<>();

        // Caso 1: Inserción de 30, 20, 10 (provoca rotación simple a la derecha)
        System.out.println("Caso 1: Inserción 30, 20, 10");
        avlTree.insert(30);
        avlTree.insert(20);
        avlTree.insert(10);
        avlTree.printTree();  // Imprimir árbol después de la inserción

        // Caso 2: Inserción de 10, 20, 30 (provoca rotación simple a la izquierda)
        System.out.println("\nCaso 2: Inserción 10, 20, 30");
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(30);
        avlTree.printTree();  // Imprimir árbol después de la inserción

        // Caso 3: Inserción de 10, 30, 20 (provoca rotación doble a la derecha)
        System.out.println("\nCaso 3: Inserción 10, 30, 20");
        avlTree.insert(10);
        avlTree.insert(30);
        avlTree.insert(20);
        avlTree.printTree();  // Imprimir árbol después de la inserción

        // Caso 4: Inserción de 30, 10, 20 (provoca rotación doble a la izquierda)
        System.out.println("\nCaso 4: Inserción 30, 10, 20");
        avlTree.insert(30);
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.printTree();  // Imprimir árbol después de la inserción
    }
}
