import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Permutation {

	private RandomizedQueue queue;

	public Permutation() {
		queue = new RandomizedQueue<String>();
	}

	public static void main(String[] args) {
		int k;
		k = Integer.parseInt(args[0]);
		if (k <= 0) {
			return;
		}
		Permutation perm = new Permutation();
		while (!StdIn.isEmpty()) {
			String inputString = StdIn.readString();
			perm.queue.enqueue(inputString);
		}
		Iterator iter = perm.queue.iterator();
		while (iter.hasNext()) {
			System.out.print(iter.next() + "\n");
			k--;
			if (k <= 0) {
				break;
			}
		}
		
		return;
	}
}