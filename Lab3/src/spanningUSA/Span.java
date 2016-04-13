package spanningUSA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Span {
	private static ArrayList<String> cities;

	private static LinkedList<Pair> connections;

	public static void main(String[] args) {
		File file = getFileName(args);
		try {
			readInput(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (Pair p : connections)
			System.out.println(p);

	}

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
			// int[] data = new int[3];
			String[] d = line.split("\\[");
			String[] c = d[0].split("--");
			// data[0] = cities.indexOf(c[0]);
			// data[1] = cities.indexOf(c[1].substring(0, c[1].length() - 1));
			// data[2] = Integer.parseInt(d[1].substring(0, d[1].length() - 1));
			connections.add(new Pair(c[0], c[1].substring(0, c[1].length() - 1),
					Integer.parseInt(d[1].substring(0, d[1].length() - 1))));
			line = s.nextLine();
		}
		String[] d = line.split("\\[");
		String[] c = d[0].split("--");
		connections.add(new Pair(c[0], c[1].substring(0, c[1].length() - 1),
				Integer.parseInt(d[1].substring(0, d[1].length() - 1))));
		s.close();
	}

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