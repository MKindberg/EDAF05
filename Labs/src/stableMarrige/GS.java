package stableMarrige;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class GS {

	public static void main(String[] args) {
		File file = null;
		if (args.length != 0)
			file = new File(args[0]);
		else
			file = new File("sm-worst-500.in");
		Scanner s;
		int n = 0;
		String chars[] = null;
		LinkedList<Integer>[] prefs = null;
		try {
			s = new Scanner(file);
			String line = "#";
			while (line.charAt(0) == '#')
				line = s.nextLine();
			n = Integer.parseInt(line.substring(2));
			chars = new String[2 * n];
			for (int i = 0; i < 2 * n; i++) {
				s.nextInt();
				chars[i] = s.next();
			}
			s.nextLine();
			prefs = new LinkedList[2 * n + 1];
			for (int i = 0; i < prefs.length - 1; i++) {
				prefs[i + 1] = new LinkedList<Integer>();
				if (s.hasNextInt())
					s.nextInt();
				for (int j = 0; j < n;)
					if (s.hasNextInt()) {
						prefs[i + 1].add(s.nextInt());
						j++;
					} else
						s.next();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Map<Integer, Integer> pairs = new HashMap<Integer, Integer>();
		for (int i = 1; i <= 2 * n; i++)
			pairs.put(i, -1);

		// int n = 3;
		// String[] chars = { "Ross ", "Monica", "Chandler", "Phoebe", "Joey ",
		// "Rachel" };
		// int[][] prefs1 = { { 6, 4, 2 }, { 3, 5, 1 }, { 2, 6, 4 }, { 5, 1, 3
		// }, { 6, 4, 2 }, { 1, 5, 3 } };
		// LinkedList<Integer>[] prefs = new LinkedList[2 * n + 1];
		// for (int i = 0; i < prefs1.length; i++) {
		// prefs[i + 1] = new LinkedList<Integer>();
		// for (int j = 0; j < prefs1[0].length; j++)
		// prefs[i + 1].add(prefs1[i][j]);
		// }
		// Map<Integer, Integer> pairs = new HashMap<Integer, Integer>();
		//
		// pairs.put(1, -1);
		// pairs.put(3, -1);
		// pairs.put(5, -1);
		// pairs.put(2, -1);
		// pairs.put(4, -1);
		// pairs.put(6, -1);

		// control:
		// for (int i = 0; i < 2 * n; i++)
		// System.out.println(chars[i]);
		// System.out.println();
		// for (int i = 0; i < 2 * n; i++) {
		// for (int j = 0; j < n; j++)
		// System.out.print(prefs[i + 1].get(j) + " ");
		// System.out.println();
		// }

		Integer male = 1;
		while (pairs.containsValue(-1)) {
			while (pairs.get(male) != -1)
				male = (male + 2) % (n * 2);
			Integer female = prefs[male].poll();
			if (pairs.containsValue(female)) {
				if (prefs[female].contains(male)) {
					pairs.put(pairs.get(female), -1);
					pairs.put(male, female);
					pairs.put(female, male);
					while (!prefs[female].isEmpty() && !prefs[female].pollLast().equals(male))
						;
				}
			} else {
				pairs.put(male, female);
				pairs.put(female, male);
				if (prefs[female].isEmpty())
					continue;
				while (!prefs[female].isEmpty() && !prefs[female].pollLast().equals(male))
					;
			}
		}
		for (int i = 1; i <= 2 * n; i += 2)
			System.out.println(chars[i - 1] + " -- " + chars[pairs.get(i) - 1]);

	}

}
