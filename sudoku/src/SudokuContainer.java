public class SudokuContainer {
	private SudokuCell[] cells = new SudokuCell[9];
	private int nextIndex = 0;
	
	public SudokuContainer() {	
	}
	
	public boolean add(SudokuCell cell) {
		if (nextIndex <= 9) {
			cells[nextIndex++] = cell;
			return true;
		}
		return false;
	}
	
	public void updatePossibilities() {
		for (int i = 0; i < nextIndex; i++) {
			if (cells[i].getNumber() == 0)
				continue;
			for (int j = 0; j < nextIndex; j++) {
				if (j == i)
					continue;
				cells[j].removePossibility(cells[i].getNumber());
			}
		}	
	}
		
	public boolean setNakedSingles() {
		boolean changed = false;
		for (int i = 0; i < nextIndex; i++)
			if (cells[i].setNumber() != 0)
				changed = true;
		return changed;
	}
	
	public boolean setHiddenSingles() {
		boolean changed = false;
		int[] count = new int[9];
		SudokuCell[] lastSeen = new SudokuCell[9];
		for (int i = 0; i < 9; i++) {
			lastSeen[i] = null;
			count[i] = 0;
		}
		for (int i = 0; i < nextIndex; i++) {
			boolean[] possibilities = cells[i].getPossibilities();
			for (int j = 0; j < 9; j++) {
				if (possibilities[j] == true) {
					count[j]++;
					lastSeen[j] = cells[i];
				}
			}
		}
		for (int i = 0; i < 9; i++) {
			if (count[i] == 1) {
				lastSeen[i].setNumber(i + 1);
				changed = true;
			}
		}
		return changed;
	}
	
	public boolean setNakedPairs() {
		for (int i = 0; i < nextIndex; i++) {
			for (int j = 0; j < nextIndex; j++) {
				if (j == i)
					continue;
				if (cells[i].isNakedPair(cells[j]) == true) {
					boolean changed = false;
					for (int k = 0; k < nextIndex; k++) {
						if ((k == i) || (k == j))
							continue;
						if (cells[k].removeAgainstAnother(cells[i]) == true)
							changed = true;
					}
					if (changed == true)
						return true;
				}
			}
		}
		return false;		
	}
	
	public boolean setNakedTriples() {
		for (int i = 0; i < nextIndex; i++) {
			for (int j = i + 1; j < nextIndex; j++) {
				for (int k = j + 1; k < nextIndex; k++) {
					if (cells[i].isNakedTriple(cells[j], cells[k]) == true) {
						boolean changed = false;
						for (int l = 0; l < nextIndex; l++) {
							if ((l == i) || (l == j) || (l == k))
								continue;
							if (cells[l].removeAgainstAnother(cells[i]) == true)
								changed = true;
							if (cells[l].removeAgainstAnother(cells[j]) == true)
								changed = true;
							if (cells[l].removeAgainstAnother(cells[k]) == true)
								changed = true;
						}
						if (changed == true)
							return true;
					}
				}
			}
		}
		return false;		
	}

	public boolean checkContainer() {
		boolean correct = true;
		int[] count = new int[9];
		for (int i = 0; i < 9; i++)
			count[i] = 0;
		for (int i = 0; i < nextIndex; i++)
			if (cells[i].getNumber() != 0)
				count[cells[i].getNumber() - 1]++;
		for (int i = 0; i < 9; i++)
			if (count[i] > 1)
				correct = false;
		return correct;
	}
}