package kyle.endless_rpg.component.races;

import kyle.endless_rpg.core.Component;

public class ElfComponent implements RaceInterface
{
	private static   final int strength = 0;
	private static   final int dexterity = 2;
	private static   final int intelligence = 2;
	private static   final int wisdom = 0;
	private static   final int charisma = 0;
	private static   final int constitution = -2;
	
	private static final String textureFile = "sprites//Elf512.png";
	public static final String raceName = "Elf";
	
	@Override
	public int getStrength() {
		return ElfComponent.strength;
	}
	@Override
	public int getDexterity() {
		return ElfComponent.dexterity;
	}
	@Override
	public int getWisdom() {
		return ElfComponent.wisdom;
	}
	@Override
	public int getCharisma() {
		return ElfComponent.charisma;
	}
	@Override
	public int getConstitution() {
		return ElfComponent.constitution;
	}
	@Override
	public int getIntelligence() {
		return ElfComponent.intelligence;
	}
	@Override
	public String getRaceName() {
		return ElfComponent.raceName;
	}
	@Override
	public String getTexturePath() {
		return ElfComponent.textureFile;
	}

}
