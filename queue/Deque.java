import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
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

	public Deque() {
		size = 0;
		head = new LinkNode();
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void addFirst(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		LinkNode node = new LinkNode();
		node.item = item;
		node.next = head.next;
		head.next = node;
		size++;
	}

	public void addLast(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		LinkNode node = new LinkNode();
		node.item = item;
		LinkNode p = head;
		while (p.next != null) {
			p = p.next;
		}
		p.next = node;
		size++;
	}

	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		LinkNode first = head.next;
		head.next = first.next;
		first.next = null;
		size--;
		return first.item;
	}

	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		LinkNode lastPrev = head;
		while (lastPrev.next.next != null) {
			lastPrev = lastPrev.next;
		}
		LinkNode last = lastPrev.next;
		lastPrev.next = null;
		size--;
		return last.item;
	}

	public Iterator<Item> iterator() {
		return new ListIterator(head);
	}

	private class ListIterator implements Iterator<Item> {
		
		private LinkNode currnode;

		public ListIterator(LinkNode head) {
			currnode = head.next;
		}

		public boolean hasNext() {
			return currnode != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Item item = currnode.item;
			currnode = currnode.next;
			return item;
		}
	}

	public static void main(String[] args) {
		return;
	}
}