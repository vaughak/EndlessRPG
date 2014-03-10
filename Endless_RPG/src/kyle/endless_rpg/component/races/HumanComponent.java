package kyle.endless_rpg.component.races;

import kyle.endless_rpg.core.Component;

public class HumanComponent implements RaceInterface
{
	private static   final int strength = 0;
	private static   final int dexterity = 0;
	private static   final int intelligence = 0;
	private static   final int wisdom = 0;
	private static   final int charisma = 0;
	private static   final int constitution = 0;
	
	private static final String textureFile = "sprites//Human512.png";
	public static final String raceName = "Human";
	
	@Override
	public int getStrength() {
		return HumanComponent.strength;
	}
	@Override
	public int getDexterity() {
		return HumanComponent.dexterity;
	}
	@Override
	public int getWisdom() {
		return HumanComponent.wisdom;
	}
	@Override
	public int getCharisma() {
		return HumanComponent.charisma;
	}
	@Override
	public int getConstitution() {
		return HumanComponent.constitution;
	}
	@Override
	public int getIntelligence() {
		return HumanComponent.intelligence;
	}
	@Override
	public String getRaceName() {
		return HumanComponent.raceName;
	}
	@Override
	public String getTexturePath() {
		return HumanComponent.textureFile;
	}
}
