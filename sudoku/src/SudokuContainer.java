import java.util.List;
import java.util.ArrayList;

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
	
	public List<SudokuCell> getCells() {
		List<SudokuCell> allCells = new ArrayList<SudokuCell>();
		for (int i = 0; i < nextIndex; i++)
			allCells.add(cells[i]);
		return allCells;
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

	public List<SudokuCell> getCellsContainingPossibility(int possibility) {
		List<SudokuCell> listOfCells = new ArrayList<SudokuCell>();
		for (int i = 0; i < nextIndex; i++)
			if (cells[i].getPossibilities()[possibility-1] == true)
				listOfCells.add(cells[i]);
		return listOfCells;
	}
	
	public boolean setHiddenSingles() {
		boolean changed = false;
		for (int i = 1; i <= 9; i++) {
			List<SudokuCell> listOfCells = getCellsContainingPossibility(i);
			if (listOfCells.size() == 1) {
				listOfCells.get(0).setNumber(i);
				changed = true;
			}
		}
		return changed;
	}
	
	public boolean setHiddenPairs() {
		for (int i = 1; i <= 9; i++) {
			List<SudokuCell> listOfCells = getCellsContainingPossibility(i);
			if (listOfCells.size() == 2) {
				for (int j = 1; j <= 9; j++) {
					if (i == j)
						continue;
					List<SudokuCell> anotherListOfCells = getCellsContainingPossibility(j);
					if (anotherListOfCells.size() != 2)
						continue;
					if ((!anotherListOfCells.contains(listOfCells.get(0))) ||
							(!anotherListOfCells.contains(listOfCells.get(1))))
						continue;
					boolean changed = false;
					for (int k = 1; k <= 9; k++) {
						if ((k == i) || (k == j))
							continue;
						if ((listOfCells.get(0).removePossibility(k)) |
								(listOfCells.get(1).removePossibility(k)))
							changed = true;
					}
					if (changed == true)
						return true;
				}
			}
		}
		return false;
	}
	
	public boolean setHiddenTriple() {
		List<List<SudokuCell>> triples = new ArrayList<List<SudokuCell>>();
		// make a list of all unique triples
		for (int i = 0; i < nextIndex; i++) {
			for (int j = i + 1; j < nextIndex; j++) {
				for (int k = j + 1; k < nextIndex; k++) {
					List<SudokuCell> triple = new ArrayList<SudokuCell>();
					triple.add(cells[i]);
					triple.add(cells[j]);
					triple.add(cells[k]);
					triples.add(triple);
				}				
			}			
		}
		// check if 3 different possibilities are found in each triple
		return false;
	}
	
	public boolean setNakedSingles() {
		boolean changed = false;
		for (int i = 0; i < nextIndex; i++)
			if (cells[i].setNumber() != 0)
				changed = true;
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