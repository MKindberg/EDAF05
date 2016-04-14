package spanningUSA;

public class Connection {

	private String city;
	private int cityn;
	private int distance;

	public Connection(String city, int cityn, int distance) {
		this.city = city;
		this.cityn = cityn;
		this.distance = distance;
	}

	public String getCity() {
		return city;
	}

	public int getCityn() {
		return cityn;
	}

	public int getDistance() {
		return distance;
	}

}
