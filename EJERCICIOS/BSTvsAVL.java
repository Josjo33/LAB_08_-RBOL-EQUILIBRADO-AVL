// Clase para comparar BST y AVL
public class BSTvsAVL {
    public static void main(String[] args) {
        // Caso 1: Arbol degenerado
        BSTree<Integer> bst = new BSTree<>();
        AVLTree<Integer> avl = new AVLTree<>();
        
        System.out.println("Insertando 1 a 10 en orden:");
        for (int i = 1; i <= 10; i++) {
            bst.insert(i);
            avl.insert(i);
        }
        
        System.out.println("\nBST (degenerado):");
        bst.printTree();
        System.out.println("Altura BST: " + bst.height());
        
        System.out.println("\nAVL (balanceado):");
        avl.printTree();
        System.out.println("Altura AVL: " + avl.height());
        
        // Caso 2: Tiempo de busqueda
        BSTree<Integer> bst2 = new BSTree<>();
        AVLTree<Integer> avl2 = new AVLTree<>();
        
        int[] valores = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};
        for (int val : valores) {
            bst2.insert(val);
            avl2.insert(val);
        }
        
        for (int i = 81; i <= 100; i++) {
            bst2.insert(i);
            avl2.insert(i);
        }
        
        long start = System.nanoTime();
        bst2.search(100);
        long end = System.nanoTime();
        System.out.println("\nTiempo busqueda BST: " + (end - start) + " ns");
        
        start = System.nanoTime();
        avl2.search(100);
        end = System.nanoTime();
        System.out.println("Tiempo busqueda AVL: " + (end - start) + " ns");
    }
}
