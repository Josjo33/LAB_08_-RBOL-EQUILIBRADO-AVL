// Clase para probar implementacion AVL
public class TestAVL {
    public static void main(String[] args) {
        // Prueba rotacion simple izquierda
        System.out.println("Caso 1: RSL");
        AVLTree<Integer> avl1 = new AVLTree<>();
        avl1.insert(30);
        avl1.insert(40);
        avl1.insert(50);
        avl1.printTree();
        
        // Prueba rotacion simple derecha
        System.out.println("\nCaso 2: RSR");
        AVLTree<Integer> avl2 = new AVLTree<>();
        avl2.insert(50);
        avl2.insert(40);
        avl2.insert(30);
        avl2.printTree();
        
        // Prueba rotacion doble izquierda
        System.out.println("\nCaso 3: RDL");
        AVLTree<Integer> avl3 = new AVLTree<>();
        avl3.insert(50);
        avl3.insert(30);
        avl3.insert(40);
        avl3.printTree();
        
        // Prueba rotacion doble derecha
        System.out.println("\nCaso 4: RDR");
        AVLTree<Integer> avl4 = new AVLTree<>();
        avl4.insert(30);
        avl4.insert(50);
        avl4.insert(40);
        avl4.printTree();
        
        // Prueba secuencia compleja
        System.out.println("\nCaso 5: Secuencia compleja");
        AVLTree<Integer> avl5 = new AVLTree<>();
        int[] valores = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};
        for (int val : valores) {
            avl5.insert(val);
        }
        avl5.printTree();
        
        // Prueba recorridos
        System.out.println("\nRecorridos:");
        System.out.print("BFS: ");
        avl5.breadthFirstTraversal();
        System.out.print("\nPreorden: ");
        avl5.preOrderTraversal();
        
        // Prueba eliminaciones
        System.out.println("\n\nEliminando 65:");
        avl5.remove(65);
        avl5.printTree();
        
        System.out.println("\nEliminando 50:");
        avl5.remove(50);
        avl5.printTree();
    }
}
