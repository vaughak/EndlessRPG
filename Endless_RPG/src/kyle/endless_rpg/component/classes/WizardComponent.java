package kyle.endless_rpg.component.classes;

import kyle.endless_rpg.core.Component;

public class WizardComponent implements ClassInterface{
	private static final int strength = 0;
	private static final int dexterity = 0;
	private static final int intelligence = 2;
	private static final int wisdom = 0;
	private static final int charisma = 0;
	private static final int constitution = 0;
	
	public static final String className = "Wizard";

	@Override
	public int getStrength() {
		return WizardComponent.strength;
	}

	@Override
	public int getDexterity() {
		return WizardComponent.dexterity;
	}

	@Override
	public int getWisdom() {
		return WizardComponent.wisdom;
	}

	@Override
	public int getCharisma() {
		return WizardComponent.charisma;
	}

	@Override
	public int getConstitution() {
		return WizardComponent.constitution;
	}

	@Override
	public int getIntelligence() {
		return WizardComponent.intelligence;
	}

	@Override
	public String getClassName() {
		return WizardComponent.className;
	}

}
