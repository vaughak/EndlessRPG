package kyle.endless_rpg;

import java.util.Random;

public class Rarity {
	private static final double COMMON_RARITY = 90;
	private static final double UNCOMMON_RARITY = 75;
	private static final double RARE_RARITY = 50;
	private static final double EPIC_RARITY = 10;
	private static final double LEGENDARY_RARITY = 1;
	
	public static double rarity(){
		double mod = 0;
		int ran;
		Random r = new Random();
		ran = r.nextInt(10000 - 1) + 1;

		if(ran <= LEGENDARY_RARITY * 100)
			mod = 1f;
		else if(ran <= EPIC_RARITY * 100)
			mod = .75f;
		else if(ran <= RARE_RARITY * 100)
			mod = .5f;
		else if(ran <= UNCOMMON_RARITY * 100)
			mod = .25f;
		else if(ran <= COMMON_RARITY * 100)
			mod = 0f;

	
		return mod;		
	}
}
