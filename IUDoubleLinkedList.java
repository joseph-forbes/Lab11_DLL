import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUDoubleLinkedList<E> implements IndexedUnsortedList<E> {

    private int modCount;

    @Override
    public void addToFront(E element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addToFront'");
    }

    @Override
    public void addToRear(E element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addToRear'");
    }

    @Override
    public void add(E element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void addAfter(E element, E target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAfter'");
    }

    @Override
    public void add(int index, E element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public E removeFirst() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFirst'");
    }

    @Override
    public E removeLast() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeLast'");
    }

    @Override
    public E remove(E element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public E remove(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public void set(int index, E element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }

    @Override
    public E get(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public int indexOf(E element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'indexOf'");
    }

    @Override
    public E first() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'first'");
    }

    @Override
    public E last() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'last'");
    }

    @Override
    public boolean contains(E target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    private class IUDoubleLinkedListIterator implements ListIterator<E> {

        private ListIteratorCursor cursor;
        private int iterModCount;
        private E lastSeen;

        public IUDoubleLinkedListIterator() {
            this(0);
        }
        public IUDoubleLinkedListIterator(int startingIndex) {
            this.cursor = new ListIteratorCursor(startingIndex);
            lastSeen = null;
            iterModCount = modCount;
        }

        @Override
        public void add(E element) {
            if(iterModCount != modCount) throw new ConcurrentModificationException();

            IUDoubleLinkedList.this.add(cursor.getVirtualIndex(), element);
            lastSeen = null;
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
            if(iterModCount != modCount) throw new ConcurrentModificationException();
            if(!hasNext()) throw new NoSuchElementException();
            
            E element = get(cursor.getVirtualIndex());
            lastSeen = element;
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
            if(!hasPrevious()) throw new NoSuchElementException();
            
            E element = get(cursor.decrement()); // Decrement, then return next
            lastSeen = element;
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
            lastSeen = null;
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
        return listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int startingIndex) {
        return new IUDoubleLinkedListIterator(startingIndex);
    }
    
}
