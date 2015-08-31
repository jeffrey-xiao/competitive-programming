/*
 * A deque is a data structure that allows in the deletion and insertion of elements from both sides.
 */

package codebook.datastructures;

import java.util.Iterator;

public class Deque <Item> implements Iterable<Item> {
	private Node<Item> first;
	private Node<Item> last;
	private int size;

	@SuppressWarnings ("hiding")
	private class Node <Item> {
		private Item value = null;
		private Node<Item> next = null;
		private Node<Item> prev = null;

		Node (Item value, Node<Item> next, Node<Item> prev) {
			this.value = value;
			this.next = next;
			this.prev = prev;
		}
	}

	public Deque () {
		first = null;
		last = null;
		size = 0;
	}

	public boolean isEmpty () {
		return size == 0;
	}

	public int size () {
		return size;
	}

	public void addFirst (Item item) {
		if (item == null)
			throw new NullPointerException();
		size++;

		if (first == null) {
			Node<Item> newFirst = new Node<Item>(item, null, null);
			first = newFirst;
			last = newFirst;
		} else {
			Node<Item> newFirst = new Node<Item>(item, first, null);
			first.prev = newFirst;
			first = newFirst;
		}
	}

	public void addLast (Item item) {
		if (item == null)
			throw new NullPointerException();
		size++;
		if (last == null) {
			Node<Item> newLast = new Node<Item>(item, null, null);
			first = newLast;
			last = newLast;
		} else {
			Node<Item> newLast = new Node<Item>(item, null, last);
			last.next = newLast;
			last = newLast;
		}
	}

	public Item removeFirst () {
		if (size == 0)
			throw new java.util.NoSuchElementException();
		size--;
		Item returnValue = first.value;
		first = first.next;
		if (first == null)
			last = null;
		else
			first.prev = null;
		return returnValue;
	}

	public Item removeLast () {
		if (size == 0)
			throw new java.util.NoSuchElementException();
		size--;
		Item returnValue = last.value;
		last = last.prev;
		if (last == null)
			first = null;
		else
			last.next = null;
		return returnValue;
	}

	public Iterator<Item> iterator () {
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {
		private Node<Item> current = first;

		@Override
		public boolean hasNext () {
			return current != null;
		}

		@Override
		public Item next () {
			if (current == null)
				throw new java.util.NoSuchElementException();
			Item item = current.value;
			current = current.next;
			return item;
		}

		@Override
		public void remove () {
			throw new UnsupportedOperationException();
		}
	}

	public static void main (String[] args) {
		Deque<Integer> d = new Deque<Integer>();
		d.addFirst(1);
		d.addFirst(2);
		System.out.println(d.first.value);
		System.out.println(d.first.next.value);
		d.addLast(3);
		System.out.println(d.removeLast());
		System.out.println(d.removeLast());
		d.addLast(4);
		System.out.println(d.removeLast());
	}
}
