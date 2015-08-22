package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue <Item> implements Iterable<Item> {
	private Item[] queue;
	private int index;
	private int size;

	public RandomizedQueue () {
		index = 0;
		size = 1;
		queue = (Item[]) new Object[1];
	}

	public boolean isEmpty () {
		return index == 0;
	}

	public int size () {
		return index;
	}

	public void enqueue (Item item) {
		if (item == null)
			throw new NullPointerException();
		if (index + 1 > size)
			addSize();
		queue[index++] = item;
	}

	public Item dequeue () {
		if (isEmpty())
			throw new java.util.NoSuchElementException();

		int random = getRandom(index);
		Item i = queue[random];
		queue[random] = queue[--index];
		queue[index] = null;
		if (index < size / 4)
			removeSize();
		return i;
	}

	public Item sample () {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		return queue[getRandom(index)];
	}

	public Iterator<Item> iterator () {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<Item> {
		int i = 0;
		private int[] shuffle = new int[index];

		@Override
		public boolean hasNext () {
			return i < index;
		}

		@Override
		public Item next () {
			if (i == 0) {
				for (int x = 0; x < index; x++)
					shuffle[x] = x;
				for (int x = 0; x < index; x++) {
					int random = getRandom(x + 1);
					int temp = shuffle[x];
					shuffle[x] = shuffle[random];
					shuffle[random] = temp;
				}
			}
			if (i >= index)
				throw new java.util.NoSuchElementException();
			return queue[shuffle[i++]];
		}

		@Override
		public void remove () {
			throw new UnsupportedOperationException();
		}
	}

	private void addSize () {
		size *= 2;
		Item[] newQueue = (Item[]) new Object[size];
		for (int x = 0; x < queue.length; x++)
			newQueue[x] = queue[x];
		queue = newQueue;
	}

	private void removeSize () {
		size /= 2;
		Item[] newQueue = (Item[]) new Object[size];
		for (int x = 0; x < newQueue.length; x++)
			newQueue[x] = queue[x];
		queue = newQueue;
	}

	private int getRandom (int index) {
		int i = (int) (Math.random() * index);
		return i;
	}

	public static void main (String[] args) {
		RandomizedQueue<Double> rq = new RandomizedQueue<Double>();
		for (int x = 0; x < 100; x++)
			rq.enqueue(Math.random());
		for (int x = 0; x < 100; x++) {
			System.out.print(rq.dequeue() + " ");
		}
	}
}