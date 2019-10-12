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
     *
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
     * @throws IllegalNullKeyException if key argument is null
     * @throws KeyNotFoundException    if key is not found in this BST
     */
    @Override
    public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
        if (key == null)
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
     * @throws IllegalNullKeyException if key is null
     * @throws KeyNotFoundException    if key is not found in this BST
     */
    @Override
    public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
        if (key == null)
            throw new IllegalNullKeyException();
        else {
            BSTNode<K, V> node = search(root, key);
            if (node == null)
                throw new KeyNotFoundException();
            else
                return node.right.key;
        }
    }

    /**
     * Returns the height of this BST.
     * H is defined as the number of levels in the tree.
     * <p>
     * If root is null, return 0
     * If root is a leaf, return 1
     * Else return 1 + max( height(root.left), height(root.right) )
     * <p>
     * Examples:
     * A BST with no keys, has a height of zero (0).
     * A BST with one key, has a height of one (1).
     * A BST with two keys, has a height of two (2).
     * A BST with three keys, can be balanced with a height of two(2)
     * or it may be linear with a height of three (3)
     * ... and so on for tree with other heights
     *
     * @return the number of levels that contain keys in this BINARY SEARCH TREE
     */
    @Override
    public int getHeight() {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        else
            return heightHelper(root);
    }

    /**
     * @param node
     * @return
     */
    private int heightHelper(BSTNode<K, V> node) {
        if (node == null)
            return 0;
        else
            return 1 + Math.max(heightHelper(node.left), heightHelper(node.right));
    }

    /**
     * Returns the keys of the data structure in sorted order.
     * In the case of binary search trees, the visit order is: L V R
     * <p>
     * If the SearchTree is empty, an empty list is returned.
     *
     * @return List of Keys in-order
     */
    @Override
    public List<K> getInOrderTraversal() {
        List<K> list = new ArrayList<>();
        if (root == null)
            return list;
        else {
            inOrderHelper(root, list);
            return list;
        }
    }

    /**
     * @param node
     * @param list
     */
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
     * <p>
     * If the SearchTree is empty, an empty list is returned.
     *
     * @return List of Keys in pre-order
     */
    @Override
    public List<K> getPreOrderTraversal() {
        List<K> list = new ArrayList<>();
        if (root == null)
            return list;
        else {
            preOrderHelper(root, list);
            return list;
        }
    }

    /**
     * @param node
     * @param list
     */
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
     * <p>
     * If the SearchTree is empty, an empty list is returned.
     *
     * @return List of Keys in post-order
     */
    @Override
    public List<K> getPostOrderTraversal() {
        List<K> list = new ArrayList<>();
        if (root == null)
            return list;
        else {
            postOrderHelper(root, list);
            return list;
        }
    }

    /**
     * @param node
     * @param list
     */
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
     * <p>
     * The root is first in the list, then the keys found in the next level down,
     * and so on.
     * <p>
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
            if (node.left != null)
                levelOrderHelper(node.left, level - 1, list);
            if (node.right != null)
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
        if (key == null)
            throw new IllegalNullKeyException();
        else if (root == null) {
            numKeys++;
            root = new BSTNode<K, V>(key, value);
        } else if (contains(key))
            throw new DuplicateKeyException();
        else if (key.compareTo(root.key) > 0)
            insertHelper(root, key, value, null);
        else
            insertHelper(root, key, value, null);
        updateHeightAndBalanceFactor();
        balanceTree();
    }

    /**
     * @param node
     * @param key
     * @param value
     * @param parent
     */
    private void insertHelper(BSTNode<K, V> node, K key, V value, BSTNode<K, V> parent) throws IllegalNullKeyException {
        if (node == null) {
            if (key.compareTo(parent.key) > 0) {
                parent.right = new BSTNode<K, V>(key, value);
                numKeys++;
            } else {
                parent.left = new BSTNode<K, V>(key, value);
                numKeys++;
            }
        } else if (key.compareTo(node.key) > 0)
            insertHelper(node.right, key, value, node);
        else
            insertHelper(node.left, key, value, node);
    }

    /**
     * @param node
     * @return
     * @throws IllegalNullKeyException
     */
    private BSTNode<K, V> rightRotation(BSTNode<K, V> node) throws IllegalNullKeyException {
        BSTNode<K, V> leftChild = node.left;
        BSTNode<K, V> leftGrandChild = null;
        if (leftChild != null) {
            leftGrandChild = leftChild.right;
            leftChild.right = node;
            node.left = leftGrandChild;
        }
        updateHeightAndBalanceFactor();
        return leftChild;
    }

    /**
     * @param node
     * @return
     * @throws IllegalNullKeyException
     */
    private BSTNode<K, V> leftRotation(BSTNode<K, V> node) throws IllegalNullKeyException {
        BSTNode<K, V> rightChild = node.right;
        BSTNode<K, V> rightGrandChild = null;
        if (rightChild != null) {
            rightGrandChild = rightChild.left;
            rightChild.left = node;
            node.right = rightGrandChild;
        }
        updateHeightAndBalanceFactor();
        return rightChild;
    }

    /**
     * @throws IllegalNullKeyException
     */
    private void updateHeightAndBalanceFactor() throws IllegalNullKeyException {
        List<K> list = getInOrderTraversal();
        for (int i = 0; i < list.size(); i++)
            search(root, list.get(i)).height = heightHelper(search(root, list.get(i)));
        for (int i = 0; i < list.size(); i++) {
            BSTNode<K, V> node = search(root, list.get(i));
            if (node.right == null && node.left == null)
                node.balanceFactor = 0;
            else if (node.right == null)
                node.balanceFactor = node.left.height;
            else if (node.left == null)
                node.balanceFactor = 0 - node.right.height;
            else
                node.balanceFactor = node.left.height - node.right.height;
        }
    }

    private void balanceTree() throws IllegalNullKeyException {
        List<K> list = getLevelOrderTraversal();
        for (int i = 0; i < list.size(); i++) {
            BSTNode<K, V> node = search(root, list.get(i));
            if (node != null && node.balanceFactor < -1) {
                if (node.right.balanceFactor > 0) {
                    node.right = rightRotation(node.right);
                }
                if (node == root)
                    node = leftRotation(node);
                else {
                    List<BSTNode<K, V>> listParent = new ArrayList<>();
                    BSTNode<K, V> parent = parentSearch(root, node.key, listParent);
                    parent.right = leftRotation(node);
                }
                if (i == 0)
                    root = node;
            } else if (node != null && node.balanceFactor > 1) {
                if (node.left.balanceFactor < 0) {
                    node.left = leftRotation(node.left);
                    print();
                }
                if(node == root)
                    node = rightRotation(node);
                else {
                    List<BSTNode<K, V>> listParent = new ArrayList<>();
                    BSTNode<K, V> parent = parentSearch(root, node.key, listParent);
                    parent.left = rightRotation(node);
                }
                print();
                if (i == 0)
                    root = node;
            }
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
        if (key == null)
            throw new IllegalNullKeyException();
        else if (!contains(key))
            throw new KeyNotFoundException();
        else if (root == null)
            return false;
        else {
            removeHelper(search(root, key), key);
            if (contains(key))
                return false;
            else {
                numKeys--;
                print();
                updateHeightAndBalanceFactor();
                balanceTree();
                return true;
            }
        }
    }

    /**
     * @param node
     * @param key
     * @return
     */
    private BSTNode<K, V> removeHelper(BSTNode<K, V> node, K key) {
        // node with only one child or no child
        if (node.left == null || node.right == null) {
            if (node.left == null && node.right == null) {
                node.key = null;
                node.value = null;
                node.removed = true;
            } else if (node.left == null) {
                node.removed = true;
                node = node.right;
            } else if (node.right == null) {
                node.removed = true;
                node = node.left;
            }
        } else {
            // node with two children: Get the inorder successor (smallest
            // in the right subtree)
            node.key = inOrderSuccessor(node.right).key;

            // Delete the inorder successor
            node.right = removeHelper(node.right, node.key);
        }
        return null;
    }

    /**
     * @param node
     * @return
     */
    BSTNode<K, V> inOrderSuccessor(BSTNode<K, V> node) {
        BSTNode<K, V> runner = node;
        /* loop down to find the leftmost leaf */
        while (runner.left != null)
            runner = runner.left;
        return runner;
    }

    /**
     * @param node
     * @param key
     * @param list
     * @return
     * @throws IllegalNullKeyException
     */
    private BSTNode<K, V> parentSearch(BSTNode<K, V> node, K key, List<BSTNode<K, V>> list) throws IllegalNullKeyException {
        if (key == null)
            throw new IllegalNullKeyException();
        else if (node == null)
            return null;
        else if (node.key.equals(key))
            return list.get(list.size() - 1);
        else if (node.key.compareTo(key) > 0) {
            list.add(node);
            return parentSearch(node.left, key, list);
        } else if (node.key.compareTo(key) < 0) {
            list.add(node);
            return parentSearch(node.right, key, list);
        } else
            return null;
    }


    /**
     * Returns the value associated with the specified key
     * <p>
     * Does not remove key or decrease number of keys
     * If key is null, throw IllegalNullKeyException
     * If key is not found, throw KeyNotFoundException().
     */
    @Override
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
        if (key == null)
            throw new IllegalNullKeyException();
        BSTNode<K, V> node = search(root, key);
        if (node == null)
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
        if (key == null)
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
        if (key == null)
            throw new IllegalNullKeyException();
        else if (node == null)
            return null;
        else if (node.removed == true)
            return null;
        else if (node.key.equals(key))
            return node;
        else if (node.key.compareTo(key) > 0)
            return search(node.left, key);
        else if (node.key.compareTo(key) < 0)
            return search(node.right, key);
        else
            return null;
    }

    /**
     * Returns the number of key,value pairs in the data structure
     */
    @Override
    public int numKeys() {
        return numKeys;
    }

    /**
     * Print the tree.
     * <p>
     * For our testing purposes: all keys that we insert in the tree
     * will have a string length of exactly 2 characters.
     * example: numbers 10-99, or strings aa - zz, or AA to ZZ
     * <p>
     * This makes it easier for you to not worry about spacing issues.
     * <p>
     * You can display in any of a variety of ways, but we should see
     * a tree that we can identify left and right children of each node
     * <p>
     * For example:
     * <p>
     * |       |-------50
     * |-------40
     * |       |-------35
     * 30
     * |-------20
     * |       |-------10
     * <p>
     * Look from bottom to top. Inorder traversal of above tree (10,20,30,35,40,50)
     * <p>
     * Or, you can display a tree of this kind.
     * <p>
     * 30
     * /\
     * /  \
     * 20  40
     * /   /\
     * /   /  \
     * 10  35  50
     * <p>
     * Or, you can come up with your own orientation pattern, like this.
     * <p>
     * 10
     * 20
     * 30
     * 35
     * 40
     * 50
     * <p>
     * The connecting lines are not required if we can interpret your tree.
     */
    @Override
    public void print() {
        if (root == null)
            System.out.print("Tree does not exist");
        else {
            int height = getHeight();
            for (int level = 1; level <= height; level++) {
                printHelper(root, level);
                System.out.println("");
            }
        }
    }

    /**
     * @param node
     * @param level
     */
    private void printHelper(BSTNode<K, V> node, int level) {
        if (level == 1) {
            if (node == null)
                System.out.print("X  ");
            else if (node.removed == true)
                System.out.print("X  ");
            else
                System.out.print(node.key + "  ");
        } else {
            if (node.left != null)
                printHelper(node.left, level - 1);
            else
                System.out.print("X  ");
            if (node.right != null)
                printHelper(node.right, level - 1);
            else
                System.out.print("X  ");
        }
    }
}
