package stableMarrige;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class GS {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = 3;
		String[] chars = { "Ross    ", "Monica", "Chandler", "Phoebe", "Joey    ", "Rachel" };
		int[][] prefs1 = { { 6, 4, 2 }, { 3, 5, 1 }, { 2, 6, 4 }, { 5, 1, 3 }, { 6, 4, 2 }, { 1, 5, 3 } };
		LinkedList<Integer>[] prefs = new LinkedList[2 * n + 1];
		for (int i = 0; i < prefs1.length; i++) {
			prefs[i + 1] = new LinkedList<Integer>();
			for (int j = 0; j < prefs1[0].length; j++)
				prefs[i + 1].add(prefs1[i][j]);
		}
		Map<Integer, Integer> pairs = new HashMap<Integer, Integer>();

		pairs.put(1, -1);
		pairs.put(3, -1);
		pairs.put(5, -1);
		pairs.put(2, -1);
		pairs.put(4, -1);
		pairs.put(6, -1);

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
				while (!prefs[female].isEmpty() && !prefs[female].pollLast().equals(male))
					;
			}

			for (int i = 1; i <= 2 * n; i++)
				System.out.println(i + " " + pairs.get(i));
			s.nextLine();
		}
		for (int i = 1; i <= 2 * n; i += 2)
			System.out.println(chars[i - 1] + "\t" + chars[pairs.get(i) - 1]);

	}

}
