package dg.projects.wizardhat;

import java.util.List;

public class Wizard {
	List<WizardIndex> indexes;
	
	public Wizard(List<WizardIndex> indexes) {
		this.indexes = indexes;
	}
	
	public void start() {
		for (WizardIndex i : indexes) {
			System.out.println("Index loaded: " + i.getIndexName() + " (" + i.getIndexDescription() + ")");
		}
	}
}
