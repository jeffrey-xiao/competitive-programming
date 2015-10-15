package codebook.datastructures;

import java.util.*;

public class MinQueue {
	ArrayDeque<Integer> q = new ArrayDeque<Integer>();
	ArrayDeque<Integer> d = new ArrayDeque<Integer>();
	
	void push (Integer i) {
		if (q.isEmpty()) {
			q.offerLast(i);
			d.offerLast(i);
		} else {
			q.offerLast(i);
			while (!d.isEmpty() && d.getLast() < i)
				d.pollLast();
			d.offerLast(i);
		}
	}
	int remove () {
		if (q.isEmpty())
			throw new NoSuchElementException();
		if (q.getFirst() == d.getFirst()) {
			q.pollFirst();
			return d.pollFirst();
		} else {
			return q.pollFirst();
		}
	}
	int getMax () {
		if (d.isEmpty())
			throw new NoSuchElementException();
		return d.peekFirst();
	}
	public static void main (String[] args) {
		MinQueue mq = new MinQueue();
		mq.push(3);
		System.out.println(mq.getMax());
		mq.push(1);
		System.out.println(mq.getMax());
		mq.push(5);
		System.out.println(mq.getMax());
		System.out.println(mq.remove());
		System.out.println(mq.getMax());
		System.out.println(mq.remove());
		System.out.println(mq.getMax());
		System.out.println(mq.remove());
	}
}

