// Kayan Sunderam

import java.util.ArrayList;

/**
 * An Integer Binary Search Tree
 * @author: Kayan Sunderam
 * @version: 4/12/23
 */

public class BST {
    private BSTNode root;

    public BSTNode getRoot() {
        return this.root;
    }

    public void setRoot(BSTNode root) {
        this.root = root;
    }

    /**
     * Sets up a binary search tree
     * with some default values
     */
    public void setupTestData() {
        this.root = new BSTNode(10);
        this.root.setLeft(new BSTNode(5));
        this.root.setRight(new BSTNode((15)));
        this.root.getLeft().setLeft(new BSTNode(3));
        this.root.getLeft().setRight(new BSTNode(9));
    }

    /**
     * Prints the provided ArrayList of nodes
     * in the form node1-node2-node3
     * @param nodes ArrayList of BSTNodes
     */
    public static void printNodes(ArrayList<BSTNode> nodes) {
        for(int i=0; i<nodes.size()-1; i++) {
            System.out.print(nodes.get(i) + "-");
        }
        System.out.println(nodes.get(nodes.size()-1));
    }

    /**
     * A function that searches for a value in the tree
     * @param val integer value to search for
     * @return true if val is in the tree, false otherwise
     */
    public boolean search(int val)
    {
        // TODO: Complete the search function
        return recursiveSearch(root, val);
    }
    // Recursive function that keeps going down the BST depending on whether the search Val would theoretically be:
    // on the left side of the tree (so it's less than the root)
    // or on the right side of the tree (so it's greater than the root)
    public boolean recursiveSearch(BSTNode x, int val)
    {
        // If the search value is equal to something in the tree, return true, there's a match!
        if  (x.getVal() == val)
        {
            return true;
        }
        // If the value that we're searching for is less than our current node in the tree,
        // then we'll want to move to the left side of the tree, where numbers are smaller
        // However, we have to make sure that there actually is a left child to go to,
        // so that is why we have to do the second check to make sure the left child of our current node isn't null
        if (x.getVal() > val && x.getLeft() != null)
        {
            // Recursively calls this method moving down to the left child
            return recursiveSearch(x.getLeft(), val);
        }
        // If searchval is greater, we move to the right of the tree, where all values are greater than the current node
        // Here, we use much of the same reasoning as we did when moving to the left of the tree,
        // we're just applying it to the other side
        if (x.getVal() < val && x.getRight() != null)
        {
            // Recursively calls this method moving down to the right child
            return recursiveSearch(x.getRight(), val);
        }
        // If we eventually get to a point where there aren't any children of the current node,
        // and we haven't already seen a match, there are no more possible matches, so we return false
        return false;
    }

    /**
     * @return ArrayList of BSTNodes in inorder
     */
    public ArrayList<BSTNode> getInorder() {
        // TODO: Complete inorder traversal
        // Creates the ArrayList that we'll use in the recursive method, and we'll keep adding to it each recursive call
        ArrayList<BSTNode> treeList = new ArrayList<BSTNode>();
        return recursiveInorder(treeList, root);
    }
    // Inorder Traversal visits each node from Left → Root → Right
    // This traversal works by going down left children until hitting the left most child
    // (and we'll know this because x.getLeft will be null).
    // Then we'll see that this doesn't have any right children either
    // (and if it did, we'd still be following the pattern of left, root, right:
    // having children would just make our current node the "root" in the pattern, and without a left child,
    // it would have added itself and then added the right child).
    // Since this node we would add is a leaf, we'd then go up one recursive level (which is also one level in the tree)
    // and add that node which was the root to the left most leaf, and then we'd continue on,
    // adding the left, going up a level and adding the root, then adding the right
    public ArrayList<BSTNode> recursiveInorder(ArrayList<BSTNode> treeList, BSTNode x)
    {
        // Left
        if (x.getLeft() != null)
        {
            recursiveInorder(treeList, x.getLeft());
        }
        // Root
        treeList.add(x);
        // Right
        if (x.getRight() != null)
        {
            recursiveInorder(treeList, x.getRight());
        }
        return treeList;
    }

    /**
     * @return ArrayList of BSTNodes in preorder
     */
    public ArrayList<BSTNode> getPreorder()
    {
        // TODO: Complete preorder traversal
        ArrayList<BSTNode> treeList = new ArrayList<BSTNode>();
        return recursivePreorder(treeList, root);
    }
    // Preorder Traversal visits each node from Root → Left → Right
    // This traversal is very similar to Inorder, except here we start with the root in the pattern
    // That means that we'll add the root node first, then go to the left child, and then if that left child is a leaf,
    // then we'll go to the right child of the root
    // However, if it's a bigger tree and the root's left child has children,
    // then we'll keep going down in the tree until we hit that sides leaves, and then we'll come up to the roots right
    // child, because each time we go down a level, that new left child actually becomes the "root" in the pattern
    public ArrayList<BSTNode> recursivePreorder(ArrayList<BSTNode> treeList, BSTNode x)
    {
        // Root
        treeList.add(x);
        // Left
        if (x.getLeft() != null)
        {
            recursivePreorder(treeList, x.getLeft());
        }
        // Right
        if (x.getRight() != null)
        {
            recursivePreorder(treeList, x.getRight());
        }
        return treeList;
    }

    /**
     * @return ArrayList of BSTNodes in postorder
     */
    public ArrayList<BSTNode> getPostorder()
    {
        // TODO: Complete postorder traversal
        ArrayList<BSTNode> treeList = new ArrayList<BSTNode>();
        return recursivePostorder(treeList, root);
    }
    // Postorder Traversal visits each node from Left → Right → Root
    // This traversal is also similar to the other two, but here we're adding to our solution at the end of the method
    // This is because (similar to inorder), we start off with the left most leaf node, but then,
    // when recursing up a level, we actually don't add the root there, but rather we continue on to the right child.
    // If it turns out that this right child actually has more children, we first continue on down there,
    // since now the right child is considered the "root" in this pattern.
    // Eventually, after hitting the leaves of one side of the tree, we'll make our back up, adding the roots,
    // and then going to the right side, finally adding the root of the whole tree last
    public ArrayList<BSTNode> recursivePostorder(ArrayList<BSTNode> treeList, BSTNode x)
    {
        // Left
        if (x.getLeft() != null)
        {
            recursivePostorder(treeList, x.getLeft());
        }
        // Right
        if (x.getRight() != null)
        {
            recursivePostorder(treeList, x.getRight());
        }
        // Root
        treeList.add(x);
        return treeList;
    }

    /**
     * Inserts the given integer value to the tree
     * if it does not already exist. Modifies the
     * root instance variable to be the root of the new modified tree.
     * @param val The value ot insert
     */
    public void insert(int val)
    {
        // TODO: Complete insert
        // Creates new node with the given value
        BSTNode node = new BSTNode(val);
        // Calls the recursive method to decide where to insert the new node in the BST, and then inserts it
        recursiveInsertion(root, node);
    }
    // This recursiveInsertion method is pretty similar to the recursiveSearch method,
    // just with the difference of inserting the node in the appropriate spot at the end of the tree
    // instead of just returning false like in recursiveSearch
    public void recursiveInsertion(BSTNode x, BSTNode node)
    {
        // If x.getVal() = node.getVal(), the if checks won't pass, and we'll just get to the end of the method,
        // with nothing needing to be inserted since this node val already exists in the BST
        // If the new node is less than what node we're currently at, we go to left child
        if (x.getVal() > node.getVal())
        {
            // But if there isn't a left child to go to, we know that this is a leaf on the BST,
            // so it's the proper place to insert the new node
            if (x.getLeft() == null)
            {
                x.setLeft(node);
                return;
            }
            // Here is the actual act of going to the left child
            recursiveInsertion(x.getLeft(), node);
        }
        // All of this just mirrors the previous few lines, just seeing whether the new node is greater
        // and thus making us go to the right child, or inserting the node as a right child
        if (x.getVal() < node.getVal())
        {
            if (x.getRight() == null)
            {
                x.setRight(node);
                return;
            }
            recursiveInsertion(x.getRight(), node);
        }
    }

    /**
     * Determines if the current BST is
     * a valid BST.
     * @return true if valid false otherwise
     */
    public boolean isValidBST() {
        // TODO: Optional Challenge!
        return false;
    }

    public static void main(String[] args) {
        // Tree to help you test your code
        BST tree = new BST();
        tree.setupTestData();

        System.out.println("\nSearching for 15 in the tree");
        System.out.println(tree.search(15));

        System.out.println("\nSearching for 22 in the tree");
        System.out.println(tree.search(22));

        System.out.println("\nPreorder traversal of binary tree is");
        ArrayList<BSTNode> sol = tree.getPreorder();
        printNodes(sol);

        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);

        System.out.println("\nPostorder traversal of binary tree is");
        sol = tree.getPostorder();
        printNodes(sol);

        tree.insert(8);
        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);
    }
}
