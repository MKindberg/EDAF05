package closestPoints;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Cp {

	private static Point[] points;

	private static double minG;

	public static void main(String[] args) {
		testAll();
		// testOne(args);

	}

	private static void testOne(String[] args) {

		while (true) {
			File file = getFileName(args);
			System.out.print(file.getName() + ": ");
			readInput(file);

			Arrays.sort(points);

			double min = closest(0, points.length);

			double minb = bruteForce();
			if (min == minb)
				System.out.println("OK!");
			else
				System.out.println("Wrong, Calculated: " + min + ". Bruteforce: " + minb);
		}
	}

	private static void testAll() {
		File folder = new File("TestFiles");
		File[] files = folder.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(".tsp");
			}
		});

		int w = 0;
		for (File file : files) {
			System.out.print(file.getName() + ": ");
			readInput(file);

			Arrays.sort(points);

			double min = closest(0, points.length);

			double minb = bruteForce();
			if (min == minb)
				System.out.println("OK!");
			else {
				System.out.println("Wrong, Calculated: " + min + ". Bruteforce: " + minb);
				w++;
			}
		}
		System.out.println(w + " wrong!");
	}

	private static double bruteForce() {
		double minb = 1000;
		Point min1 = null;
		Point min2 = null;
		for (Point p1 : points)
			for (Point p2 : points)
				if (p1 != p2)
					if (p1.distanceTo(p2) < minb) {
						minb = p1.distanceTo(p2);
						min1 = p1;
						min2 = p2;
					}
		return minb;
	}

	private static double closest(int start, int end) {
		// calculate min in this half
		int len = end - start;
		if (len <= 3) {
			double min = points[start].distanceTo(points[start + 1]);
			if (len == 3) {
				double dist = points[start].distanceTo(points[start + 2]);
				if (min > dist)
					min = dist;
				dist = points[start + 1].distanceTo(points[start + 2]);
				if (min > dist)
					min = dist;
			}
			minG = min;
			return min;
		}

		int mid = start + len / 2;

		// calculate min of right half
		double minL = closest(start, mid);

		// calculate min of right half
		double minR = closest(mid, end);
		// merge halves

		double midX = points[mid].getX();
		double delta = Math.min(minL, minR);
		delta = Math.min(delta, minG);

		LinkedList<Point> closeToL = new LinkedList<Point>();
		LinkedList<Point> closeToR = new LinkedList<Point>();
		for (int i = 0; mid - i > start && points[mid - i].getX() - midX < delta; i++)
			closeToL.add(points[mid - i]);
		for (int i = 0; mid + i < end && points[mid + i].getX() - midX < delta; i++)
			closeToR.add(points[mid + i]);
		double min = delta;
		for (Point pL : closeToL)
			for (Point pR : closeToR) {
				double dist = pL.distanceTo(pR);
				if (min > dist && pL != pR)
					min = dist;
			}
		minG = min;
		return min;

	}

	private static void readInput(File file) {
		Scanner s = null;
		try {
			s = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String word = s.next();
		while (!word.equals("DIMENSION") && !word.equals("DIMENSION:"))
			word = s.next();
		if (word.equals("DIMENSION"))
			s.next();
		int n = s.nextInt();
		points = new Point[n];
		word = s.nextLine();
		while (!word.trim().equals("NODE_COORD_SECTION"))
			word = s.nextLine();
		for (int i = 0; i < n; i++) {
			s.next();
			points[i] = new Point(Double.parseDouble(s.next()), Double.parseDouble(s.next()));
		}
		// s.close();

	}

	/**
	 * Reads the filename from either args or system input
	 * 
	 * @param args
	 * @return The filename
	 */
	private static File getFileName(String[] args) {
		String path = "TestFiles\\";
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
			if (file.isEmpty()) {
				file = "rl1323.tsp";
				System.out.println("Using default file: " + file);
			}
			// s.close();
			System.out.println();
		}
		return new File(path + file);
	}
}
