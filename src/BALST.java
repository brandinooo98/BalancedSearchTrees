import com.sun.jdi.InterfaceType;

import java.util.ArrayList;
import java.util.List;
/**
 * BALST constructs an AVL tree and holds its many functions
 *
 * @param <K> is the generic type of key
 * @param <V> is the generic type of value
 */
public class BALST<K extends Comparable<K>, V> implements BALSTADT<K, V> {

    private BSTNode<K, V> root;

    private int numKeys;

    public BALST() {
    }

    /**
     * Returns the key that is in the root node of this BST.
     * If root is null, returns null.
     * @return key found at root node, or null
     */
    @Override
    public K getKeyAtRoot() {
        return root.key;
    }

    /**
     * Tries to find a node with a key that matches the specified key.
     * If a matching node is found, it returns the returns the key that is in the left child.
     * If the left child of the found node is null, returns null.
     *
     * @param key A key to search for
     * @return The key that is in the left child of the found key
     *
     * @throws IllegalNullKeyException if key argument is null
     * @throws KeyNotFoundException if key is not found in this BST
     */
    @Override
    public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
        if(key == null)
            throw new IllegalNullKeyException();
        else {
            BSTNode<K, V> node = search(root, key);
            if (node == null)
                throw new KeyNotFoundException();
            else
                return node.left.key;
        }
    }

    /**
     * Tries to find a node with a key that matches the specified key.
     * If a matching node is found, it returns the returns the key that is in the right child.
     * If the right child of the found node is null, returns null.
     *
     * @param key A key to search for
     * @return The key that is in the right child of the found key
     *
     * @throws IllegalNullKeyException if key is null
     * @throws KeyNotFoundException if key is not found in this BST
     */
    @Override
    public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
        if(key == null)
            throw new IllegalNullKeyException();
        else {
            BSTNode<K, V> node = search(root, key);
            if (node == null)
                throw new KeyNotFoundException();
            else
                return node.left.key;
        }
    }

    /**
     * Returns the height of this BST.
     * H is defined as the number of levels in the tree.
     *
     * If root is null, return 0
     * If root is a leaf, return 1
     * Else return 1 + max( height(root.left), height(root.right) )
     *
     * Examples:
     * A BST with no keys, has a height of zero (0).
     * A BST with one key, has a height of one (1).
     * A BST with two keys, has a height of two (2).
     * A BST with three keys, can be balanced with a height of two(2)
     *                        or it may be linear with a height of three (3)
     * ... and so on for tree with other heights
     *
     * @return the number of levels that contain keys in this BINARY SEARCH TREE
     */
    @Override
    public int getHeight() {
        if(root == null)
            return 0;
        if(root.left == null && root.right == null)
            return 1;
        else
            return heightHelper(root);
    }

    /**
     * Returns the keys of the data structure in sorted order.
     * In the case of binary search trees, the visit order is: L V R
     *
     * If the SearchTree is empty, an empty list is returned.
     *
     * @return List of Keys in-order
     */
    @Override
    public List<K> getInOrderTraversal() {
        List<K> list = new ArrayList<>();
        if(root == null)
            return list;
        else {
            inOrderHelper(root, list);
            return list;
        }
    }

    private void inOrderHelper(BSTNode<K, V> node, List<K> list) {
        if (node == null)
            return;

        /* first recur on left child */
        inOrderHelper(node.left, list);

        /* then print the data of node */
        list.add(node.key);

        /* now recur on right child */
        inOrderHelper(node.right, list);
    }

    /**
     * Returns the keys of the data structure in pre-order traversal order.
     * In the case of binary search trees, the order is: V L R
     *
     * If the SearchTree is empty, an empty list is returned.
     *
     * @return List of Keys in pre-order
     */
    @Override
    public List<K> getPreOrderTraversal() {
        List<K> list = new ArrayList<>();
        if(root == null)
            return list;
        else {
            preOrderHelper(root, list);
            return list;
        }
    }

    private void preOrderHelper(BSTNode<K, V> node, List<K> list) {
        if (node == null)
            return;

        /* first print data of node */
        list.add(node.key);

        /* then recur on left sutree */
        preOrderHelper(node.left, list);

        /* now recur on right subtree */
        preOrderHelper(node.right, list);
    }

    /**
     * Returns the keys of the data structure in post-order traversal order.
     * In the case of binary search trees, the order is: L R V
     *
     * If the SearchTree is empty, an empty list is returned.
     *
     * @return List of Keys in post-order
     */
    @Override
    public List<K> getPostOrderTraversal() {
        List<K> list = new ArrayList<>();
        if(root == null)
            return list;
        else {
            postOrderHelper(root, list);
            return list;
        }
    }

    private void postOrderHelper(BSTNode<K, V> node, List<K> list) {
        if (node == null)
            return;

        // first recur on left subtree
        postOrderHelper(node.left, list);

        // then recur on right subtree
        postOrderHelper(node.right, list);

        // now deal with the node
        list.add(node.key);
    }

    /**
     * Returns the keys of the data structure in level-order traversal order.
     *
     * The root is first in the list, then the keys found in the next level down,
     * and so on.
     *
     * If the SearchTree is empty, an empty list is returned.
     *
     * @return List of Keys in level-order
     */
    @Override
    public List<K> getLevelOrderTraversal() {
        List<K> list = new ArrayList<>();
        int height = getHeight();
        for (int level = 1; level <= height; level++)
            levelOrderHelper(root, level, list);
        return list;
    }

    /**
     * @param node
     * @param level
     * @param list
     */
    private void levelOrderHelper(BSTNode<K, V> node, int level, List<K> list) {
        if (root == null)
            return;
        if (level == 1)
            list.add(node.key);
        else if (level > 1) {
            levelOrderHelper(node.left, level - 1, list);
            levelOrderHelper(node.right, level - 1, list);
        }
    }

    /**
     * Add the key,value pair to the data structure and increase the number of keys.
     * If key is null, throw IllegalNullKeyException;
     * If key is already in data structure, throw DuplicateKeyException();
     * Do not increase the num of keys in the structure, if key,value pair is not added.
     */
    @Override
    public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
        if(key == null)
            throw new IllegalNullKeyException();
        else if(root == null)
            root = new BSTNode<K, V>(key, value);
        else if(contains(key))
            throw new DuplicateKeyException();
        else if(key.compareTo(root.key) > 0)
            insertHelper(root, key, value, null);
        else
            insertHelper(root, key, value, null);
    }

    /**
     * If key is found, remove the key,value pair from the data structure and decrease num keys.
     * If key is not found, do not decrease the number of keys in the data structure.
     * If key is null, throw IllegalNullKeyException
     * If key is not found, throw KeyNotFoundException().
     */
    @Override
    public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
        search(root, key);
        return false;
    }

    /**
     *  Returns the value associated with the specified key
     *
     * Does not remove key or decrease number of keys
     * If key is null, throw IllegalNullKeyException
     * If key is not found, throw KeyNotFoundException().
     */
    @Override
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
        if(key == null)
            throw new IllegalNullKeyException();
        BSTNode<K, V> node = search(root, key);
        if(node == null)
            throw new KeyNotFoundException();
        else
            return node.value;
    }

    /**
     * Returns true if the key is in the data structure
     * If key is null, throw IllegalNullKeyException
     * Returns false if key is not null and is not present
     */
    @Override
    public boolean contains(K key) throws IllegalNullKeyException {
        if(key == null)
            throw new IllegalNullKeyException();
        else {
            try {
                BSTNode<K, V> node = search(root, key);
                if (node == null)
                    throw new KeyNotFoundException();
                else
                    return true;
            } catch (KeyNotFoundException e) {
                return false;
            }
        }
    }

    /**
     * @param node
     * @param key
     * @return
     * @throws IllegalNullKeyException
     */
    private BSTNode<K, V> search(BSTNode<K, V> node, K key) throws IllegalNullKeyException {
        if(key == null)
            throw new IllegalNullKeyException();
        else if(node == null)
            return null;
        else if(node.key.equals(key))
            return node;
        else if (node.key.compareTo(key) > 0)
            return search(node.left, key);
        else if (node.key.compareTo(key) < 0)
            return search(node.right, key);
        else
            return null;
    }

    private int heightHelper(BSTNode<K, V> node) {
        if(node == null)
            return 0;
        else
            return 1 + Math.max(heightHelper(node.left), heightHelper(node.right) );
    }

    private void insertHelper(BSTNode<K, V> node, K key, V value, BSTNode<K, V> parent) {
        if (node == null) {
            if (key.compareTo(parent.key) > 0)
                parent.right = new BSTNode<K, V>(key, value);
            else
                parent.left = new BSTNode<K, V>(key, value);
        }
        else if (key.compareTo(node.key) > 0)
            insertHelper(node.right, key, value, node);
        else
            insertHelper(node.left, key, value, node);
    }

    /**
     * @param node
     * @param key
     * @return
     */
    private BSTNode<K, V> removeHelper(BSTNode<K, V> node, K key) {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (node == null)
            return node;

        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
        if (key.compareTo(node.key) < 0)
            node.left = removeHelper(node.left, key);

            // If the key to be deleted is greater than the
            // root's key, then it lies in right subtree
        else if (key.compareTo(node.key) > 0)
            node.right = removeHelper(node.right, key);

            // if key is same as root's key, then this is the node
            // to be deleted
        else {
            // node with only one child or no child
            if ((node.left == null) || (node.right == null)) {
                BSTNode<K, V> temp = null;
                if (temp == node.left)
                    temp = node.right;
                else
                    temp = node.left;

                // No child case
                if (temp == null) {
                    temp = node;
                    node = null;
                }
                else // One child case
                    node = temp; // Copy the contents of
                // the non-empty child
            }
            else {
                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                BSTNode<K, V> temp = smallestNode(node.right);

                // Copy the inorder successor's data to this node
                node.key = temp.key;

                // Delete the inorder successor
                node.right = removeHelper(node.right, temp.key);
            }
        }
        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = heightHelper(node);
        return null;
    }

    /**
     * @param node
     * @return
     */
    BSTNode<K, V> smallestNode(BSTNode<K, V> node){
        BSTNode<K, V> runner = node;
        /* loop down to find the leftmost leaf */
        while (runner.left != null)
            runner = runner.left;
        return runner;
    }

    /**
     *  Returns the number of key,value pairs in the data structure
     */
    @Override
    public int numKeys() {
        if (root == null)
            return 0;
        else
            return count(root);
    }

    private int count(BSTNode<K, V> node) {
        return (count(node.left) + 1 + count(node.right));
    }

    /**
     * Print the tree.
     *
     * For our testing purposes: all keys that we insert in the tree
     * will have a string length of exactly 2 characters.
     * example: numbers 10-99, or strings aa - zz, or AA to ZZ
     *
     * This makes it easier for you to not worry about spacing issues.
     *
     * You can display in any of a variety of ways, but we should see
     * a tree that we can identify left and right children of each node
     *
     * For example:

     |       |-------50
     |-------40
     |       |-------35
     30
     |-------20
     |       |-------10

     Look from bottom to top. Inorder traversal of above tree (10,20,30,35,40,50)

     Or, you can display a tree of this kind.

     30
     /\
     /  \
     20  40
     /   /\
     /   /  \
     10  35  50

     Or, you can come up with your own orientation pattern, like this.

     10
     20
     30
     35
     40
     50

     The connecting lines are not required if we can interpret your tree.

     */
    @Override
    public void print() {
        if(root == null)
            System.out.print("Tree does not exist");
        else{
            int height = getHeight();
            for (int level = 1; level <= height; level++) {
                printHelper(root, level);
                System.out.println("");
            }
        }
    }

    private void printHelper(BSTNode<K, V> node, int level) {
        if (root == null)
            return;
        if (level == 1) {
            System.out.print(node.key + "  ");
        }
        else{
            printHelper(node.left, level - 1);
            printHelper(node.right, level - 1);
        }
    }
}
