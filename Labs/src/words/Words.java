package words;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Words {
	public static void main(String[] args) {

		File file = getName(args, 0);

		// Read input
		Scanner s1 = null;
		ArrayList<String> words = new ArrayList<String>();
		try {
			s1 = new Scanner(file);
			while (s1.hasNextLine())
				words.add(s1.nextLine());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			s1.close();
		}

		// Set up graph
		LinkedList<Integer>[] relations = new LinkedList[words.size()];
		for (int i = 0; i < relations.length; i++) {
			String word = words.get(i);
			relations[i] = new LinkedList<Integer>();
			for (int j = 0; j < words.size(); j++) {
				boolean linked = true;
				if (i != j) {
					String w = words.get(j);
					for (int k = 1; k < 5; k++) {
						int index = w.indexOf(word.charAt(word.length() - k));
						if (index == -1) {
							linked = false;
							break;
						} else
							w = w.substring(0, index) + w.substring(index + 1);
					}
					if (linked)
						relations[i].add(j);
				}
			}
		}

		// read missions
		file = getName(args, 1);
		LinkedList<String[]> missions = new LinkedList<String[]>();

		Scanner s2 = null;
		try {
			s2 = new Scanner(file);
			while (s2.hasNext()) {
				String[] m = new String[2];
				m[0] = s2.next();
				m[1] = s2.next();
				missions.add(m);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			s2.close();
		}

		// check connections
		for (String[] pair : missions) {

			int start = words.indexOf(pair[0]);
			int end = words.indexOf(pair[1]);
			if (start == end) {
				System.out.println(0);
				continue;
			}
			boolean[] visited = new boolean[words.size()];
			visited[start] = true;
			boolean found = false;
			LinkedList<LinkedList<Integer>> level = new LinkedList<LinkedList<Integer>>();

			level.add(new LinkedList<Integer>());
			level.get(0).add(start);

			for (int i = 1; i < visited.length; i++) {
				level.add(new LinkedList<Integer>());
				for (int l : level.get(i - 1)) {
					for (int node : relations[l]) {
						if (visited[node])
							continue;
						if (node == end) {
							System.out.println(i);
							found = true;
							break;
						}
						level.get(i).add(node);
						visited[node] = true;
					}
					if (found)
						break;
				}
				if (found)
					break;

			}
			if (!found)
				System.out.println(-1);
		}

	}

	private static File getName(String[] args, int i) {
		Scanner s = new Scanner(System.in);
		File file = null;
		// which file?
		if (args.length > i)
			file = new File(args[i]);
		else {
			if (i == 0)
				return new File("words-5757.dat");
			else if (i == 1)
				return new File("words-5757-test.in");
			String f = "";
			while (f.isEmpty()) {
				System.out.print("File name: ");
				f = s.nextLine();
			}
			file = new File(f);
		}
		return file;
	}
}
