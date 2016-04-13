package spanningUSA;

public class UnionFind {
	private int[] inUnion;
	// private LinkedList<Integer>[] unions;

	public UnionFind(int n) {
		inUnion = new int[n];
		// unions = new LinkedList[n];
		for (int i = 0; i < n; i++)
			// unions[i] = new LinkedList<Integer>();
			// unions[i].add(i);
			inUnion[i] = i;
	}

	public int find(int n) {
		return inUnion[n];
	}

	public void union(int a, int b) {
		// unions[a].addAll(unions[b]);
		// unions[b].clear();
		int u = inUnion[b];
		for (int i = 0; i < inUnion.length; i++)
			if (inUnion[i] == u)
				inUnion[i] = inUnion[a];
	}
}
