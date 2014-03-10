package kyle.endless_rpg.component;

import kyle.endless_rpg.UI.ProgressBar;
import kyle.endless_rpg.core.Component;

public class StatsComponent implements Component{
	public float maxHitpoints, maxMana;
	public float hitpoints, mana;
	
	public int strength;
	public int dexterity;
	public int intelligence;
	public int wisdom;
	public int charisma;
	public int constitution;
	public int level;
	
	public float experience, experienceToLevel;

	public ProgressBar healthBar;
	public ProgressBar manaBar;
	public ProgressBar experienceBar;
}
