package kyle.endless_rpg.component.classes;

import kyle.endless_rpg.core.Component;

public class RangerComponent implements ClassInterface{
	private static final int strength = 0;
	private static final int dexterity = 2;
	private static final int intelligence = 0;
	private static final int wisdom = 0;
	private static final int charisma = 0;
	private static final int constitution = 0;
	
	public static final String className = "Ranger";

	@Override
	public int getStrength() {
		return RangerComponent.strength;
	}

	@Override
	public int getDexterity() {
		return RangerComponent.dexterity;
	}

	@Override
	public int getWisdom() {
		return RangerComponent.wisdom;
	}

	@Override
	public int getCharisma() {
		return RangerComponent.charisma;
	}

	@Override
	public int getConstitution() {
		return RangerComponent.constitution;
	}

	@Override
	public int getIntelligence() {
		return RangerComponent.intelligence;
	}

	@Override
	public String getClassName() {
		return RangerComponent.className;
	}
}
