package closestPoints;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Cp {

	private static Point[] points;

	public static void main(String[] args) {
		File file = getFileName(args);
		try {
			readInput(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void readInput(File file) throws FileNotFoundException {
		Scanner s = new Scanner(file);
		String word = s.next();
		while (!word.equals("DIMENSION")) {
			word = s.next();
		}
		s.next();
		int n = s.nextInt();
		points = new Point[n];
		s.nextLine();
		s.nextLine();
		s.nextLine();
		for(int i=0;i<n;i++){
			s.next();
			points[i]=new Point(s.nextInt(), s.nextInt());
		}
		s.close();
		
		for(int i=0;i<points.length;i++)
			System.out.println(points[i]);
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
				file = "att48-tsp.txt";
			else if (!file.endsWith(".in"))
				file += ".in";
			s.close();
			System.out.println();
		}
		return new File(path + file);
	}
}
