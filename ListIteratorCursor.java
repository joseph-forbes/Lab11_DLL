public class ListIteratorCursor {
    private int virtualIndex;

    public ListIteratorCursor() {
        this(0);
    }
    public ListIteratorCursor(int startingIndex) {
        this.virtualIndex = startingIndex;
    }

    public int getVirtualIndex() {
        return virtualIndex;
    }

    public int getNext() {
        return virtualIndex + 1;
    }
    public int getPrevious() {
        return virtualIndex - 1;
    }

    public int increment() {
        return virtualIndex++;        
    }
    public int decrement() {
        return virtualIndex--;
    }
}
