import java.util.ArrayList;
import java.util.Comparator;
/**
    Generic class to implement Binary Search Tree
*/
public class TreeMap<K extends Comparable<K>, V> {
    // data member: reference to the root node
    private TreeNode root;
    // data member: the number of nodes in BST
    private int size;
    //data member: private comparator 
    private Comparator<K> comp;
    //data member: to counter the iterations of various methods
    public static int iterations;
    /**
        Inner class to model a node of the BST
    */
    private class TreeNode{
        // data member: the value of the node
        HashMapEntry<K,V> value;
        // data member: reference to the left child node
        TreeNode left;
        // data member: reference to the right child node
        TreeNode right;
        /**
            Constructor to initialize the value of the tree node
            sets the left and right child to null
        */
        TreeNode(HashMapEntry<K,V> val){
            value = val;
            left = right = null;
        }
    }
    /**
        Default constructor to create an empty BST
        Time complexity: O(1)
    */
    TreeMap(Comparator<K> c){
        comp = c; 
        root = null;
        size = 0; 
    }
    /**
        Method to return the size of the tree
        @return the number of nodes in the BST
        Time complexity: O(1)
    */
    public int size() { 
        return size; 
    }
    /**
        Method to check if the BST is empty
        @return true if the tree is empty, false otherwise
        Time complexity: O(1)
    */
    public boolean isEmpty() { 
        return (size == 0); 
    }
    /**
        Method to clear the BST
        sets root to null and size to zero
        Time complexity: O(1)
    */
    public void clear() { 
        root = null; 
        size = 0;
    }
    /** 
        Search method
        @param value to be serached
        @return true if value is found in the tree, false otherwise
        Time complexity: O(n)
    */
    public boolean contains(K key) {
        TreeNode node = root;
        while (node != null) {
            iterations++;
            if(key.compareTo(node.value.getKey()) < 0)
                node = node.left;
            else if (key.compareTo(node.value.getKey())> 0)
                node = node.right;
            else
                return true;
        }
        return false;
    }
    /**
     * get method
     * @param   key holds the key of the value we're searching
     * @return  the value at that key
     */
    public V get(K key){
        TreeNode node = root;
        while (node != null) {
            ++iterations;
            if(comp.compare(key, node.value.getKey()) < 0)
                node = node.left;
            else if (comp.compare(key, node.value.getKey()) > 0)
                node = node.right;
            else
                return node.value.getValue();
        }
        return null;
    }

    /**
        Method to add a new node to the BST
        @param value to be added
        @return true if value was added successfully, false if the value is already in the tree
        Time complexity: O(logn) to O(n)
    */
    public boolean add(K key, V val) {
        // iterations++;
        // System.out.println(iterations);
        HashMapEntry<K,V> newVal = new HashMapEntry<>(key, val);
        if (root == null) // the first node to be inserted
            root = new TreeNode(newVal);
        else {
            TreeNode parent, node;
            parent = null; 
            node = root;
            //System.out.println(newVal);
            while (node != null) { // Looking for a leaf node
                parent = node;
                if(comp.compare(key, node.value.getKey()) < 0) {
                    node = node.left; 
                }
                else if (comp.compare(key, node.value.getKey()) > 0) {
                    node = node.right;
                }
                else{
                    node.value.setValue(val);
                    return false; // duplicates are not allowed
                }
            }
            if (comp.compare(key, parent.value.getKey()) < 0)
                parent.left = new TreeNode(newVal);
            else
                parent.right = new TreeNode(newVal);
        }
        size++;
        return true; 
    }
    /** 
        Helper method to change the links between the nodes
        @param parent has node as a child
        @param node to be removed
        @param newChild will replace node as the child of parent
        Time complexity: O(1)
    */
    private void changeChild(TreeNode parent,
                             TreeNode node, 
                             TreeNode newChild){
        if(parent.left == node)
            parent.left = newChild;
        else
            parent.right = newChild;
    }
    /**
        Method to remove a value from the BST
        @param value to be removed from the BST
        @return true if value is found and the node removed,
                false if the value is not found in the BST
        Time complexity: O(logn) to O(n)
    */
    public boolean remove(K key) {
        TreeNode parent, node;
        parent = null; node = root;
        // Find value first
        while (node != null) {
            if (comp.compare(key, node.value.getKey()) < 0) {
                parent = node;
                node = node.left;// go left
            }
            else if (comp.compare(key, node.value.getKey()) > 0) {
                parent = node;
                node = node.right;//go right
            }
            else
                break; // value found
        }
        if (node == null) // value not in the tree
            return false;
        // Case 1: node has no children
        if(node.left == null && node.right == null){
            if(parent == null) // delete root
                root = null;
            else
                changeChild(parent, node, null);
        }
        else if(node.left == null){ 
            //case 2: node has one right child
            if (parent == null) // delete root
                root = node.right;
            else
                changeChild(parent, node, node.right);
        }
        else if(node.right == null){ 
            //case 2: node has one left child
            if (parent == null) // delete root
                root = node.left;
            else
                changeChild(parent, node, node.left);
        } 
        else { 
            // case 3: node has two children
            TreeNode rightMostParent = node;
            TreeNode rightMost = node.left;
            // go right on the left subtree
            while (rightMost.right != null) {
                rightMostParent = rightMost;
                rightMost = rightMost.right;
            }
            // copy the value of rigthMost to node
            node.value = rightMost.value;
            //delete rigthMost
            changeChild(rightMostParent,rightMost,
                        rightMost.left);
        }
        size--;
        return true;
    }
    /**
        Method to traverse the BST using inorder traversal
        Time complexity: O(n)
    */
    public void inorder() {
        inorder(root);
    }
    /**
        Recursive Helper Method to traverse the BST starting from node
        @param node root of the subtree being traversed
        Time complexity: O(n)
    */
    private void inorder(TreeNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.value.toString() + " ");
            inorder(node.right);
        }
    }
    /**
        Method to traverse the BST using preorder traversal
        Time complexity: O(n)
    */
    public void preorder() {
        preorder(root);
    }
    /**
        Recursive Helper Method to traverse the BST starting from node
        @param node root of the subtree being traversed
        Time complexity: O(n)
    */
    private void preorder(TreeNode node) {
        if (node != null) {
            System.out.print(node.value.toString() + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }
    /**
        Method to traverse the BST using postorder traversal
        Time complexity: O(n)
    */
    public void postorder() {
        postorder(root);
    }
    /**
        Recursive Helper Method to traverse the BST starting from node
        @param node root of the subtree being traversed
        Time complexity: O(n)
    */
    private void postorder(TreeNode node)  {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.value.toString() + " "); 
        }
    }

    // Time complexity: O(n)
    public ArrayList<HashMapEntry<K,V>> sortedKeys(){
        ArrayList<HashMapEntry<K,V>> al = new ArrayList<>();
        return sortedKeys(root, al);
    }
    public ArrayList<HashMapEntry<K,V>> sortedKeys(TreeNode node, ArrayList<HashMapEntry<K,V>> al){
        iterations++;
        if (node != null) {
            sortedKeys(node.left, al);
            al.add(new HashMapEntry(node.value.getKey(), node.value.getValue()));
            sortedKeys(node.right, al);
        }
        return al;
    }
}