public class Main {
	public static void main(String args[]) {
		// easy
		//SudokuGrid game = new SudokuGrid("0 0 0 2 6 0 7 0 1 6 8 0 0 7 0 0 9 0 1 9 0 0 0 4 5 0 0 8 2 0 1 0 0 0 4 0 0 0 4 6 0 2 9 0 0 0 5 0 0 0 3 0 2 8 0 0 9 3 0 0 0 7 4 0 4 0 0 5 0 0 3 6 7 0 3 0 1 8 0 0 0");
		// intermediate
		//SudokuGrid game = new SudokuGrid("0 2 0 6 0 8 0 0 0 5 8 0 0 0 9 7 0 0 0 0 0 0 4 0 0 0 0 3 7 0 0 0 0 5 0 0 6 0 0 0 0 0 0 0 4 0 0 8 0 0 0 0 1 3 0 0 0 0 2 0 0 0 0 0 0 9 8 0 0 0 3 6 0 0 0 3 0 6 0 9 0");
		// difficult
		SudokuGrid game = new SudokuGrid("0 0 0 6 0 0 4 0 0 7 0 0 0 0 3 6 0 0 0 0 0 0 9 1 0 8 0 0 0 0 0 0 0 0 0 0 0 5 0 1 8 0 0 0 3 0 0 0 3 0 6 0 4 5 0 4 0 2 0 0 0 6 0 9 0 3 0 0 0 0 0 0 0 2 0 0 0 0 1 0 0");
		game.print();
		if (game.solve())
			System.out.println("successfully solved the puzzle");			
		else
			System.out.println("couldn't solve the puzzle");
		if (game.check())
			System.out.println("no errors in grid");			
		else
			System.out.println("errors in grid");
		game.print();
		game.printDebug();
	}
}