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
        return null;
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
        return null;
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
        return null;
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

        return null;
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
        else if(contains(key))
            throw new DuplicateKeyException();
        else if(root == null)
            root = new BSTNode<K, V>(key, value);
        else if(key.compareTo(root.key) > 0)
            insertHelper(root, key, value);
        else
            insertHelper(root, key, value);
    }

    /**
     * If key is found, remove the key,value pair from the data structure and decrease num keys.
     * If key is not found, do not decrease the number of keys in the data structure.
     * If key is null, throw IllegalNullKeyException
     * If key is not found, throw KeyNotFoundException().
     */
    @Override
    public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
        // TODO Auto-generated method stub
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
        return 1 + Math.max(heightHelper(node.left), heightHelper(node.right) );
    }

    private void insertHelper(BSTNode<K, V> node, K key, V value) {
        if (node == null)
            node = new BSTNode<K, V>(key, value);
        else if (key.compareTo(node.key) > 0)
            insertHelper(node.right, key, value);
        else
            insertHelper(node.left, key, value);
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
            printHelper(root);
        }
    }

    private void printHelper(BSTNode<K, V> node) {
        if (node == null)
            System.out.println("Tree is empty");
    }
}
