package dg.projects.wizardhat;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		ArrayList<WizardIndex> indexes = new ArrayList<WizardIndex>();
		// indexes
		indexes.add(new RARBG());
		Wizard gandalf = new Wizard(indexes);
		gandalf.start();
	}
}
