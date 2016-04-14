package spanningUSA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Span {
	private static ArrayList<String> cities;

	private static LinkedList<Pair> connections;

	private static LinkedList<Pair> road;

	public static void main(String[] args) {
		File file = getFileName(args);
		try {
			readInput(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		connections.sort(null); // sorts all connections ascending on distance
		UnionFind uf = new UnionFind(cities.size());

		road = new LinkedList<Pair>();

		for (Pair c : connections) {
			int a = c.getCity1n();
			int b = c.getCity2n();
			if (uf.find(a) != uf.find(b)) { // if adding the connection
											// doesn't
											// create a loop
				uf.union(a, b);
				road.add(c);
				if (road.size() == cities.size())// if we already visit every
													// city
					break;
			}
		}
		int len = 0;
		System.out.println("All connections in the tree: ");
		for (Pair r : road) {
			System.out.println(r);
			len += r.getDist();
		}
		System.out.println();
		System.out.println("Total length: " + len);

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

		connections = new LinkedList<Pair>();
		while (s.hasNextLine()) {
			String[] d = line.split("\\[");
			String[] c = d[0].split("--");
			String city1 = c[0];
			String city2 = c[1].substring(0, c[1].length() - 1);

			int dist = Integer.parseInt(d[1].substring(0, d[1].length() - 1));
			int city1n = cities.indexOf(city1);
			int city2n = cities.indexOf(city2);
			connections.add(new Pair(city1, city2, dist, city1n, city2n));
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
		connections.add(new Pair(city1, city2, dist, city1n, city2n));
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