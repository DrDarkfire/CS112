import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TooIOFun {

	public static void main(String[] args) {
		String filename = "dairy.txt";
		ArrayList<String> lines = new ArrayList<>();
		
		// load previous diary lines (if they exist)
		Scanner in = null;
		try { // try without resources
			in = new Scanner(new File(filename));
			while (in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("No diary - creating " + filename);
		}
		finally {
			if (in != null)
				in.close();
		}
		// add additional diary lines
		in = new Scanner(System.in);
		while (true) {
			System.out.print("New diary line (or Ender to exit): ");
			String line = in.nextLine();
			if (line.length() == 0)
				break;
			lines.add(line);
		}
		in.close();
		
		// save all diary lines
		// try with resources
		try (PrintWriter out = new PrintWriter(new File(filename))) {
			for (String line : lines)
				out.println(line);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
