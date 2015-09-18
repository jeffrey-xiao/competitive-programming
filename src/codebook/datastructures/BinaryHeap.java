/*
 * Data structure in such a node must be greater than or equal to/less than or equal to its children
 */

package codebook.datastructures;

public class BinaryHeap {
	private Integer[] heap;
	private int index;
	private int size;

	public BinaryHeap () {
		index = 0;
		size = 1;
		heap = new Integer[1];
	}

	public boolean isEmpty () {
		return index == 0;
	}

	public int size () {
		return index;
	}

	public void add (Integer item) {
		if (item == null)
			throw new NullPointerException();
		if (index + 1 > size)
			addSize();
		heap[index] = item;
		heapifyUp(index++);
	}

	public Integer remove () {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		Integer ret = heap[0];
		heap[0] = heap[--index];
		if (index < size / 4)
			removeSize();
		heapifyDown(0);
		return ret;
	}

	public Integer peek () {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		return heap[0];
	}

	private void heapifyDown (int i) {
		if (heap[i] == null)
			return;
		int index1 = (i << 1) + 1;
		int index2 = (i << 1) + 2;
		int cmp1 = index1 >= size || heap[index1] == null ? 0 : heap[i].compareTo(heap[index1]);
		int cmp2 = index2 >= size || heap[index2] == null ? 0 : heap[i].compareTo(heap[index2]);
		if (cmp1 > 0 && cmp2 > 0) {
			if (heap[index1].compareTo(heap[index2]) < 0) {
				Integer temp = heap[i];
				heap[i] = heap[index1];
				heap[index1] = temp;
				heapifyDown(index1);
			} else {
				Integer temp = heap[i];
				heap[i] = heap[index2];
				heap[index2] = temp;
				heapifyDown(index2);
			}
		} else if (cmp1 > 0) {
			Integer temp = heap[i];
			heap[i] = heap[index1];
			heap[index1] = temp;
			heapifyDown(index1);
		} else if (cmp2 > 0) {
			Integer temp = heap[i];
			heap[i] = heap[index2];
			heap[index2] = temp;
			heapifyDown(index2);
		}
	}
	
	private void heapifyUp (int i) {
		while (i > 0) {
			if (heap[i].compareTo(heap[(i-1) >> 1]) < 0) {
				Integer temp = heap[i];
				heap[i] = heap[(i - 1) >> 1];
				heap[(i-1) >> 1] = temp;
			}
			i = (i-1) >> 1;
		}
	}
	
	private void addSize () {
		size *= 2;
		Integer[] newQueue = new Integer[size];
		for (int x = 0; x < heap.length; x++)
			newQueue[x] = heap[x];
		heap = newQueue;
	}

	private void removeSize () {
		size /= 2;
		Integer[] newQueue = new Integer[size];
		for (int x = 0; x < newQueue.length; x++)
			newQueue[x] = heap[x];
		heap = newQueue;
	}
	public static void main (String[] args) {
		BinaryHeap h = new BinaryHeap();
		for (int x = 0; x < 100; x++)
			h.add((int)(Math.random()*100));
		for (int x = 0; x < 100; x++) {
			System.out.print(h.remove() + " ");
		}
	}
}
