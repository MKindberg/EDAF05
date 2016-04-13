package spanningUSA;

public class Pair implements Comparable<Pair> {
	private String city1;
	private String city2;
	private int dist;
	private int city1n;
	private int city2n;

	public Pair(String city1, String city2, int dist, int city1n, int city2n) {
		this.city1 = city1;
		this.city2 = city2;
		this.dist = dist;
		this.city1n = city1n;
		this.city2n = city2n;
	}

	public String getCity1() {
		return city1;
	}

	public String getCity2() {
		return city2;
	}

	public int getDist() {
		return dist;
	}

	public String toString() {
		return city1 + "--" + city2 + " [" + dist + "]";
	}

	@Override
	public int compareTo(Pair p) {
		return Integer.compare(dist, p.getDist());
	}

}