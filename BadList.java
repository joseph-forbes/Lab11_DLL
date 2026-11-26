import java.util.Iterator;
import java.util.ListIterator;

/**
  * "Bad" implementation of the IndexedUnsortedList interface.
 *
  * @param <E> - type of elements held in this collection
  * @author CPSC 221
  */
public class BadList<E> implements IndexedUnsortedList<E> {

	@Override
	public E removeFirst() {
		return null;
	}

	@Override
	public E removeLast() {
		return null;
	}

	@Override
	public E remove(E element) {
		return null;
	}

	@Override
	public E first() {
		return null;
	}

	@Override
	public E last() {
		return null;
	}

	@Override
	public boolean contains(E target) {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public int size() {
		return -1;
	}

	@Override
	public void addToFront(E element) {
		
	}

	@Override
	public void addToRear(E element) {
		
	}

	@Override
	public void addAfter(E element, E target) {
		
	}

	@Override
	public void add(int index, E element) {
		
	}

	@Override
	public void set(int index, E element) {
		
	}

	@Override
	public void add(E element) {
		
	}

	@Override
	public E get(int index) {
		return null;
	}

	@Override
	public int indexOf(E element) {
		return 0;
	}

	@Override
	public E remove(int index) {
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		return null;
	}

	@Override
	public ListIterator<E> listIterator() {
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int startingIndex) {
		return null;
	}
}
