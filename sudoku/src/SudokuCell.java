public class SudokuCell {
	private int id = -1;
	private int row = -1;
	private int column = -1;
	private int box = -1;
	private int number = 0;
	private boolean[] possibilities = new boolean[9];

	public SudokuCell(int id) {
		this(id, 0);
	}
	
	public SudokuCell(int id, int n) {
		setId(id);
		setNumber(n);
	}

	public void setId(int id) {
		this.id = id;
		this.row = calculateRow(this.id);
		this.column = calculateColumn(this.id);
		this.box = calculateBox(this.id);
	}

	public int getId() {
		return this.id;
	}
	
	public int getRow() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
	}
	
	public int getBox() {
		return this.box;
	}
	
	public static int calculateRow(int index) {
		return index / 9;
	}

	public static int calculateColumn(int index) {
		return index % 9;
	}

	public static int calculateBox(int index) {
		return ((index / 27) * 3) + ((index % 9) / 3);
	}
	
	public boolean removePossibility(int n) {
		if (possibilities[n-1] == true) {
			possibilities[n-1] = false;
			return true;
		} else {
			return false;
		}
	}

	public boolean removeAgainstAnother(SudokuCell cell) {
		boolean changed = false;
		boolean[] otherPossibilities = cell.getPossibilities();
		for (int i = 0; i < 9; i++) {
			if (otherPossibilities[i] == true) {
				if (removePossibility(i + 1) == true)
					changed = true;
			}
		}
		return changed;
	}

	public void setNumber(int n) {
		if (n == 0) {
			for (int i = 0; i < 9; i++)
				possibilities[i] = true;
		} else {
			for (int i = 0; i < 9; i++)
				possibilities[i] = false;
		}
		number = n;
	}
	
	public int setNumber() {
		if (getTotalPossible() == 1) {
			for (int i = 0; i < 9; i++) {
				if (possibilities[i] == true) {
					setNumber(i + 1);
					return i + 1;
				}
			}
		}
		return 0;
	}
	
	public int getNumber() {
		return number; 
	}
	
	public int getTotalPossible() {
		int total = 0;
		for (int i = 0; i < 9; i++)
			if (possibilities[i] == true)
				total++;
		return total;
	}

	public boolean[] getPossibilities() {
		return possibilities.clone();
	}
	
	public String getPossibilitiesText() {
		String s = "";
		for (int i = 0; i < 9; i++)
			if (possibilities[i] == true)
				s += i + 1;
			else
				s += " ";
		return s;
	}
	
	public boolean isNakedPair(SudokuCell cell) {
		boolean[] otherPossibilities = cell.getPossibilities();
		if ((getTotalPossible() != 2) || (cell.getTotalPossible() != 2))
			return false;
		for (int i = 0; i < 9; i++)
			if (possibilities[i] != otherPossibilities[i])
				return false;
		return true;
	}

	public boolean isNakedTriple(SudokuCell cellOne, SudokuCell cellTwo) {
		int typeCount = 0;
		int[] totalPossibilities = new int[9];
		boolean[] selfPossibilities = getPossibilities();
		boolean[] otherPossibilitiesOne = cellOne.getPossibilities();
		boolean[] otherPossibilitiesTwo = cellTwo.getPossibilities();
		if ((getTotalPossible() > 3) ||
				(cellOne.getTotalPossible() > 3) ||
				(cellTwo.getTotalPossible() > 3))
			return false;
		if ((getTotalPossible() < 1) ||
				(cellOne.getTotalPossible() < 1) ||
				(cellTwo.getTotalPossible() < 1))
			return false;
		for (int i = 0; i < 9; i++)
			totalPossibilities[i] = 0;
		for (int i = 0; i < 9; i++) {
			if (selfPossibilities[i] == true)
				totalPossibilities[i]++;
			if (otherPossibilitiesOne[i] == true)
				totalPossibilities[i]++;
			if (otherPossibilitiesTwo[i] == true)
				totalPossibilities[i]++;
		}
		for (int i = 0; i < 9; i++)
			if (totalPossibilities[i] > 0)
				typeCount++;
		if (typeCount != 3)
			return false;
		return true;
	}
}