import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private final LinkNode head;
	private int size;

	private class LinkNode {
		Item item;
		LinkNode next;

		public LinkNode() {
			item = null;
			next = null;
		}
	}

	public RandomizedQueue() {
		head = new LinkNode();
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void enqueue(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		LinkNode p = head;
		while (p.next != null) {
			p = p.next;
		}
		LinkNode node = new LinkNode();
		node.item = item;
		p.next = node;
		size++;
	}

	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		/*
		LinkNode first = head.next;
		head.next = first.next;
		first.next = null;
		size--;
		return first.item;
		*/
		int number = StdRandom.uniform(1, size+1);
		LinkNode curr;
		LinkNode prev = head;
		for (int i = 1; i <= number - 1; i++) {
			prev = prev.next;
		}
		curr = prev.next;
		prev.next = curr.next;
		size--;
		return curr.item;
	}

	public Item sample() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		LinkNode currnode = head;
		int number = StdRandom.uniform(1, size + 1);
		for (int i = 1; i <= number; i++) {
			currnode = currnode.next;
		}
		return currnode.item;
	}

	public Iterator<Item> iterator() {
		return new ListIterator(head, size);
	}

	private class ListIterator implements Iterator<Item> {
		
		private final LinkNode head;
		private final int size;
		private int curr;
		private int[] order;

		public ListIterator(LinkNode head, int size) {
			this.head = head;
			this.size = size;
			curr = 0;
			order = new int[size];
			for (int i = 0; i < size; i++) {
				order[i] = i;
			}
			StdRandom.shuffle(order);
		}

		public boolean hasNext() {
			return curr < size;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			LinkNode current = head;
			for (int i = 0; i <= order[curr]; i++) {
				current = current.next;
			}
			Item item = current.item;
			curr++;
			return item;
		}
	}

	public static void main(String[] args) {
		/*
		for (int i = 0; i < 20; i++) {
			int number = StdRandom.uniform(11);
			System.out.print(number+"\n");
		}
		*/
		return;
	}
}