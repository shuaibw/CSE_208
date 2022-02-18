

public class BinarySearchTree<Item extends Comparable<Item>> {
    private class Node {
        Item item;
        Node left;
        Node right;
        int height;
        int diff;//right.h - left.h

        private Node(Item item) {
            this.item = item;
        }
    }

    private Node root;

    public BinarySearchTree() {
    }

    private void update(Node n) {
        if (n.left == null && n.right == null) {
            n.height = 0;
            n.diff = 0;
            return;
        }
        int leftHeight = n.left != null ? n.left.height : -1;
        int rightHeight = n.right != null ? n.right.height : -1;
        n.height = Math.max(leftHeight, rightHeight) + 1;
        n.diff = rightHeight - leftHeight;
    }

    private Node rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        update(x);
        update(y);
        return y;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        update(x);
        update(y);
        return y;
    }

    private Node zigZig(Node x) {
        return rightRotate(x);
    }

    private Node zagZag(Node x) {
        return leftRotate(x);
    }

    private Node zigZag(Node x) {
        x.left = leftRotate(x.left);
        return rightRotate(x);
    }

    private Node zagZig(Node x) {
        x.right = rightRotate(x.right);
        return leftRotate(x);
    }

    private Node reBalance(Node x) {
        if (x.diff == -2) {
            Node ret = x.left.diff <= 0 ? zigZig(x) : zigZag(x);
            System.out.print("Height invariant violated.\nAfter rebalancing: ");
            return ret;
        } else if (x.diff == 2) {
            Node ret = x.right.diff >= 0 ? zagZag(x) : zagZig(x);
            System.out.print("Height invariant violated.\nAfter rebalancing: ");
            return ret;
        }
        return x;
    }

    public boolean searchItem(Item t) {
        Node node = search(t);
        return node != null;
    }

    private Node search(Item t) {
        Node cur = root;
        while (cur != null) {
            int cmp = t.compareTo(cur.item);
            if (cmp < 0) cur = cur.left;
            else if (cmp > 0) cur = cur.right;
            else return cur;
        }
        return null;
    }

    public void insertItem(Item t) {
        if (t == null) throw new IllegalArgumentException("Null item passed to insertItem");
        if (search(t) != null) throw new IllegalArgumentException("Duplicate item passed to insertItem");
        root = insert(root, t);
        printAVL();
    }

    private Node insert(Node cur, Item t) {
        if (cur == null) return new Node(t);
        int cmp = t.compareTo(cur.item);
        if (cmp < 0) cur.left = insert(cur.left, t);
        else cur.right = insert(cur.right, t);
        update(cur);
        return reBalance(cur);
    }

    private Node min(Node cur) {
        if (cur == null) return null;
        while (cur.left != null) cur = cur.left;
        return cur;
    }

    private Node max(Node cur) {
        if (cur == null) return null;
        while (cur.right != null) cur = cur.right;
        return cur;
    }

    private Node delMin(Node cur) {
        if (cur.left == null) return cur.right;
        cur.left = delMin(cur.left);
        return cur;
    }

    private Node delete(Node cur, Item t) {
        if (cur == null) return null; // if no such item, then do nothing
        int cmp = t.compareTo(cur.item);
        if (cmp < 0) cur.left = delete(cur.left, t);
        else if (cmp > 0) cur.right = delete(cur.right, t);
        else {
            if (cur.right == null) return cur.left; // if only one child on left, return that
            if (cur.left == null) return cur.right; // if only one child on right, return that
            Node temp = cur; // if two children
            cur = min(temp.right);
            cur.right = delMin(temp.right); // delete successor
            cur.left = temp.left;
        }
        update(cur);
        return reBalance(cur);
    }

    public void delete(Item t) {
        if (t == null) throw new IllegalArgumentException("Null item passed to delete");
        root = delete(root, t);
        printAVL();
    }

    private boolean isLeaf(Node x) {
        return x.left == null && x.right == null;
    }

    public void printAVL() {
        preOrderWithParens(root);
        System.out.println();
    }

    private void preOrderWithParens(Node cur) {
        if (cur == null) return;
        boolean isLeaf = isLeaf(cur);
        System.out.print(cur.item);
        if (!isLeaf) System.out.print("(");
        preOrderWithParens(cur.left);
        if (!isLeaf) System.out.print(")(");
        preOrderWithParens(cur.right);
        if (!isLeaf) System.out.print(")");
    }
}
