package kyle.endless_rpg.component.classes;

import kyle.endless_rpg.core.Component;

public class WarriorComponent implements ClassInterface{
	private static final int strength = 2;
	private static final int dexterity = 0;
	private static final int intelligence = 0;
	private static final int wisdom = 0;
	private static final int charisma = 0;
	private static final int constitution = 0;
	
	public static final String className = "Warrior";

	@Override
	public int getStrength() {
		return WarriorComponent.strength;
	}

	@Override
	public int getDexterity() {
		return WarriorComponent.dexterity;
	}

	@Override
	public int getWisdom() {
		return WarriorComponent.wisdom;
	}

	@Override
	public int getCharisma() {
		return WarriorComponent.charisma;
	}

	@Override
	public int getConstitution() {
		return WarriorComponent.constitution;
	}

	@Override
	public int getIntelligence() {
		return WarriorComponent.intelligence;
	}

	@Override
	public String getClassName() {
		return WarriorComponent.className;
	}

}
