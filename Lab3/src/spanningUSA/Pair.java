package spanningUSA;

public class Pair {
	private String city1;
	private String city2;
	private int dist;
	private boolean visited;

	public Pair(String city1, String city2, int dist) {
		this.city1 = city1;
		this.city2 = city2;
		this.dist = dist;
		visited = false;
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

	public Boolean isVisited() {
		return visited;
	}

}