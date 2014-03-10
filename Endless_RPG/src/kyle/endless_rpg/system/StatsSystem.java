package kyle.endless_rpg.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import kyle.endless_rpg.Assets;
import kyle.endless_rpg.UI.PlayMenuBuilder;
import kyle.endless_rpg.UI.ProgressBar;
import kyle.endless_rpg.UI.TextProgressBar;
import kyle.endless_rpg.component.PlayerComponent;
import kyle.endless_rpg.component.StatsComponent;
import kyle.endless_rpg.core.Entity;
import kyle.endless_rpg.screens.ScreenScaling;


public class StatsSystem extends EntitySystem {
	StatsComponent stats;
	private static final float base_xp = 50f;
	private static final float factor_xp = 2.6f;
	private float levelProgress;
	
	public StatsSystem(Entity entity){
		stats = entity.getComponent(StatsComponent.class);
		ProgressBar.ProgressBarStyle barStyle = new ProgressBar.ProgressBarStyle(Assets.manager.get(Assets.healthEmptyImg, Texture.class),Assets.manager.get(Assets.healthFullImg, Texture.class), 40);
		stats.healthBar = new ProgressBar(0, 100, 1f, false, barStyle, ScreenScaling.VIRTUAL_WIDTH/3, 40);
		
		barStyle = new ProgressBar.ProgressBarStyle(Assets.manager.get(Assets.manaEmptyImg, Texture.class),Assets.manager.get(Assets.manaFullImg, Texture.class), 40);
		stats.manaBar = new ProgressBar(0, 100, 1f, false, barStyle, ScreenScaling.VIRTUAL_WIDTH/3, 40);
		
		barStyle = new ProgressBar.ProgressBarStyle(Assets.manager.get(Assets.experienceEmptyImg, Texture.class),Assets.manager.get(Assets.experienceFullImg, Texture.class), 40);
		stats.experienceBar = new ProgressBar(0, 100, 1f, false, barStyle, ScreenScaling.VIRTUAL_WIDTH/3, 40);
	}

	
	@Override
	public void processEntity(Entity entity, float deltaTime) {
	
		stats.healthBar.setValue(stats.hitpoints);
		stats.manaBar.setValue(stats.mana);
		stats.experienceBar.setValue(stats.experience);
		
		if(hasLeveled())
			levelUp();
	}
	
	private void levelUp(){
		if(stats.level <= 0){
			stats.maxHitpoints = 10;
			stats.maxMana = 10;
		}
		stats.level++;
		
		
		stats.maxHitpoints = stats.maxHitpoints + ((int)Math.ceil((double)stats.constitution/2) - 5) + (int)Math.ceil((double)stats.level/2);
		stats.hitpoints = stats.maxHitpoints;
		
		stats.maxMana = stats.maxMana + ((int)Math.ceil((double)stats.intelligence/2) - 5) + (int)Math.ceil((double)stats.level/2);
		stats.mana = stats.maxMana;
		System.out.println(stats.maxMana);
		System.out.println(stats.mana);
		
		if(stats.experience > stats.experienceToLevel)
			stats.experience = stats.experience - stats.experienceToLevel;
		else
			stats.experience = 0;
		
		stats.experienceToLevel = Math.round((float) (base_xp * (Math.pow((stats.level+1), factor_xp))));	
		PlayMenuBuilder.logInput.add("You are now level " + stats.level + "!");
		PlayMenuBuilder.logInput.add(stats.experienceToLevel + " experience to level " + (stats.level + 1) + ".");
		
		stats.healthBar.setRange(0, stats.maxHitpoints);
		stats.healthBar.setValue(stats.maxHitpoints);
		
		stats.manaBar.setRange(0, stats.maxMana);
		stats.manaBar.setValue(stats.maxMana);
		
		stats.experienceBar.setRange(0, stats.experienceToLevel);
		stats.experienceBar.setValue(stats.experience);

		//PlayMenuBuilder.newStr = "You are now level " + stats.level + "! " + stats.experienceToLevel + " to level " + (stats.level + 1) + ".";
		//PlayMenuBuilder.updateCombatLog();
	}
	
	private boolean hasLeveled(){
		if(stats.experience >= stats.experienceToLevel)
			return true;
		
		return false;
	}

}
