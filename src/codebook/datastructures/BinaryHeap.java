/*
 * Data structure in which a node must be greater than or equal to/less than or equal to its children.
 *
 * Time complexity:
 *  - Remove max O(log N)
 *  - Get max: O(1)
 *  - Search: O(N)
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
    int child = (i << 1) + 1;
    if (child >= index)
      return;
    if (child + 1 < index && heap[child + 1] < heap[child])
      child++;
    if (heap[i] <= heap[child])
      return;
    swap(i, child);
    heapifyDown(child);
  }

  private void heapifyUp (int i) {
    while (i > 0) {
      if (heap[i].compareTo(heap[(i - 1) >> 1]) < 0)
        swap(i, (i - 1) >> 1);
      i = (i - 1) >> 1;
    }
  }

  private void swap (int i, int j) {
    Integer temp = heap[i];
    heap[i] = heap[j];
    heap[j] = temp;
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
      h.add((int)(Math.random() * 100));
    for (int x = 0; x < 100; x++) {
      System.out.print(h.remove() + " ");
    }
  }
}
