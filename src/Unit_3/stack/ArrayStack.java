package Unit_3.stack;

/**
 * Created by nirpis.hu on 2017/8/24.
 */
public class ArrayStack<AnyType> {

    private int theSize;

    private AnyType [] theItem;

    private final int DEFAULT_CAPACITY = 10;

    private final int BOTTOM = -1;

    private int top = -1;

    public int size() {
        return theSize;
    }

    public void doClear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
        top = BOTTOM;
    }

    public ArrayStack() {
        doClear();
    }

    public void push(AnyType x) {
        if (theItem.length == theSize)
            ensureCapacity(size() * 2 + 1);
        theItem[size()] = x;
        top++;
    }

    public boolean isEmpty() {
        if (top == BOTTOM)
            return true;
        return false;
    }

    public AnyType top() {
        return theItem[top];
    }

    public AnyType pop() {
        AnyType returnVal = theItem[top];
        top--;
        return returnVal;
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize)
            return;
        AnyType[] old = theItem;
        theItem = (AnyType[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++)
            theItem[i] = old[i];
    }

}
