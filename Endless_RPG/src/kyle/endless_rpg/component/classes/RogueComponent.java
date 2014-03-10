package kyle.endless_rpg.component.classes;

import kyle.endless_rpg.core.Component;

public class RogueComponent implements ClassInterface{
	private static final int strength = 0;
	private static final int dexterity = 1;
	private static final int intelligence = 0;
	private static final int wisdom = 0;
	private static final int charisma = 1;
	private static final int constitution = 0;
	
	public static final String className = "Rogue";

	@Override
	public int getStrength() {
		return RogueComponent.strength;
	}

	@Override
	public int getDexterity() {
		return RogueComponent.dexterity;
	}

	@Override
	public int getWisdom() {
		return RogueComponent.wisdom;
	}

	@Override
	public int getCharisma() {
		return RogueComponent.charisma;
	}

	@Override
	public int getConstitution() {
		return RogueComponent.constitution;
	}

	@Override
	public int getIntelligence() {
		return RogueComponent.intelligence;
	}

	@Override
	public String getClassName() {
		return RogueComponent.className;
	}

}
