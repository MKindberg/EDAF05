package stableMarrige;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class GS {

	public static void main(String[] args) {
		Scanner s1 = new Scanner(System.in);
		File file = null;
		// which file?
		if (args.length != 0)
			file = new File(args[0]);
		else {
			System.out.print("File name: ");
			String f = s1.nextLine();
			if (f.isEmpty())
				file = new File("sm-friends.in");
			else
				file = new File(f);
		}
		// Read input
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

		// algorithm
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
