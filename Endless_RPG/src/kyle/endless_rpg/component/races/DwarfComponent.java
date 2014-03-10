package kyle.endless_rpg.component.races;

import kyle.endless_rpg.core.Component;

public class DwarfComponent implements RaceInterface
{
	private static   final int strength = 0;
	private static   final int dexterity = 0;
	private static   final int intelligence = 0;
	private static   final int wisdom = 2;
	private static   final int charisma = -2;
	private static   final int constitution = 2;
	
	private static final String textureFile = "sprites/Dwarf512.png";
	public static final String raceName = "Dwarf";
	
	@Override
	public int getStrength() {
		return DwarfComponent.strength;
	}
	@Override
	public int getDexterity() {
		return DwarfComponent.dexterity;
	}
	@Override
	public int getWisdom() {
		return DwarfComponent.wisdom;
	}
	@Override
	public int getCharisma() {
		return DwarfComponent.charisma;
	}
	@Override
	public int getConstitution() {
		return DwarfComponent.constitution;
	}
	@Override
	public int getIntelligence() {
		return DwarfComponent.intelligence;
	}
	public String getRaceName() {
		return DwarfComponent.raceName;
	}
	@Override
	public String getTexturePath() {
		return DwarfComponent.textureFile;
	}
}
