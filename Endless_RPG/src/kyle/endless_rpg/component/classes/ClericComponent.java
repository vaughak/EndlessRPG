package kyle.endless_rpg.component.classes;

import kyle.endless_rpg.core.Component;

public class ClericComponent implements ClassInterface{
	private static final int strength = 0;
	private static final int dexterity = 0;
	private static final int intelligence = 0;
	private static final int wisdom = 2;
	private static final int charisma = 0;
	private static final int constitution = 0;
	
	public static final String className = "Cleric";

	@Override
	public int getStrength() {
		return ClericComponent.strength;
	}

	@Override
	public int getDexterity() {
		return ClericComponent.dexterity;
	}

	@Override
	public int getWisdom() {
		return ClericComponent.wisdom;
	}

	@Override
	public int getCharisma() {
		return ClericComponent.charisma;
	}

	@Override
	public int getConstitution() {
		return ClericComponent.constitution;
	}

	@Override
	public int getIntelligence() {
		return ClericComponent.intelligence;
	}

	@Override
	public String getClassName() {
		return ClericComponent.className;
	}


}
