package spanningUSA;

public class UnionFind {
	private int[] inUnion;

	public UnionFind(int n) {
		inUnion = new int[n];
		for (int i = 0; i < n; i++)
			inUnion[i] = i;
	}

	/**
	 * Returns the unions of node n
	 * 
	 * @param n
	 * @return
	 */
	public int find(int n) {
		return inUnion[n];
	}

	/**
	 * Merges the unions of node a and b
	 * 
	 * @param a
	 * @param b
	 */
	public void union(int a, int b) {
		int u = inUnion[b];
		for (int i = 0; i < inUnion.length; i++)
			if (inUnion[i] == u)
				inUnion[i] = inUnion[a];
	}
}
