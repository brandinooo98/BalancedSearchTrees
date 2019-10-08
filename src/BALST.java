import java.util.List;

/**
 *
 * Class to implement a BalanceSearchTree. Can be of type AVL or Red-Black.
 * Note which tree you implement here and as a comment when you submit.
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
        // TODO Auto-generated method stub
        return key.left.key;
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
        // TODO Auto-generated method stub
        return key.right.key;
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
        // TODO Auto-generated method stub
        return 0;
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
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
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
        if (root == null)
            root = new BSTNode<>(key, value);
        K rightKey;
        K leftKey;
        if(key.compareTo(getKeyAtRoot()) > 0) {
            if(getKeyOfRightChildOf(getKeyAtRoot()) == null)

        }

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
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Returns true if the key is in the data structure
     * If key is null, throw IllegalNullKeyException
     * Returns false if key is not null and is not present
     */
    @Override
    public boolean contains(K key) throws IllegalNullKeyException {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     *  Returns the number of key,value pairs in the data structure
     */
    @Override
    public int numKeys() {
        // TODO Auto-generated method stub
        return 0;
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
        // TODO Auto-generated method stub

    }

}
