import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUDoubleLinkedList<E> implements IndexedUnsortedList<E> {

    private BidirectionalNode<E> front;
    private BidirectionalNode<E> rear;
    private int count;
    private int modCount;

    // constructor    
    public IUDoubleLinkedList() {
       front = null;
       rear = null;
       count = 0;
       modCount = 0;
    }

    //creates a new node and connects it to the previous front node.
    @Override
    public void addToFront(E element) {
        BidirectionalNode<E> newNode = new BidirectionalNode<>(element);

        newNode.setNext(front);

        if (front == null) {
            rear = newNode;
        } else {
            front.setPrevious(newNode);
        }

        front = newNode;
        count++;
        modCount++;
    }

    //creates a new node and connects it to the previous rear.
    @Override
    public void addToRear(E element) {
        BidirectionalNode<E> newNode = new BidirectionalNode<>(element);

        newNode.setPrevious(rear);

        if (rear == null) {
            front = newNode;
        } else {
            rear.setNext(newNode);
        }

        rear = newNode;
        count++;
        modCount++;
    }

    //re-uses addToRear() because it is supposed to add it to the back of the list.
    @Override
    public void add(E element) {
        addToRear(element);
    }

    //add after checks for a target element to insert after.
    @Override
    public void addAfter(E element, E target) {
        BidirectionalNode<E> current = front;

        // Search while method to check for element in the list.
        while (current != null && !current.getElement().equals(target)) {
            current = current.getNext();
        }

        //if target isn't found.
        if (current == null) {throw new NoSuchElementException();}

        // If target locaiton is the last node, reuse addToRear() again.
        if (current == rear) {
            addToRear(element);
            return;
        }

        // Create the new node
        BidirectionalNode<E> newNode = new BidirectionalNode<>(element);
        // Connect the new node
        newNode.setPrevious(current);
        newNode.setNext(current.getNext());
        // Update the nodes around it.
        current.getNext().setPrevious(newNode);
        current.setNext(newNode);
        count++;
        modCount++;
    }

    @Override
    public void add(int index, E element) {
        // Check that index is valid
        if (index < 0 || index > count) {throw new IndexOutOfBoundsException();}

        // If inserting at the front
        if (index == 0) {
            addToFront(element);
            return;
        }

        // if inserting at the rear
        if (index == count) {
            addToRear(element);
            return;
        }

        // Find the node at the called index.
        BidirectionalNode<E> current = front;

        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        // Create the new node
        BidirectionalNode<E> newNode = new BidirectionalNode<>(element);

        // Connect the new node.
        newNode.setPrevious(current.getPrevious());
        newNode.setNext(current);

        // Update the nearby nodes
        current.getPrevious().setNext(newNode);
        current.setPrevious(newNode);
        count++;
        modCount++;
    }

    @Override
    
    public E removeFirst() {
        // Check if the list is empty first!
        if (isEmpty()) {throw new NoSuchElementException();}

        // Save the element we are removing
        E element = front.getElement();

        // Move front to the next node
        front = front.getNext();

        // Update the remaining references
        if (front == null) {
            rear = null;
        } else {
            front.setPrevious(null);
        }
        count--;
        modCount++;
        return element;
}

    @Override
    public E removeLast() {
        // Check if the list is empty!
        if (isEmpty()) {throw new NoSuchElementException();}

        // Save the element we are removing
        E element = rear.getElement();

        // Move rear to previous
        rear = rear.getPrevious();

    // Update the remaining pointers
        if (rear == null) {
            front = null;
        } else {
            rear.setNext(null);
        }   

        count--;
        modCount++;
        return element;
    }

    @Override
    public E remove(E element) {
        // Start at the front of the list
        BidirectionalNode<E> current = front;

        // Search for the element using comparison
        while (current != null && !current.getElement().equals(element)) {
            current = current.getNext();
        }

        //If element was not found
        if (current == null) {throw new NoSuchElementException();}

        //If remove the first node
            if (current == front) {
            return removeFirst();
        }

        //If removing the last node
        if (current == rear) {
            return removeLast();
        }

        // Save the element being removed
        E removedElement = current.getElement();

         // Get the next and previous nodes.
        BidirectionalNode<E> previous = current.getPrevious();
        BidirectionalNode<E> next = current.getNext();

      // Connect the next and previous node.
        previous.setNext(next);
        next.setPrevious(previous);
        count--;
        modCount++;
        return removedElement;
    }

     @Override
    public E remove(int index) {
        // Check for a valid index
        if (index < 0 || index >= count) {throw new IndexOutOfBoundsException();}

    //If removing the first node
        if (index == 0) { return removeFirst();}

    // If removing the last node
        if (index == count - 1) {return removeLast();}

        // Move to the called index
        BidirectionalNode<E> current = front;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        // Save the element being removed
        E removedElement = current.getElement();

        // Get the previous and next nodes
        BidirectionalNode<E> previous = current.getPrevious();
        BidirectionalNode<E> next = current.getNext();

        // Connect neighboring nodes together
        previous.setNext(next);
        next.setPrevious(previous);
        count--;
        modCount++;

    return removedElement;
    }

    @Override
    public void set(int index, E element) {
        // Check for a valid index
        if (index < 0 || index >= count) {throw new IndexOutOfBoundsException();}

        //Move to Index
        BidirectionalNode<E> current = front;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        current.setElement(element);
        modCount++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= count) {throw new IndexOutOfBoundsException();}

        // Start at the front of the list, move to index
        BidirectionalNode<E> current = front;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current.getElement();
    }

    @Override
    public int indexOf(E element) {
        BidirectionalNode<E> current = front;
        int index = 0;

        // check through the list and see if we find the element
        while (current != null) {
            if (current.getElement().equals(element)) {
                return index;
            }

            current = current.getNext();
            index++;
        }

        //If element was not found return -1 for contains method.
        return -1;
    }

    @Override
    public E first() {
        if(isEmpty()){throw new NoSuchElementException();}
       return front.getElement();
    }

    @Override
    public E last() {
        if(isEmpty()){throw new NoSuchElementException();}
        return rear.getElement();
    }

    @Override
    public boolean contains(E target) {
        return indexOf(target) != -1;
    }

    @Override
    public boolean isEmpty() {
       return count == 0;
    }

    @Override
    public int size() {
        return count; 
    }

    //Concatination toString.  Almost forgot this.  
    public String toString() {
        String result = "[";
        BidirectionalNode<E> current = front;

        while (current != null) {
            result += current.getElement();
            current = current.getNext();
            if (current != null) {
                result += ", ";
            }
        }
        result += "]";
        return result;
    }


    private class IUDoubleLinkedListIterator implements ListIterator<E> {

        private ListIteratorCursor cursor;
        private int iterModCount;
        private E lastSeen;
        private enum LastCalled {
            PREVIOUS, NEXT, NULL
        }
        private LastCalled lastCalled;

        public IUDoubleLinkedListIterator() {
            this(0);
        }
        public IUDoubleLinkedListIterator(int startingIndex) {
            this.cursor = new ListIteratorCursor(startingIndex);
            lastSeen = null;
            lastCalled = LastCalled.NULL;
            iterModCount = modCount;
        }

        @Override
        public void add(E element) {
            if(iterModCount != modCount) throw new ConcurrentModificationException();

            IUDoubleLinkedList.this.add(cursor.getVirtualIndex(), element);
            lastSeen = null;
            lastCalled = LastCalled.NULL;
            iterModCount++;
        }

        @Override
        public boolean hasNext() {
            if(iterModCount != modCount) throw new ConcurrentModificationException();
            return cursor.getVirtualIndex() < size();
        }

        @Override
        public boolean hasPrevious() {
            if(iterModCount != modCount) throw new ConcurrentModificationException();

            return cursor.getVirtualIndex() > 0;
        }

        @Override
        public E next() {
            System.out.println(IUDoubleLinkedList.this.toString());
            if(iterModCount != modCount) throw new ConcurrentModificationException();
            if(!hasNext()) throw new NoSuchElementException();
            
            E element = get(cursor.getVirtualIndex());
            lastSeen = element;
            lastCalled = LastCalled.NEXT;
            cursor.increment();
            return element;
        }

        @Override
        public int nextIndex() {
            if(iterModCount != modCount) throw new ConcurrentModificationException();
            return cursor.getVirtualIndex();
        }

        @Override
        public E previous() {
            if(iterModCount != modCount) throw new ConcurrentModificationException();
            cursor.decrement();
            if(cursor.getVirtualIndex() < 0) throw new NoSuchElementException();

            E element = get(cursor.getVirtualIndex()); // Decrement, then return next
            lastSeen = element;
            lastCalled = LastCalled.PREVIOUS;
            return element;
        }

        @Override
        public int previousIndex() {
            if(iterModCount != modCount) throw new ConcurrentModificationException();
            return cursor.getVirtualIndex() - 1;
        }

        @Override
        public void remove() {
            if(iterModCount != modCount) throw new ConcurrentModificationException();
            if(lastSeen == null) throw new IllegalStateException();
            
            IUDoubleLinkedList.this.remove(lastSeen);
            if(lastCalled == LastCalled.NEXT) cursor.decrement();
            lastSeen = null;
            lastCalled = LastCalled.NULL;
            iterModCount++;
        }

        @Override
        public void set(E element) {
            if(iterModCount != modCount) throw new ConcurrentModificationException();
            if(lastSeen == null) throw new IllegalStateException();
            
            IUDoubleLinkedList.this.set(indexOf(lastSeen), element);
            lastSeen = element;
            iterModCount++;
        }
    
        
    }

    @Override
    public Iterator<E> iterator() {
        return new IUDoubleLinkedListIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new IUDoubleLinkedListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int startingIndex) {
        if(startingIndex > size() || startingIndex < 0) throw new IndexOutOfBoundsException();
        
        return new IUDoubleLinkedListIterator(startingIndex);
    }
    
}
