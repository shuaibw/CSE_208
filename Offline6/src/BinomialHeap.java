import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class BinomialHeap {
    private class Node {
        int key;
        int degree;
        Node parent;
        Node child; //leftmost child;
        Node sibling; //immediate right sibling

        Node(int key) {
            this.key = key;
        }
    }

    private Node head;
    private final HashMap<Integer, Node> map;

    public BinomialHeap() {
        map = new HashMap<>();
    }

    public int findMax() throws Exception { // ceil(lgn) + 1
        if (isEmpty()) throw new Exception("Cannot find max: empty heap");
        Node cur = head;
        int max = head.key;
        while (cur != null) {
            max = Integer.max(max, cur.key);
            cur = cur.sibling;
        }
        return max;
    }

    public int extractMax() throws Exception {
        if (isEmpty()) throw new Exception("Cannot extract max: empty heap");
        Node max = splitMax();
        if (max.child == null) {
            map.remove(max.key);
            return max.key;
        }
        BinomialHeap h = new BinomialHeap();
        Node cur = max.child;
        Node prev = null;
        Node next = cur.sibling;
        max.child = null;
        while (next != null) {
            cur.sibling = prev;
            prev = cur;
            cur = next;
            next = next.sibling;
        }
        cur.sibling = prev;
        h.head = cur;
        this.head = union(this, h).head;
        map.remove(max.key);
        return max.key;
    }

    public void insert(int key) throws Exception {
        if (map.containsKey(key)) throw new Exception("Cannot insert duplicate key");
        BinomialHeap h = new BinomialHeap();
        h.head = new Node(key);
        map.put(key, h.head);
        this.head = union(this, h).head;
    }

    public BinomialHeap union(BinomialHeap h1, BinomialHeap h2) {
        BinomialHeap h = new BinomialHeap();
        h.head = merge(h1.head, h2.head);
        if (h.head == null) return h;
        Node prev = null;
        Node cur = h.head;
        Node next = h.head.sibling;
        while (next != null) {
            //Move forward: Two ascending order trees or three same order trees
            if (cur.degree != next.degree || (next.sibling != null && next.sibling.degree == cur.degree)) {
                prev = cur;
                cur = next;
            } else {
                if (next.key <= cur.key) {
                    cur.sibling = next.sibling; //jump over next
                    link(next, cur); //add next as child to cur
                } else {
                    if (prev == null) h.head = next;
                    else prev.sibling = next;
                    link(cur, next);
                    cur = next;
                }
            }
            next = cur.sibling;
        }
        return h;
    }

    private void link(Node y, Node z) { //connect y as z's left child
        y.parent = z;
        y.sibling = z.child;
        z.child = y;
        z.degree++;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void increaseKey(int prevKey, int newKey) {
        Node cur = map.get(prevKey);
        cur.key = newKey;
        bubbleUp(cur);
    }

    public void print() {
        Node cur = head;
        while (cur != null) {
            System.out.printf("Binomial Tree, B%d%n", cur.degree);
            bfsRoot(cur);
            cur = cur.sibling;
        }
    }

    private void bfsRoot(Node root) {
        LinkedList<Node> q = new LinkedList<>();
        q.addLast(root);
        int lvlID = 0;
        while (!q.isEmpty()) {
            int lvlSize = q.size();
            System.out.printf("Level %d: ", lvlID++);
            while (lvlSize-- != 0) {
                Node cur = q.removeFirst();
                System.out.print(cur.key + " ");
                Node child = cur.child;
                while (child != null) {
                    q.addLast(child);
                    child = child.sibling;
                }
            }
            System.out.println();
        }
    }

    private void bubbleUp(Node cur) {
        Node parent = cur.parent;
        while (parent != null && parent.key < cur.key) {
            int temp = parent.key;
            parent.key = cur.key;
            cur.key = temp;
            cur = parent;
            parent = cur.parent;
        }
    }

    private Node splitMax() {
        Node maxNode = head;
        Node cur = head;
        Node prev = null;
        while (cur.sibling != null) {
            if (cur.sibling.key > maxNode.key) {
                prev = cur;
                maxNode = cur.sibling;
            }
            cur = cur.sibling;
        }
        if (prev == null) head = maxNode.sibling;
        else prev.sibling = maxNode.sibling;
        return maxNode;
    }

    private Node merge(Node h1, Node h2) {
        if (h1 == null && h2 == null) return null;
        if (h1 == null) return h2;
        if (h2 == null) return h1;
        Node cur = null, newHead = null;
        while (h1 != null && h2 != null) {
            Node minDeg;
            if (h1.degree < h2.degree) {
                minDeg = h1;
                h1 = h1.sibling;
            } else {
                minDeg = h2;
                h2 = h2.sibling;
            }
            if (cur == null) {
                cur = minDeg;
                newHead = cur;
            } else {
                cur.sibling = minDeg;
                cur = cur.sibling;
            }
        }
        if (h1 != null) cur.sibling = h1;
        else cur.sibling = h2;
        return newHead;
    }

    public static void main(String[] args) throws Exception {
        BinomialHeap h1 = new BinomialHeap();
        Random random = new Random(123);
        for (int i = 0; i < 100; i++) {
            h1.insert(random.nextInt(100));
        }
        h1.print();
    }
}

