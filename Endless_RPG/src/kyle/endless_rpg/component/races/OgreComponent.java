package kyle.endless_rpg.component.races;

import kyle.endless_rpg.core.Component;


public class OgreComponent implements RaceInterface
{
	private static   final int strength = 4;
	private static   final int dexterity = 0;
	private static   final int intelligence = -2;
	private static   final int wisdom = -2;
	private static   final int charisma = 0;
	private static   final int constitution = -2;
	
	private static final String textureFile = "sprites//Ogre512.png";
	public static final String raceName = "Ogre";
	
	@Override
	public int getStrength() {
		return OgreComponent.strength;
	}
	@Override
	public int getDexterity() {
		return OgreComponent.dexterity;
	}
	@Override
	public int getWisdom() {
		return OgreComponent.wisdom;
	}
	@Override
	public int getCharisma() {
		return OgreComponent.charisma;
	}
	@Override
	public int getConstitution() {
		return OgreComponent.constitution;
	}
	@Override
	public int getIntelligence() {
		return OgreComponent.intelligence;
	}
	@Override
	public String getRaceName() {
		return OgreComponent.raceName;
	}
	@Override
	public String getTexturePath() {
		return OgreComponent.textureFile;
	}

}
