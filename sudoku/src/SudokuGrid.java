public class SudokuGrid {
	private SudokuCell[] puzzle = new SudokuCell[81];
	private SudokuContainer[] rows = new SudokuContainer[9];
	private SudokuContainer[] columns = new SudokuContainer[9];
	private SudokuContainer[] boxes = new SudokuContainer[9];
	
	public SudokuGrid() {
		this("");
	}

	public SudokuGrid(String rawPuzzle) {
		setPuzzle(rawPuzzle);
	}
	
	public void setPuzzle(String rawPuzzle) {
		String[] data = rawPuzzle.split("\\s+");
		if ((data == null) || (data.length > 81))
			throw new IllegalArgumentException("invalid puzzle");
		for (int i = 0; i < 81; i++) {
			int num;
			if (i >= data.length) {
				num = 0;
			} else {
				try {
					num = Integer.valueOf(data[i]);
				} catch (NumberFormatException e) {
					num = 0;
				}
			}
			if ((num >= 0) || (num <= 9))
				puzzle[i] = new SudokuCell(num);
			else
				throw new IllegalArgumentException("invalid puzzle input " + num);
		}
		for (int i = 0; i < 9; i++) {
			rows[i] = new SudokuContainer();
			columns[i] = new SudokuContainer();
			boxes[i] = new SudokuContainer();
		}
		for (int i = 0; i < 81; i++) {
			rows[SudokuCell.calculateRow(i)].add(puzzle[i]);
			columns[SudokuCell.calculateColumn(i)].add(puzzle[i]);
			boxes[SudokuCell.calculateBox(i)].add(puzzle[i]);
		}
	}

	public void print() {
		for (int i = 0; i < puzzle.length; i++) {
			if (((i + 1) % 9) == 0)
				System.out.println(puzzle[i].getNumber());
			else
				System.out.print(puzzle[i].getNumber() + " ");
		}
	}
	
	public void printDebug() {
		for (int i = 0; i < puzzle.length; i++) {
			if (((i + 1) % 9) == 0)
				System.out.println(puzzle[i].getNumber() + " (" + puzzle[i].getPossibilitiesText() + ")\n");
			else
				System.out.print(puzzle[i].getNumber() + " (" + puzzle[i].getPossibilitiesText() + ") \t");
		}
	}

	public boolean solve() {
		boolean changed = true;
		outer:
		while (changed) {
			changed = false;
			for (int i = 0; i < 9; i++) {
				rows[i].updatePossibilities();
				columns[i].updatePossibilities();
				boxes[i].updatePossibilities();
			}	
			for (int i = 0; i < 9; i++) {
				if ((rows[i].setNakedSingles() == true) || 
						(columns[i].setNakedSingles() == true) || 
						(boxes[i].setNakedSingles() == true)) {
					changed = true;
					continue outer;
				}
			}
			for (int i = 0; i < 9; i++) {
				if ((rows[i].setHiddenSingles() == true) ||
						(columns[i].setHiddenSingles() == true) ||
						(boxes[i].setHiddenSingles() == true)) {
					changed = true;
					continue outer;
				}
			}
			for (int i = 0; i < 9; i++) {
				if ((rows[i].setNakedPairs() == true) || 
						(columns[i].setNakedPairs() == true) ||
						(boxes[i].setNakedPairs() == true)) {
					changed = true;
					continue outer;
				}
			}
			for (int i = 0; i < 9; i++) {
				if ((rows[i].setNakedTriples() == true) || 
						(columns[i].setNakedTriples() == true) ||
						(boxes[i].setNakedTriples() == true)) {
					changed = true;
					continue outer;
				}
			}
		}
		for (int i = 0; i < 81; i++)
			if (puzzle[i].getTotalPossible() > 0)
				return false;
		return true;
	}
	
	public boolean check() {
		for (int i = 0; i < 9; i++) {
			if ((rows[i].checkContainer() == false) || 
					(columns[i].checkContainer() == false) || 
					(boxes[i].checkContainer() == false))
				return false;
		}
		return true;
	}
}