/***
 * Class to model the entity BST
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: April 13 2023
 * Last Date Modified: April 13 2023
 */
/**
    Generic class to implement Binary Search Tree
*/
public class BST<E extends Comparable<E>> {
    // data member: reference to the root node
    private TreeNode root;
    // data member: the number of nodes in BST
    private int size;
    //data member: counter
    public static int iterations;
    /**
        Inner class to model a node of the BST
    */
    private class TreeNode{
        // data member: the value of the node
        E value;
        // data member: reference to the left child node
        TreeNode left;
        // data member: reference to the right child node
        TreeNode right;
        /**
            Constructor to initialize the value of the tree node
            sets the left and right child to null
        */
        TreeNode(E val){
            value = val;
            left = right = null;
        }
    }
    /**
        Default constructor to create an empty BST
        Time complexity: O(1)
    */
    BST(){ 
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
    public boolean contains(E value) {
        iterations = 0;
        TreeNode node = root;
        while (node != null) {
            iterations++;
            if( value.compareTo(node.value) < 0)
                node = node.left;
            else if (value.compareTo(node.value)> 0)
                node = node.right;
            else
                return true;
        }
        return false;
    }
    /**
        Method to add a new node to the BST
        @param value to be added
        @return true if value was added successfully, false if the value is already in the tree
        Time complexity: O(logn) to O(n)
    */
    public boolean add(E value) {
        iterations = 0; 
        if (root == null) // the first node to be inserted
            root = new TreeNode(value);
        else {
            TreeNode parent, node;
            parent = null; node = root;
            while (node != null) { // Looking for a leaf node
                iterations++;
                parent = node;
                if(value.compareTo(node.value) < 0) {
                    node = node.left; 
                }
                else if (value.compareTo(node.value) > 0) {
                    node = node.right;
                }
                else
                    return false; // duplicates are not allowed
            }
            if (value.compareTo(parent.value)< 0)
                parent.left = new TreeNode(value);
            else
                parent.right = new TreeNode(value);
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
    public boolean remove(E value) {
        iterations = 0;
        TreeNode parent, node;
        parent = null; node = root;
        // Find value first
        while (node != null) {
            iterations++;
            if (value.compareTo(node.value) < 0) {
                parent = node;
                node = node.left;// go left
            }
            else if (value.compareTo(node.value) > 0) {
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
                iterations++;
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
            System.out.print(node.value + " ");
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
            System.out.print(node.value + " ");
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
            System.out.print(node.value + " "); 
        }
    }





    /***
     * Method to get the height of a binary tree
     * no param
     * @return the numerical value for height
     */
    // O(n)
    public int height(){
        return height(root);
    }
    /***
     * Resursive Helper Method for height
     * @param   node holds the current Node
     * @return the height of the tree back to the method above with no param
     */
    // O(n)
    private int height(TreeNode node){
        if(node == null){ //base case
            return 0;
        }
        int lHeight = height(node.left); //recursive call on the left subtree
        int rHeight = height(node.right); 
        return Math.max(lHeight, rHeight) + 1; 
    }
    /***
     * Method to check if a binary tree is balanced
     * no param
     * @return T/F respectively
     */
    // O(n)
    public boolean isBalanced(){
        return isBalanced(root);
    }
    /***
     * Helper method for checking balance
     * @param   node points to the next node we are going to check
     * @return  T/F respectively
     */
    public boolean isBalanced(TreeNode node){
        if(node == null){// base case 1
            return true;
        }
        int lHeight = height(node.left);
        int rHeight = height(node.right);
        int difference = Math.abs(lHeight - rHeight);
        if(difference > 1){ // base case 2
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }


}