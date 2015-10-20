/*
 * An implementation of a hash map using buckets.
 */

package codebook.datastructures;

public class HashMap {
	private class HashEntry {
		Integer key, value;
		HashEntry next;

		HashEntry (Integer key, Integer value) {
			this.key = key;
			this.value = value;
			next = null;
		}
	}

	private int TABLE_SIZE;
	private int size;
	private HashEntry[] table;

	public HashMap (int TABLE_SIZE) {
		this.TABLE_SIZE = TABLE_SIZE;
		this.size = 0;
		this.table = new HashEntry[TABLE_SIZE];
	}

	public int getSize () {
		return size;
	}

	public void clear () {
		size = 0;
		for (int i = 0; i < TABLE_SIZE; i++)
			table[i] = null;
	}

	public Integer get (Integer key) {
		int hash = key.hashCode() % TABLE_SIZE;
		HashEntry curr = table[hash];
		while (curr != null) {
			if (curr.key == key)
				return curr.value;
			curr = curr.next;
		}
		return null;
	}

	public void insert (Integer key, Integer value) {
		int hash = key.hashCode() % TABLE_SIZE;
		remove(key);
		HashEntry next = table[hash];
		table[hash] = new HashEntry(key, value);
		table[hash].next = next;
	}

	public Integer remove (Integer key) {
		int hash = key.hashCode() % TABLE_SIZE;
		HashEntry curr = table[hash];
		if (curr == null)
			return null;
		else if (curr.key == key) {
			table[hash] = null;
			return curr.value;
		}
		while (curr.next != null) {
			if (curr.next.key == key) {
				Integer ret = curr.next.value;
				curr.next = curr.next.next;
				return ret;
			}
			curr = curr.next;
		}
		return null;
	}

	public static void main (String[] args) {
		HashMap hm = new HashMap(50);
		Integer[] added = new Integer[100];
		for (int i = 0; i < 100; i++) {
			Integer add1 = (int) (Math.random() * 100);
			added[i] = add1;
			Integer add2 = (int) (Math.random() * 100);
			hm.insert(add1, add2);
			assert (hm.get(add1) == add2);
		}
		for (int i = 0; i < 100; i++) {
			hm.remove(added[i]);
			assert (hm.get(added[i]) == null);
		}
	}
}
