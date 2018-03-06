import java.util.ArrayList;
import java.util.Scanner;

public class SudokuNode extends SearchNode {
	public static final int SIZE = 81;
	public static final int UNKNOWN = 0;
	private int[][] SudokuSolver;
	private SudokuNode[][] node;
	
	//private static Scanner in = new Scanner(System.in);
	public SudokuNode(Scanner scanner) {
		System.out.print("Grid: ");
		Scanner sc = new Scanner(System.in);
	    for (int i = 0; i < 9; i++) {
	    	for (int j = 0; j < 9; j++) {
	    		int in = sc.nextInt();
	    		//node[i][j] = in;
	    	}
	    }
	}
	
	public SudokuNode(int[][] grid) {
		this.SudokuSolver = grid;
		int[][] array = grid;
	}
	
	public int getCell(int row, int column) {
		return SudokuSolver[row][column];
	}
	
	public String toString() { 
		System.out.println(SudokuSolver[0][0] + SudokuSolver[0][1] + SudokuSolver[0][2] + SudokuSolver[0][3] + SudokuSolver[0][4] + SudokuSolver[0][5] + SudokuSolver[0][6] + SudokuSolver[0][7] + SudokuSolver[0][8]);
		System.out.println(SudokuSolver[1][0] + SudokuSolver[1][1] + SudokuSolver[1][2] + SudokuSolver[1][3] + SudokuSolver[1][4] + SudokuSolver[1][5] + SudokuSolver[1][6] + SudokuSolver[1][7] + SudokuSolver[1][8]);
		System.out.println(SudokuSolver[2][0] + SudokuSolver[2][1] + SudokuSolver[2][2] + SudokuSolver[2][3] + SudokuSolver[2][4] + SudokuSolver[2][5] + SudokuSolver[2][6] + SudokuSolver[2][7] + SudokuSolver[2][8]);
		System.out.println(SudokuSolver[3][0] + SudokuSolver[3][1] + SudokuSolver[3][2] + SudokuSolver[3][3] + SudokuSolver[3][4] + SudokuSolver[3][5] + SudokuSolver[3][6] + SudokuSolver[3][7] + SudokuSolver[3][8]);
		System.out.println(SudokuSolver[4][0] + SudokuSolver[4][1] + SudokuSolver[4][2] + SudokuSolver[4][3] + SudokuSolver[4][4] + SudokuSolver[4][5] + SudokuSolver[4][6] + SudokuSolver[4][7] + SudokuSolver[4][8]);
		System.out.println(SudokuSolver[5][0] + SudokuSolver[5][1] + SudokuSolver[5][2] + SudokuSolver[5][3] + SudokuSolver[5][4] + SudokuSolver[5][5] + SudokuSolver[5][6] + SudokuSolver[5][7] + SudokuSolver[5][8]);
		System.out.println(SudokuSolver[6][0] + SudokuSolver[6][1] + SudokuSolver[6][2] + SudokuSolver[6][3] + SudokuSolver[6][4] + SudokuSolver[6][5] + SudokuSolver[6][6] + SudokuSolver[6][7] + SudokuSolver[6][8]);
		System.out.println(SudokuSolver[7][0] + SudokuSolver[7][1] + SudokuSolver[7][2] + SudokuSolver[7][3] + SudokuSolver[7][4] + SudokuSolver[7][5] + SudokuSolver[7][6] + SudokuSolver[7][7] + SudokuSolver[7][8]);
		System.out.println(SudokuSolver[8][0] + SudokuSolver[8][1] + SudokuSolver[8][2] + SudokuSolver[8][3] + SudokuSolver[8][4] + SudokuSolver[8][5] + SudokuSolver[8][6] + SudokuSolver[8][7] + SudokuSolver[8][8]);
		return SudokuSolver.toString();
	}

	public boolean isGoal() {
		for (int[] e : SudokuSolver)
			for (int h : e)
				if(h == '.')
					return false;
		return true;
	}


	public ArrayList<SearchNode> expand() {
		ArrayList<SearchNode> children = new ArrayList<>();
		while(!isGoal())
		{
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
					if(SudokuSolver[i][j] == '.')
						
						
			return children;
		}
		return null;
	}
	
	public Object clone() {
		return super.clone();
	}
}
