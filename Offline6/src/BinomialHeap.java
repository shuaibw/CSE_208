import java.util.ArrayList;

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

    public BinomialHeap() {
    }

    public int findMax() { // ceil(lgn) + 1
        Node cur = head;
        int max = head.key;
        while (cur != null) {
            max = Integer.max(max, cur.key);
            cur = cur.sibling;
        }
        return max;
    }

    public int extractMax() throws Exception {
        if (head == null) throw new Exception("Cannot extract max: empty heap");
        int max = Integer.MIN_VALUE;
        Node maxNode = null;
        Node cur = head;
        while (cur != null) {
            if (cur.key <= max) continue;
            max = cur.key;
            maxNode = cur;
            cur = cur.sibling;
        }
        Node prev = null;
        cur = head;
        while (cur != maxNode) {
            prev = cur;
            cur = cur.sibling;
        }
        if(prev==null) head=maxNode.sibling;
        else prev.sibling = maxNode.sibling;
        BinomialHeap h = new BinomialHeap();
        Node child = maxNode.child;
        while(child.sibling!=null){
            child.parent=null;
            Node temp=child.sibling;
            child.sibling=child;

        }
    }

    public void insert(int key) {
        BinomialHeap h = new BinomialHeap();
        h.head = new Node(key);
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

    public static void main(String[] args) {

    }
}

