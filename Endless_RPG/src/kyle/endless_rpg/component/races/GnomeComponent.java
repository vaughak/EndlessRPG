package kyle.endless_rpg.component.races;

import kyle.endless_rpg.core.Component;

public class GnomeComponent implements RaceInterface
{
	private static   final int strength = 0;
	private static   final int dexterity = 2;
	private static   final int intelligence = 2;
	private static   final int wisdom = 0;
	private static   final int charisma = -2;
	private static   final int constitution = 0;
	
	private static final String textureFile = "sprites//Gnome512.png";
	public static final String raceName = "Gnome";
	
	@Override
	public int getStrength() {
		return GnomeComponent.strength;
	}
	@Override
	public int getDexterity() {
		return GnomeComponent.dexterity;
	}
	@Override
	public int getWisdom() {
		return GnomeComponent.wisdom;
	}
	@Override
	public int getCharisma() {
		return GnomeComponent.charisma;
	}
	@Override
	public int getConstitution() {
		return GnomeComponent.constitution;
	}
	@Override
	public int getIntelligence() {
		return GnomeComponent.intelligence;
	}
	@Override
	public String getRaceName() {
		return GnomeComponent.raceName;
	}
	@Override
	public String getTexturePath() {
		return GnomeComponent.textureFile;
	}
}
