package spanningUSA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Prims {
	private static ArrayList<String> cities;

	private static LinkedList<Connection>[] connections;

	private static LinkedList<Pair> road;

	private static LinkedList<String> concit;

	public static void main(String[] args) {
		File file = getFileName(args);
		try {
			readInput(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		concit = new LinkedList<String>();

		LinkedList<Connection> adj = new LinkedList<Connection>();
		int sum = 0;
		concit.add(cities.get(0));
		Connection min = getMin(connections[0]);
		concit.add(min.getCity());
		sum += min.getDistance();
		adj.addAll(connections[0]);
		int i = 0;
		while (concit.size() != cities.size()) {
			min = getMin(adj);
			if (!concit.contains(min.getCity())) {
				concit.add(min.getCity());
				sum += min.getDistance();
				adj.addAll(connections[min.getCityn()]);

			}
			adj.remove(min);
		}
		System.out.println(sum);

	}

	private static Connection getMin(LinkedList<Connection> adj) {
		Connection min = adj.getFirst();
		for (Connection c : adj)
			if (min.getDistance() > c.getDistance())
				min = c;
		return min;
	}

	/**
	 * Reads the input and places it in cities and connections
	 * 
	 * @param file
	 *            The file to read
	 * @throws FileNotFoundException
	 */
	private static void readInput(File file) throws FileNotFoundException {
		Scanner s = new Scanner(file);
		cities = new ArrayList<String>();
		String line = s.nextLine();
		while (!line.endsWith("]")) {
			cities.add(line.substring(0, line.length() - 1));
			line = s.nextLine();
		}
		connections = new LinkedList[cities.size()];
		for (int i = 0; i < connections.length; i++)
			connections[i] = new LinkedList<Connection>();

		while (s.hasNextLine()) {
			String[] d = line.split("\\[");
			String[] c = d[0].split("--");
			String city1 = c[0];
			String city2 = c[1].substring(0, c[1].length() - 1);

			int dist = Integer.parseInt(d[1].substring(0, d[1].length() - 1));
			int city1n = cities.indexOf(city1);
			int city2n = cities.indexOf(city2);
			connections[city1n].add(new Connection(city2, city2n, dist));
			connections[city2n].add(new Connection(city1, city1n, dist));
			line = s.nextLine();
		}
		// for the last element
		String[] d = line.split("\\[");
		String[] c = d[0].split("--");
		String city1 = c[0];
		String city2 = c[1].substring(0, c[1].length() - 1);

		int dist = Integer.parseInt(d[1].substring(0, d[1].length() - 1));
		int city1n = cities.indexOf(city1);
		int city2n = cities.indexOf(city2);
		connections[city1n].add(new Connection(city2, city2n, dist));
		connections[city2n].add(new Connection(city1, city1n, dist));
		s.close();
	}

	/**
	 * Reads the filename from either args or system input
	 * 
	 * @param args
	 * @return The filename
	 */
	private static File getFileName(String[] args) {
		String path = "";
		String file = null;
		if (args.length > 0)
			if (args[0].endsWith(".in"))
				file = args[0];
			else
				file = args[0] + ".in";
		else {
			Scanner s = new Scanner(System.in);
			System.out.print("Enter filename: ");
			file = s.nextLine();
			if (file.isEmpty())
				file = "USA-highway-miles.in";
			else if (!file.endsWith(".in"))
				file += ".in";
			s.close();
			System.out.println();
		}
		return new File(path + file);
	}
}