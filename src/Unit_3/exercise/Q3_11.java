package Unit_3.exercise;

/**
 * Created by nirpis.hu on 2017/8/28.
 *
 * Question : 假设单链表使用一个头节点实现，但无尾节点，并假设它只保留对该头节点的引用。编写一个类，包含：
 *            a.返回链表大小的方法
 *            b.打印链表的方法
 *            c.测试值x是否含于链表的方法
 *            d.如果值x尚含于链表，添加值x到该链表的方法
 *            e.如果值x含于链表，将x从该链表删除的方法
 *
 */
public class Q3_11 {

    Q3_11() {
        init();
    }

    public int size() {
        return theSize;
    }

    public void printList() {
        Node<Object> p = head.next;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public boolean contains(Object data) {
        Node<Object> p = head.next;
        while (p != null) {
            if (p.data == data)
                return true;
            else
                p = p.next;
        }
        return false;
    }

    public boolean add(Object data) {
        if (contains(data))
            return false;
        Node<Object> newNode = new Node<>(data);
        newNode.next = head.next;
        head.next = newNode;
        theSize++;
        return true;
    }

    public boolean remove(Object data) {
        if (!contains(data))
            return false;
        Node<Object> p = head.next;
        Node<Object> trailer = head;
        while(!p.data.equals(data)) {
            trailer = p;
            p = p.next;
        }
        trailer.next = p.next;
        theSize--;
        return true;
    }

    private class Node<Object> {
        Object data;
        Node next;

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
        Node(Object data) {
            this(data, null);
        }
        Node() {
            this(null, null);
        }
    }

    private Node<Object> head;
    private int theSize;

    void init() {
        theSize = 0;
        head = new Node<>();
        head.next = null;
    }


}
