/*
 * Data structure in which a node must be greater than or equal to/less than or equal to its children.
 * Has the ability to change the priority of nodes.
 *
 * Time complexity:
 *  - Remove max O(log N)
 *  - Get max: O(1)
 *  - Search: O(N)
 */

package codebook.datastructures;

public class BinaryHeapExtended {
  private Integer[] heap, toId, toPos;
  private int index;
  private int size;

  public BinaryHeapExtended() {
    index = 0;
    size = 1;
    heap = new Integer[1];
    toId = new Integer[1];
    toPos = new Integer[1];
  }

  public static void main(String[] args) {
    BinaryHeapExtended h = new BinaryHeapExtended();
    for (int x = 0; x < 100; x++)
      h.add(x, (int) (Math.random() * 100));
    for (int x = 0; x < 100; x++)
      h.changePriority(x, (int) (Math.random() * 100));
    for (int x = 0; x < 100; x++) {
      Pair res = h.remove();
      System.out.println(res.first + " " + res.second);
    }
  }

  public boolean isEmpty() {
    return index == 0;
  }

  public int size() {
    return index;
  }

  public void add(Integer id, Integer item) {
    if (item == null)
      throw new NullPointerException();
    if (index + 1 > size)
      addSize();
    heap[index] = item;
    toId[index] = id;
    toPos[id] = index;
    heapifyUp(index++);
  }

  public Pair remove() {
    if (isEmpty())
      throw new java.util.NoSuchElementException();
    Integer removedId = toId[0];
    Integer removedValue = heap[0];
    heap[0] = heap[--index];
    toId[0] = toId[index];
    toPos[toId[0]] = 0;
    if (index < size / 4)
      removeSize();
    heapifyDown(0);
    return new Pair(removedId, removedValue);
  }

  public Pair peek() {
    if (isEmpty())
      throw new java.util.NoSuchElementException();
    return new Pair(toId[0], heap[0]);
  }

  public void changePriority(int id, int value) {
    int pos = toPos[id];
    if (heap[pos] < value) {
      heap[pos] = value;
      heapifyDown(pos);
    } else if (heap[pos] > value) {
      heap[pos] = value;
      heapifyUp(pos);
    }
  }

  private void heapifyDown(int i) {
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

  private void heapifyUp(int i) {
    while (i > 0) {
      if (heap[i].compareTo(heap[(i - 1) >> 1]) < 0)
        swap(i, (i - 1) >> 1);
      i = (i - 1) >> 1;
    }
  }

  private void swap(int i, int j) {
    Integer temp = heap[i];
    heap[i] = heap[j];
    heap[j] = temp;

    temp = toId[i];
    toId[i] = toId[j];
    toId[j] = temp;

    toPos[toId[i]] = i;
    toPos[toId[j]] = j;
  }

  private void addSize() {
    size *= 2;
    int maxId = 0;
    for (int i = 0; i < index; i++)
      maxId = Math.max(maxId, toId[i]);
    Integer[] newQueue = new Integer[size];
    Integer[] newToId = new Integer[size];
    Integer[] newToPos = new Integer[Math.max(size, maxId + 1)];
    for (int x = 0; x < heap.length; x++) {
      newQueue[x] = heap[x];
      newToId[x] = toId[x];
      newToPos[x] = toPos[x];
    }
    heap = newQueue;
    toId = newToId;
    toPos = newToPos;
  }

  private void removeSize() {
    size /= 2;
    int maxId = 0;
    for (int i = 0; i < index; i++)
      maxId = Math.max(maxId, toId[i]);
    Integer[] newQueue = new Integer[size];
    Integer[] newToId = new Integer[size];
    Integer[] newToPos = new Integer[Math.max(size, maxId + 1)];
    for (int x = 0; x < size; x++) {
      newQueue[x] = heap[x];
      newToId[x] = toId[x];
      newToPos[x] = toPos[x];
    }
    heap = newQueue;
    toId = newToId;
    toPos = newToPos;
  }

  class Pair {
    int first, second;

    Pair(int first, int second) {
      this.first = first;
      this.second = second;
    }
  }
}
