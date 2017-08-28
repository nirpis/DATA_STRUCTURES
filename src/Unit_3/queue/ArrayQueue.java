package Unit_3.queue;

/**
 * Created by nirpis on 2017/8/25.
 */
public class ArrayQueue<AnyType> {

    private AnyType [] theItem = (AnyType[]) new Object[50];

    private int front = 0;

    private int rear = 0;

    public void initQueue() {
        front = 0;
        rear = 0;
        theItem = (AnyType[]) new Object[50];
    }

    public boolean isQueueFull() {
        if (((rear) % theItem.length + 1 ) == front)
            return true;
        return false;
    }

    public boolean isQueueEmpty() {
        if (front == rear)
            return true;
        return false;
    }

    public void enQueue(AnyType x) {
        if (!isQueueFull()) {
            theItem[rear%theItem.length] = x;
            rear++;
            return;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public AnyType deQueue() {
        if(!isQueueEmpty()) {
            int val = front%theItem.length;
            front++;
            return theItem[val];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

}
